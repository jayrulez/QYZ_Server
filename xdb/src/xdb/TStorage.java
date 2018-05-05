package xdb;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

/**
 * 类型化存储接口,应用表。
 *
 */
final class TStorage<K, V> extends Storage {
	//////////////////////////////////////////////////////////////////
	// management
	/**
	 * 打开以后，不能更改配置。
	 */
	TStorage(Logger logger, XdbConf xconf, TableConf tconf) {
		super(logger, xconf, tconf);
	}

	////////////////////////////////////////////////////////
	// 并发优化。 参考旧版： SVN revision 1512
	//
	// 数据： changed(C) marshal(M) snapshot(S) 的并发，
	// 线程： Cleaner, Checkpoint, Procedure(多个线程)
	//
	// -------------------------------------------------------------------
	// 操作方法              数据  线程          锁(NEW表示这个锁的获取是新增的)
	// -------------------------------------------------------------------
	// onRecordChange     CM   Procedure   flushReadLock + 记录锁
	// isChangedOrUnknown CM   Cleaner     flushReadLock(NEW) + TTableCache.lru
	// marshalN           CM   Checkpoint  记录锁 + 《把C倒进到M并且对当前C的系列化记录》
	// snapshot marshal0  CMS  Checkpoint  flushWriteLock 唯我独尊
	// flush              S    Checkpoint  snapshotClearWriteLock(NEW) : 只有清除的瞬间需要这个锁。
	// find + exist       S    Procedure   snapshotClearReadLock(NEW) + 记录锁
	// -------------------------------------------------------------------
	// 其他
	// 1 ConcurrentMap 遍历操作只能在一个线程中执行，可以和其他操作并发。对于CMS的访问都不需要加锁保护。
	// 2 snapshotClearReadLock 除了清除的瞬间，snapshot 的访问是允许并发的。

	private ConcurrentMap<K, TRecord<K, V>> changed  = xdb.util.Misc.newConcurrentMap();
	private ConcurrentMap<K, TRecord<K, V>> marshal  = xdb.util.Misc.newConcurrentMap();
	private ConcurrentMap<K, TRecord<K, V>> snapshot = xdb.util.Misc.newConcurrentMap();
	private final ReentrantReadWriteLock snapshotClearLock = new ReentrantReadWriteLock(); 

	// from TTable
	void onRecordChange(TRecord<K, V> r) {
		// 事务内调用，已获得锁: flushReadLock + r的记录锁。
		// 使用 ConcurrentMap 的并发行得到更高的并发。
		if (r.getState() == TRecord.State.REMOVE) {
			changed.remove(r.getKey());
			marshal.remove(r.getKey());
		} else {
			changed.put(r.getKey(), r);
		}
	}

	// from TTableCache.Cleaner
	boolean isChangedOrUnknown(K key) {
		// 已获得锁: flushReadLock
		// 必须保证 dirty 的记录不能返回 false。 允许把干净的记录看成是脏的，这种情况下认为是 Unknown。
		return changed.containsKey(key) || marshal.containsKey(key);
	}

	// 只有 Checkpoint 线程会修改这些值。 JMX需要读取，用个 volatile 表示一下。
	private volatile long countMarshalN = 0;
	private volatile long countMarshal0 = 0;
	private volatile long countFlush = 0;
	private volatile long countSnapshot = 0;
	private volatile long countMarshalNTryFail = 0;
	// package private . 在 TRecord.flush 里访问。
	volatile long flushKeySize = 0;
	volatile long flushValueSize = 0;

	/**
	 * 多趟系列化。
	 */
	@Override
	int marshalN() {
		// 什么锁都没拿到。

		/*
		 * <也许先看看 Checkpoint.java 开头的说明和TStorage.isChangedOrUnknown中的注释比较好。>
		 *
		 * REMOVE 优化
		 *     REMOVE 状态：新增且在保存之前删除掉的记录状态为 REMOVE。
		 *     原想法是，所有的修改操作，都在保存时处理，也就是说REMOVE状态记录在snapshot时才会从cache中清除。
		 *     为了加快内存收集，这种记录在事务提交时就从cache中清除。
		 *
		 * marshalN
		 *     把 changed 中的记录进行系列化处理，然后移到marshal中。伪代码如下：
		 *     for ( TRecord<K, V> r : changed.values() ) r.marshalN();
		 *     marshal.putAll(changed);
		 *     changed.clear();
		 *     对于 marshalN 来说，做多少算多少就可以了。并不要求原子的完成以上操作。
		 *     利用 ConcurrentMap 本身的并发性，以及容忍由于没有锁保护而产生的《不和谐结果》。
		 *     并不对 marshalN 进行锁保护。
		 *
		 * 不和谐结果
		 *     到底是什么不和谐的结果请看下面代码中的注释。
		 */

		int marshaled = 0;
		int tryFail = 0;

		// 这里不能使用putAll，而是使用 iterator，是因为没有锁保护，得不到 putAll 进去的记录数。
		for (Iterator<TRecord<K, V>> it = changed.values().iterator(); it.hasNext(); /* none */) {
			/*
			 * 遍历时可能出现 onRecordChange 调用。
			 * 当发生 put 时
			 *    a) 如果在 it 后面，那么稍后就可以处理它，没问题；
			 *    b) 如果是当前 it ，马上就处理它，有一点点不好，如下：
			 *       这里需要确认一下，当前 it.next() 返回的是put前引用或者是新的引用。
			 *       由于对于某个key来说，它对应的记录对象自创建起，经过flush之前都不会变。
			 *       ToDo 这里不管返回新旧，逻辑上都是正确的。以后再确认这个问题。
			 *    c) 如果在 it 之前，那么这一次就不处理了，下一次 marshalN 再处理，没问题；
			 *
			 * 当发生 remove 时
			 *    a) 如果在 it 后面，稍后看不到它，没问题。
			 *    b) 如果是当前 it ，会得到旧的记录，然后会被处理并加入到marshal中。《不和谐结果之一。》
			 *       这个记录只能等 snapshot 来清除它了。REMOVE 提前删除优化失效。
			 *       如果发生了此冲突，表示记录正在使用，后续的 tryMarshalN 一般会失败，不会把这个记录
			 *       加到(put)到marshal中，实际上降低了这种冲突发生的概率。
			 *    c) 如果在 it 之前，白做了一次系列化操作而已，没问题。marshalN本来就会白做。
			 */
			TRecord<K, V> r = it.next();
			if (r.tryMarshalN()) { // 正在使用事务中的记录，这里会失败，保留在 changed 中，没问题。
				/*
				 * <必须先 put 后 remove>
				 *     允许记录出现在两个Map中，但不允许记录不出现在任何一个Map中。see isChangedOrUnknown。
				 */
				marshal.put(r.getKey(), r);
				/*
				 * 《不和谐结果之二》
				 * 如果在这个时间窗口发生了 remove。那么也会出现垃圾记录，只能等 snapshot 来清理。
				 */
				it.remove();
				++ marshaled;
			} else 
				++ tryFail;
		}
		this.countMarshalN += marshaled;
		this.countMarshalNTryFail += tryFail;
		return marshaled;
	}

	/**
	 * 系列化。snapshot。
	 */
	@Override
	int marshal0() {
		// snapshot 步骤。 已获得 flushWriteLock。很安全，随便用。
		marshal.putAll(changed);
		for (TRecord<K, V> r : changed.values())
			r.marshal0();
		final int size = changed.size();
		changed.clear();
		this.countMarshal0 += size;
		return size;
	}

	/**
	 * 完整性保证点，当所有的Storage的快照建立完成时，一个XDB.checkpoint就完成了。。
	 */
	@Override
	int snapshot() {
		// flushWriteLock。 顺便搞。
		final ConcurrentMap<K, TRecord<K, V>> tmp = this.snapshot;
		this.snapshot = this.marshal;
		this.marshal = tmp;
		for (TRecord<K, V> r : this.snapshot.values())
			r.snapshot();
		this.countSnapshot += snapshot.size();
		return snapshot.size();
	}

	@Override
	int flush() {
		// 此时，什么锁都没有拿到。

		// flush 记录数等于 snapshot 的记录数。这里不统计 REMOVE 状态的记录。
		// 这样，REMOVE状态记录数量 = snapshotCount - flushCount。
		// 可以用来看 marshalN 产生的垃圾的数量。
		int count = 0;
		for (TRecord<K, V> r : this.snapshot.values()) {
			// flush. 对 snapshot 的数据来说，此时是只读的，也不会有人去修改，不需要加锁。
			if (r.flush(this))
				++ count;
		}

		// clear. 全部都写到 Storage 中，此时可以安全清除掉。
		Map<K, TRecord<K, V>> tmp = null;
		// 这个锁的作用就是对 snapshot 的引用提供保护。很不幸，这里无法用volatile.
		// 把 snapshot 取出来慢慢 clear。马上置空，使得 Procedure 可以同步访问。
		final Lock writeLock = snapshotClearLock.writeLock();
		writeLock.lock(); 
		try {
			tmp = this.snapshot;
			this.snapshot = xdb.util.Misc.newConcurrentMap();
		} finally {
			writeLock.unlock();
		}
		// 到这里，所有的Procedure已经拿不到需要清除的snapshot了。可以安全的不加锁的清除记录。
		for (TRecord<K, V> r : tmp.values())
			r.clear();
		this.countFlush += count;
		return count;
	}

	
	/**
	 * 在记录锁内使用。
	 */
	final boolean exist(K key, TTable<K, V> table) {
		// 事务内，已获得锁： flushReadLock + 记录锁。
		final Lock readLock = snapshotClearLock.readLock();
		readLock.lock(); // 允许存储过程并发访问 snapshot，这个仅仅和 flush 的 clear 互斥。
		try {
			TRecord<K, V> r = this.snapshot.get(key);
			if (null != r)
				return r.exist();
		} finally {
			readLock.unlock();
		}
		return super.exist(table.marshalKey(key));
	}

	/**
	 * 在记录锁内使用。
	 */
	V find(K key, TTable<K, V> table) {
		// 事务内，已获得锁： flushReadLock + 记录锁。
		OctetsStream value = null;
		boolean foundInSnapshot = false;

		final Lock readLock = snapshotClearLock.readLock();
		readLock.lock(); // 允许存储过程并发访问 snapshot，这个仅仅和 flush 的 clear 互斥。
		try {
			TRecord<K, V> r = this.snapshot.get(key);
			if (null != r) {
				foundInSnapshot = true;
				// snapshot 数据是只读的，clear只是释放引用，可以看成是不可变变量。
				// 不需要保护，在锁外面慢慢 unmarshalValue。
				value = r.find(); 
			}
		} finally {
			readLock.unlock();
		}

		try {
			if (foundInSnapshot) {
				if (null != value)
					return table.unmarshalValue(value);
				return null;
			}
			// find in storage
			value = super.find(table.marshalKey(key));
			if (null != value)
				return table.unmarshalValue(value);
			return null;
		} catch (MarshalException me) {
			throw new xio.MarshalError("unmarshal", me);
		}
	}

	public long getCountFlush() {
		return this.countFlush;
	}

	public long getCountMarshal0() {
		return this.countMarshal0;
	}

	public long getCountMarshalN() {
		return this.countMarshalN;
	}

	public long getCountMarshalNTryFail() {
		return this.countMarshalNTryFail;
	}

	public long getCountSnapshot() {
		return countSnapshot;
	}

	public long getFlushKeySize() {
		return flushKeySize;
	}

	public long getFlushValueSize() {
		return flushValueSize;
	}

	/*
	int flush() {
		//Trace.debug("flush=" + changed.values());
		changedLock.lock();
		try {
			for (TRecord<K, V> r : changed.values()) {
				switch (r.getState()) {
				case INDB_GET:
					replaceUnsafe(r.encodeKey(), r.encodeValue());
					break;
				case INDB_REMOVE:
					removeUnsafe(r.encodeKey());
					r.getTable().getCache().remove(r.getKey());
					break;
				case INDB_ADD:
					replaceUnsafe(r.encodeKey(), r.encodeValue());
					r.setState(TRecord.State.INDB_GET);
					break;
				case ADD:
					insertUnsafe(r.encodeKey(), r.encodeValue());
					r.setState(TRecord.State.INDB_GET);
					break; 
				case REMOVE:
					// REMOVE状态,在事务结束的时候从cache中被删除.此处不可能出现.完备起见，也给一个实现。
					Trace.warn("flush invalid record state: " + r);
					r.getTable().getCache().remove(r.getKey());
					break;
				}
			}
			int count = changed.size();
			changed.clear();
			return count;
		} finally {
			changedLock.unlock();
		}
	}
	*/
}

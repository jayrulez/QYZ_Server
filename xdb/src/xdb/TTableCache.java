package xdb;

import xdb.TRecord.State;
import xdb.logs.AddRemoveInfo;
import xdb.logs.LogNotify;

/**
 * 表格缓存接口类。
 *
 * @param <K>
 * @param <V>
 */
public abstract class TTableCache<K, V> {
	private volatile TTable<K, V> table; // 在初始化时设置一次，不支持修改。
	private volatile CacheRemovedHandle<K, V> removedhandle;
	private volatile int capacity;

	////////////////////////////////////////////////////////////////////
	// 构造和初始化。
	TTableCache() {
		// 全部的初始化和参数设置都放在initialize方法中。
		// 不在构造函数中初始化。
	}

	/**
	 * 初始化cache。
	 * <p><b>子类可以重载以增加自己的配置和初始化实现。</b>
	 * <p><b>子类重载时必须调用super.initialize以初始化父类。</b>
	 * @param table
	 * @param conf
	 */
	void initialize(TTable<K, V> table, TableConf conf) {
		this.table = table;
		this.capacity = conf.getCacheCapacity();
	}

	/**
	 * 创建 cache 实例辅助方法。
	 * @param <K>
	 * @param <V>
	 * @param table
	 * @return
	 */
	static <K, V> TTableCache<K, V> newInstance(TTable<K, V> table, TableConf conf) {
		String cacheClass = conf.getOtherAttr("cacheClass").trim();
		if (cacheClass.isEmpty()) {
			cacheClass = Xdb.getInstance().getConf().getDefaultTableCache();
			if (cacheClass.isEmpty())
				//cacheClass = "xdb.TTableCacheConcurrentMap"; // default cache implement
				cacheClass = "xdb.TTableCacheLRU"; // default cache implement
		}

		try {
			@SuppressWarnings("unchecked")
			TTableCache<K, V> theCache = (TTableCache<K, V>)Class.forName(cacheClass).newInstance();
			theCache.initialize(table, conf);
			return theCache;
		} catch (Throwable e) {
			throw new XError(e);
		}
	}

	/**
	 * 清空 cache。
	 * Removes all of the mappings from this map. The map will be empty after this call returns.
	 * 清除所有的缓存记录，即使记录是脏的。
	 * @throws UnsupportedOperationException 如果表格不是内存表。
	 */
	public abstract void clear();

	/**
	 * 立即清理缓存。清理超出缓存容量的记录。脏数据不会被清理。
	 */
	public abstract void clean();

	/**
	 * 遍历 cache。对于持久表，这个方法只能看到缓存中的记录。
	 * <b>不建议随便使用。</b>
	 * @see CacheQuery
	 */
	public abstract void walk(CacheQuery<K, V> query);

	/**
	 * 得到缓存中的记录数量。这个值仅供参考，不能用于逻辑判断。
	 * @return 记录数量
	 */
	abstract int getSize();

	public final TTable<K, V> getTable() {
		return table;
	}

	/**
	 * @return 缓存容量
	 */
	public final int getCapacity() {
		return this.capacity;
	}

	/**
	 * 设置缓存容量。
	 * @param capacity
	 */
	public synchronized void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	//////////////////////////////////////////////////////////////////
	// CacheRemovedHandle ：这个功能看起来不是很有用。
	/**
	 * 内存表的记录由于容量限制被缓存清除时，会回调设置的接口通告应用。
	 * <p>注意：1. 只有内存表需要回调；2. 主动删除不会回调。
	 * @see CacheRemovedHandle
	 * @param handle
	 */
	public synchronized void setRemovedhandle(CacheRemovedHandle<K, V> handle) {
		this.removedhandle = handle;
	}

	/**
	 * 
	 * @return CacheRemovedHandle
	 */
	public final CacheRemovedHandle<K, V> getRemovedhandle() {
		return this.removedhandle;
	}

	// package private
	abstract TRecord<K, V> get(K k);
	abstract void addNoLog(K key, TRecord<K, V> r);
	abstract void add(K key, TRecord<K, V> r);
	abstract TRecord<K, V> remove(K k);

	/**
	 * 辅助方法
	 * @param records
	 * @param query
	 */
	final void _walk_notify_query(Object[] records, CacheQuery<K, V> query) {
		for (Object _r : records) {
			@SuppressWarnings("unchecked")
			final TRecord<K, V> r = (TRecord<K, V>)_r; // unchecked
			final Lockey lock = r.getLockey();
			lock.lock();
			try {
				final V value = r.getValue();
				if (null != value) // 被删除的记录不回调。
					query.onQuery(Consts.toConst(r.getKey()), Consts.toConst(value));
			} finally {
				lock.unlock();
			}
		}
	}

	/**
	 * 在当前事务中添加日志：当添加或者删除操作时，往cache中添加的记录需要记录日志，使得事务回滚时能清除数据。
	 * <p><b>这个方法仅用于子类实现</b>
	 * @param key
	 * @param r
	 */
	final void logAddRemove(K key, TRecord<K, V> r) {
		Transaction.currentSavepoint().add(r.getLogKey(), new LogAddRemove(key, r));
	}

	private class LogAddRemove implements Log {
		private K key;
		private State saved_state;
		private TRecord<K, V> record;

		LogAddRemove(K key, TRecord<K, V> record) {
			this.key         = key;
			this.record      = record;
			this.saved_state = record.getState();
		}

		@Override
		public void commit() {
			// 新加入Cache的记录，由record的commit完成xdbLogNotify。这里不处理。
			if (!record.commit())
				TTableCache.this.remove(key);
			record.getTable().onRecordChanged(record, new LogNotify(new AddRemoveInfo(true, saved_state)));
		}

		@Override
		public void rollback() {
			if (record.rollback())
				TTableCache.this.remove(key);
		}
	}

	/**
	 * 删除记录，当记录状态允许时。
	 * <p><b>警告：</b>这个方法调用remove(K key)完成真正的删除。
	 * 可能不允许在iterator的循环中使用。
	 * @param record
	 * @return
	 */
	final boolean tryRemoveRecord(TRecord<K, V> record) {
		// 保持记录锁。只能清除不是正在使用的记录。 
		// 由于 lru 采用 access order，通常最老的记录都不在使用中。
		final Lockey lockey = record.getLockey();
		final K key = record.getKey();
		if (false == lockey.tryLock()) {
			Trace.debug("cleaner IS IT RARE? " + record + " size=" + getSize());
			// 原来这里是 continue。发现锁定失败，马上退出循环会比较好。
			// 一般来说，只会在大量增加或者遍历修改记录时会发生这种情况。
			return false;
		}
		try {
			final TStorage<K, V> storage = record.getTable().getStorage();
			if (null == storage) {
				//Trace.debug("cleaner remove " + record + " size=" + lru.size());
				remove(key);
				// MEMORY 生成一个回调,通知一下应用。
				this.notifyRemovedhandle(record);
				return true;
			}

			if (storage.isChangedOrUnknown(key)) {
				//Trace.debug("cleaner isChanged " + record + " size=" + lru.size());
				// 原来这里是 continue。发现记录被使用或者状态无法判断，马上退出循环会比较好。
				// 一般来说，只会在大量增加或者遍历修改记录时会发生这种情况。
				return false;
			}

			//Trace.debug("cleaner remove " + record + " size=" + lru.size());
			remove(key);
			return true;
		} finally {
			lockey.unlock();
		}
	}

	final void notifyRemovedhandle(TRecord<K, V> record) {
		final CacheRemovedHandle<K, V> handle = this.getRemovedhandle();
		if (null != handle)
			Xdb.executor().execute(new Removed<K, V>(record, handle));
	}

	private static class Removed<K, V> implements Runnable {
		K key;
		V value;
		CacheRemovedHandle<K, V> handle;

		Removed(TRecord<K, V> r, CacheRemovedHandle<K, V> handle) {
			this.key = r.getKey();
			this.value = r.getValue();
			this.handle = handle;
		}

		@Override
		public void run() {
			handle.recordRemoved(key, value);
		}
	}

}

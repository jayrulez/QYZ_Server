package xdb;

import com.goldhuman.Common.Octets;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

import java.util.concurrent.atomic.*;

import xdb.logs.Listener;
import xdb.logs.ListenerMap;
import xdb.logs.LogRecord;

import java.util.concurrent.locks.Lock;

/**
 * xtable是xbean的管理器，支持并发访问，自动持久化。管理缓存，统计性能数据。
 * 只能在Procedure中操作xtable。任何其他访问方式，xdb都会抛出异常。
 * 管理器使用Map实现，也就是说，任何xtable都有唯一关键字。关键字必须是可比较的，需要实现Comparable。
 * 目前只允许简单类型当作关键字类型，刚好都是可比较的。
 * 从xtable得到的xbean都是内部实例的引用。直接对引用进行读取和修改，没有单独的保存操作。
 * <B> 从xtable得到引用只在事务执行过程中有效，可以传递给嵌套过程或函数，但不能保存下来。 </B>
 *
 * @param <K>
 * @param <V>
 */
public abstract class TTable<K, V> extends Table implements TTableMBean {
	private String lockname; // initialize in open(...)
	private int lockId; // initialize in initialize(...)
	private TableConf conf;  // initialize in open。XdbConf中表格的配置的引用。
	private xdb.util.AutoKey<K> autoKey;
	private javax.management.ObjectName mbeanObjectName = null; // 保存下来，用于注销

	/////////////////////////////////////////////////////////////////////
	public final K nextKey() {
		return autoKey.next(); // autoKey 的创建由子类初始化。这里不知道K的真正类型。
	}

	public final xdb.util.AutoKey<K> getAutoKey() {
		return autoKey;
	}

	/**
	 * 获得key关联的值，不存在返回null。
	 * 
	 * 不管是否存在都保持锁。先严格。
	 * 
	 * @param key
	 * @return value的引用。 
	 */
	public final V get(K key) {
		return get(key, true);
	}

	/**
	 * 获得key关联的值，如果不存在，返回value。
	 * 
	 * 如果值存在，保持锁，否则不保持锁。
	 * 
	 * @param key
	 * @param value
	 * @return 如果key存在返回记录 value 的引用，否则返回传入的value。
	 */
	public final V get(K key, V value) {
		V cur = get(key, false);
		if (null != cur)
			return cur;
		return value;
	}

	/**
	 * 插入记录，存在，抛出异常。
	 * @param key
	 * @param value
	 */
	public final void insert(K key, V value) {
		if (false == add(key, value))
			throw new XError("insert key=" + key + " value=" + value);
	}

	/**
	 * 删除记录，不存在，抛出异常。
	 * @param key
	 */
	public final void delete(K key) {
		if (false == remove(key))
			throw new XError("delete key=" + key);
	}

	/**
	 * 如果存在，则更新，返回true。保持锁。
	 * 否则返回null。没有保持锁。
	 * @param key
	 * @param value
	 * @return 更新是否成功
	 */
	public final boolean update(K key, V value) {
		V cur = get(key, false);
		if (null == cur)
			return false;
		delete(key);
		insert(key, value);
		return true;
	}

	/**
	 * 加入记录。
	 * @param key
	 * @param value
	 * @return 如果纪录存在，返回原来的值。如果第一次加入，返回null
	 */
	public final V put(K key, V value) {
		V origin = get(key, true);
		if (null == origin) {
			insert(key, value);
			return null;
		}
		delete(key);
		insert(key, value);
		return origin;
	}

	/**
	 * 如果key对应的值已经存在，返回关联的值。
	 * 否则建立新的关联，返回null。不返回新加入的值，这样外面可以区分是否第一次加入。
	 * 
	 * 操作完整以后，保持了当前记录的锁。
	 * 
	 * @param key
	 * @param value
	 * @return 如果key对应的值已经存在，返回关联的值，否则返回null。
	 */
	public final V putIfAbsent(K key, V value) {
		V origin = get(key, true);
		if (null == origin) {
			insert(key, value);
			return null;
		}
		return origin;
	}

	////////////////////////////////////////////////////////////////////////////
	/**
	 * 加入一条记录。
	 * 
	 * 加入成功则保持锁，否则不保持。
	 * 
	 * @param key
	 * @param value
	 * @return boolean 添加是否成功。
	 */
	public final boolean add(K key, V value) {
		if (null == key)
			throw new NullPointerException("key is null");
		if (null == value)
			throw new NullPointerException("value is null");

		if (null != this.autoKey)
			this.autoKey.accept(key);

		countAdd.incrementAndGet();
		Lockey lockey = Lockeys.get(this, key);
		lockey.lock();
		try {
			TRecord<K, V> r = cache.get(key);
			if (null != r) {
				Transaction.current().add(lockey);
				return r.add(value);
			}

			countAddMiss.incrementAndGet();
			if (_exist(key)) {
				countAddStorageMiss.incrementAndGet();
				return false;
			}

			cache.add(key, new TRecord<K, V>(this, value, lockey, TRecord.State.ADD));
			Transaction.current().add(lockey);
			return true;
		} finally {
			lockey.unlock();
		}
	}

	/**
	 * 删除记录。
	 * 不管是否成功，都保持锁。
	 * @param key
	 * @return remove 是否成功。
	 */
	public final boolean remove(K key) {
		if (null == key)
			throw new NullPointerException("key is null");

		final Transaction currentT = Transaction.current();
		currentT.rmvCachedTRecord(this, key);

		countRemove.incrementAndGet();
		Lockey lockey = Lockeys.get(this, key);
		lockey.lock();
		try {
			TRecord<K, V> r = cache.get(key);
			if (null != r) {
				currentT.add(lockey);
				//Transaction.current().rmvCachedTRecord(this, r.getKey());
				return r.remove();
			}

			countRemoveMiss.incrementAndGet();
			boolean exists = _exist(key);
			if (false == exists)
				countRemoveStorageMiss.incrementAndGet();
			cache.add(key, new TRecord<K, V>(this, null, lockey, exists));
			currentT.add(lockey);
			return exists;
		} finally {
			lockey.unlock();
		}
	}

	/**
	 * 查找记录关联。
	 * 记录存在则返回，并且保持锁。如果不存在，则根据参数holdNull决定是否保持锁。
	 * 
	 * @param key
	 * @param holdNull
	 * @return value 的引用
	 */
	public final V get(K key, boolean holdNull) {
		if (null == key)
			throw new NullPointerException("key is null");
		
		countGet.incrementAndGet();

		/////////////////////////////////////////////////////
		// get from transaction cache first
		final Transaction currentT = Transaction.current();
		TRecord<K, V> rCached = currentT.getCachedTRecord(this, key);
		if (rCached != null)
			return rCached.getValue();

		/////////////////////////////////////////////////////
		// real get
		Lockey lockey = Lockeys.get(this, key);
		lockey.lock();
		try {
			TRecord<K, V> r = cache.get(key);
			if (null == r) {
				countGetMiss.incrementAndGet();
				V value = _find(key);
				if (null == value) {
					countGetStorageMiss.incrementAndGet();
					if (holdNull)
						currentT.add(lockey);
					return null;
				}

				r = new TRecord<K, V>(this, value, lockey, TRecord.State.INDB_GET);
				cache.addNoLog(key, r);
			}

			currentT.add(lockey);
			currentT.addCachedTRecord(this, r);
			return r.getValue();
		} finally {
			lockey.unlock();
		}
	}

	/**
	 * 选择记录字段。
	 * 不保持锁;
	 * 不能在回调时，保存变量的引用，虽然不可变变量可以保存，但也不要这么做;
	 * 如果不存在，返回null，否则通过回调 field.get() 得到的字段的拷贝(不可变变量直接返回)。
	 * <B>由于不保持锁，XGEN在生成实现代码时，只支持不可变字段的select(通过直接返回引用实现)
	 * 和整个value的select(通过xbean.toData()实现)。可变类型的字段不支持局部select。</B>
	 * @param key
	 * @param field
	 * @return 
	 */
	public final <F> F select(K key, TField<V, F> field) {
		if (null == key)
			throw new NullPointerException("key is null");

		countGet.incrementAndGet();
		Lockey lockey = Lockeys.get(this, key);
		lockey.lock();
		try {
			TRecord<K, V> r = cache.get(key);
			if (null == r) {
				countGetMiss.incrementAndGet();
				V value = _find(key);
				if (null == value) {
					countGetStorageMiss.incrementAndGet();
					return null;
				}

				r = new TRecord<K, V>(this, value, lockey, TRecord.State.INDB_GET);
				cache.addNoLog(key, r);
			}

			final V value = r.getValue();
			if (null == value)
				return null;
			return field.get(value);
		} finally {
			lockey.unlock();
		}
	}

	////////////////////////////////////////////////////////////////////
	private ThreadLocal<LogRecord<K, V>> logRecord = new ThreadLocal<LogRecord<K, V>>() {
		// 这里并不需要为所有线程创建，实际上只有执行事务的线程才需要。
		// 或者限制为只有 Worker 才创建。为了简化耦合，不存在就自动创建了。
		@Override
		protected LogRecord<K, V> initialValue() {
			return new LogRecord<K, V>(TTable.this.newValue());
		}
	};

	// 所有的监听者。
	private ListenerMap listenerMap = new ListenerMap();

	private final String toFullVarName(String [] names) {
		// 严格参数检查，否则直接把names拼起来就可以了。
		xdb.logs.VarNames varNames = new xdb.logs.VarNames(names);
		return new LogRecord<K, V>(TTable.this.newValue()).toFullVarName(varNames);
	}

	public final void removeListener(Listener l, String ... names) {
		listenerMap.remove(toFullVarName(names), l);
	}

	public final String addListener(Listener l, String ... names) {
		return listenerMap.add(toFullVarName(names), l);
	}

	public final boolean hasListener() {
		return listenerMap.hasListener();
	}

	@Override
	final void logNotify() {
		try {
			logRecord.get().logNotify(listenerMap);
		} catch (Throwable e) {
			// 发生错误时,清除掉所有收集数据,下一次需要的时候重建。
			logRecord.remove();
			Trace.fatal("TTable.logNotify", e);
		}
	}

	////////////////////////////////////////////////////////////////////
	// record cache
	private TTableCache<K, V> cache;

	public final TTableCache<K, V> getCache() {
		return cache;
	}

	/////////////////////////////////////////////////////////////////////
	// 存储接口和类型转换辅助函数
	private TStorage<K, V> storage;

	final TStorage<K, V> getStorage() {
		return storage;
	}

	// dirty record
	final void onRecordChanged(TRecord<K, V> r, xdb.logs.LogNotify ln) {
		logRecord.get().onChanged(this, r, ln);

		if (r.getState() == TRecord.State.REMOVE)
			cache.remove(r.getKey()); // 刷新前就被删除了，清除 cache。对于 memory 表，相当于真的删除了。

		if (null != storage)
			storage.onRecordChange(r);
	}

	@Override
	protected final void initialize(Tables tables) {
		this.autoKey = bindAutoKey();
		this.lockId = Lockeys.getInstance().getLockId(lockname);
	}

	/**
	 * autoIncrement 的表格需要重载实现这个方法。当前类是模板的，不知道正确的 K 类型。
	 */ 
	protected xdb.util.AutoKey<K> bindAutoKey() {
		return null;
	}

	public TableConf getConf() {
		return conf;
	}

	@Override
	final Storage open(XdbConf xconf, Logger logger) {
		if (null != storage)
			throw new XError("table has opened : " + getName());

		conf = xconf.getTableConf(getName());
		if (null == conf)
			throw new XError("no configuration for table '" + getName() + "'");

		lockname = conf.getLockName();
		cache = TTableCache.newInstance(this, conf);

		switch (conf.getPersistence()) {
		case MEMORY: storage = null; break;
		case DB:     storage = new TStorage<K, V>(logger, xconf, conf); break;
		}
		this.mbeanObjectName = Xdb.mbeans().register(this, "xdb:type=Tables,name=" + getName());
		return storage;
	}

	@Override
	final void close() {
		if (null != storage) {
			storage.close();
			storage = null;
		}
		Xdb.mbeans().unregister(this.mbeanObjectName);
	}

	public abstract V newValue();
	public abstract OctetsStream marshalKey(K key);
	public abstract OctetsStream marshalValue(V value);
	public abstract K unmarshalKey(OctetsStream os) throws MarshalException;
	public abstract V unmarshalValue(OctetsStream os) throws MarshalException;

	private final boolean _exist(K key) {
		if (null != storage)
			return storage.exist(key, this);
		return false;
	}

	private final V _find(K key) {
		if (null != storage)
			return storage.find(key, this);
		return null;
	}

	/**
	 * 返回表格的第一个key
	 * @return
	 */
	public K firstKey() {
		if (null == storage)
			throw new XError("Persistence is not DB");
		OctetsStream first = storage.firstKey();
		if (null == first)
			return null;
		try {
			return unmarshalKey(first);
		} catch (Throwable e) {
			xdb.Trace.error("firstKey unmarshalKey", e);
			return null;
		}
	}
	
	/**
	 * 根据参数，返回表格下一个key。
	 * @param key
	 * @return
	 */
	public K nextKey(K key) {
		if (null == storage)
			throw new XError("Persistence is not DB");
		OctetsStream next = storage.nextKey(marshalKey(key));
		if (null == next)
			return null;
		try {
			return unmarshalKey(next);
		} catch (Throwable e) {
			xdb.Trace.error("nextKey unmarshalKey", e);
			return null;
		}
	}

	/**
	 * walk时的回调接口
	 */
	public static interface IWalk<K, V> {
		public boolean onRecord(K k, V v);
	}
	
	/**
	 * 如果关心walk时出错的处理，可以使用IWalkDetail接口，可以获得没有unmarshal原始数据
	 */
	public static interface IWalkDetail<K, V> extends IWalk<K, V> {
		public boolean onError(byte[] key, byte[] data);
	}

	/**
	 * 可以浏览已经flush到C++页面中的所有数据；浏览不到xdb.cache的脏页
	 * 并发特性：不能同flush并发，不能同checkpoint并发
	 * 缓存特性：共用C++的内存页面
	 * 
	 * 不需要在事务中执行
	 * @param iwalk
	 */
	public final void walk(final IWalk<K, V> iwalk) {
		if (null == iwalk)
			throw new NullPointerException();
		if (null != storage) {
			Lock flushLock = Xdb.getInstance().getTables().flushReadLock();
			Lock checkpointLock = Xdb.getInstance().getTables().checkpointLock();
			flushLock.lock();
			checkpointLock.lock();
			try {
				storage.walk(new Storage.IWalk() {
					@Override
					public boolean onRecord(byte[] key, byte[] data) {
						try {
							K k = unmarshalKey(OctetsStream.wrap(Octets.wrap(key, key.length)));
							V v = unmarshalValue(OctetsStream.wrap(Octets.wrap(data, data.length)));
							return iwalk.onRecord(k, v);
						} catch (Throwable e) {
							Trace.error("table:" + TTable.this.getTableName() + ",walk:" + iwalk.getClass().getName() + ",error:", e);
							if (iwalk instanceof IWalkDetail<?, ?>) {
								return ((IWalkDetail<K, V>)iwalk).onError(key, data);
							}
							return true;
						}
					}
				});
			} finally {
				flushLock.unlock();
				checkpointLock.unlock();
			}
		}
	}
	
	/**
	 * 只能浏览磁盘文件的数据
	 * @param ibrowse
	 */
	public final void browse(final IWalk<K, V> ibrowse) {
		// 默认使用 128 个页面来读取
		browse(ibrowse, 128);
	}
	
	/**
	 * 只能浏览磁盘文件的数据
	 * 并发特性：可以和flush过程并发，但是不能同checkpoint并发
	 * 缓存特性：浏览时新建PageHash，通过cache_size指定PageHash的页面限制，
	 * 
	 * 不需要在事务中执行
	 * @param iwalk
	 */
	public final void browse(final IWalk<K, V> ibrowse, int cache_size) {
		if (null == ibrowse)
			throw new NullPointerException();
		if (null != storage) {
			Lock checkpointLock = Xdb.getInstance().getTables().checkpointLock();
			checkpointLock.lock();
			try {
				storage.browse(new Storage.IWalk() {
					@Override
					public boolean onRecord(byte[] key, byte[] data) {
						try {
							K k = unmarshalKey(OctetsStream.wrap(Octets.wrap(key, key.length)));
							V v = unmarshalValue(OctetsStream.wrap(Octets.wrap(data, data.length)));
							return ibrowse.onRecord(k, v);
						} catch (Throwable e) {
							Trace.error("table:" + TTable.this.getTableName() + ",browse:" + ibrowse.getClass().getName() + ",error:", e);
							if (ibrowse instanceof IWalkDetail<?, ?>) {
								return ((IWalkDetail<K, V>)ibrowse).onError(key, data);
							}
							return true;
						}
					}
				}, cache_size);
			} finally {
				checkpointLock.unlock();
			}
		}
	}

	/////////////////////////////////////////////////////
	// MBean
	private AtomicLong countAdd = new AtomicLong();
	private AtomicLong countAddMiss = new AtomicLong();
	private AtomicLong countAddStorageMiss = new AtomicLong();

	private AtomicLong countGet = new AtomicLong();
	private AtomicLong countGetMiss = new AtomicLong();
	private AtomicLong countGetStorageMiss = new AtomicLong();

	private AtomicLong countRemove = new AtomicLong();
	private AtomicLong countRemoveMiss = new AtomicLong();
	private AtomicLong countRemoveStorageMiss = new AtomicLong();

	@Override
	public String getTableName() {
		return getName();
	}

	@Override
	public String getLockName() {
		return lockname;
	}

	int getLockId() {
		return lockId;
	}

	@Override
	public Persistence getPersistence() {
		return conf.getPersistence();
	}

	@Override
	public String getPersistenceName() {
		return conf.getPersistence().name();
	}

	@Override
	public void setCacheCapacity(int capacity) {
		cache.setCapacity(capacity);
	}

	@Override
	public int getCacheCapacity() {
		return cache.getCapacity();
	}

	@Override
	public String getCacheClassName() {
		return cache.getClass().getName();
	}

	@Override
	public int getCacheSize() {
		return cache.getSize();
	}

	@Override
	public long getCountAdd() {
		return countAdd.get();
	}

	@Override
	public long getCountAddMiss() {
		return countAddMiss.get();
	}

	@Override
	public long getCountAddStorageMiss() {
		return countAddStorageMiss.get();
	}

	@Override
	public long getCountGet() {
		return countGet.get();
	}

	@Override
	public long getCountGetMiss() {
		return countGetMiss.get();
	}

	@Override
	public long getCountGetStorageMiss() {
		return countGetStorageMiss.get();
	}

	@Override
	public long getCountRemove() {
		return countRemove.get();
	}

	@Override
	public long getCountRemoveMiss() {
		return countRemoveMiss.get();
	}

	@Override
	public long getCountRemoveStorageMiss() {
		return countRemoveStorageMiss.get();
	}

	// compute format
	private String format(long miss, long ops) {
		return String.format("%.2f", (double)(ops-miss) / ops);
	}

	@Override
	public String getPercentAddHit() {
		return format(getCountAddMiss(), getCountAdd());
	}

	@Override
	public String getPercentGetHit() {
		return format(getCountGetMiss(), getCountGet());
	}

	@Override
	public String getPercentRemoveHit() {
		return format(getCountRemoveMiss(), getCountRemove());
	}

	@Override
	public String getPercentCacheHit() {
		return format(getCountAddMiss() + getCountRemoveMiss() + getCountGetMiss(),
				getCountAdd() + getCountRemove() + getCountGet());
	}

	@Override
	public long getStorageCountFlush() {
		return null != storage ? storage.getCountFlush() : -1;
	}

	@Override
	public long getStorageCountMarshal0() {
		return null != storage ? storage.getCountMarshal0() : -1;
	}

	@Override
	public long getStorageCountMarshalN() {
		return null != storage ? storage.getCountMarshalN() : -1;
	}

	@Override
	public long getStorageCountMarshalNTryFail() {
		return null != storage ? storage.getCountMarshalNTryFail() : -1;
	}

	@Override
	public long getStorageCountSnapshot() {
		return null != storage ? storage.getCountSnapshot() : -1;
	}

	@Override
	public long getStorageFlushKeySize() {
		return null != storage ? storage.flushKeySize : -1;
	}

	@Override
	public long getStorageFlushValueSize() {
		return null != storage ? storage.flushValueSize : -1;
	}
}

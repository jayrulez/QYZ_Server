package xdb.util;

import java.util.Calendar;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import xdb.Xdb;

public abstract class ConcurrentCache<K, V> implements ConcurrentCacheMBean {
	static class Value<V> {
		private volatile long accessTime;
		private volatile V value;

		Value() {
		}

		Value(V value) {
			this.value = value;
		}

		final V access() {
			this.accessTime = System.nanoTime();
			return value;
		}
	}

	private String name;
	private ConcurrentMap<K, Value<V>> cache = xdb.util.Misc.newConcurrentMap();
	private volatile ConcurrentCacheConf conf;
	private AtomicLong countGet = new AtomicLong();
	private AtomicLong countMiss = new AtomicLong();
	private final javax.management.ObjectName mbeanObjectName; // 保存下来，用于注销

	public ConcurrentCache(String name) {
		this.name = name;
		this.conf = xdb.Xdb.getInstance().getConf().getConcurrentCacheConf(name);
		if (null == this.conf)
			throw new RuntimeException("ConcurrentCacheConf not found. ccache='" + name + "'");
		this.mbeanObjectName = Xdb.mbeans().register(this, "xdb:type=Caches,name=" + name);
	}

	final synchronized void close() {
		Xdb.mbeans().unregister(this.mbeanObjectName);
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public long getCountGet() {
		return this.countGet.get();
	}

	@Override
	public long getCountMiss() {
		return this.countMiss.get();
	}

	// compute format
	private String format(long miss, long ops) {
		return String.format("%.2f", (double)(ops-miss) / ops);
	}

	@Override
	public String getPercentGetHit() {
		return format(countMiss.get(), countGet.get());
	}

	@Override
	public int getSize() {
		return cache.size();
	}

	@Override
	public int getCapacity() {
		return conf.getCapacity();
	}

	@Override
	public void setCapacity(int capacity) {
		conf.setCapacity(capacity);
	}

	public ConcurrentCacheConf getConf() {
		return conf;
	}

	public synchronized void setConf(ConcurrentCacheConf conf) {
		this.conf = conf;
	}

	/**
	 * 给 Listener 用来判断记录缓存是否存在。仅当存在缓存时才进行更新。
	 * @param key
	 * @return
	 */
	public final boolean existInCache(K key) {
		return cache.get(key) != null;
	}

	/**
	 * 子类更新时用来获取存在的缓存记录项的引用。仅当存在缓存时才进行更新。
	 * @param key
	 * @return
	 */
	protected final V peek(K key) {
		final Value<V> vv = cache.get(key);
		if (null == vv)
			return null;
		return vv.value;
	}

	/**
	 * 查询记录，如果cache中没有则会去xdb装载。
	 * <p>返回的记录永远不会为null。但是，返回的记录内部包含表格数据可能为null。
	 * @param key
	 * @return
	 */
	public final V get(K key) {
		this.countGet.incrementAndGet();
		Value<V> vv = cache.get(key);
		if (null == vv) {
			this.countMiss.incrementAndGet();
			vv = new Value<V>();
			final Value<V> vv2 = cache.putIfAbsent(key, vv);
			if (null != vv2)
				vv = vv2;
		}
		if (null == vv.value) {
			synchronized(vv) {
				if (null == vv.value)
					vv.value = realGet(key);
			}
		}
		return vv.access();
	}

	/**
	 * 由使用者实现这个方法。使用者负责启动事务读取所有表格并组装成出记录数据项。
	 * @param key
	 * @return
	 */
	protected abstract V realGet(K key);
	//protected abstract xdb.Lockey[] realGetLocks(K key);

	///////////////////////////////////////////////////////////////////////////
	// 更新以及删除
	/**
	 * 实现子类调用这个方法更新缓存数据项。Listener得到新的数据，使用这个方法，
	 * 通过传值方式把新数据更新到cache中。
	 * @param key
	 * @param value
	 */
	protected void update(K key, V value) {
		final Value<V> vv = cache.get(key);
		if (null != vv) // 仅当记录存在时才进行更新操作。
			vv.value = value;
	}

	private java.util.TimerTask cleanTimer;

	public synchronized void setupCleanTimer() {
		setupCleanTimer(conf.getHourOfDay(), conf.getMinute());
	}

	// 启动清理cache的定时器
	public synchronized void setupCleanTimer(int hourOfDay, int minute) {
		if (null != cleanTimer)
			return;

		Calendar firstTime = Calendar.getInstance();
		firstTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
		firstTime.set(Calendar.MINUTE, minute);
		firstTime.set(Calendar.SECOND, 0);
		firstTime.set(Calendar.MILLISECOND, 0);
		// 把初始时间散列到一个小时中。防止通知执行clean。
		firstTime.add(Calendar.SECOND, xdb.Xdb.random().nextInt(3600));
		if (firstTime.before(Calendar.getInstance()))
			firstTime.add(java.util.Calendar.DAY_OF_MONTH, 1); // tomorrow. nextday

		cleanTimer = new java.util.TimerTask() {
			public void run() {
				ConcurrentCaches.getInstance().add(new Runnable() {
					@Override
					public void run() {
						ConcurrentCache.this.clean();
					}
				});
			}
		};
		xdb.Xdb.timer().schedule(cleanTimer, firstTime.getTime(), 24 * 3600 * 1000); // every day
	}

	public synchronized void clearCleanTimer() {
		if (null != cleanTimer) {
			cleanTimer.cancel();
			cleanTimer = null;
		}
	}

	/**
	 * 按照lru原则，定时清除缓存数据。
	 * 建立Cache时，设置定时器，定时执行。
	 */
	void clean() {
		final int c = conf.getCapacity();
		if (c <= 0)
			return; // 容量不限

		final int s = cache.size();
		if (s <= c)
			return; // 容量足够

		// 这里使用TreeMap来完成时戳的排序。这个方案一次性读取和判断accessTime。
		// 其他排序算法可能多次读取accessTime，有并发问题。
		PriorityQueue<AccessTimeEntry<K, V>> sorted = new PriorityQueue<AccessTimeEntry<K, V>>();
		for (Entry<K, Value<V>> e : cache.entrySet()) // 直接浏览map。只允许一个线程完成这个操作。
			sorted.add(new AccessTimeEntry<K, V>(e.getValue().accessTime, e));

		// 这是个建议值，实际删除可能比nclean少。详细情况请参考后面的循环。
		for (int nclean = s - c + 255; nclean > 0; /* -- after remove */) {
			AccessTimeEntry<K, V> akv = sorted.poll();
			if (null == akv)
				break;
			if (akv.accessTime != akv.kv.getValue().accessTime)
				continue; // 排序后，记录时戳发生了更新，直接跳过。
			cache.remove(akv.kv.getKey());
			--nclean;
		}
	}

	static class AccessTimeEntry<K, V> implements Comparable<AccessTimeEntry<K, V>> {
		long accessTime;
		Entry<K, Value<V>> kv;

		@Override
		public int compareTo(AccessTimeEntry<K, V> o) {
			return accessTime < o.accessTime ? -1 : (accessTime == o.accessTime ? 0 : 1);
		}

		public boolean equals(Object obj) {
			if (obj instanceof AccessTimeEntry)
				return this.accessTime == ((AccessTimeEntry<?, ?>)obj).accessTime;
			return false;
		};

		AccessTimeEntry(long accessTime, Entry<K, Value<V>> kv) {
			this.accessTime = accessTime;
			this.kv = kv;
		}
	}
}

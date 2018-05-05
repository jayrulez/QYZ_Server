package xdb;

import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;


/**
 * 
 * 基于 ConcurrentMap 的 TTableCache 实现。
 *
 * @author lichenghua
 *
 * @param <K>
 * @param <V>
 */
public class TTableCacheConcurrentMap<K, V> extends TTableCache<K, V> {
	private ConcurrentMap<K, TRecord<K, V>> map = xdb.util.Misc.newConcurrentMap();
	private Runnable cleanWorker;
	private boolean cleanning = false;

	@Override
	void initialize(TTable<K, V> table, TableConf conf) {
		super.initialize(table, conf);

		cleanWorker = new Runnable() {
			@Override
			public void run() {
				if (setCleanning()) {
					TTableCacheConcurrentMap.this.cleanNow();
					resetCleanning();
				}
			}
		};
		// 允许配置清除间隔。
		int delay = 3600 * 1000; // 毫秒，一小时。
		// 为了使清除任务不会集中在某个时间点执行，简单处理一下：随机初始化延迟时间。（不算什么好方法）
		int initialDelay = Xdb.random().nextInt(delay);
		Xdb.executor().scheduleWithFixedDelay(cleanWorker, initialDelay, delay, TimeUnit.MILLISECONDS);
	}

	synchronized boolean setCleanning() {
		if (this.cleanning)
			return false;
		this.cleanning = true;
		return true;
	}

	synchronized void resetCleanning() {
		this.cleanning = false;
	}

	@Override
	public void clean() {
		this.cleanWorker.run();
	}

	private final void cleanNow() {
		final int capacity = super.getCapacity();
		if (capacity <= 0)
			return; // 容量不限

		final int size = this.getSize();
		if (size <= capacity)
			return; // 容量足够

		// 直接排序values有并发问题。修改为使用TreeMap，一个记录的时戳仅使用一次。
		// 旧的排序方式如下。
		// Object[] values = map.values().toArray();
		// java.util.Arrays.sort(values, TRecordComparator.singleton);

		PriorityQueue<AccessTimeRecord<K, V>> sorted = new PriorityQueue<AccessTimeRecord<K, V>>();
		for (TRecord<K, V> r : map.values()) // 直接浏览map。只允许一个线程完成这个操作。
			sorted.add(new AccessTimeRecord<K, V>(r.getLastAccessTime(), r));

		// 每次多回收 255个，see TTableCacheLRU，这个数就不配置了。
		// 这是个建议值，实际删除可能比nclean少。详细情况请参考后面的循环。
		for (int nclean = size - capacity + 255; nclean > 0; /* -- after remove */) {
			AccessTimeRecord<K, V> ar = sorted.poll();
			if (null == ar)
				break;
			if (ar.accessTime != ar.r.getLastAccessTime())
				continue; // 排序后，记录时戳发生了更新，直接跳过。
			if (super.tryRemoveRecord(ar.r)) // 运行的不频繁：不管删除是否成功，都继续循环。
				--nclean; // 删除成功才减少计数
		}
	}

	static class AccessTimeRecord<K, V> implements Comparable<AccessTimeRecord<K, V>> {
		long accessTime;
		TRecord<K, V> r;

		@Override
		public int compareTo(AccessTimeRecord<K, V> o) {
			return accessTime < o.accessTime ? -1 : (accessTime == o.accessTime ? 0 : 1);
		}

		public boolean equals(Object obj) {
			if (obj instanceof AccessTimeRecord)
				return this.accessTime == ((AccessTimeRecord<?, ?>)obj).accessTime;
			return false;
		};

		AccessTimeRecord(long accessTime, TRecord<K, V> r) {
			this.accessTime = accessTime;
			this.r = r;
		}
	}

	@Override
	public void clear() {
		if (super.getTable().getPersistence() != Table.Persistence.MEMORY)
			throw new UnsupportedOperationException();
		map.clear();
	}

	@Override
	public void walk(CacheQuery<K, V> query) {
		super._walk_notify_query(map.values().toArray(), query);
	}

	@Override
	int getSize() {
		return map.size();
	}

	@Override
	TRecord<K, V> get(K k) {
		// 纪录访问时戳。
		final TRecord<K, V> r = map.get(k);
		if (null != r)
			r.access();
		return r;
	}

	@Override
	void addNoLog(K key, TRecord<K, V> r) {
		if (null != map.putIfAbsent(key, r))
			throw new XError("cache.addNoLog duplicate record");
	}

	@Override
	void add(K key, TRecord<K, V> r) {
		if (null != map.putIfAbsent(key, r))
			throw new XError("cache.addNoLog duplicate record");
		super.logAddRemove(key, r);

		// 注意：这里先加入容器(map)中，才纪录日志。
		// TTableCacheLRU 的实现逻辑是先记录日志，才真正加入到容器中。
		// 先纪录日志更可靠一些。
	}

	@Override
	TRecord<K, V> remove(K key) {
		return map.remove(key);
	}

}

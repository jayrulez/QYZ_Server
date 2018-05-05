package xdb;

import java.util.WeakHashMap;

public class TTableCacheNull<K, V> extends TTableCache<K, V> {
	private WeakHashMap<K, TRecord<K, V>> weakmap = new WeakHashMap<K, TRecord<K, V>>(16, 0.75f);

	@Override
	void initialize(TTable<K, V> table, TableConf conf) {
		super.initialize(table, conf);
	}
	
	@Override
	public void clear() {
		weakmap.clear();
	}

	@Override
	public void clean() {
	}

	@Override
	public void walk(CacheQuery<K, V> query) {
	}

	@Override
	int getSize() {
		return weakmap.size();
	}

	@Override
	TRecord<K, V> get(K k) {
		return weakmap.get(k);
	}

	@Override
	void addNoLog(K key, TRecord<K, V> r) {
		weakmap.put(key, r);
	}

	@Override
	void add(K key, TRecord<K, V> r) {
		weakmap.put(key, r);
		super.logAddRemove(key, r);
	}

	@Override
	TRecord<K, V> remove(K k) {
		return weakmap.remove(k);
	}

}

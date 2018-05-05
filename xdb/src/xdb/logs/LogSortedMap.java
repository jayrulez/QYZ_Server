package xdb.logs;

import java.util.Comparator;
import java.util.SortedMap;

/**
 * 包装SortedMap，日志LogMap实现，
 * 目前没有这样的应用接口，只用于LogNavigableMap。
 * 
 * @author lichenghua
 *
 * @param <K> key type
 * @param <V> value type
 * @param <W> wrapped type
 */
public class LogSortedMap<K, V, W extends SortedMap<K, V>>
	extends LogMap<K, V, W> implements SortedMap<K, V> {

	public LogSortedMap(xdb.LogKey logkey, W wrapped) {
		super(logkey, wrapped);
	}

	@Override
	public Comparator<? super K> comparator() {
		return wrapped.comparator();
	}

	@Override
	public K firstKey() {
		return wrapped.firstKey();
	}

	@Override
	public K lastKey() {
		return wrapped.lastKey();
	}

	@Override
	public SortedMap<K, V> headMap(K toKey) {
		return new LogSortedMap<K, V, SortedMap<K, V>>(logkey, wrapped.headMap(toKey));
	}

	@Override
	public SortedMap<K, V> subMap(K fromKey, K toKey) {
		return new LogSortedMap<K, V, SortedMap<K, V>>(logkey, wrapped.subMap(fromKey, toKey));
	}

	@Override
	public SortedMap<K, V> tailMap(K fromKey) {
		return new LogSortedMap<K, V, SortedMap<K, V>>(logkey, wrapped.tailMap(fromKey));
	}
}

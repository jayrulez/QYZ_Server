package xdb.consts;

import java.util.*;

public class ConstSortedMap<K, V, W extends SortedMap<K, V>>
	extends ConstMap<K, V, W> implements SortedMap<K, V> {

	public ConstSortedMap(W w) {
		super(w);
	}

	@Override
	public Comparator<? super K> comparator() {
		return w.comparator();
	}

	@Override
	public SortedMap<K, V> headMap(K toKey) {
		return new ConstSortedMap<K, V, SortedMap<K, V>>(w.headMap(toKey));
	}

	@Override
	public SortedMap<K, V> subMap(K fromKey, K toKey) {
		return new ConstSortedMap<K, V, SortedMap<K, V>>(w.subMap(fromKey, toKey));
	}

	@Override
	public SortedMap<K, V> tailMap(K fromKey) {
		return new ConstSortedMap<K, V, SortedMap<K, V>>(w.tailMap(fromKey));
	}

	@Override
	public K firstKey() {
		return xdb.Consts.toConst(w.firstKey());
	}

	@Override
	public K lastKey() {
		return xdb.Consts.toConst(w.lastKey());
	}
}

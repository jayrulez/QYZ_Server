package xdb.consts;

import java.util.*;

public class ConstNavigableMap<K, V>
	extends ConstSortedMap<K, V, NavigableMap<K, V>> implements NavigableMap<K, V> {

	public ConstNavigableMap(NavigableMap<K, V> w) {
		super(w);
	}

	@Override
	public final Entry<K, V> higherEntry(K key) {
		return new ConstEntry<K, V>(w.higherEntry(key));
	}

	@Override
	public final K higherKey(K key) {
		return xdb.Consts.toConst(w.higherKey(key));
	}

	@Override
	public final Entry<K, V> lowerEntry(K key) {
		return new ConstEntry<K, V>(w.lowerEntry(key));
	}

	@Override
	public final K lowerKey(K key) {
		return xdb.Consts.toConst(w.lowerKey(key));
	}

	@Override
	public final Entry<K, V> ceilingEntry(K key) {
		return new ConstEntry<K, V>(w.ceilingEntry(key));
	}

	@Override
	public final K ceilingKey(K key) {
		return xdb.Consts.toConst(w.ceilingKey(key));
	}

	@Override
	public final Entry<K, V> firstEntry() {
		return new ConstEntry<K, V>(w.firstEntry());
	}

	@Override
	public final Entry<K, V> lastEntry() {
		return new ConstEntry<K, V>(w.lastEntry());
	}

	@Override
	public final Entry<K, V> floorEntry(K key) {
		return new ConstEntry<K, V>(w.floorEntry(key));
	}

	@Override
	public final K floorKey(K key) {
		return xdb.Consts.toConst(w.floorKey(key));
	}

	@Override
	public final Entry<K, V> pollFirstEntry() {
		throw new UnsupportedOperationException();
	}

	@Override
	public final Entry<K, V> pollLastEntry() {
		throw new UnsupportedOperationException();
	}

	private NavigableMap<K, V> descendingMap;

	@Override
	public final NavigableMap<K, V> descendingMap() {
		if (null == descendingMap)
			descendingMap = new ConstNavigableMap<K, V>(w.descendingMap());
		return descendingMap;
	}

	@Override
	public final NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
		return new ConstNavigableMap<K, V>(w.headMap(toKey, inclusive));
	}

	@Override
	public final NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
		return new ConstNavigableMap<K, V>(w.subMap(fromKey, fromInclusive, toKey, toInclusive));
	}

	@Override
	public final NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
		return new ConstNavigableMap<K, V>(w.tailMap(fromKey, inclusive));
	}

	private NavigableSet<K> navigableKeySet;

	@Override
	public final NavigableSet<K> navigableKeySet() {
		if (null == navigableKeySet)
			navigableKeySet = new ConstNavigableSet<K>(w.navigableKeySet());
		return navigableKeySet;
	}

	@Override
	public final NavigableSet<K> descendingKeySet() {
		return this.descendingMap().navigableKeySet();
	}
}

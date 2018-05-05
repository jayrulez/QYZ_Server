package xdb.logs;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NavigableMap;
import java.util.AbstractSet;
import java.util.SortedSet;

public class LogNavigableMap<K, V>
	extends LogSortedMap<K, V, NavigableMap<K, V>> implements NavigableMap<K, V> {

	public LogNavigableMap(xdb.LogKey logkey, NavigableMap<K, V> wrapped) {
		super(logkey, wrapped);
	}

	@Override
	public K ceilingKey(K key) {
		return wrapped.ceilingKey(key);
	}

	@Override
	public Entry<K, V> ceilingEntry(K key) {
		// 从Navigable返回的所有entry都是不可修改的。 不用拦截。
		// 下同。
		return wrapped.ceilingEntry(key);
//		return new WrapEntry(wrapped.ceilingEntry(key));
	}

	@Override
	public K higherKey(K key) {
		return wrapped.higherKey(key);
	}

	@Override
	public Entry<K, V> higherEntry(K key) {
		return wrapped.higherEntry(key);
//		return new WrapEntry(wrapped.higherEntry(key));
	}

	@Override
	public K floorKey(K key) {
		return wrapped.floorKey(key);
	}

	@Override
	public Entry<K, V> floorEntry(K key) {
		return wrapped.floorEntry(key);
//		return new WrapEntry(wrapped.floorEntry(key));
	}

	@Override
	public K lowerKey(K key) {
		return wrapped.lowerKey(key);
	}

	@Override
	public Entry<K, V> lowerEntry(K key) {
		return wrapped.lowerEntry(key);
//		return new WrapEntry(wrapped.lowerEntry(key));
	}

	@Override
	public Entry<K, V> firstEntry() {
		return wrapped.firstEntry();
//		return new WrapEntry(wrapped.firstEntry());
	}

	@Override
	public Entry<K, V> lastEntry() {
		return wrapped.lastEntry();
//		return new WrapEntry(wrapped.lastEntry());
	}

	@Override
	public Entry<K, V> pollFirstEntry() {
		Entry<K, V> e = wrapped.pollFirstEntry();
		if (null != e) {
			myLog().afterRemove(e.getKey(), e.getValue());
			return e;
		}
		return null;
	}

	@Override
	public Entry<K, V> pollLastEntry() {
		Entry<K, V> e = wrapped.pollLastEntry();
		if (null != e) {
			myLog().afterRemove(e.getKey(), e.getValue());
			return e;
		}
		return null;
	}

	/////////////////////////////////////////////
	// subMap
	private NavigableMap<K,V> descendingMap = null;
	@Override
	public NavigableMap<K, V> descendingMap() {
		if (null == descendingMap)
			descendingMap = new LogNavigableMap<K, V>(logkey, wrapped.descendingMap());
		return descendingMap;
	}

	@Override
	public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
		return new LogNavigableMap<K, V>(logkey, wrapped.headMap(toKey, inclusive));
	}

	@Override
	public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
		return new LogNavigableMap<K, V>(logkey, wrapped.subMap(fromKey, fromInclusive, toKey, toInclusive));
	}

	@Override
	public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
		return new LogNavigableMap<K, V>(logkey, wrapped.tailMap(fromKey, inclusive));
	}

	//////////////////////////////////////////
	private final static class KeySet<K, V> extends AbstractSet<K> implements NavigableSet<K> {
		private final NavigableMap<K, V> m; // 完成所有日志，实际上，m总是LogNavigableMap的实例。

		KeySet(NavigableMap<K, V> m) {
			// for debug
//			if (!(m instanceof LogNavigableMap))
//				throw new IllegalArgumentException();
			this.m = m;
		}

		@Override
		public Iterator<K> iterator() {
			return m.keySet().iterator();
		}

		@Override
		public Iterator<K> descendingIterator() {
			return this.descendingSet().iterator();
		}

		@Override
		public int size() {
			return m.size();
		}

		@Override
		public boolean contains(Object o) {
			return m.containsKey(o);
		}

		@Override
		public boolean remove(Object o) {
			return m.remove(o) != null;
		}

		@Override
		public void clear() {
			m.clear();
		}

		@Override
		public K ceiling(K e) {
			return m.ceilingKey(e);
		}

		@Override
		public Comparator<? super K> comparator() {
			return m.comparator();
		}

		@Override
		public K first() {
			return m.firstKey();
		}

		@Override
		public K floor(K e) {
			return m.floorKey(e);
		}
		
		@Override
		public K lower(K e) {
			return m.lowerKey(e);
		}

		@Override
		public K last() {
			return m.lastKey();
		}

		@Override
		public K higher(K e) {
			return m.higherKey(e);
		}

		@Override
		public K pollFirst() {
			Entry<K, V> e = m.pollFirstEntry();
			return e == null ? null : e.getKey();
		}

		@Override
		public K pollLast() {
			Entry<K, V> e = m.pollLastEntry();
			return e == null ? null : e.getKey();
		}

		@Override
		public NavigableSet<K> headSet(K toElement, boolean inclusive) {
			return new KeySet<K, V>(m.headMap(toElement, inclusive));
		}

		@Override
		public SortedSet<K> headSet(K toElement) {
			return this.headSet(toElement, true);
		}

		@Override
		public NavigableSet<K> subSet(K fromElement, boolean fromInclusive, K toElement, boolean toInclusive) {
			return new KeySet<K, V>(m.subMap(fromElement, fromInclusive, toElement, toInclusive));
		}

		@Override
		public SortedSet<K> subSet(K fromElement, K toElement) {
			return this.subSet(fromElement, true, toElement, false);
		}

		@Override
		public NavigableSet<K> tailSet(K fromElement, boolean inclusive) {
			return new KeySet<K, V>(m.tailMap(fromElement, inclusive));
		}

		@Override
		public SortedSet<K> tailSet(K fromElement) {
			return this.tailSet(fromElement, true);
		}

		@Override
		public NavigableSet<K> descendingSet() {
			return m.descendingKeySet();
		}
	}

	private KeySet<K, V> keyset = null;

	@Override
	public NavigableSet<K> navigableKeySet() {
		return (null != keyset) ? keyset : (keyset = new KeySet<K, V>(this));
	}

	@Override
	public NavigableSet<K> descendingKeySet() {
		return this.descendingMap().navigableKeySet();
	}

}

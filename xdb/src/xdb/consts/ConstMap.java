package xdb.consts;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import xdb.Consts;

public class ConstMap<K, V, W extends Map<K, V>> implements Map<K, V> {

	final W w;

	public ConstMap(W w) {
		this.w = w;
	}

	@Override
	public final int size() {
		return w.size();
	}

	@Override
	public final boolean isEmpty() {
		return w.isEmpty();
	}

	@Override
	public final boolean containsKey(Object key) {
		return w.containsKey(key);
	}

	@Override
	public final boolean containsValue(Object val) {
		return w.containsValue(val);
	}

	@Override
	public final V get(Object key) {
		return Consts.toConst(w.get(key));
	}

	@Override
	public final V put(K key, V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final V remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void clear() {
		throw new UnsupportedOperationException();
	}

	private  Set<K> keySet = null;
	private  Set<Map.Entry<K, V>> entrySet = null;
	private  Collection<V> values = null;

	@Override
	public final Set<K> keySet() {
		if (keySet == null)
			keySet = new ConstSet<K, Set<K>>(w.keySet());
		return keySet;
	}

	@Override
	public final Set<Map.Entry<K, V>> entrySet() {
		if (entrySet == null)
			entrySet = new ConstEntrySet<K, V>(w.entrySet());
		return entrySet;
	}

	@Override
	public final Collection<V> values() {
		if (values == null)
			values = new ConstCollection<V, Collection<V>>(w.values());
		return values;
	}

	@Override
	public final boolean equals(Object o) {
		return o == this || w.equals(o);
	}

	@Override
	public final int hashCode() {
		return w.hashCode();
	}

	@Override
	public final String toString() {
		return w.toString();
	}

	private static final class ConstEntrySet<K, V> extends ConstSet<Entry<K, V>, Set<Entry<K, V>>> {

		ConstEntrySet(Set<Map.Entry<K, V>> s) {
			super(s);
		}

		@Override
		public final Iterator<Map.Entry<K, V>> iterator() {
			return new Iterator<Map.Entry<K, V>>() {
				Iterator<Map.Entry<K, V>> i = w.iterator();

				public boolean hasNext() {
					return i.hasNext();
				}

				public Map.Entry<K, V> next() {
					return new ConstEntry<K, V>(i.next());
				}

				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
		}

		@SuppressWarnings("unchecked")
		@Override
		public final Object[] toArray() {
			Object[] a = w.toArray();
			for (int i = 0; i < a.length; i++)
				a[i] = new ConstEntry<K, V>((Map.Entry<K, V>) a[i]);
			return a;
		}

		@SuppressWarnings("unchecked")
		@Override
		public final <T> T[] toArray(T[] a) {
			// We don't pass a to c.toArray, to avoid window of
			// vulnerability wherein an unscrupulous multithreaded client
			// could get his hands on raw (unwrapped) Entries from c.
			Object[] arr = w.toArray(a.length == 0 ? a : Arrays.copyOf(a, 0));

			for (int i = 0; i < arr.length; i++)
				arr[i] = new ConstEntry<K, V>((Map.Entry<K, V>) arr[i]);

			if (arr.length > a.length)
				return (T[]) arr;

			System.arraycopy(arr, 0, a, 0, arr.length);
			if (a.length > arr.length)
				a[arr.length] = null;
			return a;
		}
	}
}

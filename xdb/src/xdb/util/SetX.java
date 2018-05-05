package xdb.util;

import java.util.Set;
import java.util.AbstractSet;
import java.util.Iterator;

/**
 * 和 java.util.HashSet 基本一致，使用 java.util.HashMap 实现。
 * 差别在于
 *    1 把 key 保存在 map 的 value 中。
 *    2 多了一个 removex 方法，删除时可以获得原来的引用。
 */
public final class SetX<E> extends AbstractSet<E> implements Set<E> {
	private transient java.util.HashMap<E, E> map;

	public SetX() {
		map = new java.util.HashMap<E, E>();
	}

	public SetX(int initialCapacity) {
		map = new java.util.HashMap<E, E>(initialCapacity);
	}

	public SetX(int initialCapacity, float loadFactor) {
		map = new java.util.HashMap<E, E>(initialCapacity, loadFactor);
	}

	@Override
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return map.containsKey(o);
	}

	@Override
	public boolean add(E e) {
		if (null == e)
			throw new NullPointerException();
		if (map.containsKey(e))
			return false;
		map.put(e, e);
		return true;
	}

	public E removex(Object o) {
		return map.remove(o);
	}

	@Override
	public boolean remove(Object o) {
		return null != removex(o);
	}

	@Override
	public void clear() {
		map.clear();
	}

//    public static int hash(int h) {
//        // This function ensures that hashCodes that differ only by
//        // constant multiples at each bit position have a bounded
//        // number of collisions (approximately 8 at default load factor).
//        h ^= (h >>> 20) ^ (h >>> 12);
//        return h ^ (h >>> 7) ^ (h >>> 4);
//    }
//
//    @Override
//	public boolean containsAll(java.util.Collection<?> c) {
//		Iterator<?> e = c.iterator();
//		while (e.hasNext()) {
//			Object n = (Object)e.next();
//			E x = map.get(n);
//			if (null == x) {
//				Iterator<E> keys = map.keySet().iterator();
//				E e2 = keys.next();
//				Set<java.util.Map.Entry<E, E>> es = map.entrySet();
//				xdb.Trace.debug(es);
//				java.util.Map.Entry<E, E> xx = es.iterator().next();
//				xdb.Trace.debug(xx.getKey() == xx.getValue());
//				xdb.Trace.debug(map.size());
//				xdb.Trace.debug(hash(n.hashCode()) + " " + hash(e2.hashCode()));
//				xdb.Trace.debug(n.equals(e2));
//				xdb.Trace.debug(n == e2);
//				xdb.Trace.debug(n + " " + e2);
//				xdb.Trace.debug(c.getClass().getName());
//				return false;
//			}
//		}
//		return true;
//	}
//
//	@Override
//	public boolean equals(Object o) {
//		if (o == this)
//			return true;
//
//		if (!(o instanceof Set))
//			throw new RuntimeException("!set");
//		java.util.Collection<?> c = (java.util.Collection<?>) o;
//		if (c.size() != size())
//			throw new RuntimeException("!size");
//		try {
//			return containsAll(c);
//		} catch (ClassCastException unused) {
//			throw new RuntimeException("!cast");
//		} catch (NullPointerException unused) {
//			throw new RuntimeException("!!null");
//		}
//	}
}

package xdb.consts;

import java.util.Collection;
import java.util.Iterator;

import xdb.Consts;

public class ConstCollection<E, W extends Collection<E>> implements Collection<E> {

	final W w;

	public ConstCollection(W c) {
		this.w = c;
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
	public final boolean contains(Object o) {
		return w.contains(o);
	}

	@Override
	public final String toString() {
		return w.toString();
	}

	@Override
	public final boolean add(E e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean containsAll(Collection<?> coll) {
		return w.containsAll(coll);
	}

	@Override
	public final boolean addAll(Collection<? extends E> coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean removeAll(Collection<?> coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean retainAll(Collection<?> coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void clear() {
		throw new UnsupportedOperationException();
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
	public Object[] toArray() {
		return Consts.toConst(w.toArray());
	}

	@Override
	public <T> T[] toArray(T[] a) {
		//存在线程问题，参考ConstEntrySet.toArray(T[]a)
		return Consts.toConst(w.toArray(a));
	}

	@Override
	public Iterator<E> iterator() {
		return new ConstIterator<E>(w.iterator());
	}
}

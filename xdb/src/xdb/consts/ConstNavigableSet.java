package xdb.consts;

import java.util.*;

public class ConstNavigableSet<E>
	extends ConstSortedSet<E, NavigableSet<E>> implements NavigableSet<E> {

	public ConstNavigableSet(NavigableSet<E> w) {
		super(w);
	}

	@Override
	public final E ceiling(E e) {
		return xdb.Consts.toConst(w.ceiling(e));
	}

	@Override
	public final E lower(E e) {
		return xdb.Consts.toConst(w.lower(e));
	}

	@Override
	public E higher(E e) {
		return xdb.Consts.toConst(w.higher(e));
	}

	@Override
	public final E floor(E e) {
		return xdb.Consts.toConst(w.floor(e));
	}

	@Override
	public Iterator<E> descendingIterator() {
		return new ConstIterator<E>(w.descendingIterator());
	}

	@Override
	public final E pollFirst() {
		throw new UnsupportedOperationException();
	}

	@Override
	public final E pollLast() {
		throw new UnsupportedOperationException();
	}

	private NavigableSet<E> descendingSet; // 缓存这个变量是为了保持和jdk一样的表现行为。

	@Override
	public final NavigableSet<E> descendingSet() {
		if (null == descendingSet)
			descendingSet = new ConstNavigableSet<E>(w.descendingSet());
		return descendingSet;
	}

	@Override
	public final NavigableSet<E> headSet(E toElement, boolean inclusive) {
		return new ConstNavigableSet<E>(w.headSet(toElement, inclusive));
	}

	@Override
	public final NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
		return new ConstNavigableSet<E>(w.subSet(fromElement, fromInclusive, toElement, toInclusive));
	}

	@Override
	public final NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
		return new ConstNavigableSet<E>(w.tailSet(fromElement, inclusive));
	}
}

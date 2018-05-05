package xdb.consts;

import java.util.*;

public class ConstSortedSet<E, W extends SortedSet<E>>
	extends ConstSet<E, W> implements SortedSet<E> {

	public ConstSortedSet(W w) {
		super(w);
	}

	@Override
	public final Comparator<? super E> comparator() {
		return w.comparator();
	}

	@Override
	public final E first() {
		return xdb.Consts.toConst(w.first());
	}

	@Override
	public final E last() {
		return xdb.Consts.toConst(w.last());
	}

	@Override
	public final SortedSet<E> headSet(E toElement) {
		return new ConstSortedSet<E, SortedSet<E>>(w.headSet(toElement));
	}

	@Override
	public final SortedSet<E> subSet(E fromElement, E toElement) {
		return new ConstSortedSet<E, SortedSet<E>>(w.subSet(fromElement, toElement));
	}

	@Override
	public final SortedSet<E> tailSet(E fromElement) {
		return new ConstSortedSet<E, SortedSet<E>>(w.tailSet(fromElement));
	}
}

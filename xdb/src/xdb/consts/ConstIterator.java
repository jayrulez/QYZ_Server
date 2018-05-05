package xdb.consts;

import java.util.*;

public class ConstIterator<E> implements Iterator<E> {
	private final Iterator<E> it;

	public ConstIterator(Iterator<E> it) {
		this.it = it;
	}

	@Override
	public final boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public final E next() {
		return xdb.Consts.toConst(it.next());
	}

	@Override
	public final void remove() {
		throw new UnsupportedOperationException();
	}
}

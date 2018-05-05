package xdb.consts;

import java.util.Set;

public class ConstSet<E, W extends Set<E>> extends ConstCollection<E, W> implements Set<E> {

	public ConstSet(W w) {
		super(w);
	}
}



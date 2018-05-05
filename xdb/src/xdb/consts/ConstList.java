package xdb.consts;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import xdb.Consts;

public class ConstList<E> extends ConstCollection<E, List<E>> implements List<E> {
	public ConstList(List<E> wrapper) {
		super(wrapper);
	}

	@Override
	public final E get(int index) {
		return Consts.toConst(w.get(index));
	}

	@Override
	public final void add(int arg0, E arg1) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final int indexOf(Object o) {
		return w.indexOf(o);
	}

	@Override
	public final int lastIndexOf(Object o) {
		return w.lastIndexOf(o);
	}

	@Override
	public final boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final ListIterator<E> listIterator() {
		return listIterator(0);
	}

	@Override
	public final ListIterator<E> listIterator(final int index) {
		return new ListIterator<E>() {
			private final ListIterator<E> i = w.listIterator(index);

			@Override
			public boolean hasNext() {
				return i.hasNext();
			}

			@Override
			public E next() {
				return Consts.toConst(i.next());
			}

			@Override
			public boolean hasPrevious() {
				return i.hasPrevious();
			}

			@Override
			public E previous() {
				return Consts.toConst(i.previous());
			}

			@Override
			public int nextIndex() {
				return i.nextIndex();
			}

			@Override
			public int previousIndex() {
				return i.previousIndex();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void set(E e) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void add(E e) {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public final List<E> subList(int fromIndex, int toIndex) {
		return new ConstList<E>(w.subList(fromIndex, toIndex));
	}

	@Override
	public final E remove(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final E set(int arg0, E arg1) {
		throw new UnsupportedOperationException();
	}
}

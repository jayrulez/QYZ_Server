package xdb.logs;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import xdb.Log;
import xdb.LogKey;
import xdb.Logs;
import xdb.Savepoint;
import xdb.Transaction;

public final class LogList<E> extends WrapList<E> {
	private LogKey logkey;

	private final class MyLog extends Note implements Log {
		private Object [] savedonwrite;

		@Override
		public void commit() {
			if (savedonwrite != null)
				xdb.Logs.logNotify(logkey.getXBean(), new LogNotify(logkey, this));
		}

		@Override
		public void rollback() {
			getWrapped().clear();
			for (int i = 0; i < savedonwrite.length; ++i) {
				@SuppressWarnings("unchecked")
				E e = (E)savedonwrite[i];
				getWrapped().add(e);
			}
		}

		void beforeChange() {
			if (savedonwrite == null) {
				savedonwrite = getWrapped().toArray();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private final MyLog myLog() {
		Savepoint sp = Transaction.currentSavepoint();
		Log log = sp.get(logkey);
		if (null == log)
			sp.add(logkey, log = new MyLog());
		return (MyLog)log;
	}

	//////////////////////////////////////////////////

	// 在修改操作前调用，不考虑修改发生错误，没有实际完成修改。
	@Override
	protected final void beforeChange() {
		myLog().beforeChange();
	}

	@Override
	protected void afterAdd(E add) {
		Logs.xdbParent(add, logkey.getXBean(), logkey.getVarname());
	}

	@Override
	protected void beforeRemove(E remove) {
		Logs.xdbParent(remove, null, null);
	}

	//////////////////////////////////////////////////
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return new WrapList<E>(this, getWrapped().subList(fromIndex, toIndex));
	}

	public LogList(LogKey logkey, List<E> wrapped) {
		super(null, wrapped);
		this.logkey = logkey;
	}
}

class WrapList<E> implements List<E> {
	private LogList<E> root;
	private List<E> wrapped;

	//////////////////////////////////////////////////
	public WrapList(LogList<E> root, List<E> wrapped) {
		this.root = root;
		this.wrapped = wrapped;
	}

	/////////////////////////////////////////////////
	// LogList root will override
	protected void beforeChange() {
		root.beforeChange();
	}

	protected void afterAdd(E add) {
		root.afterAdd(add);
	}

	protected void beforeRemove(E remove) {
		root.beforeRemove(remove);
	}

	protected List<E> getWrapped() {
		return wrapped;
	}

	//////////////////////////////////////////////////
	@Override
	public boolean add(E e) {
		beforeChange();
		if (wrapped.add(e)) {
			afterAdd(e);
			return true;
		}
		return false;
	}

	@Override
	public void add(int index, E element) {
		beforeChange();
		wrapped.add(index, element);
		afterAdd(element);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		beforeChange();
		if (wrapped.addAll(c)) {
			for (E e : c)
				afterAdd(e);
			return true;
		}
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		beforeChange();
		if (wrapped.addAll(index, c)) {
			for (E e : c)
				afterAdd(e);
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		beforeChange();
		for (E e : wrapped)
			beforeRemove(e);
		wrapped.clear();
	}

	@Override
	public boolean contains(Object o) {
		return wrapped.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return wrapped.containsAll(c);
	}

	@Override
	public boolean equals(Object obj) {
		return wrapped.equals(obj);
	}

	@Override
	public E get(int index) {
		return wrapped.get(index);
	}

	@Override
	public int hashCode() {
		return wrapped.hashCode();
	}

	@Override
	public int indexOf(Object o) {
		return wrapped.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return wrapped.isEmpty();
	}

	private class WrapIt implements Iterator<E> {
		private Iterator<E> it = wrapped.iterator();
		private E current;

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public E next() {
			return current = it.next();
		}

		@Override
		public void remove() {
			beforeChange();
			beforeRemove(current);
			it.remove();
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new WrapIt();
	}

	@Override
	public int lastIndexOf(Object o) {
		return wrapped.lastIndexOf(o);
	}

	private class WrapListIt implements ListIterator<E> {
		private ListIterator<E> it;
		private E current;

		WrapListIt() {
			it = wrapped.listIterator();
		}

		WrapListIt(int index) {
			it = wrapped.listIterator(index);
		}

		@Override
		public void add(E e) {
			beforeChange();
			it.add(e);
			afterAdd(e);
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public boolean hasPrevious() {
			return it.hasPrevious();
		}

		@Override
		public E next() {
			return current = it.next();
		}

		@Override
		public int nextIndex() {
			return it.nextIndex();
		}

		@Override
		public E previous() {
			return current = it.previous();
		}

		@Override
		public int previousIndex() {
			return it.previousIndex();
		}

		@Override
		public void remove() {
			beforeChange();
			beforeRemove(current);
			it.remove();
		}

		@Override
		public void set(E e) {
			beforeChange();
			beforeRemove(current);
			it.set(e);
			afterAdd(e);
		}
	}

	@Override
	public ListIterator<E> listIterator() {
		return new WrapListIt();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new WrapListIt(index);
	}

	@Override
	public E remove(int index) {
		beforeChange();
		E removed = wrapped.remove(index);
		beforeRemove(removed); // 这里不是 before . ^_^
		return removed;
	}

	@Override
	public boolean remove(Object o) {
		int index = this.indexOf(o);
		if (index < 0)
			return false;
		this.remove(index);
		return true; // // ignore result, 没有异常就是成功。
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// 通过 WrapIt 实现修改操作拦截。
		boolean modified = false;
		Iterator<?> e = iterator();
		while (e.hasNext()) {
			if (c.contains(e.next())) {
				e.remove();
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// 通过 WrapIt 实现修改操作拦截。
		boolean modified = false;
		Iterator<E> e = iterator();
		while (e.hasNext()) {
			if (!c.contains(e.next())) {
				e.remove();
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public E set(int index, E element) {
		beforeChange();
		E removed = wrapped.set(index, element);
		beforeRemove(removed); // 这里不再是 before . ^_^
		afterAdd(element);
		return removed;
	}

	@Override
	public int size() {
		return wrapped.size();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return new WrapList<E>(root, wrapped.subList(fromIndex, toIndex));
	}

	@Override
	public Object[] toArray() {
		return wrapped.toArray();
	}

	@Override
	public <X> X[] toArray(X[] a) {
		return wrapped.toArray(a);
	}

	@Override
	public String toString() {
		return wrapped.toString();
	}
}


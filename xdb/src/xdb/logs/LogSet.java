package xdb.logs;

import java.util.AbstractSet;
import java.util.Iterator;

import xdb.Log;
import xdb.LogKey;
import xdb.Logs;
import xdb.Savepoint;
import xdb.Transaction;
import xdb.util.SetX;

public final class LogSet<E> extends AbstractSet<E> {

	private LogKey logkey;
	private final SetX<E> wrapped;

	private final class MyLog extends NoteSet<E> implements Log {
		@Override
		public void commit() {
			if (super.isSetChanged()) {
				xdb.Logs.logNotify(LogSet.this.logkey.getXBean(), new LogNotify(logkey, this));
			}
		}

		@Override
		public void rollback() {
			LogSet.this.wrapped.removeAll(super.getAdded());
			LogSet.this.wrapped.addAll(super.getRemoved());
			for (E e : super.getOld()) {
				LogSet.this.wrapped.remove(e);
				LogSet.this.wrapped.add(e);
			}
			super.clear();
		}

		private void beforeRemove(E e) {
			Logs.xdbParent(e, null, null);
			super.logRemove(e);
		}

		private void afterRemove(E e) {
			super.logRemove(e);
			Logs.xdbParent(e, null, null);
		}

		private void afterAdd(E e) {
			super.logAdd(e);
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

	////////////////////////////////////////////
	public LogSet(LogKey logkey, SetX<E> wrapped) {
		this.logkey = logkey;
		this.wrapped = wrapped;
	}

	////////////////////////////////////////////
	@Override
	public boolean add(E e) {
		Logs.xdbManagedCheck(e); // 不能提前设置parent，只能检查。因为可能已经存在e,add会失败。
		if (wrapped.add(e)) {
			myLog().afterAdd(e);
			Logs.xdbParent(e, logkey.getXBean(), logkey.getVarname());
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		for (Iterator<E> it = iterator(); it.hasNext();) {
			myLog().beforeRemove(it.next());
		}
		wrapped.clear();
	}

	@Override
	public boolean contains(Object o) {
		return wrapped.contains(o);
	}

	private class WrapIt implements Iterator<E> {
		Iterator<E> it = LogSet.this.wrapped.iterator();
		E current;

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
			myLog().beforeRemove(current);
			it.remove();
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new WrapIt();
	}

	@Override
	public boolean remove(Object o) {
		E e = wrapped.removex(o);
		if (e != null) {
			myLog().afterRemove(e);
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return wrapped.size();
	}

	@Override
	public int hashCode() {
		return wrapped.hashCode();
	}
}


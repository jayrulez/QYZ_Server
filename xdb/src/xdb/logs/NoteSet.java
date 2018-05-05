package xdb.logs;

import java.util.HashSet;
import java.util.Set;

public class NoteSet<E extends Object> extends Note {
	private final Set<E> added = new HashSet<E>();
	private final Set<E> removed = new HashSet<E>();
	private final Set<E> old = new HashSet<E>(); // 事务开始时已经存在的项第一次被删除时记录。

	/**
	 * 合并的时候，把后来的日志重做一次。
	 * @param note
	 */
	final void merge(Note note) {
		@SuppressWarnings("unchecked")
		NoteSet<E> another = (NoteSet<E>)note;
		// 合并时要求 note 按时间顺序通告。 see Transaction._real_commit_ 提交保存点。
		for (E e : another.added)   this.logAdd(e);
		for (E e : another.removed) this.logRemove(e);
	}

	final void logAdd(E e) {
		if (false == this.removed.remove(e)) {
			this.added.add(e);
		}
	}

	final void logRemove(E e) {
		if (false == this.added.remove(e)) {
			this.removed.add(e);
			// 一旦发生删除，要记录下最久远的原始数据，用于回滚
			if (false == this.old.contains(e))
				this.old.add(e);
		}
	}

	public final Set<E> getAdded() {
		return added;
	}

	public final Set<E> getRemoved() {
		return removed;
	}
	
	public final Set<E> getOld() {
		return old;
	}

	public final boolean isSetChanged() {
		return (false == added.isEmpty()) || (false == removed.isEmpty());
	}

	public final void clear() {
		added.clear();
		removed.clear();
	}

	@Override
	public String toString() {
		return "added=" + added + " removed=" + removed;
	}
}


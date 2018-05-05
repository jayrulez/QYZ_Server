package xdb.logs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class NoteMap<K, V> extends Note {
	private final Set<K>    added     = new HashSet<K>();
	private final Map<K, V> removed   = new HashMap<K, V>();
	private final Map<K, V> replaced  = new HashMap<K, V>();
	private List<V> changed; // setup by ListenableMap

	final void setChanged(List<V> changed) {
		this.changed = changed;
	}

	/**
	 * 合并的时候，把后来的日志重做一次。其中added,replaced都是logPut的结果。
	 * @param note
	 */
	final void merge(Note note) {
		@SuppressWarnings("unchecked")
		NoteMap<K, V> another = (NoteMap<K, V>)note;
		// 合并时要求 note 按时间顺序通告。 see Transaction._real_commit_ 提交保存点。
		for (K k : another.added)
			this.logPut(k, null, null);
		for (Map.Entry<K, V> e : another.removed.entrySet())
			this.logRemove(e.getKey(), e.getValue());
		for (Map.Entry<K, V> e : another.replaced.entrySet())
			this.logPut(e.getKey(), e.getValue(), e.getValue());
	}

	public final Set<K> getAdded() {
		return added;
	}

	/**
	 * 被put调用替换掉的键值，其中值(value)为替换前的。
	 * @return
	 */
	public final Map<K, V> getReplaced() {
		return replaced;
	}

	/**
	 * 被remove调用删除的键值，其中值(key, value)为删除前的。
	 * @return
	 */
	public final Map<K, V> getRemoved() {
		return removed;
	}

	/**
	 * 通过get返回的引用，直接修改值(value)的日志。
	 * 可能包含已被删除掉的键值。
	 * @return
	 */
	public final List<V> getChanged() {
		return this.changed;
	}

	// map 本身的改变，不包括 List<V> changed。
	final boolean isMapChanged() {
		return (!added.isEmpty()) || (!removed.isEmpty()) || (!replaced.isEmpty());
	}

	final void clear() {
		this.added.clear();
		this.removed.clear();
		this.replaced.clear();
		this.changed = null;
	}

	final void logRemove(K key, V value ) {
		if (!this.added.remove(key)) {
			// 如果是覆盖的，此时v才是最早的，参数value是replace时的。
			V v = this.replaced.remove(key);
			this.removed.put(key, v == null ? value : v);
		} // else 删除新增记录，删除日志即可。
	}

	final void logPut(K key, V origin, V value) {
		if (this.added.contains(key))
			return; // 覆盖新增条目。仍然保留新增的日志。

		// 判断是否加入删除过。
		V v = this.removed.remove(key);
		if (null != v) {
			// 删除以后再次加入，保留第一次是删除产生的日志内容，日志转移到覆盖日志中。
			// 此时条目存在。不处理再次加入的value和以前删除的value是否一样。
			this.replaced.put(key, v);
			return;
		}
		if (this.replaced.containsKey(key))
			return; // 再次覆盖条目。保留第一次的日志。

		// 第一次覆盖。此时根据origin产生added或者replaced日志。
		if (null == origin)
			this.added.add(key);
		else
			this.replaced.put(key, origin);
	}

	@Override
	public String toString() {
		return "added=" + added + " replaced=" + replaced + " removed=" + removed + " changed=" + changed;
	}
}

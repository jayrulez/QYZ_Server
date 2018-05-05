package xdb.logs;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import xdb.Log;
import xdb.LogKey;
import xdb.Logs;
import xdb.Savepoint;
import xdb.Transaction;

/**
 * 包装Map，记录所有的修该日志。
 * 
 * @author lichenghua
 *
 * @param <K> key type
 * @param <V> value type
 * @param <W> wrapped type
 */
public class LogMap<K, V, W extends Map<K, V>> implements Map<K, V> {

	// package private。 开放给子类使用。
	final LogKey logkey;
	final W wrapped;

	////////////////////////////////////////////
	public LogMap(LogKey logkey, W wrapped) {
		this.logkey = logkey;
		this.wrapped = wrapped;
	}

	final class MyLog extends NoteMap<K, V> implements Log {
		@Override
		public void commit() {
			if (super.isMapChanged())
				xdb.Logs.logNotify(logkey.getXBean(), new LogNotify(logkey, this));
		}

		@Override
		public void rollback() {
			for (K k : super.getAdded()) wrapped.remove(k);
			wrapped.putAll(super.getRemoved());
			wrapped.putAll(super.getReplaced());
			super.clear();
		}

		/**
		 * afterRemove 的参数key不是容器中原有的，而是外部传递进来的参数, 无法管理它的 parent。
		 * 不允许自定义bean当key，可以绕过这个问题。
		 * beforeRemove afterPut 也都不管理key's Parent。
		 * XBean.setXdbParent(key, null, null);
		 */
		private void beforeRemove(K key, V value) {
			// 先设置parent，后记日志，xdbParent可能有异常。
			Logs.xdbParent(value, null, null);
			super.logRemove(key, value);
		}

		void afterRemove(K key, V value) {
			// 先记日志，后设置parent，xdbParent可能有异常。这样回滚可以撤销刚才的修改。
			super.logRemove(key, value);
			Logs.xdbParent(value, null, null);
		}

		private void afterPut(K key, V origin, V value) {
			super.logPut(key, origin, value);
			// 此时数据已经修改。先记录log，xdbParent可能失败。
			if (null != origin)
				Logs.xdbParent(origin, null, null);
			// value 的 xdbParent 在修改前就设置。
		}
	}

//	final class MyLog extends NoteMap<K, V> implements Log {
//		private final Map<K, V> changed = new HashMap<K, V>();
//		@Override
//		public void commit() {
//			for (Map.Entry<K, V> e : changed.entrySet()) {
//				if (wrapped.containsKey(e.getKey()))
//					super.getReplaced().put(e.getKey(), e.getValue());
//				else
//					super.getRemoved().put(e.getKey(), e.getValue());
//			}
//
//			if (super.isMapChanged()) {
//				xdb.Logs.logNotify(logkey.getXBean(), new LogNotify(logkey, this));
//			}
//		}
//
//		@Override
//		public void rollback() {
//			for (K k : super.getAdded().keySet())
//				wrapped.remove(k);
//			wrapped.putAll(changed);
//			changed.clear();
//			super.clear();
//		}
//
//		/**
//		 * afterRemove 的参数key不是容器中原有的，而是外部传递进来的参数, 无法管理它的 parent。
//		 * 不允许自定义bean当key，可以绕过这个问题。
//		 * beforeRemove afterPut 也都不管理key's Parent。
//		 * XBean.setXdbParent(key, null, null);
//		 */
//		private void beforeRemove(K key, V value) {
//			// 先设置parent，后记日志，xdbParent可能有异常。
//			Logs.xdbParent(value, null, null);
//			if (false == changed.containsKey(key) && null == super.getAdded().remove(key))
//				changed.put(key, value);
//		}
//
//		void afterRemove(K key, V value) {
//			// 先记日志，后设置parent，xdbParent可能有异常。这样回滚可以撤销刚才的修改。
//			if (false == changed.containsKey(key) && null == super.getAdded().remove(key))
//				changed.put(key, value);
//			Logs.xdbParent(value, null, null);
//		}
//
//		private void afterPut(K key, V origin, V value) {
//			if (false == changed.containsKey(key) && false == super.getAdded().containsKey(key)) {
//				if (null == origin)
//					super.getAdded().put(key, value);
//				else
//					changed.put(key, origin);
//			}
//			// 此时数据已经修改。先记录log，xdbParent可能失败。
//			if (null != origin)
//				Logs.xdbParent(origin, null, null);
//			// value 的 xdbParent 在修改前就设置。
//		}
//	}

	@SuppressWarnings("unchecked")
	final MyLog myLog() {
		Savepoint sp = Transaction.currentSavepoint();
		Log log = sp.get(logkey);
		if (null == log)
			sp.add(logkey, log = new MyLog());
		return (MyLog)log;
	}

	// //////////////////////////////////////////
	@Override
	public void clear() {
		for (Map.Entry<K, V> e : wrapped.entrySet())
			myLog().beforeRemove(e.getKey(), e.getValue());
		wrapped.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return wrapped.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return wrapped.containsValue(value);
	}

	// package private
	abstract class WrapEntryIt<E> implements Iterator<E> {
		private final Iterator<Entry<K, V>> it;
		private WrapEntry current;

		WrapEntryIt() {
			this.it = wrapped.entrySet().iterator();
		}

		WrapEntryIt(Iterator<Entry<K, V>> it) {
			this.it = it;
		}

		@Override
		public void remove() {
			myLog().beforeRemove(current.getKey(), current.getValue());
			it.remove();
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		Entry<K, V> nextEntry() {
			return current = new WrapEntry(it.next());
		}
	}

	private Iterator<Entry<K, V>> newEntryIterator() {
		return new WrapEntryIt<Entry<K, V>>() {
			public Entry<K, V> next() {
				return nextEntry();
			}
		};
	}

	final class WrapEntry implements Map.Entry<K, V> {
		private Map.Entry<K, V> e;
		WrapEntry(Map.Entry<K, V> e) {
			this.e = e;
		}

		@Override
		public boolean equals(Object obj) {
			return e.equals(obj);
		}

		@Override
		public K getKey() {
			return e.getKey();
		}

		@Override
		public V getValue() {
			return e.getValue();
		}

		@Override
		public int hashCode() {
			return e.hashCode();
		}

		@Override
		public V setValue(V value) {
			if (null == value)
				throw new NullPointerException();
			Logs.xdbParent(value, LogMap.this.logkey.getXBean(), LogMap.this.logkey.getVarname());
			V origin = e.setValue(value);
			LogMap.this.myLog().afterPut(e.getKey(), origin, value);
			return origin;
		}
	}

	private final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
		@Override
		public Iterator<Map.Entry<K, V>> iterator() {
			return LogMap.this.newEntryIterator();
		}

		@Override
		public boolean contains(Object o) {
			return LogMap.this.wrapped.entrySet().contains(o);
		}

		@Override
		public int size() {
			return LogMap.this.wrapped.entrySet().size();
		}

		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
			// 此处得不到容器中原来对象，无法设置Managed：xdbParent。
//			if (entryset.remove(o)) {
//				@SuppressWarnings("unchecked")
//				Map.Entry<K, V> entry = (Map.Entry<K, V>) o;
//				LogMap.this._logremove(entry.getKey(), entry.getValue());
//				return true;
//			}
//			return false;
		}

		@Override
		public void clear() {
			LogMap.this.clear();
		}
	}

	private EntrySet esview;

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		return esview != null ? esview : (esview = new EntrySet());
	}

	@Override
	public V get(Object key) {
		return wrapped.get(key);
	}

	@Override
	public int hashCode() {
		return wrapped.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return wrapped.equals(obj);
	}

	@Override
	public boolean isEmpty() {
		return wrapped.isEmpty();
	}

	private Iterator<K> newKeyIterator() {
		return new WrapEntryIt<K>() {
			public K next() {
				return nextEntry().getKey();
			}
		};
	}

	private final class KeySet extends AbstractSet<K> {
		@Override
		public Iterator<K> iterator() {
			return LogMap.this.newKeyIterator();
		}

		@Override
		public int size() {
			return LogMap.this.size();
		}

		@Override
		public boolean contains(Object o) {
			return LogMap.this.containsKey(o);
		}

		@Override
		public boolean remove(Object o) {
			return LogMap.this.remove(o) != null;
		}

		@Override
		public void clear() {
			LogMap.this.clear();
		}
	}

	private KeySet ksview;

	@Override
	public Set<K> keySet() {
		return ksview != null ? ksview: (ksview = new KeySet());
	}

	@Override
	public V put(K key, V value) {
		if (null == value || null == key)
			throw new NullPointerException();

		Logs.xdbParent(value, logkey.getXBean(), logkey.getVarname());
		V origin = wrapped.put(key, value);
		myLog().afterPut(key, origin, value);
		return origin;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
			this.put(e.getKey(), e.getValue());
		// impl.putAll(m); // 实际的实现有优化,但无法拦截修改操作.
	}

	@Override
	public V remove(Object arg0) {
		V v = wrapped.remove(arg0);
		if (null != v) {
			@SuppressWarnings("unchecked")
			K key = (K) arg0;
			myLog().afterRemove(key, v);
		}
		return v;
	}

	@Override
	public int size() {
		return wrapped.size();
	}

	private Iterator<V> newValueIterator() {
		return new WrapEntryIt<V>() {
			public V next() {
				return nextEntry().getValue();
			}
		};
	}

	private final class Values extends AbstractCollection<V> {
		@Override
		public Iterator<V> iterator() {
			return LogMap.this.newValueIterator();
		}

		@Override
		public int size() {
			return LogMap.this.size();
		}

		@Override
		public boolean contains(Object o) {
			return LogMap.this.containsValue(o);
		}

		@Override
		public void clear() {
			LogMap.this.clear();
		}
	}

	private Values vsview;

	@Override
	public Collection<V> values() {
		return vsview != null ? vsview: (vsview = new Values());
	}

	@Override
	public String toString() {
		return wrapped.toString();
	}
}


package xdb.logs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 管理表格的数据变更订阅者。每张表拥有一个自己的listener管理对象。
 * 功能：增加；删除；查询；触发回调。
 */
public final class ListenerMap {
	private Map<String, Set<Listener>> listenerMap = new HashMap<String, Set<Listener>>();
	private final Lock sync = new ReentrantLock();

	/**
	 * listener拷贝，用于多线程只读访问。
	 * 当 listenerMap 发生改变的时候，生成一个拷贝。
	 */
	private volatile Map<String, Set<Listener>> listenerMapCopy = new HashMap<String, Set<Listener>>();

	// MUST under sync lock
	private void setListenerMapCopy() {
		Map<String, Set<Listener>> localCopy = new HashMap<String, Set<Listener>>();
		for (Map.Entry<String, Set<Listener>> e : listenerMap.entrySet()) {
			Set<Listener> values = new HashSet<Listener>();
			values.addAll(e.getValue());
			localCopy.put(e.getKey(), values);
		}
		this.listenerMapCopy = localCopy;
	}

	public final String add(String fullVarName, Listener l) {
		sync.lock();
		try {
			Set<Listener> ls = listenerMap.get(fullVarName);
			if (null == ls)
				listenerMap.put(fullVarName, ls = new HashSet<Listener>());
			if (!ls.add(l))
				throw new IllegalStateException("Listener has added");

			setListenerMapCopy();
		} finally {
			sync.unlock();
		}
		return fullVarName;
	}

	public final void remove(String fullVarName, Listener l) {
		sync.lock();
		try {
			Set<Listener> ls = listenerMap.get(fullVarName);
			if (null != ls) {
				boolean changed = ls.remove(l);
				if (ls.isEmpty())
					listenerMap.remove(fullVarName);

				if (changed)
					setListenerMapCopy();
			}
		} finally {
			sync.unlock();
		}
	}

	public final boolean hasListener() {
		final Map<String, Set<Listener>> localCopy = this.listenerMapCopy;
		return false == localCopy.isEmpty();
	}

	public final boolean hasListener(String fullVarName) {
		final Map<String, Set<Listener>> localCopy = this.listenerMapCopy;
		// empty Set will remove immediately. see this.remove
		return null != localCopy.get(fullVarName);
	}

	/////////////////////////////////////////////////////////////////////////
	final void notifyChanged(String fullVarName, Object key) {
		notify(ZZZ.CHANGED_ALL, fullVarName, key, null);
	}

	final void notifyRemoved(String fullVarName, Object key) {
		notify(ZZZ.REMOVED, fullVarName, key, null);
	}

	final void notifyChanged(String fullVarName, Object key, Note note) {
		notify(ZZZ.CHANGED_NOTE, fullVarName, key, note);
	}

	private static enum ZZZ {
		// 区分调用Listener的不同的方法。需要时再抽象。
		CHANGED_ALL, CHANGED_NOTE, REMOVED
	}

	private final void notify(ZZZ zzz, String fullVarName, Object key, Note note) {
		final Map<String, Set<Listener>> localCopy = this.listenerMapCopy;
		final Set<Listener> listeners = localCopy.get(fullVarName);

		if (null == listeners)
			return;

		for (Listener l : listeners) {
			xdb.Transaction trans = xdb.Transaction.current();
			int spBefore = trans.currentSavepointId();
			int spBeforeAccess = spBefore > 0 ? trans.getSavepoint(spBefore).getAccess() : 0;

			try {
				switch (zzz) {
				case CHANGED_ALL: l.onChanged(key); break;
				case CHANGED_NOTE: l.onChanged(key, fullVarName, note); break;
				case REMOVED: l.onRemoved(key); break;
				}

			} catch (Throwable e) {
				xdb.Trace.error("doChanged key=" + key + " name=" + fullVarName, e);

				/*
				 * 回调错误处理规则。
				 *     spBefore     spAfter     rollback      desc
				 *     -----------------------------------------------------------
				 * (a) 0            0           NONE          前后都没有保存点
				 * (b) 0            >0          spBefore + 1  开始前没有，回调中创建了保存点。
				 * (c) >0 Any       <spBefore   ERROR         错误
				 * (d) >0 Dirty     -           spBefore      开始前的保存点发生了改变
				 * (e) >0 Clean     >spBefore   spBefore + 1
				 * (f) >0 Clean     =spBefore   NONE          回调没有修改操作，
				 * 
				 */
				int spAfter = trans.currentSavepointId();
				if (0 == spBefore) {
					if (spAfter > 0) // (b)
						trans.rollback(spBefore + 1);
					// else (a)
				} else {
					if (spAfter < spBefore) // (c)
						throw new IllegalStateException("spAfter < spBefore");

					if (trans.getSavepoint(spBefore).isAccessSince(spBeforeAccess)) // (d)
						trans.rollback(spBefore);
					else if (spAfter > spBefore) // (e)
						trans.rollback(spBefore + 1);
					// else (f)
				}
			}
		}
	}
}

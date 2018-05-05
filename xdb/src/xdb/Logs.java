package xdb;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.NavigableMap;
import xdb.logs.*;
import xdb.util.SetX;

public final class Logs {

	////////////////////////////////////////////////////////////////////
	// 常变量日志直接记录到保存点。
	public static void logIf(LogKey key) {
		Savepoint sp = Transaction.currentSavepoint();
		if (null == sp.get(key)) {
			Log log = key.create();
			sp.add(key, log);
		}
	}

	////////////////////////////////////////////////////////////////////
	// 容器包装器实例记录在事务中，修改日志在容器修改时，根据当前保存点动态获取。 
	public static <E> List<E> logList(LogKey key, List<E> wrapped) {
		Map<LogKey, Object> wrappers = Transaction.current().wrappers;
		Object exist = wrappers.get(key);
		if (null == exist) {
			LogList<E> log = new LogList<E>(key, wrapped);
			wrappers.put(key, log);
			return log;
		}
		@SuppressWarnings("unchecked")
		LogList<E> log = (LogList<E>)exist;
		return log;
	}

	public static <K, V> Map<K, V> logMap(LogKey key, Map<K, V> wrapped) {
		Map<LogKey, Object> wrappers = Transaction.current().wrappers;
		Object exist = wrappers.get(key);
		if (null == exist) {
			LogMap<K, V, Map<K, V>> log = new LogMap<K, V, Map<K, V>>(key, wrapped);
			wrappers.put(key, log);
			return log;
		}
		@SuppressWarnings("unchecked")
		LogMap<K, V, Map<K, V>> log = (LogMap<K, V, Map<K, V>>)exist;
		return log;
	}

	public static <K, V> NavigableMap<K, V> logNavigableMap(LogKey key, NavigableMap<K, V> wrapped) {
		Map<LogKey, Object> wrappers = Transaction.current().wrappers;
		Object exist = wrappers.get(key);
		if (null == exist) {
			LogNavigableMap<K, V> log = new LogNavigableMap<K, V>(key, wrapped);
			wrappers.put(key, log);
			return log;
		}
		@SuppressWarnings("unchecked")
		LogNavigableMap<K, V> log = (LogNavigableMap<K, V>)exist;
		return log;
	}

	public static <E> Set<E> logSet(LogKey key, SetX<E> wrapped) {
		Map<LogKey, Object> wrappers = Transaction.current().wrappers;
		Object exist = wrappers.get(key);
		if (null == exist) {
			LogSet<E> log = new LogSet<E>(key, wrapped);
			wrappers.put(key, log);
			return log;
		}
		@SuppressWarnings("unchecked")
		LogSet<E> log = (LogSet<E>)exist;
		return log;
	}

	//////////////////////////////////////////////////////////
	/** 
     * Returns true if instances of the given class is known to be immutable; 
     * false if we don't know.
     */
	public static boolean isImmutable(@SuppressWarnings("rawtypes") Class t) {
		// 按使用频繁程度判断
		return t == String.class
				|| t == Integer.class || t == Long.class
				|| t == Boolean.class || t == Short.class
				|| t == Byte.class || t == Character.class
				|| t == Float.class || t == Double.class
				|| t.isPrimitive()
				//|| t == BigInteger.class || t == BigDecimal.class || t.isEnum()
				;
	}

	/** 
     * Returns true if instances of the given class is known to be immutable; 
     * false if we don't know.
     */
	public static boolean isImmutable(Object obj) {
		return isImmutable(obj.getClass());
	}

	/**
	 * bean managed setup.
	 * 
	 * @param bean    bean to setup
	 * @param parent  parent reference
	 * @param varname name in parent
	 * @param log 记录日志，当事务失败时回滚，恢复旧的parent和varname。
	 * 	      在数据从db.h第一次装载进来时，不需要记录日志，总是设置成功。@see TRecord
	 * @exception NullPointerException if bean is null.
	 * @exception XManagedError if setup fail or bean can not be managed.
	 */
	public static void xdbParent(Object bean, XBean parent, String varname, boolean log) {
		if (null == bean)
			throw new NullPointerException();

		if (bean instanceof Bean) {
			// 防止 XBean.Data 对象加入
			if (!(bean instanceof XBean))
				throw new XManagedError();

			((XBean)bean).xdbParent(parent, varname, log);
			return;
		}

		// 因为存在Any类型，这里无法作更多检查了。
		/*
		if (isImmutable(bean))
			return;
		throw new XManagedError();
		*/
	}

	public static void xdbParent(Object bean, XBean parent, String varname) {
		Logs.xdbParent(bean, parent, varname, true);
	}

	/**
	 * 检查bean没有被管理.
	 * 
	 * @param bean
	 * @exception NullPointerException if bean is null.
	 * @exception XManagedError if bean has managed or bean can not be managed.
	 */
	public static void xdbManagedCheck(Object bean) {
		if (null == bean)
			throw new NullPointerException();

		if (bean instanceof Bean) {
			// 防止 XBean.Data 对象加入
			if (!(bean instanceof XBean))
				throw new XManagedError();

			if (((XBean)bean).xdbManaged())
				throw new XManagedError();

			return;
		}

		// 因为存在Any类型，这里无法作更多检查了。
		/*
		if (isImmutable(bean))
			return;
		throw new XManagedError();
		*/
	}

	public static void logNotify(XBean xbean, xdb.logs.LogNotify notify) {
		xbean.xdbLogNotify(notify);
	}
}

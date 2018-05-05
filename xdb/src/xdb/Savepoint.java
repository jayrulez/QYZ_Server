package xdb;

import java.util.*;

public final class Savepoint {
	// 靠! LinkedHashMap 居然没有按相反顺序遍历的iterator。
	Map<LogKey, Log> logs = new HashMap<LogKey, Log>();
	List<Log> addOrder = new ArrayList<Log>();
	int access = 0;

	int commit() {
		for (Log log : addOrder) log.commit();
		// 按相反顺序提交。收集LogNotify时，可以得到最外层事务开始旧数据。
		//for (int i = addOrder.size() - 1; i >= 0; --i) addOrder.get(i).commit();
		return addOrder.size();
	}

	int rollback() {
		// 按加入相反顺序回滚。
		// 当set或者map的key使用自定义xbean时，可以在加入容器前修改(hashCode会发生变化)，
		// 此时需要按造相反顺序回滚，先回滚容器，然后是xbean本身。
		for (int i = addOrder.size() - 1; i >= 0; --i)
			addOrder.get(i).rollback();
		return addOrder.size();
	}

	public int size() {
		return addOrder.size();
	}

	public boolean isAccessSince(int a) {
		return a != access;
	}

	public int getAccess() {
		return access;
	}

	public Log get(LogKey key) {
		++ access;
		return logs.get(key);
	}

	public void add(LogKey key, Log log) {
		++ access;
		Log old = logs.put(key, log);
		if (null != old) {
			logs.put(key, old); // 恢复原值，比较恶心
			throw new XError("xdb.Savepoint.add duplicate log");
		}
		addOrder.add(log);
	}

	public boolean addIfAbsent(LogKey key, Log log) {
		++ access;
		if (!logs.containsKey(key)) {
			logs.put(key, log);
			addOrder.add(log);
			return true;
		}
		return false;
	}

	///////////////////////////////////////////////////////////
	// test
	private static class L implements Log {
		private String name;
		L(String name) {
			this.name = name;
		}
		@Override
		public void commit() {
		}
		@Override
		public void rollback() {
		}
		@Override
		public String toString() {
			return name;
		}
	}
	public static void main(String args[]) {
		Savepoint sp = new Savepoint();
		XBean x1 = new XBean(null, null);
		XBean x2 = new XBean(null, null);
		XBean x3 = new XBean(null, null);
		sp.add(new LogKey(x3, "c"), new L("c"));
		sp.add(new LogKey(x2, "b"), new L("b"));
		sp.add(new LogKey(x1, "a"), new L("a"));
		System.out.println(sp.addOrder);
	}
}

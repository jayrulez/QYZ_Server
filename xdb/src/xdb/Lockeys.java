package xdb;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 对于相同的名字和key必须能得到同一个Lockey实例。
 * 这就是这个类的最大功能。
 */
public final class Lockeys {

	private final Locks locks = new Locks();	
	
	/**
	 * for test only
	 * @param name
	 * @param key
	 * @return
	 */
	public final boolean exist(String name, Object key) {
		return locks.exist(new Lockey(name, this.getLockId(name), key));
	}

	private final Lockey get(Lockey lockey) {
		// 优化，先看当前事务是否持有，若持有，直接返回
		Transaction curTransaction = Transaction.current();
		if (curTransaction != null) {
			Lockey lockey1 = curTransaction.get(lockey);
			if (lockey1 != null) 
				return lockey1;
		}
		return locks.get(lockey);
	}

	public final int size() {
		return locks.size();
	}

	/////////////////////////////////////////////////////////////////////
	// 没有锁保护，Xdb.start 的时候，调用一次 getInstance
	private static Lockeys instance = new Lockeys();

	public static Lockeys getInstance() {
		return instance;
	}

	/**
	 *  key 必须实现 hashCode, equals, Comparable。
	 * @param name
	 * @param key
	 * @return Lockey 实例
	 * @deprecated
	 * 		使用 新方法 get(TTable<?, ?> ttable, Object key).
	 */
	public static Lockey get(String name, Object key) {
		return instance.get(new Lockey(name, instance.getLockId(name), key));
	}

	// 本方法不公开。原则上，编号是内部信息。
	static Lockey get(String name, int index, Object key) {
		return instance.get(new Lockey(name, index, key));
	}

	/**
	 * 返回表格单个记录锁。
	 * @param ttable
	 * @param key
	 * @return Lockey 实例
	 */
	public static Lockey get(TTable<?, ?> ttable, Object key) {
		return get(ttable.getLockName(), ttable.getLockId(), key);
	}

	/**
	 * 返回表格多个记录锁。当多个key都是已知并且离散时，使用这个方法。如：
	 * get(ttable, 1, 2, 3);
	 * @param ttable
	 * @param keys
	 * @return
	 */
	public static Lockey[] get(TTable<?, ?> ttable, Object ... keys) {
		final String lockName = ttable.getLockName();
		final int lockId = ttable.getLockId();
		final Lockey[] lockeys = new Lockey[keys.length];

		int i = 0;
		for (Object key : keys)
			lockeys[i++] = get(lockName, lockId, key);

		return lockeys;
	}

	/**
	 * 返回多个记录锁。
	 * @param ttable
	 * @param keys
	 * @return
	 */
	public static Lockey[] get(TTable<?, ?> ttable, Collection<?> keys) {
		final String lockName = ttable.getLockName();
		final int lockId = ttable.getLockId();
		final Lockey[] lockeys = new Lockey[keys.size()];

		int i = 0;
		for (Object key : keys)
			lockeys[i++] = get(lockName, lockId, key);

		return lockeys;
	}

	public static void lock(TTable<?, ?> table, Collection<?> keys) {
		lock(get(table, keys));
	}

	public static void lock(Lockey lockey) {
		Transaction.current().add(lockey);
	}
	
	/**
	 * 获得多条记录锁，对锁的进行顺序并且加入当前事务。
	 * 当明确知道需要锁多条记录时，使用这个方法，可以减少死锁的可能。
	 * @param lockeys 锁
	 */
	public static void lock(Lockey[] lockeys) {
		java.util.Arrays.sort(lockeys);
		for (Lockey lockey : lockeys) {
			//xdb.Trace.debug("add" + lockey);
			Transaction.current().add(lockey);
		}
	}
	/////////////////////////////////////////////////////////////////////
	// 锁编号管理
	private final Map<String, Integer> lockIdMap = new HashMap<String, Integer>();
	private int autoId = 0;
	private final Lock syncIdMap = new ReentrantLock();

	/**
	 * <p>获得锁名的编号。当编号不存在时，动态分配一个。
	 * <p>这个方法需要同步保护，还需要查找，在原则上，以后创建锁都通过Table，而不通过这个方法。
	 * @param lockname
	 * @return
	 */
	final int getLockId(String lockname) {
		syncIdMap.lock();
		try {
			Integer i = lockIdMap.get(lockname);
			if (null != i)
				return i;
			++ autoId;
			lockIdMap.put(lockname, autoId);
			return autoId;
		} finally {
			syncIdMap.unlock();
		}
	}

	/**
	 * xdb启动时，给现有的锁编号。忽略非用户表。
	 * <p>问题：是否xdb启动后分配的编号和初始化时分配的编号？
	 * @param tables
	 */
	final void initializeLockIdMap(Collection<Table> tables) {
		Set<String> lockNames = new HashSet<String>();
		for (Table table : tables) {
			if (table instanceof TTable<?, ?>) { // 忽略非用户表 
				TTable<?, ?> ttable = (TTable<?, ?>)table;
				lockNames.add(ttable.getLockName());
			}
		}
		initializeLockIdMap(lockNames.toArray(new String[lockNames.size()]));
	}

	final void initializeLockIdMap(String[] lockNames) {
		// 排序以后，锁的顺序就不会由于逻辑执行顺序而变。有利于...(我也说不上)。
		Arrays.sort(lockNames);
		for (String lockName : lockNames)
			this.getLockId(lockName);
	}
}

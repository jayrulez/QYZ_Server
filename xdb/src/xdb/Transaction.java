package xdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;

import xdb.Procedure.Broadcast;
import xdb.Procedure.ExecuteProcedure;
import xdb.Procedure.ExecuteRunnable;
import xdb.Procedure.SendResponse;
import xdb.Procedure.SendToRole;
import xdb.Procedure.SendToRoles;
import xdb.Procedure.SendToXio;
import xdb.Procedure.Task;

public final class Transaction {
	private int trancount = 0;
	private List<Savepoint> logs = new ArrayList<Savepoint>();

	// friend class Logs
	Map<LogKey, Object> wrappers = new HashMap<LogKey, Object>();

	//////////////////////////////////////////////////////////////////////
	// Record 是模板，比较讨厌。
	// 也许该把 Record 记录下来。 dump 信息丰富一些。
	// 从 Lockey 找到 Record 会有点不方便。
	// 先这样拉，还比较简洁。{优化，本地缓存Lockey，可重入锁的lock，先检查本地，没有再去Lockeys取}
	private HashMap<Lockey, Lockey> locks = new HashMap<Lockey, Lockey>();
	
	// 记录改动过的表 { 优化，Listener在logNotify时，只迭代修改过的表 }
	private HashMap<String, TTable<?, ?>> logNotifyTTables = new HashMap<String, TTable<?, ?>>();
	
	// 本次事务cache住用过的记录
	// { 优化：TRecord使用过后，本地cache住，减少TTableCache的锁冲突 }
	// { 前提：cachedTRecord存在同时事务持有记录锁，所以select方法中不能加cache }
	private HashMap<String, HashMap<Object, Object>> cachedTRecord = new HashMap<String, HashMap<Object, Object>>();

	/**
	 * 添加事务锁
	 */
	public void add(Lockey lockey) {
		if (null == locks.get(lockey)) {
			// 成功得到锁以后才加入容器。
			lockey.lock();
			locks.put(lockey, lockey);
		}
	}
	
	/**
	 * 添加修改过的脏表，{优化}
	 */
	public void addLogNotifyTTable(TTable<?, ?> ttable) {
		logNotifyTTables.put(ttable.getName(), ttable);
	}
	
	/**
	 * 添加TRecord的缓存，{优化}
	 */
	<K, V> void addCachedTRecord(TTable<K, V> ttable, TRecord<K, V> r) {
		HashMap<Object, Object> cachedForTable = cachedTRecord.get(ttable.getName());
		if (cachedForTable == null) {
			cachedForTable = new HashMap<Object, Object>();
			cachedTRecord.put(ttable.getName(), cachedForTable);
		}
		cachedForTable.put(r.getKey(), r);
	}
	
	/**
	 * 删除TRecord的缓存，{优化}
	 */
	<K, V> void rmvCachedTRecord(TTable<K, V> ttable, K k) {
		HashMap<Object, Object> cachedForTable = cachedTRecord.get(ttable.getName());
		if (cachedForTable != null) {
			cachedForTable.remove(k);
		}
	}
	
	/**
	 * 获得TRecord的缓存，{优化}
	 */
	@SuppressWarnings("unchecked")
	<K, V> TRecord<K, V> getCachedTRecord(TTable<K, V> ttable, K k) {
		HashMap<Object, Object> cachedForTable = cachedTRecord.get(ttable.getName());
		if (cachedForTable == null) {
			cachedForTable = new HashMap<Object, Object>();
			cachedTRecord.put(ttable.getName(), cachedForTable);
		}
		return (TRecord<K, V>)cachedForTable.get(k);
	}
	
	/**
	 * 获得事务已加的锁，{优化}
	 */
	Lockey get(Lockey lockey) {
		return locks.get(lockey);
	}

	/**
	 * 结束事务，释放所有锁并且清除，清除wrapper。
	 */
	private void finish() {
		wrappers.clear();

		// 没有按照lock的顺序unlock。
		for (Lockey lockey : locks.values()) {
			// Trace.debug("unlock " + lockey);
			try {
				lockey.unlock();
			} catch (Throwable e) {
				Trace.fatal("unlock " + lockey, e);
			}
		}
		locks.clear();
		cachedTRecord.clear();
	}

	//////////////////////////////////////////////////////////////////////
	public void begin() {
		++trancount;
	}

	public int trancount() {
		return trancount;
	}

	public void commit() {
		if (--trancount < 0)
			throw new XError("xdb: commit mismatch");
	}

	int savepoint() {
		logs.add(new Savepoint());
		return logs.size();
	}

	/**
	 * 返回当前保存点编号。0 表示没有保存点。
	 */
	public int currentSavepointId() {
		return logs.size();
	}

//	/**
//	 * 返回当前保存点。null 表示没有保存点。
//	 */
//	public Savepoint getCurrentSavepoint() {
//		if (logs.isEmpty())
//			return null;
//		return logs.get(logs.size() - 1);
//	}

	/**
	 * 根据编号得到保存点。null 表示没有找到保存点。
	 */
	public Savepoint getSavepoint(int savepoint) {
		if (savepoint < 1 || savepoint > logs.size())
			return null;
		return logs.get(savepoint - 1);
	}

	public void rollback(int savepoint) {
		if (savepoint < 1 || savepoint > logs.size())
			throw new XError("xdb: invalid savepoint " + savepoint + "@" + logs.size());

		while (logs.size() >= savepoint)
			logs.remove(logs.size() - 1).rollback();

		--trancount;
		// 如果需要回滚整个事务，抛出异常，see Procedure.rollbackAll，其他情况都是匹配一个begin。
	}

	private static ThreadLocal<Transaction> threadlocal = new ThreadLocal<Transaction>();

	public static final Transaction create() {
		Transaction t = threadlocal.get();
		if (null == t)
			threadlocal.set(t = new Transaction());
		return t;
	}

	public static final void destroy() {
		threadlocal.set(null);
	}

	public static final Transaction current() {
		return threadlocal.get();
	}

	public static final Savepoint currentSavepoint() {
		Transaction current = current();
		return current.logs.get(current.logs.size() - 1);
	}

	public static final void verify() {
		if (threadlocal.get() == null)
			throw new XError("Transaction.verify");
	}

	////////////////////////////////////////////////////////////////////////////
	// Procedure 执行点。
	/**
	 * 回滚所有的修改，返回回滚的日志数量。
	 */
	private void _last_rollback_() {
//		int count = 0;
		try {
			for (int index = logs.size() - 1; index >= 0; --index)
				/*count += */logs.get(index).rollback();
			logs.clear();
			trancount = 0;
		} catch (Throwable err) {
			// 如果发生错误，此时数据已经处于不正常状态.
			Trace.fatal("last rollback ", err);
			Runtime.getRuntime().halt(54321);
		}
//		return count;
	}

	/**
	 * 提交所有的修改，返回提交的日志数量。
	 */
	private int _real_commit_() {
		if (0 != trancount) // 检查 begin 与 commit/rollback 是否匹配
			throw new XError("xdb: mismatch (begin,commit/rollback) trancount=" + trancount);
		int count = 0;
		for (Savepoint sp : logs)
			count += sp.commit();
		logs.clear();
		return count;
	}

	// 参数用于调试输出,没有其他作用.
	private void logNotify(Procedure p) {
		try {
			int maxNestNotify = 255; // configure ?
			for (int nest = 0; nest < maxNestNotify; ++nest) {
				HashMap<String, TTable<?, ?>> curLogNotifyTTables = logNotifyTTables;
				logNotifyTTables = new HashMap<String, TTable<?, ?>>();
				for (TTable<?, ?> ttable : curLogNotifyTTables.values()) {
					ttable.logNotify();
				}
//				Xdb.getInstance().getTables().logNotify(); // 未优化"只logNotify有改变的表格"前的版本。
				if (_real_commit_() == 0)
					return; // 嵌套的通告终于完成了。
			}
			// 通告次数太多。可能出现了循环。
			Trace.fatal("reach maxNestNotify. proc=" + p.getClass().getName());
		} catch (Throwable e) {
			Trace.fatal("logNotify", e);
		}
		// 尝试回滚最后一次"Xdb.getInstance().getTables().logNotify()"中的修改。
		_last_rollback_();
		
		logNotifyTTables.clear(); // 错误时清除记录下来的表格。
	}

	// 按存储过程名字分别统计事务数量。
	private static xdb.util.Counter counter = new xdb.util.Counter(Xdb.mbeans(), "xdb", "Procedures");
	// 总的事务数量: totalCount = totalFalse + totalException + 成功执行的。
	private static AtomicLong totalCount = new AtomicLong();
	private static AtomicLong totalFalse = new AtomicLong();
	private static AtomicLong totalException = new AtomicLong();

	public static long getTotalCount()       { return totalCount.get(); }
	public static long getTotalFalse()       { return totalFalse.get(); }
	public static long getTotalException()   { return totalException.get(); }

	public void perform(Procedure p) throws Throwable {
		try {
			// 总数 = .True(未统计此项) + .False + .Exception
			counter.increment(p.getClass().getName());
			totalCount.incrementAndGet();

			// flush lock . MEMORY类型的表本来不需要这个锁，为了不复杂化流程，不做特殊处理。
			Lock flushLock = Xdb.getInstance().getTables().flushReadLock();
			flushLock.lockInterruptibly();
			try {
				if (p.call()) {
					if (_real_commit_() > 0)
						logNotify(p);
					// else : 没有修改，不需要logNotify。至此过程处理已经完成了。
					// .True
				} else {
					// 执行逻辑返回false统计
					counter.increment(p.getClass().getName() + ".False");
					totalFalse.incrementAndGet();
					_last_rollback_(); // 应用返回 false，回滚。
				}

			} catch (Throwable e) {
				_last_rollback_(); // 未处理的异常，回滚。
				throw e;

			} finally {
				this.doneRunAllTask();
				this.finish();
				flushLock.unlock();
			}

		} catch (Throwable e) {
			p.setException(e);
			p.setSuccess(false);
			// 执行异常统计
			counter.increment(p.getClass().getName() + ".Exception");
			totalException.incrementAndGet();
			// 所有的异常错误都应该处理，尽量不抛到这里。这里仅记录日志。
			Trace.log(p.getConf().getTrace(), "Transaction Perform Exception", e);
			throw e;
		}
	}

	
	
	////////////////////////////////
	/**
	 * 看起来下面的内容简直就是Procedure.ppsendxxxx()的翻版，差别在于：
	 * 1. 改名为Transition.tpsendxxxx()~~~.....
	 * 2. 这里的所有task的操作都是在***事务call()结束后，而事务并没有释放lock之前调用的***
	 * 	  而Procedure.ppsendxxxxx()的情况则是事务call()结束，并且释放lock之后调用的.
	 * 
	 * 使用ppsend潜在的风险:
	 * 	 假设
	 * 		ProcedureA{ lock(all); psend(PA); }
	 * 		ProcedureB{ lock(all); psend(PB); }
	 * 	正常情况下：
	 * 		ProcedureA先执行，ProcedureB后执行，则客户端先收到PA，再收到PB
	 * 	而在一些极端的情况下，由于psend操作的时候，事务已经放开lock了，此时,客户端有可能先收到PB，再收到PA
	 */
	//
	//add by cjc
	//
	
	public static void tsend(long roleid, xio.Protocol p) 
	{
		tpost(new SendToRole(roleid, p));
	}

	public static void tsend(long roleid1, long roleid2, xio.Protocol p) 
	{
		tpost(new SendToRoles(roleid1, roleid2, p));
	}

	public static void tsend(java.util.Collection<Long> roleids, xio.Protocol p) 
	{
		tpost(new SendToRoles(roleids, p));
	}

	public static void tsendResponse(xio.Protocol pFromLink, xio.Protocol p) 
	{
		tpost(new SendResponse(pFromLink, p));
	}

	public static void tbroadcast(xio.Protocol p, int timems) 
	{
		tpost(new Broadcast(p, timems));
	}

	public static void tsend(xio.Xio conn, xio.Protocol p) 
	{
		tpost(new SendToXio(conn, p));
	}

	public static void texecute(Procedure p) 
	{
		tpost(new ExecuteProcedure(p));
	}

	public static void texecute(Runnable r) 
	{
		tpost(new ExecuteRunnable(r));
	}
	//////////////////////////////////////////////////////////////////////////////
	// 以下方法提交的任务在存储过程最终提交时有效。

	public static void tsendWhileCommit(long roleid, xio.Protocol p) {
		tpostWhileCommit(new SendToRole(roleid, p));
	}

	public static void tsendWhileCommit(long roleid1, long roleid2, xio.Protocol p) {
		tpostWhileCommit(new SendToRoles(roleid1, roleid2, p));
	}

	public static void tsendWhileCommit(java.util.Collection<Long> roleids, xio.Protocol p) {
		tpostWhileCommit(new SendToRoles(roleids, p));
	}

	public static void tsendResponseWhileCommit(xio.Protocol pFromLink, xio.Protocol p) {
		tpostWhileCommit(new SendResponse(pFromLink, p));
	}

	public static void tbroadcastWhileCommit(xio.Protocol p, int timems) {
		tpostWhileCommit(new Broadcast(p, timems));
	}

	public static void tsendWhileCommit(xio.Xio conn, xio.Protocol p) {
		tpostWhileCommit(new SendToXio(conn, p));
	}

	public static void texecuteWhileCommit(Procedure p) {
		tpostWhileCommit(new ExecuteProcedure(p));
	}

	public static void texecuteWhileCommit(Runnable r) {
		tpostWhileCommit(new ExecuteRunnable(r));
	}
	///////////////////////////////////////////////////////////////////
	// 以下方法提交的任务在存储过程回滚时有效。
	public static void tsendWhileRollback(long roleid, xio.Protocol p) {
		tpostWhileRollback(new SendToRole(roleid, p));
	}

	public static void tsendWhileRollback(long roleid1, long roleid2, xio.Protocol p) {
		tpostWhileRollback(new SendToRoles(roleid1, roleid2, p));
	}

	public static void tsendWhileRollback(java.util.Collection<Long> roleids, xio.Protocol p) {
		tpostWhileRollback(new SendToRoles(roleids, p));
	}

	public static void tsendResponseWhileRollback(xio.Protocol pFromLink, xio.Protocol p) {
		tpostWhileRollback(new SendResponse(pFromLink, p));
	}

	public static void tbroadcastWhileRollback(xio.Protocol p, int timems) {
		tpostWhileRollback(new Broadcast(p, timems));
	}

	public static void tsendWhileRollback(xio.Xio conn, xio.Protocol p) {
		tpostWhileRollback(new SendToXio(conn, p));
	}

	public static void texecuteWhileRollback(Procedure p) {
		tpostWhileRollback(new ExecuteProcedure(p));
	}

	public static void texecuteWhileRollback(Runnable r) {
		tpostWhileRollback(new ExecuteRunnable(r));
	}
	
	private static void tpostWhileCommit(Task task) 
	{
		task.setExpected(true);
		Transaction.currentSavepoint().add(new LogKey(new XBean(null, null), ""), task);
		tpost(task);
	}

	private static void tpostWhileRollback(Task task) 
	{
		task.setExpected(false);
		Transaction.currentSavepoint().add(new LogKey(new XBean(null, null), ""), task);
		tpost(task);
	}
	
	private static void tpost(Task task) 
	{
		current()._ttasks.add(task);
	}
	
	private ArrayList<Task> _ttasks = 
		new ArrayList<Task>();
	
	private void doneRunAllTask()
	{
		for (Task task : this._ttasks) 
		{
			try 
			{
				task.process();
			} catch (Throwable e) 
			{//仅仅记录日志
				xdb.Trace.error("Transaction.runTasks", e);
			}
		}
		_ttasks.clear();
	}
	
}

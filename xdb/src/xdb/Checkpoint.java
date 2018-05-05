package xdb;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * <pre>
 * XDB 并发分析和优化
 * 
 * 事务的开始
 *    略。
 *    总结： 事务是什么。
 *
 * 事务的实现
 *    Table 需要实现三个基本操作，add, remove, get, 数据层是两个Map。一个是 MapCache，处于XDB的控制下；
 *    另一个是 MapData，就是 db.h 的表，是 xDB 不能控制的。先不考虑把修改保存到 MspData 中时，为了能让
 *    事务工作，最终的结果是得到TRecord的5个状态。
 *    此时，所有的事务都能工作了，只是所有的修改都不会被存下来。整个数据层次如下：
 *    总结： TTable(add, remove, get) on TTableCache + TStorage
 *
 * 保存和事务并发
 *    所有的事务访问都在 Tables.flushReadLock 内执行。保存在 Tables.flushWriteLock 内执行，
 *    保存过程包括，系列化(marshal)，修改记录状态和Cache(snapshot)，写入(flush)。
 *    通过 flushLock 的保护，当保存开始时，实际上停止了所有的事务，防止把正在执行中的事务的脏数据保存下来。
 *    当保存完成以后，释放flushWriteLock，让事务能开始执行。
 *    这就是未优化版本的XDB的并发设计。
 *    总价： Tables.flushWriteLock { marshal + snapshot + flush }
 *
 * 性能问题
 *    由于保存需要停止所有事务，会造成并发上的瓶颈。优化的目的就是让保存时，停止事务的时间尽量短。
 *
 * 优化思路
 *    把 marshal 和 flush 操作放到 flushLock 之外执行。
 *    其中 marshal 采用记录锁和事务并发执行；
 *    snapshot 仍然在 flushLock 之内执行，由于剩下的工作很少，执行的很快；
 *    flush 就把 snapshot 的快照数据写入，几乎是不需要锁的。由于 snapshot 执行后，事务层就
 *    认为所有的数据已经保存好了，而这时记录实际上还没有刷新到Storage中，此时需要修改原来直接
 *    查询底层数据的接口，让它们先从正在保存快照中查询。这样snapshot就会被事务并发访问，需要保护。
 *
 * 总结： 整个实现从 Checkpoint.checkpoint(XdbConf ) 开始看.
 * </pre>
 */
final class Checkpoint extends ThreadHelper implements CheckpointMBean {
	private Tables tables;

	// 只有 Checkpoint 线程会修改这些值。 JMX需要读取，用个 volatile 表示一下。
	private volatile long marshalNCount = 0;
	private volatile long marshal0Count = 0;
	private volatile long snapshotCount = 0;
	private volatile long flushCount   = 0;
	private volatile int  checkpointCount = 0;

	private volatile long marshalNTotalTime = 0;
	private volatile long snapshotTotalTime = 0; // 包含 marshal0 的时间
	private volatile long flushTotalTime = 0;
	private volatile long checkpointTotalTime = 0;

	private volatile long nextMarshalTime;
	private volatile long nextCheckpointTime;
	private volatile long nextFullBackupTime;
	private volatile long nextIncBackupTime;

	private Backup backup; // 如果需要查看备份状态，这里需要线程安全。暂时不实现这个功能。

	private boolean fullBackupNow = false; // 是否需要马上开始一次全备份。
	private Object fullBackupWaitQueue = new Object(); // 等待全备份结束。
	private boolean checkpointNow = false; // 马上执行一次checkpoint
	private Object checkpointWaitQueue = new Object(); // checkpoint执行等待队列。

	private volatile boolean allowBackup = true;

	// 危险参数。仅用于测试。
	private boolean allowCheckpointXXX = true; // 是否允许执行DB.H的checkpoint。

	@Override
	public boolean isAllowBackup() {
		return allowBackup;
	}

	@Override
	public void setAllowBackup(boolean allowBackup) {
		this.allowBackup = allowBackup;
		if (false == allowBackup)
			this.fullBackupDone(); // 伪造备份结束，唤醒备份请求。
	}

	@Override
	public void fullBackup(long waitTimeout) throws InterruptedException {
		if ( false == allowBackup )
			throw new IllegalStateException("backup disabled");

		synchronized(this.fullBackupWaitQueue) {
			this.fullBackupNow = true;
			this.wakeup();
			if ( waitTimeout >= 0 )
				this.fullBackupWaitQueue.wait(waitTimeout);
		}

		if ( false == allowBackup ) // 这里并不区分是否备份被禁止还是正常备份完成。
			throw new IllegalStateException("backup disabled");
	}

	private boolean fullBackupNow() {
		synchronized(this.fullBackupWaitQueue) { return this.fullBackupNow; }
	}

	private void fullBackupDone() {
		synchronized(this.fullBackupWaitQueue) {
			this.fullBackupNow = false;
			this.fullBackupWaitQueue.notifyAll();
		}
	}

	@Override
	public void checkpoint(long waitTimeout) throws InterruptedException {
		synchronized(this.checkpointWaitQueue) {
			this.checkpointNow = true;
			this.wakeup();
			if ( waitTimeout == 0 )
				this.checkpointWaitQueue.wait();
			else if (waitTimeout > 0)
				this.checkpointWaitQueue.wait(waitTimeout);
		}
	}

	private boolean checkpointNow() {
		synchronized(this.checkpointWaitQueue) { return this.checkpointNow; }
	}

	private void checkpointDone() {
		synchronized(this.checkpointWaitQueue) {
			this.checkpointNow = false;
			this.checkpointWaitQueue.notifyAll();
		}
	}

	private class Backup extends ThreadHelper {
		private boolean isFull;

		Backup(boolean isFull) {
			super("xdb.Backup");
			this.isFull = isFull;
			super.start();
		}

		@Override
		public void run() {
			// 发生任何错误，只记录日志。
			try {
				if (this.isFull) {
					Checkpoint.this.tables.backupFull();
					Checkpoint.this.fullBackupDone();
				} else {
					Checkpoint.this.tables.backupInc();
				}
			} catch (Throwable ex) {
				Trace.error("backup", ex);
			}
		}
	}

	Checkpoint(Tables tables) {
		super("xdb.Checkpoint");

		this.tables = tables;

		long now = System.currentTimeMillis();
		XdbConf conf = Xdb.getInstance().getConf();

		nextMarshalTime = now + conf.getMarshalPeriod();
		nextCheckpointTime = now + conf.getCheckpointPeriod();
		nextFullBackupTime = fixedRateFullTime(now, conf);
		nextIncBackupTime = fixedRateIncTime(now, conf);
		allowBackup = conf.isAllowBackup();
		allowCheckpointXXX = conf.isAllowCheckpointXXX();

		Xdb.mbeans().register(this, "xdb:type=Xdb,name=Checkpoint");
	}

	private final long fixedRateFullTime(long now, XdbConf conf) {
		int fullPeriod = conf.getBackupFullPeriod();
		return now / fullPeriod * fullPeriod + fullPeriod
				+ xdb.Xdb.random().nextInt(1800) * 1000;
	}

	private final long fixedRateIncTime(long now, XdbConf conf) {
		int fullPeriod = conf.getBackupFullPeriod();
		int incPeriod = conf.getBackupIncPeriod();
		return now / fullPeriod * fullPeriod + (now % fullPeriod) / incPeriod * incPeriod + incPeriod;
	}

	private void checkpoint(final long now, XdbConf conf) {
		try {
			if (conf.getMarshalPeriod() >= 0 && nextMarshalTime <= now && conf.isAutoMarshal()) {
				nextMarshalTime = now + conf.getMarshalPeriod();
				long start = System.nanoTime();
				int countMarshalN = 0;
				for (Storage storage : tables.getStorages())
					countMarshalN += storage.marshalN();
				this.marshalNCount += countMarshalN;
				this.marshalNTotalTime += System.nanoTime() - start;
				Trace.debug("marshalN=*/" + countMarshalN);
			}

			// 如果备份正在进行，即使设置了checkpointNow，也不会马上执行，而是等待下一个循环再次尝试。
			final int checkpointPeriod = conf.getCheckpointPeriod();
			if (checkpointPeriod >= 0 && false == this.backuping()
					&& (this.checkpointNow() || nextCheckpointTime <= now)) {
				nextCheckpointTime = now + checkpointPeriod;
				checkpoint(conf);
			}

		} catch (Throwable e) {
			Trace.fatal("halt program", e);
			Runtime.getRuntime().halt(54321);
		}
	}

	private final xdb.util.Elapse elapse = new xdb.util.Elapse();

	private void checkpoint(XdbConf conf) {
		Trace.debug("---------------- begin ----------------");
		final List<Storage> storages = this.tables.getStorages();

		//////////////////////////////////////////
		// marshalN
		if (conf.getMarshalN() < 1)
			Trace.warn("marshalN disabled");

		elapse.reset();
		for (int i = 1; i <= conf.getMarshalN(); ++i) {
			int countMarshalN = 0;
			for (Storage storage : storages)
				countMarshalN += storage.marshalN();
			this.marshalNCount += countMarshalN;
			Trace.debug("marshalN=" + i + "/" + countMarshalN);
		}
		this.marshalNTotalTime += elapse.elapsed();

		//////////////////////////////////////////
		// snapshot
		{
			int countSnapshot = 0;
			int countMarshal0 = 0;
			Lock lock = tables.flushWriteLock();
			lock.lock();
			elapse.reset();
			try {
				for (Storage storage : storages)
					countMarshal0 += storage.marshal0();
				for (Storage storage : storages)
					countSnapshot += storage.snapshot();
			} finally {
				lock.unlock();
			}

			final long snapshotTime = elapse.elapsedAndReset();
			if (snapshotTime / 100000 > conf.getSnapshotFatalTime())
				Trace.fatal("snapshot time=" + snapshotTime + " snapshot=" + countSnapshot + " marshal0=" + countMarshal0);

			this.marshal0Count += countMarshal0;
			this.snapshotTotalTime += snapshotTime;
			this.snapshotCount += countSnapshot;
			Trace.debug("snapshot=" + countSnapshot + " marshal0=" + countMarshal0);
		}

		//////////////////////////////////////////
		// flush
		int countFlush = 0;
		for (Storage storage : storages)
			countFlush += storage.flush();
		this.flushCount += countFlush;
		this.flushTotalTime += elapse.elapsedAndReset();
		Trace.debug("flush=" + countFlush);

		//////////////////////////////////////////
		// checkpoint
		if (countFlush > 0 && allowCheckpointXXX) {
			final Lock lock = this.tables.checkpointLock();
			lock.lock();
			try {
				this.tables.getLogger().checkpoint(storages, System.currentTimeMillis());
			} finally {
				lock.unlock();
			}
			this.checkpointTotalTime += elapse.elapsedAndReset();
			Trace.debug("checkpoint");
		}
		++ checkpointCount;
		this.checkpointDone();
		Trace.debug("----------------- end -----------------");
	}

	private boolean backuping() {
		if (null == backup)
			return false;
		if (backup.isAlive())
			return true;
		backup.joinAssuring();
		backup = null;
		return false;
	}

	private final void backup(long now, XdbConf conf) {
		// 备份过程错误处理：只记录日志。
		try {
			if (this.fullBackupNow() || nextFullBackupTime <= now) {
				nextFullBackupTime = fixedRateFullTime(now, conf);
				// 每次开始完整备份，都重新安排一次增量备份时间。
				nextIncBackupTime = fixedRateIncTime(now, conf);
				backup = new Backup(true);
			} else if (nextIncBackupTime <= now) {
				nextIncBackupTime = fixedRateIncTime(now, conf);
				/*
				 * 增量备份数据库无法拷贝直接使用，需要restore才能使用。
				 * 由于目前没有restore工具，而且这个功能从没有被用过。
				 * 现在先不实现增量备份，有了工具以后，再考虑支持。
				 */
				//backup = new Backup(false);
			}
		} catch (Throwable ex) {
			Trace.error("backup", ex);
		}
	}

	@Override
	public void run() {
		XdbConf conf = Xdb.getInstance().getConf();
		long backupDelayTime = System.currentTimeMillis() + conf.getBackupDelay();
		while (super.isRunning()) {
			long now = System.currentTimeMillis();
			this.checkpoint(now, conf);

			if ( allowBackup && now > backupDelayTime && !backuping() )
				this.backup(now, conf);

			super.sleepIdle(100); // 检测精度，相当于最小间隔。
		}

		// final checkpoint process

		// wait backup done
		try {
			if (this.backuping()) {
				Trace.fatal("wait backup done ...");
				backup.joinAssuring(); // wait backup done
				backup = null;
			}
		} catch (Throwable e) {
			Trace.error("backup", e);
		}

		// final checkpoint
		Trace.fatal("final checkpoint begin");
		checkpoint(conf);
		Trace.fatal("final checkpoint end");
	}

	// 监控调试

	@Override
	public long getCountMarshalN() {
		return marshalNCount;
	}

	@Override
	public long getCountMarshal0() {
		return marshal0Count;
	}

	@Override
	public long getCountFlush() {
		return this.flushCount;
	}

	@Override
	public long getTotalTimeCheckpoint() {
		return this.checkpointTotalTime;
	}

	@Override
	public long getTotalTimeFlush() {
		return this.flushTotalTime;
	}

	@Override
	public long getTotalTimeSnapshot() {
		return snapshotTotalTime;
	}

	@Override
	public int getCountCheckpoint() {
		return checkpointCount;
	}

	@Override
	public long getTotalTimeMarshalN() {
		return marshalNTotalTime;
	}

	public long getNextFlushTime() {
		return nextMarshalTime;
	}

	public long getNextCheckpointTime() {
		return nextCheckpointTime;
	}

	public long getNextFullBackupTime() {
		return nextFullBackupTime;
	}

	public long getNextIncBackupTime() {
		return nextIncBackupTime;
	}

	@Override
	public String getTimeOfNextCheckpoint() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return dateFormat.format(this.getNextCheckpointTime());
	}

	@Override
	public String getTimeOfNextFlush() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return dateFormat.format(this.getNextFlushTime());
	}

	@Override
	public String getTimeOfNextFullBackup() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return dateFormat.format(this.getNextFullBackupTime());
	}

	@Override
	public String getTimeOfNextIncBackup() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return dateFormat.format(this.getNextIncBackupTime());
	}

	@Override
	public int getPeriodCheckpoint() {
		return xdb.Xdb.getInstance().getConf().getCheckpointPeriod();
	}

	@Override
	public void setPeriodCheckpoint(int period) {
		xdb.Xdb.getInstance().getConf().setCheckpointPeriod(period);
	}

	@Override
	public long getCountSnapshot() {
		return this.snapshotCount;
	}
}

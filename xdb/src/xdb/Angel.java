package xdb;

import java.lang.management.LockInfo;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadMXBean;
import java.lang.management.ManagementFactory;
import java.util.*;
import java.lang.management.ThreadInfo;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import xdb.util.TimeoutManager;

/**
 * THIS IS NOT AN ANGEL.
 * <p>查找死锁环，并尝试中断环中的线程。随机挑选中断的线程。只有在线程等待的锁是可被中断的，中断才能成功。
 */

public final class Angel extends ThreadHelper {
	/**
	 * @return root thread group
	 */
	public static ThreadGroup getRootThreadGroup() {
		ThreadGroup root = Thread.currentThread().getThreadGroup();
		for (ThreadGroup p = root.getParent(); null != p; p = p.getParent())
			root = p;
		return root;
	}

	/**
	 * @return all thread in system
	 */
	public static Map<Long, Thread> getAllThreads() {
		return enumerate(getRootThreadGroup(), true);
	}

	/**
	 * 枚举指定线程组下的线程。
	 * @param   group     线程组
     * @param   recurse   a flag indicating whether also to include threads
     *                    in thread groups that are subgroups of the thread group.
	 * @return thread in the group
	 */
	public static Map<Long, Thread> enumerate(ThreadGroup group, boolean recurse) {
		Thread [] threads = new Thread[256];
		while (true) {
			int size = group.enumerate(threads, recurse);
			if (size < threads.length) {
				Map<Long, Thread> m = new HashMap<Long, Thread>();
				for (int i = 0; i < size; ++i) {
					Thread thread = threads[i];
					if (null != thread)
						m.put(thread.getId(), thread);
				}
				return m;
			}
			threads = new Thread[threads.length * 2];
		}
	}

	////////////////////////////////////////////////
	private final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
	private final ThreadGroup rootThreadGroup;
	private volatile int interruptCount = 0;
	private int sleepIdleMs = 1000;

	public int getInterruptCount() {
		return interruptCount;
	}

	Angel() {
		super("xdb.Angel");
		rootThreadGroup = getRootThreadGroup();
	}

	@Override
	public void run() {
		while (isRunning()) {
			try {
				if (detect()) {
					sleepIdleMs = 1000;
				} else {
					sleepIdleMs += 1000;
					if (sleepIdleMs > Xdb.getInstance().getConf().getAngelPeriod())
						sleepIdleMs = Xdb.getInstance().getConf().getAngelPeriod();
				}
				sleepIdle(sleepIdleMs);
			} catch (Throwable ex) {
				Trace.error("angel run " + ex);
			}
		}
	}

	/**
	 * ThreadInfo 最多只打印出8层的栈信息 see ThreadInfo.MAX_FRAMES
	 */
	private final static int MAXDEPTH = 255;

	/**
	 * 检测死锁，并尝试打破死锁环。
	 */
	private final boolean detect() {
		// 返回死锁的线程。 可能包含多个环。 以及等待环上的线程。
		long[] deadlockedThreadIds = threadMXBean.findDeadlockedThreads();
		if (null == deadlockedThreadIds)
			return false;

		// 构建死锁线程信息映射。
		Map<Long, ThreadInfo> deadlockedThreads = new HashMap<Long, ThreadInfo>();
		for (ThreadInfo tinfo : threadMXBean.getThreadInfo(deadlockedThreadIds, 
					threadMXBean.isObjectMonitorUsageSupported(),
					threadMXBean.isSynchronizerUsageSupported())) {
			try {
				// getLockOwnerId == -1。不在等待被其他线程拥有的锁. 肯定不是环的一部分。
				// 这种情况按道理不可能发生在findDeadlockedThreads的结果中。
				// 如果出现，一般是并发访问造成的，如线程被销毁了。这里简单的忽略掉。
				if (null != tinfo && tinfo.getLockOwnerId() != -1)
					deadlockedThreads.put(tinfo.getThreadId(), tinfo);
			} catch (Exception e) {
				// 并发访问： 在构建过程中，线程发生了变动。忽略这种错误。
				if (Trace.isInfoEnabled())
					Trace.info("angel critical exeption");
			}
		}

		/*
		 * 所有的线程。用来提供ThreadId到Thread的转换，java不提供这种转换。
		 * xdb.Worker 可以转换，对于其他自建线程，需要枚举系统内的所有线程，用来进行中断等操作。
		 * 在检测过程中，如果需要使用才初始化这个变量。由于线程本身动态创建和销毁的并发性，
		 * 这个'所有的线程'仅在这一次检测中有效，并且不保证所有的转换查找都能成功。
		 */
		Map<Long, Thread> allThreads = null;

		// 检测死锁环，从环中随机挑选一个线程，尝试执行中断操作。
		while ( !deadlockedThreads.isEmpty() ) {
			Map<Long, ThreadInfo> cycle = new HashMap<Long, ThreadInfo>();
			ThreadInfo tinfo = deadlockedThreads.entrySet().iterator().next().getValue();
			do {
				if (null != cycle.put(tinfo.getThreadId(), tinfo)) {
					// cycle found.

					// random_sample interrupting thread from cycle.
					//final ThreadInfo[] _cycle_ = cycle.values().toArray(new ThreadInfo[cycle.size()]);
					//final ThreadInfo interruptingThreadInfo = _cycle_[xdb.Xdb.random().nextInt(_cycle_.length)];
					final ThreadInfo interruptingThreadInfo = tinfo;

					// dump interrupt info
					StringBuilder sb = new StringBuilder("Angel.interrupt thread \"");
					sb.append(interruptingThreadInfo.getThreadName()).append("\" Id=").append(interruptingThreadInfo.getThreadId());
					sb.append(" in cycle:\n");
					for (ThreadInfo info : cycle.values()) // 打印的时候把挂在环上的线程也打出来。
						dumpThreadInfoTo(info, sb);
					Trace.fatal(sb);

					// interrupt thread
					allThreads = interrupt(interruptingThreadInfo, allThreads);

					// break and try to find another cycle
					break;
				}
			} while ((tinfo = deadlockedThreads.get(tinfo.getLockOwnerId())) != null);
			// 删除已经被处理的线程。cycle是完整的环，或者是那些等待的环已被打破剩下的孤立枝节。
			deadlockedThreads.keySet().removeAll(cycle.keySet());
		}
		return true;
	}

	private void dumpThreadInfoTo(ThreadInfo tinfo, StringBuilder sb) {
		sb.append("\"" + tinfo.getThreadName() + "\"" + " Id="
				+ tinfo.getThreadId() + " " + tinfo.getThreadState());
		if (tinfo.getLockName() != null) {
			sb.append(" on " + tinfo.getLockName());
		}
		if (tinfo.getLockOwnerName() != null) {
			sb.append(" owned by \"" + tinfo.getLockOwnerName() + "\" Id=" + tinfo.getLockOwnerId());
		}
		if (tinfo.isSuspended()) {
			sb.append(" (suspended)");
		}
		if (tinfo.isInNative()) {
			sb.append(" (in native)");
		}
		sb.append('\n');
		StackTraceElement[] stackTrace = tinfo.getStackTrace();
		int i = 0;
		for (; i < stackTrace.length && i < MAXDEPTH; i++) {
			StackTraceElement ste = stackTrace[i];
			sb.append("\tat " + ste.toString());
			sb.append('\n');
			if (i == 0 && tinfo.getLockInfo() != null) {
				Thread.State ts = tinfo.getThreadState();
				switch (ts) {
				case BLOCKED:
					sb.append("\t-  blocked on " + tinfo.getLockInfo());
					sb.append('\n');
					break;
				case WAITING:
					sb.append("\t-  waiting on " + tinfo.getLockInfo());
					sb.append('\n');
					break;
				case TIMED_WAITING:
					sb.append("\t-  waiting on " + tinfo.getLockInfo());
					sb.append('\n');
					break;
				default:
				}
			}

			for (MonitorInfo mi : tinfo.getLockedMonitors()) {
				if (mi.getLockedStackDepth() == i) {
					sb.append("\t-  locked " + mi);
					sb.append('\n');
				}
			}
		}
		if (i < stackTrace.length) {
			sb.append("\t...");
			sb.append('\n');
		}

		LockInfo[] locks = tinfo.getLockedSynchronizers();
		if (locks.length > 0) {
			sb.append("\n\tNumber of locked synchronizers = " + locks.length);
			sb.append('\n');
			for (LockInfo li : locks) {
				sb.append("\t- " + li);
				sb.append('\n');
			}
		}
		sb.append('\n');
	}

	/**
	 * 中断线程，吃掉所有错误。
	 */
	private final Map<Long, Thread> interrupt(ThreadInfo tinfo, Map<Long, Thread> allThreads) {
		try {
			Worker worker = Worker.get(tinfo.getThreadId());
			if (worker != null) {
				worker.angelInterrupt();
				interruptCount ++;
				return allThreads;
			}

			// 当死锁线程不是Worker时，查找系统内的所有线程，也尝试中断。
			if (null == allThreads)
				allThreads = enumerate(rootThreadGroup, true);

			Thread thread = allThreads.get(tinfo.getThreadId());
			if (null != thread) {
				thread.interrupt();
				interruptCount ++;
				return allThreads;
			}
			if (Trace.isInfoEnabled())
				Trace.info("Angle.logonly: thread not found. " + tinfo);

		} catch ( Throwable e ) {
			Trace.fatal("Angle.interrupt " + tinfo, e);
		}
		return allThreads;
	}

	/**
	 * 加上超时修饰。在任务执行时进行计时，如果任务超时，尝试中断任务。
	 * 如果timeout <= 0，不进行修饰直接返回。
	 * @param <V>
	 * @param task
	 * @param timeout
	 * @return decorate task with timeout if timeout > 0. 
	 */
	public static <V> Callable<V> decorate(Callable<V> task, long timeout) {
		return timeout > 0 ? new TimeoutCallable<V>(task, timeout) : task;
	}

	/**
	 * 加上超时修饰。在任务执行时进行计时，如果任务超时，尝试中断任务。
	 * 如果timeout <= 0，不进行修饰直接返回。
	 * @param <V>
	 * @param task
	 * @param timeout
	 * @return decorate task with timeout if timeout > 0. 
	 */
	public static <V> Callable<V> decorate(Runnable task, V result, long timeout) {
		final Callable<V> callable = Executors.callable(task, result);
		return timeout > 0 ? new TimeoutCallable<V>(callable, timeout) : callable;
	}

	/**
	 * 加上超时修饰。在任务执行时进行计时，如果任务超时，尝试中断任务。
	 * 如果timeout <= 0，不进行修饰直接返回。
	 * @param <V>
	 * @param task
	 * @param timeout
	 * @return decorate task with timeout if timeout > 0. 
	 */
	public static Callable<?> decorateCallable(Runnable task, long timeout) {
		return decorate(task, null, timeout);
	}

	/**
	 * 加上超时修饰。在任务执行时进行计时，如果任务超时，尝试中断任务。
	 * 如果timeout <= 0，不进行修饰直接返回。
	 * <b>ThreadPoolExecutor.execute 专用版本，可以少创建一个 Callable 对象。</b>
	 * @param task
	 * @param timeout
	 * @return decorate task with timeout if timeout > 0.
	 */
	public static Runnable decorateRunnable(Runnable task, long timeout) {
		return timeout > 0 ? new TimeoutRunnable(task, timeout) : task;
	}

	static class TimeoutRunnable implements Runnable, TimeoutManager.Timeout {
		private final Runnable inner;
		private final long timeout;
		private volatile Thread runner;

		public TimeoutRunnable(Runnable task, long timeout) {
			if (null == task)
				throw new NullPointerException();
			this.inner = task;
			this.timeout = timeout;
		}

		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			if (timeout > 0) {
				runner = Thread.currentThread();
				final TimeoutManager tm = TimeoutManager.getInstance();
				tm.schedule(this, timeout);
				try {
					inner.run();
				} finally {
					tm.remove(this);
					runner = null;
				}
			} else
				inner.run();
		}

		@Override
		public void onTimeout() {
			final Thread r = runner;
			if (r != null) {
				if (r instanceof xdb.Worker) {
					((xdb.Worker)r).angelInterrupt();
				} else
					r.interrupt();
			}
		}
	}

	static class TimeoutCallable<V> implements Callable<V>, TimeoutManager.Timeout {
		private final Callable<V> inner;
		private final long timeout;
		private volatile Thread runner;

		public TimeoutCallable(Callable<V> task, long timeout) {
			if (null == task)
				throw new NullPointerException();
			this.inner = task;
			this.timeout = timeout;
		}

		@SuppressWarnings("deprecation")
		@Override
		public V call() throws Exception {
			if (timeout > 0) {
				runner = Thread.currentThread();
				final TimeoutManager tm = TimeoutManager.getInstance();
				tm.schedule(this, timeout);
				try {
					return inner.call();
				} finally {
					tm.remove(this);
					runner = null;
				}
			} else
				return inner.call();
		}

		@Override
		public void onTimeout() {
			final Thread r = runner;
			if (r != null) {
				if (r instanceof xdb.Worker) {
					((xdb.Worker)r).angelInterrupt();
				} else
					r.interrupt();
			}
		}
	}
}

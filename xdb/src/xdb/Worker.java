package xdb;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public final class Worker extends java.lang.Thread {

	private ThreadFactory threadFactory;

	private Worker(ThreadFactory threadFactory, String executorName, Runnable r) {
		super(r);
		this.threadFactory = threadFactory;
		this.setDaemon(true);
		this.setName("xdb.Worker." + executorName + "." + this.getId());
	}

//	/**
//	 * 返回创建本线程的工厂。
//	 * @return
//	 */
//	public ThreadFactory getThreadFactory() {
//		return threadFactory;
//	}

	@Override
	public void run() {
		//Trace.debug("start " + this);

		workers.put(getId(), this);

		try {
			super.run();

		} catch (Throwable th) {
			xdb.Trace.error("worker catch Exception", th);
		} finally {
			workers.remove(getId());
		}
	}

	private static ConcurrentMap<Long, Worker> workers = xdb.util.Misc.newConcurrentMap();

	static Worker get(Long threadid) {
		return workers.get(threadid);
	}

	private AtomicBoolean angel = new AtomicBoolean(false); 

	void angelInterrupt() {
		angel.set(true);
		super.interrupt();
	}

	static boolean angelInterrupted() {
		Thread cur = Thread.currentThread();
		if (cur instanceof Worker) {
			return ((Worker)cur).angel.getAndSet(false);
		}
		return false;
	}

	private static class WorkerFactory implements ThreadFactory {
		private final String executorName;
		WorkerFactory(String executorName) {
			this.executorName = executorName;
		}
		@Override
		public Thread newThread(Runnable r) {
			return new Worker(this, executorName, r);
		}
	};

	/**
	 * 根据给定的名字创建线程工厂。这个工厂创建的线程都会以此名字为前缀命名。
	 * 一般用于 ThreadPoolExecutor。
	 * @param executorName
	 * @return
	 */
	public static ThreadFactory newFactory(String executorName) {
		return new WorkerFactory(executorName);
	}

	/**
	 * <p>防止线程饥饿。当线程向自己所在的线程池<b>提交任务并等待</b>任务结束时，有可能出现饥饿。
	 * <p>饥饿：线程池只有一个线程，它提交并等待任务结束，此时由于没有更多线程来执行池中的任务，
	 * 那么它将永远等待，这就是线程饥饿。
	 * <p>给线程池配置更多地线程可以减少饥饿的可能，但不能完全避免饥饿。
	 * <p>禁止线程向自己所在线程池提交并等待任务，可以完全避免饥饿。注意，仅提交任务，不等待是没有问题的。
	 * <p>检测请细节看实现。
	 *
	 * @param executor
	 * @see xdb.util.TimeoutExecutor
	 * @see xdb.util.ScheduledTimeoutExecutor
	 */
	public final static void debugHunger(ThreadPoolExecutor executor) {
		if (Trace.isDebugEnabled()) {
			final Thread ct = Thread.currentThread();
			if (ct instanceof Worker) {
				final Worker wk = (Worker)ct;
				if (wk.threadFactory == executor.getThreadFactory())
					xdb.Trace.warn("Say no to hunger!");
			}
		}
	}
}

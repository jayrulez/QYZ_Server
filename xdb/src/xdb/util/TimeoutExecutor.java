package xdb.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadFactory;

/**
 * 继承自 ThreadPoolExecutor，所有在这个执行器中调度任务允许设置最大运行时间。
 * 当任务的运行时间超过配置时，会尝试中断执行线程。但不能保证一定能中断成功。
 */
public class TimeoutExecutor extends ThreadPoolExecutor {
    /**
     * updated only while holding synchronized(this), but
     * volatile to allow concurrent readability even during updates.
     */
	private volatile long defaultTimeout; 

	public synchronized void setDefaultTimeout(long defaultTimeout) {
		this.defaultTimeout = defaultTimeout;
	}

	public long getDefaultTimeout() {
		return defaultTimeout;
	}

	public TimeoutExecutor(
			long defaultTimeout, int corePoolSize,
			ThreadFactory factory) {
		super(corePoolSize, corePoolSize, 0, TimeUnit.NANOSECONDS,
				new LinkedBlockingQueue<Runnable>(), factory);
		this.defaultTimeout = defaultTimeout;
	}

	/**
	 * 
	 * @param defaultTimeout 默认超时，当 <=0 时，默认不超时。
	 * @param corePoolSize
	 * @param workQueue
	 * @param factory
	 */
	public TimeoutExecutor(
			long defaultTimeout, int corePoolSize,
			BlockingQueue<Runnable> workQueue, ThreadFactory factory) {
		super(corePoolSize, corePoolSize, 0, TimeUnit.NANOSECONDS, workQueue, factory);
		this.defaultTimeout = defaultTimeout;
	}

	//////////////////////////////////////////////////////////
	// 超时版本的类 ExecutorService 扩展接口
	/**
	 * 当 timeout <= 0，不做超时检测
	 */
	public Future<?> submit(Runnable task, long timeout) {
		xdb.Worker.debugHunger(this);
		return super.submit(xdb.Angel.decorateCallable(task, timeout));
	}

	/**
	 * 当 timeout <= 0，不做超时检测
	 */
	public <T> Future<T> submit(Runnable task, T result, long timeout) {
		xdb.Worker.debugHunger(this);
		return super.submit(xdb.Angel.decorate(task, result, timeout));
	}

	/**
	 * 当 timeout <= 0，不做超时检测
	 */
	public <T> Future<T> submit(Callable<T> task, long timeout) {
		xdb.Worker.debugHunger(this);
		return super.submit(xdb.Angel.decorate(task, timeout));
	}

	/**
	 * 当 timeout <= 0，不做超时检测
	 */
	public void execute(Runnable command, long timeout) {
		super.execute(xdb.Angel.decorateRunnable(command, timeout));
	}

	//////////////////////////////////////////////////////////
	// 重载这些方法为所有的任务提供默认超时。

	@Override
	public Future<?> submit(Runnable task) {
		xdb.Worker.debugHunger(this);
		return super.submit(xdb.Angel.decorateCallable(task, defaultTimeout));
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		xdb.Worker.debugHunger(this);
		return super.submit(xdb.Angel.decorate(task, result, defaultTimeout));
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		xdb.Worker.debugHunger(this);
		return super.submit(xdb.Angel.decorate(task, defaultTimeout));
	}

	@Override
	public void execute(Runnable command) {
		super.execute(xdb.Angel.decorateRunnable(command, defaultTimeout));
	}
}

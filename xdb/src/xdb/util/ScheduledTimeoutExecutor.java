package xdb.util;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ScheduledTimeoutExecutor extends ScheduledThreadPoolExecutor {
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

	public ScheduledTimeoutExecutor(
			long defaultTimeout, int corePoolSize, ThreadFactory factory) {
		super(corePoolSize);
		super.setThreadFactory(factory);
		this.defaultTimeout = defaultTimeout;
	}

	/*
	 * 注意，下面的实现并不调用对应的super方法。因为在 ScheduledThreadPoolExecutor 的内部实现
	 * 总是调用 schedule，这里也直接就调用它。如果jdk对实现有局部优化，这种方式不会出错，但会丢失优化。
	 * 这里假定 jdk 不会这么干了。
	 */

	//////////////////////////////////////////////////////////
	// 超时版本的 executor 扩展接口。
	/**
	 * 当 timeout <= 0，不做超时检测
	 */
	public Future<?> submit(Runnable task, long timeout) {
		xdb.Worker.debugHunger(this);
		return super.schedule(xdb.Angel.decorateCallable(task, timeout), 0, TimeUnit.NANOSECONDS);
	}

	/**
	 * 当 timeout <= 0，不做超时检测
	 */
	public <T> Future<T> submit(Runnable task, T result, long timeout) {
		xdb.Worker.debugHunger(this);
		return super.schedule(xdb.Angel.decorate(task, result, timeout), 0, TimeUnit.NANOSECONDS);
	}

	/**
	 * 当 timeout <= 0，不做超时检测
	 */
	public <T> Future<T> submit(Callable<T> task, long timeout) {
		xdb.Worker.debugHunger(this);
		return super.schedule(xdb.Angel.decorate(task, timeout), 0, TimeUnit.NANOSECONDS);
	}

	/**
	 * 当 timeout <= 0，不做超时检测
	 */
	public void execute(Runnable command, long timeout) {
		super.schedule(xdb.Angel.decorateCallable(command, timeout), 0, TimeUnit.NANOSECONDS);
	}

	/**
	 * 当 timeout <= 0，不做超时检测
	 */
	public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit, long timeout) {
		return super.schedule(xdb.Angel.decorate(callable, timeout), delay, unit);
	}

	/**
	 * 当 timeout <= 0，不做超时检测
	 */
	public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit, long timeout) {
		return super.schedule(xdb.Angel.decorateCallable(command, timeout), delay, unit);
	}

	/**
	 * 当 timeout <= 0，不做超时检测
	 */
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit, long timeout) {
		return super.scheduleAtFixedRate(xdb.Angel.decorateRunnable(command, timeout), initialDelay, period, unit);
	}

	/**
	 * 当 timeout <= 0，不做超时检测
	 */
	public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit, long timeout) {
		return super.scheduleWithFixedDelay(xdb.Angel.decorateRunnable(command, timeout), initialDelay, delay, unit);
	}

	//////////////////////////////////////////////////////////
	// 重载这些方法为所有的任务提供默认超时。

	@Override
	public Future<?> submit(Runnable task) {
		xdb.Worker.debugHunger(this);
		return super.schedule(xdb.Angel.decorateCallable(task, defaultTimeout), 0, TimeUnit.NANOSECONDS);
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		xdb.Worker.debugHunger(this);
		return super.schedule(xdb.Angel.decorate(task, result, defaultTimeout), 0, TimeUnit.NANOSECONDS);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		xdb.Worker.debugHunger(this);
		return super.schedule(xdb.Angel.decorate(task, defaultTimeout), 0, TimeUnit.NANOSECONDS);
	}

	@Override
	public void execute(Runnable command) {
		super.schedule(xdb.Angel.decorateCallable(command, defaultTimeout), 0, TimeUnit.NANOSECONDS);
	}

	@Override
	public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
		return super.schedule(xdb.Angel.decorate(callable, defaultTimeout), delay, unit);
	}

	@Override
	public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
		return super.schedule(xdb.Angel.decorateCallable(command, defaultTimeout), delay, unit);
	}

	@Override
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
		return super.scheduleAtFixedRate(xdb.Angel.decorateRunnable(command, defaultTimeout), initialDelay, period, unit);
	}

	@Override
	public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
		return super.scheduleWithFixedDelay(xdb.Angel.decorateRunnable(command, defaultTimeout), initialDelay, delay, unit);
	}
}

package xdb;

import java.util.concurrent.*;
import java.util.List;

import xdb.util.TimeoutExecutor;
import xdb.util.ScheduledTimeoutExecutor;

/**
 * 
 * 线程池。包含三个池子。
 *
 * <p> ScheduledThreadPoolExecutor scheduled，用来调度需要延迟或者定时执行的任务。
 * <p> ThreadPoolExecutor procedure，用来调度立即执行存储过程任务。XDB内部创建的任务使用这个池子。
 * <p> ThreadPoolExecutor 默认的，用来调度应用层的立即执行任务，一般来说用来执行协议，直接继承。
 * 
 * <p> 为了兼容，Executor 实现了接口 ScheduledExecutorService。对于需要 schedule 支持的任务，
 *     内部会把这些任务分派到 scheduled 里执行。
 *
 * @see java.util.concurrent.ScheduledThreadPoolExecutor
 * @see java.util.concurrent.ThreadPoolExecutor
 */
public final class Executor extends TimeoutExecutor
		implements ScheduledExecutorService, ExecutorMBean {

	private final ScheduledTimeoutExecutor scheduled;
	private final TimeoutExecutor          procedure;

	private xdb.util.MBeans.Manager mbeans = new xdb.util.MBeans.Manager();

	private static Object lock = new Object();
	private static Executor instance;

	public static void start(int defaultTimeout,
			int executorCorePoolSize, int procedureCorePoolSize, int scheduleCorePoolSize,
			int timeoutPeriod) {

		synchronized (lock) {
			if (null == instance) {
				instance = new Executor(defaultTimeout,
					executorCorePoolSize, procedureCorePoolSize, scheduleCorePoolSize,
					timeoutPeriod);
			}
		}
	}

	/**
	 * <p>对于 xdb 应用，不需要主动调用，Xdb.stop 会调用这个方法。
	 * <p>对于 xio only 的应用，需要主动调用这个方法。
	 */
	public static void stop() {
		synchronized (lock) {
			if (null != instance) {
				instance.shutdown_protected = false; // allow shutdown now
				instance.shutdown();
				instance = null;
			}
		}
	}

	public static Executor getInstance() {
		return instance;
	}

	@SuppressWarnings("deprecation")
	private Executor(int defaultTimeout,
			int executorCorePoolSize, int procedureCorePoolSize, int scheduleCorePoolSize,
			int timeoutPeriod) {

		super(defaultTimeout, executorCorePoolSize, Worker.newFactory(""));

		this.procedure = new TimeoutExecutor(
				defaultTimeout, procedureCorePoolSize, Worker.newFactory("procedure"));

		this.scheduled = new ScheduledTimeoutExecutor(
				defaultTimeout, scheduleCorePoolSize, Worker.newFactory("scheduled"));

		// 注意，timeout 当作普通定时任务也放到 scheduled 中运行。
		this.scheduled.scheduleWithFixedDelay(
				xdb.util.TimeoutManager.getInstance(),
				timeoutPeriod, timeoutPeriod, TimeUnit.MILLISECONDS);

		mbeans.register(this, "xdb:type=Xdb,name=Executor");
	}

	/**
	 * 返回支持延迟，支持超时的执行器。
	 * Executor 虽然会根据 schedule 方法的参数，决定是否放到这个池子中。
	 * 通过 Executor 无法访问 ScheduledTimeoutExecutor 所有方法，需要时使用这个方法直接拿到引用。
	 * @return
	 */
	public ScheduledTimeoutExecutor getScheduledTimeoutExecutor() {
		return scheduled;
	}

	/**
	 * 返回存储过程专用执行器。xdb 本身使用这个方法执行存储过程。
	 * 如，当使用 xdb.Procedure的execute,submit进入事务时，使用这个执行器。
	 * 对于那些直接调用 xdb.Procedure.call 进入事务的存储过程调用，将在调用者线程中执行。
	 * @return
	 */
	public TimeoutExecutor getProcedureTimeoutExecutor() {
		return procedure;
	}

	/**
	 * 配置线程池的数量。<B>由于max一开始就被设置成初始大小，所以只能往小的地方改。</B>
	 * @param executorCorePoolSize
	 * @param procedureCorePoolSize
	 * @param scheduleCorePoolSize
	 */
	public void setCorePoolSize(
			int executorCorePoolSize, int procedureCorePoolSize, int scheduleCorePoolSize) {
		super.setCorePoolSize(executorCorePoolSize);
		this.procedure.setCorePoolSize(procedureCorePoolSize);
		this.scheduled.setCorePoolSize(scheduleCorePoolSize);
	}

	/**
	 * 伪装 schedule 的结果。实际上此任务被放到 ThreadPoolExecutor 中执行。
	 */
	private static class ScheduledFutureWrapper<V> implements ScheduledFuture<V> {
		private final Future<V> future;
		ScheduledFutureWrapper(Future<V> future) {
			this.future = future;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return 0;
		}
		@Override
		public int compareTo(Delayed o) {
			long d = o.getDelay(TimeUnit.NANOSECONDS);
			return (d == 0)? 0 : ((d > 0)? -1 : 1);
		}
		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			return future.cancel(mayInterruptIfRunning);
		}
		@Override
		public V get() throws InterruptedException, ExecutionException {
			return future.get();
		}
		@Override
		public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
			return future.get(timeout, unit);
		}
		@Override
		public boolean isCancelled() {
			return future.isCancelled();
		}
		@Override
		public boolean isDone() {
			return future.isDone();
		}
		
	}

	@Override
	public boolean remove(Runnable task) {
		if (task instanceof ScheduledFuture<?>)
			return super.remove(task);
		return this.scheduled.remove(task);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
		if (delay <= 0)
			return new ScheduledFutureWrapper(super.submit(command));
		return this.scheduled.schedule(command, delay, unit);
	}

	@Override
	public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
		if (delay <= 0)
			return new ScheduledFutureWrapper<V>(super.submit(callable));
		return this.scheduled.schedule(callable, delay, unit);
	}

	@Override
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
		return this.scheduled.scheduleAtFixedRate(command, initialDelay, period, unit);
	}

	@Override
	public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
		return this.scheduled.scheduleWithFixedDelay(command, initialDelay, delay, unit);
	}

	////////////////////////////////////////////////////////////////////
	// shutdown process
	// package private.
	private boolean shutdown_protected = true; // 只有  Executor.stop() 会设置这个。保护 Executor，防止误操作。

	@SuppressWarnings("deprecation")
	@Override
	public void shutdown() {
		if (shutdown_protected)
			throw new IllegalStateException("shutdown protected");

		// 使用快速退出。中断正在执行的任务。取消队列中的任务。
		super.shutdownNow();
		this.scheduled.shutdownNow();
		this.procedure.shutdownNow();

		xdb.util.TimeoutManager.getInstance().clear();
		await(this, "");
		await(this.scheduled, "scheduled");
		await(this.procedure, "procedure");

		mbeans.unregisterAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Runnable> shutdownNow() {
		if (shutdown_protected)
			throw new IllegalStateException("shutdown protected");

		List<Runnable> r = super.shutdownNow();
		r.addAll(this.scheduled.shutdownNow());
		r.addAll(this.procedure.shutdownNow());

		xdb.util.TimeoutManager.getInstance().clear();
		await(this, "");
		await(this.scheduled, "scheduled");
		await(this.procedure, "procedure");

		mbeans.unregisterAll();

		return r;
	}

	private void await(ThreadPoolExecutor tpe, final String name) {
		for (;;) {
			try {
				if (tpe.awaitTermination(5, TimeUnit.SECONDS))
					return;
				Trace.warn("Executor." + name + ".shutdown timeout. queue.size=" + tpe.getQueue().size());
			} catch (InterruptedException e) {
				Trace.warn("Executor." + name + ".shutdown inprocess. skip InterruptedException");
			}
		}
	}

	// MBean Executor
	@Override
	public int getExecutorActiveCount() {
		return super.getActiveCount();
	}

	@Override
	public long getExecutorCompletedTaskCount() {
		return super.getCompletedTaskCount();
	}

	@Override
	public int getExecutorPoolSize() {
		return super.getPoolSize();
	}

	@Override
	public String getExecutorState() {
		if (super.isShutdown())
			return super.isTerminated() ? "TERMINATED" : "TERMINATING";
		return "RUNNING";
	}

	@Override
	public long getExecutorTaskCount() {
		return super.getTaskCount();
	}

	// MBean Procedure
	@Override
	public int getProcedureActiveCount() {
		return this.procedure.getActiveCount();
	}

	@Override
	public long getProcedureCompletedTaskCount() {
		return this.procedure.getCompletedTaskCount();
	}

	@Override
	public int getProcedurePoolSize() {
		return this.procedure.getPoolSize();
	}

	@Override
	public String getProcedureState() {
		if (this.procedure.isShutdown())
			return this.procedure.isTerminated() ? "TERMINATED" : "TERMINATING";
		return "RUNNING";
	}

	@Override
	public long getProcedureTaskCount() {
		return this.procedure.getTaskCount();
	}

	// MBean Scheduled
	@Override
	public int getScheduledActiveCount() {
		return this.scheduled.getActiveCount();
	}

	@Override
	public long getScheduledCompletedTaskCount() {
		return this.scheduled.getCompletedTaskCount();
	}

	@Override
	public int getScheduledPoolSize() {
		return this.scheduled.getPoolSize();
	}

	@Override
	public String getScheduledState() {
		if (this.scheduled.isShutdown())
			return this.scheduled.isTerminated() ? "TERMINATED" : "TERMINATING";
		return "RUNNING";
	}

	@Override
	public long getScheduledTaskCount() {
		return this.scheduled.getTaskCount();
	}

	// MBean purge
	@Override
	public void purgeExecutor(String iamsure) {
		if (iamsure.equals("iamsure"))
			super.purge();
	}

	@Override
	public void purgeScheduled(String iamsure) {
		if (iamsure.equals("iamsure"))
			this.scheduled.purge();
	}

	@Override
	public void purgeProcedure(String iamsure) {
		if (iamsure.equals("iamsure"))
			this.procedure.purge();
	}

	// MBean corePoolSize
	@Override
	public void setExecutorCorePoolSize(int corePoolSize) {
		super.setCorePoolSize(corePoolSize);
	}

	@Override
	public void setScheduledCorePoolSize(int corePoolSize) {
		this.scheduled.setCorePoolSize(corePoolSize);
	}

	@Override
	public int getProcedureCorePoolSize() {
		return this.procedure.getCorePoolSize();
	}

	@Override
	public void setProcedureCorePoolSize(int corePoolSize) {
		this.procedure.setCorePoolSize(corePoolSize);
	}

	@Override
	public int getExecutorCorePoolSize() {
		return super.getCorePoolSize();
	}

	@Override
	public int getScheduledCorePoolSize() {
		return this.scheduled.getCorePoolSize();
	}

	@Override
	public void testAlive(long timeout)
		throws InterruptedException, ExecutionException, TimeoutException {
		if (timeout < 500)
			timeout = 500;

		Runnable alive = new Runnable() { public void run() { /* do nothing */ } };

		// 增加 Executor 需要修改这里。
		super.submit(alive).get(timeout, TimeUnit.MILLISECONDS);
		this.scheduled.submit(alive).get(timeout, TimeUnit.MILLISECONDS);
		this.procedure.submit(alive).get(timeout, TimeUnit.MILLISECONDS);
	}

	@Override
	public long getExecutorDefaultTimeout() {
		return super.getDefaultTimeout();
	}

	@Override
	public long getScheduledDefaultTimeout() {
		return this.scheduled.getDefaultTimeout();
	}

	@Override
	public void setExecutorDefaultTimeout(long timeout) {
		super.setDefaultTimeout(timeout);
	}

	@Override
	public void setScheduledDefaultTimeout(long timeout) {
		this.scheduled.setDefaultTimeout(timeout);
	}

	@Override
	public long getProcedureDefaultTimeout() {
		return this.procedure.getDefaultTimeout();
	}

	@Override
	public void setProcedureDefaultTimeout(long timeout) {
		this.procedure.setDefaultTimeout(timeout);
	}
}

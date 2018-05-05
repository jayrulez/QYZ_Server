package xdb;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.Executors;

/**
 * 
 * 创建事务环境，调度和执行存储过程。
 * 在发生XLockDead错误时，随即延迟一定时间再次执行存储过程。
 *
 */
class ProcedureFuture<P extends Procedure> implements RunnableFuture<P> {
	/**
	 * 只有创建线程和当前工作线程会修改，不会并发修改。可能有多个读取线程。
	 */
	private volatile Future<P> future;
	private final P p;
	private final Procedure.Done<P> done;
	/**
	 * 重新执行事务时，工作线程会切换，但任何时候只会存在一个访问者。
	 */
	private int ranTimes = 0;

	public ProcedureFuture(P p) {
		this.p = p;
		future = Xdb.executor().getProcedureTimeoutExecutor().submit(this, p, p.getConf().getMaxExecutionTime());
		this.done = null;
	}

	public ProcedureFuture(P p, Procedure.Done<P> done) {
		this.p = p;
		this.done = done;
		future = Xdb.executor().getProcedureTimeoutExecutor().submit(this, p, p.getConf().getMaxExecutionTime());
	}

	private void done() {
		p.fetchTasks();
		// 存储过程内部提交的任务派发到协议线程池中执行。
		Xdb.executor().execute(new Runnable() {
			@Override
			public void run() {
				p.runLastTasks();
			}
		});

		if (null != done) {
			try {
				done.doDone(p);
			} catch (Throwable e) {
				// 此时过程已经结束，不把错误报告给等待future的线程，仅记录错误日志。
				// 虽然异步调用(execute)和同步调用(submit)，一般来说不会同时使用。
				// 当同时使用时，doDone的错误只在日志中记录，不影响等待future的线程。
				// see Procedure.execute(p, done);
				Trace.error("doDone", e);
			}
		}
	}

	/**
	 * 执行存储过程。创建销毁事务，死锁后重新执行。
	 * 开始设计时，这个类是唯一的事务调度接口。只有通过继承 Procedure，并且通过这里才能使用事务。
	 * 为了方便存储过程的调用，现在 Procedure.call 中也能根据需要创建事务，但不具备这里的死锁重做的能力。
	 * see Procedure.call
	 * <b>警告，这个方法不安全(线程不安全，定义也不安全)，不能在随意调用。</b>
	 * 原来这个方法被嵌套在内部Runnable实现中，完全被起来了。
	 * 改为现在的实现方式是避免new一个新的对象，节约内存，提高性能。
	 * 但外部拿到Future以后可以通过强制转换并访问到这个方法。
	 */
	@Override
	public void run() {
		++ ranTimes;

		try {
			// 创建事务和执行存储过程。
			try {
				Transaction.create().perform(p);
			} finally {
				Transaction.destroy(); // safe if create fail
			}
			done();

		} catch (XLockDead e) {
			if (ranTimes >= p.getConf().getRetryTimes()) {
				done();
				throw new XAngelError(); // 达到最大重复次数.报告最终错误.
			}
			int delay = Xdb.random().nextInt(p.getConf().getRetryDelay());
			future = Xdb.executor().getScheduledTimeoutExecutor().schedule(
					Executors.callable(this, p), delay, TimeUnit.MILLISECONDS,
					p.getConf().getMaxExecutionTime());
			throw e; // 报告死锁错误，future打断当前的监视对象，重新监视。

		} catch (Error error) {
			done();
			throw error;

		} catch (Throwable e) {
			done();
			throw new XError(e); // 有其他方法,不需要包装一下,直接扔出去吗?
		}
	}

	/////////////////////////////////////////////////////////////////////
	// Future implement
	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return future.cancel(mayInterruptIfRunning);
	}

	@Override
	public P get() throws InterruptedException, ExecutionException {
		for (;;) {
			try {
				future.get();
				return p;
			} catch (ExecutionException e) {
				if (!(e.getCause() instanceof XLockDead))
					throw e;
				//Trace.debug("skip " + e);
			}
		}
	}

	@Override
	public P get(long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		for (;;) {
			try {
				future.get(timeout, unit);
				return p;
			} catch (ExecutionException e) {
				if (!(e.getCause() instanceof XLockDead))
					throw e;
				//Trace.debug("skip " + e);
			}
		}
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

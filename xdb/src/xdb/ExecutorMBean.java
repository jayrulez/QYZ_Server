package xdb;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface ExecutorMBean {
	public long getExecutorTaskCount();
	public long getExecutorCompletedTaskCount();
	public int  getExecutorActiveCount();
	public int  getExecutorPoolSize();
	public String getExecutorState();

	public long getProcedureTaskCount();
	public long getProcedureCompletedTaskCount();
	public int  getProcedureActiveCount();
	public int  getProcedurePoolSize();
	public String getProcedureState();

	public long getScheduledTaskCount();
	public long getScheduledCompletedTaskCount();
	public int  getScheduledActiveCount();
	public int  getScheduledPoolSize();
	public String getScheduledState();

	/**
	 * Tries to remove from the work queue all Future tasks that have been cancelled.
	 * see java.util.concurrent.ThreadPoolExecutor.purge
	 * @param iamsure 必须输入"iamsure"，防止误操作。
	 */
	public void purgeExecutor(String iamsure);

	/**
	 * Tries to remove from the work queue all Future tasks that have been cancelled.
	 * see java.util.concurrent.ThreadPoolExecutor.purge
	 * @param iamsure 必须输入"iamsure"，防止误操作。
	 */
	public void purgeScheduled(String iamsure);
	public void purgeProcedure(String iamsure);

	public int getExecutorCorePoolSize();
	public void setExecutorCorePoolSize(int corePoolSize);

	public int getProcedureCorePoolSize();
	public void setProcedureCorePoolSize(int corePoolSize);

	public int getScheduledCorePoolSize();
	public void setScheduledCorePoolSize(int corePoolSize);

	public long getExecutorDefaultTimeout();
	public void setExecutorDefaultTimeout(long timeout);

	public long getScheduledDefaultTimeout();
	public void setScheduledDefaultTimeout(long timeout);

	public long getProcedureDefaultTimeout();
	public void setProcedureDefaultTimeout(long timeout);

	/**
	 * Executor 是否活着。正常返回表示或者。异常表示已经死亡或者执行错误。
	 * @param timeout 设置检测超时时间，单位毫秒。小于500毫秒，将按500毫秒进行检查。实际检查时间可能超过这个值。
	 * @throws CancellationException if the computation was cancelled
	 * @throws ExecutionException if the computation threw an exception
	 * @throws InterruptedException if the current thread was interrupted while waiting
	 * @throws TimeoutException if the wait timed out
	 */
	public void testAlive(long timeout)
		throws InterruptedException, ExecutionException, TimeoutException;
}

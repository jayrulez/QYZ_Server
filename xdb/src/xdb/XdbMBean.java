package xdb;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

/**
 * Xdb的管理接口。
 * 根据需要，逐步增加。
 */
public interface XdbMBean {

	/**
	 * 停止服务器，调用以后，整个程序会被停止，这个函数不返回。
	 * @param iamsure 必须输入"iamsure"，防止误操作。
	 */
	public void shutdown(String iamsure);

	public int getAngleInterruptCount();

	public long getTransactionCount();
	public long getTransactionFalse();
	public long getTransactionException();

	/**
	 * 程序是否活着。正常返回表示活着。抛出异常表示已经死亡或者执行错误。
	 * @see ExecutorMBean.testAlive
	 * @param timeout 设置检测超时时间，单位毫秒。小于500毫秒，将按500毫秒进行检查。实际检查时间可能超过这个值。
	 * @throws CancellationException if the computation was cancelled
	 * @throws ExecutionException if the computation threw an exception
	 * @throws InterruptedException if the current thread was interrupted while waiting
	 * @throws TimeoutException if the wait timed out
	 */
	public void testAlive(long timeout)
		throws InterruptedException, ExecutionException, TimeoutException;

	/**
	 * 使用 “,” 分隔多个名字空间。按声明的顺序使用名字空间。如，"xdb.;xio;"
	 * @param nsClass
	 * @param nsLock
	 */
	public Map<String, AtomicInteger> top(String nsClass, String nsLock);
}

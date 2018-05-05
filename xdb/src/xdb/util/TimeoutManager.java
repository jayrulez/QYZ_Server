package xdb.util;

import java.util.concurrent.ConcurrentMap;
import java.util.Map.Entry;

/**
 * 低精度超时定时器实现。当延迟到达时执行任务。特性：
 * <ul>
 * <li><b>专用的定时器实现，not well-defined.</b>
 * <li><b>不适合用来调度大量同时存在的超时任务。现在仅用于实现 xdb.Executor 的超时保护。</b>
 * <li>适用于超时任务实际上很少发生的情形。
 * <li>低精度。秒级。
 * <li>采用ConcurrentMap实现，提高并发性。
 * <li>积极回收内存（尽快释放对象引用），remove 即 cancel。
 * <li>超时发生时，直接在调度线程中执行。所以目标任务必须是很快完成的。
 * <li>调度目标Runnable最好不重载实现Object的hashCode和equals，否则可能对效率造成较大影响。
 * <li>超时任务不使用接口Runnable，而是定义新的接口Timeout，这样便于在同一个类中实现。
 * </ul>
 */
public final class TimeoutManager implements Runnable {
	private static TimeoutManager instance = new TimeoutManager();

	/**
	 * @deprecated 这里使用单件模式。不利于启动停止的管理。不过使用方便。先如此吧。
	 * @return
	 */
	public static TimeoutManager getInstance() {
		return instance;
	}

	/////////////////////////////////////////////////////////////////
	private ConcurrentMap<Timeout, Long> targets = xdb.util.Misc.newConcurrentMap();

	public static interface Timeout {
		/**
		 * 超时发生时回调这个函数。仅限于实现一些简单快捷的操作。
		 */
		public void onTimeout();
	}

	public TimeoutManager() {
	}

	/**
	 * 调度超时任务。
	 * @param target 执行目标。
	 * @param timeout 单位为毫秒。虽然实际精度是秒级。
	 * @return 返回 target。
	 */
	public Timeout schedule(Timeout target, long timeout) {
		if (target == null)
			throw new NullPointerException();

		if (timeout < 0)
			timeout = 0;

		targets.put(target, System.currentTimeMillis() + timeout);
		return target;
	}

	/**
	 * 删除和取消超时任务。如果任务
	 * @param target
	 * @return
	 */
	public boolean remove(Timeout target) {
		return null != targets.remove(target);
	}

	/**
	 * @deprecated 本方法是线程不安全的，只能由一个线程中调用。
	 */
	public void run() {
		long now = System.currentTimeMillis();
		for (Entry<Timeout, Long> e : targets.entrySet()) {
			final Timeout target = e.getKey();
			if (e.getValue() <= now && remove(target)) {
				try {
					target.onTimeout();
				} catch (Throwable x) {
					xdb.Trace.error("Timeout.target.run", x);
				}
			}
		}
	}

	/**
	 * @return the number of in this timeout.
	 */
	public int size() {
		return targets.size();
	}

	public void clear() {
		targets.clear();
	}
}

package xdb;

public class ThreadHelper extends Thread {
	private volatile boolean running = true;
	private boolean idle = true;

	public ThreadHelper(String name) {
		super(name);
		this.setDaemon(true);
	}

	public ThreadHelper(String name, boolean daemon) {
		super(name);
		this.setDaemon(daemon);
	}

	public final boolean isRunning() {
		return running;
	}

	/**
	 * 忽略中断异常，必须等待线程结束。
	 */
	public final void joinAssuring() {
		while (true) {
			try {
				join();
				break;
			} catch ( Throwable ex ) {
				Trace.warn(this.getClass().getName() + "shutdown. ignore ex=" + ex);
			}
		}
	}

	public void shutdown() {
		running = false;
		wakeup();
		joinAssuring();
	}

	public synchronized void wakeup() {
		idle = false;
		this.notify();
	}

	/**
	 * 
	 * 挂起当前线程一段时间。
	 * 
	 * 通过 shutdown 或者 wakeup 打断。
	 * 
	 * @param ms
	 */
	public final synchronized void sleepIdle(long ms) {
		try {
			if (idle)
				this.wait(ms);
		} catch (InterruptedException ex) {
			Trace.warn(this.getClass().getName() + "sleepOut. ex=" + ex);
		} finally {
			idle = true;
		}
	}

	Runnable cock() {
		return new Runnable() {
			@Override
			public void run() {
				ThreadHelper.this.wakeup();
			}
		};
	}
}

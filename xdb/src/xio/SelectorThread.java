package xio;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * Selector 事件管理和派发。
 *
 */
public final class SelectorThread extends Thread {
	private volatile boolean running = true;
	private final Selector selector;
	private final Lock registerLock = new ReentrantLock();

	//////////////////////////////////////////////////////////////
	// 由引擎线程执行的任务。
	private List<Runnable> tasks = new ArrayList<Runnable>();
	private final Lock tasksLock = new ReentrantLock();

	// 创建Xio；把 SocketChannel注册到Selector中；把Xio加入到Manager中。
	final class RegisterSocketChannel implements Runnable {
		private Creator creator;
		private Manager manager;
		private SocketChannel sc;

		RegisterSocketChannel(Creator creator, Manager manager, SocketChannel sc) {
			this.creator = creator;
			this.manager = manager;
			this.sc = sc;
		}

		@Override
		public void run() {
			manager.addXio(creator.newXio(SelectorThread.this, sc));
		}
	}

	/**
	 * 提交  SocketChannel 注册任务。
	 * @param creator
	 * @param manager
	 * @param sc
	 */
	void execute(Creator creator, Manager manager, SocketChannel sc) {
		execute(new RegisterSocketChannel(creator, manager, sc));
	}

	/**
	 * package private
	 * @param task
	 */
	void execute(Runnable task) {
		tasksLock.lock();
		try {
			tasks.add(task);
			selector.wakeup();
		} finally {
			tasksLock.unlock();
		}
	}

	private void executeNow() {
		List<Runnable> rt = null;
		tasksLock.lock();
		try {
			if (0 == tasks.size())
				return; // 没有任务。
			rt = tasks;
			tasks = new ArrayList<Runnable>();
		} finally {
			tasksLock.unlock();
		}
		for (Runnable task : rt) {
			try {
				task.run();
			} catch (Throwable e) {
				xdb.Trace.error("xio.SelectorThread.executeNow : " + task.getClass().getName(), e);
			}
		}
	}

	public SelectorThread(String threadName) {
		super(threadName);
		this.setDaemon(true);

		try {
			selector = Selector.open();
		} catch (java.io.IOException e) {
			throw new IOError(e);
		}
	}

	public SelectionKey register(SelectableChannel sc, int ops, Handle handle) {
		try {
			sc.configureBlocking(false);
			registerLock.lock();
			try {
				// 当引擎线程执行register时，wakeup会导致一次多余唤醒。
				// 这在连接建立不是很繁忙的应用中问题不大。
				// 下面通过判断是否本线程来决定是否调用wakeup。
				if (Thread.currentThread() != this)
					this.selector.wakeup(); // 不会丢失。
				return sc.register(this.selector, ops, handle);
			} finally {
				registerLock.unlock();
			}
		} catch (java.io.IOException e) {
			throw new IOError(e);
		}
	}

	public void close() {
		this.running = false;
		this.selector.wakeup();

		// join
		while (true) {
			try {
				this.join();
				break;
			} catch ( Throwable ex ) {
				xdb.Trace.warn(this.getClass().getName() + "close. ignore ex=" + ex);
			}
		}

		try {
			selector.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (running) {
			try {
				executeNow();

				// 如果注册正在进行中，不要进入 select()。在这里等待完成。 
				registerLock.lock();
				try { /* nothing */ } finally { registerLock.unlock(); }
				// 如果在这个时间窗口  wakeup，下面的 select 会马上返回。wakeup 不会丢失。
				if (selector.select() <= 0)
					continue; // 没有事件，可能是 wakeup 或者 shutdown。

				java.util.Set<SelectionKey> selected = selector.selectedKeys();
				for (SelectionKey key : selected) {
					if (!key.isValid())
						continue; // key maybe cancel in loop
					Handle handle = null;
					try {
						handle = (Handle)key.attachment();
						handle.doHandle(key);
					} catch (Throwable e) {
						try {
							key.channel().close();
						} catch (Throwable e2) {
							/* skip */
						}
						try {
							if (null != handle)
								handle.doException(key, e);
						} catch (Throwable e3) {
							// 注意，这里打印 doHandle 异常调用栈，而不是 doException 的异常调用栈。
							xdb.Trace.error("doException " + e3, e);
						}
					}
				}
				selected.clear();

			} catch (Throwable e) {
				xdb.Trace.error("xio.Engine.run", e);
			}
		}
	}
}

package xdb.util;

import java.util.NoSuchElementException;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * <b>任务线程调度需求： 把任务分类，不同类别的任务可以并发执行；同一类别的任务不并发执行，顺序执行。</b>
 * <p>本实现完成分类以及任务派发，不管理自己的线程，依赖外部的执行器执行任务。
 * 
 * <p><b>需求来源</b>
 * <p>
 * 在开发网络应用时，经常会听到“协议顺序的问题”，这个问题的简单描述就是需要保证协议的执行顺序。
 * 比如服务器先后收到两个协议 Open，Close，在并发的执行时，可能 Close 先被执行并完成，此时
 * 最后的状态是打开的。对于客户端发送者来说，得到的最终结果就不是他所期望的。即使 Close 执行时
 * 需要作必要的判断，会发现本来就是关闭的，并报告了错误，此时客户端能得到错误提示，但已经于事无补了。
 * <p>
 * 协议顺序问题的本质是协议设计的不好，正确的解决方法在协议层解决，如增加协议，OpenResult, CloseResult。
 * 客户端总是都在得到请求结果以后才发后续的请求。很显然，这样自然就没有协议顺序问题了，这是正确的解决方法。
 * 但是正确的解决方法有时会很麻烦，在某些特殊交互下（如，时间性要求比较高）特殊情况下，很难采用增加协议结果来解决。
 * 最后就得到了以上的需求：那就保证按收到的顺序执行协议吧。
 * <p>
 * <b>警告：尽量在设计协议时，避免出现协议顺序问题，不要过于依赖这个执行器来保证顺序。</b>
 * 这个执行器是为了简化某些实在很难解决“协议顺序问题”的协议复杂度，降低由于协议设计不够好而带来的风险。
 * 它是作为一种保护机制，不是合理的功能设计。依赖这个执行器不是一个好习惯。
 * 
 * <p><b>实现思路</b>
 * 
 * <p>每个分类使用一个队列（Serial），每个队列最多只派发一个任务除去运行，当任务执行完成时，派发下一个任务。
 * 虽然同时存在分类是有限的，但分类的总数量不可预知的，这就需要回收空的分类队列，否则最终可能会耗尽内存。
 * 按这种方式，写了一个实现(SVN-revision 1619)。svn1619 实现使用一把大锁来保护所有队列以及队列的回收，
 * 基本可工作，还有些并发 BUG 没有处理，而且在锁的问题上有优化空间。
 * 
 * <p>SVN-1619实现的最大的问题不是锁冲突，由于运行环境中，等待运行的任务数量不会太多，对于每个分类队列，
 * 基本上都只使用一次就被回收了，增加不必要的内存垃圾。采取和并分类的方法，把多个分类按固定的规则映射到到
 * 同一个队列（Serial）中，每个队列仍然最多只派发一个任务除去运行。显然，这仍然满足同一分类不会并发执行，
 * 但对于被映射盗同一个队列的不同 key 也不会并发了。采用映射方式以后，就可以把队列的总数量固定下来，
 * 不再需要回收队列，只要总的队列数量有足够的并发能力，对于应用来说影响不大。<code>假设队列队列使用
 * 数组保存，总队列数量为 length，映射得到的队列下标为： key.hashCode() % length。</code> 
 * 这种方式需要解决以下的问题：
 * <ol>
 * <li>映射均匀性。如果所有的 key 都被映射到同一个队列，最终所有的任务都被顺序执行，
 *     显然这种并发是不能接受的。为了防止 key.hashCode 不够好，采取再次 hash 的办法。
 *     由于是 hash 的方法，不一定能得到特定环境下最优的结果，但基本上可用。
 *     see SerialKeyExecutor.hash
 * 
 * <li>总队列数量的配置。这个比较简单，一般要比 service 的线程数量大，默认为 1024，够大了。
 *     只能在初始化时配置，运行时不能修改。
 * </ol>
 * 
 * <p><b>依赖性。</b>外部 ExecutorService.shutdownNow 会取消还没有开始的任务。
 *    SerialKeyExecutor 在这种情况下，无法继续正常工作。这是合理的。SerialKeyExecutor
 *    本身并不提供shutdown方法，完全依赖于外部 service。
 * 
 * @see xdb.util.ProcedureOneByOne
 */
public final class SerialKeyExecutor<K> implements Executor {
	private final ExecutorService service;
	private final Serial[] serials;

	/**
	 * 构造和初始化系列化执行器。
	 * 使用 Xdb 的 ProcedureExecutor 当作依赖的执行器.
	 * 并发系列设置为1024。
	 * @throws NullPointerException if Xdb is not start.
	 */
	public SerialKeyExecutor() {
		this(xdb.Xdb.executor());
	}

	/**
	 * 构造和初始化系列化执行器。
	 * 并发系列设置为1024。
	 * @param service 依赖的外部执行器。
	 */
	public SerialKeyExecutor(ExecutorService service) {
		this(service, 1024);
	}

	/**
	 * 构造和初始化系列化执行器。
	 * @param service 依赖的外部执行器。
	 * @param concurrencyLevel 并发系列。
	 */
	@SuppressWarnings("unchecked")
	public SerialKeyExecutor(ExecutorService service, int concurrencyLevel) {
		if (concurrencyLevel < 0 || concurrencyLevel > 0x40000000)
			throw new IllegalArgumentException("Illegal concurrencyLevel: " + concurrencyLevel);
		if (null == service)
			throw new NullPointerException();
		this.service = service;
		int capacity = 1;
		while (capacity < concurrencyLevel)
			capacity <<= 1;
		this.serials = new SerialKeyExecutor.Serial[capacity];
		for (int i = 0; i < this.serials.length; ++i)
			this.serials[i] = new Serial();
	}

	/**
	 * @return 依赖的外部执行器。
	 */
	public ExecutorService getExecutorService() {
		return service;
	}

	////////////////////////////////////////////////
	// 添加分类任务的方法。非标准接口，必须显式调用。

	/**
	 * 添加任务到队列中。根据参数key进行分类。
	 * @param key
	 * @param task
	 */
	public void execute(K key, Runnable task) {
		submit(key, task);
	}

	/**
	 * 添加任务到队列中。根据参数key进行分类。
	 * @param key
	 * @param task
	 * @return
	 */
	public Future<?> submit(K key, Runnable task) {
		return submit(key, Executors.callable(task));
	}

	/**
	 * 添加任务到队列中。根据参数key进行分类。
	 * 
	 * @param key
	 * @param task
	 * @return
	 */
	public <T> Future<T> submit(K key, Runnable task, T result) {
		return submit(key, Executors.callable(task, result));
	}

	/**
	 * 添加任务到队列中。根据参数key进行分类。
	 * 
	 * @param <T>
	 * @param key
	 * @param callable
	 * @return
	 */
	public <T> Future<T> submit(K key, Callable<T> callable) {
		if (service.isShutdown())
			throw new RejectedExecutionException();
		return serialFor(key).submit(key, callable);
	}

	/**
	 * 立即执行任务。
	 */
	@Override
	public void execute(Runnable task) {
		service.execute(task);
	}

	/**
	 * 取消指定 key 的所有任务。会尝试取消正在执行的任务，但不删除它；未开始执行的任务全部取消并删除。
	 * <b>需要遍历实现，效率不是很高。</b>
	 * @param key
	 * @return canceled number in this call.
	 */
	public int cancel(K key) {
		return serialFor(key).cancel(key);
	}

    /**
     * Because the states of tasks and threads
     * may change dynamically during computation, the returned value
     * is only an approximation, but one that does not ever decrease
     * across successive calls.
     *
     * @return the number of tasks
     */
	public int size() {
		int n = 0;
		for (Serial sq : serials)
			n += sq.size();
		return n;
	}

	/**
	 * cancel all
	 * @return the number of tasks that have canceled.
	 * @deprecated debug only
	 */
	public int purge() {
		if (false == service.isTerminated())
			throw new IllegalStateException("service is still running.");
		int n = 0;
		for (Serial sq : serials)
			n += sq.purge();
		return n;
	}

    /**
	 * Applies a supplemental hash function to a given hashCode, which defends
	 * against poor quality hash functions. This is critical because HashMap
	 * uses power-of-two length hash tables, that otherwise encounter collisions
	 * for hashCodes that do not differ in lower bits. Note: Null keys always
	 * map to hash 0, thus index 0.
	 * @see java.util.HashMap
	 */
	static int hash(int h) {
		// This function ensures that hashCodes that differ only by
		// constant multiples at each bit position have a bounded
		// number of collisions (approximately 8 at default load factor).
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	/**
	 * Returns index for hash code h.
	 */
	static int indexFor(int h, int length) {
		return h & (length - 1);
	}

	/**
	 * for debug or manage.
	 * <b>not well defined!</b>
	 * @deprecated public for debug only
	 */
	public Serial serialFor(K key) {
		return serials[getIndexForKey(key)];
	}

	
	/** add by cjc 20110906
	 *  Serial Segment ID. for debug and statitis
	 */
	public int getIndexForKey(K key)
	{
		return indexFor(hash(key.hashCode()), serials.length);
	}
	
	
	/**
	 * for debug or manage.
	 */
//	public Serial[] serials() {
//		return serials;
//	}

	/**
	 * 取消并且删除任务。
	 * @param future 参数必须是通过 submit 提交任务时返回的结果。
	 */
    @SuppressWarnings("unchecked")
	public boolean remove(Future<?> future) {
		if (future instanceof SerialKeyExecutor<?>.Serial.Task<?>)
			return ((Serial.Task<?>)future).remove();

		throw new RuntimeException("SerialKeyExecutor.remove: future is not a serial task.");
	}

	private static Callable<Object> dummyHeader = new Callable<Object>() {
		public Object call() {
			throw new IllegalStateException("SerialKeyExecutor.header");
		}
	};

	public final class Serial {
		private final Task<?> header;
		private int size = 0;
		private final Lock lock = new ReentrantLock();

		Serial() {
			header = new Serial.Task<Object>(null, dummyHeader);
			reset();
		}

		// concurrent: user submit task.
		<T> Future<T> submit(K key, Callable<T> callable) {
			lock.lock();
			try {
				Serial.Task<T> newTask = addLast(new Serial.Task<T>(key, callable));
				if (size == 1) {
					try {
						SerialKeyExecutor.this.execute(newTask);
					} catch (RejectedExecutionException e) {
						remove(newTask); // roll back
						throw e;
					} catch (Throwable e) { // 确保正确性，应不会发生。
						remove(newTask); // roll back. 
						throw new RejectedExecutionException(e);
					}
				}
				return newTask;
			} finally {
				lock.unlock();
			}
		}

		// concurrent: serial task done. only One-Thread. lock in SerialTask.
		void scheduleNext(Serial.Task<?> done) {
			assert( done == getFirst() );
			this.remove(done);

			/**
			 * 删除已经取消的任务。
			 * 如果任务取消比较频繁，可以减少不必要的线程切换，提高效率。
			 * 这是优化，去掉这一步，仍然是正确的。
			 */
			while (this.size > 0 && getFirst().isCancelled()) removeFirst();
			// 如果任务在这个时间窗口被 cancel，相当于没有完成上面的删除，不会有问题。

			/**
			 * 调度执行下一个任务。
			 */
			while (this.size > 0) {
				final Serial.Task<?> first = getFirst();
				try {
					SerialKeyExecutor.this.execute(first);
					break; // execute done
				} catch (Throwable e) {
					// 一旦发生执行错误，马上结束所有任务。报告错误时，使用同一个异常引用，应该没问题吧。
					while (this.size > 0) removeFirst().setException(e);
					// 下面的处理方式更快，但是 Task 没有清理，不利于错误发现
					/*
					for (Task<?> task = header.next; task != header; task = task.next)
						task.setException(e); // 重复使用同一个异常引用，应该没问题吧。
					reset();
					// */
				}
			}
		}

		// concurrent: user cancel task by key
		int cancel(final K key) {
			lock.lock();
			try {
				if (this.size == 0)
					return 0;

				int canceled = 0;
				// cancel scheduled-task but don't remove.
				if (header.next.cancel(false))
					++ canceled;
				// try cancel and remove others.
				Serial.Task<?> task = header.next.next; // skip over scheduled-task.
				while (task != header) {
					final Serial.Task<?> next = task.next;
					if (task.key.equals(key) && task.cancel(false)) {
						this.remove(task);
						++ canceled;
					}
					task = next;
				}
				return canceled;
			} finally {
				lock.unlock();
			}
		}

		// for debug.
		int purge() {
			lock.lock();
			try {
				int canceled = 0;
				for (Serial.Task<?> task = header.next; task != header; task = task.next) {
					if (task.cancel(false))
						++ canceled;
				}
				reset();
				return canceled;
			} finally {
				lock.unlock();
			}
		}

		public int size() {
			lock.lock();
			try {
				return size;
			} finally {
				lock.unlock();
			}
		}

		final class Task<T> extends FutureTask<T> {
			private Task<?> next = null;
			private Task<?> previous = null;
			private final K key;

			Task(K key, Callable<T> callable) {
				super(callable);
				this.key = key;
			}
	
			// concurrent: user cancel and remove the task.
			public boolean remove() {
				if (super.cancel(false)) {
					// future.cancel 保证只能进入这里一次。
					lock.lock();
					try {
						/**
						 * 不能删除已经被派发出去的任务。下面条件判断的含义，如下：
						 * a) &&之前：检测派发但还没有执行完的任务。
						 * b) &&之后：已经派发出去任务有可能在这里（获得锁之前）执行完毕并移除。
						 */
						if (this != Serial.this.header.next && null != this.next)
							Serial.this.remove(this);
					} finally {
						lock.unlock();
					}
					return true;
				}
				return false;
			}

			// 这里不能重载 done() 来实现 scheduleNext 的逻辑。
			// 因为任务可能会被 cancel，此时 done() 也会被调用。

			@Override
			public void run() {
				try {
					super.run();
				} finally {
					lock.lock();
					try {
						Serial.this.scheduleNext(this);
					} finally {
						lock.unlock();
					}
				}
			}

			@Override
			protected void setException(Throwable t) {
				// override to access protected.
				super.setException(t);
			}
		}

		////////////////////////////////////////////////////////////////////
		// queue 相关方法. 注意，unsafe! 部分实现不安全，都控制在内部使用，马马虎虎过关。
		Serial.Task<?> getFirst() {
			if (size == 0)
			    throw new NoSuchElementException();
			return header.next;
		}

		Serial.Task<?> removeFirst() {
			return remove(getFirst());
		}

		private Task<?> remove(Task<?> task) {
			task.previous.next = task.next;
			task.next.previous = task.previous;
			task.next = task.previous = null; // 清除删除掉的节点，防止错误扩展并加快垃圾收集.
			size--;
			return task;
		}

		private <T> Serial.Task<T> addLast(Serial.Task<T> task) {
			task.next = header;
			task.previous = header.previous;
			header.previous.next = task;
			header.previous = task;
			++ size;
			return task;
		}

		/**
		 * 快速清除链表，没有清除中间接点。
		 */
		void reset() {
			header.next = header.previous = header;
			size = 0;
		}
	}
}

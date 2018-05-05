package xdb.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BeanPool<T extends xdb.Bean> {
	private Queue<T> queue = new ConcurrentLinkedQueue<T>();
	private AtomicInteger size = new AtomicInteger();
	private volatile int capacity;

	public BeanPool() {
		this(4096);
	}

	public BeanPool(int capacity) {
		this.capacity = capacity;
	}

	public T get() {
		T result = queue.poll();
		if (null != result) {
			size.decrementAndGet();
			return result;
		}
		return newBean();
	}

	protected abstract T newBean();

	public void _reset_unsafe_add_(T bean) {
		if (null == bean)
			return;
		if (size.incrementAndGet() < capacity) {
			bean._reset_unsafe_();
			queue.add(bean);
		} else {
			size.decrementAndGet();
		}
	}

	public int size() {
		return size.get();
	}

	public int getCapacity() {
		return capacity;
	}

	public synchronized void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public BeanPool<T> initialize(int capacity) {
		this.setCapacity(capacity);
		return this;
	}
}

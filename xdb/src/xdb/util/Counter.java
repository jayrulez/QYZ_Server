
package xdb.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.DynamicMBean;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.ReflectionException;

/**
 * 计数器集合。
 */
public class Counter implements DynamicMBean {
	private volatile Map<String, AtomicLong> noLock = new HashMap<String, AtomicLong>();
	private HashMap<String, AtomicLong> underLock = new HashMap<String, AtomicLong>();
	private java.util.concurrent.locks.Lock lock = new java.util.concurrent.locks.ReentrantLock();

	// get or create
	private AtomicLong open(String name) {
		final Map<String, AtomicLong> volatileTmp = noLock;
		AtomicLong counter = volatileTmp.get(name);
		if (null != counter) {
			return counter;
		}

		lock.lock();
		try {
			counter = underLock.get(name);
			if (null != counter) {
				return counter;
			}

			// create counter
			counter = new AtomicLong(0);
			underLock.put(name, counter);

			// copy to noLock. clone 效率更高.
			@SuppressWarnings("unchecked")
			final Map<String, AtomicLong> tmp = (Map<String, AtomicLong>)underLock.clone();
			noLock = tmp;

			return counter;
		} finally {
			lock.unlock();
		}
	}

	public void increment(String name) {
		open(name).incrementAndGet();
	}

	public void increment(String name, int delta) {
		open(name).addAndGet(delta);
	}

	public AtomicLong get(String name) {
		return noLock.get(name);
	}

	public void set(String name, long value) {
		open(name).set(value);
	}

	////////////////////////////////////////////////////////////////
	// DynamicMBean implement
	@Override
	public Object getAttribute(String name) {
		return noLock.get(name);
	}

	@Override
	public AttributeList getAttributes(String[] attributeNames) {
		AttributeList alist = new AttributeList();
		for (int i = 0; i < attributeNames.length; ++i) {
			Object value = getAttribute(attributeNames[i]);
			if (null != value)
				alist.add(new Attribute(attributeNames[i], value));
		}
		return alist;
	}

	@Override
	public void setAttribute(Attribute attribute) {
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		return null;
	}

	@Override
	public Object invoke(String operationName, Object params[], String signature[])
		throws MBeanException, ReflectionException {
			return null;
	}

	@Override
	public MBeanInfo getMBeanInfo() {
		MBeanAttributeInfo[] attrs = new MBeanAttributeInfo[noLock.size()];
		int i = 0;
		for (String attr : noLock.keySet())
			attrs[i++] = new MBeanAttributeInfo(attr, "java.lang.Long", attr, true, false, false);

		/*
		   MBeanConstructorInfo[] constructors = null; // new MBeanConstructorInfo[1];
		   MBeanOperationInfo[] operations = null; //new MBeanOperationInfo[1];
		   MBeanNotificationInfo[] notifications = null; // new MBeanNotificationInfo[0]
		 */

		return new MBeanInfo(this.getClass().getName(), "Counter", attrs, null, null, null);
	}

	/**
	 * 构造一个计数器
	 * @param base 基本目录
	 * @param type 类型，计数集合名字
	 */
	@Deprecated
	public Counter(String base, String type) {
		MBeans.register(this, base + ":type=" + type);
	}

	/**
	 * 构造一个计数器
	 * @param mbeans 把计数器注册到哪个mbeans管理器中。
	 * @param base 基本目录
	 * @param type 类型，计数集合名字
	 */
	public Counter(MBeans.Manager mbeans, String base, String type) {
		mbeans.register(this, base + ":type=" + type);
	}
}


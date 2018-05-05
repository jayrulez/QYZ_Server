
package com.goldhuman.Common;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.DynamicMBean;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.ReflectionException;

public class Counter implements DynamicMBean
{
	private ConcurrentMap<String, AtomicLong> mapcount = new ConcurrentHashMap<String, AtomicLong>();

	public void increment(String name)
	{
		AtomicLong first = new AtomicLong(1);
		AtomicLong exist = mapcount.putIfAbsent(name, first);
		if (null != exist)
			exist.incrementAndGet();
	}

	public void increment(String name, int delta)
	{
		AtomicLong first = new AtomicLong(delta);
		AtomicLong exist = mapcount.putIfAbsent(name, first);
		if (null != exist)
			exist.addAndGet(delta);
	}

	public AtomicLong get(String name)
	{
		return mapcount.get(name);
	}

	public void set(String name, long value)
	{
		AtomicLong first = new AtomicLong(value);
		AtomicLong exist = mapcount.putIfAbsent(name, first);
		if (null != exist)
			exist.set(value);
	}

	//////////////////////////////////////////////////////////////////////
	// mbean
	public Object getAttribute(String name)
	{
		return mapcount.get(name);
	}

	public AttributeList getAttributes(String[] attributeNames)
	{
		AttributeList alist = new AttributeList();
		for (int i = 0; i < attributeNames.length; ++i)
		{
			Object value = getAttribute(attributeNames[i]);
			alist.add(new Attribute(attributeNames[i], value));
		}
		return alist;
	}

	public void setAttribute(Attribute attribute)
	{
	}

	public AttributeList setAttributes(AttributeList attributes)
	{
		return null;
	}

	public Object invoke(String operationName, Object params[], String signature[])
		throws MBeanException, ReflectionException
	{
			return null;
	}

	public MBeanInfo getMBeanInfo()
	{
		MBeanAttributeInfo[] attributes = new MBeanAttributeInfo[mapcount.size()];
		int i = 0;
		for (String attri : mapcount.keySet())
			attributes[i++] = new MBeanAttributeInfo(attri, "java.lang.Long", attri, true, false, false);

		/*
		   MBeanConstructorInfo[] constructors = null; // new MBeanConstructorInfo[1];
		   MBeanOperationInfo[] operations = null; //new MBeanOperationInfo[1];
		   MBeanNotificationInfo[] notifications = null; // new MBeanNotificationInfo[0]
		 */

		return new MBeanInfo(this.getClass().getName(), "Counter", attributes, null, null, null);
	}

	public Counter(String type)
	{
		try
		{
			MBeanServer server = java.lang.management.ManagementFactory.getPlatformMBeanServer();
			server.registerMBean(this, new ObjectName("Counter:type=" + type));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}


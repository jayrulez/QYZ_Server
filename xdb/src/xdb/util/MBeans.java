package xdb.util;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import xdb.Trace;

/**
 * <p>注册MBean需要修改java的策略配置文件(java.policy)。增加权限，格式如下：
 * <br>-- permission javax.management.MBeanTrustPermission "register";
 * 
 * <p> java.policy 文件所在目录：
 * <br>JDK_HOME/jre/lib/security/java.policy
 * <br>JRE_HOME/lib/security/java.policy 
 * <br>最好把jdk,jre的配置都修改。
 *
 * <p>[注意] 使用MBean，在启动应用时，可能需要增加启动参数，请参考相应jvm版本的jmx配置。
 * @see JMXServer
 */

public class MBeans {
	public static ObjectName newObjectName(String name) {
		try {
			return new ObjectName(name);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static class Manager {
		private java.util.Set<ObjectName> mbeans = new java.util.HashSet<ObjectName>();
		/**
		 * 注册把 MBean 实例
		 * @param object MBean 实例
		 * @param name   MBean 名字
		 */
		public ObjectName register(Object object, String name) {
			synchronized (mbeans) {
				ObjectName objname = newObjectName(name);

				if (false == mbeans.add(objname))
					throw new RuntimeException("duplicate mbean name of '" + objname + "'");

				try {
					MBeanServer server = java.lang.management.ManagementFactory.getPlatformMBeanServer();
					server.registerMBean(object, objname);
					return objname;
				} catch (Throwable e) {
					mbeans.remove(objname);
					throw new RuntimeException("see xdb.Xdb.registerMBean", e);
				}
			}
		}

		/**
		 * 注销所有通过这个类注册的mbeans。
		 */
		public void unregisterAll() {
			synchronized (mbeans) {
				MBeanServer server = java.lang.management.ManagementFactory.getPlatformMBeanServer();
				for (ObjectName name : mbeans) {
					try {
						server.unregisterMBean(name);
					} catch (Throwable e) {
						Trace.error("unregisterMBean name=" + name, e);
					}
				}
				mbeans.clear();
			}
		}

		/**
		 * 注销掉MBean。
		 * 
		 * @param objname 对象名字，register 返回的名字。
		 */
		public void unregister(ObjectName objname) {
			synchronized (mbeans) {
				try {
					if (mbeans.remove(objname)) {
						MBeanServer server = java.lang.management.ManagementFactory.getPlatformMBeanServer();
						server.unregisterMBean(objname);
					}
				} catch (Throwable e) {
					Trace.error("unregisterMBean name=" + objname, e);
				}
			}
		}
	}

	////////////////////////////////////////////////////////
	// global instance, 为了兼容继续保留。
	private static Manager local = new Manager();

	/**
	 * 注册把 MBean 实例
	 * @param object MBean 实例
	 * @param name   MBean 名字
	 */
	@Deprecated
	public static ObjectName register(Object object, String name) {
		return local.register(object, name);
	}

	/**
	 * Xdb 停止的时候自动调用。不需要显示调用。
	 */
	@Deprecated
	public static void unregisterAll() {
		local.unregisterAll();
	}

	/**
	 * 注销掉MBean。
	 * 
	 * @param objname 对象名字，register 返回的名字。
	 */
	@Deprecated
	public static void unregister(ObjectName objname) {
		local.unregister(objname);
	}
}

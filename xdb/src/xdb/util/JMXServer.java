package xdb.util;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

/**
 * Usage:
 * <pre>
 * main() {
 *     JMXServer js = new JMXServer(...);
 *     js.start();
 *     ...
 *     js.stop();
 * }
 * </pre>
 * 
 * <p>As you may already know if you have been confronted with this problem, 
 * the JMX RMI connector opens two ports: one is for the RMI registry, 
 * and it's the port that you usually supply with the 
 * -Dcom.sun.management.jmxremote.port=<port> property. The other port is used to 
 * export JMX RMI connection objects. This second port is usually dynamically 
 * allocated at random. Indeed you don't need to know this port number in order to 
 * connect to the JMX agent: the only port number you need to know to connect is 
 * the RMI registry port number from which to obtain the connection objects.
 * <p> This however can prove to be troublesome if your application is behind a 
 * firewall that block access to random ports. The default JVM agent will not 
 * let you specify that second port number, and you're stuck. The only way to 
 * specify that second port number is to use a JMXServiceURL, but you can't 
 * supply a JMXServiceURL to the default agent.
 */
public class JMXServer {
	private JMXConnectorServer cs;

	public JMXServer() throws IOException {
		this(1098);
	}

	/**
	 * <i>this(rmiport, rmiport + 1, "127.0.0.1", null, null);</i>
	 * 
	 * @param rmiport
	 * @throws IOException
	 */
	public JMXServer(int rmiport) throws IOException {
		this(rmiport, rmiport + 1, "127.0.0.1", null, null);
	}

	/**
	 * @param rmiport      rmi 端口
	 * @param serverport   服务端口，通常等于(rmiport + 1)。
	 * @param hostname     如果不为null，则修改系统属性“java.rmi.server.hostname”。
	 * @param passwordfile 如果为 null，则不需要验证并获得所有权限。
	 * @param accessfile   如果为null，用户获得所有权限。
	 * @throws IOException
	 */
	public JMXServer(int rmiport, int serverport
			, String hostname
			, String passwordfile, String accessfile
			) throws IOException {

		if (null != hostname)
			System.setProperty("java.rmi.server.hostname", hostname);

		LocateRegistry.createRegistry(rmiport);

		JMXServiceURL url = new JMXServiceURL(
				String.format("service:jmx:rmi://localhost:%d/jndi/rmi://localhost:%d/jmxrmi",
				serverport, rmiport));

		Map<String, Object> env = new HashMap<String, Object>();

		if (null != passwordfile)
			env.put("jmx.remote.x.password.file", passwordfile);
		if (null != accessfile)
			env.put("jmx.remote.x.access.file", accessfile);

		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		cs = JMXConnectorServerFactory.newJMXConnectorServer(url, env, mbs);
	}

	public void start() throws IOException {
		cs.start();
	}

	public void stop() {
		try { cs.stop(); } catch (Exception ex) { ex.printStackTrace(); }
	}

	/////////////////////////////////////////////////////////////////////////////
	// javaagent
	private static int getPropertyInt(String key, int def) {
		return Integer.parseInt(System.getProperty(key, String.valueOf(def)));
	}
 
	/**
	 * for -javaagent
	 * @param agentArgs
	 * @throws IOException
	 */
	public static void premain(String agentArgs) throws IOException {

    	final int rmiport     = getPropertyInt("xdb.util.jmxserver.rmi.port", 1098);
		final int serverport  = getPropertyInt("xdb.util.jmxserver.port", rmiport + 1);
		final String hostname = System.getProperty("java.rmi.server.hostname") == null ? "127.0.0.1" : null;
		final String password = System.getProperty("xdb.util.jmxserver.password.file");
		final String access   = System.getProperty("xdb.util.jmxserver.access.file");

		JMXServer jmxs = new JMXServer(rmiport, serverport, hostname, password, access);
		jmxs.start();
    }

	public static void main(String args[]) throws Exception {
		String hostname = "127.0.0.1";
		int rmiport = 1098;
		int serverport = 1099;
		if (args.length > 0) { hostname = args[0]; }
		if (args.length > 1) { rmiport = Integer.parseInt(args[1]); serverport = rmiport + 1; }
		if (args.length > 2) { serverport = Integer.parseInt(args[2]); }

		System.out.println("hostname=" + hostname + " rmpport=" + rmiport + " serviceport=" + serverport);
		JMXServer js = new JMXServer(rmiport, serverport, hostname, null, null);

		try {
			js.start();

			xdb.util.MBeans.Manager manager = new xdb.util.MBeans.Manager();
			Counter counter = new Counter(manager, "xtest", "TestCounter");
			counter.increment("test");
			System.out.println("jmx service started!");
			System.in.read();
			manager.unregisterAll();
			System.out.println("jmx service stop ... ");
		} finally {
			js.stop();
		}
	}

	/*
	public static class Authenticator implements JMXAuthenticator {

		public Subject authenticate(Object credentials) {
			// Verify that credentials is of type String[].
			if (credentials == null)
				throw new SecurityException("Credentials required");
			if (!(credentials instanceof String[]))
				throw new SecurityException("Credentials should be String[]");

			// Verify
			final String[] aCredentials = (String[]) credentials;
			if (aCredentials.length < 2)
				throw new SecurityException("Credentials should have 2 elements");

			// Perform authentication
			String username = aCredentials[0];
			String password = aCredentials[1];

			// ... perform authentication based on the

			if (false) {
				throw new SecurityException("Invalid credentials");
			}
			return new Subject(true,
					Collections.singleton(new JMXPrincipal(username)),
					Collections.EMPTY_SET, Collections.EMPTY_SET);
		}
	}

	public static class MBSFInvocationHandler implements InvocationHandler {

		public static MBeanServerForwarder newProxyInstance() {
			final InvocationHandler handler = new MBSFInvocationHandler();
			@SuppressWarnings("rawtypes")
			final Class[] interfaces = new Class[] { MBeanServerForwarder.class };
			Object proxy = Proxy.newProxyInstance(MBeanServerForwarder.class.getClassLoader(), interfaces, handler);
			return MBeanServerForwarder.class.cast(proxy);
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			final String methodName = method.getName();

			if (methodName.equals("getMBeanServer"))
				return mbs;

			if (methodName.equals("setMBeanServer")) {
				if (args[0] == null)
					throw new IllegalArgumentException("Null MBeanServer");

				if (mbs != null)
					throw new IllegalArgumentException("MBeanServer object already initialized");

				mbs = (MBeanServer) args[0];
				return null;
			}

			// Retrieve Subject from current AccessControlContext
			AccessControlContext acc = AccessController.getContext();
			Subject subject = Subject.getSubject(acc);

			// Allow operations performed locally on behalf of the connector
			// server itself
			if (subject == null)
				return method.invoke(mbs, args);

			// Restrict access to "createMBean" and "unregisterMBean" to any
			// user
			if (methodName.equals("createMBean") || methodName.equals("unregisterMBean"))
				throw new SecurityException("Access denied");

			// Retrieve JMXPrincipal from Subject
			Set<JMXPrincipal> principals = subject.getPrincipals(JMXPrincipal.class);
			if (principals == null || principals.isEmpty())
				throw new SecurityException("Access denied");

			Principal principal = principals.iterator().next();
			String identity = principal.getName();

			// "role1" can perform any operation other than "createMBean"
			// and "unregisterMBean"
			if (identity.equals("role1"))
				return method.invoke(mbs, args);

			// "role2" can only call "getAttribute" on the
			// MBeanServerDelegate MBean
			if (identity.equals("role2")
					&& methodName.equals("getAttribute")
					&& MBeanServerDelegate.DELEGATE_NAME.equals(args[0])) {
				return method.invoke(mbs, args);
			}

			throw new SecurityException("Access denied");
		}

		private MBeanServer mbs;
	}
	*/
}

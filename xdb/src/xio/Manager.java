package xio;

import com.goldhuman.Common.Marshal.OctetsStream;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xdb.Executor;
import xdb.Trace;

import javax.management.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
/**
 * 
 * 连接管理
 * 
 * DynamicMBean 由 XioConf 在open的时候注册到MBeans中。
 * 
 * @author lichenghua
 *
 */
public abstract class Manager implements DynamicMBean {
	// 没有线程安全保护。除了构造初始化，以后都是读。
	String name;
	int maxSize;
	private Map<String, Creator> creators = new HashMap<String, Creator>();
	Manager.Coder coder; // 协议配置
	private XioConf conf;
	private AtomicLong newXioCount = new AtomicLong();

	/**
	 * 协议处理派发：把协议加入到线程池中，由工作线程处理。应用可以重载这个函数做一些处理。 
	 * <p><b>注意，这里可能拦截不到所有的协议。</b>如 Rpc 的处理不一定会走这条路。
	 * 如下两种情形
	 * <ol>
	 * <li>同步调用时，Rpc.Result会直接交给等待的线程。 
	 * <li>如果 Rpc.Result 配置了存储过程，xio将直接把处理交给 xdb.Procedure。在内部把存储过程的结果发送回去。
	 * </ol>
	 * @param p 需要执行的协议。
	 */
	public void execute(Protocol p) {
		Executor.getInstance().execute(p);
	}

	/**
	 * 发送协议之前调用
	 */
	protected void beforeSend(Protocol p, Xio to) {
	}

	// 需要实现的Manager方法。
	/**
	 * 连接关闭时调用
	 */
	protected abstract void removeXio(Xio xio, Throwable e);

	/**
	 * 连接建立时调用
	 */
	protected abstract void addXio(Xio xio);

	/**
	 * 当前管理器中的所有连接
	 */
	public abstract int size();

	/**
	 * 随便返回一个连接，可能返回null。用于测试目的
	 */
	public abstract Xio get();

	/**
	 * close
	 */
	protected void close() {
		// close creator
		for (Creator c : creators.values())
			c.close();
		closeAllContexts();
	}


	/**
	 * 通过重载这个方法可以实现:
	 * * IP 限制。
	 * * 初始化filter。
	 * * 设置 interestOps
	 * * 提供新的 Xio 实现。
	 * @param channel
	 * @return
	 */
	protected Xio newXio(Creator creator, SelectorThread selector, SocketChannel channel) {
		Xio.Input input = new Xio.Input();
		Xio.Output output = new Xio.Output(creator);
		coder.initFilterList(input, output);
		return new Xio(creator, selector, channel, Xio.OP_READ, input, output);
	}

	final long incrementAndGetNewXioCount() {
		return newXioCount.incrementAndGet();
	}

	final String getMBeanName() {
		return "XioConf." + conf.getName() + "/" + getClass().getName() + "." + getName();
	}

	protected void onConnectAbort(Connector c, Throwable e) {
		xdb.Trace.warn("Connect Abort: " + c, e);
	}

	public Manager.Coder getCoder() {
		return coder;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public int getCreatorCount() {
		return creators.size();
	}

	public Creator getCreator(InetSocketAddress address) {
		for (Creator c : this.creators.values())
			if (address.equals(c.getAddress()))
				return c;
		return null;
	}

	public Creator getCreator(String name) {
		return creators.get(name);
	}

	void open() {
		for (Creator c : creators.values()) c.open();
	}

	/////////////////////////////////////////////////////////////////////
	// 系列号及上下文管理.
	private int serialId = 0; // 1~0x7fffffff. see Rpc
	private Map<Integer, java.io.Closeable> contexts = new HashMap<Integer, java.io.Closeable>();

	private final void closeAllContexts() {
		synchronized (contexts) {
			for (java.io.Closeable c : contexts.values())
				try { c.close(); } catch (Throwable e) { }
				contexts.clear();
		}
	}

	public final java.io.Closeable getContext(int sid) {
		synchronized (contexts) {
			return contexts.get(sid);
		}
	}

	public final <T> T getContext(int sid, T hint) {
		synchronized (contexts) {
			@SuppressWarnings("unchecked")
			T a = (T)contexts.get(sid);
			return a;
		}
	}

	public final java.io.Closeable removeContext(int sid) {
		synchronized (contexts) {
			return contexts.remove(sid);
		}
	}

	public final <T> T removeContext(int sid, T hint) {
		synchronized (contexts) {
			@SuppressWarnings("unchecked")
			T a = (T)contexts.remove(sid);
			return a;
		}
	}

	public final int addContext(java.io.Closeable obj) {
		synchronized (contexts) {
			do {
				++ serialId;
				if (serialId <= 0) // 高位保留给rpc，用来区分是否请求. 另外保留 0。
					serialId = 1;
				if (false == contexts.containsKey(serialId)) {
					contexts.put(serialId, obj);
					return serialId;
				}
			} while (true);
		}
	}
	/////////////////////////////////////////////////////////////////
	public static abstract class Coder {
		public abstract void parse(Manager manager, Element self) throws Exception;
		public abstract void initFilterList(Filter.List input, Filter.List output);
		public abstract void dispatch(int type, OctetsStream os, Xio from, Object context) throws Exception;
		public abstract void checkSend(Protocol p, int psize);

		static Coder create(Manager manager, Element self) throws Exception {
			String clsName = self.getAttribute("class");
			Coder coder = clsName.isEmpty() ? new Protocol.Coder() :
				(Coder)Class.forName(clsName).newInstance();
			coder.parse(manager, self);
			return coder;
		}
	}

	private void add(Creator c) {
        Trace.debug("addCreator:{}", c);
        if (null != creators.put(c.getName(), c))
			throw new RuntimeException("Creator duplicate! " + c);
	}

	void add(Manager.Coder coder) {
		if (null != this.coder)
			throw new RuntimeException("too many Coder");
		this.coder = coder;
	}

	/**
	 * 解析配置，并初始化配置。
	 * 可以重载这个方法，用来解析新增的自定义 Manager 的配置。重载时必须调用 super.parse 以初始化基本配置。
	 * @param self
	 * @throws Exception
	 */
	protected void parse(Element self) throws Exception {
		this.name = self.getAttribute("name");
		if (this.name.isEmpty())
			throw new RuntimeException("Manager need a name");

		String tmp;
		this.maxSize = (tmp = self.getAttribute("maxSize")).isEmpty() ? 0 : Integer.parseInt(tmp);

		NodeList childnodes = self.getChildNodes();
		for (int i = 0; i < childnodes.getLength(); ++i) {
			Node node = childnodes.item(i);
			if (Node.ELEMENT_NODE != node.getNodeType())
				continue;

			Element e = (Element) node;
			String n = e.getNodeName();
			if (n.equals("Connector"))     add(Connector.create(this, e));
			else if (n.equals("Acceptor")) add(Acceptor.create(this, e));
			else if (n.equals("Coder"))    add(Manager.Coder.create(this, e));
            else if (n.equals("DynamicMultiConnector")) add (DynamicMultiConnector.create(this, e));
			else
				throw new RuntimeException("Unkown! node=" + n + " parent=" + self.getNodeName() + "," + name);
		}
	}

	public final XioConf getConf() {
		return conf;
	}

	public static Manager create(XioConf conf, Element self) throws Exception {
		String clsName = self.getAttribute("class");
		Manager manager = clsName.isEmpty() ? new ManagerSimple() :
			(Manager)Class.forName(clsName).newInstance();
		manager.parse(self);
		manager.conf = conf;
		return manager;
	}

	protected Manager() {
	}

	////////////////////////////////////////////////////////////////
	// DynamicMBean implement
	@Override
	public Object getAttribute(String attribute) {
		if (attribute.startsWith("count."))
			return this.newXioCount.get(); // 目前只有一个计数器。
		return "<- see this";
	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		AttributeList alist = new AttributeList();
		for (int i = 0; i < attributes.length; ++i) {
			Object value = getAttribute(attributes[i]);
			if (null != value)
				alist.add(new Attribute(attributes[i], value));
		}
		return alist;
	}

	@Override
	public MBeanInfo getMBeanInfo() {
		MBeanAttributeInfoBuilder builder = new MBeanAttributeInfoBuilder();
		this.buildMBeanInfo(builder);
		return new MBeanInfo(this.getClass().getName(), "Manager",
				builder.toArray(), null, null, null);
	}

	public static final class MBeanAttributeInfoBuilder {
		private Map<String, MBeanAttributeInfo> attrs = new HashMap<String, MBeanAttributeInfo>();

		public void add(MBeanAttributeInfo minfo) {
			if (attrs.put(minfo.getName(), minfo) != null)
				throw new RuntimeException("duplicate MBeanAttributeInfo. name=" + minfo.getName());
		}

		public MBeanAttributeInfo[] toArray() {
			return attrs.values().toArray(new MBeanAttributeInfo[attrs.size()]);
		}
	}

	/**
	 * 用于子类重载，并加入更多的动态 MBean 属性。
	 * 重载实现必须调用 super 的方法。否则基类的属性会被丢失。
	 * 当子类加入动态属性时，也需要重载 getAttribute 返回属性值。
	 * @param attrs
	 */
	protected void buildMBeanInfo(MBeanAttributeInfoBuilder attrs) {
		for (Creator c : creators.values()) {
			StringBuilder sb = new StringBuilder();
			if (c instanceof Acceptor)
				sb.append("acceptor.");
			else if (c instanceof Connector)
				sb.append("connector.");
			else
				sb.append("creator.");
			sb.append(c.getName());
			attrs.add(new MBeanAttributeInfo(sb.toString(),
					"java.lang.String", "creator", true, false, false));
		}
		attrs.add(new MBeanAttributeInfo("count.newXioCount",
				"java.lang.Long", "count of xio created", true, false, false));
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature)
			throws MBeanException, ReflectionException {
		return null;
	}

	@Override
	public void setAttribute(Attribute attribute)
			throws AttributeNotFoundException, InvalidAttributeValueException,
			MBeanException, ReflectionException {
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		return null;
	}
}

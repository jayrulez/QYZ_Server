package xio;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

/**
 * xio 装载配置。
 * 
 * @author lichenghua
 *
 */
public class XioConf {
	private String name;
	private Map<String, Manager> managers = new HashMap<String, Manager>();

	public String getName() {
		return name;
	}

	public Manager getManager(String name) {
		return managers.get(name);
	}

	void open() {
		for (Manager manager : managers.values()) {
			manager.open();
			// 在 xio 停止时，会自动 unregister 所有的 MBean。
			Engine.mbeans().register(manager, "xio:type=" + manager.getMBeanName());
		}
	}

	void close() {
		for (Manager manager : managers.values()) {
			manager.close();
		}
	}

	private void add(Manager manager) {
		if (null != managers.put(manager.getName(), manager))
			throw new RuntimeException("Manager duplicate : " + manager);
	}

	public XioConf(Element self) throws Exception {
		if (false == self.getNodeName().equals("XioConf"))
			throw new IllegalArgumentException(self.getNodeName() + " is not a XioConf.");

		name = self.getAttribute("name");

		NodeList childnodes = self.getChildNodes();
		for (int i = 0; i < childnodes.getLength(); ++i) {
			Node node = childnodes.item(i);
			if (Node.ELEMENT_NODE != node.getNodeType())
				continue;

			Element e = (Element) node;
			String n = e.getNodeName();
			if (n.equals("Manager")) add(Manager.create(this, e));
			else
				throw new RuntimeException("Unkown! node=" + n + " parent=" + self.getNodeName());
		}
	}

	/**
	 * 在指定节点的子节点中查找并装载 xio 配置，然后返回。没有找到会抛出异常。
	 * 
	 * @param parent 指定的父节点
	 * @return xio配置。
	 * @throws Exception
	 */
	public static XioConf loadInChildNodes(Element parent) throws Exception {
		NodeList childnodes = parent.getChildNodes();
		for (int i = 0; i < childnodes.getLength(); ++i) {
			Node node = childnodes.item(i);
			if (Node.ELEMENT_NODE != node.getNodeType())
				continue;

			Element e = (Element) node;
			String nodename = e.getNodeName();
			if (nodename.equals("XioConf")) {
				return new XioConf(e);
			}
		}
		throw new java.lang.IllegalStateException();
	}

	/**
	 * 从文件重装载和注册 xio 配置。
	 * @param conf 配置文件名。
	 * @throws Exception
	 */
	public static void loadAndRegister(String conf) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setXIncludeAware(true);
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().parse(conf);
		Engine.getInstance().register(new XioConf(doc.getDocumentElement()));
	}

	/**
	 * @param self
	 * @throws Exception
	 */
	public static void loadAndRegister(Element self) throws Exception {
		Engine.getInstance().register(new XioConf(self));
	}

	/**
	 * 从 XioConf 节点创建并注册配置。
	 * 支持从节点创建配置，是为了方便配置可以包含到任意的 xml 配置中。
	 * <p>在装载时，直接注册到引擎中，免去启动时的注册调用。但由于不允许同名的 XioConf 存在，
	 * 重复注册会失败。如果采用这种方式装载并注册配置，会产生一个问题，所有包含配置的文件都无法装载多次。
	 * 更好的方法是当配置包含在其他配置中时，使用 loadInChildNodes 得到配置实例，自己控制注册时间
	 * （必须在xio.Engine.start之前）。
	 * @param parent
	 * @throws Exception
	 */
	public static void loadAndRegisterInChildNodes(Element parent) throws Exception {
		Engine.getInstance().register(loadInChildNodes(parent));
	}
}

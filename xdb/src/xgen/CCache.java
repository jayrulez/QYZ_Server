package xgen;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CCache {
	private String name;
	private String key;
	private List<CTable> ctables = new ArrayList<CTable>();
	private Set<String> ctableNames = new HashSet<String>();

	//////////////////////////////
	// compiled
	private Xdb xdb;
	private Type keytype;

	public String getName() {
		return name;
	}

	public Xdb getXdb() {
		return xdb;
	}

	void compile(Xdb xdb) {
		this.xdb = xdb;

		for (CTable ctable : ctables)
			ctable.compile(this);

		if (ctables.isEmpty())
			throw new IllegalStateException("ccache reference none table. ccache=" + name);
		this.keytype = ctables.get(0).getTable().getKeyType();
		for (CTable ctable : ctables) {
			if (false == ctable.getTable().getKeyType().getName().equals(this.key))
				throw new IllegalStateException("ccache : different table-keytype. ccache='"
						+ name + "' ctable='" + ctable.getName());
		}
	}


	void make() {
		PrintStream ps = Main.fopen(xdb.getXcachedir(), Main.toUpper1(name));
		ps.println("package xcache;");
		ps.println("");
		ps.println("// 用户指定的cache名字作为最外层类的名字。cache的访问入口。");
		ps.println("public class " + Main.toUpper1(name) + " {");
		ps.println("	////////////////////////////////////////////////////////////////");
		ps.println("	// define cached valuetype");
		ps.println("	public static class Valuetype {");

		ps.println("		// declare valuetype");
		for (CTable ctable : ctables) {
			ctable.makeDeclareValuetype(ps);
		}

		ps.println("		// define valuetype");
		for (CTable ctable : ctables) {
			ps.println("		" + ctable.getCValuetypeName() + " " + ctable.getName() + ";");
		}
		ps.println("");

		for (CTable ctable : ctables) {
			ctable.makeGetValuetype(ps);
		}
		ps.println("		Valuetype shallowcopy() {");
		ps.println("			Valuetype sc = new Valuetype();");
		for (CTable ctable : ctables) {
			ps.println("			sc." + ctable.getName() + " = this." + ctable.getName() + ";");
		}
		ps.println("			return sc;");
		ps.println("		}");
		ps.println("	}");
		ps.println("");
		ps.println("	///////////////////////////////////////////////////////////////");
		ps.println("	// cache define");
		ps.println("	// 外层公开的接口，得到的是Cache类型是基类的。基类只公开get和existInCache(不是必要的)。");
		ps.println("	public static xdb.util.ConcurrentCache<"
							+ keytype.getBoxingName() + ", Valuetype> getCache() {");
		ps.println("		return CacheImpl.instance;");
		ps.println("	}");
		ps.println("");
		ps.println("	public static Valuetype get(" + keytype.getBoxingName() + " key) {");
		ps.println("		return CacheImpl.instance.get(key);");
		ps.println("	}");
		ps.println("");
		ps.println("	public static class CacheImpl extends xdb.util.ConcurrentCache<"
							+ keytype.getBoxingName() + ", Valuetype> {");
		ps.println("		CacheImpl() {");
		ps.println("			super(\"" + name + "\");");
		ps.println("		}");
		ps.println("");
		ps.println("		// 暂时先不公开。需要的时候在基类添加接口并调整这里代码。");
		ps.println("		// @Override");
		ps.println("		xdb.Lockey[] realGetLocks(" + keytype.getBoxingName() + " key) {");
		ps.println("			return new xdb.Lockey[]{");
		for (CTable ctable : ctables) {
			ps.println("					xdb.Lockeys.get(xtable."
					+ Main.toUpper1(ctable.getName()) + ".getTable(), key),");
		}
		ps.println("			};");
		ps.println("		}");
		ps.println("");
		ps.println("		private static class RealGet extends xdb.Procedure {");
		ps.println("			private xdb.Lockey[] locks;");
		ps.println("			private " + keytype.getBoxingName() + " key;");
		ps.println("			private Valuetype value = new Valuetype();");
		ps.println("			");
		ps.println("			@Override");
		ps.println("			protected boolean process() throws Exception {");
		ps.println("				// 先把记录锁排序并锁上，减少死锁可能。");
		ps.println("				xdb.Lockeys.lock(locks);");
		ps.println("");
		for (CTable ctable : ctables) {
			ctable.makeRealGet(ps, "				");
		}
		ps.println("				return true;");
		ps.println("			}");
		ps.println("");
		ps.println("			Valuetype get(xdb.Lockey[] locks, " + keytype.getBoxingName() + " key) {");
		ps.println("				this.locks = locks;");
		ps.println("				this.key = key;");
		ps.println("				if (false == this.call())");
		ps.println("					throw new RuntimeException(\"readGet fail.\", this.getException());");
		ps.println("				return this.value;");
		ps.println("			}");
		ps.println("		}");
		ps.println("");
		ps.println("		@Override");
		ps.println("		protected Valuetype realGet(" + keytype.getBoxingName() + " key) {");
		ps.println("			return new RealGet().get(realGetLocks(key), key);");
		ps.println("		}");
		ps.println("");
		ps.println("		/////////////////////////////////////////////////////////////////////");
		ps.println("		// update helper");
		for (CTable ctable : ctables) {
			ctable.makeUpdateHelper(ps);
		}
		ps.println("		static CacheImpl instance = new CacheImpl();");
		ps.println("	}");
		ps.println("");
		ps.println("	////////////////////////////////////////////////////////////////////////////////");
		ps.println("	// deepcopy helper");
		for (CTable ctable : ctables) {
			ctable.makeDeepcopyHelper(ps);
		}
		ps.println("	/////////////////////////////////////////////////////////////////////////////////");
		ps.println("	// 注册 Listener。");
		ps.println("	static void registryTableListener() {");
		for (CTable ctable : ctables) {
			ctable.makeRegistryTableListener(ps);
		}
		ps.println("	}");
		ps.println("");
		ps.println("	static void unregistryTableListener() {");
		for (CTable ctable : ctables) {
			ctable.makeUnregistryTableListener(ps);
		}
		ps.println("	}");
		ps.println("");
		for (CTable ctable : ctables) {
			ctable.makeTableListener(ps);
		}
		ps.println("}");
		ps.close();
	}

	static void make_ConcurrentCaches_(Xdb xdb, List<CCache> ccaches) {
		PrintStream ps = Main.fopen(xdb.getXcachedir(), "_ConcurrentCaches_");

		ps.println("package xcache;");
		ps.println("");
		ps.println("public class _ConcurrentCaches_ extends xdb.util.ConcurrentCaches {");
		ps.println("	public _ConcurrentCaches_() {");
		ps.println("	}");
		ps.println("");
		ps.println("	@Override");
		ps.println("	protected void onStart() {");
		for (CCache ccache : ccaches) {
			ps.println("		super.add(" + Main.toUpper1(ccache.getName()) + ".getCache());");
		}
		ps.println("");
		for (CCache ccache : ccaches) {
			ps.println("		" + Main.toUpper1(ccache.getName()) + ".registryTableListener();");
		}
		ps.println("	}");
		ps.println("");
		ps.println("	@Override");
		ps.println("	protected void onStop() {");
		for (CCache ccache : ccaches) {
			ps.println("		" + Main.toUpper1(ccache.getName()) + ".unregistryTableListener();");
		}
		ps.println("	}");
		ps.println("");
		ps.println("}");
		ps.println("");

		ps.close();
	}

	void add(CTable c) {
		if (false == ctableNames.add(c.getName()))
			throw new IllegalArgumentException("duplicate ctable: '" + c.getName() + "'@'" + name + "'");
		ctables.add(c);
	}

	public CCache(Element self) {
		name = self.getAttribute("name").trim().toLowerCase();
		Main.verifyName(name);
		key = self.getAttribute("key");

		NodeList childnodes = self.getChildNodes();
		for (int i = 0; i < childnodes.getLength(); ++i) {
			Node node = childnodes.item(i);
			if (Node.ELEMENT_NODE != node.getNodeType())
				continue;

			Element e = (Element) node;
			String nodename = e.getNodeName();

			if (nodename.equals("ctable"))
				add(new CTable(e));
			else
				throw new RuntimeException("node=" + nodename);
		}
	}
}

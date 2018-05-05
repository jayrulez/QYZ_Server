package xgen;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CTable {
	private String name;
	private List<CVariable> cvariables = new ArrayList<CVariable>();
	private Set<String> cvarNames = new HashSet<String>();

	////////////////////////////////
	// compiled
	private CCache ccache;
	private Table table;
	private List<Variable> variables; // 变量引用列表。
	private boolean isXBeanOrCBean; // XBean or CBean

	public String getName() {
		return name;
	}

	public CCache getCcache() {
		return ccache;
	}

	public Table getTable() {
		return table;
	}

	void makeTableListener(PrintStream ps) {
		String output = 
			"	static class %1$sListener implements xdb.logs.Listener {\r\n" +
			"		static class UpdateJob implements Runnable {\r\n" +
			"			private %2$s key;\r\n" +
			"			private %3$s value;\r\n" +
			"\r\n" +
			"			UpdateJob(%2$s key, %3$s value) {\r\n" +
			"				this.key = key;\r\n" +
			"				this.value = value;\r\n" +
			"			}\r\n" +
			"\r\n" +
			"			@Override\r\n" +
			"			public void run() {\r\n" +
			"				CacheImpl.instance.update%1$s(key, value);\r\n" +
			"			}\r\n" +
			"		}\r\n" +
			"		@Override\r\n" +
			"		public void onChanged(Object key) {\r\n" +
			"			if (CacheImpl.instance.existInCache((%2$s)key))\r\n" +
			"				xdb.util.ConcurrentCaches.getInstance().add(\r\n" +
			"						new UpdateJob((%2$s)key, %4$s(xtable.%1$s.get((%2$s)key))));\r\n" +
			"		}\r\n" +
			"		@Override\r\n" +
			"		public void onChanged(Object key, String fullVarName, xdb.logs.Note note) {\r\n" +
			"			if (CacheImpl.instance.existInCache((%2$s)key))\r\n" +
			"				xdb.util.ConcurrentCaches.getInstance().add(\r\n" +
			"						new UpdateJob((%2$s)key, %4$s(xtable.%1$s.get((%2$s)key))));\r\n" +
			"		}\r\n" +
			"		@Override\r\n" +
			"		public void onRemoved(Object key) {\r\n" +
			"			if (CacheImpl.instance.existInCache((%2$s)key))\r\n" +
			"				xdb.util.ConcurrentCaches.getInstance().add(\r\n" +
			"						new UpdateJob((%2$s)key, null));\r\n" +
			"		}\r\n" +
			"		static %1$sListener instance = new %1$sListener();\r\n" +
			"	}\r\n";

		String Name = Main.toUpper1(name);
		String keyTypeName = table.getKeyType().getBoxingName();
		String deepcopy = this.isXBeanOrCBean ? "deepcopy" : "";
		output = String.format(output, Name, keyTypeName, this.getCValuetypeName(), deepcopy);
		ps.println(output);
	}

	void makeUnregistryTableListener(PrintStream ps) {
		String Name = Main.toUpper1(name);
		if (this.cvariables.isEmpty()) {
			ps.println("		xtable." + Name + ".getTable().removeListener("
					+ Name + "Listener.instance, \"value\");");
		} else for (CVariable cvar : cvariables) {
			ps.println("		xtable." + Name + ".getTable().removeListener("
					+ Name + "Listener.instance, \"value\", \"" + cvar.getname() + "\");");
		}
		ps.println("");
	}

	void makeRegistryTableListener(PrintStream ps) {
		String Name = Main.toUpper1(name);
		if (this.cvariables.isEmpty()) {
			ps.println("		xtable." + Name + ".getTable().addListener(" + Name + "Listener.instance, \"value\");");
		} else for (CVariable cvar : cvariables) {
			ps.println("		xtable." + Name + ".getTable().addListener(" + Name + "Listener.instance, \"value\", \"" + cvar.getname() + "\");");
		}
		ps.println("");
	}

	void makeDeepcopyHelper(PrintStream ps) {
		if (!this.isXBeanOrCBean)
			return; // 当表格的值类型是简单类型时，不需要生成deepcopy。

		String Name = Main.toUpper1(name);
		String originValueTypeName = table.getValueType().getBoxingName();

		ps.println("	public static Valuetype." + Name + " deepcopy(" + originValueTypeName + " b) {");
		ps.println("		if (null == b) return null;");
		ps.println("");
		ps.println("		Valuetype." + Name + " r = new Valuetype." + Name + "();");

		for (Variable v : variables) {
			if (v.getVartype().isAny())
				throw new RuntimeException("variable is any! ccache='" + ccache.getName()
						+ "' table='" + name + "' variable='" + v.getname() + "'");

			String getDeepcopy = "get" + v.getName();

			if (v.getVartype() instanceof TypeCollection)
				getDeepcopy += "AsData()";
			else if (v.getVartype() instanceof TypeBaseMap)
				getDeepcopy += "AsData()";
			else if (v.getVartype() instanceof TypeBinary)
				getDeepcopy += "Copy()";
			else if (v.getVartype() instanceof XBean)
				getDeepcopy += "().toData()";
			else // constant
				getDeepcopy += "()";

			ps.println("		r." + v.getname() + " = b." + getDeepcopy + ";");
		}

		ps.println("		return r;");
		ps.println("	}");
		ps.println("");
	}

	void makeUpdateHelper(PrintStream ps) {
		String Name = Main.toUpper1(name);
		String output = 
				"		void update%1$s(%2$s key, %3$s value) {\r\n" +
				"			Valuetype tsc = peek(key);\r\n" +
				"			if (null != tsc) {\r\n" +
				"				tsc = tsc.shallowcopy();\r\n" +
				"				tsc.%4$s = value;\r\n" +
				"				update(key, tsc);\r\n" +
				"			}\r\n" +
				"		}\r\n" +
				"\r\n";
		output = String.format(output, Name, table.getKeyType().getBoxingName(), this.getCValuetypeName(), name);
		ps.println(output);
	}

	void makeRealGet(PrintStream ps, String pf) {
		String Name = Main.toUpper1(name);
		String deepcopy = this.isXBeanOrCBean ? "deepcopy" : "";
		ps.println(pf + "value." + name + " = " + deepcopy + "(xtable." + Name + ".get(key));");
	}

	void makeGetValuetype(PrintStream ps) {
		ps.println("		public " + this.getCValuetypeName() + " get" + Main.toUpper1(name) + "() {");
		ps.println("			return " + name + ";");
		ps.println("		}");
		ps.println("");
	}

	String getCValuetypeName() {
		return this.isXBeanOrCBean ? "Valuetype." + Main.toUpper1(name) : table.getValueType().getBoxingName();
	}

	void makeDeclareValuetype(PrintStream ps) {
		if (!this.isXBeanOrCBean)
			return; // 当表格的值类型是简单类型时，不需要定义数据结构。

		String Name = Main.toUpper1(name);
		ps.println("		public static class " + Name + " {");

		// 定义：private int i; // comment
		for (Variable v : variables) {
			ps.println("			private " + v.getVartype().getGetterName()
					+ " " + v.getname() + ";"
					+ (v.getComment().isEmpty() ? "" : (" // " + v.getComment())));
		}
		ps.println("");
		ps.println("			" + Name + "() { // hide construct");
		ps.println("			}");
		ps.println("");

		// 定义：public int getI() { return i; }
		for (Variable v : variables) {
			ps.println("			public " + v.getVartype().getGetterName()
					+ " get" + v.getName() + "() {");
			ps.println("				return " + v.getname() + ";");
			ps.println("			}");
			ps.println("");
		}
		ps.println("		}");
		ps.println("");
	}

	void compile(CCache ccache) {
		this.ccache = ccache;
		table = ccache.getXdb().getTable(name);
		if (null == table)
			throw new IllegalStateException("the table reference not exist. ccache='"
					+ ccache.getName() + "' table='" + name + "'");

		this.isXBeanOrCBean = table.getValueType() instanceof XBean || table.getValueType() instanceof CBean;

		// 表格值类型不是结构时，不能定义引用。
		if (!cvariables.isEmpty() && !isXBeanOrCBean)
			throw new IllegalStateException("the ctable '" + name
					+ "' has cvariable but the valuetype of table is not a bean.");

		for (CVariable cvar : cvariables) {
			cvar.compile(this);
		}

		if (isXBeanOrCBean && cvariables.isEmpty()) {
			if (table.getValueType() instanceof XBean)
				this.variables = ((XBean)(table.getValueType())).getVariables();
			else if (table.getValueType() instanceof CBean)
				this.variables = ((CBean)(table.getValueType())).getVariables();
		} else {
			this.variables = new ArrayList<Variable>();
			for (CVariable cvar : cvariables) {
				this.variables.add(cvar.getVariable());
			}
		}
	}

	public CTable(Element self) {
		name = self.getAttribute("name").trim().toLowerCase();
		Main.verifyName(name);

		NodeList childnodes = self.getChildNodes();
		for (int i = 0; i < childnodes.getLength(); ++i) {
			Node node = childnodes.item(i);
			if (Node.ELEMENT_NODE != node.getNodeType())
				continue;

			Element e = (Element) node;
			String nodename = e.getNodeName();

			if (nodename.equals("cvariable"))
				add(new CVariable(e));
			else
				throw new RuntimeException("node=" + nodename);
		}
	}
	
	public void add(CVariable var) {
		if (false == cvarNames.add(var.getname()))
			throw new IllegalArgumentException("duplicate cvariable: '" + var.getname() + "'@'" + name + "'");
		cvariables.add(var);
	}
}

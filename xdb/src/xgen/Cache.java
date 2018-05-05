package xgen;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xgen.Cref.TypeVariable;

public class Cache {
	private String name;
	private String key;
	private List<Cref> crefDefines = new ArrayList<Cref>();
	private Set<String> crefNames = new HashSet<String>();

	//////////////////////////////////////////////////////////
	// compiled
	private Xdb xdb;
	private Type keytype;
	private Map<String, Type> refTypes = new TreeMap<String, Type>(); // 包含所引用的所有类型
	private List<List<Cref>> crefsGroupbyTable = new ArrayList<List<Cref>>();
	// make
	private List<Integer>    crefsGroupbyTableCategory; // see makeCrefsGroupbyTableCategory()
	private Map<String, List<Cref>> listenerGroup = new TreeMap<String, List<Cref>>();

	void make() {
		makeCrefsGroupbyTableCategory();
		final String Name = Main.toUpper1(name);

		PrintStream ps = Main.fopen(xdb.getXcachedir(), Main.toUpper1(name));

		ps.println("package xcache;");
		ps.println("");
		ps.println("// 用户指定的cache名字作为最外层类的名字。cache的访问入口。");
		ps.println("public class " + Name + " {");
		ps.println("	////////////////////////////////////////////////////////////////");
		ps.println("	// define cached valuetype");
		ps.println("	public static class Valuetype {");
		ps.println("		// define valuetype");
		for (int i = 0; i < crefsGroupbyTable.size(); ++i) {
			makeDefineValuetype(ps, i);
		}
		ps.println("");

		for (int i = 0; i < crefsGroupbyTable.size(); ++i) {
			makeGetValuetype(ps, i);
		}
		ps.println("");

		ps.println("		// declare valuetype");
		for (int i = 0; i < crefsGroupbyTable.size(); ++i) {
			makeDeclareValuetype(ps, i);
		}
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
		ps.println("	public static class CacheImpl extends xdb.util.ConcurrentCache<" + keytype.getBoxingName() + ", Valuetype> {");
		ps.println("		CacheImpl() {");
		ps.println("			super(\"" + name + "\");");
		ps.println("		}");
		ps.println("");
		ps.println("		// 暂时先不公开。需要的时候在基类添加接口并调整这里代码。");
		ps.println("		// @Override");
		ps.println("		xdb.Lockey[] realGetLocks(" + keytype.getBoxingName() + " key) {");
		ps.println("			return new xdb.Lockey[]{");
		for (int i = 0; i < crefsGroupbyTable.size(); ++i) {
			final List<Cref> crefs = crefsGroupbyTable.get(i);
			final Table table = crefs.get(0).getTable(); // 至少有一个。see addGroupbyTable
			final String Tname = Main.toUpper1(table.getName());
			ps.println("					xdb.Lockeys.get(xtable." + Tname + ".getTable(), key),");
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
		for (int i = 0; i < crefsGroupbyTable.size(); ++i) {
			final List<Cref> crefs = crefsGroupbyTable.get(i);
			final int category = crefsGroupbyTableCategory.get(i).intValue();
			final Table t = crefs.get(0).getTable(); // 至少有一个。see addGroupbyTable
			final String Tn = Main.toUpper1(t.getName());
			final String dc = (category == 100) ? "" : "deepcopy";
			ps.println("				" + "value." + t.getName() + " = " + dc + "(xtable." + Tn + ".get(key));");
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
		// update helper?
		ps.println("		static CacheImpl instance = new CacheImpl();");
		ps.println("	}");
		ps.println("");
		ps.println("	////////////////////////////////////////////////////////////////////////////////");
		ps.println("	// deepcopy helper");
		for (Type refType : refTypes.values()) {
			if (refType.deepcopy4cache(ps, "	") == 1)
				ps.println("");
		}
		for (int i = 0; i < crefsGroupbyTable.size(); ++i) {
			makeDeepcopy(ps, i);
		}
		ps.println("	/////////////////////////////////////////////////////////////////////////////////");
		ps.println("	// Listener");
		// make listenerGroup
		for (Cref cref : crefDefines) {
			final String listenerName = cref.listenerName();
			List<Cref> crefs = listenerGroup.get(listenerName);
			if (null == crefs)
				listenerGroup.put(listenerName, crefs = new ArrayList<Cref>());
			crefs.add(cref);
		}
		ps.println("	static void registryTableListener() {");
		for (List<Cref> crefs : listenerGroup.values()) {
			Cref cref = crefs.get(0); // 当listener的名字相同时，随便取一个作为注册参数构造者。
			String out = String.format("xtable.%1$s.getTable().addListener(%2$s.instance, \"value\"%3$s);",
					Main.toUpper1(cref.getTable().getName()), cref.listenerName(), cref.listenerArgs());
			ps.println("		" + out);
		}
		ps.println("	}");
		ps.println("");
		ps.println("	static void unregistryTableListener() {");
		for (List<Cref> crefs : listenerGroup.values()) {
			Cref cref = crefs.get(0); // 当listener的名字相同时，随便取一个作为注册参数构造者。
			String out = String.format("xtable.%1$s.getTable().removeListener(%2$s.instance, \"value\"%3$s);",
					Main.toUpper1(cref.getTable().getName()), cref.listenerName(), cref.listenerArgs());
			ps.println("		" + out);
		}
		ps.println("	}");
		ps.println("");
		for (List<Cref> crefs : listenerGroup.values()) {
			makeTableListener(ps, crefs);
		}
		ps.println("}");

		ps.close();
	}

	void makeTableListener(PrintStream ps, List<Cref> crefs) {
		final String lName = crefs.get(0).listenerName();
		final String kName = keytype.getBoxingName();
		final Table table = crefs.get(0).getTable();
		final String tName = table.getName();
		final String TName = Main.toUpper1(tName);
		final String vName = table.getValueType().getBoxingName();

		ps.println("	static class " + lName + " implements xdb.logs.Listener {");
		ps.println("		@Override");
		ps.println("		public void onChanged(Object _key) {");
		ps.println("			final " + kName + " key = (" + kName + ")_key;");
		ps.println("			final Valuetype cv = CacheImpl.instance.get(key);");
		ps.println("			if (null == cv)");
		ps.println("				return;");
		ps.println("			final " + vName + " value = xtable." + TName + ".get(key);");
		ps.println("			// 增加或者覆盖记录，更新所有提取的数据");
		{
			switch (table.getValueType().deepcopy4cache(null, "")) {
			case 1:
				ps.println("			cv." + tName + "  = deepcopy(value);");
				break;
			case 0:
				ps.println("			cv." + tName + "  = value;");
				break;
			default:
				throw new RuntimeException("unknown deepcopy type! cache=" + name + " lName=" + lName);
			}
		}
		ps.println("		}");
		ps.println("");
		ps.println("		@Override");
		ps.println("		public void onChanged(Object _key, String fullVarName, xdb.logs.Note note) {");
		ps.println("			final " + kName + " key = (" + kName + ")_key;");
		ps.println("			final Valuetype cv = CacheImpl.instance.get(key);");
		ps.println("			if (null == cv)");
		ps.println("				return;");
		ps.println("			final " + vName + " value = xtable." + TName + ".get(key);");
		switch (getCategoryByTableName(tName)) {
		case 100:
			ps.println("			cv." + tName + "  = value;");
			break;
		case 101: case 102:
			ps.println("			cv." + tName + "  = deepcopy(value);");
			break;
		default:
			for (Cref cref : crefs) { // 更新此listener关注的相关数据
				makeDeepcopy(ps, cref, "cv." + tName, "value", "			");
			}
			break;
		}
		ps.println("		}");
		ps.println("");
		ps.println("		@Override");
		ps.println("		public void onRemoved(Object _key) {");
		ps.println("			final " + kName + " key = (" + kName + ")_key;");
		ps.println("			final Valuetype cv = CacheImpl.instance.get(key);");
		ps.println("			if (null == cv)");
		ps.println("				return;");
		ps.println("			cv." + tName + " = null;");
		ps.println("		}");
		ps.println("");
		ps.println("		static " + lName + " instance = new " + lName + "();");
		ps.println("	}");
		ps.println("");
	}

	int getCategoryByTableName(String tName) {
		for (int i = 0; i < crefsGroupbyTable.size(); ++i) {
			if (crefsGroupbyTable.get(i).get(0).getTable().getName().equals(tName))
				return crefsGroupbyTableCategory.get(i);
		}
		throw new RuntimeException("getCategoryByTableName not found. cache=" + name + " table=" + tName);
	}

	void makeDeepcopy(PrintStream ps, int index) {
		final int category = crefsGroupbyTableCategory.get(index).intValue();
		if (100 == category)
			return; // 简单类型不需要deepcopy

		final List<Cref> crefs = crefsGroupbyTable.get(index);
		Table table = crefs.get(0).getTable(); // 至少有一个。see addGroupbyTable
		final String Name = Main.toUpper1(table.getName());

		String originValueTypeName = table.getValueType().getBoxingName();

		ps.println("	public static Valuetype." + Name + " deepcopy(" + originValueTypeName + " b) {");
		ps.println("		if (null == b) return null;");
		ps.println("");
		ps.println("		Valuetype." + Name + " r = new Valuetype." + Name + "();");
		switch (category) {
		case 101:
			makeDeepcopy(ps, ((XBean)(table.getValueType())).getVariables(), "		");
			break;
		case 102:
			makeDeepcopy(ps, ((CBean)(table.getValueType())).getVariables(), "		");
			break;
		default: // 111 and 211
			for (Cref cref : crefs) {
				makeDeepcopy(ps, cref, "r", "b", "		");
			}
			break;
		}

		ps.println("		return r;");
		ps.println("	}");
		ps.println("");
	}

	void makeDeepcopy(PrintStream ps, Cref cref, String left, String right, String pf) {
		switch (cref.getLastVariableType().deepcopy4cache(null, "")) {
		case 1:
			ps.println(pf + cref.getLeftName(left) + " = deepcopy(" + cref.getRightName(right) + ");");
			break;
		case 0:
			ps.println(pf + cref.getLeftName(left) + " = " + cref.getRightName(right) + ";");
			break;
		case -1:
			ps.println(pf + cref.getLeftName(left) + " = " + cref.getRightName(right, true) + ";");
			break;
		default:
			throw new RuntimeException("unknown deepcopy type! cache=" + name);
		}
	}

	void makeDeepcopy(PrintStream ps, List<Variable> variables, String pf) {
		for (Variable v : variables) {
			switch (v.getVartype().deepcopy4cache(null, "")) {
			case 1:
				ps.println(pf + "r." + v.getname() + " = deepcopy(b.get" + v.getName() + "());");
				break;
			case 0:
				ps.println(pf + "r." + v.getname() + " = b.get" + v.getName() + "();");
				break;
			case -1: // TypeBinary
				ps.println(pf + "r." + v.getname() + " = b.get" + v.getName() + "Copy();");
				break;
			default:
				throw new RuntimeException("unknown deepcopy type! cache=" + name);
			}
		}
	}

	void makeGetValuetype(PrintStream ps, int index) {
		final List<Cref> crefs = crefsGroupbyTable.get(index);
		final int category = crefsGroupbyTableCategory.get(index).intValue();
		Table table = crefs.get(0).getTable(); // 至少有一个。see addGroupbyTable
		final String Name = Main.toUpper1(table.getName());

		switch (category) { // see makeCrefsGroupbyTableCategory
		case 100:
			ps.println("		public " + table.getValueType().getBoxingName() + " get" + Name + "() {");
			break;
		default: // 101,102,111,211
			ps.println("		public " + Name + " get" + Name + "() {");
			break;
		}
		ps.println("			return " + table.getName() + ";");
		ps.println("		}");
		ps.println("");
	}

	void makeDefineValuetype(PrintStream ps, int index) {
		final List<Cref> crefs = crefsGroupbyTable.get(index);
		final int category = crefsGroupbyTableCategory.get(index).intValue();
		Table table = crefs.get(0).getTable(); // 至少有一个。see addGroupbyTable

		switch (category) { // see makeCrefsGroupbyTableCategory
		case 100:
			ps.println("		private volatile " + table.getValueType().getBoxingName() + " " + table.getName() + ";");
			break;
		default: // 101,102,111,211
			ps.println("		private volatile " + Main.toUpper1(table.getName()) + " " + table.getName() + ";");
			break;
		}
	}

	void makeDeclareValuetype(PrintStream ps, int index) {
		final List<Cref> crefs = crefsGroupbyTable.get(index);
		final int category = crefsGroupbyTableCategory.get(index).intValue();
		Table table = crefs.get(0).getTable(); // 至少有一个。see addGroupbyTable

		switch (category) { // see makeCrefsGroupbyTableCategory
		case 100: // 简单类型，不需要生成结构。
			break;
		case 101:
			declare10N(ps, table, ((XBean)(table.getValueType())).getVariables(), "		");
			break;
		case 102:
			declare10N(ps, table, ((CBean)(table.getValueType())).getVariables(), "		");
			break;
		default: // 111 and 211
			declareN11(ps, table, crefs, "		");
			break;
		}
	}

	void declareN11(PrintStream ps, Table table, List<Cref> crefs, String pf) {
		TypeVariable root = new TypeVariable(null, table.getName());
		for (Cref cref : crefs) {
			buildTree(root, cref, 0);
		}
		print(ps, root, "		");
	}

	void print(PrintStream ps, TypeVariable parent, String pf) {
		if (parent.child == null)
			return;
		ps.println(pf + "public static class " + Main.toUpper1(parent.variable) + " {");
		ps.println(pf + "	" + Main.toUpper1(parent.variable) + "() {");
		ps.println(pf + "	}");
		ps.println("");
		for (TypeVariable sibling = parent.child; sibling != null; sibling = sibling.sibling) {
			final String NN = Main.toUpper1(sibling.variable);
			if (sibling.child != null) { // 中间层次的变量需要在构造的时候初始化。
				ps.println(pf + "	private " + NN + " " + sibling.variable + " = new " + NN + "();");
			} else {
				ps.println(pf + "	private volatile " + sibling.type.getGetterName() + " " + sibling.variable + ";");
			}
		}
		ps.println("");
		for (TypeVariable sibling = parent.child; sibling != null; sibling = sibling.sibling) {
			print(ps, sibling, pf + "	");
		}
		for (TypeVariable sibling = parent.child; sibling != null; sibling = sibling.sibling) {
			final String NN = Main.toUpper1(sibling.variable);
			if (sibling.child != null) { // 中间层次返回类型不同。
				ps.println(pf + "	public " + NN + " get" + NN + "() {");
			} else {
				ps.println(pf + "	public " + sibling.type.getGetterName() + " get" + NN + "() {");
			}
			ps.println(pf + "		return " + sibling.variable + ";");
			ps.println(pf + "	}");
			ps.println("");
		}
		ps.println(pf + "}");
		ps.println("");
	}

	void buildTree(TypeVariable parent, Cref cref, int index) {
		TypeVariable cur = cref.getTypeVariable(index);
		if (null == cur)
			return; // 全部处理完成
		TypeVariable siblingLast = null;
		for (TypeVariable sibling = parent.child; sibling != null; sibling = sibling.sibling) {
			if (sibling.variable.equals(cur.variable)) {
				buildTree(sibling, cref, index+1);
				return;
			}
			siblingLast = sibling;
		}
		if (null == siblingLast)
			parent.child = cur;
		else
			siblingLast.sibling = cur;
		buildTree(cur, cref, index+1);
	}

	void declare10N(PrintStream ps, Table table, List<Variable> variables, String pf) {
		final String Name = Main.toUpper1(table.getName());
		ps.println(pf + "public static class " + Name + " {");
		final String pf2 = pf + "	";
		for (Variable v : variables) {
			ps.println(pf2 + "private volatile " + v.getVartype().getGetterName() + " " + v.getname() + ";");
		}
		ps.println("");
		ps.println(pf + "	" + Name + "() {");
		ps.println(pf + "	}");
		ps.println("");
		for (Variable v : variables) {
			ps.println(pf2 + "public " + v.getVartype().getGetterName() + " get" + v.getName() + "() {");
			ps.println(pf2 + "	return " + v.getname() + ";");
			ps.println(pf2 + "}");
			ps.println("");
		}
		ps.println(pf + "}");
		ps.println("");
	}

	void makeCrefsGroupbyTableCategory() {
		// crefsGroupbyTableCategory 和 crefsGroupbyTable意义对应。
		crefsGroupbyTableCategory = new ArrayList<Integer>();
		/**
		 * 以下数字2表示大于等于2。
		 * Category crefs.size() typeVariableList  table.valueType
		 *   100    1            0                 0 简单类型，Integer,String等 
		 *   101    1            0                 1 xbean，提取整个bean
		 *   102    1            0                 2 cbean，提取整个bean
		 *   111    1            >=1               1 bean 单层或多层
		 *   200    >=2          has(0)            - 配置错误 。提取整个和提取部分一起出现。
		 *   211    >=2          >=1               1 提取指定字段，可能包含多层。
		 */
		crefDefines.get(0).getTable().getValueType();
		for (List<Cref> crefs : crefsGroupbyTable) {
			switch (crefs.size()) {
			case 0:
				throw new RuntimeException("impossible! crefs.isEmpty()! ccache" + name);
			case 1:
				if (0 == crefs.get(0).getTypeVariableList().size()) {
					if (crefs.get(0).getTable().getValueType() instanceof XBean)
						crefsGroupbyTableCategory.add(101);
					else if (crefs.get(0).getTable().getValueType() instanceof CBean)
						crefsGroupbyTableCategory.add(102);
					else
						crefsGroupbyTableCategory.add(100);
				} else {
					crefsGroupbyTableCategory.add(111);
				}
				break;
			default: // >= 2
				for (Cref cref : crefs) {
					if (cref.getTypeVariableList().isEmpty())
						throw new RuntimeException("all or part, but not both! cache=" + name);
				}
				crefsGroupbyTableCategory.add(211);
				break;
			}
		}
	}

	void compile(Xdb xdb) {
		this.xdb = xdb;
		for (Cref cref : crefDefines) {
			cref.compile(this);
		}
		if (crefDefines.isEmpty())
			throw new RuntimeException("no reference! cache=" + name);
	
		// validate table keytype
		for (Cref cref : crefDefines) {
			if (false == cref.getTable().getKeyType().getName().equals(this.key))
				throw new IllegalStateException("cache : different table-keytype. cache='"
						+ name + "' table='" + cref.getTable().getName());
		}
		this.keytype = crefDefines.get(0).getTable().getKeyType();

		for (Cref cref : crefDefines) {
			if (cref.getTypeVariableList().isEmpty()) {
				// 提取整个bean。
				if (cref.getTable().getValueType() instanceof CBean) {
					for (Variable v : ((CBean)(cref.getTable().getValueType())).getVariables()) {
						refTypes.put(v.getVartype().getGetterName(), v.getVartype());
					}
				} else if (cref.getTable().getValueType() instanceof XBean) {
					for (Variable v : ((XBean)(cref.getTable().getValueType())).getVariables()) {
						refTypes.put(v.getVartype().getGetterName(), v.getVartype());
					}
				}
				// else 是简单类型，不用加入到引用类型中。加入也不会影响生成结果。
			}
			else {
				// 获取提取变量的类型，肯定是最后一个。
				Type type = cref.getTypeVariable(cref.getTypeVariableList().size() - 1).type;
				refTypes.put(type.getGetterName(), type);
			}
		}
	}

	public Xdb getXdb() {
		return xdb;
	}

	public String getName() {
		return name;
	}

	/**
	 * 根据表从分类表中查找引用的数组。
	 * @param table
	 * @return null 如果没有找到
	 */
	List<Cref> getCrefsbyTable(Table table) {
		for (List<Cref> crefs : crefsGroupbyTable) {
			if (crefs.get(0).getTable() == table) // 表中至少有一项。see addGroupbyTable
				return crefs;
		}
		return null;
	}

	void addGroupbyTable(Cref cref) {
		List<Cref> crefs = getCrefsbyTable(cref.getTable());
		if (null == crefs)
			crefsGroupbyTable.add(crefs = new ArrayList<Cref>());
		crefs.add(cref);
	}

	void add(Cref cref) {
		if (false == crefNames.add(cref.getRef()))
			throw new RuntimeException("duplicate car name. cache=" + name + " car=" + cref.getRef());
		crefDefines.add(cref);
	}

	Cache(Element self) {
		name = self.getAttribute("name").trim().toLowerCase();
		Main.verifyName(name);
		if (name.isEmpty())
			throw new RuntimeException("cache name can not be empty.");

		key = self.getAttribute("key");
		if (key.isEmpty())
			throw new RuntimeException("cache key can not be empty. cache=" + name);

		NodeList childnodes = self.getChildNodes();
		for (int i = 0; i < childnodes.getLength(); ++i) {
			Node node = childnodes.item(i);
			if (Node.ELEMENT_NODE != node.getNodeType())
				continue;

			Element e = (Element) node;
			String nodename = e.getNodeName();

			if (nodename.equals("cref"))
				add(new Cref(e));
			else
				throw new RuntimeException("node=" + nodename);
		}
	}

	static void make_ConcurrentCaches_(Xdb xdb, List<Cache> caches) {
		PrintStream ps = Main.fopen(xdb.getXcachedir(), "_ConcurrentCaches_");

		ps.println("package xcache;");
		ps.println("");
		ps.println("public class _ConcurrentCaches_ extends xdb.util.ConcurrentCaches {");
		ps.println("	public _ConcurrentCaches_() {");
		ps.println("	}");
		ps.println("");
		ps.println("	@Override");
		ps.println("	protected void onStart() {");
		for (Cache ccache : caches) {
			ps.println("		super.add(" + Main.toUpper1(ccache.getName()) + ".getCache());");
		}
		ps.println("");
		for (Cache ccache : caches) {
			ps.println("		" + Main.toUpper1(ccache.getName()) + ".registryTableListener();");
		}
		ps.println("	}");
		ps.println("");
		ps.println("	@Override");
		ps.println("	protected void onStop() {");
		for (Cache ccache : caches) {
			ps.println("		" + Main.toUpper1(ccache.getName()) + ".unregistryTableListener();");
		}
		ps.println("	}");
		ps.println("");
		ps.println("}");
		ps.println("");

		ps.close();
	}
}

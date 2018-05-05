package xgen;

import java.io.PrintStream;
import org.w3c.dom.Element;
import java.util.*;

public class Table {
	private String name;
	private String lock;
	private String key;
	private String value;
	private boolean autoIncrement;
	private boolean isMemory;
	private String foreign;
	private String capacity;

	public String getName() {
		return name;
	}

	public String getLock() {
		return lock;		
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

	Table(Element self) {
		name = self.getAttribute("name").trim();
		if (!name.toLowerCase().equals(name))
			throw new RuntimeException("tablename MUSTBE lowercase");
		Main.verifyName(name);

		Define define = Define.getInstance();

		lock = self.getAttribute("lock");
		if (lock == null)
			throw new RuntimeException("not found lock in table '" + name + "'");
		else if (lock.equals(""))
			lock = name;
		else if (define.isParsed() && !Define.getInstance().isLockname(lock))
			throw new RuntimeException("undefined lockname '" + lock + "' in table '" + name + "'");

		if (define.isParsed())
		{
			if (self.hasAttribute("cachelow"))
				throw new RuntimeException("cachelow is not allowed for defined xdb in table '" + name + "'");
			if (self.hasAttribute("cachehigh"))
				throw new RuntimeException("cachehigh is not allowed for defined xdb in table '" + name + "'");
			String cachecap = self.getAttribute("cacheCapacity");
			if (cachecap != null && define.getCacheCap(cachecap) == null)
				throw new RuntimeException("undefined cacheCapacity '" + cachecap + "' in table '" + name + "'");
			String cachepage = self.getAttribute("cachePage");
			if (cachepage != null && define.getCachePage(cachepage) == null)
				throw new RuntimeException("undefined cachePage '" + cachepage + "' in table '" + name + "'");
		}

		key = self.getAttribute("key").trim();
		value = self.getAttribute("value").trim();
		isMemory = self.getAttribute("persistence").equalsIgnoreCase("MEMORY");

		autoIncrement = self.getAttribute("autoIncrement").equals("true");
		foreign = self.getAttribute("foreign").trim();
		capacity = self.getAttribute("capacity").trim();
	}

	private Type keytype;
	private Type valuetype;

	public Type getKeyType() {
		return keytype;
	}

	public Type getValueType() {
		return valuetype;
	}
	
	void compile(Xdb xdb) {
		keytype = Type.compile(key, null, null);
		if (!keytype.isConstant())
			throw new RuntimeException("table.key need a constant valuetype");
		if (autoIncrement && !keytype.supportAutoIncrement())
			throw new RuntimeException("type of '" + key + "' unsupport autoIncrement");
		valuetype = Type.compile(value, null, null);
		if (!(valuetype instanceof XBean) && !valuetype.isConstant())
			throw new RuntimeException("table-valuetype MUST be constant or xbean. table=" + name);
	}

	void verify(Xdb xdb) {
		// 验证foreign表存在以及变量类型和表的key类型一致。
		if (!foreign.isEmpty()) {
			// Foreign = tableName | key:tableName;value:tableName.
			Foreign tableForeign = new Foreign(foreign, "table=" + getName());
			if (tableForeign.getKey() != null)
				keytype.verifyForeign(xdb, new Foreign(tableForeign, tableForeign.getKey()));
			if (tableForeign.getValue() != null)
				valuetype.verifyForeign(xdb, new Foreign(tableForeign, tableForeign.getValue()));
		}
		
		Capacity cap = new Capacity(capacity, "table." + this.getName());
		this.keytype.verifyCapacity(xdb, cap.extractKey());
		this.valuetype.verifyCapacity(xdb, cap.extractValue());
		cap.warnIf(null != cap.getCapacity(), "capacity is not required.");

		// setOwner
		this.keytype.addOwnerTable(this);
		this.valuetype.addOwnerTable(this);
	}

	/**
	 * not verify. be used after verify.
	 * @return
	 */
	Set<String> getForeigns() {
		Set<String> foreigns = new HashSet<String>();
		Foreign f = new Foreign(foreign, "");
		if (f.getKey() != null)
			foreigns.add(f.getKey());
		if (f.getValue() != null)
			foreigns.add(f.getValue());

		Set<Type> types = new HashSet<Type>();
		keytype.depends(types);
		valuetype.depends(types);
		for (Type type : types) {
			if (type instanceof CBean) {
				((CBean)type).collectForeigns(foreigns);
			} else if (type instanceof XBean) {
				((XBean)type).collectForeigns(foreigns);
			}
		}
		return foreigns;
	}
	
	boolean isNeedExplicitCheck() {
		return Main.explicitLockCheck && !Main.explicitLockIgnoreTables.contains(name);
	}

	void make() {
		if (valuetype.isAny() && !isMemory)
			throw new RuntimeException("'" + value + "@" + name + "(isAny() && !isMemory)");

		String classname = Main.toUpper1(name);
		PrintStream ps = Main.openTableFile(classname);

		String K = keytype.getBoxingName();
		String V = valuetype.getBoxingName();
		String KV = "<" + K + ", " + V + ">";

		//  开放给应用的接口.
		ps.println("package xtable;");
		ps.println("");
		ps.println("// typed table access point");
		ps.println("public class " + classname + " {");
		ps.println("	" + classname + "() {");
		ps.println("	}");
		ps.println("");
		
		// explicitLockCheck
		if (isNeedExplicitCheck()) {
			ps.println("	private static void _explicitLockCheck(" + K + " key) {");
			ps.println("		if (!_Tables_.isExplicitLockCheck) return;");
			ps.println("		xdb.Lockey lockey = xdb.Lockeys.get(_Tables_.getInstance()." + name + ", key);");
			ps.println("		if (!lockey.isHeldByCurrentThread()) {");
			ps.println("			throw new IllegalStateException(\"check ExplicitLock Exception. lockey:[\" + lockey.toString());");
			ps.println("		}");
			ps.println("	}");
			ps.println("");
		}
		
		if (this.autoIncrement) {
			ps.println("	public static xdb.util.AutoKey<" + K + "> getAutoKey() {");
			ps.println("		return _Tables_.getInstance()." + name + ".getAutoKey();");
			ps.println("	}");
			ps.println("");
			ps.println("	public static " + K + " nextKey() {");
			ps.println("		return getAutoKey().next();");
			ps.println("	}");
			ps.println("");
			ps.println("	public static " + K + " insert(" + V + " value) {");
			ps.println("		Long next = nextKey();");
			ps.println("		insert(next, value);");
			ps.println("		return next;");
			ps.println("	}");
			ps.println("");
		}
		// get
		ps.println("	public static " + V + " get(" + K + " key) {");
		if (isNeedExplicitCheck()) {
			ps.println("		_explicitLockCheck(key);");
		}
		ps.println("		return _Tables_.getInstance()." + name + ".get(key);");
		ps.println("	}");
		ps.println("");
		ps.println("	public static " + V + " get(" + K + " key, " + V + " value) {");
		if (isNeedExplicitCheck()) {
			ps.println("		_explicitLockCheck(key);");
		}
		ps.println("		return _Tables_.getInstance()." + name + ".get(key, value);");
		ps.println("	}");
		ps.println("");
		// insert delete update put putIfAbsent
		ps.println("	public static void insert(" + K + " key, " + V + " value) {");
		ps.println("		_Tables_.getInstance()." + name + ".insert(key, value);");
		ps.println("	}");
		ps.println("");
		ps.println("	public static void delete(" + K + " key) {");
		if (isNeedExplicitCheck()) {
			ps.println("		_explicitLockCheck(key);");
		}
		ps.println("		_Tables_.getInstance()." + name + ".delete(key);");
		ps.println("	}");
		ps.println("");
//		ps.println("	public static boolean update(" + K + " key, " + V + " value) {");
//		ps.println("		return _Tables_.getInstance()." + name + ".update(key, value);");
//		ps.println("	}");
//		ps.println("");
//		ps.println("	public static " + V + " put(" + K + " key, " + V + " value) {");
//		ps.println("		return _Tables_.getInstance()." + name + ".put(key, value);");
//		ps.println("	}");
//		ps.println("");
//		ps.println("	public static " + V + " putIfAbsent(" + K + " key, " + V + " value) {");
//		ps.println("		return _Tables_.getInstance()." + name + ".putIfAbsent(key, value);");
//		ps.println("	}");
//		ps.println("");
		ps.println("	public static boolean add(" + K + " key, " + V + " value) {");
		if (isNeedExplicitCheck()) {
			ps.println("		boolean ret = _Tables_.getInstance()." + name + ".add(key, value);");
			ps.println("		if (!ret) {");
			ps.println("			_explicitLockCheck(key);");
			ps.println("		}");
			ps.println("		return ret;");
		} else {
			ps.println("		return _Tables_.getInstance()." + name + ".add(key, value);");
		}
		ps.println("	}");
		ps.println("");
		// remove
		ps.println("	public static boolean remove(" + K + " key) {");
		if (isNeedExplicitCheck()) {
			ps.println("		_explicitLockCheck(key);");
		}
		ps.println("		return _Tables_.getInstance()." + name + ".remove(key);");
		ps.println("	}");
		ps.println("");
		// cache
		ps.println("	public static xdb.TTableCache" + KV + " getCache() {");
		ps.println("		return _Tables_.getInstance()." + name + ".getCache();");
		ps.println("	}");
		ps.println("");
		ps.println("	public static xdb.TTable" + KV + " getTable() {");
		ps.println("		return _Tables_.getInstance()." + name + ";");
		ps.println("	}");
		ps.println("");
		valuetype.printTableSelectMethod(ps, K);
		ps.println("}");

		ps.close();
	}

	void define(PrintStream ps) {
		String k = keytype.getBoxingName();
		String v = valuetype.getBoxingName();
		String t = "xdb.TTable<" + k + ", " + v + ">";

		ps.println("	" + t + " " + name + " = new " + t + "() {");
		ps.println("		@Override");
		ps.println("		public String getName() {");
		ps.println("			return " + Main.quote(name) + ";");
		ps.println("		}");
		ps.println("");

		if (this.autoIncrement) {
			ps.println("		@Override");
			ps.println("		protected xdb.util.AutoKey<" + k + "> bindAutoKey() {");
			ps.println("			return getInstance().getTableSys().getAutoKeys().getAutoKey" + k + "(getName());");
			ps.println("		}");
			ps.println("");
		}

		ps.println("		@Override");
		ps.println("		public OctetsStream marshalKey(" + k + " key) {");
		ps.println("			OctetsStream _os_ = new OctetsStream();");
		keytype.marshal(null, null, ps, "			", "key");
		ps.println("			return _os_;");
		ps.println("		}");
		ps.println("");
		ps.println("		@Override");
		ps.println("		public OctetsStream marshalValue(" + v + " value) {");
		ps.println("			OctetsStream _os_ = new OctetsStream();");
		valuetype.marshal(null, null, ps, "			", "value");
		ps.println("			return _os_;");
		ps.println("		}");
		ps.println("");

		ps.println("		@Override");
		ps.println("		public " + k + " unmarshalKey(OctetsStream _os_) throws MarshalException {");
		ps.println("			" + keytype.defineNoParent("key"));
		keytype.unmarshal(null, null, ps, "			", "key");
		ps.println("			return key;");
		ps.println("		}");
		ps.println("");

		ps.println("		@Override");
		ps.println("		public " + v + " unmarshalValue(OctetsStream _os_) throws MarshalException {");
		ps.println("			" + valuetype.defineNoParent("value"));
		valuetype.unmarshal(null, null, ps, "			", "value");
		ps.println("			return value;");
		ps.println("		}");
		ps.println("");
		ps.println("		@Override");
		ps.println("		public " + v + " newValue() {");
		ps.println("			" + valuetype.defineNoParent("value"));
		ps.println("			return value;");
		ps.println("		}");
		ps.println("");
		ps.println("	};");
		ps.println("");
	}

	static void make(Collection<Table> tables) {
		String classname = "_Tables_";
		PrintStream ps = Main.openTableFile(classname);

		ps.println("package xtable;");
		ps.println("");
		if (!tables.isEmpty()) {
			ps.println("import com.goldhuman.Common.Marshal.OctetsStream;");
			ps.println("import com.goldhuman.Common.Marshal.MarshalException;");
		}
		ps.println("");
		ps.println("public class _Tables_ extends xdb.Tables {");
		ps.println("	static volatile boolean isExplicitLockCheck = false;");
		ps.println("");
		ps.println("	public static void startExplicitLockCheck() {");
		ps.println("		isExplicitLockCheck = true;");
		ps.println("	}");
		ps.println("");
		ps.println("	public static _Tables_ getInstance() {");
		ps.println("		return (_Tables_)xdb.Xdb.getInstance().getTables();");
		ps.println("	}");
		ps.println("");
		ps.println("	public _Tables_() {");
		for (Table table : tables)
		ps.println("		add(" + table.getName() + ");");
		ps.println("	}");
		ps.println("");
		ps.println("	// visible in package");
		for (Table table : tables) table.define(ps);
		ps.println("");
		ps.println("}");

		ps.close();
	}

	public void printMeta(PrintStream ps, String prefix) {
		ps.println(prefix + "super.addTable(" + Main.quote(getName()) + ", "
				+ Main.quote(this.isMemory ? "MEMORY" : "DB")
				+ ", " + Main.quote(this.key) + ", " + this.autoIncrement
				+ ", " + Main.quote(this.value)
				+ ", " + Main.quote(this.foreign)
				+ ", " + Main.quote(this.capacity)
				+ ");");
	}
	//-add by cjc 2009-9-14 for depends
	public void depends(Set<Type> types)
	{
		this.getValueType().depends(types);
		//cbean
		this.getKeyType().depends(types);
	}
	
	public boolean isMemory()
	{
		return isMemory;
	}
}

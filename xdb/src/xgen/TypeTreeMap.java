package xgen;

import java.io.PrintStream;
import java.util.Map;
import java.util.Set;

public class TypeTreeMap extends TypeBaseMap {
	@Override
	public void addOwnerTable(Table owner) {
		super.addOwnerTable(owner);
		this.keytype.addOwnerTable(owner);
		this.valuetype.addOwnerTable(owner);
	}

	@Override
	public void verifyForeign(Xdb xdb, Foreign foreign) {
		if (null != foreign.getKey()) {
			Table table = xdb.getTable(foreign.getKey());
			foreign.throwIf(null == table, "[treemap.key] table not exist");
			foreign.throwIf(table.isMemory(), "[treemap.key] foreign table is memory");
			foreign.throwIf(table.getKeyType() != this.keytype, "[treemap.key] type not match.");
		}
		if (null != foreign.getValue()) {
			Table table = xdb.getTable(foreign.getValue());
			foreign.throwIf(null == table, "[treemap.value] table not exist");
			foreign.throwIf(table.isMemory(), "[treemap.value] foreign table is memory");
			foreign.throwIf(table.getKeyType() != this.valuetype, "[treemap.value] type not match");
		}
	}

	@Override
	public void verifyCapacity(Xdb xdb, Capacity capacity) {
		capacity.capacityNeed();
		keytype.verifyCapacity(xdb, capacity.extractKey());
		valuetype.verifyCapacity(xdb, capacity.extractValue());
	}

	TypeTreeMap(Map<String, Type> types) {
		types.put(getName(), this);
	}

	@Override
	public Type compile(String key, String value) {
		return new TypeTreeMap(key, value);
	}

	private TypeTreeMap(String key, String value) {
		keytype = Type.compile(key, null, null);
		if (!keytype.isConstant())
			throw new RuntimeException("map.key need a constant valuetype");
		valuetype = Type.compile(value, null, null);
	}

	@Override
	public void construct(XBean bean, Variable var, PrintStream ps, String prefix) {
		String c = "";
		if (!var.getComparator().isEmpty())
			c = "new " + var.getComparator() + "()";
		ps.println(prefix + var.getname() + " = new " + getTypeName() + "(" + c + ");");
	}

	@Override
	public void print4reset(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + var.getname() + ".clear();");
	}

	@Override
	public String defineNoParent(String varname) {
		return getTypeName() + " " + varname + " = new " + getTypeName() + "();";
	}

	@Override
	public String defineSetParent(XBean bean, Variable var, String definename) {
		return defineNoParent(definename);
	}

	@Override
	public String getBoxingName() {
		return getGetterName();
	}

	@Override
	public String getName() {
		return "treemap";
	}

	@Override
	public String getTypeName() {
		return "java.util.TreeMap" + KV();
	}

	@Override
	public String getGetterName() {
		return "java.util.NavigableMap" + KV();
	}

	@Override
	public void getter(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return xdb.Logs.logNavigableMap(new xdb.LogKey(this, "
				+ Main.quote(var.getname()) + "), " + var.getname() + ");");
		ps.println(prefix + "}");
		ps.println("");
		
		if (!isAny() && !bean.isData()) {
			ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "AsData() { // " + var.getComment());
			Main._xdb_verify_(ps, prefix + "	");
			ps.println(prefix + "	" + getGetterName() + " " + var.getname() + ";");
			ps.println(prefix + "	" + bean.getName() + " _o_ = this;");
			deepCopy(true, var, ps, prefix + "	");
			ps.println(prefix  + "	" + "return " + var.getname() + ";");
			ps.println(prefix + "}");
			ps.println();
		}
	}

	@Override
	public void getterConst(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return xdb.Consts.constNavigableMap(" + var.getname() + ");");
		ps.println(prefix + "}");
		ps.println("");
		
		if (!isAny()) {
			ps.println(prefix + "@Override");
			ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "AsData() { // " + var.getComment());
			Main._xdb_verify_(ps, prefix + "	");
			ps.println(prefix + "	" + getGetterName() + " " + var.getname() + ";");
			ps.println(prefix + "	" + bean.getName() + " _o_ = " + bean.getName() + ".this;");
			deepCopy(true, var, ps, prefix + "	");
			ps.println(prefix  + "	" + "return " + var.getname() + ";");
			ps.println(prefix + "}");
			ps.println("");
		}
	}	

	@Override
	public String hashCode(String varname) {
		return varname + ".hashCode()";
	}
	
	@Override
	public void marshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		ps.println(prefix + "_os_.compact_uint32(" + localvarname + ".size());");
		ps.println(prefix + "for (java.util.Map.Entry" + KV() + " _e_ : " + localvarname + ".entrySet())");
		ps.println(prefix + "{");
		keytype.marshal(bean, var, ps, prefix + "	", "_e_.getKey()");
		valuetype.marshal(bean, var, ps, prefix + "	", "_e_.getValue()");
		ps.println(prefix + "}");
	}

	@Override
	public String newListenable(XBean bean, Variable var) {
		return "new xdb.logs.ListenableMap().setVarName(" + Main.quote(var.getname()) + ")";
	}

	@Override
	public String notEquals(String varname) {
		return "!" + varname + ".equals(_o_." + varname + ")";
	}
	

	@Override
	public void unmarshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		ps.println(prefix + "for (int size = _os_.uncompact_uint32(); size > 0; --size)");
		ps.println(prefix + "{");
		ps.println(prefix + "	" + keytype.defineSetParent(bean, var, "_k_"));
		keytype.unmarshal(bean, var, ps, prefix + "	", "_k_");
		ps.println(prefix + "	" + valuetype.defineSetParent(bean, var, "_v_"));
		valuetype.unmarshal(bean, var, ps, prefix + "	", "_v_");
		ps.println(prefix + "	" + localvarname + ".put(_k_, _v_);");
		ps.println(prefix + "}");
	}

	@Override
	public void depends(Set<Type> types) {
		if (types.add(this)) {
			keytype.depends(types);
			valuetype.depends(types);
		}
		
	}

}

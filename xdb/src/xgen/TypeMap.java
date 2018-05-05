package xgen;

import java.util.Map;
import java.util.Set;
import java.io.PrintStream;

public class TypeMap extends TypeBaseMap {
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
			foreign.throwIf(null == table, "[map.key] table not exist");
			foreign.throwIf(table.isMemory(), "[map.key] foreign table is memory");
			foreign.throwIf(table.getKeyType() != this.keytype, "[map.key] type not match.");
		}
		if (null != foreign.getValue()) {
			Table table = xdb.getTable(foreign.getValue());
			foreign.throwIf(null == table, "[map.value] table not exist");
			foreign.throwIf(table.isMemory(), "[map.value] foreign table is memory");
			foreign.throwIf(table.getKeyType() != this.valuetype, "[map.value] type not match");
		}
	}

	@Override
	public void verifyCapacity(Xdb xdb, Capacity capacity) {
		capacity.capacityNeed();
		keytype.verifyCapacity(xdb, capacity.extractKey());
		valuetype.verifyCapacity(xdb, capacity.extractValue());
	}

	@Override
	public String getName() {
		return "map";
	}

	@Override
	public String getTypeName() {
		return "java.util.HashMap" + KV();
	}	
	
	@Override
	public String getGetterName() {
		return "java.util.Map" + KV();
	}

	@Override
	public void construct(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + var.getname() + " = new " + getTypeName() + "();");
	}

	@Override
	public void print4reset(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + var.getname() + ".clear();");
	}

	@Override
	public Type compile(String key, String value) {
		return new TypeMap(key, value);
	}

	private TypeMap(String key, String value) {
		keytype = Type.compile(key, null, null);
		if (!keytype.isConstant())
			throw new RuntimeException("map.key need a constant valuetype");
		valuetype = Type.compile(value, null, null);
	}

	/**
	 * ��������ʵ����ע�ᵽtypes��
	 * @param types �����������͵Ĺ�������
	 */
	TypeMap(Map<String, Type> types) {
		types.put(getName(), this);
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
	public void unmarshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		ps.println(prefix + "{");
		ps.println(prefix + "	int size = _os_.uncompact_uint32();");
		ps.println(prefix + "	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12");
		ps.println(prefix + "		" + var.getname() + " = new " + getTypeName() + "(size * 2);");
		ps.println(prefix + "	}");
		ps.println(prefix + "	for (; size > 0; --size)");
		ps.println(prefix + "	{");
		ps.println(prefix + "		" + keytype.defineSetParent(bean, var, "_k_"));
		keytype.unmarshal(bean, var, ps, prefix + "		", "_k_");
		ps.println(prefix + "		" + valuetype.defineSetParent(bean, var, "_v_"));
		valuetype.unmarshal(bean, var, ps, prefix + "		", "_v_");
		ps.println(prefix + "		" + localvarname + ".put(_k_, _v_);");
		ps.println(prefix + "	}");
		ps.println(prefix + "}");
	}	

	@Override
	public void getter(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return xdb.Logs.logMap(new xdb.LogKey(this, "
				+ Main.quote(var.getname()) + "), " + var.getname() + ");");
		ps.println(prefix + "}");
		ps.println();
		
		if (!isAny()) {
			ps.println(prefix + "@Override");
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
		ps.println(prefix + "	return xdb.Consts.constMap(" + var.getname() + ");");
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
	public String notEquals(String varname) {
		return "!" + varname + ".equals(_o_." + varname + ")";
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
	public String newListenable(XBean bean, Variable var) {
		return "new xdb.logs.ListenableMap().setVarName(" + Main.quote(var.getname()) + ")";
	}

	@Override
	public void depends(Set<Type> types) {
		if (types.add(this)) {
			keytype.depends(types);
			valuetype.depends(types);
		}
	}
}

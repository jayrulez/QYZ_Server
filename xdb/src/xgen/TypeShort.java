package xgen;

import java.io.PrintStream;
import java.util.Map;
import java.util.Set;

public class TypeShort extends Type {
	@Override
	public void printTableSelectMethod(PrintStream ps, String K) {
		String V = this.getBoxingName();
		ps.println("	public static " + V + " select(" + K + " key) {");
		ps.println("		return getTable().select(key, new xdb.TField<" + V + ", " + V + ">() {");
		ps.println("			public " + V + " get(" + V + " v) { return v; }");
		ps.println("		});");
		ps.println("	}");
		ps.println("");
	}

	@Override
	public String getName() {
		return "short";
	}

    @Override
    public short getTag() {
        return Tag.SHORT;
    }

	@Override
	public void verifyForeign(Xdb xdb, Foreign foreign) {
		foreign.throwIf(null != foreign.getKey(), "[short] need value only.");
		if (null != foreign.getValue()) {
			Table table = xdb.getTable(foreign.getValue());
			foreign.throwIf(null == table, "[short] table not exist.");
			foreign.throwIf(table.isMemory(), "[short] foreign table is memory");
			foreign.throwIf(table.getKeyType() != this, "[short] type not match");
		}
	}

	@Override
	public void verifyCapacity(Xdb xdb, Capacity capacity) {
		capacity.notNeed();
	}

	@Override
	public Type compile(String key, String value) {
		if (key != null && !key.isEmpty())
			throw new RuntimeException(getName() + " DO NOT NEED A KEY!");
		if (value != null && !value.isEmpty())
			throw new RuntimeException(getName() + " DO NOT NEED A VALUE!");
		return this;
	}

	@Override
	public String compareto(String vn1, String vn2) {
		return "Short.valueOf(" + vn1 + ").compareTo(" + vn2 + ")";
	}

	@Override
	public String getBoxingName() {
		return "Short";
	}

	@Override
	public String getTypeName() {
		return "short";
	}

	@Override
	public String defineNoParent(String varname) {
		return getTypeName() + " " + varname + " = 0;";
	}

	@Override
	public String notEquals(String varname) {
		return varname + " != _o_." + varname;
	}

	@Override
	public String hashCode(String varname) {
		return varname;
	}

	@Override
	public String defineSetParent(XBean bean, Variable var, String definename) {
		return defineNoParent(definename);
	}

	@Override
	public void marshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		ps.println(prefix + "_os_.marshal(" + localvarname + ");");
	}

	@Override
	public void unmarshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		ps.println(prefix + localvarname + " = _os_.unmarshal_short();");
	}

	@Override
	public void construct(XBean bean, Variable var, PrintStream ps, String prefix) {
		String initial = var.getInitial();
		if (!initial.isEmpty())
			ps.println(prefix + var.getname() + " = " + initial + ";");
	}

	@Override
	public void print4reset(XBean bean, Variable var, PrintStream ps, String prefix) {
		String initial = var.getInitial().isEmpty() ? "0" : var.getInitial();
		ps.println(prefix + var.getname() + " = " + initial + ";");
	}

	@Override
	public boolean isConstant() {
		return true;
	}

	@Override
	public String deepCopy(boolean isData, Variable var, String fullvarname) {
		return fullvarname;
	}

	@Override
	public void deepCopy(boolean isData, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + var.getname() + " = _o_." + var.getname() + ";");
	}

	@Override
	public int deepcopy4cache(PrintStream ps, String pf) {
		return 0;
	}
	
	/**
	 * ��������ʵ����ע�ᵽtypes��
	 * @param types �����������͵Ĺ�������
	 */
	TypeShort(Map<String, Type> types) {
		types.put(getName(), this);
	}

	@Override
	public void getterInterface(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "public short get" + var.getName() + "(); // " + var.getComment());
	}

	@Override
	public void getter(XBean bean, Variable var, PrintStream ps, String prefix) {
		if (null != bean) // cbean û�� interface
			ps.println(prefix + "@Override");
		ps.println(prefix + "public short get" + var.getName() + "() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return " + var.getname() + ";");
		ps.println(prefix + "}");
		ps.println("");
	}

	@Override
	public void getterConst(XBean bean, Variable var, PrintStream ps, String prefix) {
		this.getter(bean, var, ps, prefix);
	}

	@Override
	public void getterData(XBean bean, Variable var, PrintStream ps, String prefix) {
		if (null != bean) // cbean û�� interface
			ps.println(prefix + "@Override");
		ps.println(prefix + "public short get" + var.getName() + "() { // " + var.getComment());
		ps.println(prefix + "	return " + var.getname() + ";");
		ps.println(prefix + "}");
		ps.println("");
	}

	@Override
	public void setterInterface(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "public void set" + var.getName() + "(short _v_); // " + var.getComment());
	}

	@Override
	public void setterConst(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "(short _v_) { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	throw new UnsupportedOperationException();");
		ps.println(prefix + "}");
		ps.println("");
	}

	@Override
	public void setter(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "(short _v_) { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	xdb.Logs.logIf(new xdb.LogKey(this, " + Main.quote(var.getname()) + ") {");
		ps.println(prefix + "		protected xdb.Log create() {");
		ps.println(prefix + "			return new xdb.logs.LogShort(this, " + var.getname() + ") {");
		ps.println(prefix + "				public void rollback() { " + var.getname() + " = _xdb_saved; }");
		ps.println(prefix + "			};}});");
		ps.println(prefix + "	" + var.getname() + " = _v_;");
		ps.println(prefix + "}");
		ps.println("");
	}

	@Override
	public void setterData(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "(short _v_) { // " + var.getComment());
		ps.println(prefix + "	" + var.getname() + " = _v_;");
		ps.println(prefix + "}");
		ps.println("");
	}

	@Override
	public String newListenable(XBean bean, Variable var) {
		return "new xdb.logs.ListenableChanged().setVarName(" + Main.quote(var.getname()) + ")";
	}

	@Override
	public void depends(Set<Type> types) {
		types.add(this);
	}
}

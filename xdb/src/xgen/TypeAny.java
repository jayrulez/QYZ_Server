package xgen;

import java.io.PrintStream;
import java.util.Set;

public class TypeAny extends Type {

	private String name;

	TypeAny(String name) {
		this.name = name;
	}

	@Override
	public void printTableSelectMethod(PrintStream ps, String K) {
		throw new UnsupportedOperationException("select for " + this.getName());
	}

	@Override
	public void verifyForeign(Xdb xdb, Foreign foreign) {
		foreign.throwIf(true, "[any] unsupported.");
	}

	@Override
	public void verifyCapacity(Xdb xdb, Capacity capacity) {
		capacity.capacityOnly();
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
	public void construct(XBean bean, Variable var, PrintStream ps, String prefix) {
		//ps.println(prefix + varname + " = new " + getTypeName() + "();");
		ps.println(prefix + var.getname() + " = null;");
	}

	@Override
	public void print4reset(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + var.getname() + " = null;");
	}

	@Override
	public void deepCopy(boolean isData, Variable var, PrintStream ps, String prefix) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int deepcopy4cache(PrintStream ps, String pf) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String deepCopy(boolean isData, Variable var, String fullvarname) {
		throw new UnsupportedOperationException(var.getname()+":"+fullvarname);
	}

	@Override
	public String defineNoParent(String varname) {
		throw new UnsupportedOperationException();
//		return getTypeName() + " " + varname + " = null;";
	}

	@Override
	public String defineSetParent(XBean bean, Variable var, String definename) {
		throw new UnsupportedOperationException();
//		return defineNoParent(definename);
	}

    @Override
    public short getTag() {
        return 0;
    }

    public boolean isPredictable() {
        return false;
    }
    @Override
	public String getBoxingName() {
		return name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getTypeName() {
		return name;
	}

	@Override
	public void getter(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public " + name + " get" + var.getName() + "() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return " + var.getname() + ";");
		ps.println(prefix + "}");
		ps.println();
	}

	@Override
	public void getterData(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public " + name + " get" + var.getName() + "() { // " + var.getComment());
		ps.println(prefix + "	return " + var.getname() + ";");
		ps.println(prefix + "}");
		ps.println();
	}

	@Override
	public void getterConst(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public " + name + " get" + var.getName() + "() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return " + var.getname() + ";");
		ps.println(prefix + "}");
		ps.println();
	}

	@Override
	public void getterInterface(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "public " + name + " get" + var.getName() + "(); // " + var.getComment());
	}

	@Override
	public String hashCode(String varname) {
		return "(" + varname + " == null ? 0 : " + varname + ".hashCode())";
	}

	@Override
	public boolean isConstant() {
		return false;
	}
	
	public boolean isAny() {
		return true;
	}

	@Override
	public String newListenable(XBean bean, Variable var) {
		return "new xdb.logs.ListenableChanged().setVarName(" + Main.quote(var.getname()) + ")";
	}

	@Override
	public String notEquals(String varname) {
		return String.format(
				"(null == %1$s && null != _o_.%1$s) || (null != %1$s && null == _o_.%1$s) || (null != %1$s && null != _o_.%1$s && !%1$s.equals(_o_.%1$s))"
				, varname);
		// return "!" + varname + ".equals(_o_." + varname + ")";
	}

	@Override
	public void setter(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "(" + getTypeName() + " _v_) { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	xdb.Logs.logIf(new xdb.LogKey(this, " + Main.quote(var.getname()) + ") {");
		ps.println(prefix + "		protected xdb.Log create() {");
		ps.println(prefix + "			return new xdb.logs.LogObject<" + getTypeName() + ">(this, " + var.getname() + ") {");
		ps.println(prefix + "				public void rollback() { " + var.getname() + " = _xdb_saved; }");
		ps.println(prefix + "		}; }});");
		ps.println(prefix + "	" + var.getname() + " = _v_;");
		ps.println(prefix + "}");
		ps.println("");
	}

	@Override
	public void setterConst(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "(" + getTypeName() + " _v_) { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	throw new UnsupportedOperationException();");
		ps.println(prefix + "}");
		ps.println();
	}

	@Override
	public void setterInterface(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "public void set" + var.getName() + "(" + getTypeName() + " _v_); // " + var.getComment());
	}

	@Override
	public void setterData(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "(" + getTypeName() + " _v_) { // " + var.getComment());
		ps.println(prefix + "	" + var.getname() + " = _v_;");
		ps.println(prefix + "}");
		ps.println("");
	}

	@Override
	public void marshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void unmarshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void depends(Set<Type> types) {
		types.add(this);
	}

	
}

package xgen;

import java.io.PrintStream;
import java.util.Set;

public abstract class TypeCollection extends Type {

	private Type valuetype;

	@Override
	public void printTableSelectMethod(PrintStream ps, String K) {
		throw new UnsupportedOperationException("select for " + this.getName());
	}

	@Override
	public boolean isAny() {
		return valuetype.isAny();
	}
    @Override
    public boolean isPredictable() {
        return false;
    }

    @Override
	public void addOwnerTable(Table owner) {
		super.addOwnerTable(owner);
		this.valuetype.addOwnerTable(owner);
	}

	protected void _compile(String key, String value) {
		if (key != null && !key.isEmpty())
			throw new RuntimeException(getName() + " DO NOT NEED A KEY!");
		valuetype = Type.compile(value, null, null);
	}

	protected String toUpper1(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
	protected String V() {
		return "<" + valuetype.getBoxingName() + ">";
	}

	Type getValueType() {
		return valuetype;
	}

	@Override
	public String getTypeName() {
		return getCollectionName() + V();
	}

	@Override
	public String getBoxingName() {
		return getGetterName();
	}
	
	protected String getImplName() {
		return getName();
	}
	
	/**
	 * �����������֡�
	 * @return
	 */
	public abstract String getCollectionName();

	@Override
	public void marshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		ps.println(prefix + "_os_.compact_uint32(" + localvarname + ".size());");
		ps.println(prefix + "for (" + valuetype.getBoxingName() + " _v_ : " + localvarname + ") {");
		valuetype.marshal(bean, var, ps, prefix + "	", "_v_");
		ps.println(prefix + "}");
	}

	@Override
	public void unmarshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		ps.println(prefix + "for (int size = _os_.uncompact_uint32(); size > 0; --size) {");
		ps.println(prefix + "	" + valuetype.defineSetParent(bean, var, "_v_"));
		valuetype.unmarshal(bean, var, ps, prefix + "	", "_v_");
		ps.println(prefix + "	" + localvarname + ".add(_v_);");
		ps.println(prefix + "}");
	}

	@Override
	public String deepCopy(boolean isData, Variable var, String fullvarname) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void deepCopy(boolean isData, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + var.getname() + " = new " + getTypeName() + "();");
		if (valuetype.isConstant()) {
			ps.println(prefix + var.getname() + ".addAll(_o_." + var.getname() + ");");
			return;
		}
		ps.println(prefix + "for (" + valuetype.getBoxingName() + " _v_ : _o_." + var.getname() + ")");
		ps.println(prefix + "	" + var.getname() + ".add(" + valuetype.deepCopy(isData, var, "_v_") + ");");
	}

	@Override
	public void depends(Set<Type> types) {
		if ( types.add(this) )
			valuetype.depends(types);
	}	

	@Override
	public void getterInterface(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "(); // " + var.getComment());
		if (!isAny())
			ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "AsData(); // " + var.getComment());
	}
	
	@Override
	public void getterData(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "() { // " + var.getComment());
		ps.println(prefix + "	return " + var.getname() + ";");
		ps.println(prefix + "}");
		ps.println("");
		
		if (!isAny()) {
			ps.println(prefix + "@Override");
			ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "AsData() { // " + var.getComment());
			ps.println(prefix + "	return " + var.getname() + ";");
			ps.println(prefix + "}");
			ps.println("");
		}
	}
	
	@Override
	public void getterConst(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return xdb.Consts.const" + toUpper1(getImplName()) + "(" + var.getname() + ");");
		ps.println(prefix + "}");
		ps.println("");
		
		if (!isAny()) {
			ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "AsData() { // " + var.getComment());
			Main._xdb_verify_(ps, prefix + "	");
			ps.println(prefix + "	" + getGetterName() + " " + var.getname() + ";");
			ps.println(prefix + "	" + bean.getName() + " _o_ = " + bean.getName() + ".this;");			
			deepCopy(true, var, ps, "		");
			ps.println(prefix  + "	" + "return " + var.getname() + ";");
			ps.println(prefix + "}");
			ps.println("");
		}
	}	


	@Override
	public void getter(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return xdb.Logs.log" + toUpper1(getImplName()) + "(new xdb.LogKey(this, "
				+ Main.quote(var.getname()) + "), " + var.getname() + ");");
		ps.println(prefix + "}");
		ps.println("");
		
		if (!isAny() && !bean.isData()) {
			ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "AsData() { // " + var.getComment());
			Main._xdb_verify_(ps, prefix + "	");
			ps.println(prefix + "	" + getGetterName() + " " + var.getname() + ";");
			ps.println(prefix + "	" + bean.getName() + " _o_ = this;");			
			deepCopy(true, var, ps, "		");
			ps.println(prefix  + "	" + "return " + var.getname() + ";");
			ps.println(prefix + "}");
			ps.println("");
		}
	}

	@Override
	public void setter(XBean bean, Variable var, PrintStream ps, String prefix) {
		// unsupported
	}

	@Override
	public void setterConst(XBean bean, Variable var, PrintStream ps, String prefix) {
		// unsupported
	}

	@Override
	public void setterInterface(XBean bean, Variable var, PrintStream ps, String prefix) {
		// unsupported
	}

	@Override
	public void setterData(XBean bean, Variable var, PrintStream ps, String prefix) {
		// unsupported
	}
	
}

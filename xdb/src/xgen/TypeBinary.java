package xgen;

import java.io.PrintStream;
import java.util.Map;
import java.util.Set;

public class TypeBinary extends Type {
	@Override
	public void printTableSelectMethod(PrintStream ps, String K) {
		// binary ʵ���Ͽ���ʵ�ֿ�¡�������������޷�����table��value������Ҳû��Ҫ֧��select��
		throw new UnsupportedOperationException("select for " + this.getName());
	}

	@Override
	public void verifyForeign(Xdb xdb, Foreign foreign) {
		foreign.warn("[binary] this foreign is unverifiable.");
	}

	@Override
	public void verifyCapacity(Xdb xdb, Capacity capacity) {
		capacity.capacityOnly();
	}

	@Override
	public String getName() {
		return "binary";
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
    public short getTag() {
        return Tag.BINARY;
    }

    @Override
	public String getTypeName() {
		return "byte []";
	}

	@Override
	public String getBoxingName() {
		return getTypeName();
	}

	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public String notEquals(String varname) {
		return "!java.util.Arrays.equals(" + varname + ", _o_." + varname + ")";
	}

	@Override
	public String defineSetParent(XBean bean, Variable var, String definename) {
		return defineNoParent(definename);
	}

	@Override
	public String defineNoParent(String varname) {
		return "byte [] " + varname + " = new byte [0];";
	}

	@Override
	public String hashCode(String varname) {
		return "java.util.Arrays.hashCode(" + varname + ")";
	}

	@Override
	public void marshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
        ps.println(prefix + "_os_.marshal(" + localvarname + ");");
	}

	@Override
	public void unmarshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		ps.println(prefix + localvarname + " = _os_.unmarshal_bytes();");
	}

	@Override
	public void construct(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + var.getname() + " = new byte[0];");
	}

	@Override
	public void print4reset(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + var.getname() + " = new byte[0];");
	}

	@Override
	public String deepCopy(boolean isData, Variable var, String fullvarname) {
		return "java.util.Arrays.copyOf(" + fullvarname + ", " + fullvarname + ".length)";
	}

	@Override
	public void deepCopy(boolean isData, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + var.getname() + " = " + deepCopy(isData, var, "_o_." + var.getname()) + ";");
	}

	@Override
	public int deepcopy4cache(PrintStream ps, String pf) {
		return -1;
//		if (ps == null)
//			return true; // �����ж��Ƿ���Ҫ����
//
//		ps.println(pf + "public static " + this.getGetterName() + " deepcopy(" + this.getGetterName() + " _o_) {");
//		ps.println(pf + "	return java.util.Arrays.copyOf(_o_, _o_.length);");
//		ps.println(pf + "}");
//		return true;
	}

	@Override
	public void getterInterface(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "public <T extends com.goldhuman.Common.Marshal.Marshal> T get"
				+ var.getName() + "(T _v_); // " + var.getComment());
		ps.println(prefix + "public boolean is" + var.getName() + "Empty(); // " + var.getComment());
		ps.println(prefix + "public byte[] get" + var.getName() + "Copy(); // " + var.getComment());
//		ps.println(prefix + "/**");
//		ps.println(prefix + " * @deprecated DO NOT use this method. for cache only.");
//		ps.println(prefix + " */");
//		ps.println(prefix + "public byte[] get" + var.getName() + "(); // " + var.getComment());
	}

	@Override
	public void getterConst(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public <T extends com.goldhuman.Common.Marshal.Marshal> T get"
				+ var.getName() + "(T _v_) { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return " + bean.getName() + ".this.get" + var.getName() + "(_v_);");
		ps.println(prefix + "}");
		ps.println("");

		ps.println(prefix + "@Override");
		ps.println(prefix + "public boolean is" + var.getName() + "Empty() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return " + bean.getName() + ".this.is" + var.getName() + "Empty();");
		ps.println(prefix + "}");
		ps.println("");

		ps.println(prefix + "@Override");
		ps.println(prefix + "public byte[] get" + var.getName() + "Copy() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return " + bean.getName() + ".this.get" + var.getName() + "Copy();");
		ps.println(prefix + "}");
		ps.println("");

//		ps.println(prefix + "@Override");
//		ps.println(prefix + "public byte[] get" + var.getName() + "() { // " + var.getComment());
//		Main._xdb_verify_(ps, prefix + "	");
//		ps.println(prefix + "	return " + bean.getName() + ".this.get" + var.getName() + "();");
//		ps.println(prefix + "}");
//		ps.println("");
	}

	@Override
	public void getter(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public <T extends com.goldhuman.Common.Marshal.Marshal> T get"
				+ var.getName() + "(T _v_) { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	try {");
		ps.println(prefix + "		_v_.unmarshal(OctetsStream.wrap(com.goldhuman.Common.Octets.wrap(" + var.getname() + ")));");
		ps.println(prefix + "		return _v_;");
		ps.println(prefix + "	} catch (MarshalException _e_) {");
		ps.println(prefix + "		throw new xio.MarshalError();");
		ps.println(prefix + "	}");
		ps.println(prefix + "}");
		ps.println("");

		ps.println(prefix + "@Override");
		ps.println(prefix + "public boolean is" + var.getName() + "Empty() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return " + var.getname() + ".length == 0;");
		ps.println(prefix + "}");
		ps.println("");

		ps.println(prefix + "@Override");
		ps.println(prefix + "public byte[] get" + var.getName() + "Copy() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return java.util.Arrays.copyOf(" + var.getname() + ", " + var.getname() + ".length);");
		ps.println(prefix + "}");
		ps.println("");

//		ps.println(prefix + "@Override");
//		ps.println(prefix + "public byte[] get" + var.getName() + "() { // " + var.getComment());
//		Main._xdb_verify_(ps, prefix + "	");
//		ps.println(prefix + "	return " + var.getname() + ";");
//		ps.println(prefix + "}");
//		ps.println("");
	}

	@Override
	public void getterData(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public <T extends com.goldhuman.Common.Marshal.Marshal> T get"
				+ var.getName() + "(T _v_) { // " + var.getComment());
		ps.println(prefix + "	try {");
		ps.println(prefix + "		_v_.unmarshal(OctetsStream.wrap(com.goldhuman.Common.Octets.wrap(" + var.getname() + ")));");
		ps.println(prefix + "		return _v_;");
		ps.println(prefix + "	} catch (MarshalException _e_) {");
		ps.println(prefix + "		throw new xio.MarshalError();");
		ps.println(prefix + "	}");
		ps.println(prefix + "}");
		ps.println("");

		ps.println(prefix + "@Override");
		ps.println(prefix + "public boolean is" + var.getName() + "Empty() { // " + var.getComment());
		ps.println(prefix + "	return " + var.getname() + ".length == 0;");
		ps.println(prefix + "}");
		ps.println("");

		ps.println(prefix + "@Override");
		ps.println(prefix + "public byte[] get" + var.getName() + "Copy() { // " + var.getComment());
		ps.println(prefix + "	return java.util.Arrays.copyOf(" + var.getname() + ", " + var.getname() + ".length);");
		ps.println(prefix + "}");
		ps.println("");

//		ps.println(prefix + "@Override");
//		ps.println(prefix + "public byte[] get" + var.getName() + "() { // " + var.getComment());
//		ps.println(prefix + "	return " + var.getname() + ";");
//		ps.println(prefix + "}");
//		ps.println("");
	}

	@Override
	public void setterInterface(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "public void set" + var.getName() + "(com.goldhuman.Common.Marshal.Marshal _v_); // " + var.getComment());
		ps.println(prefix + "public void set" + var.getName() + "Copy(byte[] _v_); // " + var.getComment());
	}

	@Override
	public void setterConst(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "(com.goldhuman.Common.Marshal.Marshal _v_) { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	throw new UnsupportedOperationException();");
		ps.println(prefix + "}");
		ps.println("");

		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "Copy(byte[] _v_) { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	throw new UnsupportedOperationException();");
		ps.println(prefix + "}");
		ps.println("");
	}

	@Override
	public void setter(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "(com.goldhuman.Common.Marshal.Marshal _v_) { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	xdb.Logs.logIf(new xdb.LogKey(this, " + Main.quote(var.getname()) + ") {");
		ps.println(prefix + "		protected xdb.Log create() {");
		ps.println(prefix + "			return new xdb.logs.LogObject<" + getTypeName() + ">(this, " + var.getname() + ") {");
		ps.println(prefix + "				public void rollback() { " + var.getname() + " = _xdb_saved; }");
		ps.println(prefix + "		}; }});");
		ps.println(prefix + "	" + var.getname() + " = _v_.marshal(new OctetsStream()).getBytes();");
		ps.println(prefix + "}");
		ps.println("");

		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "Copy(byte[] _v_) { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	xdb.Logs.logIf(new xdb.LogKey(this, " + Main.quote(var.getname()) + ") {");
		ps.println(prefix + "		protected xdb.Log create() {");
		ps.println(prefix + "			return new xdb.logs.LogObject<" + getTypeName() + ">(this, " + var.getname() + ") {");
		ps.println(prefix + "				public void rollback() { " + var.getname() + " = _xdb_saved; }");
		ps.println(prefix + "		}; }});");
		ps.println(prefix + "	" + var.getname() + " = java.util.Arrays.copyOf(_v_, _v_.length);");
		ps.println(prefix + "}");
		ps.println("");
	}

	@Override
	public void setterData(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "(com.goldhuman.Common.Marshal.Marshal _v_) { // " + var.getComment());
		ps.println(prefix + "	" + var.getname() + " = _v_.marshal(new OctetsStream()).getBytes();");
		ps.println(prefix + "}");
		ps.println("");

		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "Copy(byte[] _v_) { // " + var.getComment());
		ps.println(prefix + "	" + var.getname() + " = java.util.Arrays.copyOf(_v_, _v_.length);");
		ps.println(prefix + "}");
		ps.println("");
	}

	@Override
	public String newListenable(XBean bean, Variable var) {
		return "new xdb.logs.ListenableChanged().setVarName(" + Main.quote(var.getname()) + ")";
	}

	@Override
	public void toString(PrintStream ps, String prefix, String varname) {
		ps.println(prefix + "_sb_.append('B').append(" + varname + ".length);");
	}

	/**
	 * ��������ʵ����ע�ᵽtypes��
	 * @param types �����������͵Ĺ�������
	 */
	TypeBinary(Map<String, Type> types) {
		types.put(getName(), this);
	}

	@Override
	public void depends(Set<Type> types) {
		types.add(this);
	}
}

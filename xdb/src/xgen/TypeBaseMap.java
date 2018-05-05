package xgen;

import java.io.PrintStream;

public abstract class TypeBaseMap extends Type {

	protected Type keytype;
	protected Type valuetype;
	
	public abstract String getName();
	public abstract String getTypeName();
	public abstract String getGetterName();
		
	protected String KV() {
		return "<" + keytype.getBoxingName() + ", " + valuetype.getBoxingName() + ">";
	}

    @Override
    public short getTag() {
        return Tag.MAP;
    }
    @Override
    public boolean isPredictable() {
        return false;
    }

	@Override
	public String getBoxingName() {
		return getGetterName();
	}

	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public boolean isAny() {
		return valuetype.isAny();
	}

	@Override
	public void printTableSelectMethod(PrintStream ps, String K) {
		throw new UnsupportedOperationException("select for " + this.getName());
	}

	@Override
	public String deepCopy(boolean isData, Variable var, String fullvarname) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deepCopy(boolean isData, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + var.getname() + " = new " + getTypeName() + "();");
		String keycopy = keytype.isConstant() ? "_e_.getKey()" : (keytype.deepCopy(isData, var, "_e_.getKey()"));
		String valuecopy = valuetype.isConstant() ? "_e_.getValue()" : (valuetype.deepCopy(isData, var, "_e_.getValue()"));
		ps.println(prefix + "for (java.util.Map.Entry" + KV() + " _e_ : _o_." + var.getname() + ".entrySet())");
		ps.println(prefix + "	" + var.getname() + ".put(" + keycopy + ", " + valuecopy + ");");
	}

	@Override
	public int deepcopy4cache(PrintStream ps, String pf) {
		if (ps == null)
			return 1; // �����ж��Ƿ���Ҫ����

		ps.println(pf + "public static " + this.getGetterName() + " deepcopy(" + this.getGetterName() + " _o_) {");
		ps.println(pf + "	" + this.getGetterName() + " _r_ = new " + this.getTypeName() + "();");
		
		if (keytype.isConstant()) {
			ps.println(pf + "	for (java.util.Map.Entry" + KV() + " _e_ : _o_.entrySet())");
			if (valuetype.isConstant()) {
				ps.println(pf + "		_r_.put(_e_.getKey(), _e_.getValue());");
			} else if (valuetype instanceof TypeBinary) {
				ps.println(pf + "		_r_.put(_e_.getKey(), java.util.Arrays.copyOf(_e_.getValue(), _e_.getValue().length)));");
			} else if (valuetype instanceof XBean) {
				ps.println(pf + "		_r_.put(_e_.getKey(), _e_.getValue().toDataIf());");
			} else {
				// ���������ǣ�������Ҫ��������������жϡ�Ū���쳣�����Ҫ��д�������������͡�
				throw new RuntimeException("unknown type for deepropy4Cache!");
			}
		} else if (keytype instanceof TypeBinary) {
			ps.println(pf + "	for (java.util.Map.entry" + KV() + " _e_ : _o_)");
			if (valuetype.isConstant()) {
				ps.println(pf + "		_r_.put(java.util.Arrays.copyOf(_e_.getValue(), _e_.getValue().length)), _e_.getValue());");
			} else if (valuetype instanceof TypeBinary) {
				ps.println(pf + "		_r_.put(java.util.Arrays.copyOf(_e_.getValue(), _e_.getValue().length)), java.util.Arrays.copyOf(_e_.getValue(), _e_.getValue().length)));");
			} else if (valuetype instanceof XBean) {
				ps.println(pf + "		_r_.put(java.util.Arrays.copyOf(_e_.getValue(), _e_.getValue().length)), _e_.getValue().toDataIf());");
			} else {
				// ���������ǣ�������Ҫ��������������жϡ�Ū���쳣�����Ҫ��д�������������͡�
				throw new RuntimeException("unknown type for deepropy4Cache!");
			}
		} else if (keytype instanceof XBean) {
			ps.println(pf + "	for (java.util.Map.entry" + KV() + " _e_ : _o_)");
			if (valuetype.isConstant()) {
				ps.println(pf + "		_r_.put(_e_.getKey().toDataIf(), _e_.getValue());");
			} else if (valuetype instanceof TypeBinary) {
				ps.println(pf + "		_r_.put(_e_.getKey().toDataIf(), java.util.Arrays.copyOf(_e_.getValue(), _e_.getValue().length)));");
			} else if (valuetype instanceof XBean) {
				ps.println(pf + "		_r_.put(_e_.getKey().toDataIf(), _e_.getValue().toDataIf());");
			} else {
				// ���������ǣ�������Ҫ��������������жϡ�Ū���쳣�����Ҫ��д�������������͡�
				throw new RuntimeException("unknown type for deepropy4Cache!");
			}
		} else {
			// ���������ǣ�������Ҫ��������������жϡ�Ū���쳣�����Ҫ��д�������������͡�
			throw new RuntimeException("unknown type for deepropy4Cache!");
		}
		ps.println(pf + "	return _r_;");
		ps.println(pf + "}");
		return 1;
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
	public void getterInterface(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "(); // " + var.getComment());
		if (!isAny())
			ps.println(prefix + "public " + getGetterName() + " get" + var.getName() + "AsData(); // " + var.getComment());
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

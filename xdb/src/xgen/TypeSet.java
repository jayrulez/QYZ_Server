package xgen;

import java.io.PrintStream;
import java.util.Map;

public class TypeSet extends TypeCollection {

	@Override
	public String getName() {
		return "set";
	}

    @Override
    public short getTag() {
        return Tag.SET;
    }

	@Override
	public void verifyForeign(Xdb xdb, Foreign foreign) {
		foreign.throwIf(null != foreign.getKey(), "[set] need value only.");
		if (null != foreign.getValue()) {
			Table table = xdb.getTable(foreign.getValue());
			foreign.throwIf(null == table, "[set.value] table not exist.");
			foreign.throwIf(table.isMemory(), "[set.value] foreign table is memory");
			foreign.throwIf(table.getKeyType() != this.getValueType(), "[set.value] type not match");
		}
	}

	@Override
	public void verifyCapacity(Xdb xdb, Capacity capacity) {
		capacity.capacityNeed();
		capacity.keyNotNeed();
		this.getValueType().verifyCapacity(xdb, capacity.extractValue());
	}

	@Override
	public boolean isConstant() {
		return false;
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
	public String getCollectionName() {
		return Type.getCurrentBean().isData() ? "java.util.HashSet" : "xdb.util.SetX";
	}

	@Override
	public int deepcopy4cache(PrintStream ps, String pf) {
		if (ps == null)
			return 1; // �����ж��Ƿ���Ҫ����

		ps.println(pf + "public static " + this.getGetterName() + " deepcopy(" + this.getGetterName() + " _o_) {");
		ps.println(pf + "	" + this.getGetterName() + " _r_ = new java.util.HashSet" + this.V() + "();");
		Type valuetype = super.getValueType();
		if (valuetype.isConstant()) {
			ps.println(pf + "	_r_.addAll(_o_);");
		} else if (valuetype instanceof TypeBinary) {
			ps.println(pf + "	for (" + valuetype.getBoxingName() + " _v_ : _o_)");
			ps.println(pf + "		_r_.add(java.util.Arrays.copyOf(_v_, _v_.length));");
		} else if (valuetype instanceof XBean) {
			ps.println(pf + "	for (" + valuetype.getBoxingName() + " _v_ : _o_)");
			ps.println(pf + "		_r_.add(_v_.toDataIf());");
		} else {
			// ���������ǣ�������Ҫ��������������жϡ�Ū���쳣�����Ҫ��д�������������͡�
			throw new RuntimeException("unknown type for deepropy4Cache!");
		}
		ps.println(pf + "	return _r_;");
		ps.println(pf + "}");
		return 1;
	}
	
	@Override
	public String getGetterName() {
		return "java.util.Set" + V();
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
		return new TypeSet(key, value);
	}

	private TypeSet(String key, String value) {
		_compile(key, value);
	}

	/**
	 * ��������ʵ����ע�ᵽtypes��
	 * @param types �����������͵Ĺ�������
	 */
	TypeSet(Map<String, Type> types) {
		types.put(getName(), this);
	}

	@Override
	public String newListenable(XBean bean, Variable var) {
		return "new xdb.logs.ListenableSet().setVarName(" + Main.quote(var.getname()) + ")";
	}
}

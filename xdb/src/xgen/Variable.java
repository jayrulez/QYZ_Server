package xgen;

import org.w3c.dom.*;

import xdb.util.DatabaseMetaData;

import java.io.*;
import java.util.Set;

public class Variable {
	private final int id;
	private String name;
	private String type;
	private String key;
	private String value;
	private String initial;
	private String comment;
	private String comparator;
	private String foreign;
	private String capacity;

	private String Name;

	public String getComparator() {
		return comparator;
	}

	public String getComment() {
		return comment;
	}

	public String getInitial() {
		return initial;
	}

	public String getName() {
		return Name;
	}

	public String getname() {
		return name;
	}
	
	public int getId() {
		return id;
	}

	public Variable(Element self) {
		name = self.getAttribute("name").trim().toLowerCase();
		try {
			id = Integer.parseInt(self.getAttribute("id"));
		} catch(Exception e) {
			throw new RuntimeException("variable:" + name + " , invalid id");
		}
		name = self.getAttribute("name").trim().toLowerCase();
		Main.verifyName(name);
		type = self.getAttribute("type").trim();
		key = self.getAttribute("key").trim();
		value = self.getAttribute("value").trim();
		initial = self.getAttribute("default").trim();
		comparator = self.getAttribute("comparator");
		foreign = self.getAttribute("foreign").trim();
		capacity = self.getAttribute("capacity");

		comment = self.getAttribute("comment");
		if (comment.isEmpty()){
			Node c = self.getNextSibling();
			if (c != null && Node.TEXT_NODE == c.getNodeType()) {
				comment = c.getTextContent().trim().replaceAll("[\r\n]", "");
			}
		}

		Name = Main.toUpper1(name);
	}

	///////////////////////////////////////////////////////////////////
	private Type vartype;

	public Type getVartype() {
		return vartype;
	}

	public void compile(Xdb xdb) {
		vartype = Type.compile(type, key, value);
	}

	public void verify(Xdb xdb, Type bean) {
		// verify capacity
		vartype.verifyCapacity(xdb, new Capacity(capacity, "bean." + bean.getName() + "." + getname()));

		if (bean.getName().equals("fxbean0") && this.name.equals("a")) {
			System.out.println();
		}
		// ��֤foreign������Լ��������ͺͱ��key����һ�¡�
		if (!foreign.isEmpty()) {
			// Foreign = tableName | key:tableName;value:tableName.
			vartype.verifyForeign(xdb, new Foreign(foreign, bean.getName() + "." + getname()));
		}
	}

	public void collectForeigns(Set<String> foreigns) {
		Foreign f = new Foreign(foreign, "");
		if (null != f.getKey())
			foreigns.add(f.getKey());
		if (null != f.getValue())
			foreigns.add(f.getValue());
	}

	public void declare(PrintStream ps, String prefix) {
		ps.println(prefix + "private " + vartype.getTypeName() + " " + name + ";" + " // " + comment);
	}

	public void construct(XBean bean, PrintStream ps, String prefix) {
		vartype.construct(bean, this, ps, prefix);
	}

	public void deepCopy(XBean bean, PrintStream ps, String prefix) {
		vartype.deepCopy(bean.isData(), this, ps, prefix);
	}

	public void marshal(XBean bean, PrintStream ps, String prefix) {
		vartype.marshal(bean, this, ps, prefix, this.getname());
	}

    public void marshal(Type compatibleType, XBean bean, PrintStream ps, String prefix) {
        compatibleType.marshal(bean, this, ps, prefix, this.getname());
    }

	public void unmarshal(XBean bean, PrintStream ps, String prefix) {
		vartype.unmarshal(bean, this, ps, prefix, this.getname());
	}

    public void unmarshal(Type compatibleType, XBean bean, PrintStream ps, String prefix) {
        compatibleType.unmarshal(bean, this, ps, prefix, this.getname());
    }

	public void setterInterface(XBean bean, PrintStream ps, String prefix) {
		vartype.setterInterface(bean, this, ps, prefix);
	}

	public void setterConst(XBean bean, PrintStream ps, String prefix) {
		vartype.setterConst(bean, this, ps, prefix);
	}

	public void setter(XBean bean, PrintStream ps, String prefix) {
		vartype.setter(bean, this, ps, prefix);
	}

	public void setterData(XBean bean, PrintStream ps, String prefix) {
		vartype.setterData(bean, this, ps, prefix);
	}

	public void getterInterface(XBean bean, PrintStream ps, String prefix) {
		vartype.getterInterface(bean, this, ps, prefix);
	}

	public void getterConst(XBean bean, PrintStream ps, String prefix) {
		vartype.getterConst(bean, this, ps, prefix);
	}

	public void getter(XBean bean, PrintStream ps, String prefix) {
		vartype.getter(bean, this, ps, prefix);
	}

	public void getterData(XBean bean, PrintStream ps, String prefix) {
		vartype.getterData(bean, this, ps, prefix);
	}

	public void equals(PrintStream ps, String prefix) {
		ps.println(prefix + "if (" + vartype.notEquals(name) + ") return false;");
	}

	public void hashCode(PrintStream ps, String prefix) {
		ps.println(prefix + "_h_ += " + vartype.hashCode(name) + ";");
	}

	public void toString(PrintStream ps, String prefix) {
		vartype.toString(ps, prefix, name);
	}

	public String newListenable(XBean bean) {
		return vartype.newListenable(bean, this);
	}

	public boolean isAny() {
		return vartype.isAny();
	}

	public void printSelect(PrintStream ps, String K, String V) {
		if (vartype.isAny())
			return;
		String F = vartype.getBoxingName();
		ps.println("	public static " + F + " select" + getName() + "(" + K + " key) {");
		ps.println("		return getTable().select(key, new xdb.TField<" + V + ", " + F + ">() {");
		ps.print("				public " + F + " get(" + V + " v) { return v.get" + getName());
		if (vartype instanceof TypeCollection || vartype instanceof TypeBaseMap) ps.print("AsData");
		else if (vartype instanceof TypeBinary) ps.print("Copy");
		ps.println("(); }");
		ps.println("			});");
		ps.println("	}");
		ps.println("");
		
	}

	public String compareto() {
		return vartype.compareto(name, "_o_." + name);
		
	}

	public void printMetaData(PrintStream ps, String prefix) {
		ps.println(prefix + ", " + Main.quote(name));
		ps.println(prefix + ", " + Main.quote(type) + ", " + Main.quote(key)
				+ ", " + Main.quote(value) + ", " + Main.quote(comparator));
		ps.println(prefix + ", " + Main.quote(initial)
				+ ", " + Main.quote(foreign) + ", " + Main.quote(capacity));
	}
	
	///////caijiacheng 2009-9-1
	public static enum MODIFY_TYPES {
		ADD,
		REMOVE,
		NONE
	};
		
	private MODIFY_TYPES modifytype = MODIFY_TYPES.NONE;
	
	public void setModifyType( MODIFY_TYPES type )
	{
		this.modifytype = type;
	}
	
	public MODIFY_TYPES getModifyType()
	{
		return this.modifytype;
	}
	Variable(DatabaseMetaData.Bean.Variable bv) {
		id = bv.getId();
		name = bv.getName();
		Main.verifyName(name);
		type = bv.getTypeName();
		key = bv.getKeyTypeName();
		value = bv.getValueTypeName();
		if(value.isEmpty())
			value = "";
		foreign ="";
		initial = "";
		capacity = "1024";
		comparator = "";
		
		Name = Main.toUpper1(name);
		//vartype = Type.compile(type, key, value);
		//vartype = bv.getTypeType();
	}
	
	public String getKeyTypeName()
	{
		return this.key;
	}
	
	public String getValueTypeName()
	{
		return this.value;
	}
	
}

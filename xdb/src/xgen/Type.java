package xgen;

import java.util.*;
import java.io.*;

/**
 * 
 * @see xdb.util.DatabaseMetaData.Type
 */
public abstract class Type {
    public final static short TAG_SHIFT = 11;
    public final static short TAG_MASK = (~0) << TAG_SHIFT;
    public final static short ID_MASK = (1 << TAG_SHIFT) - 1;
    public final static class Tag {
        public final static short
                BOOL = 1 << TAG_SHIFT,
                BYTE = 2 << TAG_SHIFT,
                SHORT = 3 << TAG_SHIFT,
                INT = 4 << TAG_SHIFT,
                LONG = 5 << TAG_SHIFT,
                FLOAT = 6 << TAG_SHIFT,
                DOUBLE = 7 << TAG_SHIFT,
                BINARY = 8 << TAG_SHIFT,
                STRING = 9 << TAG_SHIFT,
                SET = 10 << TAG_SHIFT,
                LIST = 11 << TAG_SHIFT,
                MAP = 12 << TAG_SHIFT,
                BEAN = 13 << TAG_SHIFT;
    }
    public abstract short getTag();

	/*
	 * ���������ġ���XBean���á�����ʵ����Ҫ���ݵ�ǰ�����ģ����ɲ�ͬ���롣
	 */
	private static XBean currentBean;
	static void setCurrentBean(XBean bean) { currentBean = bean; }
	static XBean getCurrentBean()          { return currentBean; }

	/**
	 * �������֣��û��Զ��壬����ϵͳ��������
	 */
	public abstract String getName();

	private Set<Table> owners = new HashSet<Table>();

	public void addOwnerTable(Table owner) {
		owners.add(owner);
	}

	public Set<Table> getOwnerTables() {
		return owners;
	}

    private final List<Type> compatibleTypes = new ArrayList<>();

    public final List<Type> getCompatibleTypes() {
        return compatibleTypes;
    }

    public final void addCompatibleTypes(Type... types) {
        for(Type type : types) {
            compatibleTypes.add(type);
        }
    }

	public abstract void verifyForeign(Xdb xdb, Foreign foreign);
	public abstract void verifyCapacity(Xdb xdb, Capacity capacity);
	public abstract void printTableSelectMethod(PrintStream ps, String K);


	public abstract Type compile(String key, String value);

	public abstract String getTypeName();

	public String getGetterName() {
		return getTypeName();
	}

	public boolean supportAutoIncrement() {
		return false;
	}

	public abstract String getBoxingName();

	public abstract boolean isConstant();

    public boolean isPredictable() {
        return true;
    }

    public abstract void getter(XBean bean, Variable var, PrintStream ps, String prefix);
	public abstract void getterConst(XBean bean, Variable var, PrintStream ps, String prefix);
	public abstract void getterInterface(XBean bean, Variable var, PrintStream ps, String prefix);
	public abstract void getterData(XBean bean, Variable var, PrintStream ps, String prefix);

	public abstract void setter(XBean bean, Variable var, PrintStream ps, String prefix);
	public abstract void setterConst(XBean bean, Variable var, PrintStream ps, String prefix);
	public abstract void setterInterface(XBean bean, Variable var, PrintStream ps, String prefix);
	public abstract void setterData(XBean bean, Variable var, PrintStream ps, String prefix);

	public abstract void construct(XBean bean, Variable var, PrintStream ps, String prefix);
	public abstract void print4reset(XBean bean, Variable var, PrintStream ps, String prefix);

	public String compareto(String vn1, String vn2) {
		throw new UnsupportedOperationException("compareTo");
	}

	public abstract void marshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname);

	public abstract void unmarshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname);


	public abstract void deepCopy(boolean isData, Variable var, PrintStream ps, String prefix);

	public abstract String deepCopy(boolean isData, Variable var, String fullvarname);

	public abstract int deepcopy4cache(PrintStream ps, String pf);

	public void toString(PrintStream ps, String prefix, String varname) {
		ps.println(prefix + "_sb_.append(" + varname + ");");
	}
	public boolean isAny() {
		return false;
	}

	public abstract String defineSetParent(XBean bean, Variable var, String definename);

	public abstract String defineNoParent(String varname);

	public abstract String notEquals(String varname);

	public abstract String hashCode(String varname);

	public abstract String newListenable(XBean bean, Variable var);

	private static Map<String, Type> types = new HashMap<String, Type>();

	static {
		// constant
		new TypeBoolean(types);
		final TypeShort tshort = new TypeShort(types);

		final TypeInt tint = new TypeInt(types);
        tint.addCompatibleTypes(tshort);
		final TypeLong tlong = new TypeLong(types);
        tlong.addCompatibleTypes(tshort, tint);


		new TypeString(types);
		new TypeFloat(types);

		// collection
		new TypeList(types);
		new TypeVector(types);
		new TypeSet(types);

		// map
		new TypeMap(types);
		new TypeTreeMap(types);

		new TypeBinary(types);
		//new TypeOctets(types);
	}

	public static void add(Type type) {
		if (types.put(type.getName(), type) != null)
			throw new RuntimeException("duplicate type " + type.getName()); 
		//System.out.println("newtype '" + type.getName() + "'");
	}

	private static boolean any = false;

	public static boolean setAny(boolean a) {
		boolean tmp = any;
		any = a;
		return tmp;
	}

	public static Type compile(String name, String key, String value) {
		Type type = types.get(name);
		if (null != type)
			return type.compile(key, value);

		if (any) // see setAny
			return new TypeAny(name);
		throw new RuntimeException("'" + name + "' TYPE NOT FOUND!");
	}

	public abstract void depends(Set<Type> types);

	private static Type dependFrom = null;

	public static boolean addDepend(Set<Type> result, Type self) {
		if (result.add(self))
			return true;
		if (dependFrom == self)
			throw new RuntimeException("unsupport self-depend. type=" + Main.quote(self.getName()));
		return false;
	}

	public Set<Type> depends() {
		Set<Type> types = new HashSet<Type>();
		dependFrom = this;
		try {
			depends(types);
		} finally {
			dependFrom = null;
		}
		return types;
	}
	//add by cjc 2009-11-12
	public static boolean isExist( String name )
	{
		return types.containsKey(name);
	}
	
}

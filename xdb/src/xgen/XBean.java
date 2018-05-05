package xgen;

import org.w3c.dom.*;

import com.goldhuman.Common.Marshal.OctetsStream;

import xdb.util.DatabaseMetaData;

import java.util.*;
import java.io.*;

public class XBean extends Type {

    @Override
    public short getTag() {
        return Tag.BEAN;
    }

    @Override
    public boolean isPredictable() {
        return false;
    }

    private String name;
	private boolean anyMaybe = false;
	private List<Variable> variables = new ArrayList<Variable>();
	private List<Enum> enums = new ArrayList<Enum>();
	private boolean isData = false;
	private Set<String> varNames = new HashSet<String>();
	private Set<Integer> ids = new HashSet<Integer>();

	@Override
	public void addOwnerTable(Table owner) {
		super.addOwnerTable(owner);
		for (Variable var : variables)
			var.getVartype().addOwnerTable(owner);
	}

	private void add(Variable var) {
		if (false == varNames.add(var.getname()))
			throw new IllegalArgumentException("duplicate varname." + var.getname() + "@" + name);
		if(!ids.add(var.getId()))
			throw new IllegalArgumentException("duplicate id." + var.getId() + "@" + name);
		variables.add(var);
	}

	public void collectForeigns(Set<String> foreigns) {
		for (Variable var : variables)
			var.collectForeigns(foreigns);
	}

	@Override
	public void verifyForeign(Xdb xdb, Foreign foreign) {
		foreign.throwIf(true, "[xbean] unsupported.");
	}

	public boolean isData() {
		return isData;
	}

	@Override
	public Type compile(String key, String value) {
		if (key != null && !key.isEmpty())
			throw new RuntimeException(getName() + " DO NOT NEED A KEY!");
		if (value != null && !value.isEmpty())
			throw new RuntimeException(getName() + " DO NOT NEED A VALUE!");
		return this;
	}

	public XBean(Element self) {
		name = self.getAttribute("name").trim();
		Main.verifyName(name);
		if (name.equals("Pod"))
			throw new RuntimeException("name of 'Pod' has been used by xdb");
		Type.add(this);
		anyMaybe = "true".equalsIgnoreCase(self.getAttribute("any"));

		NodeList childnodes = self.getChildNodes();
		for (int i = 0; i < childnodes.getLength(); ++i) {
			Node node = childnodes.item(i);
			if (Node.ELEMENT_NODE != node.getNodeType())
				continue;

			Element e = (Element) node;
			String nodename = e.getNodeName();

			if (nodename.equals("variable"))
				add(new Variable(e));
			else if (nodename.equals("enum"))
				enums.add(new Enum(e));
			else
				throw new RuntimeException("node=" + nodename);
		}
	}

	@Override
	public boolean isAny() {
		for (Type type : this.depends())
			if (type != this && type.isAny())
				return true;
		return false;
	}

	public void compile(Xdb xdb) {
		boolean old = Type.setAny(anyMaybe);
		try {
			for (Variable var : variables)
				var.compile(xdb);
		} finally {
			Type.setAny(old);
		}
	}

	public void verify(Xdb xdb) {
		for (Variable var : variables)
			var.verify(xdb, this);
	}

	@Override
	public void verifyCapacity(Xdb xdb, Capacity capacity) {
		capacity.notNeed();
	}

	private void generateImplementCopyConstruct(PrintStream ps) {
		String cls = getName();
		ps.println("	public " + cls + "(" + cls + " _o_) {");
		ps.println("		this(_o_, null, null);");
		ps.println("	}");
		ps.println("");
		ps.println("	" + cls + "(xbean." + cls + " _o1_, xdb.XBean _xp_, String _vn_) {");
		ps.println("		super(_xp_, _vn_);");

		if (isAny()) {
			ps.println("		throw new UnsupportedOperationException();");
			ps.println("	}");
			ps.println("");
			return;
		}

		ps.println("		if (_o1_ instanceof " + cls + ") assign((" + cls + ")_o1_);");
		ps.println("		else if (_o1_ instanceof " + cls + ".Data) assign((" + cls + ".Data)_o1_);");
		ps.println("		else if (_o1_ instanceof " + cls + ".Const) assign(((" + cls + ".Const)_o1_).nThis());");
		ps.println("		else throw new UnsupportedOperationException();");
		ps.println("	}");
		ps.println("");

		ps.println("	private void assign(" + cls + " _o_) {");
		Main._xdb_verify_(ps, "		_o_.");
		for (Variable var : variables) var.deepCopy(this, ps, "		");
		ps.println("	}");
		ps.println("");

		ps.println("	private void assign(" + cls + ".Data _o_) {");
		for (Variable var : variables) var.deepCopy(this, ps, "		");
		ps.println("	}");
		ps.println("");
	}

    public static void generateMarshal(xgen.XBean hostType, List<Variable> variables, PrintStream ps, boolean verifyUnsafe, String indent) {
        // marshal implement
        ps.println(indent + "	@Override");
        ps.println(indent + "	public final OctetsStream marshal(OctetsStream _os_) {");
        OctetsStream os = new OctetsStream();
        os.clear();
        if (hostType.isAny())
            ps.println(indent + "		throw new UnsupportedOperationException();");
        else {
            if(verifyUnsafe)
                Main._xdb_verify_(ps, indent + "		");
            ps.println(indent + "		_os_.marshal((short)" + variables.size() + ");");
            for (Variable var : variables) {
                final Type varType = var.getVartype();
                ps.printf("%s_os_.marshal((short)(%5s|%3s));", indent, var.getVartype().getTag(), var.getId());
                if(!varType.isPredictable())
                    ps.println("{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();");
                var.marshal(hostType, ps, "");
                if(!varType.isPredictable())
                    ps.println("_temp_.marshal(_os_); _os_ = _temp_;}");
            }
            ps.println(indent + "		return _os_;");
        }
        ps.println(indent + "	}");
        ps.println("");

        // unmarshal implement
        ps.println(indent + "	@Override");
        ps.println(indent + "	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {");
        if (hostType.isAny())
            ps.println(indent + "		throw new UnsupportedOperationException();");
        else {
            if(verifyUnsafe)
                Main._xdb_verify_(ps, indent + "		");
            ps.println(indent + "		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {");
            ps.println(indent + "			final short _id_ = _os_.unmarshal_short();");
            ps.println(indent + "			switch(_id_) {");
            for (Variable var : variables) {
//                ps.printf("%scase (%5s|%3s):", indent + "				", var.getVartype().getTag(), var.getId());
//                var.unmarshal(hostType, ps, "");
//                ps.println(indent + "				break;");
                final Type varType = var.getVartype();
                ps.printf("%scase (%5s|%3s):", indent + "				", varType.getTag(), var.getId());
                if(!varType.isPredictable())
                    ps.println("{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());");
                var.unmarshal(varType, hostType, ps, "");
                if(!varType.isPredictable())
                    ps.println("_os_ = _temp_;}");
                ps.println(indent + "				break;");
                for(Type compatibleType : var.getVartype().getCompatibleTypes()) {
                    ps.printf("%scase (%5s|%3s):", indent + "				", compatibleType.getTag(), var.getId());
                    if(!compatibleType.isPredictable())
                        ps.println("{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());");
                    var.unmarshal(compatibleType, hostType, ps, "");
                    if(!compatibleType.isPredictable())
                        ps.println("_os_ = _temp_;}");
                    ps.println(indent + "				break;");
                }
            }
            ps.println(indent + "default:skipUnknownField(_id_, _os_);");
            ps.println(indent + "			}");
            ps.println(indent + "		}");
            ps.println(indent + "		return _os_;");
        }
        ps.println(indent + "	}");
        ps.println("");
    }

	private void generateImplement() {
		String cls = getName();
		PrintStream ps = Main.openBean__File(cls);

		ps.println("");
		ps.println("package xbean.__;");
		ps.println("");
		ps.println("import com.goldhuman.Common.Marshal.OctetsStream;");
		ps.println("import com.goldhuman.Common.Marshal.MarshalException;");
		ps.println("");
		ps.println("public final class " + cls + " extends xdb.XBean implements xbean." + cls + " {");

		// variable declare
		for (Variable var : variables) var.declare(ps, "	");
		ps.println("");


		// _reset_unsafe_
		ps.println("	@Override");
		ps.println("	public void _reset_unsafe_() {");
		for (Variable var : variables)
			var.getVartype().print4reset(this, var, ps, "		");
		ps.println("	}");
		ps.println("");

		// constructor initial. Ϊ����ȷ������copy-constructor������һ�� int ������
		// ��������parent��XBean,���е��Զ���XBeanҲ��XBean����ֹ�����ϳ������⡣
		ps.println("	" + cls + "(int __, xdb.XBean _xp_, String _vn_) {");
		ps.println("		super(_xp_, _vn_);");
		for (Variable var : variables) var.construct(this, ps, "		");
		ps.println("	}");
		ps.println("");
		ps.println("	public " + cls + "() {");
		ps.println("		this(0, null, null);");
		ps.println("	}");
		ps.println("");

		generateImplementCopyConstruct(ps);

        generateMarshal(this, this.variables, ps, true, "    ");


		// copy toData toBean
		ps.println("	@Override");
		ps.println("	public xbean." + cls + " copy() {");	
		Main._xdb_verify_(ps, "		");
		ps.println("		return new " + cls + "(this);");
		ps.println("	}");
		ps.println("");	
		ps.println("	@Override");
		ps.println("	public xbean." + cls + " toData() {");
		Main._xdb_verify_(ps, "		");
		ps.println("		return new Data(this);");
		ps.println("	}");
		ps.println("");	
		ps.println("	public xbean." + cls + " toBean() {");
		Main._xdb_verify_(ps, "		");
		ps.println("		return new " + cls + "(this); // same as copy()");
		ps.println("	}");
		ps.println("");	
		ps.println("	@Override");
		ps.println("	public xbean." + cls + " toDataIf() {");
		Main._xdb_verify_(ps, "		");
		ps.println("		return new Data(this);");
		ps.println("	}");
		ps.println("");	
		ps.println("	public xbean." + cls + " toBeanIf() {");
		Main._xdb_verify_(ps, "		");
		ps.println("		return this;");
		ps.println("	}");
		ps.println("");	

		// const
		ps.println("	@Override");
		ps.println("	public xdb.Bean toConst() {");	
		Main._xdb_verify_(ps, "		");
		ps.println("		return new Const();");
		ps.println("	}");
		ps.println("");	

		// getter setter
		for (Variable var : variables) var.getter(this, ps, "	");
		for (Variable var : variables) var.setter(this, ps, "	");

		// equals
		ps.println("	@Override");
		ps.println("	public final boolean equals(Object _o1_) {");
		Main._xdb_verify_(ps, "		");
		ps.println("		" + cls + " _o_ = null;");
		ps.println("		if ( _o1_ instanceof " + cls + " ) _o_ = (" + cls + ")_o1_;");
		ps.println("		else if ( _o1_ instanceof " + cls + ".Const ) _o_ = ((" + cls + ".Const)_o1_).nThis();");
		ps.println("		else return false;");

		for (Variable var : variables) var.equals(ps, "		");
		ps.println("		return true;");
		ps.println("	}");
		ps.println("");	

		// hashCode
		ps.println("	@Override");
		ps.println("	public final int hashCode() {");
		Main._xdb_verify_(ps, "		");
		ps.println("		int _h_ = 0;");
		for (Variable var : variables) var.hashCode(ps, "		");
		ps.println("		return _h_;");
		ps.println("	}");
		ps.println("");	

		// toString
		ps.println("	@Override");
		ps.println("	public String toString() {");
		Main._xdb_verify_(ps, "		");
		ps.println("		StringBuilder _sb_ = new StringBuilder();");
		ps.println("		_sb_.append(\"(\");");
		boolean first = true;
		for (Variable var : variables) {
			if (false == first) ps.println("		_sb_.append(\",\");");
			var.toString(ps, "		");
			first = false;
		}
		ps.println("		_sb_.append(\")\");");
		ps.println("		return _sb_.toString();");
		ps.println("	}");
		ps.println("");	

		// newListenable
		ps.println("	@Override");
		ps.println("	public xdb.logs.Listenable newListenable() {");
		ps.println("		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();");	
		for (Variable var : variables) {
			ps.println("		lb.add(" + var.newListenable(this) + ");");
		}
		ps.println("		return lb;");
		ps.println("	}");
		ps.println("");	

		////////////////////////////////////////////////
		// Const implement
		generateNestConstImeplement(ps, "	");
		ps.println("");
		isData = true;
		generateNestDataImeplement(ps, "	");
		isData = false;
		ps.println("}");

		ps.close();
	}

	private void generateNestDataImplementCopyConstruct(PrintStream ps) {
		String cls = getName();
		ps.println("		Data(xbean." + cls + " _o1_) {");
		if (isAny()) {
			ps.println("			throw new UnsupportedOperationException();");
			ps.println("		}");
			ps.println("");
			return;
		}

		ps.println("			if (_o1_ instanceof " + cls + ") assign((" + cls + ")_o1_);");
		ps.println("			else if (_o1_ instanceof " + cls + ".Data) assign((" + cls + ".Data)_o1_);");
		ps.println("			else if (_o1_ instanceof " + cls + ".Const) assign(((" + cls + ".Const)_o1_).nThis());");
		ps.println("			else throw new UnsupportedOperationException();");
		ps.println("		}");
		ps.println("");

		ps.println("		private void assign(" + cls + " _o_) {");
		for (Variable var : variables) var.deepCopy(this, ps, "			");
		ps.println("		}");
		ps.println("");

		ps.println("		private void assign(" + cls + ".Data _o_) {");
		for (Variable var : variables) var.deepCopy(this, ps, "			");
		ps.println("		}");
		ps.println("");
	}

	private void generateNestDataImeplement(PrintStream ps, String p) {
		String cls = getName();

		ps.println(p + "public static final class Data implements xbean." + cls + " {");

		for (Variable var : variables) var.declare(ps, p + "	");
		ps.println("");

		ps.println(p + "	@Override");
		ps.println(p + "	public void _reset_unsafe_() {");
		ps.println(p + "		throw new UnsupportedOperationException();");
		ps.println(p + "	}");
		ps.println("");

		ps.println(p + "	public Data() {");
		for (Variable var : variables) var.construct(this, ps, p + "		");
		ps.println(p + "	}");
		ps.println("");
		generateNestDataImplementCopyConstruct(ps);

		// marshal implement
		generateMarshal(this, this.variables, ps, false, p);

		// copy toData toBean
		ps.println(p + "	@Override");
		ps.println(p + "	public xbean." + cls + " copy() {");	
		ps.println(p + "		return new Data(this);");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	@Override");
		ps.println(p + "	public xbean." + cls + " toData() {");
		ps.println(p + "		return new Data(this);");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	public xbean." + cls + " toBean() {");
		ps.println(p + "		return new " + cls + "(this, null, null);");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	@Override");
		ps.println(p + "	public xbean." + cls + " toDataIf() {");
		ps.println(p + "		return this;");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	public xbean." + cls + " toBeanIf() {");
		ps.println(p + "		return new " + cls + "(this, null, null);");
		ps.println(p + "	}");
		ps.println("");	

		ps.println(p + "	// xdb.Bean interface. Data Unsupported");
		ps.println(p + "	public boolean xdbManaged() { throw new UnsupportedOperationException(); }");
		ps.println(p + "	public xdb.Bean xdbParent() { throw new UnsupportedOperationException(); }");
		ps.println(p + "	public String xdbVarname()  { throw new UnsupportedOperationException(); }");
		ps.println(p + "	public Long    xdbObjId()   { throw new UnsupportedOperationException(); }");
		ps.println(p + "	public xdb.Bean toConst()   { throw new UnsupportedOperationException(); }");
		ps.println(p + "	public boolean isConst()    { return false; }");
		ps.println(p + "	public boolean isData()     { return true; }");
		ps.println("");	

		// getter setter
		for (Variable var : variables) var.getterData(this, ps, p + "	");
		for (Variable var : variables) var.setterData(this, ps, p + "	");

		// equals
		ps.println(p + "	@Override");
		ps.println(p + "	public final boolean equals(Object _o1_) {");
		ps.println(p + "		if (!(_o1_ instanceof " + cls + ".Data)) return false;");
		ps.println(p + "		" + cls + ".Data _o_ = (" + cls + ".Data) _o1_;");
		for (Variable var : variables) var.equals(ps, "			");
		ps.println(p + "		return true;");
		ps.println(p + "	}");
		ps.println("");

		// hashCode
		ps.println(p + "	@Override");
		ps.println(p + "	public final int hashCode() {");
		ps.println(p + "		int _h_ = 0;");
		for (Variable var : variables) var.hashCode(ps, p + "		");
		ps.println(p + "		return _h_;");
		ps.println(p + "	}");
		ps.println("");	

		// toString
		ps.println(p + "	@Override");
		ps.println(p + "	public String toString() {");
		ps.println(p + "		StringBuilder _sb_ = new StringBuilder();");
		ps.println(p + "		_sb_.append(\"(\");");
		boolean first = true;
		for (Variable var : variables) {
			if (false == first) ps.println(p + "		_sb_.append(\",\");");
			var.toString(ps, p + "		");
			first = false;
		}
		ps.println(p + "		_sb_.append(\")\");");
		ps.println(p + "		return _sb_.toString();");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "}");
	}

	private void generateNestConstImeplement(PrintStream ps, String p) {
		String N = getName();
		String Nthis = N + ".this";

		ps.println(p + "private class Const implements xbean." + N + " {");
		// copy
		ps.println(p + "	" + N + " nThis() {");
		ps.println(p + "		return " + Nthis + ";");
		ps.println(p + "	}");
		ps.println("");
		ps.println(p + "	@Override");
		ps.println(p + "	public void _reset_unsafe_() {");
		ps.println(p + "		throw new UnsupportedOperationException();");
		ps.println(p + "	}");
		ps.println("");

		ps.println(p + "	@Override");
		ps.println(p + "	public xbean." + N + " copy() {");
		ps.println(p + "		return " + Nthis + ".copy();");
		ps.println(p + "	}");
		ps.println("");
		ps.println(p + "	@Override");
		ps.println(p + "	public xbean." + N + " toData() {");
		ps.println(p + "		return " + Nthis + ".toData();");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	public xbean." + N + " toBean() {");
		ps.println(p + "		return " + Nthis + ".toBean();");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	@Override");
		ps.println(p + "	public xbean." + N + " toDataIf() {");
		ps.println(p + "		return " + Nthis + ".toDataIf();");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	public xbean." + N + " toBeanIf() {");
		ps.println(p + "		return " + Nthis + ".toBeanIf();");
		ps.println(p + "	}");
		ps.println("");	

		// getter setter
		for (Variable var : variables) var.getterConst(this, ps, p + "	");
		for (Variable var : variables) var.setterConst(this, ps, p + "	");
		// const
		ps.println(p + "	@Override");
		ps.println(p + "	public xdb.Bean toConst() {");	
		Main._xdb_verify_(ps, p + "		");
		ps.println(p + "		return this;");
		ps.println(p + "	}");
		ps.println("");	
		// const
		ps.println(p + "	@Override");
		ps.println(p + "	public boolean isConst() {");	
		Main._xdb_verify_(ps, p + "		");
		ps.println(p + "		return true;");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	@Override");
		ps.println(p + "	public boolean isData() {");	
		ps.println(p + "		return " + Nthis + ".isData();");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	@Override");
		ps.println(p + "	public OctetsStream marshal(OctetsStream _os_) {");
		ps.println(p + "		return " + Nthis + ".marshal(_os_);");
		ps.println(p + "	}");
		ps.println("");
		ps.println(p + "	@Override");
		ps.println(p + "	public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {");
		Main._xdb_verify_(ps, p + "		");
		ps.println(p + "		throw new UnsupportedOperationException();");
		ps.println(p + "	}");
		ps.println("");
		ps.println(p + "	@Override");
		ps.println(p + "	public xdb.Bean xdbParent() {");
		ps.println(p + "		return " + Nthis + ".xdbParent();");
		ps.println(p + "	}");
		ps.println("");
		ps.println(p + "	@Override");
		ps.println(p + "	public boolean xdbManaged() {");
		ps.println(p + "		return " + Nthis + ".xdbManaged();");
		ps.println(p + "	}");
		ps.println("");
		ps.println(p + "	@Override");
		ps.println(p + "	public String xdbVarname() {");
		ps.println(p + "		return " + Nthis + ".xdbVarname();");
		ps.println(p + "	}");
		ps.println("");
		ps.println(p + "	@Override");
		ps.println(p + "	public Long xdbObjId() {");
		ps.println(p + "		return " + Nthis + ".xdbObjId();");
		ps.println(p + "	}");
		ps.println("");
		ps.println(p + "	@Override");
		ps.println(p + "	public boolean equals(Object obj) {");
		ps.println(p + "		return " + Nthis + ".equals(obj);");
		ps.println(p + "	}");
		ps.println("");
		ps.println(p + "	@Override");
		ps.println(p + "	public int hashCode() {");
		ps.println(p + "		return " + Nthis + ".hashCode();");
		ps.println(p + "	}");
		ps.println("");
		ps.println(p + "	@Override");
		ps.println(p + "	public String toString() {");
		ps.println(p + "		return " + Nthis + ".toString();");
		ps.println(p + "	}");
		ps.println("");
		ps.println(p + "}");
	}

	private void generateInterface() {
		String classname = getName();
		PrintStream ps = Main.openBeanFile(classname);

		ps.println("");
		ps.println("package xbean;");
		ps.println("");
		ps.println("public interface " + classname + " extends xdb.Bean {");

		ps.println("	public " + classname + " copy(); // deep clone");
		ps.println("	public " + classname + " toData(); // a Data instance");
		ps.println("	public " + classname + " toBean(); // a Bean instance");
		ps.println("	public " + classname + " toDataIf(); // a Data instance If need. else return this");
		ps.println("	public " + classname + " toBeanIf(); // a Bean instance If need. else return this");
		ps.println("");
		// enums
		for (Enum e : enums)
			e.print(ps, "	");
		if (!enums.isEmpty())
			ps.println("");

		// getter
		for (Variable var : variables) var.getterInterface(this, ps, "	");
		ps.println("");

		// setter
		for (Variable var : variables) var.setterInterface(this, ps, "	");

		ps.println("}");

		ps.close();
	}

	void make() {
		Type.setCurrentBean(this);
		generateInterface();
		generateImplement();
		Type.setCurrentBean(null);
	}

	static void make(Collection<XBean> beans) {
		// generate Pod
		String classname = "Pod";
		PrintStream ps = Main.openBeanFile(classname);
		ps.println("");
		ps.println("package xbean;");
		ps.println("");
		ps.println("/**");
		ps.println(" * bean factory");
		ps.println(" */");
		ps.println("public final class " + classname + " {");

		for (XBean bean : beans) {
			final String bn = bean.getName();
			ps.println("	public static xdb.util.BeanPool<" + bn + "> pool" + bn + " = new xdb.util.BeanPool<" + bn + ">() {");
			ps.println("		@Override");
			ps.println("		protected " + bn + " newBean() {");
			ps.println("			return new xbean.__." + bn + "();");
			ps.println("		}");
			ps.println("	};");
			ps.println("");
			ps.println("	public static " + bn + " new" + bn + "() {");
			ps.println("		return pool" + bn + ".get();");
			ps.println("	}");
			ps.println("");
			ps.println("	public static void _reset_unsafe_add_(" + bn + " bean) {");
			ps.println("		pool" + bn + "._reset_unsafe_add_(bean);");
			ps.println("	}");
			ps.println("");
			ps.println("	public static void padd(" + bn + " bean) {");
			ps.println("		xdb.Procedure.padd(bean, pool" + bn + ");");
			ps.println("	}");
			ps.println("");
			ps.println("	public static " + bn + " new" + bn + "Data() {");
			ps.println("		return new xbean.__." + bn + ".Data();");
			ps.println("	}");
			ps.println("");
		}

		ps.println("}");
		ps.close();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getTypeName() {
		return "xbean." + getName();
	}

	@Override
	public String getBoxingName() {
		return getTypeName();
	}

	@Override
	public void construct(XBean bean, Variable var, PrintStream ps, String prefix) {
		String varname = var.getname();
		if (bean.isData()) {
			ps.println(prefix + varname + " = new " + getName() + ".Data();");
		} else {
			ps.println(prefix + varname + " = new " + getName() + "(0, this, " + Main.quote(varname) + ");");
		}
	}

	@Override
	public void print4reset(XBean bean, Variable var, PrintStream ps, String prefix) {
		String varname = var.getname();
		ps.println(prefix + varname + "._reset_unsafe_();");
	}

	@Override
	public String deepCopy(boolean isData, Variable var, String fullvarname) {
		if (isData)
			return "new " + getName() + ".Data(" + fullvarname + ")";
		return "new " + getName() + "(" + fullvarname + ", this, " + Main.quote(var.getname()) + ")";
	}

	@Override
	public void deepCopy(boolean isData, Variable var, PrintStream ps, String prefix) {
		if (isData)
			ps.println(prefix + var.getname() + " = new " + getName()
					+ ".Data(_o_." + var.getname() + ");");
		else
			ps.println(prefix + var.getname() + " = new " + getName()
					+ "(_o_." + var.getname() + ", this, " + Main.quote(var.getname()) + ");");
	}

	@Override
	public int deepcopy4cache(PrintStream ps, String pf) {
		if (ps == null)
			return 1; // �����ж��Ƿ���Ҫ����

		ps.println(pf + "public static " + this.getGetterName() + " deepcopy(" + this.getGetterName() + " _o_) {");
		ps.println(pf + "	return _o_.toDataIf();");
		ps.println(pf + "}");
		return 1;
	}

	@Override
	public boolean isConstant() {
		return false;
	}
	
	@Override
	public void marshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		ps.println(prefix + localvarname + ".marshal(_os_);");
	}

	@Override
	public void unmarshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		ps.println(prefix + localvarname + ".unmarshal(_os_);");
	}

	@Override
	public String defineSetParent(XBean bean, Variable var, String definename) {
		if (bean.isData())
			return defineNoParent(definename);

		return getTypeName() + " " + definename + " = new " + getName()
		+ "(0, this, " + Main.quote(var.getname()) + ");";
	}

	@Override
	public String defineNoParent(String varname) {
		if (Type.getCurrentBean() != null && Type.getCurrentBean().isData())
			return getTypeName() + " " + varname + " = xbean.Pod.new" + getName() + "Data();";
		return getTypeName() + " " + varname + " = xbean.Pod.new" + getName() + "();";
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
	public void getterInterface(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "public xbean." + name + " get" + var.getName() + "(); // " + var.getComment());
	}

	@Override
	public void getter(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public xbean." + name + " get" + var.getName() + "() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return " + var.getname() + ";");
		ps.println(prefix + "}");
		ps.println();
	}

	@Override
	public void getterConst(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public xbean." + name + " get" + var.getName() + "() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return xdb.Consts.toConst(" + var.getname() + ");");
		ps.println(prefix + "}");
		ps.println();
	}

	@Override
	public void getterData(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public xbean." + name + " get" + var.getName() + "() { // " + var.getComment());
		ps.println(prefix + "	return " + var.getname() + ";");
		ps.println(prefix + "}");
		ps.println();
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

	@Override
	public String newListenable(XBean bean, Variable var) {
		return "new xdb.logs.ListenableChanged().setVarName(" + Main.quote(var.getname()) + ")";
		// return var.getname() + ".newListenable().setVarName(" + Main.quote(var.getname()) + ")";
	}

	public void printTableSelectMethod(PrintStream ps, String K) {
		final String V = this.getBoxingName();
		if (false == this.isAny()) {
			ps.println("	public static " + V + " select(" + K + " key) {");
			ps.println("		return getTable().select(key, new xdb.TField<" + V + ", " + V + ">() {");
			ps.println("			public " + V + " get(" + V + " v) { return v.toData(); }");
			ps.println("		});");
			ps.println("	}");
			ps.println("");
		}
		for (Variable var : variables)
			var.printSelect(ps, K, V);
	}

	public void printMeta(PrintStream ps, String prefix) {
		ps.println(prefix + "Bean bean = new Bean(" + Main.quote(getName())
				+ ", " + isAny() + ", " + isConstant() + ");");
		for (Variable var : this.variables) {
			ps.println(prefix + "super.addVariableFor(bean");
			ps.println(prefix + "	, " + var.getId());
			var.printMetaData(ps, prefix + "	");
			ps.println(prefix + "	);");
		}
		ps.println(prefix + "super.addBean(bean);");
	}

	///////caijiacheng 2009-9-1------///
	private boolean hasModify = false;
	public void setHasModify(){ hasModify = true; }
	public boolean hasModify(){return hasModify; }

	public Variable getVariable(String name) {				
		for (  Variable var : variables ) {
			if ( var.getname().equals(name) )
				return var;
		}
		return null;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public void skipVarMarshal(Variable var) {
		//System.out.println("Generate marshal: Skip var: " + this.name + "." + var.getname() );
		return;
	}


	void transformMake() {
		Type.setCurrentBean(this);
		generateInterface();
		generateTransformImplement();
		Type.setCurrentBean(null);
	}


	private void generateTransformImplement() {
		String cls = getName();
		PrintStream ps = Main.openBean__File(cls);

		ps.println("");
		ps.println("package xbean.__;");
		ps.println("");
		ps.println("import com.goldhuman.Common.Marshal.OctetsStream;");
		ps.println("import com.goldhuman.Common.Marshal.MarshalException;");
		ps.println("");
		ps.println("public final class " + cls + " extends xdb.XBean implements xbean." + cls + " {");

		// variable declare
		for (Variable var : variables) 
		{
			var.declare(ps, "	");
		}
		ps.println("");

		// constructor initial. Ϊ����ȷ������copy-constructor������һ�� int ������
		// ��������parent��XBean,���е��Զ���XBeanҲ��XBean����ֹ�����ϳ������⡣
		ps.println("	" + cls + "(int __, xdb.XBean _xp_, String _vn_) {");
		ps.println("		super(_xp_, _vn_);");
		for (Variable var : variables)
		{
			var.construct(this, ps, "		");
		}

		ps.println("	}");
		ps.println("");
		ps.println("	public " + cls + "() {");
		ps.println("		this(0, null, null);");
		ps.println("	}");
		ps.println("");

		generateImplementCopyConstruct(ps);

		// marshal implement
		ps.println("	@Override");
		ps.println("	public final OctetsStream marshal(OctetsStream _os_) {");
		if (isAny())
			ps.println("		throw new UnsupportedOperationException();");
		else {
			Main._xdb_verify_(ps, "		");
			for (Variable var : variables)
			{
				if( var.getModifyType() == Variable.MODIFY_TYPES.REMOVE )
					skipVarMarshal(var);
				else
					var.marshal(this, ps, "		");
			}
			ps.println("		return _os_;");
		}
		ps.println("	}");
		ps.println("");	

		// unmarshal implement
		ps.println("	@Override");
		ps.println("	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {");
		if (isAny())
			ps.println("		throw new UnsupportedOperationException();");
		else {
			Main._xdb_verify_(ps, "		");
			//unmarshal ���и�˳�����⣬���Ի��ô�DataMeta�ж�ȡ��������ȻDataMeta˳�򶼴��˾��鷳��
			DatabaseMetaData.Bean bn = DatabaseMetaData.getInstance().getBean(this.getName());

			if ( bn == null )//�µ�xml��ɾ����Xbean
			{
				for (Variable var : variables) var.unmarshal(this, ps, "		");
			}
			else
			{	
				for ( DatabaseMetaData.Bean.Variable xb : bn.getVariables() )
				{
					Variable var = this.getVariable(xb.getName());
					assert(var != null);					
					var.unmarshal(this, ps, "		");
				}
			}
			ps.println("		return _os_;");
		}
		ps.println("	}");
		ps.println("");	

		// copy toData toBean
		ps.println("	@Override");
		ps.println("	public xbean." + cls + " copy() {");	
		Main._xdb_verify_(ps, "		");
		ps.println("		return new " + cls + "(this);");
		ps.println("	}");
		ps.println("");	
		ps.println("	@Override");
		ps.println("	public xbean." + cls + " toData() {");
		Main._xdb_verify_(ps, "		");
		ps.println("		return new Data(this);");
		ps.println("	}");
		ps.println("");	
		ps.println("	public xbean." + cls + " toBean() {");
		Main._xdb_verify_(ps, "		");
		ps.println("		return new " + cls + "(this); // same as copy()");
		ps.println("	}");
		ps.println("");	
		ps.println("	@Override");
		ps.println("	public xbean." + cls + " toDataIf() {");
		Main._xdb_verify_(ps, "		");
		ps.println("		return new Data(this);");
		ps.println("	}");
		ps.println("");	
		ps.println("	public xbean." + cls + " toBeanIf() {");
		Main._xdb_verify_(ps, "		");
		ps.println("		return this;");
		ps.println("	}");
		ps.println("");	

		// const
		ps.println("	@Override");
		ps.println("	public xdb.Bean toConst() {");	
		Main._xdb_verify_(ps, "		");
		ps.println("		return new Const();");
		ps.println("	}");
		ps.println("");	

		// getter setter
		for (Variable var : variables)
		{
			var.getter(this, ps, "	");
		}
		for (Variable var : variables)
		{
			var.setter(this, ps, "	");
		}
		// equals
		ps.println("	@Override");
		ps.println("	public final boolean equals(Object _o1_) {");
		Main._xdb_verify_(ps, "		");
		ps.println("		" + cls + " _o_ = null;");
		ps.println("		if ( _o1_ instanceof " + cls + " ) _o_ = (" + cls + ")_o1_;");
		ps.println("		else if ( _o1_ instanceof " + cls + ".Const ) _o_ = ((" + cls + ".Const)_o1_).nThis();");
		ps.println("		else return false;");

		for (Variable var : variables) var.equals(ps, "		");
		ps.println("		return true;");
		ps.println("	}");
		ps.println("");	

		// hashCode
		ps.println("	@Override");
		ps.println("	public final int hashCode() {");
		Main._xdb_verify_(ps, "		");
		ps.println("		int _h_ = 0;");
		for (Variable var : variables) var.hashCode(ps, "		");
		ps.println("		return _h_;");
		ps.println("	}");
		ps.println("");	

		// toString
		ps.println("	@Override");
		ps.println("	public String toString() {");
		Main._xdb_verify_(ps, "		");
		ps.println("		StringBuilder _sb_ = new StringBuilder();");
		ps.println("		_sb_.append(\"(\");");
		boolean first = true;
		for (Variable var : variables) {
			if (false == first) ps.println("		_sb_.append(\",\");");
			var.toString(ps, "		");
			first = false;
		}
		ps.println("		_sb_.append(\")\");");
		ps.println("		return _sb_.toString();");
		ps.println("	}");
		ps.println("");	

		// newListenable
		ps.println("	@Override");
		ps.println("	public xdb.logs.Listenable newListenable() {");
		ps.println("		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();");	
		for (Variable var : variables) {
			ps.println("		lb.add(" + var.newListenable(this) + ");");
		}
		ps.println("		return lb;");
		ps.println("	}");
		ps.println("");	

		////////////////////////////////////////////////
		// Const implement
		generateNestConstImeplement(ps, "	");
		ps.println("");
		isData = true;
		generateTransformNestDataImeplement(ps, "	");
		isData = false;
		ps.println("}");

		ps.close();
	}

	private void generateTransformNestDataImeplement(PrintStream ps, String p) {
		String cls = getName();

		ps.println(p + "public static final class Data implements xbean." + cls + " {");

		for (Variable var : variables) var.declare(ps, p + "	");
		ps.println("");

		ps.println(p + "	@Override");
		ps.println(p + "	public void _reset_unsafe_() {");
		ps.println(p + "		throw new UnsupportedOperationException();");
		ps.println(p + "	}");
		ps.println("");
		
		ps.println(p + "	public Data() {");
		for (Variable var : variables) var.construct(this, ps, p + "		");
		ps.println(p + "	}");
		ps.println("");
		generateNestDataImplementCopyConstruct(ps);

		// marshal implement
		ps.println(p + "	@Override");
		ps.println(p + "	public final OctetsStream marshal(OctetsStream _os_) {");
		if (isAny())
			ps.println(p + "		throw new UnsupportedOperationException();");
		else {
			//for (Variable var : variables) var.marshal(this, ps, p + "		");
			for (Variable var : variables)
			{
				if( var.getModifyType() == Variable.MODIFY_TYPES.REMOVE )
					skipVarMarshal(var);
				else
					var.marshal(this, ps, "		");
			}
			ps.println(p + "		return _os_;");
		}
		ps.println(p + "	}");
		ps.println("");	

		// unmarshal implement
		ps.println(p + "	@Override");
		ps.println(p + "	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {");
		if (isAny())
			ps.println(p + "		throw new UnsupportedOperationException();");
		else {
			//for (Variable var : variables) var.unmarshal(this, ps, p + "		");					
			DatabaseMetaData.Bean bn = DatabaseMetaData.getInstance().getBean(this.getName());

			if ( bn == null )//����ӵ�Xbean
			{
				for (Variable var : variables) var.unmarshal(this, ps, "		");
			}
			else
			{	
				for ( DatabaseMetaData.Bean.Variable xb : bn.getVariables() )
				{
					Variable var = this.getVariable(xb.getName());
					assert(var != null);					
					var.unmarshal(this, ps, "		");
				}
			}
			
			ps.println(p + "		return _os_;");
		}
		ps.println(p + "	}");
		ps.println("");	

		// copy toData toBean
		ps.println(p + "	@Override");
		ps.println(p + "	public xbean." + cls + " copy() {");	
		ps.println(p + "		return new Data(this);");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	@Override");
		ps.println(p + "	public xbean." + cls + " toData() {");
		ps.println(p + "		return new Data(this);");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	public xbean." + cls + " toBean() {");
		ps.println(p + "		return new " + cls + "(this, null, null);");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	@Override");
		ps.println(p + "	public xbean." + cls + " toDataIf() {");
		ps.println(p + "		return this;");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "	public xbean." + cls + " toBeanIf() {");
		ps.println(p + "		return new " + cls + "(this, null, null);");
		ps.println(p + "	}");
		ps.println("");	

		ps.println(p + "	// xdb.Bean interface. Data Unsupported");
		ps.println(p + "	public boolean xdbManaged() { throw new UnsupportedOperationException(); }");
		ps.println(p + "	public xdb.Bean xdbParent() { throw new UnsupportedOperationException(); }");
		ps.println(p + "	public String xdbVarname()  { throw new UnsupportedOperationException(); }");
		ps.println(p + "	public Long    xdbObjId()   { throw new UnsupportedOperationException(); }");
		ps.println(p + "	public xdb.Bean toConst()   { throw new UnsupportedOperationException(); }");
		ps.println(p + "	public boolean isConst()    { return false; }");
		ps.println(p + "	public boolean isData()     { return true; }");
		ps.println("");	

		// getter setter
		for (Variable var : variables) var.getterData(this, ps, p + "	");
		for (Variable var : variables) var.setterData(this, ps, p + "	");

		// equals
		ps.println(p + "	@Override");
		ps.println(p + "	public final boolean equals(Object _o1_) {");
		ps.println(p + "		if (!(_o1_ instanceof " + cls + ".Data)) return false;");
		ps.println(p + "		" + cls + ".Data _o_ = (" + cls + ".Data) _o1_;");
		for (Variable var : variables) var.equals(ps, "			");
		ps.println(p + "		return true;");
		ps.println(p + "	}");
		ps.println("");

		// hashCode
		ps.println(p + "	@Override");
		ps.println(p + "	public final int hashCode() {");
		ps.println(p + "		int _h_ = 0;");
		for (Variable var : variables) var.hashCode(ps, p + "		");
		ps.println(p + "		return _h_;");
		ps.println(p + "	}");
		ps.println("");	

		// toString
		ps.println(p + "	@Override");
		ps.println(p + "	public String toString() {");
		ps.println(p + "		StringBuilder _sb_ = new StringBuilder();");
		ps.println(p + "		_sb_.append(\"(\");");
		boolean first = true;
		for (Variable var : variables) {
			if (false == first) ps.println(p + "		_sb_.append(\",\");");
			var.toString(ps, p + "		");
			first = false;
		}
		ps.println(p + "		_sb_.append(\")\");");
		ps.println(p + "		return _sb_.toString();");
		ps.println(p + "	}");
		ps.println("");	
		ps.println(p + "}");
	}

	@Override
	public void depends(Set<Type> types) {
		if (Type.addDepend(types, this)) {
			for (Variable var : variables) {
				var.getVartype().depends(types);
			}
		}
	}

	/**
	 * add by cjc 
	 * ͨ��DataBaseMetaData����Ϣ��var type��Ϣ����������һ���µ�xbean
	 * 
	 */
	public XBean(DatabaseMetaData.Bean db) {
		name = db.getName();
		Main.verifyName(name);
		if (name.equals("Pod"))
			throw new RuntimeException("name of 'Pod' has been used by xdb");
		Type.add(this);		
		
		
		DatabaseMetaData.Bean be = DatabaseMetaData.getInstance().getBean(name);
		assert(be != null);
		anyMaybe = be.isAny();	
		for ( DatabaseMetaData.Bean.Variable vb : be.getVariables() )
		{	
			add(new Variable(vb));
		}
	}	
	/**
	 * add by cjc . 
	 * ��������Ҫ��ֻ��enums��xbean�ſ�.����
	 */
	public boolean isEnumsOnly()
	{
		return variables.size() == 0 && enums.size() > 0; 
	}
	
}

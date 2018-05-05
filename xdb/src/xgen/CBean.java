package xgen;

import org.w3c.dom.*;

import xdb.util.DatabaseMetaData;

import java.util.*;
import java.io.*;

public class CBean extends Type {

	private String name;
	private List<Variable> variables = new ArrayList<Variable>();
	private List<Enum> enums = new ArrayList<Enum>();
	private Set<String> varNames = new HashSet<String>();

    @Override
    public short getTag() {
        return Tag.BEAN;
    }
    @Override
    public boolean isPredictable() {
        return false;
    }

    @Override
	public void addOwnerTable(Table owner) {
		super.addOwnerTable(owner);
		for (Variable var : variables)
			var.getVartype().addOwnerTable(owner);
	}

	private void add(Variable var) {
		if (false == varNames.add(var.getname()))
			throw new IllegalArgumentException("duplicate varname." + var.getname() + "@" + name);
		variables.add(var);
	}

	public void collectForeigns(Set<String> foreigns) {
		for (Variable var : variables)
			var.collectForeigns(foreigns);
	}

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
	public void verifyForeign(Xdb xdb, Foreign foreign) {
		foreign.throwIf(null != foreign.getKey(), "[cbean] need value only.");
		if (null != foreign.getValue()) {
			Table table = xdb.getTable(foreign.getValue());
			foreign.throwIf(null == table, "[cbean] table not exist.");
			foreign.throwIf(table.isMemory(), "[cbean] foreign table is memory");
			foreign.throwIf(table.getKeyType() != this, "[cbean] type not match");
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

	public CBean(Element self) {
		name = self.getAttribute("name").trim();
		Main.verifyName(name);
		if (name.equals("Pod"))
			throw new RuntimeException("name of 'Pod' has been used by xdb");
		Type.add(this);

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

	public void compile(Xdb xdb) {
		for (Variable var : variables) {
			var.compile(xdb);
			if (!var.getVartype().isConstant())
				throw new RuntimeException(this.getName() + " is constant bean. only immutable variable is available");
		}
	}

	public void verify(Xdb xdb) {
		for (Variable var : variables)
			var.verify(xdb, this);
	}

	void make() {
		boolean noverifySaved = Main.noverify;
		Main.noverify = true;
		_make();
		Main.noverify = noverifySaved;
	}

	private void _make() {
		String classname = getName();
		PrintStream ps = Main.openBeanFile(classname);
		ps.println("");
		ps.println("package xbean;");
		ps.println("");
		ps.println("import com.goldhuman.Common.Marshal.Marshal;");
		ps.println("import com.goldhuman.Common.Marshal.MarshalException;");
		ps.println("import com.goldhuman.Common.Marshal.OctetsStream;");
		ps.println("");
		ps.println("public class " + classname + " implements Marshal, Comparable<" + classname +"> {");
		ps.println("");
		for (Variable var : variables) var.declare(ps, "	");
		ps.println("");

		// enums
		for (Enum e : enums)
			e.print(ps, "	");
		if (!enums.isEmpty())
			ps.println("");

		// default construct
		ps.println("	public " + classname + "() {");
		for (Variable var : variables) var.construct(null, ps, "		");
		ps.println("	}");
		ps.println("");

		if (!this.variables.isEmpty()) // is same of default construct
		{
			// construct with parameters
			StringBuilder params = new StringBuilder();
			boolean isfirst = true;
			for (Variable var : this.variables) {
				if (isfirst) isfirst = false; else params.append(", ");
				params.append(var.getVartype().getTypeName()).append(" ").append(var.getname());
			}
			ps.println("	public " + classname + "(" + params.toString() + ") {");
			for (Variable var : variables)
				ps.println("		this." + var.getname() + " = " + var.getname() + ";");
			ps.println("	}");
			ps.println("");
		}

		for (Variable var : variables) var.getter(null, ps, "	");

        XBean.generateMarshal(null, this.variables, ps, true, "");

		// Comparable, Object
		ps.println("	@Override");
		ps.println("	public int compareTo(" + this.getName() + " _o_) {");
		ps.println("		if (_o_ == this)");
		ps.println("			return 0;");
		ps.println("		int _c_ = 0;");
		for (Variable var : variables) {
		ps.println("		_c_ = " + var.compareto() + ";");
		ps.println("		if (0 != _c_) return _c_;");
		}
		ps.println("		return _c_;");
		ps.println("	}");
		ps.println("");	
		ps.println("	@Override");
		ps.println("	public boolean equals(Object _o_) {");
		ps.println("		if (_o_ instanceof " + this.getName() + ")");
		ps.println("			return 0 == this.compareTo((" + this.getName() + ")_o_);");
		ps.println("		return false;");
		ps.println("	}");
		ps.println("");	
		ps.println("	@Override");
		ps.println("	public int hashCode() {");
		ps.println("		int _h_ = 0;");
		for (Variable var : variables) var.hashCode(ps, "		");
		ps.println("		return _h_;");
		ps.println("	}");
		ps.println("");	
		ps.println("}");
		ps.close();
	}

	@Override
	public String compareto(String vn1, String vn2) {
		return vn1 + ".compareTo(" + vn2 + ")";
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
	public String notEquals(String varname) {
		return "!" + varname + ".equals(_o_." + varname + ")";
	}

	@Override
	public String hashCode(String varname) {
		return varname + ".hashCode()";
	}

	@Override
	public boolean isConstant() {
		return true;
	}

	@Override
	public String defineNoParent(String varname) {
		return getTypeName() + " " + varname + " = new " + this.getTypeName() + "();";
	}

	@Override
	public String defineSetParent(XBean bean, Variable var, String definename) {
		return this.defineNoParent(definename);
	}

	@Override
	public void marshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		ps.println(prefix + localvarname + ".marshal(_os_);");
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

	@Override
	public void construct(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + var.getname() + " = new " + this.getTypeName() + "(" + var.getInitial() + ");");
	}

	@Override
	public void print4reset(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + var.getname() + " = new " + this.getTypeName() + "(" + var.getInitial() + ");");
	}

	@Override
	public void unmarshal(XBean bean, Variable var, PrintStream ps, String prefix, String localvarname) {
		ps.println(prefix + localvarname + ".unmarshal(_os_);");
	}

	@Override
	public void getterInterface(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "public xbean." + name + " get" + var.getName() + "(); // " + var.getComment());
	}

	@Override
	public void getter(XBean bean, Variable var, PrintStream ps, String prefix) {
		if (null != bean) // cbean û�� interface
			ps.println(prefix + "@Override");
		ps.println(prefix + "public xbean." + name + " get" + var.getName() + "() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return " + var.getname() + ";");
		ps.println(prefix + "}");
		ps.println();
	}

	@Override
	public void getterConst(XBean bean, Variable var, PrintStream ps, String prefix) {
		if (null != bean) // cbean û�� interface
			ps.println(prefix + "@Override");
		ps.println(prefix + "public xbean." + name + " get" + var.getName() + "() { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	return " + var.getname() + ";");
		ps.println(prefix + "}");
		ps.println();
	}

	@Override
	public void getterData(XBean bean, Variable var, PrintStream ps, String prefix) {
		if (null != bean) // cbean û�� interface
			ps.println(prefix + "@Override");
		ps.println(prefix + "public xbean." + name + " get" + var.getName() + "() { // " + var.getComment());
		ps.println(prefix + "	return " + var.getname() + ";");
		ps.println(prefix + "}");
		ps.println();
	}

	@Override
	public void setterInterface(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "public void set" + var.getName() + "(" + this.getTypeName() + " _v_); // " + var.getComment());
	}

	@Override
	public void setterConst(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "(" + this.getTypeName() + " _v_) { // " + var.getComment());
		Main._xdb_verify_(ps, prefix + "	");
		ps.println(prefix + "	throw new UnsupportedOperationException();");
		ps.println(prefix + "}");
		ps.println("");
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
	public void setterData(XBean bean, Variable var, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public void set" + var.getName() + "(" + getTypeName() + " _v_) { // " + var.getComment());
		ps.println(prefix + "	" + var.getname() + " = _v_;");
		ps.println(prefix + "}");
		ps.println("");
	}

	@Override
	public String newListenable(XBean bean, Variable var) {
		return "new xdb.logs.ListenableChanged().setVarName(" + Main.quote(var.getname()) + ")";
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


	@Override
	public void depends(Set<Type> types) {
		if (Type.addDepend(types, this)) {
			for (Variable var : variables) {
				var.getVartype().depends(types);
			}
		}
	}
	
	//����Xtransform�ж�CBean��֧��
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
	
	//add by cjc for Xtransform to support CBean
	void transformMake() {
		boolean noverifySaved = Main.noverify;
		Main.noverify = true;
		_transformMake();
		Main.noverify = noverifySaved;
	}
	private void _transformMake() {
		String classname = getName();
		PrintStream ps = Main.openBeanFile(classname);
		ps.println("");
		ps.println("package xbean;");
		ps.println("");
		ps.println("import com.goldhuman.Common.Marshal.Marshal;");
		ps.println("import com.goldhuman.Common.Marshal.MarshalException;");
		ps.println("import com.goldhuman.Common.Marshal.OctetsStream;");
		ps.println("");
		ps.println("public class " + classname + " implements Marshal, Comparable<" + classname +"> {");
		ps.println("");
		for (Variable var : variables) var.declare(ps, "	");
		ps.println("");

		// default construct
		ps.println("	public " + classname + "() {");
		for (Variable var : variables) var.construct(null, ps, "		");
		ps.println("	}");
		ps.println("");

		if (!this.variables.isEmpty()) // is same of default construct
		{
			// construct with parameters
			StringBuilder params = new StringBuilder();
			boolean isfirst = true;
			for (Variable var : this.variables) {
				if (isfirst) isfirst = false; else params.append(", ");
				params.append(var.getVartype().getTypeName()).append(" ").append(var.getname());
			}
			ps.println("	public " + classname + "(" + params.toString() + ") {");
			for (Variable var : variables)
				ps.println("		this." + var.getname() + " = " + var.getname() + ";");
			ps.println("	}");
			ps.println("");
		}

		for (Variable var : variables) var.getter(null, ps, "	");

		// marshal implement
		ps.println("	@Override");
		ps.println("	public final OctetsStream marshal(OctetsStream _os_) {");
		//for (Variable var : variables) var.marshal(null, ps, "		");
		for (Variable var : variables)
			if( var.getModifyType() == Variable.MODIFY_TYPES.REMOVE )
				skipVarMarshal(var);
			else
				var.marshal(null, ps, "		");
		//
		ps.println("		return _os_;");
		ps.println("	}");
		ps.println("");	

		// unmarshal implement
		ps.println("	@Override");
		ps.println("	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {");
		
		//for (Variable var : variables) var.unmarshal(null, ps, "		");
		DatabaseMetaData.Bean bn = DatabaseMetaData.getInstance().getBean(this.getName());
		if ( bn == null )//����ӵ�Cbean
		{
			for (Variable var : variables) var.unmarshal(null, ps, "		");
		}
		else
		{	
			for ( DatabaseMetaData.Bean.Variable xb : bn.getVariables() )
			{
				Variable var = this.getVariable(xb.getName());
				assert(var != null);					
				var.unmarshal(null, ps, "		");
			}
		}
		//					
		ps.println("		return _os_;");
		ps.println("	}");
		ps.println("");	

		// Comparable, Object
		ps.println("	@Override");
		ps.println("	public int compareTo(" + this.getName() + " _o_) {");
		ps.println("		if (_o_ == this)");
		ps.println("			return 0;");
		ps.println("		int _c_ = 0;");
		for (Variable var : variables) {
		ps.println("		_c_ = " + var.compareto() + ";");
		ps.println("		if (0 != _c_) return _c_;");
		}
		ps.println("		return _c_;");
		ps.println("	}");
		ps.println("");	
		ps.println("	@Override");
		ps.println("	public boolean equals(Object _o_) {");
		ps.println("		if (_o_ instanceof " + this.getName() + ")");
		ps.println("			return 0 == this.compareTo((" + this.getName() + ")_o_);");
		ps.println("		return false;");
		ps.println("	}");
		ps.println("");	
		ps.println("	@Override");
		ps.println("	public int hashCode() {");
		ps.println("		int _h_ = 0;");
		for (Variable var : variables) var.hashCode(ps, "		");
		ps.println("		return _h_;");
		ps.println("	}");
		ps.println("");	
		ps.println("}");
		ps.close();
	}
	
}

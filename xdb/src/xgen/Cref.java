package xgen;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

public class Cref {
	private String ref;

	//////////////////////////////////////////////////////////////////
	// compiled
	private Cache ccache;
	private Table table;
	private List<TypeVariable> typeVariableList = new ArrayList<TypeVariable>();

	static class TypeVariable {
		Type type;
		String variable;
		///////////////
		TypeVariable child;
		TypeVariable sibling;

		TypeVariable(Type type, String variable) {
			this.type = type;
			this.variable = variable;
		}
	}

	String listenerName() {
		StringBuilder sb = new StringBuilder();
		sb.append("_");
		sb.append(Main.toUpper1(table.getName()));
		sb.append("_");
		if (false == typeVariableList.isEmpty()) {
			sb.append(this.getTypeVariable(0).variable);
			sb.append("_");
		}
		sb.append("Listener");
		return sb.toString();
	}

	String listenerArgs() {
		if (typeVariableList.isEmpty()) {
			return "";
		}
		return ", " + Main.quote(this.getTypeVariable(0).variable);
	}

	public Table getTable() {
		return table;
	}

	public List<TypeVariable> getTypeVariableList() {
		return typeVariableList;
	}

	TypeVariable getTypeVariable(int index) {
		if (index >= this.typeVariableList.size())
			return null;
		return this.typeVariableList.get(index);
	}

	Type getLastVariableType() {
		return this.getTypeVariable(this.typeVariableList.size() - 1).type;
	}

	boolean isLastTypeVariable(int index) {
		return index == this.typeVariableList.size() - 1;
	}

	public String getRef() {
		return ref;
	}

	Cref(Element self) {
		ref = self.getAttribute("ref").trim().toLowerCase();
	}

	@Override
	public String toString() {
		return " ccache='" + ccache.getName() + "' ref='" + ref + "'";
	}

	void compile(Cache cache) {
		this.ccache = cache;
		final String [] refs = ref.split("\\.");
		if (refs.length == 0)
			throw new RuntimeException("ref is empty." + this);
		table = cache.getXdb().getTable(refs[0]);
		if (null == table)
			throw new RuntimeException("ref table is not found." + this);

		Type base = table.getValueType();
		for (int i = 1; i < refs.length; ++i) {
			final String variable = refs[i];
			final Type type = compile(base, variable);
			typeVariableList.add(new TypeVariable(type, variable));
			base = type;
		}

		cache.addGroupbyTable(this);
	}

	String getLeftName(String left) {
		StringBuilder sb = new StringBuilder(left);
		for (TypeVariable tv : this.typeVariableList) {
			sb.append(".");
			sb.append(tv.variable);
		}
		return sb.toString();
	}

	String getRightName(String right) {
		return this.getRightName(right, false);
	}

	String getRightName(String right, boolean isTypeBinary) {
		StringBuilder sb = new StringBuilder(right);
		if (this.typeVariableList.size() > 0) {
			int i = 0;
			for (; i < this.typeVariableList.size() - 1; ++i) {
				sb.append(".get");
				sb.append(Main.toUpper1(this.typeVariableList.get(i).variable));
				sb.append("()");
			}
			sb.append(".get");
			sb.append(Main.toUpper1(this.typeVariableList.get(i).variable));
			if (isTypeBinary) {
				sb.append("Copy");
			}
			sb.append("()");
		}
		return sb.toString();
	}
	
	Type compile(Type base, String variable) {
		if (base instanceof XBean) {
			XBean b = (XBean)base;
			Variable v = b.getVariable(variable);
			if (null == v)
				throw new RuntimeException("vairable not found. " + this + " variable='" + variable + "'");
			return v.getVartype();
		}
		if (base instanceof CBean) {
			CBean b = (CBean)base;
			Variable v = b.getVariable(variable);
			if (null == v)
				throw new RuntimeException("vairable not found. " + this + " variable='" + variable + "'");
			return v.getVartype();
		}
		throw new RuntimeException("base type is not a bean." + this + " variable='" + variable + "'");
	}

}

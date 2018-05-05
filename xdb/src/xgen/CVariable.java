package xgen;

import org.w3c.dom.Element;

public class CVariable {
	private String name;

	/////////////////////////////
	// compiled
	private CTable ctable;
	private Variable variable;

	public CTable getCtable() {
		return ctable;
	}

	public Variable getVariable() {
		return variable;
	}

	void compile(CTable ctable) {
		this.ctable = ctable;

		if (ctable.getTable().getValueType() instanceof XBean)
			variable = ((XBean)ctable.getTable().getValueType()).getVariable(name);
		else if (ctable.getTable().getValueType() instanceof CBean)
			variable = ((CBean)ctable.getTable().getValueType()).getVariable(name);

		if (null == variable)
			throw new IllegalStateException("the variable reference not exist. ccache='"
					+ ctable.getCcache().getName() + "' table='"
					+ ctable.getName() + "' variable='" + name + "'");
	}

	public CVariable(Element self) {
		name = self.getAttribute("name").trim().toLowerCase();
	}

	public String getname() {
		return name;
	}

}

package map.map.story.bool;

import map.ai.vm.expr.BooleanExpr;

public abstract class CfgBool<T extends cfg.ectype.ExeCondition> extends BooleanExpr {
	protected final T mcfg;
	public CfgBool(T mcfg) {
		this.mcfg = mcfg;
	}

}

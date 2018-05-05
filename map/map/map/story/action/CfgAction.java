package map.map.story.action;

import map.ai.vm.Expression;
import cfg.ectype.CompareOperator;

public abstract class CfgAction<T extends cfg.ectype.Action> extends Expression {
	final protected T mcfg;
	public CfgAction(T mcfg) {
		this.mcfg = mcfg;
	}

	public static boolean compare(double x, int op, double y) {
		switch (op) {
			case CompareOperator.EQUAL: return x == y;
			case CompareOperator.GREATER: return x > y;
			case CompareOperator.LESS: return x < y;
			case CompareOperator.GREATER_OR_EQUAL: return x >= y;
			case CompareOperator.LESS_OR_EQUAL: return x <= y;
			case CompareOperator.NOT_EQUAL: return x != y;
			default: throw new RuntimeException("unknown CompareOperator:" + op);
		}
	}
}

package map.ai.vm.expr;

import map.ai.vm.Expression;

public abstract class BooleanExpr extends Expression {
	@Override
	public final void enter() {}

	@Override
	@Deprecated
	public final boolean run() {
		throw new RuntimeException("BooleanExpr.run is deprecated! use BooleanExpr.comput alternatively!");
	}
	
	public abstract boolean comput();
}

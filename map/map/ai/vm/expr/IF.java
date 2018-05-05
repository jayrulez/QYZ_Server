package map.ai.vm.expr;

import map.ai.vm.Expression;
import map.ai.vm.VM;

public final class IF extends Expression {
	private BooleanExpr condition;
	private Expression expr;
	private boolean isTrue;
	
	public IF(BooleanExpr condition, Expression expr) {
		this.condition = condition;
		this.expr = expr;
		this.isTrue = false;
	}
	
	@Override
	public void enter() {
		isTrue = condition.comput();
		expr.entered = false;
	}
	
	@Override
	public boolean run() {
		return isTrue && expr.execute();
	}

	@Override
	protected void onInit(VM vm) {
		expr.init(vm);
	}
	
	@Override
	public void onDestroy() {
		this.expr.onDestroy();
	}


}

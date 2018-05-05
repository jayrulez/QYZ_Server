package map.ai.vm.expr;

import map.ai.vm.Expression;
import map.ai.vm.VM;

public final class Until extends Expression {
	private final BooleanExpr condition;
	private final Expression expr;
	private boolean succ;
	public Until(BooleanExpr condition, Expression expr) {
		this.condition = condition;
		this.expr = expr;
	}
	
	@Override
	protected void onInit(VM vm) {
		this.condition.init(vm);
		this.expr.init(vm);
	}
	
	@Override
	public void onDestroy() {
		this.expr.onDestroy();
	}

	@Override
	public void enter() {
		succ = false;
		expr.entered = false;
	}

	@Override
	public boolean run() {
		if(!succ) {
			if(!condition.comput()) return true;
			succ = true;
		}
		return expr.execute();
	}

}

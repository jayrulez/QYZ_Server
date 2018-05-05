package map.ai.vm.expr;

import map.ai.vm.VM;

public final class Not extends BooleanExpr {
	private BooleanExpr expr;
	
	public Not(BooleanExpr expr) {
		this.expr = expr;
	}
	
	@Override
	public boolean comput() {
		return !expr.comput();
	}

	@Override
	protected void onInit(VM vm) {
		this.expr.init(vm);
	}

}

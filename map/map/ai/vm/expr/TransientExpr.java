package map.ai.vm.expr;

import map.ai.vm.Expression;
import map.ai.vm.VM;

public abstract class TransientExpr extends Expression {

	@Override
	protected void onInit(VM vm) {
		
	}

	@Override
	public final void enter() {
		
	}

	@Override
	public boolean run() {
		process();
		return false;
	}
	
	public abstract void process();

}

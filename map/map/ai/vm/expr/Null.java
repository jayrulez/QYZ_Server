package map.ai.vm.expr;

import map.ai.vm.Expression;
import map.ai.vm.VM;

public final class Null extends Expression {
	public static final Null Ins = new Null();
	@Override
	public void enter() {}
	
	@Override
	public boolean run() {
		return false;
	}

	@Override
	protected void onInit(VM vm) {
		
	}
}

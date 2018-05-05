package map.ai.vm.expr;

import map.ai.vm.VM;

public final class True extends BooleanExpr {
	public final static True Ins = new True();
	@Override
	public boolean comput() {
		return true;
	}
	@Override
	protected void onInit(VM vm) {
		
	}

}

package map.ai.vm.expr;

import map.ai.vm.VM;

public final class False extends BooleanExpr {
	public final static False Ins = new False();
	@Override
	public boolean comput() {
		return false;
	}
	@Override
	protected void onInit(VM vm) {
		
	}

}

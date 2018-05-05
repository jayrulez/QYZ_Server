package map.ai.vm.expr;

import map.ai.vm.Expression;
import map.ai.vm.VM;

public final class IF2 extends Expression {
	private BooleanExpr condition;
	private Expression ifTrue, ifFalse;
	private boolean isTrue;
	
	public IF2(BooleanExpr condition, Expression ifTrue, Expression ifFalse) {
		this.condition = condition;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
		this.isTrue = false;
	}
	
	@Override
	public void enter() {
		isTrue = condition.comput();
		ifTrue.entered = false;
		ifFalse.entered = false;
	}
	
	@Override
	public boolean run() {
		return isTrue ? ifTrue.execute() : ifFalse.execute();
	}

	@Override
	protected void onInit(VM vm) {
		condition.init(vm);
		ifTrue.init(vm);
		ifFalse.init(vm);
	}
	
	@Override
	public void onDestroy() {
		this.ifTrue.onDestroy();
		this.ifFalse.onDestroy();
	}

}

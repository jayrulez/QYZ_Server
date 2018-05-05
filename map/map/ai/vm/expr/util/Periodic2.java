package map.ai.vm.expr.util;

import map.ai.vm.Expression;
import map.ai.vm.VM;

public final class Periodic2 extends Expression {

	private final long interval;
	private final Expression expr;
	private int remainRepeatTime;
	private long nextActiveTime;
	private boolean canRun;
	public Periodic2(Expression expr, long interval, int maxRepeatTime) {
		this.expr = expr;
		this.interval = interval;
		this.remainRepeatTime = maxRepeatTime;
		this.nextActiveTime = 0;
		this.canRun = false;
	}
	
	@Override
	public void enter() {
		expr.entered = false;
		final long now = vm.getTime();
		if(now > nextActiveTime && remainRepeatTime > 0) {
			canRun = true;
			nextActiveTime = now + interval;
			remainRepeatTime--;
		} else {
			canRun = false;
		}
	}

	@Override
	public boolean run() {
		if(!canRun) return false;
		return expr.execute();
	}

	@Override
	protected void onInit(VM vm) {
		this.expr.init(vm);
	}

}

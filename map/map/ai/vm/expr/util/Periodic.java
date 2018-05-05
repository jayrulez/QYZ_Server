package map.ai.vm.expr.util;

import map.ai.vm.Expression;
import map.ai.vm.VM;

public final class Periodic extends Expression {
	private final long interval;
	private final Expression expr;
	private long nextActiveTime;
	private boolean canRun;
	public Periodic(Expression expr, float interval) {
		this.expr = expr;
		this.interval = (long)(interval * 1000);
		this.nextActiveTime = 0;
		this.canRun = false;
	}
	
	@Override
	public void enter() {
		expr.entered = false;
		final long now = vm.getTime();
		if(now > nextActiveTime) {
			canRun = true;
			nextActiveTime = now + interval;
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

package map.ai.vm.expr.action;

import map.ai.vm.Expression;

public final class Delay extends Expression {
	private final int delay;
	private long expireTime;
	public Delay(int delay) {
		this.delay = delay;
	}
	
	@Override
	public void enter() {
		this.expireTime = vm.getTime() + delay;
	}

	@Override
	public boolean run() {

		return this.expireTime > vm.getTime();
	}

}

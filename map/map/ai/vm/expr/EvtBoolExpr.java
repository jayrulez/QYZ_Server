package map.ai.vm.expr;

import map.ai.vm.VM;

public abstract class EvtBoolExpr extends BooleanExpr {
	private final int eventid;
	public EvtBoolExpr(int eventid) {
		this.eventid = eventid;
	}

	@Override
	protected final void onInit(VM vm) {
		vm.register(eventid, this);
	}

}

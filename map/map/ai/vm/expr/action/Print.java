package map.ai.vm.expr.action;

import xdb.Trace;
import map.ai.vm.Expression;
import map.ai.vm.VM;

public final class Print extends Expression {
	private final String text;
	public Print(String text) {
		this.text = text;
	}
	
	@Override
	public void enter() {
		
		
	}

	@Override
	public boolean run() {
		Trace.info(text);
		return false;
	}

	@Override
	protected void onInit(VM vm) {
		
	}

}

package map.map.story.action;

import map.ai.vm.Expression;
import map.ai.vm.VM;
import map.map.story.AbstractStory;

public final class CompleteEctypeCurLayout extends Expression {

	@Override
	protected void onInit(VM vm) {

	}

	@Override
	public void enter() {

	}

	@Override
	public boolean run() {
		final AbstractStory map = (AbstractStory)vm.host;
		final cfg.ectype.Layout layoutCfg = (cfg.ectype.Layout)vm.reg1;
		map.completeCurLayout(layoutCfg.id);
		return false;
	}

}

package map.map.story.action;

import cfg.ectype.Enter;
import map.ai.vm.VM;
import map.map.story.AbstractStory;

public final class ChangeEctypeEntry extends CfgAction<cfg.ectype.Enter> {

	
	public ChangeEctypeEntry(Enter mcfg) {
		super(mcfg);
	}

	@Override
	protected void onInit(VM vm) {


	}

	@Override
	public void enter() {


	}

	@Override
	public boolean run() {
		final AbstractStory map = (AbstractStory)vm.host;
		final int layoutid = ((cfg.ectype.Layout)vm.reg1).id;
		if(mcfg.isopen) {
			map.openEntry(layoutid, mcfg.id);
		} else {
			map.closeEntry(layoutid, mcfg.id);
		}
		return false;
	}

}

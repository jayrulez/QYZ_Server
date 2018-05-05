package map.map.story.action;

import cfg.ectype.Exit;
import map.ai.vm.VM;
import map.map.story.AbstractStory;

public final class ChangeEctypeExit extends CfgAction<cfg.ectype.Exit> {
	
	public ChangeEctypeExit(Exit mcfg) {
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
			map.openExit(layoutid, mcfg.id);
		} else {
			map.closeExit(layoutid, mcfg.id);
		}
		return false;
	}

}

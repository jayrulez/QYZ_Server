package map.map.story.action;

import map.ai.vm.VM;
import cfg.ectype.ControllerOperation;
import map.controller.Controller;
import map.map.GameMap;

public final class ChangeController extends CfgAction<cfg.ectype.ControllerOperation> {

	public ChangeController(ControllerOperation mcfg) {
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
		final GameMap map = (GameMap)vm.host;
		if(mcfg.isopen) {
			map.openController(mcfg.id);
		} else {
			map.closeController(mcfg.id, Controller.CloseType.Leave);
		}
		return false;
	}

}

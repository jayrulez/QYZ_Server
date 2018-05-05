package map.map.story.bool;

import cfg.ectype.Controller;
import map.map.GameMap;

public final class ControllerIsOpen extends CfgBool<cfg.ectype.Controller> {

	public ControllerIsOpen(Controller mcfg) {
		super(mcfg);
	}

	@Override
	public boolean comput() {
		final  GameMap map = (GameMap)vm.host;
		return map.isControllerOpen(mcfg.id);
	}

}

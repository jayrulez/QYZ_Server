package map.map.story.action;

import cfg.ectype.EnviromentOperate;
import map.map.story.AbstractStory;

public final class OperateEnviroment extends CfgAction<cfg.ectype.EnviromentOperate> {

	public OperateEnviroment(EnviromentOperate mcfg) {
		super(mcfg);
	}

	@Override
	public boolean run() {
		final AbstractStory map = (AbstractStory)vm.host;
		map.setEnvVar(mcfg.name, mcfg.op, mcfg.value);
		return false;
	}

}

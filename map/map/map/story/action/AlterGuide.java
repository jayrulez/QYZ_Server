package map.map.story.action;

import map.map.story.AbstractStory;

public final class AlterGuide extends CfgAction<cfg.ectype.AlterGuide> {

	public AlterGuide(cfg.ectype.AlterGuide mcfg) {
		super(mcfg);
	}

	@Override
	public boolean run() {
		final AbstractStory map = (AbstractStory)vm.host;
		map.beginPromptAction(mcfg.actionid);
		return false;
	}

}

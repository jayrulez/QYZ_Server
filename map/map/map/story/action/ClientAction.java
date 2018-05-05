package map.map.story.action;

import map.map.story.AbstractStory;

public final class ClientAction extends CfgAction<cfg.ectype.Action> {
	private AbstractStory map;
	private final boolean suspendMap;
	public ClientAction(cfg.ectype.Action mcfg, boolean suspendMap) {
		super(mcfg);
		this.suspendMap = suspendMap;
	}
	
	@Override
	public void enter() {
		map = (AbstractStory)vm.host;
		map.beginAction(mcfg.actionid, this, true, this.suspendMap);
	}

	@Override
	public boolean run() {
		return !map.isClientActionEnd(mcfg.actionid);
	}

}

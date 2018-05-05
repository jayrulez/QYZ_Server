package map.map.story.action;

import cfg.ectype.MathOperator;
import map.agent.Listener;
import map.agent.Monster;
import map.map.GameMap;
import map.map.story.AbstractStory;

public final class KillMonster extends CfgAction<cfg.ectype.KillMonster> {
	private AbstractStory map;
	public KillMonster(cfg.ectype.KillMonster mcfg) {
		super(mcfg);
	}
	
	private Listener listener;
	private boolean finish;

	private void resetEnvs() {
		for(cfg.ectype.MissionKillMonster mission : mcfg.missions) {
			map.setEnvVar(mission.enviroment, cfg.ectype.MathOperator.SET, 0);
		}
	}
	
	@Override
	public void enter() {
		finish = false;
		map = (AbstractStory)vm.host;
		map.beginAction(mcfg.actionid, this, false, false);
		resetEnvs();
		
		listener = (go, evtid, param) -> {
			if(finish) return;
			if (go.isMonster()) {
				final int monsterid = ((Monster)go).getMonsterId();
				final cfg.ectype.MissionKillMonster mission = mcfg.missions_monsterid.get(monsterid);
				if(mission != null) {
					final int curNum = map.setEnvVar(mission.enviroment, MathOperator.ADD, 1);
					if(curNum >= mission.count) {
						finish = mcfg.missions.stream().allMatch(m -> map.getEnvVar(m.enviroment) >= m.count);
					}
				}
			}
		};
        ((GameMap)vm.host).addListener(Listener.DEATH,  listener);
	}

	@Override
	public boolean run() {
		return !finish;
	}

	@Override
	public void exit() {
		map.endAction(mcfg.actionid);
		resetEnvs();
        ((GameMap)vm.host).removeListener(Listener.DEATH, listener);
		listener = null;
	}
	
	@Override
	public void onDestroy() {
		if(entered) {
			exit();
		}
	}

}

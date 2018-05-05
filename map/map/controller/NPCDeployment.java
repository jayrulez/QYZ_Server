package map.controller;

import cfg.map.Reason;
import map.MapUtils;
import map.agent.ANPC;
import map.agent.Agent;

public final class NPCDeployment extends CfgDeployment<cfg.map.NPCDeployment> {

	public NPCDeployment(Controller controller, cfg.map.NPCDeployment ctrlCfg) {
		super(controller, ctrlCfg);
	}
	
	private ANPC npc;

	@Override
	public void open() {
		npc = gamemap.createNPC(deploymentCfg.npcid, MapUtils.c2p(deploymentCfg.position), MapUtils.c2p(deploymentCfg.orientation));
	}
	
	@Override
	public void onDead(Agent npc) {
		
	}

	@Override
	public void onLeave(Agent agent) {
		npc = null;
	}
	
	@Override
	public void close(Controller.CloseType closeType) {
		if(npc != null) {
			gamemap.leave(npc, Reason.CLOSE_CONTROLLER);
			npc = null;
		}
	}
}

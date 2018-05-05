package map.controller;

import map.map.GameMap;

import java.util.ArrayList;
import java.util.List;

public final class Controller {
	public enum CloseType {
		Leave,
		Kill,
	}


	private final GameMap map;
	private final cfg.map.Controller ccfg;
	
	private final List<Deployment> deployments = new ArrayList<>();
	
	public Controller(GameMap map, cfg.map.Controller ccfg) {
		this.map = map;
		this.ccfg = ccfg;
	}
	
	public final int getId() {
		return ccfg.id;
	}
	
	public final GameMap getMap() {
		return map;
	}
	
	public void open() {
		for(cfg.map.Deployment dcfg : ccfg.deployments) {
			final Deployment deployment = createDeployment(this, dcfg);
			deployment.open();
			deployments.add(deployment);
		}
	}
	
	public void close(CloseType closeType) {
		deployments.forEach(d -> d.close(closeType));
		deployments.clear();
	}

	public void update(long now) {
		
	}
	
	private Deployment createDeployment(Controller controller, cfg.map.Deployment dcfg) {
		switch(dcfg.getTypeId()) {
		case cfg.map.MonsterDeployment.TYPEID: {
			return new MonsterDeployment(controller, (cfg.map.MonsterDeployment)dcfg);
		}
		case cfg.map.NPCDeployment.TYPEID: {
			return  new NPCDeployment(controller, (cfg.map.NPCDeployment)dcfg);
		}
		case cfg.map.MineralDeployment.TYPEID: {
			return new MineDeployment(controller, (cfg.map.MineralDeployment)dcfg);
		}
		case cfg.map.SFXDeployment.TYPEID: {
			return new SFXDeployment(controller, (cfg.map.SFXDeployment)dcfg);
		}
		case cfg.map.SceneObjDeployment.TYPEID: {
			return new SceneObjDeployment(controller, (cfg.map.SceneObjDeployment)dcfg);
		}
		default: {
			throw new RuntimeException("unknown controller type:" + dcfg);
		}
		}
	}
}

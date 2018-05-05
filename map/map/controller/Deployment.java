package map.controller;

import map.map.GameMap;
import map.agent.Agent;

public abstract class Deployment {
	protected final Controller controller;
	protected final GameMap gamemap;
	public Deployment(Controller controller) {
		this.controller = controller;
		this.gamemap = controller.getMap();
	}
	
	public final Controller getController() {
		return controller;
	}

	public abstract void open();
	
	public abstract void close(Controller.CloseType closeType);
	
	public abstract void onDead(Agent agent);
	public abstract void onLeave(Agent agent);

	public void update(long now) {

	}

}

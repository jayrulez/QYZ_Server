package map.map.story;

import map.map.GameMap;

public final class Script {
	private final map.ai.vm.VM vm;
	public Script(GameMap map, cfg.ectype.Layout layoutCfg) {
		this.vm = VMFactory.createVM(layoutCfg.scripts);
		this.vm.host = map;
		this.vm.reg1 = layoutCfg;
	}
	
	public void start() {
		vm.start();
	}
	
	public void update(long now) {
		this.vm.run(now);
	}
	
	public void end() {
		this.vm.destroy();
	}
}

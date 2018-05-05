package map.controller;

import cfg.Const;
import cfg.map.Reason;
import cfg.map.WeightedPolygonRegion;
import common.Utils;
import map.MapUtils;
import map.agent.AMine;
import map.agent.Agent;
import map.agent.Listener;
import pathfinding.Vector3;

import java.util.*;

public final class MineDeployment extends CfgDeployment<cfg.map.MineralDeployment> {

	public MineDeployment(Controller controller, cfg.map.MineralDeployment ctrlCfg) {
		super(controller, ctrlCfg);
	}

	public final static class MineParam {
		public int mineid;
		public Vector3 position;
		public Vector3 orient;
		public cfg.map.MineralSpawn msCfg;
		public cfg.CfgObject region;
	}
	private final Map<AMine, MineParam> mines = new HashMap<>();
	private final HashMap<cfg.map.MineralSpawn, Integer> mineNum = new HashMap<>();
	
	@Override
	public void open() {
        switch (deploymentCfg.location.getTypeId()) {
            case cfg.map.MultiPoints.TYPEID : {
                final int totalMineNum = deploymentCfg.minerals.stream().mapToInt(m -> m.count).sum();
                final cfg.map.MultiPoints regions = (cfg.map.MultiPoints)deploymentCfg.location;

                final List<Integer> positions = new ArrayList<>(common.Utils.getMutiRandom(0, regions.positions.size(), totalMineNum));

                int index = 0;
                for (cfg.map.MineralSpawn ms : deploymentCfg.minerals) {
                    for(int i = 0 ; i < ms.count ; i++) {
                        final MineParam param = new MineParam();
                        param.mineid = ms.mineralid;
                        param.msCfg = ms;
                        param.region = regions.positions.get(positions.get(index++ % positions.size()));
                        mines.put(createMine(param), param);
                    }
                    Utils.addValue(mineNum, ms, ms.count);
                }
                break;
            }
            case cfg.map.MultiPolygon.TYPEID: {
                final cfg.map.MultiPolygon regions = (cfg.map.MultiPolygon)deploymentCfg.location;
                for (cfg.map.MineralSpawn ms : deploymentCfg.minerals) {
                    for(cfg.map.WeightedPolygonRegion region : regions.polygons) {
                        for (int i = 0; i < region.weight; i++) {
                            final MineParam param = new MineParam();
                            param.mineid = ms.mineralid;
                            param.msCfg = ms;
                            param.region = region;
                            mines.put(createMine(param), param);
                        }
                        Utils.addValue(mineNum, ms, region.weight);
                    }
                }
                break;
            }
        }
	}
	
	public AMine createMine(MineParam param) {
        final MapUtils.Location location;
        if(param.region instanceof cfg.map.FixedPoint) {
            final cfg.map.FixedPoint region = (cfg.map.FixedPoint)param.region;
            location = new MapUtils.Location();
            location.position = MapUtils.c2p(region.position);
            location.orient = MapUtils.c2p(region.orientation);
        } else {
            location = MapUtils.randomPolygonsPositionAndOrient(((WeightedPolygonRegion)param.region).vertices);
        }
		param.position = location.position;
		param.orient = location.orient;

		final AMine mine = gamemap.createMine(param.mineid, param.position);
		mines.put(mine, param);
		mine.addListener(Listener.LEAVE, (go, evtid, p) -> {
			onLeave(go);
		});
		return mine;
	}

	@Override
	public void close(Controller.CloseType closeType) {
        if(this.closed) return;
        this.closed = true;
		for(AMine mine : mines.keySet()) {
			gamemap.leave(mine, Reason.CLOSE_CONTROLLER);
		}
	}

	@Override
	public void onDead(Agent agent) {

	}

	@Override
	public void onLeave(Agent agent) {
        if(this.closed) return;
		final MineParam param = mines.remove(agent);

		final int count = param.msCfg.regeneratecount;
		if(count == Const.NULL || mineNum.get(param.msCfg) < count) {
			Utils.addValue(mineNum, param.msCfg, 1);
            gamemap.schedule(() -> {
                mines.put(createMine(param), param);
            }, (long)(param.msCfg.regeneratecd * 1000));

		}
	}

}

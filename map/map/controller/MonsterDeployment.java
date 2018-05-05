package map.controller;

import cfg.Const;
import cfg.map.Reason;
import cfg.map.MultiPolygon;
import cfg.map.PatrolType;
import cfg.map.WeightedPolygonRegion;
import common.Utils;
import map.MapUtils;
import map.agent.Agent;
import map.agent.Listener;
import map.agent.Monster;
import pathfinding.Vector3;

import java.util.*;
import java.util.stream.Collectors;

public final class MonsterDeployment extends CfgDeployment<cfg.map.MonsterDeployment> {

	public final static class MonsterParam {
		public int monsterid;
		public Vector3 orient;
		public Vector3 position;
		public cfg.map.MonsterSpawn mbCfg;
		public cfg.CfgObject region; // cfg.map.FixedPoint or WeightedPolygonRegion
	}
	
	public MonsterDeployment(Controller controller, cfg.map.MonsterDeployment ctrlCfg) {
		super(controller, ctrlCfg);
	}
	
	private final HashMap<cfg.map.MonsterSpawn, Integer> monsterNum = new HashMap<>();

	@Override
	public void open() {
        switch (deploymentCfg.location.getTypeId()) {
            case cfg.map.MultiPoints.TYPEID : {
                final int totalMonsterNum = deploymentCfg.monsters.stream().mapToInt(ms -> ms.count).sum();
                final cfg.map.MultiPoints regions = (cfg.map.MultiPoints)deploymentCfg.location;

                final List<Integer> positions = new ArrayList<>(common.Utils.getMutiRandom(0, regions.positions.size(), totalMonsterNum));

                int index = 0;
                for (cfg.map.MonsterSpawn mb : deploymentCfg.monsters) {
                    for (int i = 0; i < mb.count; i++) {
                        final MonsterParam mp = new MonsterParam();
                        mp.monsterid = mb.monsterid;
                        mp.mbCfg = mb;
                        mp.region = regions.positions.get(positions.get(index++ % positions.size()));
                        monsters.put(createMonster(mp), mp);
                    }
                    Utils.addValue(monsterNum, mb, mb.count);
                }
                break;
            }
            case cfg.map.MultiPolygon.TYPEID: {
                final cfg.map.MultiPolygon regions = (cfg.map.MultiPolygon)deploymentCfg.location;
                for (cfg.map.MonsterSpawn mb : deploymentCfg.monsters) {
                    for(cfg.map.WeightedPolygonRegion region : regions.polygons) {
                        for (int i = 0; i < region.weight; i++) {
                            final MonsterParam mp = new MonsterParam();
                            mp.monsterid = mb.monsterid;
                            mp.mbCfg = mb;
                            mp.region = region;
                            monsters.put(createMonster(mp), mp);
                        }
                        Utils.addValue(monsterNum, mb, region.weight);
                    }
                }
                break;
            }
        }
	}

	@Override
	public void close(Controller.CloseType closeType) {
        if(this.closed) return;
        this.closed = true;
		if(closeType == Controller.CloseType.Leave) {
			for (Monster monster : monsters.keySet()) {
				gamemap.leave(monster, Reason.CLOSE_CONTROLLER);
			}
		} else {
			for (Monster monster : monsters.keySet()) {
				monster.forceKill();
			}
		}
	}
	
	Monster createMonster(MonsterParam mp) {
		final MapUtils.Location location;
        if(mp.region instanceof cfg.map.FixedPoint) {
            final cfg.map.FixedPoint region = (cfg.map.FixedPoint)mp.region;
            location = new MapUtils.Location();
            location.position = MapUtils.c2p(region.position);
            location.orient = MapUtils.c2p(region.orientation);
        } else {
            location = MapUtils.randomPolygonsPositionAndOrient(((WeightedPolygonRegion)mp.region).vertices);
        }
		mp.position = location.position;
		mp.orient = location.orient;

		final int patrolType = deploymentCfg.patroltype;
		final List<Vector3> pathVertexs;
		switch (patrolType) {
			case PatrolType.NULL: {
				pathVertexs = null;
				break;
			}
			case PatrolType.ONCE:
			case PatrolType.CYCLE:
			case PatrolType.REPEAT: {
				pathVertexs = deploymentCfg.path.vertices.stream().map(v -> MapUtils.c2p(v.position)).collect(Collectors.toList());
				break;
			}
			case PatrolType.RANDOM: {
				pathVertexs = Utils.randomChoose(((MultiPolygon)deploymentCfg.location).polygons).vertices.stream()
						.map(v -> MapUtils.c2p(v)).collect(Collectors.toList());
				break;
			}
			default : {
				throw new RuntimeException("unknown patroltype:" + patrolType);
			}
		}
		final Monster monster = gamemap.createMonster(mp.monsterid, mp.position, mp.orient, patrolType, pathVertexs);
		monster.addListener(Listener.DEATH, (go ,evtid, p) -> {
			onDead(go);
		});
		return monster;
	}
	
	private final HashMap<Monster, MonsterParam> monsters = new HashMap<>();
	
	public void onDead(Agent agent) {
        if(this.closed) return;
		final MonsterParam param = monsters.remove(agent);
		final int count = param.mbCfg.regeneratecount;
		if(count == Const.NULL || monsterNum.get(param.mbCfg) < count) {
			Utils.addValue(monsterNum, param.mbCfg, 1);
			gamemap.schedule(() -> {
				monsters.put(createMonster(param), param);
			}, (long)(param.mbCfg.regeneratecd * 1000));
		}
	}

	@Override
	public void onLeave(Agent agent) {

	}
	
}

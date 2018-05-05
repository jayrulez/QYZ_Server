
package map.map;

import cfg.CfgMgr;
import cfg.fight.AgentType;
import cfg.fight.PKState;
import cfg.fight.Relation;
import cfg.map.Reason;
import cfg.map.ReviveType;
import cfg.map.WorldMap;
import common.Utils;
import map.MapModule;
import map.MapUtils;
import map.agent.*;
import map.msg.*;
import map.skill.DeathParam;
import pathfinding.Vector3;

import java.util.*;

public class World extends GameMap {

	public static World create(int worldid, int lineid, int serverid) {
		final World.Builder b = new World.Builder();
		b.mapid = Utils.makeId(serverid, MapMgr.Ins.getLocalServerid(), worldid, lineid);
		b.subid = worldid;
		b.serverid = serverid;
		final cfg.map.WorldMap worldCfg = CfgMgr.worldmap.get(worldid);
        b.scene = worldCfg.scenename;
		b.landscapeid = worldCfg.landscapeid;
		b.regionsetid = worldCfg.regionsetid;

		b.xcellNum = 0; // 默认为地图size / 10
		b.zcellNum = 0;

		b.baseUpdateInterval = 100;
		b.nearbyUpdateInterval = 1000;
		b.normalUpdateInterval = 100;
		b.controllerUpdateInterval = 1000;

        b.maxDefencerBodyHeight = 10;
        b.maxDefencerBodyRadius = 4;

        b.innerSightRadius = CfgMgr.roleconfig.multiplayermapinnersightradius;
        b.outerSightRadius = CfgMgr.roleconfig.multiplayermapoutersightradius;

		b.initOrient = MapUtils.createOrient(0);
		b.initPosition = new Vector3(worldCfg.WorldFlyInX, 0, worldCfg.WorldFlyInY);

        b.useBroadcastPolicy = true;

		return new World(b);
	}
	
	private final int worldid;
	private final int lineid;
	private final cfg.map.WorldMap worldmapCfg;
    private boolean isExpMonsterOpen = false;
    private long idleTime;

	public World(Builder b) {
		super(b);

		this.worldid = Utils.getWorldidById(b.mapid);
		this.lineid = Utils.getLineididById(b.mapid);
		this.worldmapCfg = CfgMgr.worldmap.get(this.worldid);
        this.idleTime = 0;
	}

	@Override
	public String toString() {
		return String.format("%s{mapid:%s type:%s worldid:%s lineid:%s players:%s agents:%s}",
				this.getClass().getSimpleName(), getMapid(), getType(), worldid, lineid, players.size(), getAgents().length);
	}

	public int getWorldid() {
		return worldid;
	}

	public int getLineid() {
		return lineid;
	}

	public final cfg.map.WorldMap getWorldmapCfg() {
		return worldmapCfg;
	}

	public final boolean isDuegon() {
		return worldmapCfg.isdungeon;
	}

    public boolean canRide() { return worldmapCfg.allowride; }

	public void init() {
		super.init();

        addListener(Listener.DEATH, (go, evtid, param) -> {
            final DeathParam dp = (DeathParam)param;
            if(!dp.defencer.isPlayer())
                return;
            if(!dp.attacker.isPlayerOrFakePlayerOrPet())
                return;
            sendXdbServer(new MWorldPKKill(dp.attacker.getOwner().getAid(), dp.defencer.getAid()));
        });

		openAllControllers();
	}

	@Override
	public boolean admit(long roleid) {
        return !isClosed() && players.size() < CfgMgr.worldmap.get(worldid).maxlineplayernum + 5;
	}

	@Override
	public void sendPlayerEnter(Player player) {
		player.sendNotRoleMsg(new SEnterWorld(getMapid(), worldid, lineid));
	}

    @Override
    public void onPlayerEnter(Player player) {
        if(!worldmapCfg.allowpk || !PKState.enums.contains(player.getPkstate()))
            player.setPkstate(PKState.PEACE);
        this.idleTime = 0;
    }

    @Override
    public void onPlayerLeave(Player player) {
        // onPlayerLeave在 players.remove之前调用
        if(lineid > worldmapCfg.initlinenum && players.size() <= 1) {
            final long newIdleTime = getNow() + WorldMap.LINE_IDLE_TIME * 1000L;
            this.idleTime = newIdleTime;
            schedule(() -> {
                if(this.idleTime == newIdleTime && !isClosed()) {
                    stop();
                    sendXdbServer(new MCloseWorldMap(worldid, lineid));
                }
            }, WorldMap.LINE_IDLE_TIME * 1000L);
        }
    }

    @Override
    public void onAgentEnter(Agent agent) {}

    @Override
    public void onAgentLeave(Agent agent) {}

	@Override
	public void revivePlayer(map.agent.Player player, int revivetype) {
        if(!player.isActive() || !player.isDead())
            return;
		switch(revivetype) {
			case ReviveType.CUR_POSITION: {
                player.sendXdb(new MCheckWorldRevive(revivetype));
				//player.reviveAtCurPosition();
				break;
			}
			case ReviveType.REVIVE_POSITION: {
				player.revive(getInitPosition(), getInitOrient());
				break;
			}
			default: {
				xdb.Trace.debug("unknown revivetype:" + revivetype);
			}
		}
	}

    public void process(XCreateWorldBoss msg) {
        final Monster boss = createMonster(msg.monsterid, MapUtils.m2p(msg.position), MapUtils.m2p(msg.orient));
        boss.addListener(Listener.DEATH, (go ,evtid, param) -> {
            final Player player = ((DeathParam)param).attacker.getOwner().asPlayerOrFakePlayer();
            sendXdbServer(new MKillWorldBoss(getLineid(), player.getRoleid(), msg.bossid));
        });
    }

    @Override
    public void onPlayerReady(Player player) {

    }

    public void process(XEndWorldBoss msg) {
		final cfg.ectype.WorldBoss wcfg = CfgMgr.worldboss.get(msg.bossid);
		for (Agent agent : getAgents()) {
			if(agent.isMonster()) {
				final Monster monster = (Monster)agent;
				if(monster.getMonsterId() == wcfg.monsterid) {
					leave(agent, Reason.ALIVE_EXPIRE);
				}
			}
		}
    }

    public void process(XCreatExpMonster msg){
        isExpMonsterOpen = true;
        cfg.ectype.ExpMonsterMsg monsterDetail = CfgMgr.expmonster.monstermsg.get(msg.lvlindex);
        cfg.ectype.ExpMonsterRef positions = monsterDetail.monsterref.get(msg.positionindex);
        if(positions.mapid != this.worldid){
            return;
        }
        for(cfg.ectype.PointInfo position : positions.refreshopint){
            creatExpMonster(monsterDetail.monsterid, position.position, position.orient, CfgMgr.expmonster.refreshtime, monsterDetail.maxlevel);
//            System.out.println(worldid + " " + lineid +"线经验怪刷新了" + monsterDetail.monsterid + " " + position.position.x + " " + position.position.y) ;
        }
    }

    public void creatExpMonster(int expmonsterid, cfg.map.Vector2 postion, float orient, int refreshTime, int maxlevel) {
        if (!isExpMonsterOpen) {
            return;
        }
        final Monster expMonster = createMonster(expmonsterid, MapUtils.c2p(postion), MapUtils.createOrient(orient));
        expMonster.addListener(Listener.DEATH, (host, evtid, p) -> {
            final DeathParam param = (DeathParam) p;
            final Fighter defencer = param.defencer;
            Player caster = defencer.getHostilityMgr().getMaxDamageTeamPlayer();
            if (caster != null) {
                final long casterid = caster.getRoleid();
                final map.msg.Team team = caster.getTeam();
                final Set<Long> killMonsterRoles = new HashSet<>();
                if(caster.getLevel() <= maxlevel) {
                    killMonsterRoles.add(casterid);
                }
                if (team.members.size() > 1) {
                    team.members.forEach(rid -> {
                        if (rid != casterid) {
                            final Player member = (Player) getAgent(rid);
                            if (member != null && member.isActive() && member.getLevel() <= maxlevel) {
                                killMonsterRoles.add(rid);
                            }
                        }
                    });
                }
//                System.out.println(casterid + "击杀了经验怪" + expmonsterid + " 计算击杀数的玩家 " + killMonsterRoles);
                if(!killMonsterRoles.isEmpty()) {
                    sendXdbServer(new MKillExpMonster(new ArrayList<>(killMonsterRoles)));
                }
            }
            schedule(() -> creatExpMonster(expmonsterid, postion, orient, refreshTime, maxlevel), refreshTime * 1000L);
        });
    }

    public void process(XCloseExpMonster msg) {
        isExpMonsterOpen = false;
//        System.out.println(worldid + " " + lineid + "线经验怪活动结束!");
        for (Agent agent : getAgents()) {
            if(agent.isMonster()) {
                final Monster monster = (Monster)agent;
                if(monster.getMonsterId() == msg.monsterid) {
                    leave(agent, Reason.ALIVE_EXPIRE);
                }
            }
        }
    }

}

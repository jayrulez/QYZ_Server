
package map.map;

import cfg.CfgMgr;
import cfg.Const;
import cfg.bonus.Drop;
import cfg.currency.CurrencyType;
import cfg.ectype.*;
import cfg.fight.AgentType;
import cfg.fight.PKState;
import cfg.fight.Relation;
import cfg.map.*;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import common.RefObject;
import common.TaskQueue;
import common.Utils;
import map.MapModule;
import map.MapUtils;
import map.agent.*;
import map.ai.AIFactory;
import map.buff.Buff;
import map.controller.Controller;
import map.msg.*;
import map.skill.DeathParam;
import map.skill.Skill;
import pathfinding.NavGraph;
import pathfinding.Vector3;
import xdb.Trace;
import xio.Protocol;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static common.Utils.getTypeById;

import pathfinding.Vector3;
import map.controller.Controller;

public abstract class GameMap {
	private final long mapid;
	private final int mapType;
	private final int serverid; // 创造者的serverid
	private final AgentMap agentMap;
	
	private final TaskQueue taskQueue = new TaskQueue();

	private final cfg.map.LandScape landscapeCfg;
	private final cfg.map.EctypeRegionSet regionSetCfg;

    private final int maxDefencerBodyRadius;
    private final int maxDefencerBodyHeight;
    private final int innerSightRadius;
    private final int outerSightRadius;

    private final boolean useBroadcastPolicy;

    private final NavGraph navGraph;


	private final Vector3 initPosition;
	private final Vector3 initOrient;

	private final long nearbyUpdateInterval;
	private long nextUpdateNearbyTime;
    private long nextUpdateAttackTime;

	private final TreeMap<Integer, Set<Listener>> listeners = new TreeMap<>();
	private final Map<Integer, Controller> controllers = new HashMap<>();
	
	private ArrayList<Runnable> deferTasks = new ArrayList<>();

	protected final Map<Long, Player> players = new HashMap<>();
	private final Map<Long, Agent> agents = new HashMap<>();
    private final Map<Long, SpawnObject<?>> objects = new HashMap<>();

    protected final Scheduler scheduler;

	private boolean isClosed;
	private boolean issuspend;
	private long deltaTime;
	private long now;

    public static class Builder {
		public long mapid;
		public int serverid;
		public int subid;
		public int landscapeid;
		public int regionsetid;
        public String scene;
		public int xcellNum;
		public int zcellNum;
		public int baseUpdateInterval;
		public int nearbyUpdateInterval;
		public int normalUpdateInterval;
		public int controllerUpdateInterval;

        public int maxDefencerBodyRadius;
        public int maxDefencerBodyHeight;
        public int innerSightRadius;
        public int outerSightRadius;
		
		public Vector3 initPosition;
		public Vector3 initOrient;

        public boolean canPetRevive;

        public boolean useBroadcastPolicy;
	}

	public GameMap(Builder b) {
		mapid = b.mapid;
		mapType = getTypeById(b.mapid);
		serverid = b.serverid;

		landscapeCfg = CfgMgr.landscape.get(b.landscapeid);
		regionSetCfg = CfgMgr.ectyperegionset.get(b.regionsetid);
        final Scene sceneCfg = CfgMgr.scene.get(b.scene);
        this.useBroadcastPolicy = b.useBroadcastPolicy;

        agentMap = new AgentMap(new pathfinding.Rect(0, 0, sceneCfg.scenesize, sceneCfg.scenesize),
                b.xcellNum != 0 ? b.xcellNum : Math.max((int)(sceneCfg.scenesize / 20), 1),
                b.zcellNum != 0 ? b.zcellNum : Math.max((int)(sceneCfg.scenesize / 20), 1));


        this.navGraph = MapModule.navGraphs.get(sceneCfg.navmeshname);
        //if(this.navGraph == null)
         //   Trace.error("GameMap mapid:{} mapType:{} scene:{} can't find navmesh:{}", b.mapid, mapType, b.scene, sceneCfg.navmeshname);
		


		nearbyUpdateInterval = b.nearbyUpdateInterval;

        this.maxDefencerBodyHeight = b.maxDefencerBodyHeight;
        this.maxDefencerBodyRadius = b.maxDefencerBodyRadius;
        this.innerSightRadius = b.innerSightRadius;
        this.outerSightRadius = b.outerSightRadius;

		this.nextUpdateNearbyTime = now;
        this.nextUpdateAttackTime = now;

        this.scheduler = new Scheduler(this);

		initPosition = b.initPosition;
		initOrient = b.initOrient;

		isClosed = false;
		issuspend = false;
		now = System.currentTimeMillis();
		deltaTime = 0;
	}

	public boolean admit(long roleid) {
		return false;
	}

    public int getMaxDefencerBodyRadius() {
        return maxDefencerBodyRadius;
    }

    public int getMaxDefencerBodyHeight() {
        return maxDefencerBodyHeight;
    }

    public int getInnerSightRadius() {
        return innerSightRadius;
    }

    public int getOuterSightRadius() {
        return outerSightRadius;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public boolean isUseBroadcastPolicy() {
        return useBroadcastPolicy;
    }

    public void init() {
		addListener(Listener.DEATH, (host, evtid, p) -> {
			final DeathParam param = (DeathParam) p;
			final Fighter defencer = param.defencer;
			xdb.Trace.debug("GameMap. Listnener.DEAD  attacker:{} defencer:{}", param.attacker.getOwner(), defencer);
			if (host.isMonster()) {
				final int monsterid = ((Monster) host).getMonsterId();
				cfg.monster.Monster mcfg = CfgMgr.monster.get(monsterid);
				Player caster = defencer.getHostilityMgr().getMaxDamageTeamPlayer();
				if (caster != null) {
					final long casterid = caster.getRoleid();
                    final map.msg.Team team = caster.getTeam();
					// 只有杀死怪的人才有掉落
					final List<map.msg.Bonus> bonuss = new ArrayList<>();
					common.Bonus.genDropItemsByProfession(caster.getProfession(), mcfg.drops, common.Bonus.calcAttenuationDropProbality(caster.getLevel() - mcfg.level), bonuss);
					onKillMonster(caster, monsterid);

                    if(!bonuss.isEmpty() || mcfg.dropexp > 0 || mcfg.dropcurrencynum > 0) {
                        final List<Player> nearbyTeamMembers = new ArrayList<>();
                        nearbyTeamMembers.add(caster);
                        onAsistKillMonster(caster, common.Bonus.calcAttenuationExp(mcfg.dropexp, caster.getLevel() - mcfg.level), mcfg.dropcurrencynum);
                        if (team.members.size() > 1) {
                            // 本地图里附近的队友都能分到经验
                            for(long rid : team.members) {
                                if(rid != casterid) {
                                    final Player member = (Player) agents.get(rid);
                                    if (member != null && member.isActive() && !member.isDead()
                                            && member.getPosition().getSubXZSquare(caster.getPosition()) < cfg.monster.Monster.TEAM_SHARE_EXP_RADIUS * cfg.monster.Monster.TEAM_SHARE_EXP_RADIUS) {
                                        nearbyTeamMembers.add(member);
                                        onAsistKillMonster(member, common.Bonus.calcAttenuationExp(mcfg.dropexp, member.getLevel() - mcfg.level), mcfg.dropcurrencynum);
                                    }
                                }
                            }
                        }
                        createDropItems(bonuss, defencer.getPosition(), nearbyTeamMembers);
                    }
				}
			}
		});
	}

	public float getExpBonusRate() {
		final cfg.role.RoleConfig rcfg = CfgMgr.roleconfig;
		if(isWorldMap()) {
			return ((World)this).isDuegon() ? rcfg.duegonbonus : rcfg.worldbonus;
		} else {
            return 1;
        }
	}

	protected void onKillMonster(Player player, int monsterid) {
		player.onKillMonster(monsterid);
	}

	protected void onAsistKillMonster(Player member, int baseExp, int currency) {
        if(baseExp <= 0 && currency <= 0) return;
        member.onAsistKillMonster(baseExp, currency);
	}

	public long getMapid() {
		return mapid;
	}

	public final int getType() {
		return mapType;
	}

	public final int getServerid() {
		return serverid;
	}

	public Vector3 getInitPosition() {
		return initPosition;
	}

	public Vector3 getInitOrient() {
		return initOrient;
	}

	public AgentMap getAgentMap() {
		return agentMap;
	}

	public cfg.map.LandScape getLandscapeCfg() {
		return landscapeCfg;
	}

	public cfg.map.EctypeRegionSet getRegionSetCfg() {
		return regionSetCfg;
	}

    public boolean canPetRevive() {
        return true;
    }

    public boolean canRide() { return false; }

	public void start() {
        init();
        onStart();
		MapMgr.Ins.addMap(this);
	}

	public void onStart() {

	}

	public void stop() {
        isClosed = true;
		MapMgr.Ins.removeMap(this);
	}

    public void externUpdate(long now) {
        taskQueue.add(() -> this.update(now));
    }

    public void schedule(Agent agent, Runnable task, long delayMilliseconds) {
        scheduler.schedule(agent, task, delayMilliseconds);
    }

	public void schedule(Runnable task, long delayMilliseconds) {
        scheduler.schedule(task, delayMilliseconds);
	}

    public void scheduleAlwaysExecute(Runnable task, long delay) {
        taskQueue.schedule(task, delay);
    }

	public void addDeferTask(Runnable task) {
		if(isClosed) return;
		if(deferTasks.isEmpty()) {
			taskQueue.add(this::runDeferTasks);
		}
		deferTasks.add(task);
	}

	private final void runDeferTasks() {
		if(!deferTasks.isEmpty()) {
			final ArrayList<Runnable> tasks = deferTasks;
			deferTasks = new ArrayList<>();
			for(Runnable t : tasks) {
                try {
                    t.run();
                } catch (Exception e) {
                    Trace.error("GameMap.runDeferTask. " + this, e);
                }
            }
		}
	}

	public void addExternTask(Runnable task) {
		this.taskQueue.add(task);
	}

	public boolean isSuspend() {
		return issuspend;
	}
	protected void setSuspend() {
		issuspend = true;
	}
	protected void clearSuspend() {
		issuspend = false;
	}


	public long getDeltaTime() {
		return deltaTime;
	}
	public long getNow() {
		return now;
	}

    public final static int ATTACK_EXPIRE_TIME = 10000;

    public final static Object lock = new Object();

	protected void update(long now) {
        scheduler.update(now);

		this.deltaTime = now - this.now;
		this.now = now;
		if(!isSuspend()) {
			if(nextUpdateNearbyTime <= now) {
				updateNearbyPlayer(now);
				nextUpdateNearbyTime = now + nearbyUpdateInterval;
                if(nextUpdateAttackTime <= now) {
                    nextUpdateAttackTime = now + ATTACK_EXPIRE_TIME;
                    updatePlayerAttackRecord(now);
                }
			}

			updateNormal(now);
			updateControllers(now);
		}
	}

    private void updatePlayerAttackRecord(long now) {
        for(Player player : players.values()) {
            if(player.isActive())
                player.updateAttackRecords(now);
        }
    }

//    private void updatePolicy(long now) {
//        players.values().stream().filter(p -> p.isActive()).forEach(player -> player.updatePolicy(now));
//    }

    private boolean dirty = false;
	private void updateNormal(long now) {
	    // 这么做的原因
        // 1. 避免ConcurrentModifyException
        // 2. list 迭代更快.
		for(Agent agent : getAgents()) {
			try {
				if(agent.isActive())
					agent.update(now);
			} catch (Exception e) {
				e.printStackTrace();
				xdb.Trace.error("GameMap.updateNormal agent:" + agent, e);
				leave(agent, Reason.INTERN_EXCEPTION);
			}
		}
        for (Iterator<SpawnObject<?>> it = objects.values().iterator(); it.hasNext() ; ) {
            final SpawnObject<?> obj = it.next();
            try {
                if (!obj.update(now))
                    it.remove();
            } catch (Exception e) {
                e.printStackTrace();
                xdb.Trace.error("GameMap.updateNormal spawnobject:" + obj, e);
                it.remove();
            }
        }
	}

	public void addListener(int evtid, Listener listener) {
		Set<Listener> ls = listeners.get(evtid);
		if(ls == null) {
			ls = new HashSet<>();
			listeners.put(evtid, ls);
		}
		ls.add(listener);
	}

	public void removeListener(int evtid, Listener listener) {
		final Set<Listener> ls = listeners.get(evtid);
		if (ls != null) {
			ls.remove(listener);
		}
	}

	public void trigger(Agent agent, int evtid, Object param) {
	    // gamemap的listener 应该不至于在trigger时发生改变.
        // 不作ConcurrenetModifyException的处理.
		final Set<Listener> ls = listeners.get(evtid);
		if(ls != null && !ls.isEmpty()) {
			for(Listener listener : ls) {
				listener.onTrigger(agent, evtid, param);
			}
		}
	}

	private final static Agent[] EMPTY_AGENTS = new Agent[0];
	private Agent[] agentTempArray = EMPTY_AGENTS;
	public final Agent[] getAgents() {
	    if(dirty) {
	        dirty = false;
            agentTempArray = agents.values().toArray(EMPTY_AGENTS);
        }
		return agentTempArray;
	}

	public int getPlayerNum() {
		return players.size();
	}

	public Agent getAgent(long aid) {
		return agents.get(aid);
	}

	public Fighter getFighter(long fid) {
		return (Fighter)agents.get(fid);
	}

	public Player getPlayer(long roleid) {
		return (Player)agents.get(roleid);
	}

	public final boolean isWorldMap() {
		return getType() == EctypeType.WORLD;
	}

	public final boolean isEctype() {
	    return this instanceof Ectype;
    }

	public Agent findAgentBySubType(Player finder, int subType) {
        double minDistance = 0;
        Agent result = null;
		for(Agent a : getAgents()) {
			if(a != finder && a.isActive() && a.getSubType() == subType) {
                Agent target = null;
				switch (a.getType()) {
					case AgentType.MONSTER:
					case AgentType.FAKE_PLAYER:
					case AgentType.PLAYER:
					case AgentType.PET: {
                        Fighter fighter = (Fighter)a;
						if(!fighter.isDead() && isEnemy(finder, fighter)) {
                            target = fighter;
                        }
						break;
					}
					case AgentType.NPC:
					case AgentType.MINE: {
                        target = a;
                        break;
					}
				}
                if(target != null) {
                    double dis = target.getPosition().getSubXZSquare(a.getPosition());
                    if (result == null || dis < minDistance) {
                        result = a;
                        minDistance = dis;
                    }
                }
			}
		}
		return result;
	}

	public Agent findAgentByType(Player finder, int agentType) {
        double minDistance = 0;
        Agent result = null;
		for(Agent a : getAgents()) {
            Agent target = null;
			if(a.getType() == agentType && a.isActive() && a != finder) {
				if(!(a instanceof Fighter)) {
                    target = a;
				} else {
					Fighter fighter = (Fighter)a;
					if(!fighter.isDead() && isEnemy(finder, fighter))
                        target = a;
				}
                if(target != null) {
                    double dis = target.getPosition().getSubXZSquare(a.getPosition());
                    if (result == null || dis < minDistance) {
                        result = a;
                        minDistance = dis;
                    }
                }
			}
		}
		return result;
	}
	
	private void updateNearbyPlayer(long now) {
        for(Player player : players.values()) {
            if(player.checkIdleExpire(now)) {
                addDeferTask(() ->leave(player, Reason.IDLE_EXPIRE));
                continue;
            }

            if(player.isActive()) {
                if(player.isOnline())
                    player.updateNearbyPlayer();
                player.updateSend();
            }
        }
		//players.values().stream().filter(player -> player.isActive()).forEach(Player::updateNearbyPlayer);
	}
	
	public void updateAgentPositionInAgentMap(Agent agent) {
		agentMap.update(agent);
	}

	
	public boolean isControllerOpen(int id) {
		return controllers.containsKey(id);
	}

	public void openController(int id) {
		if(controllers.containsKey(id))
			return;
        Trace.debug("GameMap.openController mapid:{} controllerid:{} begin", getMapid(), id);
        final Controller ctrl = new Controller(this, landscapeCfg.controllers_id.get(id));
		ctrl.open();
		controllers.put(id, ctrl);
//		Trace.info("GameMap.openController mapid:{} controllerid:{} end", getMapid(), id);
	}
	
	public void closeController(int id, Controller.CloseType closeType) {
	    addDeferTask(() -> {
            Trace.debug("GameMap.closeController mapid:{} controllerid:{} begin", getMapid(), id);
            final Controller ctrl = controllers.remove(id);
            if (ctrl != null) {
                ctrl.close(closeType);
//			Trace.info("GameMap.closeController mapid:{} controllerid:{} end", getMapid(), id);
            }
        });
	}
	
	protected void openAllControllers() {
		for(cfg.map.Controller mcCfg : this.landscapeCfg.controllers) {
//			Trace.info("GameMap.openAllControllers mapid:{} controllerid:{}", getMapid(), mcCfg.id);
			openController(mcCfg.id);
		}
	}
	
	private void updateControllers(long now) {
		for(Controller c : controllers.values())
			c.update(now);
	}

	protected void initPlayerPositionAndOrient(Player player) {
		player.initPosition(getInitPosition());
		player.initOrient(getInitOrient());
	}

    public void addSpawnObject(SpawnObject<?> object) {
        this.objects.put(object.getId(), object);
    }

    private final static double skillPerformSightRadius = CfgMgr.roleconfig.skillperformsightradius;
    final static int ATTACK_FLAG_TIME = CfgMgr.roleconfig.attackeffecttime;
    public void broadcast(Agent from, xio.Protocol msg) {
        final Set<Player> receivers = new HashSet<>(from.getSubscriptMePlayers().keySet());
        if(useBroadcastPolicy) {
            switch (msg.getType()) {
                case SSkillPerform.PROTOCOL_TYPE: {
                    final Vector3 fromPos = from.getPosition();
                    receivers.removeIf(player -> !fromPos.isSubXZMagnitudeInRadius(player.getPosition(), skillPerformSightRadius));
                    break;
                }
                case SSkillAttack.PROTOCOL_TYPE: {
                    final Agent finalFrom = from.getOwner();
                    if(finalFrom.isPlayer())
                        ((Player)finalFrom).send(from.getAid(), msg);
                    return;
                }
                case SAddEffect.PROTOCOL_TYPE:
                case SRemoveEffect.PROTOCOL_TYPE:
                case SSkillInterrupt.PROTOCOL_TYPE: {
                    final Agent finalFrom = from.getOwner();
                    receivers.removeIf(player -> player != finalFrom && !player.isRecentAttackByOther(finalFrom, ATTACK_FLAG_TIME) && !player.isRecentMeAttack(finalFrom, ATTACK_FLAG_TIME));
                    break;
                }
            }
        }
        send(receivers, from, msg);
    }

	public void enter(Agent agent) {
		final long aid = agent.getAid();
		if(agents.get(aid) != null) {
			xdb.Trace.error("GameMap.enter {} has been in {}", agent, this);
			return;
		}

		final Player player = agent.isPlayer() ? (Player)agent : null;
        if(agent.isPlayerOrFakePlayerOrPet()) {
            Trace.info("GameMap.enter {} {}", this, agent);
        } else {
            Trace.debug("GameMap.enter {} {}", this, agent);
        }
		if(player != null) {
			if(!MapMgr.Ins.playerEnterMap(aid, this)) {
				Trace.error("GameMap.enter roleid:{} has been in other gamemap.", aid);
				return;
			}
			players.put(aid, player);
		}

		dirty = true;
		agents.put(aid, agent);

		if(MapUtils.isNullVector3(agent.getPosition())) {
			if(agent.isPlayerOrFakePlayer()) {
				initPlayerPositionAndOrient(agent.asPlayerOrFakePlayer());
			} else {
				agent.initPosition(getInitPosition());
				agent.initOrient(getInitOrient());
			}
		}
		agentMap.add(agent);
		onAgentEnter(agent);
		if(player != null) {
			onPlayerEnter(player);
		}
		agent.onEnter();

        agent.setActive(true);
        if(player != null) {
            sendPlayerEnter(player);
        }

		// 进入之后再刷新视野
        final Set<Player> receivers = new HashSet<>();
		players.values().stream().filter(p -> p.getPosition().getSubXZMagnitude(agent.getPosition()) < getInnerSightRadius())
				.forEach(p -> {
			p.mySubscriptAgents.put(agent, new MySubscriptInfo(agent, false));
			agent.addSubscriptMePlayer(p);
            receivers.add(p);
		});
        if(!receivers.isEmpty())
            send(receivers, agent, agent.createSelfEnter());

		if(agent instanceof Fighter) {
			final Fighter fighter = (Fighter)agent;
			fighter.clearBorn();
			fighter.clearRevive();
		}

		if(player != null) {
			player.updateNearbyPlayer();
        }
        agent.onEnterDone();
	}

	public MEnterMap createMEnterMap(int ctxid, Player player) {
		final MEnterMap msg = new MEnterMap();
		msg.retcode = 0;
		msg.roleid = player.getAid();
		msg.ctxid = ctxid;
		player.fillMapContext(msg.cur);
        msg.rolenum = getPlayerNum();
		return msg;
	}

	protected MLeaveMap createMLeaveMap(int ctxid, Player player, int reason) {
		final MLeaveMap msg = new MLeaveMap();
		msg.ctxid = ctxid;
		msg.retcode = 0;
		msg.roleid = player.getAid();
        player.fillMapContext(msg.cur);
        msg.rolenum = getPlayerNum();
		return msg;
	}
	
	public void leave(Agent agent, int reason) {
		final long aid = agent.getAid();
		if(agents.get(aid) != agent) {
			xdb.Trace.warn("GameMap.leave error. {} not in {}", agent, this);
			return;
		}

        if(agent.isPlayerOrFakePlayerOrPet()) {
            Trace.info("GameMap.leave {} {} reason:{}", this, agent, reason);
        } else {
            Trace.debug("GameMap.leave {} {} reason:{}", this, agent, reason);
        }
		agent.setReason(reason);
		// 先清完视野再离开
		final Player player = agent.isPlayer() ? (Player)agent : null;
		if(player != null) {
			for (Agent a : player.mySubscriptAgents.keySet()) {
				a.removeSubscriptMePlayer(player);
			}
			player.mySubscriptAgents.clear();
		}

		final List<Agent> leaves = Arrays.asList(agent);
		for(Player p : agent.getSubscriptMePlayers().keySet()) {
			p.mySubscriptAgents.remove(agent);
			p.onAgentsLeave(leaves);
		}
		agent.getSubscriptMePlayers().clear();

		agent.setActive(false);
		agent.onLeave();
		if(player != null) {
			onPlayerLeave(player);
		}
		onAgentLeave(agent);
		// 清除完视野
		agentMap.remove(agent);
		if(player != null) {
			players.remove(aid, player);
			MapMgr.Ins.playerLeaveMap(aid, this);
		}
		agents.remove(aid, agent);
        dirty = true;
        agent.trigger(Listener.LEAVE, reason);
	}

	public void externPlayerEnter(int ctxid, PlayerBuilder builder) {
		addExternTask(() -> {
		    final long roleid = builder.basic.basic.agentid;
			Player player = players.get(roleid);
            final ErrorCode err;
			if(player != null) {
			    if(!this.isEctype() || !((Ectype)this).hasEnd()) {
                    player.reenter();
                    MapMgr.dispatcher.sendServerByRoleid(player.getRoleid(), createMEnterMap(ctxid, player));
                    return;
                } else {
                    leave(player, Reason.ENTER_PREV_MAP);
                    err = ErrorCode.ECTYPE_PLAYER_LEAVE;
                }
			} else {
			    if(admit(roleid)) {
                    player = createPlayer(builder);
                    MapMgr.dispatcher.sendServerByRoleid(roleid, createMEnterMap(ctxid, player));
                    return;
                } else {
                    err = ErrorCode.NOT_ADMIT_ENTER;
                }
            }
            Trace.info("XEnterMap roleid:{} mapid:{} fail. err:{}", roleid, mapid, err);
            final MEnterMap re = new MEnterMap();
            re.ctxid = ctxid;
            re.retcode = err.getErrorId();
            re.roleid = roleid;
            re.cur.info1.mapid = mapid;
            re.rolenum = getPlayerNum();
            MapMgr.dispatcher.sendServerByRoleid(roleid, re);
		});
	}

	public void externPlayerLeave(int ctxid, long roleid, int reason) {
		addExternTask(() -> {
			final Player agent = getPlayer(roleid);
            if(agent == null) return;
			leave(agent, reason);
			MapMgr.dispatcher.sendServerByRoleid(roleid, createMLeaveMap(ctxid, agent, reason));
		});
	}

	public void externPlayerLeaveCurThenEnterNew(int ctxid, long roleid, int reason, XEnterMap msg) {
		addExternTask(() -> {
			final Player agent = getPlayer(roleid);
            if(agent != null) {
                leave(agent, reason);
            }
//			MapMgr.dispatcher.sendServerByRoleid(roleid, createMLeaveMap(ctxid, agent, reason));
			Handler.process(msg);
		});
	}

	protected void internPlayerLeave(Player player, int reason) {
		leave(player, reason);
		MapMgr.dispatcher.sendServerByRoleid(player.getRoleid(), createMLeaveMap(0, player, reason));
	}

    public final NavGraph.Path findPath(Vector3 from, Vector3 to) {
        return navGraph.findPath(from, to);
    }

	public abstract void onAgentEnter(Agent agent);
	public abstract void onAgentLeave(Agent agent);
	public abstract void onPlayerEnter(Player player);
	public abstract void onPlayerLeave(Player player);

	public abstract void sendPlayerEnter(Player player);
	public abstract void revivePlayer(Player player, int reviveType);

    public abstract void onPlayerReady(Player player);

	public void foreachNearbyInAgentMap(Agent agent, double radius, Consumer<Agent> func) {
		agentMap.foreachNearbyAgents(agent, Agent.TYPE_ALL, radius, func);
	}

    public void foreachNearbyFighterInAgentMap(Agent agent, double radius, Consumer<Fighter> func) {
        agentMap.foreachNearbyAgents(agent, cfg.fight.AgentType.PLAYER|AgentType.PET|AgentType.FAKE_PLAYER|AgentType.MONSTER, radius, func);
    }

    public void foreachNearbyFighterInAgentMap(Vector3 pos, double radius, Consumer<Fighter> func) {
        agentMap.foreachNearbyAgents(pos, cfg.fight.AgentType.PLAYER|AgentType.PET|AgentType.FAKE_PLAYER|AgentType.MONSTER, radius, func);
    }
		
	public void externBroadcastMap(Protocol proto) {
		taskQueue.add(() -> {
			broadcastMap(proto);
		});
	}

    public static RoleProtocols makeRoleProtocol(long aid, xio.Protocol msg) {
        final RoleProtocols re = new RoleProtocols();
        re.roleid = aid;
        re.ptype = msg.getType();
        re.data = new OctetsStream().marshal(msg);
        return re;
    }

    public static void send(Set<Player> receivers, Agent from, xio.Protocol msg) {
        receivers.removeIf(player -> {
            if(!player.isActive() || !player.isOnline() || !player.checkSend(from, msg)) return true;
            final long roleid = player.getRoleid();
            Trace.debug("RoleProtocol. player:{} from:{} {}{}", roleid, from.getAid(), msg.getClass().getName(), msg);
            return false;
        });

        if(receivers.isEmpty()) return;
        final RoleProtocols re = makeRoleProtocol(from.getAid(), msg);
        if(receivers.size() > 1) {
            MapMgr.dispatcher.multicastClient(receivers.stream().map(p -> p.getRoleid()).collect(Collectors.toSet()), re);
        } else {
            MapMgr.dispatcher.sendClient(receivers.iterator().next().getRoleid(), re);
        }
    }

    public static void sendNotRoleid(Set<Long> receivers, xio.Protocol msg) {
        final int n = receivers.size();
        if(n > 1) {
            MapMgr.dispatcher.multicastClient(receivers, msg);
        } else if(n == 1) {
            MapMgr.dispatcher.sendClient(receivers.iterator().next(), msg);
        }
    }
	
	public void broadcastMap(Protocol proto) {
		for(Player player : players.values()) {
			if(player.isActive()) {
				player.sendNotRoleMsg(proto);
			}
		}
	}

	public Player createPlayer(PlayerBuilder b) {
        onPrecreatePlayer(b);
		final Player player = new Player(this, b);
		enter(player);
		return player;
	}

    public boolean isEnemy(Fighter a, Fighter b) {
        if(MapModule.campRelations[a.getCamp()][b.getCamp()] == Relation.ENEMY)
            return true;

        final Player pa = a.getOriginPlayer();
        if(pa == null)
            return false;
        final Player pd = b.getOriginPlayer();
        if(pd == null)
            return false;
        if(pa == pd)
            return false;

        final int minPkLevel = CfgMgr.roleconfig.pkinfo.level.level;
        if(pa.getLevel() < minPkLevel || pd.getLevel() < minPkLevel)
            return false;

        switch (pa.getPkstate()) {
            case PKState.FAMILY_AND_TEAM:
                return !pa.isSameFamily(pd) && !pa.isSameTeam(pd);
            case PKState.TEAM:
                return !pa.isSameTeam(pd);
            default:
                return false;
        }
    }

    public boolean isFriend(Fighter a, Fighter b) {
        return !isEnemy(a, b);
    }


    public boolean checkTargetType(Fighter attacker, Fighter defencer, int relation) {
        switch (relation) {
            case Relation.SELF: {
                return attacker == defencer;
            }
            case Relation.ENEMY: {
                return isEnemy(attacker, defencer);
            }
            case Relation.FRIEND: {
                return isFriend(attacker, defencer);
            }
            default:
                return true;
        }

    }

    protected void onPrecreatePlayer(PlayerBuilder b) {

    }

    public final FakePlayer createFakePlayer(PlayerBuilder b) {
        AgentBuilder ab = b.basic.basic;
        ab.atype = AgentType.FAKE_PLAYER;
        //ab.position = ab.orient = common.Utils.nullmvector();
        b.cur.info2.hp = b.cur.info2.mp = Const.NULL;
        onPrecreatePlayer(b);
        final AIFactory.Builder ai = new AIFactory.Builder();
        ai.acfg = CfgMgr.ai.get(3); // 3 is ArenaAI
        final FakePlayer player = new FakePlayer(this, b, ai);
        enter(player);
        return player;
    }
	
	public Monster createMonster(Monster.Builder b) {
		final Monster monster = new Monster(b);
		enter(monster);
		return monster;
	}

	public Monster createMonster(int monsterid, Vector3 position, Vector3 orient) {
		return createMonster(monsterid, position, orient, PatrolType.NULL, null);
	}

    public Monster createMonster(int monsterid, int regionid, int patrolType, List<Vector3> vertexs) {
        final cfg.map.PolygonRegion region = regionSetCfg.regions_id.get(regionid).polygon;
        MapUtils.Location loc = MapUtils.randomPolygonsPositionAndOrient(region);
        return createMonster(monsterid, loc.position, loc.orient, patrolType, vertexs);

    }

	public Monster createMonster(int monsterid, Vector3 position, Vector3 orient, int patrolType, List<Vector3> vertexs) {
		final Monster.Builder builder = new Monster.Builder();
		final cfg.monster.Monster mcfg = CfgMgr.monster.get(monsterid);
		final cfg.character.Model sbcfg = CfgMgr.model.get(mcfg.modelname);
		builder.map = this;

		builder.b = new FighterBuilder();
		{
			AgentBuilder b = builder.b.basic;
			b.agentid = Agent.allocAid();
			b.atype = AgentType.MONSTER;
            b.subtype = monsterid;
			b.bodyheight = sbcfg.bodyheight;
			b.bodyradius = sbcfg.bodyradius;
			b.position = MapUtils.p2m(position);
			b.orient = MapUtils.p2m(orient);
		}

		{
			FighterBuilder b = builder.b;
			b.camp = mcfg.camp;
		}

		builder.mcfg = mcfg;

		final AIFactory.Builder ab = new AIFactory.Builder();
		final cfg.monster.DefaultAIParam acfg = mcfg.defaultai;
		if(acfg.ai > 0) {
			ab.acfg = CfgMgr.ai.get(acfg.ai);
			ab.proactive = acfg.proactive;
			ab.guardRadius = acfg.guardradius;
			ab.traceRadius = acfg.traceradius;
			ab.hostilitytype = acfg.hostilitytype;
			ab.walkbackhealhprate = acfg.walkbackhealhprate;
			ab.walkbackhealhppercentrate = acfg.walkbackhealhppercentrate;

			ab.patrolType = patrolType;
			ab.patrolPath = vertexs;
		}
		builder.ai = ab;


		return createMonster(builder);
	}

	public List<Monster> createMonsters(Map<Integer, Integer> monsterId2Num, int regionid) {
		final cfg.map.PolygonRegion region = regionSetCfg.regions_id.get(regionid).polygon;
		ArrayList<Monster> monsters = new ArrayList<>();
		for(Map.Entry<Integer, Integer> e : monsterId2Num.entrySet()) {
			final int monsterid = e.getKey();
			final int num = e.getValue();
			for(int i = 0 ; i < num ; i++) {
				MapUtils.Location loc = MapUtils.randomPolygonsPositionAndOrient(region);
				monsters.add(createMonster(monsterid, loc.position, loc.orient));
			}
		}
		return monsters;
	}

	public List<Monster> createMonsters(int monsterid, int num, int regionid) {
		final cfg.map.PolygonRegion region = regionSetCfg.regions_id.get(regionid).polygon;
		ArrayList<Monster> monsters = new ArrayList<>();
		for(int i = 0 ; i < num ; i++) {
			MapUtils.Location loc = MapUtils.randomPolygonsPositionAndOrient(region);
			monsters.add(createMonster(monsterid, loc.position, loc.orient));
		}
		return monsters;
	}

	public Monster createMonster(int monsterid, int regionid) {
		MapUtils.Location loc = MapUtils.randomPolygonsPositionAndOrient(regionSetCfg.regions_id.get(regionid).polygon);
		return createMonster(monsterid, loc.position, loc.orient);
	}

	private cfg.map.PolygonRegion randomRegion(int ignoreRegionid) {
	    final int n = regionSetCfg.regions.size();
        if(n > 1) {
            while(true) {
                final cfg.map.IndexedPolygonRegion region = regionSetCfg.regions.get(Utils.random().nextInt(n));
                if (region.id != ignoreRegionid) {
                    return region.polygon;
                }
            }
		} else if(n == 1) {
            final cfg.map.IndexedPolygonRegion region = regionSetCfg.regions.get(0);
            return region.id != ignoreRegionid ? region.polygon : null;
        }
		return null;
	}

	private static class Counter {
		public int num;
	}

    public interface OnCreateMonster {
        void process(Monster monster);
    }

    public interface OnBatchBegin {
        void process();
    }

    public interface OnBatchEnd {
        void process();
    }

    public interface OnAllBatchEnd {
        void process();
    }

	public interface OnCreateRune {
		void process(ARune rune);
	}

    final static class PatrolInfo {
        int patrolType;
        List<pathfinding.Vector3> patrolVertexs;
    }

    protected PatrolInfo convert(cfg.map.PatrolInfo patrolInfo) {
        final PatrolInfo pi = new PatrolInfo();
        switch (patrolInfo.getTypeId()) {
            case RandomPatrol.TYPEID : {
                final cfg.map.RandomPatrol rp = (cfg.map.RandomPatrol)patrolInfo;
                pi.patrolType = PatrolType.RANDOM;
                pi.patrolVertexs = CfgMgr.ectyperegionset.get(rp.regionsetid).regions_id.get(rp.regionid).polygon.vertices.stream()
                        .map(v -> MapUtils.c2p(v)).collect(Collectors.toList());
                break;
            }
            case CurvePatrol.TYPEID: {
                final cfg.map.CurvePatrol cp = (cfg.map.CurvePatrol)patrolInfo;
                pi.patrolType = cp.patroltype;
                pi.patrolVertexs = CfgMgr.ectypecurveset.get(cp.curversetid).curves_id.get(cp.curverid).curve.vertices.stream()
                        .map(v -> MapUtils.c2p(v.position)).collect(Collectors.toList());
                break;
            }
            case NullPatrol.TYPEID: {
                pi.patrolType = PatrolType.NULL;
                pi.patrolVertexs = null;
                break;
            }
            default: {
                throw new RuntimeException("unknown patrol:" + patrolInfo);
            }
        }
        return pi;
    }

	public void createMonsters(Map<Integer, Integer> monsters, int regionid, int ignoreRegionid, cfg.map.PatrolInfo patrolInfo,
                               OnBatchEnd onBatchEnd, OnCreateMonster onCreateMonster) {
        if(patrolInfo == null) {
            createMonsters(monsters, regionid, ignoreRegionid, PatrolType.NULL, null, onBatchEnd, onCreateMonster);
            return;
        }
		final PatrolInfo pi = convert(patrolInfo);
		createMonsters(monsters, regionid, ignoreRegionid, pi.patrolType, pi.patrolVertexs, onBatchEnd, onCreateMonster);
	}

    public void createMonsters(cfg.map.MonsterSpawn mscfg, int regionid, cfg.map.PatrolInfo patrolInfo) {
        final RefObject<Integer> total = new RefObject<>(mscfg.count);
        final cfg.map.PolygonRegion region = getRegionSetCfg().regions_id.get(regionid).polygon;

        final PatrolInfo pi = convert(patrolInfo);

        final RefObject<Listener> refOnDeath = new RefObject<>(null);
        final Listener onDeath =  (go, evtid, param) -> {
                total.value += 1;
                if (mscfg.regeneratecount == -1 || (total.value += 1) <= mscfg.regeneratecount) {
                    schedule(() -> {
                        MapUtils.Location loc = MapUtils.randomPolygonsPositionAndOrient(region);
                        final Monster newMonster = createMonster(mscfg.monsterid, loc.position, loc.orient, pi.patrolType, pi.patrolVertexs);
                        newMonster.addListener(Listener.DEATH, refOnDeath.value);
                    }, (long)(mscfg.regeneratecd * 1000));
                }
            };
        refOnDeath.value = onDeath;

        for(int i = 0 ; i < mscfg.count ; i++) {
            MapUtils.Location loc = MapUtils.randomPolygonsPositionAndOrient(region);
            final Monster newMonster = createMonster(mscfg.monsterid, loc.position, loc.orient, pi.patrolType, pi.patrolVertexs);
            newMonster.addListener(Listener.DEATH, onDeath);
        }
    }

    public void createMonsters(cfg.ectype.MonsterInfo mi, int ignoreRegionid, OnBatchEnd onBatchEnd, OnCreateMonster onCreateMonster) {
        final PatrolInfo pi = convert(mi.patrol);
        createMonsters(mi.monsters, mi.regionid, ignoreRegionid, pi.patrolType, pi.patrolVertexs, onBatchEnd, onCreateMonster);
    }

	public void createMonsters(Map<Integer, Integer> monsterId2Num, int regionid, int ignoreRegionid, int patrolType,
                               List<pathfinding.Vector3> patrolVertexs, OnBatchEnd onBatchEnd, OnCreateMonster onCreateMonster) {
		final cfg.map.PolygonRegion region = regionid != Const.NULL ? regionSetCfg.regions_id.get(regionid).polygon : null;
		final Counter counter = new Counter();
        final Listener listener = (go, evtid, param) -> {
            if (--counter.num <= 0) {
                onBatchEnd.process();
            }
        };
		for(Map.Entry<Integer, Integer> e : monsterId2Num.entrySet()) {
			final int monsterid = e.getKey();
			final int num = e.getValue();
			counter.num += num;
			for(int i = 0 ; i < num ; i++) {
				MapUtils.Location loc = MapUtils.randomPolygonsPositionAndOrient(regionid != Const.NULL ? region : randomRegion(ignoreRegionid));
				final Monster monster = createMonster(monsterid, loc.position, loc.orient, patrolType, patrolVertexs);
                if(onCreateMonster != null)
                    onCreateMonster.process(monster);
                if(onBatchEnd != null) {
                    monster.addListener(Listener.DEATH, listener);
                }
			}
		}
	}

    protected static class BatchMonsterInfo {
        public int delaySeconds;
        public Map<Integer, Integer> monsters;
        public int regionid;
        public int ignoreRegionid;
        public cfg.map.PatrolInfo patrolInfo;
        OnBatchBegin onBatchBegin;
        OnBatchEnd onBatchEnd;
        OnCreateMonster onCreateMonster;
    }

    protected void createMultiBatchMonsters(List<BatchMonsterInfo> batches, OnAllBatchEnd onAllBatchEnd) {
        createMultiBatchMonsters(batches, 0, onAllBatchEnd);
    }

    protected void createMultiBatchMonsters(List<BatchMonsterInfo> batches, int index, OnAllBatchEnd onAllBatchEnd) {
        final BatchMonsterInfo batch = batches.get(index);
        if(batch.onBatchBegin != null)
            batch.onBatchBegin.process();

        schedule(() -> {
            createMonsters(batch.monsters, batch.regionid, batch.ignoreRegionid, batch.patrolInfo,
                    () -> {
                        if(batch.onBatchEnd != null)
                            batch.onBatchEnd.process();
                        final int nextIndex = index + 1;
                        if(nextIndex < batches.size())
                            createMultiBatchMonsters(batches, nextIndex, onAllBatchEnd);
                        else if(onAllBatchEnd != null)
                            onAllBatchEnd.process();
                    }, batch.onCreateMonster);
        }, batch.delaySeconds * 1000L);
    }

    protected void createMultiBatchMonsters2(List<cfg.ectype.MonsterInfo> batches, int batchPrepareSeconds, int ignoreRegionid,
             OnBatchBegin onBatchBegin, OnBatchEnd onBatchEnd, OnCreateMonster onCreateMonster, OnAllBatchEnd onAllBatchEnd) {
        createMultiBatchMonsters(batches.stream().map(b -> {
            final BatchMonsterInfo c = new BatchMonsterInfo();
            c.monsters = b.monsters;
            c.delaySeconds = batchPrepareSeconds;
            c.regionid = b.regionid;
            c.ignoreRegionid = ignoreRegionid;
            c.patrolInfo = b.patrol;
            c.onBatchBegin = onBatchBegin;
            c.onBatchEnd = onBatchEnd;
            c.onCreateMonster = onCreateMonster;
            return c;
        }).collect(Collectors.toList()), onAllBatchEnd);
    }
	
	public ANPC createNPC(ANPC.Builder b) {
		final map.agent.ANPC npc = new map.agent.ANPC(b);
		enter(npc);
		return npc;
	}

	public ANPC createNPC(int npcid, Vector3 position, Vector3 orient) {
		final ANPC.Builder builder = new ANPC.Builder();
		final cfg.npc.NPC mcfg = CfgMgr.npc.get(npcid);
		builder.map = this;
		builder.b = new AgentBuilder();
		{
			AgentBuilder b = builder.b;
			b.agentid = Agent.allocAid();
			b.atype = AgentType.NPC;
            b.subtype = npcid;
			b.bodyheight = 2;
			b.bodyradius = 0.2f;
			b.position = MapUtils.p2m(position);
			b.orient = MapUtils.p2m(orient);
		}

		builder.ncfg = mcfg;
        if(mcfg == null) {
            Trace.error("map:%s" + this + ",npcid:" + npcid + " not exist!");
        }
		return createNPC(builder);
	}
	
	public void createOneItem(map.msg.Bonus bonus, Vector3 position, Player owner) {
        final SMonsterDrop msg = new SMonsterDrop();
        msg.owner = owner.getRoleid();
        msg.bonus = bonus;
        msg.position = MapUtils.p2m(position);
		msg.orient.x = 1;
        owner.broadcastToNearby(msg);
		owner.addMonsterDropBonus(bonus);
	}

	public void createDropItems(List<map.msg.Bonus> bonuss, Vector3 position, List<Player> owners) {
	    for(map.msg.Bonus bonus : bonuss) {
            if (bonus.items.size() ==1){
                createOneItem(bonus, MapUtils.randomPositionInCircle(position, Drop.DROP_ITEM_RADIUS), common.Utils.randomChoose(owners));
            } else {
                for (Map.Entry<Integer, Integer> item : bonus.items.entrySet()) {
                    final map.msg.Bonus bs = new map.msg.Bonus();
                    bs.bindtype = bonus.bindtype;
                    bs.items.put(item.getKey(), item.getValue());
                    createOneItem(bs, MapUtils.randomPositionInCircle(position, Drop.DROP_ITEM_RADIUS), common.Utils.randomChoose(owners));
                }
            }
        }
	}

	public ARune createRune(int runeid, int regionid, int ignoreRegionid, OnCreateRune onCreateRune) {
		final cfg.map.PolygonRegion region = regionid != Const.NULL ? regionSetCfg.regions_id.get(regionid).polygon : null;
		MapUtils.Location loc = MapUtils.randomPolygonsPositionAndOrient(regionid != Const.NULL ? region : randomRegion(ignoreRegionid));
		Vector3 position = loc.position;
		return createRune(runeid, position, onCreateRune);
	}

	public ARune createRune(int runeid, Vector3 position, OnCreateRune onCreateRune) {
		final ARune.Builder builder = new ARune.Builder();
		builder.map = this;
		builder.b = new AgentBuilder();
		builder.runeid = runeid;
		{
			AgentBuilder b = builder.b;
			b.agentid = Agent.allocAid();
			b.atype = AgentType.RUNE;
			b.subtype = runeid;
			b.bodyheight = 2;
			b.bodyradius = 0.2f;
			b.position = MapUtils.p2m(position);
			b.orient = MapUtils.p2m(MapUtils.createOrient(0));
		}
		final ARune rune = createRune(builder);
		if (onCreateRune != null) {
			onCreateRune.process(rune);
		}
        return rune;
	}

	private ARune createRune(ARune.Builder b) {
		final ARune rune = new ARune(b);
		enter(rune);
		return rune;
	}

	public AMine createMine(AMine.Builder b) {
		final AMine mine = new AMine(b);
		enter(mine);
		return mine;
	}
	
	public AMine createMine(int mineid, Vector3 position) {
		final AMine.Builder builder = new AMine.Builder();
		builder.map = this;
		builder.b = new AgentBuilder();
		{
			AgentBuilder b = builder.b;
			b.agentid = Agent.allocAid();
			b.atype = AgentType.MINE;
            b.subtype = mineid;
			b.bodyheight = 2;
			b.bodyradius = 0.2f;
			b.position = MapUtils.p2m(position);
			b.orient = MapUtils.p2m(MapUtils.createOrient(0));
		}

		final cfg.mine.Mineral mcfg = CfgMgr.mineral.get(mineid);
		builder.mcfg = mcfg;
		return createMine(builder);
	}
	
	public APet createPet(APet.Builder b) {
		final APet pet = new APet(b);
		enter(pet);
		return pet;
	}

	public APet createPet(Player master, PetBuilder b) {
		final APet.Builder pb = new APet.Builder();
		pb.b = b;
		pb.map = this;
		pb.master = master;

		final Fighter owner = getFighter(b.ownerid);
		if(owner != null) {
			b.basic.camp = owner.getCamp();
		}
		return createPet(pb);
	}

	public void sendXdbServer(xio.Protocol msg) {
	    MapMgr.dispatcher.sendXdbServer(getServerid(), msg);
    }

	public boolean eatRune(Player player, ARune arune) {
		double disSquare = player.getPosition().getSubXZSquare(arune.getPosition());
		if (disSquare > 3 * 3) {
			xdb.Trace.error("player {} is too far from the rune {}, {}", player.getRoleid(), arune.getAid(), disSquare);
			player.sendNotRoleMsg(new SEatRune(arune.runeid, arune.getAid(), 0));
			return false;
		}
        leave(arune, Reason.EATEN);
        return eatRune(player, arune.runeid, arune.getAid());
	}

    public boolean eatRune(Player player, int runeid, long aid){
        final Rune rune = CfgMgr.rune.get(runeid);
        if (rune instanceof ItemRune) {
            ItemRune itemRune = (ItemRune) rune;
            Bonus bonus = new Bonus();
            int num = itemRune.num;
            if(itemRune.itemid == CurrencyType.JingYan){
                num = CfgMgr.exptable.get(player.getLevel()).partyexp * num;
            }
            bonus.items.put(itemRune.itemid, num);
            player.addBonus(bonus);
        } else if (rune instanceof BuffRune) {
            BuffRune buffRune = (BuffRune) rune;
            Buff.installNotSkillHitPointBuff(player, buffRune.buffid);
        } else if (rune instanceof RandomRune){
            RandomRune randomRune = (RandomRune)rune;
            List<Integer> weight = randomRune.runes.stream().map(r -> r.droprate).collect(Collectors.toList());
            int index = common.Utils.getRandomIndex(weight);
            eatRune(player, randomRune.runes.get(index).rune, aid);
        } else if(rune instanceof MultiRune){
            MultiRune multiRune = (MultiRune)rune;
            Bonus bonus = new Bonus();
            common.Bonus.genBonusByProfession(player.getProfession(), multiRune.items, 1, bonus);
            player.addBonus(bonus);
        }
        player.sendNotRoleMsg(new SEatRune(runeid, aid, 1));
        xdb.Trace.debug("rune leave" + aid);
        return true;
    }


}

package map.agent;

import cfg.ai.AI;
import cfg.fight.AgentType;
import cfg.map.Reason;
import map.MapUtils;
import map.map.GameMap;
import map.msg.AgentBuilder;
import xio.Protocol;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Agent {
	public static final int TYPE_ALL = 0xffffffff;
	private static final AtomicLong NEXT_AID = new AtomicLong(0);
	public static long allocAid() {
		return NEXT_AID.decrementAndGet();
	}

	protected final GameMap map;
	private final long aid;
	private final int type;
	private final int subType;

	protected pathfinding.Vector3 position;
	protected pathfinding.Vector3 orient;

	protected double bodyHeight;
	protected double bodyRadius;

	private int reason;

	private final Map<Integer, Set<Listener>> listeners = new HashMap<>();

	private boolean active; // 如果active = false,表示这个对象已经被销毁

	public Agent(GameMap map, AgentBuilder b) {
		this.map = map;
		this.aid = b.agentid;
		this.type = b.atype;
		this.subType = b.subtype;

		if(b.position != null) {
			this.position = MapUtils.m2p(b.position);
			this.orient = MapUtils.m2p(b.orient);
		} else {
			this.position = this.orient = null;
		}
		this.bodyRadius = b.bodyradius;
		this.bodyHeight = b.bodyheight;
		this.active = false;
		this.reason = Reason.NULL;
        this.idleTime = map.getNow();
	}

	@Override
	public String toString() {
		return String.format("%s{aid:%s, type:%d, position:%s }", this.getClass().getSimpleName(), aid, type, position);
	}

	public final boolean isFighter() {
	    return this instanceof Fighter;
    }

    public final Fighter asFighter() {
        if(this instanceof Fighter) {
            return (Fighter) this;
        } else {
            return null;
        }
    }

	public abstract String getName();

	public final long getNow() {
		return map.getNow();
	}

	public final long getDeltaTime() {
		return map.getDeltaTime();
	}

    public boolean canMove() {
        return true;
    }

    public double getSpeed() {
        return 0;
    }

	public final GameMap getMap() {
		return map;
	}
	public final long getAid() {
		return aid;
	}
	public final int getType() {
		return type;
	}
	public final int getSubType() {
		return subType;
	}

	public final int getReason() {
		return reason;
	}
	public final void setReason(int reason) {
		this.reason = reason;
	}

	public final void initPosition(pathfinding.Vector3 position) {
		this.position = position;
	}
	public final pathfinding.Vector3 getPosition() {
		return position;
	}

	public final void setPosition(pathfinding.Vector3 position) {
		this.position = position;
		updatePositionInAgentMap();
	}

	public final void initOrient(pathfinding.Vector3 orient) {
		this.orient = orient;
	}
	public final pathfinding.Vector3 getOrient() {
		return orient;
	}

	public void setOrient(pathfinding.Vector3 orient) {
		this.orient = orient;
	}

	public double getBodyRadius() {
		return bodyRadius;
	}
	public double getBodyHeight() {
		return bodyHeight;
	}

	public final boolean isActive() {
		return active;
	}
	public final void setActive(boolean active) {
		this.active = active;
	}

    public final boolean isPlayerOrFakePlayer() {
        return (type & (AgentType.PLAYER | AgentType.FAKE_PLAYER)) != 0;
    }

    public final boolean isPlayerOrFakePlayerOrPet() {
        return (type & (AgentType.PLAYER | AgentType.FAKE_PLAYER | AgentType.PET)) != 0;
    }

    public final Player asPlayerOrFakePlayer() {
        return this instanceof Player ? (Player)this : null;
    }

	public final boolean isPlayer() {
		return type == AgentType.PLAYER;
	}

	public final boolean isFakePlayer() {
	    return type == AgentType.FAKE_PLAYER;
    }

	public final boolean isPet() {
		return type == AgentType.PET;
	}
	public final boolean isMonster() {
		return type == AgentType.MONSTER;
	}

	public final void updatePositionInAgentMap() {
		map.updateAgentPositionInAgentMap(this);
	}

    public Agent getOwner() {
        return this;
    }

	private boolean hasDeferTask;
	private ArrayList<Runnable> deferTasks = new ArrayList<>();
	public final void addDeferTask(Runnable task) {
		if(!hasDeferTask) {
			hasDeferTask = true;
			map.addDeferTask(this::runDeferTasks);
		}
		deferTasks.add(task);
	}
	
	public void runDeferTasks() {
		hasDeferTask = false;
        for(int i = 0 ; i < deferTasks.size() ; i++) {
            final Runnable task = deferTasks.get(i);
            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
                map.leave(this, Reason.INTERN_EXCEPTION);
                return;
            }
        }
        deferTasks.clear();
	}

    public void schedule(Runnable task, long delay) {
        map.schedule(this, task, delay);
    }

    public void scheduleAlwaysExecute(Runnable task, long delay) {
        map.scheduleAlwaysExecute(task, delay);
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
		if(ls != null) {
			ls.remove(listener);
		}
	}

	private final static Listener[] EMPTY_LISTENER = new Listener[0];
	public void trigger(int evtid, Object param) {
		final Set<Listener> ls = listeners.get(evtid);
		if(ls != null && !ls.isEmpty()) {
			for(Listener listener : ls.toArray(EMPTY_LISTENER)) {
				listener.onTrigger(this, evtid, param);
			}
		}
		map.trigger(this, evtid, param);
	}

	public void clearListeners() {

	}

    public final static class SubscriptMeInfo {
        public final Player player;
        public long lastSendMoveTime;
        public SubscriptMeInfo(Player player) {
            this.player = player;
        }
    }

	protected HashMap<Player, SubscriptMeInfo> subscriptMeAgents = new HashMap<>();
    private long idleTime;

    public HashMap<Player, SubscriptMeInfo> getSubscriptMePlayers() {
        return subscriptMeAgents;
    }

    public void addSubscriptMePlayer(Player player) {
        this.subscriptMeAgents.put(player, new SubscriptMeInfo(player));
        onAddSubscript(player);
    }

    public void removeSubscriptMePlayer(Player player) {
        this.subscriptMeAgents.remove(player);
        onRemoveSubscript(player);
    }

    protected void onAddSubscript(Player player) {
        idleTime = Long.MAX_VALUE;
    }

    protected void onRemoveSubscript(Player player) {
        if(subscriptMeAgents.isEmpty()) {
            idleTime = getNow() + AI.IDLE_TIME * 1000L;
        }
    }

    public void onBeAttackByOther(Fighter other) {

    }

    public void onAttackOther(Fighter other) {

    }

    protected void setIdleTime(long time) {
        this.idleTime = time;
    }

    protected boolean inAIIdle() {
        return idleTime < getNow();
    }

	public abstract xio.Protocol createSelfEnter();

	public void broadcastToNearby(Protocol proto) {
		this.map.broadcast(this, proto);
	}

	public void onEnter() {
	}

	public void onLeave() {
	}

	public void onEnterDone() {

    }

	public abstract void update(long now);
}

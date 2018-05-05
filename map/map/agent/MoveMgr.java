package map.agent;

import cfg.CfgMgr;
import map.MapUtils;
import map.map.GameMap;
import map.msg.SMove;
import map.msg.SStop;
import pathfinding.Vector3;
import xdb.Trace;

import java.util.*;

public final class MoveMgr {
	private Agent self;

	private Vector3 targetPosition;
	private boolean ignoreSelf;

	
	public MoveMgr(Agent self) {
		this.self = self;
	}
	
	public void init(Vector3 targetPosition) {
		this.targetPosition = targetPosition;
		this.ignoreSelf = false;
	}

	public Vector3 getTargetPosition() {
		return targetPosition;
	}

    public Vector3 getTargetPositionOrCurPosition() {
        return targetPosition != null ? targetPosition : self.getPosition();
    }

    public void setTarget(Vector3 targetPosition) {
        setTarget(targetPosition, false);
    }
	
	public void setTarget(Vector3 targetPosition, boolean ignoreSelf) {
		if(this.targetPosition == null || !targetPosition.equals(this.targetPosition)) {
			this.targetPosition = targetPosition;
			this.ignoreSelf = ignoreSelf;
            self.setOrient(targetPosition.sub(self.getPosition()));
			notifyMovement();
		}
	}

    public void stopAtPosition(Vector3 position, Vector3 orient) {
        stopAtPosition(position, orient, false);
    }

	public void stopAtPosition(Vector3 position, Vector3 orient, boolean ignoreSelf) {
		boolean change = false;
		if(!self.getPosition().equals(position)) {
			self.setPosition(position);
			change = true;
		}
		if(!self.getOrient().equals(orient)) {
			self.setOrient(orient);
			change = true;
		}
		if(this.targetPosition != null) {
			this.targetPosition = null;
			change = true;
		}
		if(change) {
			this.ignoreSelf = ignoreSelf;
			notifyMovement();
		}
	}
	
	public void stopAtCurPosition() {
		if(this.targetPosition != null) {
			this.targetPosition = null;
			this.ignoreSelf = false;
			notifyMovement();
		}
	}

    public void moveTo(Vector3 position) {
        moveTo(position, false);
    }

	public void moveTo(Vector3 position, boolean isPlayerControl) {
		boolean change = false;
		final Vector3 pos = self.getPosition();
		if(!pos.equals(position)) {
			self.setPosition(position);
			change = true;
		}
		if(this.ignoreSelf != isPlayerControl) {
			this.ignoreSelf = isPlayerControl;
			change = true;
		}
		if(change) {
			notifyMovement();
		}
	}

	public Vector3 getVelocity() {
	    if(targetPosition != null) {
            Vector3 v = targetPosition.sub(self.getPosition());
            return v.getXZSquare() < 0.01 ? Vector3.ZERO : v.scaleXZ(self.getSpeed());
        } else {
            return Vector3.ZERO;
        }
    }

	public boolean isStop() {
		return targetPosition == null;
	}

	public xio.Protocol createMove() {
        return (targetPosition != null) ?
                 new SMove(MapUtils.p2m(self.getPosition()), MapUtils.p2m(targetPosition))
                :new SStop(MapUtils.p2m(self.getPosition()), MapUtils.p2m(self.getOrient()));
	}
	
	private boolean needNotifyMove = false;
    private long nextSendMoveTime;
    private static final long nearSendMoveInterval = (long)(CfgMgr.roleconfig.movebroadcastinterval * 1000);
    private static final long farSendMoveInterval = 3000;
    private LinkedList<Agent.SubscriptMeInfo> dirtyPlayers = new LinkedList<>();

	public void notifyMovement() {
        if(needNotifyMove) return;
        final int n = self.getSubscriptMePlayers().size();
        if(n == 0 || (ignoreSelf && n == 1)) return;
        needNotifyMove = true;
        if(nextSendMoveTime < self.getNow()) {
            self.addDeferTask(this::broadcastToNearbyMovement);
        } else {
            // 现在所有玩家都是dirty的,下一次update时通知.
            nextSendMoveTime = self.getNow();
        }
        dirtyPlayers.clear();
	}

    private final Set<Player> sendRoleids = new HashSet<>();
	public final void broadcastToNearbyMovement() {
        final Vector3 selfPos = self.getPosition();
        final long now = self.getNow();
        nextSendMoveTime = Long.MAX_VALUE;
        if(needNotifyMove) {
            needNotifyMove = false;
            dirtyPlayers.clear();
            for(Agent.SubscriptMeInfo si : self.getSubscriptMePlayers().values()) {
                final Player player = si.player;
                final double distance = player.getPosition().getSubXZMagnitude(selfPos);
                final long sendTime = now + Math.max((long)(distance  / player.getAttrMgr().getMoveSpeed() * 500), nearSendMoveInterval);
                if(si.lastSendMoveTime < now - nearSendMoveInterval) {
                    sendRoleids.add(player);
                    si.lastSendMoveTime = now;
                } else {
                    dirtyPlayers.add(si);
                    if(sendTime < nextSendMoveTime)
                        nextSendMoveTime = sendTime;
                }
            }

        } else if(!dirtyPlayers.isEmpty()) {
            Trace.debug("broadcastToNearbyMovement 2");
            for(Iterator<Agent.SubscriptMeInfo> it = dirtyPlayers.iterator() ; it.hasNext() ;) {
                final Agent.SubscriptMeInfo si = it.next();
                final Player player = si.player;
                final double distance = player.getPosition().getSubXZMagnitude(selfPos);
                final long sendTime = now + Math.max((long)(distance  / player.getAttrMgr().getMoveSpeed() * 500), nearSendMoveInterval);
                if(si.lastSendMoveTime < now - nearSendMoveInterval) {
                    sendRoleids.add(player);
                    si.lastSendMoveTime = now;
                    it.remove();
                } else {
                    if(sendTime < nextSendMoveTime)
                        nextSendMoveTime = sendTime;
                }
            }
        }
        if(ignoreSelf)
            sendRoleids.remove(self.getAid());
        if(!sendRoleids.isEmpty()) {
            Trace.debug("self:{} broadcastToNearbyMovement sendroleids:{}", self, sendRoleids);
            GameMap.send(sendRoleids, self, createMove());
            sendRoleids.clear();
        }
        if(nextSendMoveTime != Long.MAX_VALUE && nextSendMoveTime > now + farSendMoveInterval)
            nextSendMoveTime = now + farSendMoveInterval;
	}

    public double calcDeltaMoveDistance() {
        return (targetPosition == null || !self.canMove()) ? 0 : self.getSpeed() * self.getDeltaTime() / 1000;
    }

	public void update(long now) {
		if(targetPosition != null && self.canMove()) {
            final double distance = self.getSpeed() * self.getDeltaTime() / 1000;
            Vector3 position = self.getPosition();
            Vector3 delta = targetPosition.sub(position);
            final double deltaDistance = delta.getXZMagnitude();
            if (deltaDistance <= distance) {
                self.setPosition(targetPosition);
                targetPosition = null;
            } else {
                self.setPosition(position.plus(delta.scaleXZ(distance)));
            }
        }
        if(nextSendMoveTime <= now)
            broadcastToNearbyMovement();
	}

	public void onDead() {
		stopAtCurPosition();
	}

	public void onRevive(pathfinding.Vector3 position, pathfinding.Vector3 orient) {
		if(position != null) {
			self.setOrient(orient);
			self.setPosition(position);
		}
	}
	
}

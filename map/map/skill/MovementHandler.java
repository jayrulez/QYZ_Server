package map.skill;

import map.agent.Fighter;
import map.agent.MoveMgr;
import map.agent.Player;
import pathfinding.Vector3;

public final class MovementHandler extends ActionHandler<cfg.skill.Movement> {
	private final long endTime;
	private final Fighter attacker;
	private final Fighter defencer;
    private final MoveMgr attackMoveMgr;
    private final Vector3 direction;
	
	private Vector3 attackerOriginPosition;
	public MovementHandler(Skill skill, long startTime, cfg.skill.Movement action) {
		super(skill, startTime, action);
		this.endTime = startTime + (long)(action.duration * 1000);
		this.attacker = skill.getAttacker();
		this.defencer = skill.getDefencer();
		this.direction = skill.getDirection();
        this.attackMoveMgr = attacker.getMoveMgr();

		this.attackerOriginPosition = null;

        final Player player = skill.getAttacker().asPlayerOrFakePlayer();
        if(player != null) {
            player.addServerMoveDistance(action.speed * action.duration);
        }
	}

	@Override
	public boolean process(long now) {
		if(this.attackerOriginPosition == null) {
			this.attackerOriginPosition = attacker.getPosition();
		}
		if(now < this.endTime) {
			double progress = (double)(now - startTime) / (endTime - startTime);
			switch(action.type) {
			case cfg.skill.Movement.MoveToTarget: {
                if(defencer != null) {
					attacker.setPosition(attackerOriginPosition.lerp(defencer.getPosition(), progress));
				} else {
					attacker.setPosition(attackerOriginPosition.plus(direction.scaleXZ(progress * (endTime - startTime) / 1000 * action.speed)));
				}
				break;
			}
			case cfg.skill.Movement.MoveInDirection: {
				attacker.setPosition(this.attackerOriginPosition.plus(direction.scale(action.speed * (now-startTime)/1000)));
				break;
			}
			default: {
				//throw new RuntimeException("unknown movement type:" + action.type);
			}
			}
			return true;
		} else {
			switch(action.type) {
			case cfg.skill.Movement.MoveToTarget: {
                if(defencer != null) {
                    attackMoveMgr.moveTo(defencer.getPosition(), true);
				} else {
                    attackMoveMgr.moveTo(attackerOriginPosition.plus(direction.scaleXZ(action.speed * (endTime - startTime) / 1000)), true);
				}
				//attacker.broadcastToNearbyMovement();
				break;
			}
			case cfg.skill.Movement.MoveInDirection: {
				//attacker.setPosition(this.attackerOriginPosition.plus(direction.scaleXZ(action.speed * action.duration)));
                attackMoveMgr.moveTo(this.attackerOriginPosition.plus(direction.scaleXZ(action.speed * action.duration)), true);
				break;
			}
			default: {
				//throw new RuntimeException("unknown movement type:" + action.type);
			}
			}
			return false;
		}
	}

}

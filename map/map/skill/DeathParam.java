package map.skill;

import map.agent.APet;
import map.agent.Fighter;
import map.agent.Player;

public final class DeathParam {
	public DeathParam(Fighter attacker, Fighter defencer) {
		this.attacker = attacker;
		this.defencer = defencer;
	}
	public final Fighter attacker;
	public final Fighter defencer;

	public Player getFinalAttackerAsPlayer() {
		if(attacker.isPlayerOrFakePlayer()) {
			return (Player)attacker;
		} else if(attacker.isPet()) {
			return ((APet)attacker).getOwner();
		} else {
			return null;
		}
	}
}

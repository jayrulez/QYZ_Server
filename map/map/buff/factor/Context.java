package map.buff.factor;

import map.agent.Fighter;
import map.skill.AttackType;

public final class Context {
	public final Fighter attacker;
    public final Fighter defencer;
    public final int skillLevel;
    public final AttackType type;

    public Context(AttackType type, Fighter attacker, Fighter defencer, int skillLevel) {
        this.type = type;
        this.attacker = attacker;
        this.defencer = defencer;
        this.skillLevel = skillLevel;
    }
}

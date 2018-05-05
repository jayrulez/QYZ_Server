package map.skill;

import map.agent.Fighter;

import java.util.Collection;

public final class AttackInfo {
	public map.skill.Skill skill;
	public cfg.skill.Action actionCfg;
	public cfg.skill.HitZone hitzone;
	public Collection<Fighter> defencers;
}

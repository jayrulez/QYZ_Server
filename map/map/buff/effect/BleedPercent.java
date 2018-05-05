package map.buff.effect;


import map.agent.Fighter;
import map.buff.factor.Context;
import map.skill.AttackType;

public final class BleedPercent extends IntervalEffect<cfg.buff.BleedPercent> {
	public BleedPercent(cfg.buff.BleedPercent ecfg, Fighter caster, int endType, long endTime) {
		super(ecfg, caster, endType, endTime);
	}

	@Override
	public void doIntervalAction(long now) {
		target.decHPAndCheckDead(new Context(AttackType.BLEED, caster, target, getSkillLevel()), target.getAttrMgr().getHPFullValue() * ecfg.value);
	}

}

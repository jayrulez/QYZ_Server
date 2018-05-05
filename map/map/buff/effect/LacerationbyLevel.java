package map.buff.effect;

import common.Utils;
import map.agent.AttrMgr;
import map.agent.Fighter;
import map.buff.factor.Context;
import map.buff.factor.Factor;
import map.buff.factor.Prioritys;
import map.skill.AttackType;

/**
 * Created by huangqiang on 2016/4/5.
 */
public class LacerationbyLevel extends IntervalEffect<cfg.buff.LacerationbyLevel> {

    public LacerationbyLevel(cfg.buff.LacerationbyLevel ecfg, int skillLevel, Fighter caster, int endType, long endTime) {
        super(ecfg, skillLevel, caster, endType, endTime);
        final int levelIndex = Math.min(skillLevel, ecfg.rate.size()) - 1;
        this.skillMultiRate = ecfg.rate.get(levelIndex);
        this.skillExtraDamage = ecfg.damage.get(levelIndex);
    }

    private final float skillMultiRate;
    private final float skillExtraDamage;

    @Override
    public void doIntervalAction(long now) {
        final AttrMgr apm = caster.getAttrMgr();
        final AttrMgr dpm = target.getAttrMgr();
        final double luckyValue = apm.getLuckyValue();
        final double defence = dpm.getDefence();
        final boolean isLucky = Utils.random01() < luckyValue;
        final double attack = isLucky ? apm.getAttackValueMax() : Utils.randomRange(apm.getAttackValueMin(), apm.getAttackValueMax());

        final double attackMultiRate = apm.getAttackMultiRate();
        final double defenceMultiRate = dpm.getDefenceMultiRate();
        target.decHPAndCheckDead(new Context(AttackType.LACERATION, caster, target, getSkillLevel()), new Factor(Prioritys.ATTACK) {

                @Override
                public double calc(double value, map.buff.factor.Context ctx) {
                    value += attack;
                    value = Math.max(1, value * skillMultiRate + skillExtraDamage - (ecfg.ignoredefence ? 0 : defence)) * (1 + attackMultiRate - defenceMultiRate);
                    return value;
                }

        });
    }
}

package map.skill;

import cfg.CfgMgr;
import cfg.fight.AttrId;
import cfg.skill.*;
import common.Utils;
import map.MapUtils;
import map.agent.AttrMgr;
import map.agent.Fighter;
import map.agent.Player;
import map.buff.Buff;
import map.buff.effect.SkillEffect;
import map.buff.factor.Context;
import map.buff.factor.Factor;
import map.buff.factor.Prioritys;
import map.msg.AttackResult;
import map.msg.HealResult;
import map.msg.SSkillAttack;
import xdb.Trace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class HitPointHandler<T extends cfg.skill.HitPointAction> extends ActionHandler<cfg.skill.Attack> {

    public static HitPointHandler create(Skill skill, long startTime, Attack action) {
        final HitPointAction hpa = skill.getDmgCfg().hitpoints.get(action.id);
        final HitZone hitZone = skill.getSkillCfg().hitzones_id.get(action.hitzoneid);
        switch (hpa.getTypeId()) {
            case Heal.TYPEID: {
                return new HealHandler(skill, startTime, action, hitZone, (Heal)hpa);
            }
            case NormalAttack.TYPEID: {
                return new NormalAttackHandler(skill, startTime, action, hitZone, (NormalAttack)hpa);
            }
            case BuffAttack.TYPEID: {
                return new BuffAttackHandler(skill, startTime, action, hitZone, (BuffAttack) hpa);
            }
            default: {
                throw new RuntimeException("unknown HitPointAction:" + hpa);
            }
        }
    }



	protected final cfg.skill.HitZone hitZone;
    protected final T hitpointAction;

    protected final List<SkillEffect> effects;
    protected final int extraHitNum;
	public HitPointHandler(Skill skill, long startTime, Attack action, cfg.skill.HitZone hitZone, T hitpointAction) {
		super(skill, startTime, action);
		this.hitZone = hitZone;
        this.hitpointAction = hitpointAction;
        this.effects = skill.getEffects().stream().filter(e -> e.getCfg().hitpoint.contains(action.id)).collect(Collectors.toList());
        this.extraHitNum = effects.isEmpty() ? 0 : this.effects.stream().mapToInt(e -> e.getCfg().targetamountchange).sum();
	}

	public static void doAction(Skill skill, Attack aaction, Collection<Fighter> defencers, cfg.skill.HitPointAction attackAction,
                                List<SkillEffect> effects, AttackType attackType) {
        final Fighter attacker = skill.getAttacker();
        final int skillLevel = skill.getSkillLevel();
	    final SSkillAttack re = new SSkillAttack();
        final List<Buff> buffMayEndByAttack = new ArrayList<>();
        try {
            switch (attackAction.getTypeId()) {
                case NormalAttack.TYPEID: {
                    final NormalAttack action = (NormalAttack)attackAction;
                    for (Fighter defencer : defencers) {
                        re.attacks.add(doAttack(attackType, buffMayEndByAttack, effects, attacker, defencer, skillLevel, action.action));
                    }
                    break;
                }
                case Heal.TYPEID: {
                    final Heal action = (Heal)attackAction;
                    for (Fighter defencer : defencers) {
                        re.heals.add(doHeal(buffMayEndByAttack, effects, attacker, defencer, skillLevel, action.action));
                    }
                    break;
                }
                case BuffAttack.TYPEID: {
                    final BuffAttack action = (BuffAttack)attackAction;
                    for (Fighter defencer : defencers) {
                        doBuff(attacker, defencer, skillLevel, action.buffid);
                    }
                    break;
                }
                default:
                    throw new RuntimeException("unknown ActionType:" + attackAction);
            }
        } finally {
            buffMayEndByAttack.forEach(Buff::removeEffectEndByAttack);
        }
        if(re.attacks.isEmpty() && re.heals.isEmpty())
            return;
        re.skillid = skill.getSkillid();
        re.attackerid = attacker.getAid();
        re.attackid = aaction.id;
        final Fighter target = skill.getActiveTarget();
        re.target = skill.getSkillCfg().needtarget && target != null ? target.getAid() : 0;
        re.direction = MapUtils.p2m(skill.getDirection());
        attacker.broadcastToNearby(re);
    }


    public static HealResult doHeal(List<Buff> buffMayEndByAttack, List<SkillEffect> effects, Fighter attacker,
                                    Fighter defencer, int skillLevel, AttackAction attackAction) {
        final AttrMgr apm = attacker.getAttrMgr();

        final HealResult result = new HealResult();
        result.defencerid = defencer.getAid();

        final double healRate = apm.getHPHealRate();


        final float skillMultiRate = Utils.getOrLast(attackAction.rate, skillLevel - 1);
        final float skillExtraAttack = Utils.getOrLast(attackAction.damage, skillLevel - 1);
        //负面buff命中率=MAX(1,数据表中的成功率*施放buff方攻击/被施放buff方防御*(1+施放buff方的异常命中-被施放buff方的异常抗性))
        final double debuffHitMultiRate = 1;//attack / defence * (1 + apm.getAbnormalHitRate() - dpm.getAbnormalResistRate());

        if(attackAction.prevbuffid > 0) {
            common.Utils.addNotNull(buffMayEndByAttack, Buff.installSkillHitPointBuff(attackAction.prevbuffid, skillLevel, attacker, defencer, debuffHitMultiRate));
        }
        effects.forEach(e -> {
            final cfg.buff.SkillEffect scfg = e.getCfg();
            if(scfg.buffid > 0 && scfg.isbefore) {
                common.Utils.addNotNull(buffMayEndByAttack, Buff.installSkillHitPointBuff(scfg.buffid, skillLevel, attacker, defencer, debuffHitMultiRate));
            }
        });

        final double baseHeal = Utils.randomRange(apm.getAttackValueMin(), apm.getAttackValueMax());
        final int finalHeal = (int)(Math.max(1, baseHeal * skillMultiRate + skillExtraAttack) * (1 + healRate));
        final int actualHeal = (int)defencer.addHp(finalHeal);

        Buff.installSkillHitPointBuff(attackAction.succbuffid, skillLevel, attacker, defencer, debuffHitMultiRate);
        effects.forEach(e -> {
            final cfg.buff.SkillEffect scfg = e.getCfg();
            if(scfg.buffid > 0 && !scfg.isbefore) {
                Buff.installSkillHitPointBuff(scfg.buffid, skillLevel, attacker, defencer, debuffHitMultiRate);
            }
        });

        if(false) {//gs.Utils.isTest()) {
            System.out.printf("healRate:%.2f skillMultiRate:%.2f skillExtraAttack:%.2f baseHeal:%.2f actualHeal:%d\n\n",
                    healRate, skillMultiRate, skillExtraAttack, baseHeal, actualHeal);
        }
        result.heal = finalHeal;
        result.hp = (int)defencer.getAttrMgr().getHPValue();

        final Player player = attacker.getOwner().isPlayer() ? (Player)attacker.getOwner() : null;
        if(player != null)
            player.updateKnownHp(defencer, result.hp);
        defencer.addBeHeal(result);
        return result;
    }

    public static double calcDebuffHitMultiRate(Fighter attacker, Fighter defencer) {
        final AttrMgr apm = attacker.getAttrMgr();
        final AttrMgr dpm = defencer.getAttrMgr();
        final double luckyValue1 = apm.getLuckyValue();
        final double defence1 = dpm.getDefence();
        final boolean isLucky1 = Utils.random01() < luckyValue1;
        final double attack1 = isLucky1 ? apm.getAttackValueMax() : Utils.randomRange(apm.getAttackValueMin(), apm.getAttackValueMax());

        //负面buff命中率=MAX(1,数据表中的成功率*施放buff方攻击/被施放buff方防御*(1+施放buff方的异常命中-被施放buff方的异常抗性))
        // 这儿只需要 负面buff命中倍率,即 施放buff方攻击/被施放buff方防御*(1+施放buff方的异常命中-被施放buff方的异常抗性)
        return attack1 / Math.max(defence1, 1) * (1 + apm.getAbnormalHitRate() - dpm.getAbnormalResistRate());
    }

    public static AttackResult doAttack(AttackType type, List<Buff> buffMayEndByAttack, List<SkillEffect> effects, Fighter attacker, Fighter defencer, int skillLevel, AttackAction attackAction) {
        attacker.onAttackOther(defencer);
        defencer.onBeAttackByOther(attacker);
        attacker.setInFight();
        defencer.setInFight();
        final double debuffHitMultiRate = calcDebuffHitMultiRate(attacker, defencer);

        if(attackAction.prevbuffid > 0) {
            common.Utils.addNotNull(buffMayEndByAttack, Buff.installSkillHitPointBuff(attackAction.prevbuffid, skillLevel, attacker, defencer, debuffHitMultiRate));
        }
        effects.forEach(e -> {
            final cfg.buff.SkillEffect scfg = e.getCfg();
            if(scfg.buffid > 0 && scfg.isbefore) {
                common.Utils.addNotNull(buffMayEndByAttack, Buff.installSkillHitPointBuff(scfg.buffid, skillLevel, attacker, defencer, debuffHitMultiRate));
            }
        });

        final AttackResult result = new AttackResult();
        result.defencerid = defencer.getAid();
        final AttrMgr apm = attacker.getAttrMgr();
        final AttrMgr dpm = defencer.getAttrMgr();
        final double aHitRate = apm.getHitRate();
        final double dHitResistRate = dpm.getHitResitRate();
        if (aHitRate < dHitResistRate && Utils.random01() >= Math.sqrt(aHitRate / Math.max(dHitResistRate, 1))) {
            Trace.debug("attacker:{}, defencer:{} hit:{} dodge:{}. random miss!", attacker, defencer, aHitRate, dHitResistRate);
            result.ismiss = Utils.toByte(true);
            buffMayEndByAttack.forEach(Buff::removeEffectEndByAttack);
            defencer.addBeAttack(result);
            return result;
        }
        final double luckyValue = apm.getLuckyValue();
        final double defence = Math.max(0, dpm.getDefence());
        final boolean isLucky = Utils.random01() < luckyValue;
        final double attack = isLucky ? apm.getAttackValueMax() : Utils.randomRange(apm.getAttackValueMin(), apm.getAttackValueMax());

        final double aExcellentRate = apm.getExcellentRate();
        final double dExcellentResistRate = dpm.getExcellentResistRate();
        final boolean isExcellentHit = Utils.random01() < aExcellentRate - dExcellentResistRate;
        final double aExcellentValue = apm.getExcellentValue();
        final double dExcellentResistValue = dpm.getExcellentResistValue();

        final double aCritRate = apm.getCritRate();
        final double dCritResistRate = dpm.getCritResitRate();
        final boolean isCritHit = Utils.random01() < aCritRate - dCritResistRate;

        final Context ctx = new Context(type, attacker, defencer, skillLevel);
        final double attackMultiRate = apm.getFactorValue(AttrId.CALC_ATTACK_MULTI_RATE, ctx, new map.buff.factor.Add(Prioritys.ATTACK, apm.getAttackMultiRate()));
        final double defenceMultiRate = dpm.getDefenceMultiRate();

        final float skillMultiRate = Utils.getOrLast(attackAction.rate, skillLevel - 1);
        final float skillExtraAttack = Utils.getOrLast(attackAction.damage, skillLevel - 1);

        final double aCritValue = apm.getCritValue();
        final double dCritResistValue = dpm.getCritResistValue();

        final double additionalDamage = apm.getAdditionalDamage();

        final int damage = defencer.decHPAndCheckDead(ctx, new Factor(Prioritys.ATTACK) {

            @Override
            public double calc(double value, map.buff.factor.Context ctx) {
                value += attack;
                if (isExcellentHit) {
                    value *= 1 + Math.max(aExcellentValue - dExcellentResistValue, 0);
                }
                value = Math.max(1, value * skillMultiRate + skillExtraAttack + additionalDamage - defence ) * (1 + attackMultiRate - defenceMultiRate);
                if (isCritHit) {
                    value *= Math.max(1, aCritValue - dCritResistValue);
                }
                boolean pvp = true;
                if(defencer.isPlayerOrFakePlayer()) {
                    value *= (1 + apm.getDamageToHuman());
                } else if(defencer.isPet()) {
                    value *= (1 + apm.getDamageToPet());
                } else {
                    pvp = false;
                }
                if(attacker.isPlayerOrFakePlayer()) {
                    value = Math.max(0, value * (1 - dpm.getResistDamageFromHuman()));
                } else if(attacker.isPet()) {
                    value = Math.max(0, value * (1 - dpm.getResistDamageFromPet()));
                } else {
                    pvp = false;
                }
                if(pvp)
                    value *= CfgMgr.roleconfig.pvpdamage;
                return value;
            }
        });


        // 后置buff不可能是那种 打击结束后立即移除
        Buff.installSkillHitPointBuff(attackAction.succbuffid, skillLevel, attacker, defencer, debuffHitMultiRate);
        effects.forEach(e -> {
            final cfg.buff.SkillEffect scfg = e.getCfg();
            if(scfg.buffid > 0 && !scfg.isbefore) {
                Buff.installSkillHitPointBuff(scfg.buffid, skillLevel, attacker, defencer, debuffHitMultiRate);
            }
        });

        if(false){//gs.Utils.isTest()) {
            System.out.printf("hit rate:%.2f hit_resit_rate:%.2f aExcellentRate:%.2f dExcellentResistRate:%.2f isExcellentHit:%s aExcellentValue:%.2f dExcellentResistValue:%.2f"
                            + "aCritRate:%.2f dCritResistRate:%.2f isCritHit:%s aCritValue:%.2f dCritResistValue:%.2f attackMultiRate:%.2f defenceMultiRate:%.2f"
                            + "skillMultiRate:%.2f skillExtraAttack:%.2f luckyValue:%.2f islucky:%s attack:%.2f defence:%.2f final damage:%d\n\n",
                    aHitRate, dHitResistRate, aExcellentRate, dExcellentResistRate, isExcellentHit, aExcellentValue, dExcellentResistValue,
                    aCritRate, dCritResistRate, isCritHit, aCritValue, dCritResistValue, attackMultiRate, defenceMultiRate,
                    skillMultiRate, skillExtraAttack, luckyValue, isLucky, attack, defence, damage);
        }
        result.ismiss = Utils.toByte(false);
        result.attack = damage;
        result.hp = (int)dpm.getHPValue();
        result.iscrit = Utils.toByte(isCritHit);
        result.isexcellent = Utils.toByte(isExcellentHit);
        result.islucky = Utils.toByte(isLucky);

        if(defencer.isMonster())
            defencer.getHostilityMgr().addValue(attacker, result.attack);
        if(attacker.getOwner().isPlayer())
            ((Player)attacker.getOwner()).updateKnownHp(defencer, result.hp);
        defencer.addBeAttack(result);
        return result;
    }


    public static void doBuff(Fighter attacker, Fighter defencer, int skillLevel, int buffid) {
        final AttrMgr apm = attacker.getAttrMgr();
        final AttrMgr dpm = defencer.getAttrMgr();
        final double attack = Utils.random01() < apm.getLuckyValue() ? apm.getAttackValueMax() : Utils.randomRange(apm.getAttackValueMin(), apm.getAttackValueMax());

        //负面buff命中率=MAX(1,数据表中的成功率*施放buff方攻击/被施放buff方防御*(1+施放buff方的异常命中-被施放buff方的异常抗性))
        // 这儿只需要 负面buff命中倍率,即 施放buff方攻击/被施放buff方防御*(1+施放buff方的异常命中-被施放buff方的异常抗性)
        final double debuffHitMultiRate = attack / dpm.getDefence() * (1 + apm.getAbnormalHitRate() - dpm.getAbnormalResistRate());
        Buff.installSkillHitPointBuff(buffid, skillLevel, attacker, defencer, debuffHitMultiRate);
    }
}

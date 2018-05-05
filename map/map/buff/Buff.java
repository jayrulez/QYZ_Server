package map.buff;

import cfg.CfgMgr;
import cfg.buff.EndCondition;
import cfg.fight.Relation;
import map.agent.BuffMgr;
import xdb.Trace;
import map.buff.effect.Effect;
import map.agent.Fighter;

import java.util.ArrayList;
import java.util.Arrays;

final public class Buff {
	public static Buff installSkillHitPointBuff(int buffid, int skillLevel, Fighter attacker, Fighter defencer, double negativeBuffHitMultiRate) {
		if(buffid > 0) {
			Trace.debug("installSkillHitPointBuff. buffid:{} skilllevel:{} attacker:{} defencer:{}", buffid, skillLevel, attacker, defencer);
			final cfg.buff.Buff buffCfg = CfgMgr.buff.get(buffid);
			final Fighter target = buffCfg.target == Relation.SELF ? attacker : defencer;
			if(!target.isDead()) {
				final Buff buff = new Buff(buffCfg, skillLevel, attacker);
				buff.installHitPoint(target, negativeBuffHitMultiRate);
                return buff;
			}
		}
        return null;
	}

	public static Buff installNotSkillHitPointBuff(Fighter host, int buffid) {
		if(buffid > 0) {
            Trace.debug("installNotSkillHitPointBuff. buffid:{} host:{}", buffid, host);
			cfg.buff.Buff buffCfg = CfgMgr.buff.get(buffid);
			final Buff buff = new Buff(buffCfg, host.getLevel(), host);
			buff.install(host);
            return buff;
		}
        return null;
	}

    public static Buff installFabaoBuff(Fighter host, int buffid) {
        Trace.debug("installFabaoBuff. buffid:{} host:{}", buffid, host);
        cfg.buff.Buff buffCfg = CfgMgr.buff.get(buffid);
        final Buff buff = new Buff(buffCfg, 1, host);
        buff.install(host);
        return buff;
    }

	public static Buff installPassiveSkillBuff(Fighter host, int skillLevel, int buffid) {
		Trace.debug("installPassiveSkillBuff. buffid:{} skilllevel:{} host:{}", buffid, skillLevel, host);
		cfg.buff.Buff buffCfg = CfgMgr.buff.get(buffid);
		final Buff buff = new Buff(buffCfg, skillLevel, host);
		buff.install(host);
		return buff;
	}

    public static Buff installAuraBuffToSelf(int buffid, int skillLevel, Fighter caster) {
        Trace.debug("installPassiveSkillBuff. buffid:{} skilllevel:{} host:{}", buffid, skillLevel, caster);
        final cfg.buff.Buff buffCfg = CfgMgr.buff.get(buffid);
        final Buff buff = new Buff(buffCfg, skillLevel, caster);
        buff.installForver(caster);
        return buff;
    }
	
	private final cfg.buff.Buff buffCfg;
	private final int skillLevel;
	private final long createTime;
	private final ArrayList<Effect> effects = new ArrayList<>();
	private Fighter target;
	private final Fighter caster;
	
	public Buff(cfg.buff.Buff buffCfg, int skillLevel, Fighter caster) {
		this.buffCfg = buffCfg;
		this.skillLevel = skillLevel;
		this.createTime = System.currentTimeMillis();
		this.caster = caster;
	}

	public final int getId() {
		return buffCfg.id;
	}

	public final int getType() {
		return 0;//type;
	}

	public final long getCreateTime() {
		return createTime;
	}

    private Effect createEffect(cfg.buff.EffectInfo ei) {
        final cfg.buff.Effect effect = CfgMgr.effect.get(ei.effectid);
        return Effect.createEffect(effect, ei.endconditiontype, ei.duration, skillLevel, caster);
    }

    private Effect createEffect(cfg.buff.EffectInfo ei, int endType, float duration) {
        final cfg.buff.Effect effect = CfgMgr.effect.get(ei.effectid);
        return Effect.createEffect(effect, endType, Arrays.asList(duration), skillLevel, caster);
    }

	public void install(Fighter fighter) {
		//assert(target == null);
		this.target = fighter;
		for(cfg.buff.EffectInfo ei : buffCfg.effects) {
            final Effect e = createEffect(ei);
            if(fighter.getBuffMgr().addEffect(e)) {
                effects.add(e);
            }
		}
	}

    public void installHitPoint(Fighter fighter, double negativeBuffHitMultiRate) {
        //assert(target == null);
        this.target = fighter;
        for(cfg.buff.EffectInfo ei : buffCfg.effects) {
            final cfg.buff.Effect effect = CfgMgr.effect.get(ei.effectid);
            if((effect.isharmful && !target.canImmuneDebuff() && common.Utils.random01() <= (effect.candisperse ? effect.hitrate * negativeBuffHitMultiRate : effect.hitrate))
                    || (!effect.isharmful && common.Utils.random01() <= effect.hitrate)) {
                final Effect e = Effect.createEffect(effect, ei.endconditiontype, ei.duration, skillLevel, caster);
                e.setClearWhileDead(true);
                if (fighter.getBuffMgr().addEffect(e)) {
                    effects.add(e);
                }
            }
        }
    }

    public void installForver(Fighter fighter) {
        //assert(target == null);
        this.target = fighter;
        for(cfg.buff.EffectInfo ei : buffCfg.effects) {
            final Effect e = createEffect(ei, EndCondition.BY_REMOVE, 0);
            if(fighter.getBuffMgr().addEffect(e)) {
                effects.add(e);
            }
        }
    }

	public void uninstall() {
		if(this.target != null) {
			effects.forEach(e -> target.getBuffMgr().removeEffect(e));
		}
	}

    public void removeEffectEndByAttack() {
        final BuffMgr buffMgr = target.getBuffMgr();
        effects.stream().filter(e -> e.isEndByAttack()).forEach(buffMgr::removeEffect);
    }
}

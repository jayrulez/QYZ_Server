package map.buff.effect;

import cfg.CfgMgr;
import cfg.fight.Relation;
import map.agent.Fighter;
import map.buff.Buff;
import map.skill.NormalAttackHandler;
import map.skill.Skill;

/**
 * Created by huangqiang on 2016/4/8.
 */
public class Aura extends CfgEffect<cfg.buff.Aura> {
    private final long interval;
    private long nextActionTime;
    private int targetType;

    private Buff selfBuff;

    public Aura(cfg.buff.Aura ecfg, int skillLevel, Fighter caster, int endType, long endTime) {
        super(ecfg, skillLevel, 1, caster, endType, endTime);
        this.interval = (long)(ecfg.judgeinterval * 1000);
        this.nextActionTime = caster.getNow() + interval;
        this.targetType = CfgMgr.buff.get(ecfg.buffid).target;
    }

    @Override
    public void onInstall() {
        if(targetType == Relation.FRIEND) {
            this.selfBuff = Buff.installAuraBuffToSelf(ecfg.buffid, getSkillLevel(), target);
        }
    }

    @Override
    public void onUninstall() {
        if(selfBuff != null) {
            selfBuff.uninstall();
            selfBuff = null;
        }
    }

    @Override
    public boolean onUpdate(long now) {
        if(now >= this.nextActionTime) {
            this.nextActionTime += interval;
            switch (targetType) {
                case Relation.FRIEND: {
                    this.target.getMap().foreachNearbyFighterInAgentMap(this.target, ecfg.judgeradius, agent -> {
                        if (agent != this.target && this.target.isMyTeamMate(agent)) {
                            Buff.installSkillHitPointBuff(ecfg.buffid, getSkillLevel(), caster, agent, NormalAttackHandler.calcDebuffHitMultiRate(caster, agent));
                            //Buff.installAuraBuffToOther(ecfg.buffid, getSkillLevel(), caster, agent);
                        }
                    });
                    break;
                }
                case Relation.ENEMY: {
                    this.target.getMap().foreachNearbyFighterInAgentMap(this.target, ecfg.judgeradius, agent -> {
                        if (agent != this.target && !agent.canImmuneDebuff() && agent.getMap().isEnemy(this.target, agent)) {
                            Buff.installSkillHitPointBuff(ecfg.buffid, getSkillLevel(), caster, agent, 1);
                        }
                    });
                    break;
                }
                default: {
                    throw new RuntimeException("config error!");
                }
            }
        }
        return true;
    }
}

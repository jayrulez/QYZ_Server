package map.buff.effect;

import map.agent.Fighter;
import map.agent.Listener;
import map.buff.factor.Context;
import map.skill.DamageParam;
import map.skill.AttackType;
import xdb.Trace;

/**
 * Created by HuangQiang on 2016/6/1.
 */
public class Reflect extends CfgEffect<cfg.buff.Reflect> {

    private final Listener listener;
    public Reflect(cfg.buff.Reflect ecfg, int skillLevel, Fighter caster, int endType, long endTime) {
        super(ecfg, skillLevel, 1, caster, endType, endTime);

        this.listener = (go, evtid, param) -> {
            final DamageParam dp = (DamageParam)param;
            if(dp.ctx.type == AttackType.SKILL) {
                final int reflectDamage = (int) (dp.damage * common.Utils.getOrLast(ecfg.reflectrate, skillLevel - 1));
                if(reflectDamage > 0) {
                    if(dp.attacker.isActive() && !dp.attacker.isDead()) {
                        //Trace.info("Reflect attack:{} defencer:{} damage:{} reflectdamage:{}", dp.attacker, dp.damage, dp.damage, reflectDamage);
                        dp.attacker.decHPAndCheckDead(new Context(AttackType.REFLECT, dp.defencer, dp.attacker, getSkillLevel()), reflectDamage);
                    }
                }
            }
        };
    }

    @Override
    public void onInstall() {
        target.addListener(Listener.BE_DAMAGE, listener);
    }

    @Override
    public void onUninstall() {
        target.removeListener(Listener.BE_DAMAGE, listener);
    }
}

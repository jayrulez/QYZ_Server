package map.buff.effect;

import cfg.fight.AttrId;
import map.agent.Fighter;
import map.buff.factor.Context;
import map.buff.factor.Factor;
import map.buff.factor.Prioritys;

/**
 * Created by HuangQiang on 2016/6/2.
 */
public class AbnormalDamageUp extends CfgEffect<cfg.buff.AbnormalDamageUp> {
    public AbnormalDamageUp(cfg.buff.AbnormalDamageUp ecfg, int skillLevel, Fighter caster, int endType, long endTime) {
        super(ecfg, skillLevel, 1, caster, endType, endTime);

        this.factor = new Factor(Prioritys.ATTACK + 10) {
            @Override
            public double calc(double curValue, Context ctx) {
                if(ctx.defencer.getBuffMgr().hasHarmfulEffect()) {
                    return curValue + common.Utils.getOrLast(ecfg.damagepercent, getSkillLevel() - 1);
                } else {
                    return curValue;
                }
            }
        };
    }

    private final Factor factor;
    @Override
    public void onInstall() {
        target.getAttrMgr().addFactor(AttrId.CALC_ATTACK_MULTI_RATE, factor);
    }


    @Override
    public void onUninstall() {
        target.getAttrMgr().removeFactor(AttrId.CALC_ATTACK_MULTI_RATE, factor.getId());
    }
}

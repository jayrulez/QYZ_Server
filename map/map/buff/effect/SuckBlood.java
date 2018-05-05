package map.buff.effect;

import map.agent.Fighter;
import map.agent.Listener;
import map.skill.DamageParam;
import map.skill.AttackType;
import xdb.Trace;

/**
 * Created by HuangQiang on 2016/6/1.
 */
public class SuckBlood extends CfgEffect<cfg.buff.SuckBlood> {

    private final Listener listener;
    public SuckBlood(cfg.buff.SuckBlood ecfg, int skillLevel, Fighter caster, int endType, long endTime) {
        super(ecfg, skillLevel, 1, caster, endType, endTime);

        this.listener = (go, evtid, param) -> {
            if(this.target.isActive() && !this.target.isDead()) {
                final DamageParam dp = (DamageParam)param;
                if(dp.ctx.type == AttackType.SKILL || dp.ctx.type == AttackType.SWAPN_OBJECT) {
                    final int addhp = (int) (dp.damage * common.Utils.getOrLast(ecfg.suckrate, skillLevel - 1));
                    if(addhp > 0) {
                        //Trace.info("SuckBlood attack:{} defencer:{} damage:{} addhp:{}", dp.attacker, dp.damage, dp.damage, addhp);
                        this.target.getAttrMgr().addHPValue(addhp);
                    }
                }
            }
        };
    }

    @Override
    public void onInstall() {
        target.addListener(Listener.DO_DAMAGE, listener);
    }

    @Override
    public void onUninstall() {
        target.removeListener(Listener.DO_DAMAGE, listener);
    }
}

package map.buff.effect;

import cfg.Const;
import cfg.buff.DisperseType;
import map.agent.Fighter;

/**
 * Created by HuangQiang on 2016/6/1.
 */
public class Disperse extends CfgEffect<cfg.buff.Disperse> {

    private int remainNum;
    public Disperse(cfg.buff.Disperse ecfg, Fighter caster, int endType, long endTime) {
        super(ecfg, 1, 1, caster, endType, endTime);
        this.remainNum = ecfg.maxnum;
    }

    @Override
    public void onInstall() {
        this.target.getBuffMgr().uninstallAll(e -> {
            if(ecfg.maxnum != Const.NULL && remainNum <= 0) return false;
            final cfg.buff.Effect effcfg = ((CfgEffect<?>)e).getCfg();
            if(!effcfg.candisperse || !e.isEndByTime())
                return false;
            switch (this.ecfg.dispersetype) {
                case DisperseType.ALL : --remainNum; return true;
                case DisperseType.ADVANTAGE_BUFF: {
                    if(!effcfg.isharmful) {
                        --remainNum;
                        return true;
                    } else {
                        return false;
                    }
                }
                case DisperseType.DISADVANTAGE_BUFF:
                    if(effcfg.isharmful) {
                        --remainNum;
                        return true;
                    } else {
                        return false;
                    }
                default: return false;
            }
        });
    }
}

package map.buff.effect;

import map.agent.Fighter;

/**
 * Created by HuangQiang on 2016/6/2.
 */
public class SkillEffect extends CfgEffect<cfg.buff.SkillEffect> {

    public SkillEffect(cfg.buff.SkillEffect ecfg, int skillLevel, Fighter caster, int endType, long endTime) {
        super(ecfg, skillLevel, 1, caster, endType, endTime);
    }

    @Override
    public void onInstall() {
        target.getSkillMgr().addSkillEffect(this);
    }

    @Override
    public void onUninstall() {
        target.getSkillMgr().removeSkillEffect(this);
    }
}

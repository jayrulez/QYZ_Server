package map.buff.effect;

import map.agent.Fighter;

/**
 * Created by HuangQiang on 2016/6/28.
 */
public class AddHPByLevelInterval extends IntervalEffect<cfg.buff.AddHPByLevelInterval> {

    public AddHPByLevelInterval(cfg.buff.AddHPByLevelInterval ecfg, int skillLevel, Fighter caster, int endType, long endTime) {
        super(ecfg, skillLevel, caster, endType, endTime);
    }

    @Override
    public void doIntervalAction(long now) {
        target.getAttrMgr().addHPValue((int)(float)common.Utils.getOrLast(ecfg.value, getSkillLevel() - 1));
    }
}

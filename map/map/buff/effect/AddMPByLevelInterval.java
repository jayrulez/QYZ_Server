package map.buff.effect;

import map.agent.AttrMgr;
import map.agent.Fighter;

/**
 * Created by HuangQiang on 2016/6/28.
 */
public class AddMPByLevelInterval extends IntervalEffect<cfg.buff.AddMPByLevelInterval> {
    public AddMPByLevelInterval(cfg.buff.AddMPByLevelInterval ecfg, int skillLevel, Fighter caster, int endType, long endTime) {
        super(ecfg, skillLevel, caster, endType, endTime);
    }

    @Override
    public void doIntervalAction(long now) {
        final AttrMgr attrMgr = target.getAttrMgr();
        target.getAttrMgr().addMPValue((int)(float)common.Utils.getOrLast(ecfg.value, getSkillLevel() - 1));
    }
}

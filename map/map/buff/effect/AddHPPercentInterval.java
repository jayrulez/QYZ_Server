package map.buff.effect;

import map.agent.AttrMgr;
import map.agent.Fighter;

/**
 * Created by huangqiang on 2016/4/12.
 */
public class AddHPPercentInterval extends IntervalEffect<cfg.buff.AddHPPercentInterval> {

    public AddHPPercentInterval(cfg.buff.AddHPPercentInterval ecfg, Fighter caster, int endType, long endTime) {
        super(ecfg, caster, endType, endTime);
    }

    @Override
    public void doIntervalAction(long now) {
        final AttrMgr attrMgr = target.getAttrMgr();
        attrMgr.addHPValue(attrMgr.getHPFullValue());
    }
}

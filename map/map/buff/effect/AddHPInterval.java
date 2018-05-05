package map.buff.effect;

import map.agent.Fighter;

/**
 * Created by huangqiang on 2016/4/12.
 */
public class AddHPInterval extends IntervalEffect<cfg.buff.AddHPInterval> {
    public AddHPInterval(cfg.buff.AddHPInterval ecfg, Fighter caster, int endType, long endTime) {
        super(ecfg, caster, endType, endTime);
    }

    @Override
    public void doIntervalAction(long now) {
        target.getAttrMgr().addHPValue((int)ecfg.value);
    }
}

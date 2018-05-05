package map.buff.effect;

import map.agent.Fighter;

/**
 * Created by huangqiang on 2016/4/12.
 */
public class AddMPInterval extends IntervalEffect<cfg.buff.AddMPInterval> {
    public AddMPInterval(cfg.buff.AddMPInterval ecfg, Fighter caster, int endType, long endTime) {
        super(ecfg, caster, endType, endTime);
    }

    @Override
    public void doIntervalAction(long now) {
        target.getAttrMgr().addMPValue((int)ecfg.value);
    }
}

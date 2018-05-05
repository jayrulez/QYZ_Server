package map.buff.effect;

import common.Utils;
import map.agent.Fighter;

/**
 * Created by huangqiang on 2016/3/21.
 */
public class AddPropertyByLevel extends CfgEffect<cfg.buff.AddPropertyByLevel> {
    private final float addAttrValue;
    public AddPropertyByLevel(cfg.buff.AddPropertyByLevel ecfg, int skillLevel, Fighter caster, int endType, long endTime) {
        super(ecfg, skillLevel, ecfg.maxoverlay, caster, endType, endTime);
        this.addAttrValue = Utils.getOrLast(ecfg.value, skillLevel - 1);
    }

    @Override
    public void onInstall() {
        target.getAttrMgr().addRawAttr(ecfg.property, addAttrValue * getOverlay());

    }
    @Override
    public void onUninstall() {
        target.getAttrMgr().addRawAttr(ecfg.property, -addAttrValue * getOverlay());
    }
}

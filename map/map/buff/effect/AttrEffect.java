package map.buff.effect;

import cfg.buff.EndCondition;
import map.agent.Fighter;

/**
 * Created by HuangQiang on 2016/7/11.
 */
public class AttrEffect extends Effect {
    private final int attrid;
    private final double value;
    public AttrEffect(int id, Fighter caster, int attrid, double value) {
        super(id, -19871218+id, 1, 1, 1, false, false, caster, EndCondition.BY_REMOVE, 0);
        this.attrid = attrid;
        this.value = value;
    }

    @Override
    protected void onInstall() {
        super.onInstall();
        caster.getAttrMgr().addRawAttr(attrid, value);
    }

    @Override
    protected void onUninstall() {
        super.onUninstall();
        caster.getAttrMgr().addRawAttr(attrid, -value);
    }
}

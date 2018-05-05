package map.buff.effect;

import map.agent.Fighter;

public class CfgEffect<T extends cfg.buff.Effect> extends Effect {
	protected final T ecfg;

    public final T getCfg() {
        return ecfg;
    }

    public CfgEffect(T ecfg, int skillLevel, int maxOverlay, Fighter caster, int endType, long endTime) {
        super(ecfg.id, ecfg.groupid, ecfg.priority, maxOverlay, skillLevel, ecfg.showicon, ecfg.isharmful, caster, endType, endTime);
        this.ecfg = ecfg;
    }

}

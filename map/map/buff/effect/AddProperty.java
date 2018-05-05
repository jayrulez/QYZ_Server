package map.buff.effect;

import map.agent.Fighter;

public final class AddProperty extends CfgEffect<cfg.buff.AddProperty> {
	public AddProperty(cfg.buff.AddProperty ecfg, Fighter caster, int endType, long endTime) {
		super(ecfg, 1, ecfg.maxoverlay, caster, endType, endTime);
	}

	@Override
	public void onInstall() {
		target.getAttrMgr().addRawAttr(ecfg.property, ecfg.value * getOverlay());
	}

	@Override
	public void onUninstall() {
		target.getAttrMgr().addRawAttr(ecfg.property, -ecfg.value * getOverlay());
	}

}

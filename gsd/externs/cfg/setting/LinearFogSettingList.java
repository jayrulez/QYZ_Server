package cfg.setting;
public final class LinearFogSettingList extends cfg.CfgObject {
	public final static int TYPEID = 1616922685;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.setting.LinearFogSetting> linearfogsettinglist = new java.util.ArrayList<>();
	public LinearFogSettingList(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.linearfogsettinglist.add(new cfg.setting.LinearFogSetting(fs));
		}
	}
}
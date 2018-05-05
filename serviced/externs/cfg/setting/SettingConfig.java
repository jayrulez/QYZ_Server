package cfg.setting;
public final class SettingConfig extends cfg.CfgObject {
	public final static int TYPEID = 1446351978;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.setting.GraphicSetting> graphicsettings = new java.util.ArrayList<>();
	public final java.util.Map<Integer, Integer> sceneobjectlayermap = new java.util.HashMap<>();
	public final java.util.List<cfg.setting.FogSetting> defaultfogsettings = new java.util.ArrayList<>();
	public final java.util.Map<String, cfg.setting.LinearFogSettingList> scenefogsettings = new java.util.HashMap<>();
	public final java.util.List<String> ignorefogscenes = new java.util.ArrayList<>();
	public final java.util.List<Integer> androidmemthreshold = new java.util.ArrayList<>();
	public final java.util.List<Integer> iosmemthreshold = new java.util.ArrayList<>();
	public SettingConfig(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.graphicsettings.add(new cfg.setting.GraphicSetting(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.sceneobjectlayermap.put(_k, fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.defaultfogsettings.add(new cfg.setting.FogSetting(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final String _k = fs.getString();
			this.scenefogsettings.put(_k, new cfg.setting.LinearFogSettingList(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.ignorefogscenes.add(fs.getString());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.androidmemthreshold.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.iosmemthreshold.add(fs.getInt());
		}
	}
}
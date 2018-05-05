package cfg.modulehelpinfo;
public final class ModuleHelpInfo extends cfg.CfgObject {
	public final static int TYPEID = 2001762980;
	final public int getTypeId() { return TYPEID; }
	public final String modulename;
	public final java.util.Map<String, cfg.modulehelpinfo.HelpInfo> infos = new java.util.HashMap<>();
	public ModuleHelpInfo(cfg.DataStream fs) {
		this.modulename = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final String _k = fs.getString();
			this.infos.put(_k, new cfg.modulehelpinfo.HelpInfo(fs));
		}
	}
}
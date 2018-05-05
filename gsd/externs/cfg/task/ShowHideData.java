package cfg.task;
public final class ShowHideData extends cfg.CfgObject {
	public final static int TYPEID = 1320369416;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.task.ShowHideGroup> showhide = new java.util.ArrayList<>();
	public final int worldmapid;
	public final String alias;
	public ShowHideData(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.showhide.add(new cfg.task.ShowHideGroup(fs));
		}
		this.worldmapid = fs.getInt();
		this.alias = fs.getString();
	}
}
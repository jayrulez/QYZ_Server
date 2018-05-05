package cfg.task;
public final class ShowHideGroup extends cfg.CfgObject {
	public final static int TYPEID = -2014948127;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> allid = new java.util.ArrayList<>();
	public final boolean showhideaccept;
	public final boolean showhidecomplete;
	public final boolean showhidefinish;
	public final boolean showhidefail;
	public ShowHideGroup(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.allid.add(fs.getInt());
		}
		this.showhideaccept = fs.getBool();
		this.showhidecomplete = fs.getBool();
		this.showhidefinish = fs.getBool();
		this.showhidefail = fs.getBool();
	}
}
package cfg.ectype;
public final class HeroEctypeMsg extends cfg.CfgObject {
	public final static int TYPEID = -2041438909;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String groupname;
	public final cfg.cmd.condition.MinLevel openlevel;
	public final int defaultid;
	public final String icon;
	public final java.util.List<cfg.ectype.EctypeRandom> ectyperandom = new java.util.ArrayList<>();
	public final java.util.List<Integer> petid = new java.util.ArrayList<>();
	public HeroEctypeMsg(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.groupname = fs.getString();
		this.openlevel = new cfg.cmd.condition.MinLevel(fs);
		this.defaultid = fs.getInt();
		this.icon = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.ectyperandom.add(new cfg.ectype.EctypeRandom(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.petid.add(fs.getInt());
		}
	}
}
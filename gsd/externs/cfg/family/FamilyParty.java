package cfg.family;
public final class FamilyParty extends cfg.CfgObject {
	public final static int TYPEID = -2005803006;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> starttime = new java.util.ArrayList<>();
	public final java.util.List<Integer> endtime = new java.util.ArrayList<>();
	public final java.util.List<Integer> starttime2 = new java.util.ArrayList<>();
	public final java.util.List<Integer> endtime2 = new java.util.ArrayList<>();
	public final int duration;
	public final int smallexpitem;
	public final int bigexpitem;
	public final int atkbuffid;
	public final int spdbuffid;
	public final int hpbuffid;
	public final int defbuffid;
	public final cfg.cmd.action.RandomBonus bonusbox;
	public final int maxbonustimes;
	public final int familyectypeid;
	public final int upperbound;
	public final cfg.cmd.action.MultiBonus showitem;
	public final cfg.map.Vector3 effectposition;
	public final cfg.map.Vector3 effectzoomin;
	public FamilyParty(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.starttime.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.endtime.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.starttime2.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.endtime2.add(fs.getInt());
		}
		this.duration = fs.getInt();
		this.smallexpitem = fs.getInt();
		this.bigexpitem = fs.getInt();
		this.atkbuffid = fs.getInt();
		this.spdbuffid = fs.getInt();
		this.hpbuffid = fs.getInt();
		this.defbuffid = fs.getInt();
		this.bonusbox = new cfg.cmd.action.RandomBonus(fs);
		this.maxbonustimes = fs.getInt();
		this.familyectypeid = fs.getInt();
		this.upperbound = fs.getInt();
		this.showitem = new cfg.cmd.action.MultiBonus(fs);
		this.effectposition = new cfg.map.Vector3(fs);
		this.effectzoomin = new cfg.map.Vector3(fs);
	}
}
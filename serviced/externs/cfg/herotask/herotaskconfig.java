package cfg.herotask;
public final class herotaskconfig extends cfg.CfgObject {
	public final static int TYPEID = 602886726;
	final public int getTypeId() { return TYPEID; }
	public final int openlevel;
	public final int ectypeamount;
	public final cfg.cmd.condition.DayLimit maxbonustimes;
	public final cfg.cmd.condition.DayLimit maxcaptianbonustimes;
	public final int npcid;
	public final int mintmemberamount;
	public final String unitylevel;
	public final int mapid;
	public final String npctalk;
	public final int beginreg;
	public final java.util.List<Float> bonusrate = new java.util.ArrayList<>();
	public final float bonusrate2;
	public final java.util.List<Integer> displaybonus = new java.util.ArrayList<>();
	public final java.util.List<cfg.herotask.herotaskaward> basicaward = new java.util.ArrayList<>();
	public final java.util.List<cfg.herotask.captianherotaskaward> captianaward = new java.util.ArrayList<>();
	public final java.util.List<String> descriptions = new java.util.ArrayList<>();
	public herotaskconfig(cfg.DataStream fs) {
		this.openlevel = fs.getInt();
		this.ectypeamount = fs.getInt();
		this.maxbonustimes = new cfg.cmd.condition.DayLimit(fs);
		this.maxcaptianbonustimes = new cfg.cmd.condition.DayLimit(fs);
		this.npcid = fs.getInt();
		this.mintmemberamount = fs.getInt();
		this.unitylevel = fs.getString();
		this.mapid = fs.getInt();
		this.npctalk = fs.getString();
		this.beginreg = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bonusrate.add(fs.getFloat());
		}
		this.bonusrate2 = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.displaybonus.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.basicaward.add(new cfg.herotask.herotaskaward(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.captianaward.add(new cfg.herotask.captianherotaskaward(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.descriptions.add(fs.getString());
		}
	}
}
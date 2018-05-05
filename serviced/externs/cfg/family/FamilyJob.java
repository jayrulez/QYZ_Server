package cfg.family;
public final class FamilyJob extends cfg.CfgObject {
	public final static int TYPEID = -1329465383;
	final public int getTypeId() { return TYPEID; }
	public final int jobid;
	public final int name;
	public final String displayname;
	public final boolean enrollperm;
	public final boolean kickoutperm;
	public final boolean familyskillperm;
	public final boolean caneditdeclaration;
	public final boolean caneditannouncement;
	public final java.util.List<Integer> amount = new java.util.ArrayList<>();
	public final java.util.Set<Integer> appointjobs = new java.util.HashSet<>();
	public FamilyJob(cfg.DataStream fs) {
		this.jobid = fs.getInt();
		this.name = fs.getInt();
		this.displayname = fs.getString();
		this.enrollperm = fs.getBool();
		this.kickoutperm = fs.getBool();
		this.familyskillperm = fs.getBool();
		this.caneditdeclaration = fs.getBool();
		this.caneditannouncement = fs.getBool();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.amount.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.appointjobs.add(fs.getInt());
		}
	}
}
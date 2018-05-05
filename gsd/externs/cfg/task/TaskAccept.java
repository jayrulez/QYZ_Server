package cfg.task;
public final class TaskAccept extends cfg.CfgObject {
	public final static int TYPEID = -768506484;
	final public int getTypeId() { return TYPEID; }
	public final int mode;
	public final int npcid;
	public final java.util.List<Integer> pretaskid = new java.util.ArrayList<>();
	public final java.util.List<Integer> mutextaskid = new java.util.ArrayList<>();
	public final boolean finishanyonepretask;
	public final java.util.List<Integer> titleid = new java.util.ArrayList<>();
	public final java.util.List<cfg.task.ItemInfo> acceptrewarditem = new java.util.ArrayList<>();
	public final int week;
	public final int rolelevelmin;
	public final int rolelevelmax;
	public final int factionlevelmin;
	public final int factionlevelmax;
	public final int prestigetype;
	public final int prestige;
	public final java.util.List<Integer> professionid = new java.util.ArrayList<>();
	public TaskAccept(cfg.DataStream fs) {
		this.mode = fs.getInt();
		this.npcid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.pretaskid.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.mutextaskid.add(fs.getInt());
		}
		this.finishanyonepretask = fs.getBool();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.titleid.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.acceptrewarditem.add(new cfg.task.ItemInfo(fs));
		}
		this.week = fs.getInt();
		this.rolelevelmin = fs.getInt();
		this.rolelevelmax = fs.getInt();
		this.factionlevelmin = fs.getInt();
		this.factionlevelmax = fs.getInt();
		this.prestigetype = fs.getInt();
		this.prestige = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.professionid.add(fs.getInt());
		}
	}
}
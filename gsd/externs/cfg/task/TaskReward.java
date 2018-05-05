package cfg.task;
public final class TaskReward extends cfg.CfgObject {
	public final static int TYPEID = -279371853;
	final public int getTypeId() { return TYPEID; }
	public final long money;
	public final int ingot;
	public final long exp;
	public final cfg.task.RewardItem rewarditem;
	public final int titleid;
	public final boolean deletetitle;
	public final int taskid;
	public final cfg.task.LocationPointData transfertolocation;
	public final boolean refreshtasklibrary;
	public final int prestigetype;
	public final int prestige;
	public final int broadcasttype;
	public final String broadcastcontent;
	public final java.util.List<cfg.task.MonsterController> monstercontroller = new java.util.ArrayList<>();
	public final int factionexp;
	public final java.util.List<cfg.task.SkillInfo> rewardskill = new java.util.ArrayList<>();
	public final java.util.List<cfg.task.ItemInfo> professionrewarditem = new java.util.ArrayList<>();
	public TaskReward(cfg.DataStream fs) {
		this.money = fs.getLong();
		this.ingot = fs.getInt();
		this.exp = fs.getLong();
		this.rewarditem = new cfg.task.RewardItem(fs);
		this.titleid = fs.getInt();
		this.deletetitle = fs.getBool();
		this.taskid = fs.getInt();
		this.transfertolocation = new cfg.task.LocationPointData(fs);
		this.refreshtasklibrary = fs.getBool();
		this.prestigetype = fs.getInt();
		this.prestige = fs.getInt();
		this.broadcasttype = fs.getInt();
		this.broadcastcontent = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monstercontroller.add(new cfg.task.MonsterController(fs));
		}
		this.factionexp = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.rewardskill.add(new cfg.task.SkillInfo(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.professionrewarditem.add(new cfg.task.ItemInfo(fs));
		}
	}
}
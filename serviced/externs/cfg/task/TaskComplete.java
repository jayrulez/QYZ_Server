package cfg.task;
public final class TaskComplete extends cfg.CfgObject {
	public final static int TYPEID = 1746727101;
	final public int getTypeId() { return TYPEID; }
	public final int mode;
	public final int npcid;
	public final int rolelevel;
	public final int rolemoney;
	public final int prestigetype;
	public final int prestige;
	public final java.util.List<cfg.task.ItemInfo> collectitem = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.task.ItemInfo> collectitem_itemid= new java.util.HashMap<>();
	public final boolean needkillmonster;
	public final java.util.List<cfg.task.KillMonsterData> killmonster = new java.util.ArrayList<>();
	public final boolean randomkillmonster;
	public final cfg.task.LocationRangeData location;
	public final cfg.task.LocationRangeData npclocation;
	public final java.util.List<cfg.task.FinishSpecialEventData> finishspecialevent = new java.util.ArrayList<>();
	public final boolean finishall;
	public TaskComplete(cfg.DataStream fs) {
		this.mode = fs.getInt();
		this.npcid = fs.getInt();
		this.rolelevel = fs.getInt();
		this.rolemoney = fs.getInt();
		this.prestigetype = fs.getInt();
		this.prestige = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.task.ItemInfo _v = new cfg.task.ItemInfo(fs);
			this.collectitem.add(_v);
			this.collectitem_itemid.put(_v.itemid, _v);
		}
		this.needkillmonster = fs.getBool();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.killmonster.add(new cfg.task.KillMonsterData(fs));
		}
		this.randomkillmonster = fs.getBool();
		this.location = new cfg.task.LocationRangeData(fs);
		this.npclocation = new cfg.task.LocationRangeData(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.finishspecialevent.add(new cfg.task.FinishSpecialEventData(fs));
		}
		this.finishall = fs.getBool();
	}
}
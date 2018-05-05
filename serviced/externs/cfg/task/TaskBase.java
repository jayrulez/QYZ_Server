package cfg.task;
public final class TaskBase extends cfg.CfgObject {
	public final static int TYPEID = 1505372245;
	final public int getTypeId() { return TYPEID; }
	public final String name;
	public final int tasktype;
	public final int contenttype;
	public final boolean canbecancle;
	public final boolean canbesearch;
	public final boolean hidetask;
	public final boolean autobetrigger;
	public final boolean close;
	public final boolean autopathfind;
	public final boolean finishedtaskcount;
	public final boolean autofinish;
	public final int counttype;
	public final int countlimit;
	public final int resetcounttimerid;
	public final int navmode;
	public final java.util.List<cfg.task.ItemInfo> givenitem = new java.util.ArrayList<>();
	public final cfg.task.CallNpcInfo callnpc;
	public final java.util.List<cfg.task.MonsterController> monstercontroller = new java.util.ArrayList<>();
	public final cfg.task.ShowHideData npcshowhide;
	public final cfg.task.ShowHideData mineshowhide;
	public final cfg.task.ShowHideData gfxshowhide;
	public final java.util.List<cfg.task.DialogueInfo> npcdialogue = new java.util.ArrayList<>();
	public final java.util.List<cfg.task.DialogueInfo> undone = new java.util.ArrayList<>();
	public final java.util.List<cfg.task.DialogueInfo> success = new java.util.ArrayList<>();
	public final cfg.task.HintInfo hints;
	public final java.util.List<cfg.task.DialogueInfo> shoutaccepted = new java.util.ArrayList<>();
	public final java.util.List<cfg.task.DialogueInfo> shoutuncommitted = new java.util.ArrayList<>();
	public final java.util.List<cfg.task.DialogueInfo> shoutcompleted = new java.util.ArrayList<>();
	public final String ringtasktalk;
	public TaskBase(cfg.DataStream fs) {
		this.name = fs.getString();
		this.tasktype = fs.getInt();
		this.contenttype = fs.getInt();
		this.canbecancle = fs.getBool();
		this.canbesearch = fs.getBool();
		this.hidetask = fs.getBool();
		this.autobetrigger = fs.getBool();
		this.close = fs.getBool();
		this.autopathfind = fs.getBool();
		this.finishedtaskcount = fs.getBool();
		this.autofinish = fs.getBool();
		this.counttype = fs.getInt();
		this.countlimit = fs.getInt();
		this.resetcounttimerid = fs.getInt();
		this.navmode = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.givenitem.add(new cfg.task.ItemInfo(fs));
		}
		this.callnpc = new cfg.task.CallNpcInfo(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monstercontroller.add(new cfg.task.MonsterController(fs));
		}
		this.npcshowhide = new cfg.task.ShowHideData(fs);
		this.mineshowhide = new cfg.task.ShowHideData(fs);
		this.gfxshowhide = new cfg.task.ShowHideData(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.npcdialogue.add(new cfg.task.DialogueInfo(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.undone.add(new cfg.task.DialogueInfo(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.success.add(new cfg.task.DialogueInfo(fs));
		}
		this.hints = new cfg.task.HintInfo(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.shoutaccepted.add(new cfg.task.DialogueInfo(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.shoutuncommitted.add(new cfg.task.DialogueInfo(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.shoutcompleted.add(new cfg.task.DialogueInfo(fs));
		}
		this.ringtasktalk = fs.getString();
	}
}
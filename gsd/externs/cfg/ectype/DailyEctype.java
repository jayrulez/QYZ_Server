package cfg.ectype;
public final class DailyEctype extends cfg.CfgObject {
	public final static int TYPEID = 670299621;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.cmd.condition.TiLi costtili;
	public final int ectypetype;
	public final String storyname;
	public final String introduction;
	public final String decs;
	public final String backgroundpic;
	public final java.util.List<Integer> showbonusid = new java.util.ArrayList<>();
	public final int mainregionid;
	public final cfg.cmd.condition.MinLevel openlevel;
	public final String showlv;
	public final int battlepower;
	public final int doublebonustime;
	public final cfg.cmd.action.RandomBonus ectyperandomdrop;
	public final int monsterreftime;
	public final java.util.List<cfg.ectype.MonsterInfo> monsters = new java.util.ArrayList<>();
	public final cfg.ectype.SweepInfo sweepinfo;
	public DailyEctype(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.costtili = new cfg.cmd.condition.TiLi(fs);
		this.ectypetype = fs.getInt();
		this.storyname = fs.getString();
		this.introduction = fs.getString();
		this.decs = fs.getString();
		this.backgroundpic = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.showbonusid.add(fs.getInt());
		}
		this.mainregionid = fs.getInt();
		this.openlevel = new cfg.cmd.condition.MinLevel(fs);
		this.showlv = fs.getString();
		this.battlepower = fs.getInt();
		this.doublebonustime = fs.getInt();
		this.ectyperandomdrop = new cfg.cmd.action.RandomBonus(fs);
		this.monsterreftime = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsters.add(new cfg.ectype.MonsterInfo(fs));
		}
		this.sweepinfo = new cfg.ectype.SweepInfo(fs);
	}
}
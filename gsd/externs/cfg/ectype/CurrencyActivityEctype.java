package cfg.ectype;
public final class CurrencyActivityEctype extends cfg.CfgObject {
	public final static int TYPEID = 214349092;
	final public int getTypeId() { return TYPEID; }
	public static final int OPEN_CG = 10009004;
	public final int id;
	public final cfg.cmd.condition.TiLi costtili;
	public final cfg.cmd.condition.DayLimit dailytime;
	public final int ectypetype;
	public final String storyname;
	public final String introduction;
	public final String decs;
	public final String backgroundpic;
	public final java.util.List<Integer> showbonusid = new java.util.ArrayList<>();
	public final int mainregionid;
	public final int minlevel;
	public final int maxlevel;
	public final int battlepower;
	public final cfg.cmd.action.RandomBonus ectyperandomdrop;
	public final int monsterreftime;
	public final String refmsg;
	public final float hurttocurrency;
	public final int monster;
	public final cfg.map.Vector2 position;
	public final float orientangle;
	public final int maxgetcurrency;
	public final int spellbonustime;
	public final java.util.List<cfg.map.Vector2> positions = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.RuneInfo> runeinfo = new java.util.ArrayList<>();
	public final cfg.ectype.SweepInfo sweepinfo;
	public CurrencyActivityEctype(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.costtili = new cfg.cmd.condition.TiLi(fs);
		this.dailytime = new cfg.cmd.condition.DayLimit(fs);
		this.ectypetype = fs.getInt();
		this.storyname = fs.getString();
		this.introduction = fs.getString();
		this.decs = fs.getString();
		this.backgroundpic = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.showbonusid.add(fs.getInt());
		}
		this.mainregionid = fs.getInt();
		this.minlevel = fs.getInt();
		this.maxlevel = fs.getInt();
		this.battlepower = fs.getInt();
		this.ectyperandomdrop = new cfg.cmd.action.RandomBonus(fs);
		this.monsterreftime = fs.getInt();
		this.refmsg = fs.getString();
		this.hurttocurrency = fs.getFloat();
		this.monster = fs.getInt();
		this.position = new cfg.map.Vector2(fs);
		this.orientangle = fs.getFloat();
		this.maxgetcurrency = fs.getInt();
		this.spellbonustime = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.positions.add(new cfg.map.Vector2(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.runeinfo.add(new cfg.ectype.RuneInfo(fs));
		}
		this.sweepinfo = new cfg.ectype.SweepInfo(fs);
	}
}
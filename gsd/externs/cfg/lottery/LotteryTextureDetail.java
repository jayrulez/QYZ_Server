package cfg.lottery;
public final class LotteryTextureDetail extends cfg.CfgObject {
	public final static int TYPEID = -1440138382;
	final public int getTypeId() { return TYPEID; }
	public final boolean showdesc;
	public final String desc;
	public final int freetype;
	public final int type;
	public final boolean iscooldown;
	public final boolean isdaylimit;
	public final boolean canuseitem;
	public LotteryTextureDetail(cfg.DataStream fs) {
		this.showdesc = fs.getBool();
		this.desc = fs.getString();
		this.freetype = fs.getInt();
		this.type = fs.getInt();
		this.iscooldown = fs.getBool();
		this.isdaylimit = fs.getBool();
		this.canuseitem = fs.getBool();
	}
}
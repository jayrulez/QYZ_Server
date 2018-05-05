package cfg.ectype;
public final class EctypeBasic extends cfg.CfgObject {
	public final static int TYPEID = -1552624694;
	final public int getTypeId() { return TYPEID; }
	public static final int successaudioid = 224;
	public static final int failedaudioid = 225;
	public final int id;
	public final int type;
	public final String scenename;
	public final String ectypename;
	public final int regionsetid;
	public final int landscapeid;
	public final cfg.ectype.ReviveInfo reviveinfo;
	public final int totaltime;
	public final boolean canpk;
	public final boolean canfly;
	public final cfg.map.Vector2 startposition;
	public final float startrotation;
	public final int endofftime;
	public final int audioid;
	public final int sightradius;
	public final boolean enterfight;
	public final boolean rebornfight;
	public EctypeBasic(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.type = fs.getInt();
		this.scenename = fs.getString();
		this.ectypename = fs.getString();
		this.regionsetid = fs.getInt();
		this.landscapeid = fs.getInt();
		this.reviveinfo = new cfg.ectype.ReviveInfo(fs);
		this.totaltime = fs.getInt();
		this.canpk = fs.getBool();
		this.canfly = fs.getBool();
		this.startposition = new cfg.map.Vector2(fs);
		this.startrotation = fs.getFloat();
		this.endofftime = fs.getInt();
		this.audioid = fs.getInt();
		this.sightradius = fs.getInt();
		this.enterfight = fs.getBool();
		this.rebornfight = fs.getBool();
	}
}
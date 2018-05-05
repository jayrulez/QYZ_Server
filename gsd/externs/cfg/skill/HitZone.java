package cfg.skill;
public final class HitZone extends cfg.CfgObject {
	public final static int TYPEID = 497415960;
	final public int getTypeId() { return TYPEID; }
	public static final int CYLINDER = 1;
	public static final int RECT = 0;
	public static final int TRIANGLE = 2;
	public final int id;
	public final int shape;
	public final float zoffset;
	public final float xlength;
	public final float bottomheight;
	public final float topheight;
	public final float zlength;
	public final float yangle;
	public final float yrotationangle;
	public final int maxtarget;
	public HitZone(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.shape = fs.getInt();
		this.zoffset = fs.getFloat();
		this.xlength = fs.getFloat();
		this.bottomheight = fs.getFloat();
		this.topheight = fs.getFloat();
		this.zlength = fs.getFloat();
		this.yangle = fs.getFloat();
		this.yrotationangle = fs.getFloat();
		this.maxtarget = fs.getInt();
	}
}
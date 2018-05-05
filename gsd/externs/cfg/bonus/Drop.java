package cfg.bonus;
public final class Drop extends cfg.CfgObject {
	public final static int TYPEID = -1623904440;
	final public int getTypeId() { return TYPEID; }
	public static final int ATTENUATION_INTERVAL = 15;
	public static final int[] EXP_ATTENUATION = {100,75,50,25,0};
	public static final int[] DROP_ATTENUATION = {100,80,60,40,40};
	public static final float EXP_RADIUS = 20f;
	public static final int SHOW_TIME = 2;
	public static final int AUTO_BATTLE_SHOW_TIME = 5;
	public static final int PROTECT_TIME = 30;
	public static final int DISAPPEAR_TIME = 180;
	public static final int PICK_RADIUS = 15;
	public static final int CURVE_TYPE = 0;
	public static final int DROP_ITEM_RADIUS = 5;
	public final int id;
	public final String dropname;
	public final cfg.cmd.action.Bonus droplist;
	public Drop(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.dropname = fs.getString();
		this.droplist = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}
package cfg.role;
public final class Const extends cfg.CfgObject {
	public final static int TYPEID = 1361263509;
	final public int getTypeId() { return TYPEID; }
	public static final int MAX_LEVEL = 150;
	public static final float PLAYER_SELECT_TARGET_RADIUS = 15f;
	public static final float PLAYER_LOSE_TARGET_RADIUS = 20f;
	public static final int SMART_ATTACK = 1;
	public static final int LOCAL_NAVMESH = 1;
	public Const(cfg.DataStream fs) {
	}
}
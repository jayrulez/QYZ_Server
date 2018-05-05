package cfg.mail;
public final class Consts extends cfg.CfgObject {
	public final static int TYPEID = -1457293539;
	final public int getTypeId() { return TYPEID; }
	public static final int MAX_MAIL_NUM = 50;
	public static final int ACCESSORY_MAIL_EXPIRE_TIME = 2592000;
	public static final int NO_ACCESSORY_MAIL_EXPIRE_TIME = 604800;
	public static final int GM_MAIL_ID = 1;
	public static final int CUSTOM_MAIL_ID = 2;
	public Consts(cfg.DataStream fs) {
	}
}
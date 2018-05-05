package cfg.chat;
public final class Const extends cfg.CfgObject {
	public final static int TYPEID = -1782113033;
	final public int getTypeId() { return TYPEID; }
	public static final int MAX_TEXT_SIZE = 40;
	public static final int MAX_VOICE_SIZE = 100000;
	public static final int MAX_IMAGE_SIZE = 300000;
	public static final int FACE_PRICE = 68;
	public static final int TOP_CHANNEL_PRICE = 100;
	public Const(cfg.DataStream fs) {
	}
}
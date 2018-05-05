package cfg.mall;
public final class MallNPC extends cfg.CfgObject {
	public final static int TYPEID = 1361201021;
	final public int getTypeId() { return TYPEID; }
	public static final int DISPLAYTIME = 5;
	public static final int BLACKMARKETEER = 23000385;
	public static final int FEASTMANAGER = 23000384;
	public static final int CARRAYSHOP = 23000454;
	public static final int WAREHOUSE = 23000455;
	public final int malltype;
	public final int cornucopianpc;
	public final float offset;
	public MallNPC(cfg.DataStream fs) {
		this.malltype = fs.getInt();
		this.cornucopianpc = fs.getInt();
		this.offset = fs.getFloat();
	}
}
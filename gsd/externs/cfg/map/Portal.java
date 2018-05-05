package cfg.map;
public final class Portal extends cfg.CfgObject {
	public final static int TYPEID = -294245080;
	final public int getTypeId() { return TYPEID; }
	public static final String portalindex1 = "chuansongmen_01";
	public static final String portalindex2 = "chuansongmen_02";
	public final int srcregionid;
	public final String srcregionname;
	public final int dstworldmapid;
	public final cfg.map.Vector2 dstregion;
	public final cfg.map.Vector3 effectpos;
	public final float rotation;
	public final int effecttype;
	public final boolean displaytext;
	public final int transmode;
	public final int pathid;
	public Portal(cfg.DataStream fs) {
		this.srcregionid = fs.getInt();
		this.srcregionname = fs.getString();
		this.dstworldmapid = fs.getInt();
		this.dstregion = new cfg.map.Vector2(fs);
		this.effectpos = new cfg.map.Vector3(fs);
		this.rotation = fs.getFloat();
		this.effecttype = fs.getInt();
		this.displaytext = fs.getBool();
		this.transmode = fs.getInt();
		this.pathid = fs.getInt();
	}
}
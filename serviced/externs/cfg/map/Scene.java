package cfg.map;
public final class Scene extends cfg.CfgObject {
	public final static int TYPEID = 270003280;
	final public int getTypeId() { return TYPEID; }
	public static final float HEIGHTMAP_MAX = 20000f;
	public static final float HEIGHTMAP_MIN = -20000f;
	public static final float DIFFWITHGROUNDANDSKY = 1.5f;
	public static final float LOADSCENEDELAYTIME = 1.0f;
	public static final int RELOADTIME = 5;
	public static final float RELOADDELAY = 0.1f;
	public final String name;
	public final String groundheightmap;
	public final String navmeshname;
	public final String skyheightmap;
	public final int skyregionset;
	public final float skyheightceil;
	public final int backgroundmusicid;
	public final float scenesize;
	public final float thunbnailsize;
	public final int pivot;
	public final float offsetX;
	public final float offsetZ;
	public Scene(cfg.DataStream fs) {
		this.name = fs.getString();
		this.groundheightmap = fs.getString();
		this.navmeshname = fs.getString();
		this.skyheightmap = fs.getString();
		this.skyregionset = fs.getInt();
		this.skyheightceil = fs.getFloat();
		this.backgroundmusicid = fs.getInt();
		this.scenesize = fs.getFloat();
		this.thunbnailsize = fs.getFloat();
		this.pivot = fs.getInt();
		this.offsetX = fs.getFloat();
		this.offsetZ = fs.getFloat();
	}
}
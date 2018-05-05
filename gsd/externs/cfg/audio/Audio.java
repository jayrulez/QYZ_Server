package cfg.audio;
public final class Audio extends cfg.CfgObject {
	public final static int TYPEID = -280478444;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int type;
	public final int priority;
	public final String cliplist;
	public final int minidletime;
	public final int maxidletime;
	public final float minvolume;
	public final float maxvolume;
	public final float probability;
	public final boolean isloop;
	public Audio(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.type = fs.getInt();
		this.priority = fs.getInt();
		this.cliplist = fs.getString();
		this.minidletime = fs.getInt();
		this.maxidletime = fs.getInt();
		this.minvolume = fs.getFloat();
		this.maxvolume = fs.getFloat();
		this.probability = fs.getFloat();
		this.isloop = fs.getBool();
	}
}
package cfg.pet;
public final class FollowInfo extends cfg.CfgObject {
	public final static int TYPEID = 897697560;
	final public int getTypeId() { return TYPEID; }
	public final float degree;
	public final float distance;
	public FollowInfo(cfg.DataStream fs) {
		this.degree = fs.getFloat();
		this.distance = fs.getFloat();
	}
}
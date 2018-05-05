package cfg.pet;
public final class Maturerate extends cfg.CfgObject {
	public final static int TYPEID = -417685503;
	final public int getTypeId() { return TYPEID; }
	public final float hpmaturerate;
	public final float mpmaturerate;
	public final float minatkmaturerate;
	public final float maxatkmaturerate;
	public final float defmaturerate;
	public final float hitmaturerate;
	public final float hitresistmaturerate;
	public Maturerate(cfg.DataStream fs) {
		this.hpmaturerate = fs.getFloat();
		this.mpmaturerate = fs.getFloat();
		this.minatkmaturerate = fs.getFloat();
		this.maxatkmaturerate = fs.getFloat();
		this.defmaturerate = fs.getFloat();
		this.hitmaturerate = fs.getFloat();
		this.hitresistmaturerate = fs.getFloat();
	}
}
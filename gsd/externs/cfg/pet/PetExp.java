package cfg.pet;
public final class PetExp extends cfg.CfgObject {
	public final static int TYPEID = -1151582185;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final long exp;
	public final float levelmaturerate;
	public final float enhancerate;
	public PetExp(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.exp = fs.getLong();
		this.levelmaturerate = fs.getFloat();
		this.enhancerate = fs.getFloat();
	}
}
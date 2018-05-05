package cfg.fight;
public final class AttrIdType extends cfg.CfgObject {
	public final static int TYPEID = 827144526;
	final public int getTypeId() { return TYPEID; }
	public final int type;
	public AttrIdType(cfg.DataStream fs) {
		this.type = fs.getInt();
	}
}
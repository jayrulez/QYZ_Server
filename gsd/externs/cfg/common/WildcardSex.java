package cfg.common;
public final class WildcardSex extends cfg.CfgObject {
	public final static int TYPEID = 1477498451;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String male;
	public final String female;
	public WildcardSex(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.male = fs.getString();
		this.female = fs.getString();
	}
}
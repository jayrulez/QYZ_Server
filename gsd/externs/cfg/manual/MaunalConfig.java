package cfg.manual;
public final class MaunalConfig extends cfg.CfgObject {
	public final static int TYPEID = 905301560;
	final public int getTypeId() { return TYPEID; }
	public final int petcostitemid;
	public MaunalConfig(cfg.DataStream fs) {
		this.petcostitemid = fs.getInt();
	}
}
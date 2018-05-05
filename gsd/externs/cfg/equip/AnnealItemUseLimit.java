package cfg.equip;
public final class AnnealItemUseLimit extends cfg.CfgObject {
	public final static int TYPEID = -672153858;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final java.util.List<Boolean> uselimit = new java.util.ArrayList<>();
	public AnnealItemUseLimit(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.uselimit.add(fs.getBool());
		}
	}
}
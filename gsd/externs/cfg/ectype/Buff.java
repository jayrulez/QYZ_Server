package cfg.ectype;
public final class Buff extends cfg.CfgObject {
	public final static int TYPEID = 1772677439;
	final public int getTypeId() { return TYPEID; }
	public final int buffid;
	public final String icon;
	public final int target;
	public final java.util.List<Integer> price = new java.util.ArrayList<>();
	public Buff(cfg.DataStream fs) {
		this.buffid = fs.getInt();
		this.icon = fs.getString();
		this.target = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.price.add(fs.getInt());
		}
	}
}
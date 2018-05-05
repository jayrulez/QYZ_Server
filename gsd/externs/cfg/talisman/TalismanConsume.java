package cfg.talisman;
public final class TalismanConsume extends cfg.CfgObject {
	public final static int TYPEID = 627907832;
	final public int getTypeId() { return TYPEID; }
	public final String consumetype;
	public final java.util.List<Integer> itemid = new java.util.ArrayList<>();
	public TalismanConsume(cfg.DataStream fs) {
		this.consumetype = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.itemid.add(fs.getInt());
		}
	}
}
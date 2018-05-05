package cfg.talisman;
public final class TalismanSkill extends cfg.CfgObject {
	public final static int TYPEID = 185137741;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final java.util.List<Integer> skillid = new java.util.ArrayList<>();
	public TalismanSkill(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.skillid.add(fs.getInt());
		}
	}
}
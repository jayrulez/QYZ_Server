package cfg.talisman;
public final class AwakeInfo extends cfg.CfgObject {
	public final static int TYPEID = 1562827648;
	final public int getTypeId() { return TYPEID; }
	public final int talismancost;
	public final String displaytext;
	public final java.util.List<cfg.talisman.AwakeEffect> effect = new java.util.ArrayList<>();
	public AwakeInfo(cfg.DataStream fs) {
		this.talismancost = fs.getInt();
		this.displaytext = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.effect.add((cfg.talisman.AwakeEffect)fs.getObject(fs.getString()));
		}
	}
}
package cfg.equip;
public final class AnnealItemEffect extends cfg.CfgObject {
	public final static int TYPEID = 230957211;
	final public int getTypeId() { return TYPEID; }
	public static final int RATE_BASE_NUMBER = 10000;
	public final int id;
	public final String name;
	public final int lostcontrol;
	public final java.util.List<Integer> effect = new java.util.ArrayList<>();
	public AnnealItemEffect(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.lostcontrol = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.effect.add(fs.getInt());
		}
	}
}
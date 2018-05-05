package cfg.buff;
public final class Buff extends cfg.CfgObject {
	public final static int TYPEID = 456626212;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String buffname;
	public final String name;
	public final int target;
	public final java.util.List<cfg.buff.EffectInfo> effects = new java.util.ArrayList<>();
	public Buff(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.buffname = fs.getString();
		this.name = fs.getString();
		this.target = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.effects.add(new cfg.buff.EffectInfo(fs));
		}
	}
}
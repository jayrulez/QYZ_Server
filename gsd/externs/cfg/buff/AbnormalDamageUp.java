package cfg.buff;
public final class AbnormalDamageUp extends cfg.buff.Effect {
	public final static int TYPEID = 1966968099;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Float> damagepercent = new java.util.ArrayList<>();
	public AbnormalDamageUp(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.damagepercent.add(fs.getFloat());
		}
	}
}
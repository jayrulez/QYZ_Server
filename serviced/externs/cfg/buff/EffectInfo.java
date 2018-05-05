package cfg.buff;
public final class EffectInfo extends cfg.CfgObject {
	public final static int TYPEID = 789889264;
	final public int getTypeId() { return TYPEID; }
	public final int effectid;
	public final String description;
	public final int endconditiontype;
	public final java.util.List<Float> duration = new java.util.ArrayList<>();
	public EffectInfo(cfg.DataStream fs) {
		this.effectid = fs.getInt();
		this.description = fs.getString();
		this.endconditiontype = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.duration.add(fs.getFloat());
		}
	}
}
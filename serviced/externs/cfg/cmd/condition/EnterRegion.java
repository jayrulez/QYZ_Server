package cfg.cmd.condition;
public final class EnterRegion extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -1283288901;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Float> coordinate = new java.util.ArrayList<>();
	public final float distance;
	public EnterRegion(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.coordinate.add(fs.getFloat());
		}
		this.distance = fs.getFloat();
	}
}
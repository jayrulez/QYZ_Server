package cfg.map;
public final class MultiPoints extends cfg.map.DeploymentLocation {
	public final static int TYPEID = -1194885056;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.map.FixedPoint> positions = new java.util.ArrayList<>();
	public MultiPoints(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.positions.add(new cfg.map.FixedPoint(fs));
		}
	}
}
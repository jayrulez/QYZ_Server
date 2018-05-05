package cfg.ectype;
public final class CurveArea extends cfg.ectype.Area {
	public final static int TYPEID = -1811066704;
	final public int getTypeId() { return TYPEID; }
	public final int curveid;
	public CurveArea(cfg.DataStream fs) {
		super(fs);
		this.curveid = fs.getInt();
	}
}
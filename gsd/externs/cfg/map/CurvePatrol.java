package cfg.map;
public final class CurvePatrol extends cfg.map.PatrolInfo {
	public final static int TYPEID = 1557942495;
	final public int getTypeId() { return TYPEID; }
	public final int curversetid;
	public final int curverid;
	public final int patroltype;
	public CurvePatrol(cfg.DataStream fs) {
		super(fs);
		this.curversetid = fs.getInt();
		this.curverid = fs.getInt();
		this.patroltype = fs.getInt();
	}
}
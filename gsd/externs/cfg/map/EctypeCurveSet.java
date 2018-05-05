package cfg.map;
public final class EctypeCurveSet extends cfg.CfgObject {
	public final static int TYPEID = -928630105;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final java.util.List<cfg.map.IndexedBezierCurve> curves = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.map.IndexedBezierCurve> curves_id= new java.util.HashMap<>();
	public EctypeCurveSet(cfg.DataStream fs) {
		this.id = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.map.IndexedBezierCurve _v = new cfg.map.IndexedBezierCurve(fs);
			this.curves.add(_v);
			this.curves_id.put(_v.id, _v);
		}
	}
}
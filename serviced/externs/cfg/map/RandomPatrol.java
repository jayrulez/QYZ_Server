package cfg.map;
public final class RandomPatrol extends cfg.map.PatrolInfo {
	public final static int TYPEID = -1055507285;
	final public int getTypeId() { return TYPEID; }
	public final int regionsetid;
	public final int regionid;
	public RandomPatrol(cfg.DataStream fs) {
		super(fs);
		this.regionsetid = fs.getInt();
		this.regionid = fs.getInt();
	}
}
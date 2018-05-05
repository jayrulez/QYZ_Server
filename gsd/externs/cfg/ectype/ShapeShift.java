package cfg.ectype;
public final class ShapeShift extends cfg.ectype.Action {
	public final static int TYPEID = -2007367347;
	final public int getTypeId() { return TYPEID; }
	public final int characterid;
	public final int shapeid;
	public ShapeShift(cfg.DataStream fs) {
		super(fs);
		this.characterid = fs.getInt();
		this.shapeid = fs.getInt();
	}
}
package cfg.ectype;
public final class HPCheck extends cfg.ectype.Action {
	public final static int TYPEID = -1145673580;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int op;
	public final float percent;
	public HPCheck(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.op = fs.getInt();
		this.percent = fs.getFloat();
	}
}
package cfg.cmd.action;
public final class Heal extends cfg.cmd.action.Action {
	public final static int TYPEID = -1416317310;
	final public int getTypeId() { return TYPEID; }
	public final float HP;
	public final float HPPrecent;
	public final float MP;
	public final float MPPrecent;
	public Heal(cfg.DataStream fs) {
		super(fs);
		this.HP = fs.getFloat();
		this.HPPrecent = fs.getFloat();
		this.MP = fs.getFloat();
		this.MPPrecent = fs.getFloat();
	}
}
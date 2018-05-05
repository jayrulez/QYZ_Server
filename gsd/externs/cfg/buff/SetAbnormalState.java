package cfg.buff;
public final class SetAbnormalState extends cfg.buff.Effect {
	public final static int TYPEID = 1589511736;
	final public int getTypeId() { return TYPEID; }
	public final String introduction;
	public final int statetype;
	public SetAbnormalState(cfg.DataStream fs) {
		super(fs);
		this.introduction = fs.getString();
		this.statetype = fs.getInt();
	}
}
package cfg.bag;
public final class SlotCondition extends cfg.CfgObject {
	public final static int TYPEID = 1775077933;
	final public int getTypeId() { return TYPEID; }
	public final int bagtype;
	public final int slotindex;
	public final cfg.cmd.condition.Condition condition;
	public SlotCondition(cfg.DataStream fs) {
		this.bagtype = fs.getInt();
		this.slotindex = fs.getInt();
		this.condition = (cfg.cmd.condition.Condition)fs.getObject(fs.getString());
	}
}
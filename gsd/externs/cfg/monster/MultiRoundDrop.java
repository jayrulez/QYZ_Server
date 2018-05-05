package cfg.monster;
public final class MultiRoundDrop extends cfg.CfgObject {
	public final static int TYPEID = -1827439134;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.Drop drop;
	public final int num;
	public MultiRoundDrop(cfg.DataStream fs) {
		this.drop = new cfg.cmd.action.Drop(fs);
		this.num = fs.getInt();
	}
}
package cfg.exchange;
public final class Exchange extends cfg.CfgObject {
	public final static int TYPEID = 1651957412;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final int index;
	public final int defaultyuanbao;
	public Exchange(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.index = fs.getInt();
		this.defaultyuanbao = fs.getInt();
	}
}
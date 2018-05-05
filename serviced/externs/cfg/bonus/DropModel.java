package cfg.bonus;
public final class DropModel extends cfg.CfgObject {
	public final static int TYPEID = 1291724993;
	final public int getTypeId() { return TYPEID; }
	public final int itemid;
	public final String name;
	public final int namecolor;
	public final String modelpath;
	public DropModel(cfg.DataStream fs) {
		this.itemid = fs.getInt();
		this.name = fs.getString();
		this.namecolor = fs.getInt();
		this.modelpath = fs.getString();
	}
}
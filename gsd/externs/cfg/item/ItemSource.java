package cfg.item;
public abstract class ItemSource extends cfg.CfgObject {
	public final int id;
	public final String name;
	public ItemSource(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
	}
}
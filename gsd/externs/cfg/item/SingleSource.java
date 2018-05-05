package cfg.item;
public final class SingleSource extends cfg.item.ItemSource {
	public final static int TYPEID = 1996271252;
	final public int getTypeId() { return TYPEID; }
	public final cfg.item.SourceInfo source;
	public SingleSource(cfg.DataStream fs) {
		super(fs);
		this.source = new cfg.item.SourceInfo(fs);
	}
}
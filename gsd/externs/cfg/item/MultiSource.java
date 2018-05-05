package cfg.item;
public final class MultiSource extends cfg.item.ItemSource {
	public final static int TYPEID = -1228134269;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.item.SourceInfo> sourcelist = new java.util.ArrayList<>();
	public MultiSource(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.sourcelist.add(new cfg.item.SourceInfo(fs));
		}
	}
}
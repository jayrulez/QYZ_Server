package cfg.item;
public final class EquipSource extends cfg.CfgObject {
	public final static int TYPEID = 1887921722;
	final public int getTypeId() { return TYPEID; }
	public final int type;
	public final java.util.List<cfg.item.SourceInfo> sourcelist = new java.util.ArrayList<>();
	public EquipSource(cfg.DataStream fs) {
		this.type = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.sourcelist.add(new cfg.item.SourceInfo(fs));
		}
	}
}
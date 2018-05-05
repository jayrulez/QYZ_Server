package cfg.manual;
public final class PetAwakeBonus extends cfg.CfgObject {
	public final static int TYPEID = 1694209819;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final java.util.List<cfg.manual.AwakeData> propertylist = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.manual.AwakeData> propertylist_awakelevel= new java.util.HashMap<>();
	public PetAwakeBonus(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.manual.AwakeData _v = new cfg.manual.AwakeData(fs);
			this.propertylist.add(_v);
			this.propertylist_awakelevel.put(_v.awakelevel, _v);
		}
	}
}
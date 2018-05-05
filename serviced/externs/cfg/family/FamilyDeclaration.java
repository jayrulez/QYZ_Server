package cfg.family;
public final class FamilyDeclaration extends cfg.CfgObject {
	public final static int TYPEID = 1194849686;
	final public int getTypeId() { return TYPEID; }
	public final int maxdeclarenum;
	public final java.util.List<cfg.common.DayTimeRange> openranges = new java.util.ArrayList<>();
	public FamilyDeclaration(cfg.DataStream fs) {
		this.maxdeclarenum = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.openranges.add(new cfg.common.DayTimeRange(fs));
		}
	}
}
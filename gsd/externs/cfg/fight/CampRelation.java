package cfg.fight;
public final class CampRelation extends cfg.CfgObject {
	public final static int TYPEID = 1110967973;
	final public int getTypeId() { return TYPEID; }
	public final int camp;
	public final java.util.List<Integer> relations = new java.util.ArrayList<>();
	public CampRelation(cfg.DataStream fs) {
		this.camp = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.relations.add(fs.getInt());
		}
	}
}
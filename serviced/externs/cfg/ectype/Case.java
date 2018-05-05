package cfg.ectype;
public final class Case extends cfg.CfgObject {
	public final static int TYPEID = 1772688412;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.ectype.ExeCondition> conditions = new java.util.ArrayList<>();
	public final cfg.ectype.Action action;
	public Case(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.conditions.add((cfg.ectype.ExeCondition)fs.getObject(fs.getString()));
		}
		this.action = (cfg.ectype.Action)fs.getObject(fs.getString());
	}
}
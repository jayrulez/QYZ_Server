package cfg.operationalactivity;
public final class OperationalActivityIndex extends cfg.CfgObject {
	public final static int TYPEID = 1443189288;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> index = new java.util.ArrayList<>();
	public OperationalActivityIndex(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.index.add(fs.getInt());
		}
	}
}
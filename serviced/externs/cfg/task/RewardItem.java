package cfg.task;
public final class RewardItem extends cfg.CfgObject {
	public final static int TYPEID = 483833217;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> itemid = new java.util.ArrayList<>();
	public final java.util.List<Integer> itemcount = new java.util.ArrayList<>();
	public final java.util.List<Float> itemprob = new java.util.ArrayList<>();
	public final float totalprob;
	public final boolean randomselectoneitem;
	public RewardItem(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.itemid.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.itemcount.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.itemprob.add(fs.getFloat());
		}
		this.totalprob = fs.getFloat();
		this.randomselectoneitem = fs.getBool();
	}
}
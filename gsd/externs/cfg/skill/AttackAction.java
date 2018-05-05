package cfg.skill;
public final class AttackAction extends cfg.CfgObject {
	public final static int TYPEID = -800976635;
	final public int getTypeId() { return TYPEID; }
	public final int prevbuffid;
	public final int succbuffid;
	public final java.util.List<Float> rate = new java.util.ArrayList<>();
	public final java.util.List<Float> damage = new java.util.ArrayList<>();
	public AttackAction(cfg.DataStream fs) {
		this.prevbuffid = fs.getInt();
		this.succbuffid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.rate.add(fs.getFloat());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.damage.add(fs.getFloat());
		}
	}
}
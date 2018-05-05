package cfg.pet;
public final class PetWash extends cfg.CfgObject {
	public final static int TYPEID = -1338795035;
	final public int getTypeId() { return TYPEID; }
	public static final int COST_ITEM_ID = 10400013;
	public static final int WASH_XUNIBI_KEY = 1;
	public static final int WASH_YUANBAO_KEY = 2;
	public final int feedid;
	public final String name;
	public final cfg.cmd.condition.MultiCondition cost;
	public final java.util.List<Integer> hp = new java.util.ArrayList<>();
	public final java.util.List<Integer> mp = new java.util.ArrayList<>();
	public final java.util.List<Integer> minatk = new java.util.ArrayList<>();
	public final java.util.List<Integer> maxatk = new java.util.ArrayList<>();
	public final java.util.List<Integer> defence = new java.util.ArrayList<>();
	public final java.util.List<Integer> hitrate = new java.util.ArrayList<>();
	public final java.util.List<Integer> hitresist = new java.util.ArrayList<>();
	public PetWash(cfg.DataStream fs) {
		this.feedid = fs.getInt();
		this.name = fs.getString();
		this.cost = new cfg.cmd.condition.MultiCondition(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.hp.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.mp.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.minatk.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.maxatk.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.defence.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.hitrate.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.hitresist.add(fs.getInt());
		}
	}
}
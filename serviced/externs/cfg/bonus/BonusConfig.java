package cfg.bonus;
public final class BonusConfig extends cfg.CfgObject {
	public final static int TYPEID = 38360520;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> retroactivecost = new java.util.ArrayList<>();
	public final java.util.List<Integer> vipretroactive = new java.util.ArrayList<>();
	public final int vowtime;
	public final int vowitem;
	public final java.util.List<Integer> viptimes = new java.util.ArrayList<>();
	public final cfg.cmd.condition.YuanBao monthlycard;
	public final cfg.cmd.condition.YuanBao growplan;
	public final java.util.List<Integer> growplanmaxlvl = new java.util.ArrayList<>();
	public BonusConfig(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.retroactivecost.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.vipretroactive.add(fs.getInt());
		}
		this.vowtime = fs.getInt();
		this.vowitem = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.viptimes.add(fs.getInt());
		}
		this.monthlycard = new cfg.cmd.condition.YuanBao(fs);
		this.growplan = new cfg.cmd.condition.YuanBao(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.growplanmaxlvl.add(fs.getInt());
		}
	}
}
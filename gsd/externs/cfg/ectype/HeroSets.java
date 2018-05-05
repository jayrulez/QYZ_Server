package cfg.ectype;
public final class HeroSets extends cfg.CfgObject {
	public final static int TYPEID = 1653066327;
	final public int getTypeId() { return TYPEID; }
	public static final int bonusrefreshtime = 5;
	public final cfg.cmd.condition.Limit dailylimit;
	public final java.util.List<Integer> resettime = new java.util.ArrayList<>();
	public final java.util.List<cfg.cmd.condition.YuanBao> changecost = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.HeroEctypeMsg> ectypemsg = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.HeroEctypeMsg> ectypemsg_id= new java.util.HashMap<>();
	public HeroSets(cfg.DataStream fs) {
		this.dailylimit = new cfg.cmd.condition.Limit(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.resettime.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.changecost.add(new cfg.cmd.condition.YuanBao(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.HeroEctypeMsg _v = new cfg.ectype.HeroEctypeMsg(fs);
			this.ectypemsg.add(_v);
			this.ectypemsg_id.put(_v.id, _v);
		}
	}
}
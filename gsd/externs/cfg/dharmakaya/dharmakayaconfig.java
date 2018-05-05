package cfg.dharmakaya;
public final class dharmakayaconfig extends cfg.CfgObject {
	public final static int TYPEID = -1322348922;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.dharmakaya.DharmakayaStateInfo> stateinfo = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.dharmakaya.DharmakayaStateInfo> stateinfo_num= new java.util.HashMap<>();
	public final java.util.List<cfg.dharmakaya.DharmakayaInfo> pointconfig = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.dharmakaya.DharmakayaInfo> pointconfig_num= new java.util.HashMap<>();
	public final float normaltranslaterate;
	public final cfg.cmd.condition.Condition normalcost;
	public final float yuanbaotranslaterate;
	public final cfg.cmd.condition.Condition yuanbaocost;
	public final cfg.dharmakaya.BreakThroughCost normalbreakcost;
	public final cfg.dharmakaya.BreakThroughCost highbreakcost;
	public final int advunlockneedlevel;
	public final int conlevelup;
	public dharmakayaconfig(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.dharmakaya.DharmakayaStateInfo _v = new cfg.dharmakaya.DharmakayaStateInfo(fs);
			this.stateinfo.add(_v);
			this.stateinfo_num.put(_v.num, _v);
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.dharmakaya.DharmakayaInfo _v = new cfg.dharmakaya.DharmakayaInfo(fs);
			this.pointconfig.add(_v);
			this.pointconfig_num.put(_v.num, _v);
		}
		this.normaltranslaterate = fs.getFloat();
		this.normalcost = (cfg.cmd.condition.Condition)fs.getObject(fs.getString());
		this.yuanbaotranslaterate = fs.getFloat();
		this.yuanbaocost = (cfg.cmd.condition.Condition)fs.getObject(fs.getString());
		this.normalbreakcost = new cfg.dharmakaya.BreakThroughCost(fs);
		this.highbreakcost = new cfg.dharmakaya.BreakThroughCost(fs);
		this.advunlockneedlevel = fs.getInt();
		this.conlevelup = fs.getInt();
	}
}
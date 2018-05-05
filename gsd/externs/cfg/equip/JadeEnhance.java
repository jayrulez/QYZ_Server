package cfg.equip;
public final class JadeEnhance extends cfg.CfgObject {
	public final static int TYPEID = -1804100690;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.equip.JadeEnhanceData> enhancedata = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.equip.JadeEnhanceData> enhancedata_enhancetypeid= new java.util.HashMap<>();
	public final cfg.cmd.condition.XuNiBi searchcost;
	public final java.util.List<Integer> holeopenlevel = new java.util.ArrayList<>();
	public final cfg.cmd.condition.OneItem enhanceitemid;
	public final cfg.cmd.condition.YuanBao level4cost;
	public final int packlarge;
	public final int openlevel;
	public final java.util.List<String> gettertexture = new java.util.ArrayList<>();
	public JadeEnhance(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.equip.JadeEnhanceData _v = new cfg.equip.JadeEnhanceData(fs);
			this.enhancedata.add(_v);
			this.enhancedata_enhancetypeid.put(_v.enhancetypeid, _v);
		}
		this.searchcost = new cfg.cmd.condition.XuNiBi(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.holeopenlevel.add(fs.getInt());
		}
		this.enhanceitemid = new cfg.cmd.condition.OneItem(fs);
		this.level4cost = new cfg.cmd.condition.YuanBao(fs);
		this.packlarge = fs.getInt();
		this.openlevel = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.gettertexture.add(fs.getString());
		}
	}
}
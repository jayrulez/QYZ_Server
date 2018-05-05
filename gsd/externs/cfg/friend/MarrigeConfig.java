package cfg.friend;
public final class MarrigeConfig extends cfg.CfgObject {
	public final static int TYPEID = 142792879;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.friend.MarrigePackage> marrigepack = new java.util.ArrayList<>();
	public final int requirelevel;
	public final java.util.List<Integer> giftpackid = new java.util.ArrayList<>();
	public final java.util.List<cfg.cmd.action.Currencys> giftcurrency = new java.util.ArrayList<>();
	public final cfg.cmd.condition.YuanBao divorceprice;
	public final int marrigetextlength;
	public final int divorcetextlength;
	public final int divorceitemid;
	public MarrigeConfig(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.marrigepack.add(new cfg.friend.MarrigePackage(fs));
		}
		this.requirelevel = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.giftpackid.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.giftcurrency.add(new cfg.cmd.action.Currencys(fs));
		}
		this.divorceprice = new cfg.cmd.condition.YuanBao(fs);
		this.marrigetextlength = fs.getInt();
		this.divorcetextlength = fs.getInt();
		this.divorceitemid = fs.getInt();
	}
}
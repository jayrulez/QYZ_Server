package cfg.pay;
public abstract class Charge extends cfg.CfgObject {
	public final int chargeid;
	public final int displayorder;
	public final int price;
	public final java.util.Map<Integer, Integer> platform = new java.util.HashMap<>();
	public Charge(cfg.DataStream fs) {
		this.chargeid = fs.getInt();
		this.displayorder = fs.getInt();
		this.price = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.platform.put(_k, fs.getInt());
		}
	}
}
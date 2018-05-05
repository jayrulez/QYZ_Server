package cfg.tips;
public final class BroadcastCfg extends cfg.CfgObject {
	public final static int TYPEID = 131552239;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> rolelevelup = new java.util.ArrayList<>();
	public final int minanneal;
	public final java.util.List<Integer> perfuse = new java.util.ArrayList<>();
	public final int mintalismanstar;
	public final int minpetawake;
	public final int minorangepetawake;
	public final int mintalismanawake;
	public final int minorangetalismanawake;
	public BroadcastCfg(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.rolelevelup.add(fs.getInt());
		}
		this.minanneal = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.perfuse.add(fs.getInt());
		}
		this.mintalismanstar = fs.getInt();
		this.minpetawake = fs.getInt();
		this.minorangepetawake = fs.getInt();
		this.mintalismanawake = fs.getInt();
		this.minorangetalismanawake = fs.getInt();
	}
}
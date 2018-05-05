package cfg.role;
public final class Paomadeng extends cfg.CfgObject {
	public final static int TYPEID = 1379335902;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> rolelevelup = new java.util.ArrayList<>();
	public final int minanneal;
	public final java.util.List<Integer> perfuse = new java.util.ArrayList<>();
	public final int mintalismanstart;
	public final int minpetawake;
	public Paomadeng(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.rolelevelup.add(fs.getInt());
		}
		this.minanneal = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.perfuse.add(fs.getInt());
		}
		this.mintalismanstart = fs.getInt();
		this.minpetawake = fs.getInt();
	}
}
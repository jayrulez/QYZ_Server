package cfg.equip;
public final class JewelryGet extends cfg.CfgObject {
	public final static int TYPEID = -209183212;
	final public int getTypeId() { return TYPEID; }
	public final int getlevel;
	public final float uprate;
	public final float downrate;
	public final java.util.List<Float> ratelist = new java.util.ArrayList<>();
	public JewelryGet(cfg.DataStream fs) {
		this.getlevel = fs.getInt();
		this.uprate = fs.getFloat();
		this.downrate = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.ratelist.add(fs.getFloat());
		}
	}
}
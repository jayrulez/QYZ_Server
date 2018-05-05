package cfg.equip;
public final class Jewelry extends cfg.CfgObject {
	public final static int TYPEID = 314283490;
	final public int getTypeId() { return TYPEID; }
	public final int jewelryid;
	public final String jewelryname;
	public final String icon;
	public final String introduce;
	public final int quality;
	public final int propertytype;
	public final float basicvalue;
	public final float maturerate;
	public final int quatilyexp;
	public Jewelry(cfg.DataStream fs) {
		this.jewelryid = fs.getInt();
		this.jewelryname = fs.getString();
		this.icon = fs.getString();
		this.introduce = fs.getString();
		this.quality = fs.getInt();
		this.propertytype = fs.getInt();
		this.basicvalue = fs.getFloat();
		this.maturerate = fs.getFloat();
		this.quatilyexp = fs.getInt();
	}
}
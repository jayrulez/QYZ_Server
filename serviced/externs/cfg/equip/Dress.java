package cfg.equip;
public final class Dress extends cfg.CfgObject {
	public final static int TYPEID = 2135936079;
	final public int getTypeId() { return TYPEID; }
	public static final int DEFAULT_DRESSID = 55000001;
	public static final int OPEN_LEVEL = 10;
	public final int id;
	public final String name;
	public final String icon;
	public final int displayorder;
	public final int showmode;
	public final java.util.Map<Integer, String> modelname = new java.util.HashMap<>();
	public final String introduction;
	public final int gainfunction;
	public final boolean quickbuy;
	public final cfg.cmd.condition.MinVipLevel viplimit;
	public final cfg.cmd.condition.YuanBao price;
	public final int sex;
	public final String gainpage;
	public final int quality;
	public final java.util.List<cfg.equip.EquipPropertyData> propertylist = new java.util.ArrayList<>();
	public Dress(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.icon = fs.getString();
		this.displayorder = fs.getInt();
		this.showmode = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.modelname.put(_k, fs.getString());
		}
		this.introduction = fs.getString();
		this.gainfunction = fs.getInt();
		this.quickbuy = fs.getBool();
		this.viplimit = new cfg.cmd.condition.MinVipLevel(fs);
		this.price = new cfg.cmd.condition.YuanBao(fs);
		this.sex = fs.getInt();
		this.gainpage = fs.getString();
		this.quality = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.propertylist.add(new cfg.equip.EquipPropertyData(fs));
		}
	}
}
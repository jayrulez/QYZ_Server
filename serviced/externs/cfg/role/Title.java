package cfg.role;
public final class Title extends cfg.CfgObject {
	public final static int TYPEID = 1376790154;
	final public int getTypeId() { return TYPEID; }
	public static final int OPEN_LEVEL = 0;
	public static final String TitlePrefabGroupPath = "dlgheadtitle";
	public final int id;
	public final int titletype;
	public final int showtype;
	public final int showmode;
	public final String titletypename;
	public final String name;
	public final String path;
	public final String condition;
	public final int titletime;
	public final String description;
	public final java.util.List<cfg.equip.EquipPropertyData> property = new java.util.ArrayList<>();
	public Title(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.titletype = fs.getInt();
		this.showtype = fs.getInt();
		this.showmode = fs.getInt();
		this.titletypename = fs.getString();
		this.name = fs.getString();
		this.path = fs.getString();
		this.condition = fs.getString();
		this.titletime = fs.getInt();
		this.description = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.property.add(new cfg.equip.EquipPropertyData(fs));
		}
	}
}
package cfg.role;
public final class DailyBounsActivity extends cfg.CfgObject {
	public final static int TYPEID = 1859474225;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.common.DateTimeRange openrange;
	public final String mailtitle;
	public final String mailcontent;
	public final java.util.List<cfg.role.IndexBonus> bonusbyday = new java.util.ArrayList<>();
	public DailyBounsActivity(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.openrange = new cfg.common.DateTimeRange(fs);
		this.mailtitle = fs.getString();
		this.mailcontent = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bonusbyday.add(new cfg.role.IndexBonus(fs));
		}
	}
}
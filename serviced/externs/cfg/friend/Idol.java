package cfg.friend;
public final class Idol extends cfg.CfgObject {
	public final static int TYPEID = 1725208350;
	final public int getTypeId() { return TYPEID; }
	public final long id;
	public final String name;
	public final String sign;
	public final String guardtalk;
	public final String image;
	public final String icon;
	public final int guardthreshold;
	public final java.util.List<cfg.friend.BonusList> bonuslist = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.friend.BonusList> bonuslist_bonusid= new java.util.HashMap<>();
	public final int guardtitleid;
	public Idol(cfg.DataStream fs) {
		this.id = fs.getLong();
		this.name = fs.getString();
		this.sign = fs.getString();
		this.guardtalk = fs.getString();
		this.image = fs.getString();
		this.icon = fs.getString();
		this.guardthreshold = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.friend.BonusList _v = new cfg.friend.BonusList(fs);
			this.bonuslist.add(_v);
			this.bonuslist_bonusid.put(_v.bonusid, _v);
		}
		this.guardtitleid = fs.getInt();
	}
}
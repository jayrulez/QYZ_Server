package cfg.common;
public final class WildcardProfession extends cfg.CfgObject {
	public final static int TYPEID = 700854895;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String qingyun;
	public final String tianyin;
	public final String guiwang;
	public final String hehuan;
	public WildcardProfession(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.qingyun = fs.getString();
		this.tianyin = fs.getString();
		this.guiwang = fs.getString();
		this.hehuan = fs.getString();
	}
}
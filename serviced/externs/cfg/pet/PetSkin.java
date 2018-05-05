package cfg.pet;
public final class PetSkin extends cfg.CfgObject {
	public final static int TYPEID = -1338904893;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final String icon;
	public final int displayorder;
	public final String gain;
	public final String detail;
	public final cfg.cmd.condition.YuanBao price;
	public final int quality;
	public final int petid;
	public final String modelname;
	public PetSkin(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.icon = fs.getString();
		this.displayorder = fs.getInt();
		this.gain = fs.getString();
		this.detail = fs.getString();
		this.price = new cfg.cmd.condition.YuanBao(fs);
		this.quality = fs.getInt();
		this.petid = fs.getInt();
		this.modelname = fs.getString();
	}
}
package cfg.equip;
public final class Riding extends cfg.CfgObject {
	public final static int TYPEID = -2113001315;
	final public int getTypeId() { return TYPEID; }
	public static final int OPEN_LEVEL = 0;
	public static final int RECOVERRIDE_TIME = 2;
	public static final int PORTAL_HEIGHT = 5;
	public final int id;
	public final String name;
	public final String icon;
	public final int displayorder;
	public final int showmode;
	public final String modelname;
	public final String introduction;
	public final java.util.List<cfg.equip.EquipPropertyData> battleproperty = new java.util.ArrayList<>();
	public final String gain;
	public final String gainpage;
	public final boolean quickbuy;
	public final cfg.cmd.condition.MinVipLevel viplimit;
	public final cfg.cmd.condition.YuanBao price;
	public final int ridingmotion;
	public final int quality;
	public final float speedbuff;
	public final float ridingheight;
	public final float initalminhigh;
	public final float initalmaxhigh;
	public final float upspeed;
	public final float downspeed;
	public final float ridespeed;
	public final float flyspeed;
	public final float initialheightinride;
	public final float initialheightinfly;
	public final String riggingpoint;
	public final float scale;
	public final float rotationy;
	public final float height;
	public final boolean rotateplayer;
	public final java.util.List<Float> rotation = new java.util.ArrayList<>();
	public Riding(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.icon = fs.getString();
		this.displayorder = fs.getInt();
		this.showmode = fs.getInt();
		this.modelname = fs.getString();
		this.introduction = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.battleproperty.add(new cfg.equip.EquipPropertyData(fs));
		}
		this.gain = fs.getString();
		this.gainpage = fs.getString();
		this.quickbuy = fs.getBool();
		this.viplimit = new cfg.cmd.condition.MinVipLevel(fs);
		this.price = new cfg.cmd.condition.YuanBao(fs);
		this.ridingmotion = fs.getInt();
		this.quality = fs.getInt();
		this.speedbuff = fs.getFloat();
		this.ridingheight = fs.getFloat();
		this.initalminhigh = fs.getFloat();
		this.initalmaxhigh = fs.getFloat();
		this.upspeed = fs.getFloat();
		this.downspeed = fs.getFloat();
		this.ridespeed = fs.getFloat();
		this.flyspeed = fs.getFloat();
		this.initialheightinride = fs.getFloat();
		this.initialheightinfly = fs.getFloat();
		this.riggingpoint = fs.getString();
		this.scale = fs.getFloat();
		this.rotationy = fs.getFloat();
		this.height = fs.getFloat();
		this.rotateplayer = fs.getBool();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.rotation.add(fs.getFloat());
		}
	}
}
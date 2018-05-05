package cfg.talisman;
public final class TalismanBasic extends cfg.CfgObject {
	public final static int TYPEID = 169149482;
	final public int getTypeId() { return TYPEID; }
	public static final int PUBLIC_SKILL_CD = 10;
	public static final int BAG_SIZE = 30;
	public final int id;
	public final String name;
	public final String introduction;
	public final boolean canuse;
	public final int quality;
	public final String icon;
	public final String modelpath;
	public final cfg.cmd.action.BindType bindtype;
	public final int qualityexp;
	public final cfg.pet.Maturerate maturerate;
	public final cfg.fight.BasicAttr attr;
	public final float specialattackrate;
	public final float uisize;
	public final float uipositionx;
	public final float uipositiony;
	public final float uipositionz;
	public TalismanBasic(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.introduction = fs.getString();
		this.canuse = fs.getBool();
		this.quality = fs.getInt();
		this.icon = fs.getString();
		this.modelpath = fs.getString();
		this.bindtype = new cfg.cmd.action.BindType(fs);
		this.qualityexp = fs.getInt();
		this.maturerate = new cfg.pet.Maturerate(fs);
		this.attr = new cfg.fight.BasicAttr(fs);
		this.specialattackrate = fs.getFloat();
		this.uisize = fs.getFloat();
		this.uipositionx = fs.getFloat();
		this.uipositiony = fs.getFloat();
		this.uipositionz = fs.getFloat();
	}
}
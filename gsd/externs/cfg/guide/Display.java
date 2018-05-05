package cfg.guide;
public final class Display extends cfg.guide.GuideEffect {
	public final static int TYPEID = -1196786778;
	final public int getTypeId() { return TYPEID; }
	public final boolean istexture;
	public final String icon;
	public final String desc;
	public final String desc1;
	public final int target;
	public Display(cfg.DataStream fs) {
		super(fs);
		this.istexture = fs.getBool();
		this.icon = fs.getString();
		this.desc = fs.getString();
		this.desc1 = fs.getString();
		this.target = fs.getInt();
	}
}
package cfg.ui;
public final class ContentUITexture extends cfg.ui.ContentUIBasicSprite {
	public final static int TYPEID = -711764162;
	final public int getTypeId() { return TYPEID; }
	public final String mainTexture;
	public final String material;
	public final String shader;
	public final cfg.ui.Rect uvRect;
	public final boolean fixedAspect;
	public final cfg.ui.Vector4 border;
	public ContentUITexture(cfg.DataStream fs) {
		super(fs);
		this.mainTexture = fs.getString();
		this.material = fs.getString();
		this.shader = fs.getString();
		this.uvRect = new cfg.ui.Rect(fs);
		this.fixedAspect = fs.getBool();
		this.border = new cfg.ui.Vector4(fs);
	}
}
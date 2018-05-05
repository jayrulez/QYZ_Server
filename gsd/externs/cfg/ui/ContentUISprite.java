package cfg.ui;
public final class ContentUISprite extends cfg.ui.ContentUIBasicSprite {
	public final static int TYPEID = 512569410;
	final public int getTypeId() { return TYPEID; }
	public final String atlas;
	public final String sprite;
	public ContentUISprite(cfg.DataStream fs) {
		super(fs);
		this.atlas = fs.getString();
		this.sprite = fs.getString();
	}
}
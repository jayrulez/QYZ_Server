package cfg.ui;
public final class ContentUIAtlasSpriteData extends cfg.CfgObject {
	public final static int TYPEID = -304457187;
	final public int getTypeId() { return TYPEID; }
	public final String name;
	public final int x;
	public final int y;
	public final int width;
	public final int height;
	public final int borderLeft;
	public final int borderRight;
	public final int borderTop;
	public final int borderBottom;
	public final int paddingLeft;
	public final int paddingRight;
	public final int paddingTop;
	public final int paddingBottom;
	public ContentUIAtlasSpriteData(cfg.DataStream fs) {
		this.name = fs.getString();
		this.x = fs.getInt();
		this.y = fs.getInt();
		this.width = fs.getInt();
		this.height = fs.getInt();
		this.borderLeft = fs.getInt();
		this.borderRight = fs.getInt();
		this.borderTop = fs.getInt();
		this.borderBottom = fs.getInt();
		this.paddingLeft = fs.getInt();
		this.paddingRight = fs.getInt();
		this.paddingTop = fs.getInt();
		this.paddingBottom = fs.getInt();
	}
}
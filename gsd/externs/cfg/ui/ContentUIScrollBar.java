package cfg.ui;
public final class ContentUIScrollBar extends cfg.ui.ContentUIProgressBarBase {
	public final static int TYPEID = 329519977;
	final public int getTypeId() { return TYPEID; }
	public final float barSize;
	public ContentUIScrollBar(cfg.DataStream fs) {
		super(fs);
		this.barSize = fs.getFloat();
	}
}
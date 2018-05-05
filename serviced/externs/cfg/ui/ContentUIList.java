package cfg.ui;
public final class ContentUIList extends cfg.ui.ContentUIBase {
	public final static int TYPEID = -1067837093;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ui.ContentUITransform m_prefabListItem;
	public ContentUIList(cfg.DataStream fs) {
		super(fs);
		this.m_prefabListItem = new cfg.ui.ContentUITransform(fs);
	}
}
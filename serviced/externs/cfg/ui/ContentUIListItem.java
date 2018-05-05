package cfg.ui;
public final class ContentUIListItem extends cfg.ui.ContentUIBase {
	public final static int TYPEID = 1758126862;
	final public int getTypeId() { return TYPEID; }
	public final int m_nIndex;
	public final cfg.ui.ContentUITransform m_uiSpriteIcon;
	public final cfg.ui.ContentUITransform m_uiSpriteRank;
	public final cfg.ui.ContentUITransform m_uiTextureIcon;
	public ContentUIListItem(cfg.DataStream fs) {
		super(fs);
		this.m_nIndex = fs.getInt();
		this.m_uiSpriteIcon = new cfg.ui.ContentUITransform(fs);
		this.m_uiSpriteRank = new cfg.ui.ContentUITransform(fs);
		this.m_uiTextureIcon = new cfg.ui.ContentUITransform(fs);
	}
}
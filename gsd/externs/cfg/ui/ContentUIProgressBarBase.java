package cfg.ui;
public abstract class ContentUIProgressBarBase extends cfg.ui.ContentUIBase {
	public final float value;
	public final float alpha;
	public final int numberOfSteps;
	public final cfg.ui.ContentUITransform backgroundWidget;
	public final cfg.ui.ContentUITransform foregroundWidget;
	public final cfg.ui.ContentUITransform thumb;
	public final String fillDirection;
	public ContentUIProgressBarBase(cfg.DataStream fs) {
		super(fs);
		this.value = fs.getFloat();
		this.alpha = fs.getFloat();
		this.numberOfSteps = fs.getInt();
		this.backgroundWidget = new cfg.ui.ContentUITransform(fs);
		this.foregroundWidget = new cfg.ui.ContentUITransform(fs);
		this.thumb = new cfg.ui.ContentUITransform(fs);
		this.fillDirection = fs.getString();
	}
}
package cfg.ui;
public final class ContentUIPlaySound extends cfg.ui.ContentUIBase {
	public final static int TYPEID = -1976978530;
	final public int getTypeId() { return TYPEID; }
	public final String audioClip;
	public final String trigger;
	public final float volume;
	public final float pitch;
	public ContentUIPlaySound(cfg.DataStream fs) {
		super(fs);
		this.audioClip = fs.getString();
		this.trigger = fs.getString();
		this.volume = fs.getFloat();
		this.pitch = fs.getFloat();
	}
}
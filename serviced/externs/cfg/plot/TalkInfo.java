package cfg.plot;
public final class TalkInfo extends cfg.CfgObject {
	public final static int TYPEID = 727960061;
	final public int getTypeId() { return TYPEID; }
	public final String talkperson;
	public final String talkcontent;
	public final String picture;
	public final String sound;
	public final float start;
	public final float duration;
	public TalkInfo(cfg.DataStream fs) {
		this.talkperson = fs.getString();
		this.talkcontent = fs.getString();
		this.picture = fs.getString();
		this.sound = fs.getString();
		this.start = fs.getFloat();
		this.duration = fs.getFloat();
	}
}
package cfg.audio;
public final class StoryDialogSound extends cfg.CfgObject {
	public final static int TYPEID = 1822599284;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final int maleaudio;
	public final int femaleaudio;
	public final int boyaudio;
	public final int girlaudio;
	public final int npcaudio;
	public final String content;
	public StoryDialogSound(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.maleaudio = fs.getInt();
		this.femaleaudio = fs.getInt();
		this.boyaudio = fs.getInt();
		this.girlaudio = fs.getInt();
		this.npcaudio = fs.getInt();
		this.content = fs.getString();
	}
}
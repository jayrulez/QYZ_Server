package cfg.audio;
public final class DialogSound extends cfg.CfgObject {
	public final static int TYPEID = -771808411;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String task;
	public final boolean isplayer;
	public final int maleaudio;
	public final int femaleaudio;
	public final int boyaudio;
	public final int girlaudio;
	public final int npcaudio;
	public final String dialogtext;
	public DialogSound(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.task = fs.getString();
		this.isplayer = fs.getBool();
		this.maleaudio = fs.getInt();
		this.femaleaudio = fs.getInt();
		this.boyaudio = fs.getInt();
		this.girlaudio = fs.getInt();
		this.npcaudio = fs.getInt();
		this.dialogtext = fs.getString();
	}
}
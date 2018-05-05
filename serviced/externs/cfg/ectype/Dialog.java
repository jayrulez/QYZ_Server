package cfg.ectype;
public final class Dialog extends cfg.ectype.Action {
	public final static int TYPEID = -1512961228;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final boolean isbubble;
	public final int speakertype;
	public final int time;
	public final int audioid;
	public final String content;
	public final int side;
	public final boolean isstop;
	public final int dialogtype;
	public Dialog(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.isbubble = fs.getBool();
		this.speakertype = fs.getInt();
		this.time = fs.getInt();
		this.audioid = fs.getInt();
		this.content = fs.getString();
		this.side = fs.getInt();
		this.isstop = fs.getBool();
		this.dialogtype = fs.getInt();
	}
}
package cfg.ectype;
public final class PlayAudio extends cfg.ectype.Action {
	public final static int TYPEID = -192078954;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public PlayAudio(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
	}
}
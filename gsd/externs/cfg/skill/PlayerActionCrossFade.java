package cfg.skill;
public final class PlayerActionCrossFade extends cfg.CfgObject {
	public final static int TYPEID = 1088853406;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String statebegin;
	public final String stateend;
	public final float crossfadeduration;
	public PlayerActionCrossFade(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.statebegin = fs.getString();
		this.stateend = fs.getString();
		this.crossfadeduration = fs.getFloat();
	}
}
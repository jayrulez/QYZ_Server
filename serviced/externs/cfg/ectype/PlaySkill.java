package cfg.ectype;
public final class PlaySkill extends cfg.ectype.Action {
	public final static int TYPEID = -175748591;
	final public int getTypeId() { return TYPEID; }
	public final int characterid;
	public final int skillid;
	public final cfg.map.Vector2 position;
	public PlaySkill(cfg.DataStream fs) {
		super(fs);
		this.characterid = fs.getInt();
		this.skillid = fs.getInt();
		this.position = new cfg.map.Vector2(fs);
	}
}
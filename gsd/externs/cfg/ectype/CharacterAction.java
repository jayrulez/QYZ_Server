package cfg.ectype;
public final class CharacterAction extends cfg.ectype.Action {
	public final static int TYPEID = -1208861101;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String action;
	public final int playtype;
	public final int actortype;
	public CharacterAction(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.action = fs.getString();
		this.playtype = fs.getInt();
		this.actortype = fs.getInt();
	}
}
package cfg.ectype;
public final class GetBuff extends cfg.ectype.Action {
	public final static int TYPEID = -1387829155;
	final public int getTypeId() { return TYPEID; }
	public final int characterid;
	public final int buffid;
	public GetBuff(cfg.DataStream fs) {
		super(fs);
		this.characterid = fs.getInt();
		this.buffid = fs.getInt();
	}
}
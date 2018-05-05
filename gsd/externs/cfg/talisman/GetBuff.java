package cfg.talisman;
public final class GetBuff extends cfg.talisman.AwakeEffect {
	public final static int TYPEID = -1563389002;
	final public int getTypeId() { return TYPEID; }
	public final int buffid;
	public GetBuff(cfg.DataStream fs) {
		super(fs);
		this.buffid = fs.getInt();
	}
}
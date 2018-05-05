package cfg.pet;
public final class GetBuff extends cfg.pet.AwakeEffect {
	public final static int TYPEID = -737000368;
	final public int getTypeId() { return TYPEID; }
	public final int buffid;
	public GetBuff(cfg.DataStream fs) {
		super(fs);
		this.buffid = fs.getInt();
	}
}
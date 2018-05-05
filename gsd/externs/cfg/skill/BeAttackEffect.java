package cfg.skill;
public final class BeAttackEffect extends cfg.CfgObject {
	public final static int TYPEID = -978099581;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int curve;
	public final String defenceraction;
	public final int defencereffectid;
	public BeAttackEffect(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.curve = fs.getInt();
		this.defenceraction = fs.getString();
		this.defencereffectid = fs.getInt();
	}
}
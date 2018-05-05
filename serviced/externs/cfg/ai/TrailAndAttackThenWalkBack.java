package cfg.ai;
public final class TrailAndAttackThenWalkBack extends cfg.ai.Expression {
	public final static int TYPEID = 1466969954;
	final public int getTypeId() { return TYPEID; }
	public final float guardradius;
	public final cfg.ai.ChooseTargetPolicy choosetargetpolicy;
	public TrailAndAttackThenWalkBack(cfg.DataStream fs) {
		super(fs);
		this.guardradius = fs.getFloat();
		this.choosetargetpolicy = (cfg.ai.ChooseTargetPolicy)fs.getObject(fs.getString());
	}
}
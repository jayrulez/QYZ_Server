package cfg.bonus;
public final class GrowPlan extends cfg.CfgObject {
	public final static int TYPEID = -819432011;
	final public int getTypeId() { return TYPEID; }
	public static final int FIRST_GROW_PLAN_END_INDEX = 4;
	public static final int SECOND_GROW_PLAN_START_INDEX = 5;
	public static final int SECOND_GROW_PLAN_END_INDEX = 9;
	public static final int THIRD_GROW_PLAN_START_INDEX = 10;
	public static final int THIRD_GROW_PLAN_END_INDEX = 15;
	public final int id;
	public final cfg.cmd.condition.MinLevel requirelvl;
	public final cfg.cmd.action.MultiBonus bonuslist;
	public GrowPlan(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.requirelvl = new cfg.cmd.condition.MinLevel(fs);
		this.bonuslist = new cfg.cmd.action.MultiBonus(fs);
	}
}
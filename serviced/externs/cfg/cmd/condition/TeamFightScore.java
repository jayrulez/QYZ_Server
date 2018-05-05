package cfg.cmd.condition;
public final class TeamFightScore extends cfg.cmd.condition.FixCurrency {
	public final static int TYPEID = 550035312;
	final public int getTypeId() { return TYPEID; }
	public TeamFightScore(cfg.DataStream fs) {
		super(fs);
	}
}
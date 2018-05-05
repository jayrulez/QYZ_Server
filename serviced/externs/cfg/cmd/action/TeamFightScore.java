package cfg.cmd.action;
public final class TeamFightScore extends cfg.cmd.action.FixCurrency {
	public final static int TYPEID = 1360015033;
	final public int getTypeId() { return TYPEID; }
	public TeamFightScore(cfg.DataStream fs) {
		super(fs);
	}
}
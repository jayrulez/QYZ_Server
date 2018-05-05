package cfg.timelottery;
public final class ScoreExchange extends cfg.CfgObject {
	public final static int TYPEID = 894117657;
	final public int getTypeId() { return TYPEID; }
	public final int needscore;
	public final String iconunlock;
	public final String iconlocked;
	public final cfg.cmd.action.MultiBonus bonus;
	public ScoreExchange(cfg.DataStream fs) {
		this.needscore = fs.getInt();
		this.iconunlock = fs.getString();
		this.iconlocked = fs.getString();
		this.bonus = new cfg.cmd.action.MultiBonus(fs);
	}
}
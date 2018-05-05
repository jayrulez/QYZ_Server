package cfg.follow;
public final class followsetting extends cfg.CfgObject {
	public final static int TYPEID = 1153018188;
	final public int getTypeId() { return TYPEID; }
	public final int maxfollowtimes;
	public final float followduration;
	public final cfg.cmd.condition.Currency followcost;
	public final String descriptioncan;
	public final String descriptioncannot;
	public final String descriptioncontinue;
	public followsetting(cfg.DataStream fs) {
		this.maxfollowtimes = fs.getInt();
		this.followduration = fs.getFloat();
		this.followcost = new cfg.cmd.condition.Currency(fs);
		this.descriptioncan = fs.getString();
		this.descriptioncannot = fs.getString();
		this.descriptioncontinue = fs.getString();
	}
}
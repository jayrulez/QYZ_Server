package cfg.achievement;
public final class TitleEvolve extends cfg.CfgObject {
	public final static int TYPEID = -1629539828;
	final public int getTypeId() { return TYPEID; }
	public final String name;
	public final cfg.cmd.condition.ChengJiu needvalue;
	public final int titleid;
	public TitleEvolve(cfg.DataStream fs) {
		this.name = fs.getString();
		this.needvalue = new cfg.cmd.condition.ChengJiu(fs);
		this.titleid = fs.getInt();
	}
}
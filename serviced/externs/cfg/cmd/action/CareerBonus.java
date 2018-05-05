package cfg.cmd.action;
public final class CareerBonus extends cfg.cmd.action.Bonus {
	public final static int TYPEID = -1457177241;
	final public int getTypeId() { return TYPEID; }
	public final int career;
	public final cfg.cmd.action.Bonus bonus;
	public CareerBonus(cfg.DataStream fs) {
		super(fs);
		this.career = fs.getInt();
		this.bonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}
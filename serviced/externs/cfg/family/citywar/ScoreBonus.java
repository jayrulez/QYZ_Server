package cfg.family.citywar;
public final class ScoreBonus extends cfg.CfgObject {
	public final static int TYPEID = 650075006;
	final public int getTypeId() { return TYPEID; }
	public final int upperboundscore;
	public final cfg.cmd.action.Bonus bonus;
	public ScoreBonus(cfg.DataStream fs) {
		this.upperboundscore = fs.getInt();
		this.bonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}
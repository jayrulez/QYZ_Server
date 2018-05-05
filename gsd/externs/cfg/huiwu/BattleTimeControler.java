package cfg.huiwu;
public final class BattleTimeControler extends cfg.CfgObject {
	public final static int TYPEID = 1679665255;
	final public int getTypeId() { return TYPEID; }
	public final int round;
	public final int wait;
	public final int battle;
	public final int relax;
	public BattleTimeControler(cfg.DataStream fs) {
		this.round = fs.getInt();
		this.wait = fs.getInt();
		this.battle = fs.getInt();
		this.relax = fs.getInt();
	}
}
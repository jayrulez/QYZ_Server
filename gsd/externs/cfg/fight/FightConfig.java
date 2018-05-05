package cfg.fight;
public final class FightConfig extends cfg.CfgObject {
	public final static int TYPEID = 2000128874;
	final public int getTypeId() { return TYPEID; }
	public final float defence2factor1;
	public final int defence2factor2;
	public final int defence2factor3;
	public final float defence2factor4;
	public final float attack2factor1;
	public final int attack2factor2;
	public final int attack2factor3;
	public FightConfig(cfg.DataStream fs) {
		this.defence2factor1 = fs.getFloat();
		this.defence2factor2 = fs.getInt();
		this.defence2factor3 = fs.getInt();
		this.defence2factor4 = fs.getFloat();
		this.attack2factor1 = fs.getFloat();
		this.attack2factor2 = fs.getInt();
		this.attack2factor3 = fs.getInt();
	}
}
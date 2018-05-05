package cfg.fight;
public final class BattlePower extends cfg.CfgObject {
	public final static int TYPEID = 2021636901;
	final public int getTypeId() { return TYPEID; }
	public final float hp;
	public final float mp;
	public final float minatk;
	public final float maxatk;
	public final float defence;
	public final float hit;
	public final float hitresist;
	public final float crit;
	public final float excellent;
	public final float luck;
	public final float abnormal;
	public final float skilllevel;
	public final float addtionaldamage;
	public final float talismanawakebonus;
	public final float talismanrate;
	public final float defence2;
	public final float attack2;
	public BattlePower(cfg.DataStream fs) {
		this.hp = fs.getFloat();
		this.mp = fs.getFloat();
		this.minatk = fs.getFloat();
		this.maxatk = fs.getFloat();
		this.defence = fs.getFloat();
		this.hit = fs.getFloat();
		this.hitresist = fs.getFloat();
		this.crit = fs.getFloat();
		this.excellent = fs.getFloat();
		this.luck = fs.getFloat();
		this.abnormal = fs.getFloat();
		this.skilllevel = fs.getFloat();
		this.addtionaldamage = fs.getFloat();
		this.talismanawakebonus = fs.getFloat();
		this.talismanrate = fs.getFloat();
		this.defence2 = fs.getFloat();
		this.attack2 = fs.getFloat();
	}
}
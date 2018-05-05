package cfg.fight;
public final class Attr extends cfg.CfgObject {
	public final static int TYPEID = 1905959161;
	final public int getTypeId() { return TYPEID; }
	public final float hp;
	public final float mp;
	public final float attackvaluemin;
	public final float attackvaluemax;
	public final float defence;
	public final float hitrate;
	public final float hitresistrate;
	public final float critrate;
	public final float critvalue;
	public final float critresistrate;
	public final float critresistvalue;
	public final float excellentrate;
	public final float excellentvalue;
	public final float excellentresistrate;
	public final float excellentresistvalue;
	public final float lucky;
	public final float attackmultirate;
	public final float defencemultirate;
	public final float abnormalresistrate;
	public final float abnormalhitrate;
	public final float movespeed;
	public final float heal;
	public final float damagetohuman;
	public final float damagetopet;
	public final float resistdamagefromhuman;
	public final float resistdamagefrompet;
	public Attr(cfg.DataStream fs) {
		this.hp = fs.getFloat();
		this.mp = fs.getFloat();
		this.attackvaluemin = fs.getFloat();
		this.attackvaluemax = fs.getFloat();
		this.defence = fs.getFloat();
		this.hitrate = fs.getFloat();
		this.hitresistrate = fs.getFloat();
		this.critrate = fs.getFloat();
		this.critvalue = fs.getFloat();
		this.critresistrate = fs.getFloat();
		this.critresistvalue = fs.getFloat();
		this.excellentrate = fs.getFloat();
		this.excellentvalue = fs.getFloat();
		this.excellentresistrate = fs.getFloat();
		this.excellentresistvalue = fs.getFloat();
		this.lucky = fs.getFloat();
		this.attackmultirate = fs.getFloat();
		this.defencemultirate = fs.getFloat();
		this.abnormalresistrate = fs.getFloat();
		this.abnormalhitrate = fs.getFloat();
		this.movespeed = fs.getFloat();
		this.heal = fs.getFloat();
		this.damagetohuman = fs.getFloat();
		this.damagetopet = fs.getFloat();
		this.resistdamagefromhuman = fs.getFloat();
		this.resistdamagefrompet = fs.getFloat();
	}
}
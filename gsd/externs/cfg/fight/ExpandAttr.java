package cfg.fight;
public final class ExpandAttr extends cfg.CfgObject {
	public final static int TYPEID = 1875947699;
	final public int getTypeId() { return TYPEID; }
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
	public ExpandAttr(cfg.DataStream fs) {
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
package cfg.fight;
public final class BasicAttr extends cfg.CfgObject {
	public final static int TYPEID = -825312969;
	final public int getTypeId() { return TYPEID; }
	public final float hp;
	public final float mp;
	public final float attackvaluemin;
	public final float attackvaluemax;
	public final float defence;
	public final float hitrate;
	public final float hitresistrate;
	public BasicAttr(cfg.DataStream fs) {
		this.hp = fs.getFloat();
		this.mp = fs.getFloat();
		this.attackvaluemin = fs.getFloat();
		this.attackvaluemax = fs.getFloat();
		this.defence = fs.getFloat();
		this.hitrate = fs.getFloat();
		this.hitresistrate = fs.getFloat();
	}
}
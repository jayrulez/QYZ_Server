package cfg.monster;
public final class DefaultAIParam extends cfg.CfgObject {
	public final static int TYPEID = 164253026;
	final public int getTypeId() { return TYPEID; }
	public final int ai;
	public final boolean proactive;
	public final int guardradius;
	public final int traceradius;
	public final int hostilitytype;
	public final int walkbackhealhprate;
	public final float walkbackhealhppercentrate;
	public DefaultAIParam(cfg.DataStream fs) {
		this.ai = fs.getInt();
		this.proactive = fs.getBool();
		this.guardradius = fs.getInt();
		this.traceradius = fs.getInt();
		this.hostilitytype = fs.getInt();
		this.walkbackhealhprate = fs.getInt();
		this.walkbackhealhppercentrate = fs.getFloat();
	}
}
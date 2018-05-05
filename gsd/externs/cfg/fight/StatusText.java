package cfg.fight;
public final class StatusText extends cfg.CfgObject {
	public final static int TYPEID = 1584767303;
	final public int getTypeId() { return TYPEID; }
	public final int attrtype;
	public final String text;
	public final int displaytype;
	public final String spritename;
	public StatusText(cfg.DataStream fs) {
		this.attrtype = fs.getInt();
		this.text = fs.getString();
		this.displaytype = fs.getInt();
		this.spritename = fs.getString();
	}
}
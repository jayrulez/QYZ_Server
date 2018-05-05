package cfg.ectype;
public final class StarConditionInfo extends cfg.CfgObject {
	public final static int TYPEID = -911604277;
	final public int getTypeId() { return TYPEID; }
	public final int condition;
	public final String description;
	public final int value;
	public StarConditionInfo(cfg.DataStream fs) {
		this.condition = fs.getInt();
		this.description = fs.getString();
		this.value = fs.getInt();
	}
}
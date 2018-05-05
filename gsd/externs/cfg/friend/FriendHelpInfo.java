package cfg.friend;
public final class FriendHelpInfo extends cfg.CfgObject {
	public final static int TYPEID = 574848947;
	final public int getTypeId() { return TYPEID; }
	public final String index;
	public final String content;
	public FriendHelpInfo(cfg.DataStream fs) {
		this.index = fs.getString();
		this.content = fs.getString();
	}
}
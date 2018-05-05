package cfg.ui;
public final class QQClubMember extends cfg.CfgObject {
	public final static int TYPEID = -1288078144;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final cfg.cmd.action.Items items;
	public QQClubMember(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.items = new cfg.cmd.action.Items(fs);
	}
}
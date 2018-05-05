package cfg.mail;
public final class Mail extends cfg.CfgObject {
	public final static int TYPEID = 92622500;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int sender;
	public final String title;
	public final String content;
	public Mail(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.sender = fs.getInt();
		this.title = fs.getString();
		this.content = fs.getString();
	}
}
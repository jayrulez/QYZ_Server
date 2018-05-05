package cfg.cmd.condition;
public final class DisplayDlg extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -1510582770;
	final public int getTypeId() { return TYPEID; }
	public final int open;
	public final String dialogname;
	public DisplayDlg(cfg.DataStream fs) {
		super(fs);
		this.open = fs.getInt();
		this.dialogname = fs.getString();
	}
}
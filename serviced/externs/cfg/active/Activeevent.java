package cfg.active;
public final class Activeevent extends cfg.CfgObject {
	public final static int TYPEID = -1403012586;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int eventtype;
	public final String decs;
	public final int addtype;
	public final int times;
	public final int addnum;
	public final String uientry;
	public final int uitabindex;
	public final int uitabindex02;
	public final int displayorder;
	public Activeevent(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.eventtype = fs.getInt();
		this.decs = fs.getString();
		this.addtype = fs.getInt();
		this.times = fs.getInt();
		this.addnum = fs.getInt();
		this.uientry = fs.getString();
		this.uitabindex = fs.getInt();
		this.uitabindex02 = fs.getInt();
		this.displayorder = fs.getInt();
	}
}
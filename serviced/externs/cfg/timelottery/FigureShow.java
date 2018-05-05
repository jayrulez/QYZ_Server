package cfg.timelottery;
public final class FigureShow extends cfg.CfgObject {
	public final static int TYPEID = -400447939;
	final public int getTypeId() { return TYPEID; }
	public final int petID;
	public final String petName;
	public final int figuretype;
	public FigureShow(cfg.DataStream fs) {
		this.petID = fs.getInt();
		this.petName = fs.getString();
		this.figuretype = fs.getInt();
	}
}
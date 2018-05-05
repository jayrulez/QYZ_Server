package cfg.plot;
public final class QTEButton extends cfg.CfgObject {
	public final static int TYPEID = -348249519;
	final public int getTypeId() { return TYPEID; }
	public final int mode;
	public final int count;
	public final int number;
	public final float posx;
	public final float posy;
	public QTEButton(cfg.DataStream fs) {
		this.mode = fs.getInt();
		this.count = fs.getInt();
		this.number = fs.getInt();
		this.posx = fs.getFloat();
		this.posy = fs.getFloat();
	}
}
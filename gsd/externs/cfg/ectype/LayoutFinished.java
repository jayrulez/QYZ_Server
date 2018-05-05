package cfg.ectype;
public final class LayoutFinished extends cfg.ectype.Action {
	public final static int TYPEID = -242305112;
	final public int getTypeId() { return TYPEID; }
	public final int flayoutid;
	public LayoutFinished(cfg.DataStream fs) {
		super(fs);
		this.flayoutid = fs.getInt();
	}
}
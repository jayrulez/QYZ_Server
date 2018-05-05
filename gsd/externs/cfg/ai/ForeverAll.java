package cfg.ai;
public final class ForeverAll extends cfg.ai.Expression {
	public final static int TYPEID = 495453270;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.ai.Expression> exprs = new java.util.ArrayList<>();
	public ForeverAll(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.exprs.add((cfg.ai.Expression)fs.getObject(fs.getString()));
		}
	}
}
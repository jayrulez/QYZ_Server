package cfg.buff;
public final class TransForm extends cfg.buff.Effect {
	public final static int TYPEID = -2026186149;
	final public int getTypeId() { return TYPEID; }
	public final String model;
	public final String model2;
	public final java.util.Map<Integer, Integer> skilllist = new java.util.HashMap<>();
	public TransForm(cfg.DataStream fs) {
		super(fs);
		this.model = fs.getString();
		this.model2 = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.skilllist.put(_k, fs.getInt());
		}
	}
}
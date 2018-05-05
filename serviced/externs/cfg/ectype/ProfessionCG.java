package cfg.ectype;
public final class ProfessionCG extends cfg.ectype.Action {
	public final static int TYPEID = -727611572;
	final public int getTypeId() { return TYPEID; }
	public final java.util.Map<Integer, String> professioncgs = new java.util.HashMap<>();
	public ProfessionCG(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.professioncgs.put(_k, fs.getString());
		}
	}
}
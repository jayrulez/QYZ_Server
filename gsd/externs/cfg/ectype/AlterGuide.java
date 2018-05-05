package cfg.ectype;
public final class AlterGuide extends cfg.ectype.Action {
	public final static int TYPEID = 832528498;
	final public int getTypeId() { return TYPEID; }
	public final String content;
	public final java.util.List<Integer> guideparams = new java.util.ArrayList<>();
	public AlterGuide(cfg.DataStream fs) {
		super(fs);
		this.content = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.guideparams.add(fs.getInt());
		}
	}
}
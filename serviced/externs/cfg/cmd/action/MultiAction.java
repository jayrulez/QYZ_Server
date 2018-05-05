package cfg.cmd.action;
public final class MultiAction extends cfg.cmd.action.Action {
	public final static int TYPEID = -1372141835;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.cmd.action.Action> actions = new java.util.ArrayList<>();
	public MultiAction(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.actions.add((cfg.cmd.action.Action)fs.getObject(fs.getString()));
		}
	}
}
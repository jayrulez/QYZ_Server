package cfg.ai;
public final class ControllerAI extends cfg.ai.AI {
	public final static int TYPEID = 217546592;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> controllers = new java.util.ArrayList<>();
	public ControllerAI(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.controllers.add(fs.getInt());
		}
	}
}
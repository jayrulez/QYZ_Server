package cfg.cmd.action;
public final class Stamina extends cfg.cmd.action.AddExperience {
	public final static int TYPEID = 1851911061;
	final public int getTypeId() { return TYPEID; }
	public Stamina(cfg.DataStream fs) {
		super(fs);
	}
}
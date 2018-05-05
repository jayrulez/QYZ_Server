package cfg.cmd.action;
public final class PetExp extends cfg.cmd.action.AddExperience {
	public final static int TYPEID = 653263512;
	final public int getTypeId() { return TYPEID; }
	public PetExp(cfg.DataStream fs) {
		super(fs);
	}
}
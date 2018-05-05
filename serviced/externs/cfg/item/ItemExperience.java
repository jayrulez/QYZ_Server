package cfg.item;
public final class ItemExperience extends cfg.item.ItemBasic {
	public final static int TYPEID = -949247826;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.AddExperience effect;
	public ItemExperience(cfg.DataStream fs) {
		super(fs);
		this.effect = (cfg.cmd.action.AddExperience)fs.getObject(fs.getString());
	}
}
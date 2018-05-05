package cfg.pet;
public final class PetConsume extends cfg.CfgObject {
	public final static int TYPEID = -1150147338;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> consumeitem = new java.util.ArrayList<>();
	public PetConsume(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.consumeitem.add(fs.getInt());
		}
	}
}
package cfg.skill;
public final class AnimTypeSelector extends cfg.CfgObject {
	public final static int TYPEID = -1549822767;
	final public int getTypeId() { return TYPEID; }
	public final int type;
	public final java.util.List<String> animtypes = new java.util.ArrayList<>();
	public AnimTypeSelector(cfg.DataStream fs) {
		this.type = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.animtypes.add(fs.getString());
		}
	}
}
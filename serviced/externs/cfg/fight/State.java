package cfg.fight;
public final class State extends cfg.CfgObject {
	public final static int TYPEID = -1028202871;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final boolean useinbuff;
	public final String modelpath;
	public final java.util.List<Boolean> abilities = new java.util.ArrayList<>();
	public State(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.useinbuff = fs.getBool();
		this.modelpath = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.abilities.add(fs.getBool());
		}
	}
}
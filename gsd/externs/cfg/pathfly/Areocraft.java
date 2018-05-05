package cfg.pathfly;
public final class Areocraft extends cfg.CfgObject {
	public final static int TYPEID = -1920886917;
	final public int getTypeId() { return TYPEID; }
	public static final String defaultmodelname = "空瑞";
	public static final String startanimname = "voyage";
	public static final String loopanimname = "stand";
	public static final String endanimname = "voyaged";
	public final int id;
	public final String modelname;
	public Areocraft(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.modelname = fs.getString();
	}
}
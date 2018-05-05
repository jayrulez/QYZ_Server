package cfg.dharmakaya;
public final class ClientSource extends cfg.CfgObject {
	public final static int TYPEID = -974867325;
	final public int getTypeId() { return TYPEID; }
	public final String name;
	public final String pics;
	public final cfg.dharmakaya.localpos pos;
	public ClientSource(cfg.DataStream fs) {
		this.name = fs.getString();
		this.pics = fs.getString();
		this.pos = new cfg.dharmakaya.localpos(fs);
	}
}
package cfg.ectype;
public final class MissionCollectMineral extends cfg.CfgObject {
	public final static int TYPEID = -508049504;
	final public int getTypeId() { return TYPEID; }
	public final int mineralid;
	public final int enviroment;
	public final int count;
	public MissionCollectMineral(cfg.DataStream fs) {
		this.mineralid = fs.getInt();
		this.enviroment = fs.getInt();
		this.count = fs.getInt();
	}
}
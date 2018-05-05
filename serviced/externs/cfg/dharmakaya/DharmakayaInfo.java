package cfg.dharmakaya;
public final class DharmakayaInfo extends cfg.CfgObject {
	public final static int TYPEID = 1114288434;
	final public int getTypeId() { return TYPEID; }
	public final int num;
	public final int unlockneedlevel;
	public final int dharmakayatype;
	public final java.util.List<cfg.dharmakaya.ClientSource> clientsource = new java.util.ArrayList<>();
	public DharmakayaInfo(cfg.DataStream fs) {
		this.num = fs.getInt();
		this.unlockneedlevel = fs.getInt();
		this.dharmakayatype = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.clientsource.add(new cfg.dharmakaya.ClientSource(fs));
		}
	}
}
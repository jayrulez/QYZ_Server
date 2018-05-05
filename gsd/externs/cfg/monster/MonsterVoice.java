package cfg.monster;
public final class MonsterVoice extends cfg.CfgObject {
	public final static int TYPEID = 731991670;
	final public int getTypeId() { return TYPEID; }
	public final int voiceid;
	public final String voicetype;
	public final java.util.List<Integer> voicelist = new java.util.ArrayList<>();
	public final float playrate;
	public final float minvolume;
	public final float maxvolume;
	public MonsterVoice(cfg.DataStream fs) {
		this.voiceid = fs.getInt();
		this.voicetype = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.voicelist.add(fs.getInt());
		}
		this.playrate = fs.getFloat();
		this.minvolume = fs.getFloat();
		this.maxvolume = fs.getFloat();
	}
}
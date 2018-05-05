package cfg.audio;
public final class SoundEffects extends cfg.CfgObject {
	public final static int TYPEID = 1066722805;
	final public int getTypeId() { return TYPEID; }
	public final int soundtype;
	public final java.util.List<Integer> soundlist = new java.util.ArrayList<>();
	public SoundEffects(cfg.DataStream fs) {
		this.soundtype = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.soundlist.add(fs.getInt());
		}
	}
}
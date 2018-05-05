package cfg.skill;
public final class SoundEffect extends cfg.skill.Action {
	public final static int TYPEID = -1561350087;
	final public int getTypeId() { return TYPEID; }
	public final float probability;
	public final float volumemin;
	public final float volumemax;
	public final java.util.List<String> pathlist = new java.util.ArrayList<>();
	public SoundEffect(cfg.DataStream fs) {
		super(fs);
		this.probability = fs.getFloat();
		this.volumemin = fs.getFloat();
		this.volumemax = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.pathlist.add(fs.getString());
		}
	}
}
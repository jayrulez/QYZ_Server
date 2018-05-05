package cfg.buff;
public final class Revive extends cfg.buff.Effect {
	public final static int TYPEID = 1174898918;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<String> introduction = new java.util.ArrayList<>();
	public final java.util.List<Float> probability = new java.util.ArrayList<>();
	public final float revivehppercent;
	public final String reviveactionname;
	public final float reviveduration;
	public final int cooldown;
	public Revive(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.introduction.add(fs.getString());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.probability.add(fs.getFloat());
		}
		this.revivehppercent = fs.getFloat();
		this.reviveactionname = fs.getString();
		this.reviveduration = fs.getFloat();
		this.cooldown = fs.getInt();
	}
}
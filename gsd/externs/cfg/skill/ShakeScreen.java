package cfg.skill;
public final class ShakeScreen extends cfg.skill.Action {
	public final static int TYPEID = 1956030411;
	final public int getTypeId() { return TYPEID; }
	public final String type;
	public final int frequency;
	public final float frequencykeepduration;
	public final float frequencyattenuation;
	public final float amplitude;
	public final float amplitudeattenuation;
	public final float life;
	public final float minrange;
	public final float maxrange;
	public ShakeScreen(cfg.DataStream fs) {
		super(fs);
		this.type = fs.getString();
		this.frequency = fs.getInt();
		this.frequencykeepduration = fs.getFloat();
		this.frequencyattenuation = fs.getFloat();
		this.amplitude = fs.getFloat();
		this.amplitudeattenuation = fs.getFloat();
		this.life = fs.getFloat();
		this.minrange = fs.getFloat();
		this.maxrange = fs.getFloat();
	}
}
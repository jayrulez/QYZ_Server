package cfg.buff;
public abstract class IntervalEffect extends cfg.buff.Effect {
	public final float interval;
	public IntervalEffect(cfg.DataStream fs) {
		super(fs);
		this.interval = fs.getFloat();
	}
}
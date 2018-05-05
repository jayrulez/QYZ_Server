package map.buff.effect;

import map.agent.Fighter;

public abstract class IntervalEffect<T extends cfg.buff.IntervalEffect> extends CfgEffect<T> {
	private final long interval;
	private long nextActionTime;
	public IntervalEffect(T ecfg, int skillLevel, Fighter caster, int endType, long endTime) {
		super(ecfg, skillLevel, 1, caster, endType, endTime);
		this.interval = (long)(ecfg.interval * 1000);
		this.nextActionTime = caster.getNow() + interval;
	}

	public IntervalEffect(T ecfg, Fighter caster, int endType, long endTime) {
		super(ecfg, 1, 1, caster, endType, endTime);
		this.interval = (long)(ecfg.interval * 1000);
		this.nextActionTime = caster.getNow() + interval;
	}
	
	@Override
	public boolean onUpdate(long now) {
		if(now >= this.nextActionTime) {
			this.nextActionTime += interval;
			doIntervalAction(now);
		}
		return true;
	}
	
	public abstract void doIntervalAction(long now);

}

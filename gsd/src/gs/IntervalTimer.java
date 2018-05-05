package gs;

public final class IntervalTimer {
	public interface Updater {
		void update();
	}
	private final int interval;
	private long nextUpdateTime;
	private Updater updater;
	
	public IntervalTimer(int interval, Updater updater) {
		this.interval = interval;
		this.updater = updater;
	}
	
	public void trigger(long now) {
		if(now > nextUpdateTime) {
			nextUpdateTime = now + interval;
			updater.update();
		}
	}

}

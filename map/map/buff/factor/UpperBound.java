package map.buff.factor;

public final class UpperBound extends Factor {
	private final double min;
	public UpperBound(int priority, double min) {
		super(priority);
		this.min = min;
	}

	@Override
	public double calc(double curValue, Context ctx) {
		return curValue >= min ? curValue : min;
	}

}

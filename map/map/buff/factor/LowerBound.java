package map.buff.factor;


public final class LowerBound extends Factor {
	private final double max;
	public LowerBound(int priority, double max) {
		super(priority);
		this.max = max;
	}

	@Override
	public double calc(double curValue, Context ctx) {
		return curValue <= max ? curValue : max;
	}

}

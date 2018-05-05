package map.buff.factor;

public final class Multiple extends Factor {
	private final double multiple;
	public Multiple(int priority, double multiple) {
		super(priority);
		this.multiple = multiple;
	}

	@Override
	public double calc(double curValue, Context ctx) {
		return curValue * multiple;
	}
	
}

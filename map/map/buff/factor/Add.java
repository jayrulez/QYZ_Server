package map.buff.factor;

public final class Add extends Factor {
	public Add(int priority, double add) {
		super(priority);
		this.add = add;
	}
	private final double add;
	@Override
	public double calc(double curValue, Context ctx) {
		return curValue + add;
	}
}

package map.buff.factor;

public class Bound extends Factor {
	private final double min;
	private final double max;
	public Bound(int priority, double min, double max) {
		super(priority);
		this.min = min;
		this.max = max;
	}

	@Override
	public double calc(double curValue, Context ctx) {
		if(curValue < min)
			return min;
		else if(curValue > max)
			return max;
		else 
			return curValue;
	}

}

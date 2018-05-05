package map.buff.factor;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Factor {
	private static AtomicLong NextId = new AtomicLong(0);
	
	private final long id;
	public Factor(int priority) {
		this.id = ((long)priority << 48) + NextId.getAndIncrement();
	}
	
	public final long getId() {
		return id;
	}


	public abstract double calc(double curValue, Context ctx);
}

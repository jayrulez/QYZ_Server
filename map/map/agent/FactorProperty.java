package map.agent;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import map.buff.factor.Factor;
import map.buff.factor.Context;

public class FactorProperty {
	private final TreeMap<Long, Factor> factors = new TreeMap<>();

	public double comput(Context ctx) {
		double newValue = 0;
		for(Factor factor : factors.values()) {
			newValue = factor.calc(newValue, ctx);
		}
		return newValue;
	}
	
	public double comput(Factor onceFactor, Context ctx) {
		double value = 0;
		if(factors.isEmpty()) {
			return onceFactor.calc(value, ctx);
		}
		final Iterator<Map.Entry<Long, Factor>> it = factors.entrySet().iterator();
		boolean run = false;
		while(it.hasNext()) {
			Map.Entry<Long, Factor> e = it.next();
			if(e.getKey() > onceFactor.getId() && !run) {
				run = true;
				value = onceFactor.calc(value, ctx);
			}
			value = e.getValue().calc(value, ctx);
		}
		if(!run) {
			value = onceFactor.calc(value, ctx);
		}
		return value;
	}
	
	public double comput(TreeMap<Long, Factor> onceFactors, Context ctx) {
		double newValue = 0;
		final Iterator<Map.Entry<Long, Factor>> it1 = factors.entrySet().iterator();
		final Iterator<Map.Entry<Long, Factor>> it2 = onceFactors.entrySet().iterator();
		
		Map.Entry<Long, Factor> e1 = null, e2 = null;
		while(true) {
			if(e1 == null) {
				if(it1.hasNext()) {
					e1 = it1.next();
				} else {
					if(e2 != null) {
						newValue = e2.getValue().calc(newValue, ctx);
					}
					while(it2.hasNext())
						newValue = it2.next().getValue().calc(newValue, ctx);
					break;
				}
			}
			if(e2 == null) {
				if(it2.hasNext()) {
					e2 = it2.next();
				} else {
					newValue = e1.getValue().calc(newValue, ctx);
					while(it1.hasNext())
						newValue = it1.next().getValue().calc(newValue, ctx);
					break;
				}
			}
			newValue = (e1.getKey() < e2.getKey() ? e1.getValue() : e2.getValue()).calc(newValue, ctx); 
		}
		return newValue;
	}
	
	public void add(Factor factor) {
		factors.put(factor.getId(), factor);
	}
	
	public void remove(Factor factor) {
		factors.remove(factor.getId());
	}
	
	public Factor remove(long fid) {	
		return factors.remove(fid);
	}

	public void clearFactors() {
		factors.clear();
	}

}

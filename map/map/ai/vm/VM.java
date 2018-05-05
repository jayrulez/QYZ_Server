package map.ai.vm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class VM {	

	private long now;
	private final HashMap<Object, Object> ctx = new HashMap<>();
	public Object host;
	
	public Object reg1;
	public Object reg2;
	public Object reg3;
	
	private final Expression main;
	
	private final Map<Integer, List<Expression>> evtListeners = new HashMap<>();
	private enum State {
		RUN,
		END,
		DESTROY
    }
	private State state;
	
	
	public VM(Expression expr, Map<Object, Object> ctx) {
		this.main = expr;
		expr.init(this);
		this.state = State.RUN;
		this.ctx.putAll(ctx);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getData(Object key) {
		return (T)ctx.get(key);
	}
	
	public void putData(Object key, Object value) {
		ctx.put(key, value);
	}
	
	public long getTime() {
		return now;
	}

	public void start() {
		this.state = State.RUN;
		this.main.entered = false;
	}
	
	public boolean isEnd() {
		return this.state == State.END;
	}
	
	public boolean run(long now) {
		this.now = now;
		switch(state) {
			case RUN : if(this.main.execute()) return true; state = State.END;
			case END : 
			case DESTROY:
        }
		return false;
	}
	
	public void destroy() {
		if(state != State.DESTROY) {
			state = State.DESTROY;
			main.onDestroy();
		}
	}
	
	public void register(int evtid, Expression expr) {
		List<Expression> listener = evtListeners.get(evtid);
		if(listener == null) {
			listener = new ArrayList<>();
			listener.add(expr);
		}
		listener.add(expr);
	}
	
	public void trigger(int evtid, Object param) {
		if(state == State.RUN) {
			final List<Expression> listener = evtListeners.get(evtid);
			if(listener != null) {

				for(Iterator<Expression> it = listener.iterator() ; it.hasNext() ;) {
					if(!it.next().onEvt(evtid, param))
						it.remove();
				}
				if(listener.isEmpty())
					evtListeners.remove(evtid);
			}
		}
	}
	
}

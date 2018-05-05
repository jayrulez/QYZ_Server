package map.ai.vm.expr;

import java.util.ArrayList;
import java.util.Collection;

import map.ai.vm.BreakException;
import map.ai.vm.ContinueException;
import map.ai.vm.Expression;
import map.ai.vm.VM;

public final class While extends Expression {
	public While(BooleanExpr condition, Collection<Expression> exprs) {
		assert(!exprs.isEmpty());
		this.condition = condition;
		this.exprs.addAll(exprs);
	}
	
	private final BooleanExpr condition;
	private final ArrayList<Expression> exprs = new ArrayList<>();
	
	private int curIdx;
	private enum State {
		COND,
		BODY,
		END,
	}
	private State state;
	@Override
	public void enter() {
		state = State.COND;
		if(!condition.comput()) {
			state = State.END;
		} else {
			state = State.BODY;
			curIdx = 0;
			for(Expression e : exprs)
				e.entered = false;
		}
	}

	@Override
	public boolean run() {
		switch(state) {
			case COND : {
				if(!condition.comput()) {
					state = State.END;
					return false;
				} else {
					state = State.BODY;
					curIdx = 0;
					for(Expression e : exprs)
						e.entered = false;
					return true;
				}
			}
			case BODY: {
				 while(true) {
						try {
							if(exprs.get(curIdx).execute()) return true;
						} catch(ContinueException ce) {
							curIdx = exprs.size();
						} catch(BreakException be) {
							state = State.END;
							return false;
						}
						
						if(++curIdx >= exprs.size()) {
							state = State.COND;
							return true;
						}
					}
			}
			default : return false;
		}
	}

	@Override
	protected void onInit(VM vm) {
		this.condition.init(vm);
		this.exprs.forEach(e -> e.init(vm));
	}
	
	@Override
	public void onDestroy() {
		this.exprs.forEach(e -> e.onDestroy());
	}

}

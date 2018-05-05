package map.ai.vm.expr;

import java.util.ArrayList;
import java.util.Collection;

import map.ai.vm.BreakException;
import map.ai.vm.Expression;
import map.ai.vm.VM;

/**
 * 
 * @author user
 * @description 按顺序完成多个事件. 可以被break打断.
 *
 */
public final class Sequence extends Expression {
	private int curIdx;
	private final ArrayList<Expression> exprs = new ArrayList<>();
	public Sequence(Collection<Expression> exprs) {
		this.exprs.addAll(exprs);
	}

	@Override
	public void enter() {
		curIdx = 0;
		for(Expression e : exprs) {
			e.entered = false;
		}
	}

	@Override
	public boolean run() {
		try {
			while(curIdx < exprs.size()) {
				final Expression e = exprs.get(curIdx);
				if(e.execute())
					return true;
				else {
					++curIdx;
				}
			}
			return false;
		} catch (BreakException e) {
			return false;
		}
	}

	@Override
	protected void onInit(VM vm) {
		this.exprs.forEach(e -> e.init(vm));
	}
	
	@Override
	public void onDestroy() {
		this.exprs.forEach(e -> e.onDestroy());
	}
}

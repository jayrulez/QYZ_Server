package map.ai.vm.expr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import map.ai.vm.Expression;
import map.ai.vm.VM;

/**
 * 
 * @author user
 * @description 并发开启多个任务.直到所有任务完成才算完成.
 *
 */
public final class Parallel extends Expression {
	private final ArrayList<Expression> exprs = new ArrayList<>();
	private final LinkedList<Expression> unfinishedExprs = new LinkedList<>();
	public Parallel(Collection<Expression> exprs) {
		this.exprs.addAll(exprs);
	}
	
	@Override
	public void enter() {
		unfinishedExprs.clear();
		unfinishedExprs.addAll(exprs);
		for(Expression e : unfinishedExprs) {
			e.entered = false;
		}
	}

	@Override
	public boolean run() {
		for(Iterator<Expression> it = unfinishedExprs.iterator() ; it.hasNext() ; ) {
			final Expression e = it.next();
			if(!e.execute()) {
				it.remove();
			}
		}
		return !unfinishedExprs.isEmpty();
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

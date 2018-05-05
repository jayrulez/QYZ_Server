package map.ai.vm.expr;

import java.util.ArrayList;
import java.util.Collection;

import map.ai.vm.Expression;
import map.ai.vm.VM;

public final class ForeverAll extends Expression {
	private final ArrayList<Expression> exprs = new ArrayList<>();
	public ForeverAll(Collection<Expression> exprs) {
		this.exprs.addAll(exprs);
	}
	
	@Override
	public void enter() {
		for(Expression e : exprs) {
			e.entered = false;
		}
	}

	@Override
	public boolean run() {
		for(Expression e : exprs) {
			if(!e.execute()) {
				e.entered = false;
			}
		}
		return true;
	}

	@Override
	protected void onInit(VM vm) {
		this.exprs.forEach(e -> e.init(vm));
	}

}

package map.ai.vm.expr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import map.ai.vm.Expression;
import map.ai.vm.VM;

/**
 * 
 * @author user
 * @description 选择某个满足条件的事件执行.如果都不满足.则不执行任何.
 *
 */
public final class Switch extends Expression {
	public static class Case {
		public final List<BooleanExpr> conditions;
		public final Expression expr;
		public Case(List<BooleanExpr> cs, Expression e) {
			this.conditions = cs;
			this.expr = e;
		}
	}
	
	public Switch(Collection<Case> cases, boolean untilAnyMatch) {
		this.cases.addAll(cases);
		this.untilAnyMatch = untilAnyMatch;
	}
	
	private boolean untilAnyMatch;
	private Case curCase;
	private final ArrayList<Case> cases = new ArrayList<>();
	@Override
	public void enter() {
		curCase = null;
		for(Case c : cases) {
			c.expr.entered = false;
		}
	}

	@Override
	public boolean run() {
		if(curCase == null) {
			for(Case c : cases) {
				if(c.conditions.stream().allMatch(cond -> cond.comput())) {
					curCase = c;
					break;
				}
			}
			if(curCase == null)
				return untilAnyMatch;
		}
		
		return curCase.expr.execute();
	}

	@Override
	protected void onInit(VM vm) {
		this.cases.forEach(c -> {
			c.conditions.forEach(cond -> cond.init(vm));
			c.expr.init(vm);
		});
	}
	
	@Override
	public void onDestroy() {
		this.cases.forEach(c -> c.expr.onDestroy());
	}
}

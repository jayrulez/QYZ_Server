package map.map.story.bool;

import cfg.ectype.CheckVariator;
import map.map.story.Story;

public final class CompareEnviroment extends CfgBool<cfg.ectype.CheckVariator> {

	public CompareEnviroment(CheckVariator mcfg) {
		super(mcfg);
	}

	@Override
	public boolean comput() {
		final Story map = (Story)vm.host;
		final int value = map.getEnvVar(mcfg.name);
		final int cmpValue = mcfg.value;
		switch(mcfg.op) {
		case cfg.ectype.CompareOperator.EQUAL :
			return value == cmpValue;
		case cfg.ectype.CompareOperator.GREATER:
			return value > cmpValue;
		case cfg.ectype.CompareOperator.LESS:
			return value < cmpValue;
		case cfg.ectype.CompareOperator.GREATER_OR_EQUAL:
			return value >= cmpValue;
		case cfg.ectype.CompareOperator.LESS_OR_EQUAL:
			return value <= cmpValue;
		default: 
			throw new RuntimeException("unknown CompareOperator:" + mcfg.op);
		}
	}

}

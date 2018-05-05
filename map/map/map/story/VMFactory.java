package map.map.story;

import cfg.ectype.FreshGuide;
import cfg.ectype.ProfessionCG;
import map.ai.vm.expr.action.Delay;
import cfg.ectype.ShowGlobalTips;
import cfg.ectype.SpecialEffect;
import map.map.story.action.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public final class VMFactory {
	public static map.ai.vm.VM createVM(List<cfg.ectype.Action> actions) {

		return new map.ai.vm.VM(createParallel(actions), new HashMap<Object, Object>());
	}

	static map.ai.vm.Expression createParallel(List<cfg.ectype.Action> actions) {
		return new map.ai.vm.expr.Parallel(createActions(actions));
	}

	static map.ai.vm.expr.BooleanExpr createCondition(cfg.ectype.ExeCondition condition) {
		switch(condition.getTypeId()) {
//		case cfg.ectype.KillMonsters.TYPEID: {
//			final cfg.ectype.KillMonsters c = (cfg.ectype.KillMonsters)condition;
//			return new KillMonsters(c.mapid, c.count);
//		}
		default :
			throw new RuntimeException("unknown Ectype Condition:" + condition);
		}
	}

	static List<map.ai.vm.expr.BooleanExpr> createConditions(List<cfg.ectype.ExeCondition> conditions) {
		return conditions.stream().map(c -> createCondition(c)).collect(Collectors.toList());
	}

	static List<map.ai.vm.Expression> createActions(List<cfg.ectype.Action> actions) {
		return actions.stream().map(a -> createAction(a)).collect(Collectors.toList());
	}

	public static List<map.ai.vm.expr.Switch.Case> createCases(List<cfg.ectype.Case> cases) {
		return cases.stream().map(c -> new map.ai.vm.expr.Switch.Case(createConditions(c.conditions),
			createAction(c.action))).collect(Collectors.toList());
	}

	static map.ai.vm.Expression createAction(cfg.ectype.Action action) {
		switch(action.getTypeId()) {
			/*
			 * 控制节点
			 */
			case cfg.ectype.Parallel.TYPEID : {
				final cfg.ectype.Parallel ac = (cfg.ectype.Parallel)action;
				return new map.ai.vm.expr.Parallel(createActions(ac.actions));
			}
			case cfg.ectype.Sequence.TYPEID: {
				final cfg.ectype.Sequence ac = (cfg.ectype.Sequence)action;
				return new map.ai.vm.expr.Sequence(createActions(ac.actions));
			}
			case cfg.ectype.Switch.TYPEID: {
				final cfg.ectype.Switch ac = (cfg.ectype.Switch)action;
				return new map.ai.vm.expr.Switch(createCases(ac.cases), false);
			}
			case cfg.ectype.SwitchUntil.TYPEID: {
				final cfg.ectype.SwitchUntil ac = (cfg.ectype.SwitchUntil)action;
				return new map.ai.vm.expr.Switch(createCases(ac.cases), true);
			}


			/*
			 *  普通原子动作节点
			 */
			case cfg.ectype.Enter.TYPEID: {
				return new map.map.story.action.ChangeEctypeEntry((cfg.ectype.Enter)action);
			}
			case cfg.ectype.Exit.TYPEID: {
				return new map.map.story.action.ChangeEctypeExit((cfg.ectype.Exit)action);
			}
			case cfg.ectype.ControllerOperation.TYPEID: {
				return new ChangeController((cfg.ectype.ControllerOperation)action);
			}
			case cfg.ectype.EnviromentOperate.TYPEID: {
				return new OperateEnviroment((cfg.ectype.EnviromentOperate)action);
			}
			case cfg.ectype.Delay.TYPEID: {
				return new Delay((int)(((cfg.ectype.Delay)action).time * 1000));
			}
			case cfg.ectype.LayoutFinished.TYPEID: {
				return new CompleteEctypeCurLayout();
			}
			case cfg.ectype.KillMonster.TYPEID: {
				return new KillMonster((cfg.ectype.KillMonster)action);
			}
			case cfg.ectype.AlterGuide.TYPEID: {
				return new AlterGuide((cfg.ectype.AlterGuide)action);
			}
			case cfg.ectype.ResumeGuide.TYPEID: {
				return new ResumeGuide((cfg.ectype.ResumeGuide)action);
			}
			case cfg.ectype.Move.TYPEID: {
				return new Move((cfg.ectype.Move)action);
			}
			case cfg.ectype.HPCheck.TYPEID: {
				return new HPCheck((cfg.ectype.HPCheck)action);
			}
            case cfg.ectype.Transmit.TYPEID: {
                return new Transmit((cfg.ectype.Transmit)action);
            }
			case cfg.ectype.AddDescription.TYPEID:
			case SpecialEffect.TYPEID: {
                return new NotEndClientAction(action);
            }
			case ShowGlobalTips.TYPEID: {
				return new map.map.story.action.ShowGlobalTips((ShowGlobalTips)action);
			}
			case cfg.ectype.Dialog.TYPEID: {
				final cfg.ectype.Dialog c = (cfg.ectype.Dialog)action;
				return new ClientAction(action, c.isstop);
			}
			case cfg.ectype.PathFinding.TYPEID:
			case cfg.ectype.PlayAudio.TYPEID:
			case cfg.ectype.ShapeShift.TYPEID:
			case cfg.ectype.GFX.TYPEID:
			case cfg.ectype.PlayerEffect.TYPEID:{
				return new ClientAction(action, false);
			}
			case cfg.ectype.CharacterAction.TYPEID:
			case cfg.ectype.PlayCG.TYPEID:
            case ProfessionCG.TYPEID:
			case FreshGuide.TYPEID:{
				return new ClientAction(action, true);
			}
			default :
				throw new RuntimeException("unknown Ectype TargetInfo:" + action);
		}

	}
	

}

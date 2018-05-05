package lx.gs.task;

import cfg.task.EFinishSpecialEventType;
import cfg.task.EPlayingCGType;
import lx.gs.task.condition.*;
import lx.gs.task.condition.Condition.ConditionParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public final class Task {
	private final cfg.task.Task taskCfg;

	private final HashMap<Integer, ArrayList<Condition>> completeConditionsByEventType = new HashMap<>();
	private final ArrayList<Condition> completeConditions = new ArrayList<>();

	private cfg.task.FinishSpecialEventData finishEctype = null;
    private boolean acceptPlayCG = false;
    private boolean completePlayCG = false;
    public Set<Integer> needKillMonsters = new HashSet<>();
	
	public Task(cfg.task.Task taskCfg) {
		this.taskCfg = taskCfg;
		final cfg.task.TaskComplete complete = taskCfg.complete;

		///////////////////////////// 完成条件
		if(complete.rolelevel != 0)
			addCompleteCondition(new LevelLimit(complete.rolelevel, 0));
		if(!complete.collectitem.isEmpty()) {
			addCompleteCondition(new ItemLimit(complete.collectitem_itemid));
		}
		
		for(cfg.task.KillMonsterData m : complete.killmonster) {
			if(m.dropitemcount > 0) {
				addCompleteCondition(new KillMonsterDropItem(m.monsterid, m.dropitemid, m.dropitemcount, m.probability));
                needKillMonsters.add(m.monsterid);
			} else {
				addCompleteCondition(new KillMonster(m.monsterid, m.monstercount));
                needKillMonsters.add(m.monsterid);
			}
		}

		for(cfg.task.FinishSpecialEventData event : complete.finishspecialevent) {
			switch (event.eventtype) {
				case EFinishSpecialEventType.MINING: {
					addCompleteCondition(new Collect(event.id, event.count));
					break;
				}
				case EFinishSpecialEventType.PLAYING_CG: {
                    if(event.playcgtype == EPlayingCGType.WHEN_ACCEPTING){
                        acceptPlayCG = true;
                    } else if(event.playcgtype == EPlayingCGType.WHEN_COMPLETING){
                        completePlayCG = true;
                    }
					break;
				}
				case EFinishSpecialEventType.USING_SKILL: {

					break;
				}
				case EFinishSpecialEventType.DOING_ECTYPE: {
					addCompleteCondition(new FinishEctype(event.id, event.count));
					finishEctype = event;
					break;
				}
				default:
					throw new RuntimeException("unknown task complete.finishspecialevent:" + event);
			}
		}
	}

	private void addCompleteCondition(Condition condition) {
		completeConditions.add(condition);
		ArrayList<Condition> conds = completeConditionsByEventType.get(condition.getEventId());
		if(conds == null) {
			conds = new ArrayList<>();
			completeConditionsByEventType.put(condition.getEventId(), conds);
		}
		conds.add(condition);
	}
	public ArrayList<Condition> getCompleteConditionsByType(int eventId){
		return completeConditionsByEventType.get(eventId);
	}
	
	public int getTaskid() {
		return taskCfg.id;
	}
	
	public cfg.task.Task getTaskCfg() {
		return taskCfg;
	}
	
	public boolean needLogHistory() {
		return taskCfg.basic.finishedtaskcount;
	}

    public boolean isAcceptPlayCG(){
        return acceptPlayCG;
    }
    public boolean isCompletePlayCG(){
        return completePlayCG;
    }

	public cfg.task.FinishSpecialEventData getNeedFinishEctypeCfg() {
		return finishEctype;
	}

	public boolean checkComplete(xbean.RoleTask info, xbean.TaskData data) {
		return this.completeConditions.stream().allMatch(a -> a.check(info, data));
	}
	
	public void onEvent(xbean.RoleTask info, xbean.TaskData data, ConditionParam param) {
		final ArrayList<Condition> conds = completeConditionsByEventType.get(param.eventid);
		if(conds != null) {
			conds.forEach(c -> c.onEvent(info, data, param));
		}

	}
}

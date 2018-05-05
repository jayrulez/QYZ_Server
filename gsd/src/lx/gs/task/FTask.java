package lx.gs.task;

import cfg.CfgMgr;
import cfg.currency.CurrencyType;
import cfg.item.EItemBindType;
import cfg.task.ShowHideData;
import cfg.task.ShowHideGroup;
import common.Bonus;
import common.ErrorCode;
import lx.gs.achievement.FAchievement;
import lx.gs.bonus.FBonus;
import lx.gs.family.msg.SCallAllFamilyMembers;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.map.FMap;
import lx.gs.role.title.FTitle;
import lx.gs.task.condition.Collect;
import lx.gs.task.condition.Condition;
import lx.gs.task.condition.Condition.ConditionParam;
import lx.gs.task.condition.EventIds;
import lx.gs.task.msg.*;
import map.msg.XPlayCGSetState;
import map.msg.XPlayerChangeTask;
import xdb.Transaction;

import java.lang.System;
import java.util.*;
import java.util.stream.Collectors;





public final class FTask {
	public final static int FAMILY_NPCID = CfgMgr.familytaskconfig.npcid;
	public final static int ACCEPT_TASK = 1;
	public final static int COMPLETE_TASK = 2;
	public final static int FINISH_TASK = 3;
	public final static int FAIL_TASK = 4;
	
	public static xbean.RoleTask getTask(long roleid) {
		xbean.RoleTask info = xtable.Roletask.get(roleid);
		if(info == null) {
			info = xbean.Pod.newRoleTask();
			info.setRoleid(roleid);
			info.setMinhistoryexpiretime(Long.MAX_VALUE);
			xtable.Roletask.add(roleid, info);
		}
//          else {
//			final long now = System.currentTimeMillis();
//			if(info.getMinhistoryexpiretime() <= now) {
//				final Map<Integer, xbean.TaskHistory> history = info.getHistorys();
//				history.values().removeIf(h -> h.getExpiretime() <= now);
//				info.setMinhistoryexpiretime(history.values().stream().mapToLong(h -> h.getExpiretime()).min().orElse(Long.MAX_VALUE));
//			}
//		}
		return info;
	}

	public static ErrorCode checkAcceptTask(long roleid, xbean.RoleTask info, int npcid, int taskid) {
		final Task task = TaskModule.tasks.get(taskid);
		if(task == null)
			return ErrorCode.NOT_EXIST_TASKID;
		final cfg.task.Task taskCfg = task.getTaskCfg();

		if(isAccept(info, taskid)){
            xdb.Trace.info("Roleid = {} has accept task, taskid = {}", roleid, taskid);
            return ErrorCode.IN_TASK_LIST;
//            return ErrorCode.OK;
        }

		final cfg.task.TaskBase base = taskCfg.basic;

		if(base.finishedtaskcount) {
			// TODO 是否全局计数
			if(base.countlimit > 0) {
				if(getCompleteCount(info, taskid) >= base.countlimit)
					return ErrorCode.EXCEED_MAX_TASK_FINISH_NUM;
			}
		}

		final cfg.task.TaskAccept accept = taskCfg.accept;
		///////////////////////////// 接取条件
		//接取等级限制
		if(accept.rolelevelmin > 0 || accept.rolelevelmax > 0) {
			final int level = xtable.Roleinfos.selectLevel(roleid);
			if(accept.rolelevelmin > 0 && level < accept.rolelevelmin)
				return ErrorCode.NOT_ENOUGH_LEVEL;
			if(accept.rolelevelmax > 0 && level > accept.rolelevelmax)
				return ErrorCode.LEVEL_TOO_HIGH;
		}
		if(accept.finishanyonepretask) {
			if(accept.pretaskid.stream().allMatch(id -> getCompleteCount(info, id) <= 0)) {
				return ErrorCode.PRETASK_NOT_FINISH;
			}
		} else {
			if(accept.pretaskid.stream().anyMatch(id -> getCompleteCount(info, id) <= 0)) {
				return ErrorCode.PRETASK_NOT_FINISH;
			}
		}

		if(accept.mutextaskid.stream().anyMatch(id -> getCompleteCount(info, id) > 0))
			return ErrorCode.MUTEXTASK_FINISH;

		if(base.tasktype != 3){//如果不是环任务,需要额外做一些检查
			if(taskCfg.accept.npcid != npcid)
				return ErrorCode.NOT_CUR_NPC_TASK;
			if(isNewBranchTask(task, info)){
                info.getAllcandobranch().remove(taskid); //如果是接取支线任务第一个，从能接的支线任务列表中移除
				int acceptBranchs = info.getAcceptbranchtasks();
				if(acceptBranchs >= 5){//同时接取的支线任务数量限制
					return ErrorCode.EXCEED_BRANCH_NUMS;
				}
				info.setAcceptbranchtasks(acceptBranchs + 1);
			}
			controlNpcShow(task, info.getShownpcs(), ACCEPT_TASK);
		}
		uncheckAddTask(info, task);
		controlMinesShow(roleid, task, info, ACCEPT_TASK);
        info.getAccepttasktime().put(taskid, System.currentTimeMillis());
        if(task.isAcceptPlayCG()){
            FMap.dispatchMessageInProcedure(roleid, new XPlayCGSetState(taskid));
        }

        FLogger.starttask(roleid, FBonus.getRoleInfo(roleid), base.tasktype, taskid);
		return ErrorCode.OK;
	}

	/**
	 * 判断是否是新的支线任务;
	 * @param task
	 * @param info
	 * @return
	 */
	public static boolean isNewBranchTask(Task task, xbean.RoleTask info){
		cfg.task.TaskBase baCfg = task.getTaskCfg().basic;
		if(baCfg.tasktype != 2){//这里支线任务type用2来表示的 见csv\common\tasktype,主线任务的type为1
			return false;
		}
		cfg.task.TaskAccept acCfg = task.getTaskCfg().accept;
		for(int i : acCfg.pretaskid){
			Task temp = TaskModule.tasks.get(i);
			if(temp.getTaskCfg().basic.tasktype == baCfg.tasktype){//如果前一个任务包含同种类型,说明不是支线任务的开头
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断两个支线任务是否是同一条线
	 * @param taskid1
	 * @param taskid2
	 * @return
	 */
	public static boolean isSameBranchTask(int taskid1, int taskid2){
        return findBranchStartId(taskid1) == findBranchStartId(taskid2);
    }
	
	/**
	 * 查找支线任务的开头
	 * @param taskid
	 * @return
	 */
	public static int findBranchStartId(int taskid){
		Task task = TaskModule.tasks.get(taskid);
		for(int i : task.getTaskCfg().accept.pretaskid){
			Task temp = TaskModule.tasks.get(i);
			if(temp.getTaskCfg().basic.tasktype == 2){//递归找开始，如果前置任务列表中，没有支线任务类型了，那么该任务为支线任务开始
				return findBranchStartId(i);
			}
		}
		return taskid;
	}
	
	
	public static ErrorCode checkCompleteTask(long roleid, xbean.RoleTask info, int npcid, int taskid) {
		final Task task = TaskModule.tasks.get(taskid);
		if(task.getTaskCfg().complete.npcid != npcid)
			return ErrorCode.NOT_CUR_NPC_TASK;
		final xbean.TaskData data = info.getTasks().remove(taskid);
		if(data == null)
			return ErrorCode.NOT_IN_TASK_LIST;
		if(!task.checkComplete(info, data))
			return ErrorCode.TASK_NOT_COMPLETED;
		//记录主线，支线最后完成的任务id
		Map<Integer, Integer> lastComplete = info.getLastcompletetaskid();
		cfg.task.TaskAccept acCfg = task.getTaskCfg().accept;
		cfg.task.TaskBase baCfg = task.getTaskCfg().basic;
		if(baCfg.tasktype == 2 && TaskModule.lastTasks.contains(taskid)){//如果完成了一条支线任务
			info.setAcceptbranchtasks(info.getAcceptbranchtasks() - 1);
		}
		if(acCfg.pretaskid.isEmpty()){//如果前置任务id为空，说明为第一个
			lastComplete.put(taskid, 1);
		}else {
			for(int i : acCfg.pretaskid){
				Task temp = TaskModule.tasks.get(i);
				if(temp.getTaskCfg().basic.tasktype != baCfg.tasktype){
					continue;
				}
				lastComplete.remove(i);//如果包含同类型的前一个，移除
				break;
			}
            lastComplete.put(taskid, 1);
		}
		if(!onObtainTaskReward(info, task, data)){
			return ErrorCode.BAG_FULL;
		}
		controlNpcShow(task, info.getShownpcs(), FINISH_TASK);
		controlMinesShow(roleid, task, info, FINISH_TASK);
        checkBrachCando(roleid, info);
        long starttime = info.getAccepttasktime().getOrDefault(taskid, 0L);
        info.getAccepttasktime().remove(taskid);
        if(task.isCompletePlayCG()){
            FMap.dispatchMessageInProcedure(roleid, new XPlayCGSetState(taskid));
        }
        FLogger.endtask(roleid, FBonus.getRoleInfo(roleid), baCfg.tasktype, taskid, System.currentTimeMillis()- starttime);
		return ErrorCode.OK;
	}

    public static void checkBrachCando(long roleid, xbean.RoleTask info){
        List<Integer> newAdd = new ArrayList<>();
        Set<Integer> allCando = info.getAllcandobranch();
        for(int i : TaskModule.firstTasks){
            //完成了的以及接取中的支线任务不放在里面
            if(allCando.contains(i) || getCompleteCount(info, i) > 0 || isAccept(info, i)){
                continue;
            }
            cfg.task.TaskAccept accept = TaskModule.tasks.get(i).getTaskCfg().accept;
            //接取等级限制
            if(accept.rolelevelmin > 0 || accept.rolelevelmax > 0) {
                final int level = xtable.Roleinfos.selectLevel(roleid);
                if(accept.rolelevelmin > 0 && level < accept.rolelevelmin)
                    continue;
                if(accept.rolelevelmax > 0 && level > accept.rolelevelmax)
                    continue;
            }
            //接取前置任务限制
            if(accept.finishanyonepretask) {
                if(accept.pretaskid.stream().allMatch(id -> getCompleteCount(info, id) <= 0)) {
                    continue;
                }
            } else {
                if(accept.pretaskid.stream().anyMatch(id -> getCompleteCount(info, id) <= 0)) {
                    continue;
                }
            }
            newAdd.add(i);
        }
        if(!newAdd.isEmpty()){
            allCando.addAll(newAdd);
            SAddNewBranchTaskNotify notify = new SAddNewBranchTaskNotify();
            notify.newtask.addAll(newAdd);
            xdb.Transaction.tsendWhileCommit(roleid, notify);
        }
    }

	public static ErrorCode checkCancelTask(xbean.RoleTask info, int taskid) {
		final xbean.TaskData data = info.getTasks().remove(taskid);
		final Task task = TaskModule.tasks.get(taskid);
		if(task.getTaskCfg().basic.tasktype == 2){//如果是取消支线任务
			assert(info.getAcceptbranchtasks() > 0);
			info.setAcceptbranchtasks(info.getAcceptbranchtasks() - 1);
		}
		if(data == null)
			return ErrorCode.NOT_IN_TASK_LIST;
		onCancelTask(info, data);
		controlNpcShow(task, info.getShownpcs(), FAIL_TASK);
		return ErrorCode.OK;
	}

	public static void onEvent(long roleid, int eventid, int id) {
		onEvent(roleid, eventid, id, 1);
	}
	public static void onEvent(long roleid, int eventid, int id, int num) {
		xbean.RoleTask info = getTask(roleid);
//		xdb.Trace.info("Task.OnEvent roleid:{} eventid:{} id:{} num:{}", info.getRoleid(), eventid, id, num);
		ConditionParam param = new ConditionParam();
		param.eventid = eventid;
		param.id = id;
		param.num = num;
		info.getTasks().values().forEach(t -> {
			final Task task = TaskModule.tasks.get(t.getTaskid());
			task.onEvent(info, t, param);
		});
	}

	// 任务变量
	public static void setVariable(xbean.RoleTask info, int key, int value) {
		final Map<Integer, Integer> vars = info.getVariables();
		if(value != 0) {
			vars.put(key, value);
		} else {
			vars.remove(key);
		}
	}
	
	static int getVariable(xbean.RoleTask info, int key) {
		return info.getVariables().getOrDefault(key, 0);
	}
	
	public static int addVariable(xbean.RoleTask info, int key, int add) {
		final Map<Integer, Integer> vars = info.getVariables();
		final int newValue = vars.getOrDefault(key, 0) + add;
		vars.put(key, newValue);
		return newValue;
	}
	
	public static void clearVariable(xbean.RoleTask info, int key) {
		info.getVariables().remove(key);
	}	

	// 完成记录
	public static void addCompleteCount(xbean.RoleTask info, int taskid) {
		final Map<Integer, xbean.TaskHistory> historys = info.getHistorys();
		xbean.TaskHistory h = historys.get(taskid);
		if(h == null) {
			h = xbean.Pod.newTaskHistory();
			// TODO 保存时间待配置
            //warning 引起前置任务未完成的bug
//			final long expireTime = common.Time.tomorrowZeroTime();
			h.setTaskid(taskid);
//			h.setExpiretime(expireTime);
			historys.put(taskid, h);
//			if(expireTime < info.getMinhistoryexpiretime())
//				info.setMinhistoryexpiretime(expireTime);
			FAchievement.checkCompleteEqual(info.getRoleid(), cfg.achievement.CounterType.TASK_MAINLINE, taskid);
		}
		h.setCount(h.getCount() + 1);
	}
	
	public static int getCompleteCount(xbean.RoleTask info, int taskid) {
		final xbean.TaskHistory h = info.getHistorys().get(taskid);
		return h != null ? h.getCount() : 0; 
	}
	
	public static boolean isAccept(xbean.RoleTask info, int taskid) {
		return info.getTasks().containsKey(taskid);
	}

	public static String gmAddTask(long roleid, int taskid) {
		final Task task = TaskModule.tasks.get(taskid);
		if(task == null)
			return "任务不存在";
		final xbean.RoleTask info = getTask(roleid);
		if(isAccept(info, taskid))
			return "任务已接取";
		uncheckAddTask(info, task);
        controlNpcShow(task, info.getShownpcs(), ACCEPT_TASK);
        Set<Integer> lastcompletes = new HashSet<>();
        lastcompletes.addAll(info.getLastcompletetaskid().keySet());
        int toaddTaskType = task.getTaskCfg().basic.tasktype;
        if(toaddTaskType == 1) {//增加主线任务需要移除所有的最后完成主线任务
            for (int i : lastcompletes) {
                Task temp = TaskModule.tasks.get(i);
                if (temp.getTaskCfg().basic.tasktype == toaddTaskType) {
                    info.getLastcompletetaskid().remove(i);
                }
                break;
            }
        }
		return "成功";
	}

	private static void uncheckAddTask(xbean.RoleTask info, Task task) {
		final int taskid = task.getTaskid();
		final xbean.TaskData data = xbean.Pod.newTaskData();
		data.setTaskid(taskid);
		final xbean.TaskData old = info.getTasks().put(taskid, data);
		assert(old == null);
		onAcceptTask(info, task, data);
	}
	
	private static void onAcceptTask(xbean.RoleTask info, Task task, xbean.TaskData data) {
		final int taskid = task.getTaskid();
		xdb.Trace.info("AcceptTask. player:{} taskid:{}", info.getRoleid(), taskid);
        syncTaskToMap(info.getRoleid(), new XPlayerChangeTask(new HashSet<Integer>(getTaskMonsters(info))));
		// TODO onAccept
	}
	
	private static boolean onObtainTaskReward(xbean.RoleTask info, Task task, xbean.TaskData data) {
		final int taskid = task.getTaskid();

		final long roleid = info.getRoleid();
		final cfg.task.Task tcfg = task.getTaskCfg();
		final map.msg.Bonus bonus = new map.msg.Bonus();
		bonus.bindtype = EItemBindType.BOUND;
		cfg.task.TaskReward reward = tcfg.reward;
		if(reward.money > 0) {
			bonus.items.put(CurrencyType.XuNiBi, (int)reward.money);
		}
		if(reward.exp > 0) {
			bonus.items.put(CurrencyType.JingYan, (int)reward.exp);
		}
		final cfg.task.RewardItem item = reward.rewarditem;
		if(item.randomselectoneitem) {
			double totalProb = 0;
			for(float prob : item.itemprob) {
				totalProb += prob;
			}
			double randomProb = common.Utils.random01() * totalProb;
			for(int i = 0 ; i < item.itemprob.size() ; i++) {
				if((randomProb -= item.itemprob.get(i)) <= 1e-6) {
					common.Utils.addValue(bonus.items, item.itemid.get(i), item.itemcount.get(i));
					break;
				}
			}
		} else {
			for(int i = 0 ; i < item.itemid.size() ; i++) {
				if(common.Utils.random01() < item.itemprob.get(i)) {
					common.Utils.addValue(bonus.items, item.itemid.get(i), item.itemcount.get(i));
				}
			}
		}
		if(reward.factionexp > 0) {

		}
		if(reward.titleid > 0) {
			if(!reward.deletetitle) {
				FTitle.unlockTitle(roleid, reward.titleid);
			} else {
			}
		}
		if(reward.taskid > 0) {
			FTask.uncheckAddTask(info, TaskModule.tasks.get(reward.taskid));
		}
        int profession = xtable.Roleinfos.selectProfession(roleid);
        for(cfg.task.ItemInfo itemInfo : reward.professionrewarditem){
            if(itemInfo.professionbelongs == profession){
                common.Utils.addValue(bonus.items, itemInfo.itemid, itemInfo.itemcount);
            }
        }
        for(cfg.task.SkillInfo skillInfo : reward.rewardskill){
            //TODO 任务奖励技能待配
        }


		if(!FBonus.addBonus(roleid, bonus, By.Task)){
			return false;
		}
		if(task.needLogHistory())
			addCompleteCount(info, taskid);
		xdb.Transaction.tsendWhileCommit(roleid, new SCompleteTask(tcfg.complete.npcid, taskid));
		xdb.Trace.info("ObtainTaskReward. player:{} taskid:{}", info.getRoleid(), taskid);
		return true;
	}

	private static void onCancelTask(xbean.RoleTask info, xbean.TaskData data) {
		final int taskid = data.getTaskid();
		xdb.Trace.info("CancelTask. player:{} taskid:{}", info.getRoleid(), taskid);


		// TODO 删除任务发放的道具物品
	}

	public static TaskInfo convert(xbean.TaskData data) {
		final TaskInfo info = new TaskInfo();
		info.taskid = data.getTaskid();
		info.counter.putAll(data.getCounter());
		return info;
	}
	
	public static FamilyTaskInfo convertFam(xbean.FamilyTaskDetail data){
		final FamilyTaskInfo info = new FamilyTaskInfo();
		info.npcid = data.getNpcid();
		info.taskid = data.getTaskid();
		return info;
	}

	public static xio.Protocol createInfo(xbean.RoleTask info) {
		final STask re = new STask();
		re.variables.putAll(info.getVariables());
//		info.getHistorys().values().forEach(h -> re.completehistory.put(h.getTaskid(), h.getCount()));
		re.completehistory.putAll(info.getLastcompletetaskid());
		info.getTasks().values().forEach(t -> re.acceptedtasks.add(convert(t)));
		info.getAcceptedfamilytasks().forEach(f -> re.curfamtasks.add(convertFam(f)));
		re.completefamtasknum = info.getCompletednumsinfamtasks();
		re.comdaycycle = info.getDailycompletedfamtasks();
		re.comweeksmallcycle = info.getWeekcompletedsmallfamtasks();
		re.lastgiveuofamtime = info.getLastgiveupfamtasktime();
		re.shownpcs.addAll(info.getShownpcs());
		re.hidemines.addAll(info.getHidemines());
		re.isuseyuanbaocomtask = info.getIsuseyuanbao();
		re.iscanclefamtask = info.getIscancletask();
		re.receivedweekbonus.addAll(info.getWeekspebonus());
        //登录的时候检查一下支线任务
        Set<Integer> doneBranchs = new HashSet<>();
        for(int i : info.getAllcandobranch()){
            if(getCompleteCount(info, i) > 0 || isAccept(info, i)){
                doneBranchs.add(i);
            }
        }
        info.getAllcandobranch().removeAll(doneBranchs);
        if(getCompleteCount(info, info.getGuidebranchtaskid()) > 0){
            info.setGuidebranchtaskid(0);
        }
        re.guidebranchtaskid = info.getGuidebranchtaskid();
        re.allcandobranch.addAll(info.getAllcandobranch());
        CfgMgr.moduleunlockcond.values().forEach(e -> {
            if(getCompleteCount(info, e.opentaskid) > 0){
                re.unlockcomtasks.add(e.opentaskid);
            }
        });
		return re;
	}
	
	public static void clearFamilyTaskInfo(xbean.RoleTask taskInfo){//每日和每周清空环任务信息,每日凌晨12点清除前一天的家族环任务。玩家需重新接取，并从头做起。
		taskInfo.setDailycompletedfamtasks(0);
		final int curCompleteFamTasksNums = taskInfo.getCompletednumsinfamtasks();
		if(taskInfo.getAcceptedfamilytasks().size() > 0){ //如果接了家族环任务
			if(curCompleteFamTasksNums < taskInfo.getAcceptedfamilytasks().size()){//有可能出现放弃任务，导致完成数量和列表长度相同
				final int taskid = taskInfo.getAcceptedfamilytasks().get(curCompleteFamTasksNums).getTaskid();
				taskInfo.getTasks().remove(taskid); //清除正在做的任务map中包含家族环的任务
			}
		}
		taskInfo.setCompletednumsinfamtasks(0);
		taskInfo.getAcceptedfamilytasks().clear();
		taskInfo.setIsuseyuanbao(0);
		taskInfo.setIscancletask(0);
	}
	
	/**
	 * 随机产生环任务中每环的具体任务，一下产生4个
	 * @param rolelvl
	 * @param taskInfo
	 */
	public static void genFamilyTasks(int rolelvl, xbean.RoleTask taskInfo){
		java.util.List<Integer> levels = CfgMgr.familytasklib.keySet().stream().filter(p-> p <= rolelvl).collect(Collectors.toList());
		final int tasklvl = Collections.max(levels);
		cfg.family.FamilyTaskLib conf = CfgMgr.familytasklib.get(tasklvl);
		final int frontTaskSize = conf. familytasklist.size();
		final int frontTaskNums = CfgMgr.familytaskconfig.circletaskamount - 1;
		//随机产生4个下标，指向不同的任务和npc的组合
		for(int idx : common.Utils.getMutiRandom(0, frontTaskSize, frontTaskNums)){
			final cfg.family.FamilyTask tcfg = conf.familytasklist.get(idx);
			final xbean.FamilyTaskDetail familyTask = xbean.Pod.newFamilyTaskDetail();
			familyTask.setTaskid(tcfg.task);
			familyTask.setNpcid(tcfg.npc);
			taskInfo.getAcceptedfamilytasks().add(familyTask);
		}
		final int lastTaskIndex = common.Utils.randomRange(0, conf.completetasklist.size());
		xbean.FamilyTaskDetail lastTask = xbean.Pod.newFamilyTaskDetail();
		lastTask.setTaskid(conf.completetasklist.get(lastTaskIndex).task);
		lastTask.setNpcid(conf.completetasklist.get(lastTaskIndex).npc);
		taskInfo.getAcceptedfamilytasks().add(lastTask); //最后的任务在家族npc处进行交接
	}
	
	/**
	 * 随机产生一环任务，和已经接取的不能重复
	 * @param rolelvl
	 * @param taskInfo
	 */
	public static xbean.FamilyTaskDetail genOneFamilytask(int rolelvl, xbean.RoleTask taskInfo){
		xbean.FamilyTaskDetail genfamilyTask = xbean.Pod.newFamilyTaskDetail();
		List<xbean.FamilyTaskDetail> acceptTasks = taskInfo.getAcceptedfamilytasks();
		java.util.List<Integer> levels = CfgMgr.familytasklib.keySet().stream().filter(p-> p <= rolelvl).collect(Collectors.toList());
		final int tasklvl = Collections.max(levels);
		cfg.family.FamilyTaskLib conf = CfgMgr.familytasklib.get(tasklvl);
        int completeNums = taskInfo.getCompletednumsinfamtasks();
        int acceptLen = acceptTasks.size();
        if(acceptLen > completeNums){
            for(int i = acceptLen - 1; i >= completeNums; i--){
                acceptTasks.remove(i);
            }
        }
		if(CfgMgr.familytaskconfig.circletaskamount - acceptTasks.size() <= 1){//如果只差最后一环了
			final int lastTaskIndex = common.Utils.randomRange(0, conf.completetasklist.size());
			genfamilyTask.setTaskid(conf.completetasklist.get(lastTaskIndex).task);
			genfamilyTask.setNpcid(conf.completetasklist.get(lastTaskIndex).npc);
		}else{
			int size = conf.familytasklist.size();
            int cycle = 100;
			while( cycle > 0 ){//随机产生一个不和已有的任务重复的任务，npcid和taskid都不能重复
				boolean contain = false;
				int index = common.Utils.randomRange(0, size);
				cfg.family.FamilyTask tcfg = conf.familytasklist.get(index);
				for(xbean.FamilyTaskDetail ftd : acceptTasks){
					if(tcfg.npc == ftd.getNpcid() || tcfg.task == ftd.getTaskid()){
						contain = true;
						break;
					}
				}
				if(!contain){
					genfamilyTask.setNpcid(tcfg.npc);
					genfamilyTask.setTaskid(tcfg.task);
					break;
				}
                cycle--;
			}
		}
		acceptTasks.add(genfamilyTask);
		return genfamilyTask;
	}

    /*
    给策划测试用
    生成下一个环任务，按顺序取
     */
    public static xbean.FamilyTaskDetail genOneFamilytaskForTest(int rolelvl, xbean.RoleTask taskInfo){
        xbean.FamilyTaskDetail genfamilyTask = xbean.Pod.newFamilyTaskDetail();
        List<xbean.FamilyTaskDetail> acceptTasks = taskInfo.getAcceptedfamilytasks();
        java.util.List<Integer> levels = CfgMgr.familytasklib.keySet().stream().filter(p-> p <= rolelvl).collect(Collectors.toList());
        final int tasklvl = Collections.max(levels);
        cfg.family.FamilyTaskLib conf = CfgMgr.familytasklib.get(tasklvl);
        if(CfgMgr.familytaskconfig.circletaskamount - acceptTasks.size() == 1){//如果只差最后一环了
            int lastIndex = taskInfo.getFamilylasttaskorder().getOrDefault(tasklvl, 0);
            if(lastIndex == conf.completetasklist.size() - 1){
                lastIndex = 0;
            }else {
                lastIndex++;
            }
            genfamilyTask.setTaskid(conf.completetasklist.get(lastIndex).task);
            genfamilyTask.setNpcid(conf.completetasklist.get(lastIndex).npc);
            taskInfo.getFamilylasttaskorder().put(tasklvl, lastIndex);
        }else{
            int size = conf.familytasklist.size();
            int prefourIndex = taskInfo.getFamilytaskorder().getOrDefault(tasklvl, 0);
            if(prefourIndex == size - 1){
                prefourIndex = 0;
            }else {
                prefourIndex++;
            }
            cfg.family.FamilyTask tcfg = conf.familytasklist.get(prefourIndex);
            genfamilyTask.setNpcid(tcfg.npc);
            genfamilyTask.setTaskid(tcfg.task);
            taskInfo.getFamilytaskorder().put(tasklvl, prefourIndex);
        }
        acceptTasks.add(genfamilyTask);
        return genfamilyTask;
    }
	
	public static void uncheckAddTask(xbean.RoleTask info, int taskid) {
		final xbean.TaskData data = xbean.Pod.newTaskData();
		data.setTaskid(taskid);
		final xbean.TaskData old = info.getTasks().put(taskid, data);
		assert(old == null);
	} 
	
	/**
	 * 检查环任务完成情况
	 */
	public static ErrorCode checkCompleteFamTask(xbean.RoleTask taskInfo, int taskid, int curtaskorder, int npcid) {
		cfg.family.FamilyTaskConfig conf = CfgMgr.familytaskconfig;
		if(conf.circletaskamount == curtaskorder){ //如果是环任务的最后一环,清空环任务信息
			if(npcid != FTask.FAMILY_NPCID){
				return ErrorCode.NOT_FAMILY_NPC;
			}
			completeCircle(taskInfo);
		}else{//否则设置完成环数
			taskInfo.setCompletednumsinfamtasks(curtaskorder);
		}

		final Task task = TaskModule.tasks.get(taskid);
		final xbean.TaskData data = taskInfo.getTasks().remove(taskid); //移除当前提交完成的任务，检查任务完成情况
		if(data == null){
			return ErrorCode.NOT_IN_TASK_LIST;
		}
//		xdb.Trace.info("family task data is = {}", data.getCounter().toString());
		if(!task.checkComplete(taskInfo, data)) {
			return ErrorCode.TASK_NOT_COMPLETED;
		}
		return ErrorCode.OK;
	}
	/**
	 * 完成一组环任务
	 * @param taskInfo
	 */
	public static void completeCircle(xbean.RoleTask taskInfo){
		taskInfo.setCompletednumsinfamtasks(0);
		taskInfo.getAcceptedfamilytasks().clear();
		taskInfo.setDailycompletedfamtasks(taskInfo.getDailycompletedfamtasks() + 1);
	}
	
	/**
	 * 完成家族环任务后发放奖励
	 * @param roleid
	 * @param curtaskorder
	 * @param taskInfo
	 */
	public static boolean comFamilyTaskAward(long roleid, int curtaskorder,int taskid, xbean.RoleTask taskInfo){
		cfg.family.FamilyTaskConfig conf = CfgMgr.familytaskconfig;
		java.util.List<Float> curList = conf.taskbonusrate;
		java.util.List<Float> cycleList= conf.circlebonusrate;
		int cycleIndex;
		if (curtaskorder == conf.circletaskamount) {//注意每次到第五环完成数会加1，这里需要回退1
			cycleIndex = taskInfo.getDailycompletedfamtasks() - 1;
		} else {
			cycleIndex = taskInfo.getDailycompletedfamtasks();
		}
		float cycleRate = cycleList.get(cycleIndex);//这里是从0开始的
//        float cycleRate = cycleList.get(cycleIndex % 4); //测试用，保证取到奖励
		float mutiple = cycleRate * curList.get(curtaskorder - 1);//taskorder是从1开始的

		final int rolelvl = xtable.Roleinfos.get(roleid).getLevel();
		cfg.family.FamilyTaskReward bonusConf = CfgMgr.familytaskreward.get(rolelvl);
		final int currency = Math.round(bonusConf.gold * mutiple);
		final int exp = Math.round(bonusConf.exp * mutiple);
		SCompleteFamilyTask response = new SCompleteFamilyTask();
		map.msg.Bonus norBonus = response.bonus;
		norBonus.items.put(CurrencyType.XuNiBi, currency);
		norBonus.items.put(CurrencyType.JingYan, exp);

		if(curtaskorder == conf.circletaskamount){//如果是完成了环任务中的最后一环，要发放额外奖励
			cfg.family.FamilyTaskSpecReward speConf = CfgMgr.familytaskspecreward.get(rolelvl);
			norBonus.items.put(CurrencyType.XuNiBi,speConf.gold);
			norBonus.items.put(CurrencyType.JingYan, speConf.exp);
			FBonus.genBonus(roleid, speConf.exbonus, 1, common.Bonus.BindType.BIND, norBonus);
		}else {//否则生成下一个要做的任务
            xbean.FamilyTaskDetail newTask = genOneFamilytask(rolelvl, taskInfo);
            if(newTask.getTaskid() == 0){
                return false;
            }
//            xbean.FamilyTaskDetail newTask = genOneFamilytaskForTest(rolelvl, taskInfo);
			response.nextfamtask.npcid = newTask.getNpcid();
			response.nextfamtask.taskid = newTask.getTaskid();
		}
		response.taskid = taskid;
		response.curtaskorder = curtaskorder;
		response.comdaycycle = taskInfo.getDailycompletedfamtasks();
		response.completefamtasknum = taskInfo.getCompletednumsinfamtasks();
		response.comweeksmallcycle = taskInfo.getWeekcompletedsmallfamtasks();
		xdb.Transaction.tsendWhileCommit(roleid, response);
		return FBonus.addBonus(roleid, norBonus, By.Family_Task);
	}
	
	/**
	 * 一键清环奖励
	 * @param roleid
	 * @param taskInfo
	 * @param toClearNum 清理的小环数目
	 * @param toClearNum 清理的小环数目
	 */
	public static boolean clearFamilyTaskAward(long roleid, xbean.RoleTask taskInfo, int toClearNum, SClearFamTask response){
		cfg.family.FamilyTaskConfig conf = CfgMgr.familytaskconfig;
		java.util.List<Float> curList = conf.taskbonusrate;
		java.util.List<Float> cycleList= conf.circlebonusrate;
		int cycle = (toClearNum - 1)/conf.circletaskamount + 1;
		float mutiple = 0;
		for(int i = conf.simpleclearamount - toClearNum + 1; i <= conf.simpleclearamount; i++){
			mutiple += curList.get((i-1) % conf.circletaskamount) * cycleList.get((i-1) / conf.circletaskamount);
		}
		final int rolelvl = xtable.Roleinfos.get(roleid).getLevel();
		cfg.family.FamilyTaskReward bonusConf = CfgMgr.familytaskreward.get(rolelvl);
		final int currency = Math.round(bonusConf.gold * mutiple);
		final int exp = Math.round(bonusConf.exp * mutiple);
		map.msg.Bonus norBonus = response.bonus;
		norBonus.items.put(CurrencyType.XuNiBi, currency);
		norBonus.items.put(CurrencyType.JingYan, exp);//基础奖励
		
		cfg.family.FamilyTaskSpecReward speConf = CfgMgr.familytaskspecreward.get(rolelvl);
		norBonus.items.put(CurrencyType.XuNiBi,speConf.gold * cycle);
		norBonus.items.put(CurrencyType.JingYan, speConf.exp *cycle);
		FBonus.genBonus(roleid, speConf.exbonus, cycle, common.Bonus.BindType.BIND, norBonus);//额外奖励
		
		return FBonus.addBonus(roleid, norBonus, By.Family_Task);

	}
	
	public static void controlNpcShow(Task task, Set<Integer> npcShowInfo, int type){
		ShowHideData datas = task.getTaskCfg().basic.npcshowhide;
		switch (type) {
		case ACCEPT_TASK:{
			for(ShowHideGroup group : datas.showhide){
				if(group.showhideaccept){
					npcShowInfo.addAll(group.allid);
				}else {
					npcShowInfo.removeAll(group.allid);
				}
			}
			return;
		}
		case COMPLETE_TASK:
		case FINISH_TASK:{
			for(ShowHideGroup group : datas.showhide){
				if(group.showhidefinish){
					npcShowInfo.addAll(group.allid);
				}else {
					npcShowInfo.removeAll(group.allid);
				}
			}
			return;
		}
		case FAIL_TASK:{
			for(ShowHideGroup group : datas.showhide){
				if(group.showhidefail){
					npcShowInfo.addAll(group.allid);
				}else {
					npcShowInfo.removeAll(group.allid);
				}
			}
			break;
		}
		default:
			break;
		}
	}

	/**
	 * 查找任务中的矿物id
	 * @param task
	 * @return
	 */
	public static Set<Integer> getMinesIdByTask(Task task){
		Set<Integer> allMinesId = new HashSet<>();
		ArrayList<Condition> allConditionMines = task.getCompleteConditionsByType(EventIds.COLLECT);
		if(allConditionMines == null){
			return allMinesId;
		}
		allConditionMines.forEach(m -> {
			Collect c = (Collect) m;
			allMinesId.add(c.getMineid());
		});
		return allMinesId;
	}

	public static void controlMinesShow(long roleid, Task task, xbean.RoleTask taskInfo , int type){
		Set<Integer> allCurMinesId = getMinesIdByTask(task);//获取和该任务有关的所有矿物id
		if(allCurMinesId.size() == 0){
			return;
		}
		Set<Integer> hideMines = taskInfo.getHidemines();
		switch (type){
			case ACCEPT_TASK:{
				hideMines.removeAll(allCurMinesId);
                SCancelHideMinesNotify notify = new SCancelHideMinesNotify();
                notify.unhideminse.addAll(allCurMinesId);
                Transaction.tsendWhileCommit(roleid, notify);
				return;
			}
			case FINISH_TASK:{
				Set<Integer> otherMinesId = new HashSet<>();
				for(int i : taskInfo.getTasks().keySet()){
					Task temp = TaskModule.tasks.get(i);
					otherMinesId.addAll(getMinesIdByTask(temp));
				}
				//如果其它任务也包含该矿物id，那么要先过滤，只隐藏都不需要的矿
				hideMines.addAll(allCurMinesId.stream().filter(i -> !otherMinesId.contains(i)).collect(Collectors.toList()));
				return;
			}
			default:
				break;
		}
	}

    public static Set<Integer> getTaskMonsters(xbean.RoleTask roleTask){
        Set<Integer> monsters = new HashSet<>();
        roleTask.getTasks().values().forEach(t -> {
            Task task = TaskModule.tasks.get(t.getTaskid());
            monsters.addAll(task.needKillMonsters);
        });
        return monsters;
    }

    //完成任务的时候貌似没有必要同步
    public static void syncTaskToMap(long roleid, XPlayerChangeTask msg){
        FMap.dispatchMessageInProcedure(roleid, msg);
    }

}

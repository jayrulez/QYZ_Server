package lx.gs.task;

/**
 *   @author HuangQiang
 *   @createtime 2015年11月12日 上午10:37:09
 */

import cfg.CfgMgr;
import gnet.link.Onlines;
import lx.gs.bonus.FBonus;
import lx.gs.task.msg.SChangeHistory;
import lx.gs.task.msg.SChangeTask;
import lx.gs.task.msg.SChangeVariable;
import lx.gs.task.msg.SDailyResetFamTaskNotify;
import xdb.Procedure;
import xdb.logs.Listener;
import xdb.logs.Note;

import java.util.*;

public enum TaskModule implements gs.Module, gs.RoleCreateListener, gs.RoleLoginListener,gs.RoleDayOverListener, gs.LevelUpListener {
	INSTANCE;
	
	public final static HashMap<Integer, Task> tasks = new HashMap<>();//type 1:主线，2：支线；3 环任务
	public final static Set<Integer> lastTasks = new HashSet<>();//支线任务的最后一个任务
    public final static Set<Integer> firstTasks = new HashSet<>();//支线任务的第一个任务
	
	@Override
	public void onDayOver(long roleid) {
		new Procedure() {
			@Override
			protected boolean process() {
				xbean.RoleTask taskInfo = FTask.getTask(roleid);
				FTask.clearFamilyTaskInfo(taskInfo);
				final common.Time.TimeInfo expireInfo = common.Time.getTimeInfo();
				if(expireInfo.dayZeroTime == expireInfo.weekZeroTime){//如果跨周了
					taskInfo.setWeekcompletedsmallfamtasks(0);
					taskInfo.getWeekspebonus().clear();//周特殊奖励重置
				}
				SDailyResetFamTaskNotify notify = new SDailyResetFamTaskNotify();
				notify.comdaycycle = taskInfo.getDailycompletedfamtasks();
				notify.comweeksmallcycle = taskInfo.getWeekcompletedsmallfamtasks();
				xdb.Transaction.tsendWhileCommit(roleid, notify);
				return true;
			}
		}.execute();
		
	}

	@Override
	public void afterRoleLoginInProcedure(long roleid) {
        xbean.RoleTask taskInfo = FTask.getTask(roleid);
		xdb.Transaction.tsendWhileCommit(roleid, FTask.createInfo(taskInfo));
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {
		
	}

	@Override
    public void start() {
        // createCache tasks.
        CfgMgr.task.values().forEach(t -> tasks.put(t.id, new Task(t)));
        tasks.values().forEach(t -> {
            if (t.getTaskCfg().basic.tasktype == 2) {
                lastTasks.add(t.getTaskid());
            }
        });
        for(int i : lastTasks){
            Task conf = tasks.get(i);
            List<Integer> pretasks = conf.getTaskCfg().accept.pretaskid;
            if(pretasks.isEmpty() || pretasks.stream().allMatch(id -> tasks.get(id).getTaskCfg().basic.tasktype == 1)){
                firstTasks.add(i);//支线任务第一个任务的前置任务为空，或者为主线任务
            }
        }
        tasks.values().forEach(t -> {//筛选出所有支线任务的最后一个任务id
            for (int i : t.getTaskCfg().accept.pretaskid) {
                lastTasks.remove(i);
            }
        });
        registerListners();
    }

    void registerListners() {
		xtable.Roletask.getTable().addListener(new Listener() {

			@Override
			public void onChanged(Object key) {	}

			@Override
			public void onRemoved(Object key) { }

			@Override
			public void onChanged(Object key, String fullVarName, Note note) {
				final Long roleid = (Long)key;
				final Map<Integer, xbean.TaskHistory> historys = xtable.Roletask.get(roleid).getHistorys();
				@SuppressWarnings("unchecked")
				final xdb.logs.NoteMap<Integer, xbean.TaskHistory> nmap = (xdb.logs.NoteMap<Integer, xbean.TaskHistory>)note;
				final SChangeHistory re = new SChangeHistory();
				for(Integer taskid : nmap.getAdded()) {
					re.changes.put(taskid, historys.get(taskid).getCount());
				}
				for(xbean.TaskHistory h : nmap.getChanged()) {
					if(!re.changes.containsKey(h.getTaskid()))
						re.changes.put(h.getTaskid(), h.getCount());
				}
				for(Integer taskid : nmap.getReplaced().keySet()) {
					if(!re.changes.containsKey(taskid))
						re.changes.put(taskid, historys.get(taskid).getCount());
				}
				for(xbean.TaskHistory h : nmap.getRemoved().values()) {
					re.removes.add(h.getTaskid());
				}
				Onlines.getInstance().send(roleid, re);
			}
			
		}, "value", "historys");
		
		xtable.Roletask.getTable().addListener(new Listener() {

			@Override
			public void onChanged(Object key) {}

			@Override
			public void onRemoved(Object key) {}

			@Override
			public void onChanged(Object key, String fullVarName, Note note) {
				final Long roleid = (Long)key;
				final Map<Integer, Integer> vars = xtable.Roletask.get(roleid).getVariables();
				@SuppressWarnings("unchecked")
				xdb.logs.NoteMap<Integer, Integer> nmap = (xdb.logs.NoteMap<Integer, Integer>)note;
				final SChangeVariable re = new SChangeVariable();
				for(Integer varid : nmap.getAdded()) {
					re.changes.put(varid, vars.get(varid));
				}
				for(Integer varid : nmap.getRemoved().keySet()) {
					re.removes.add(varid);
				}
				for(Integer varid : nmap.getReplaced().keySet()) {
					if(!re.changes.containsKey(varid))
						re.changes.put(varid, vars.get(varid));
				}
				Onlines.getInstance().send(roleid, re);
					
			}
			
		}, "value", "variables");
		
		xtable.Roletask.getTable().addListener(new Listener() {//监听器的使用

			@Override
			public void onChanged(Object key) {}

			@Override
			public void onRemoved(Object key) {}

			@Override
			public void onChanged(Object key, String fullVarName, Note note) {
				final Long roleid = (Long)key;
				final Map<Integer, xbean.TaskData> tasks = xtable.Roletask.get(roleid).getTasks();
				@SuppressWarnings("unchecked")
				xdb.logs.NoteMap<Integer, xbean.TaskData> nmap = (xdb.logs.NoteMap<Integer, xbean.TaskData>)note;
				HashSet<Integer> changes = new HashSet<>();
				for(Integer taskid : nmap.getAdded()) {//新增的值
					changes.add(taskid);
					Onlines.getInstance().send(roleid, new SChangeTask(FTask.convert(tasks.get(taskid))));
				}
				for(xbean.TaskData data : nmap.getChanged()) {//通过get，返回的引用发生了变化
					if(!changes.contains(data.getTaskid())) {
						changes.add(data.getTaskid());
						Onlines.getInstance().send(roleid, new SChangeTask(FTask.convert(data)));
					}
				}
				for(Integer taskid : nmap.getReplaced().keySet()) {//被put调用替换的值
					if(!changes.contains(taskid))
						Onlines.getInstance().send(roleid, new SChangeTask(FTask.convert(tasks.get(taskid))));
				}
			}
			
		}, "value", "tasks");
	}


	@Override
	public void onRoleCreateInProcedure(long roleid) {
		final xbean.RoleTask info = FTask.getTask(roleid);
		for(int taskid : CfgMgr.roleconfig.createroletasks) {
			FTask.uncheckAddTask(info, taskid);
		}
	}

	@Override
	public void onRoleDeleteInProcedure(long roleid) {

	}


    @Override
    public void onLevelUp(long roleid, int oldLevel, int newLevel) {
        FTask.checkBrachCando(roleid, FTask.getTask(roleid));
    }
}

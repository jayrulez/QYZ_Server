
package xbean;

public interface RoleTask extends xdb.Bean {
	public RoleTask copy(); // deep clone
	public RoleTask toData(); // a Data instance
	public RoleTask toBean(); // a Bean instance
	public RoleTask toDataIf(); // a Data instance If need. else return this
	public RoleTask toBeanIf(); // a Bean instance If need. else return this

	public long getRoleid(); // 
	public long getMinhistoryexpiretime(); // 
	public java.util.Map<Integer, xbean.TaskHistory> getHistorys(); // 
	public java.util.Map<Integer, xbean.TaskHistory> getHistorysAsData(); // 
	public java.util.Map<Integer, Integer> getLastcompletetaskid(); // 记录上一个完成任务的id，包括主线和支线任务
	public java.util.Map<Integer, Integer> getLastcompletetaskidAsData(); // 记录上一个完成任务的id，包括主线和支线任务
	public int getAcceptbranchtasks(); // 记录接取的支线任务条数
	public java.util.Set<Integer> getShownpcs(); // 要显式的npcid集合
	public java.util.Set<Integer> getShownpcsAsData(); // 要显式的npcid集合
	public java.util.Set<Integer> getHidemines(); // 要隐藏的矿物集合
	public java.util.Set<Integer> getHideminesAsData(); // 要隐藏的矿物集合
	public java.util.Map<Integer, xbean.TaskData> getTasks(); // 
	public java.util.Map<Integer, xbean.TaskData> getTasksAsData(); // 
	public java.util.Map<Integer, Integer> getVariables(); // 
	public java.util.Map<Integer, Integer> getVariablesAsData(); // 
	public java.util.List<xbean.FamilyTaskDetail> getAcceptedfamilytasks(); // 当前接收的环任务概览
	public java.util.List<xbean.FamilyTaskDetail> getAcceptedfamilytasksAsData(); // 当前接收的环任务概览
	public int getCompletednumsinfamtasks(); // 每组环任务中完成的环数
	public int getIsuseyuanbao(); // 是否使用了元宝来直接完成环任务，0，否；1，是
	public int getIscancletask(); // 是否取消过任务,0，否；1，是
	public int getDailycompletedfamtasks(); // 每天完成的环任务组数
	public int getWeekcompletedsmallfamtasks(); // 每周完成的环任务组数
	public java.util.List<Integer> getWeekspebonus(); // 已经领取的周计数任务奖励
	public java.util.List<Integer> getWeekspebonusAsData(); // 已经领取的周计数任务奖励
	public long getLastgiveupfamtasktime(); // 上次放弃家族任务的时间
	public int getGuidebranchtaskid(); // 主界面上当前显示的支线任务
	public java.util.Map<Integer, Long> getAccepttasktime(); // 接取任务时的时间,记日志用
	public java.util.Map<Integer, Long> getAccepttasktimeAsData(); // 接取任务时的时间,记日志用
	public java.util.Map<Integer, Integer> getFamilytaskorder(); // 测试环任务用，按顺序取环任务
	public java.util.Map<Integer, Integer> getFamilytaskorderAsData(); // 测试环任务用，按顺序取环任务
	public java.util.Map<Integer, Integer> getFamilylasttaskorder(); // 测试环任务用，取最后一环
	public java.util.Map<Integer, Integer> getFamilylasttaskorderAsData(); // 测试环任务用，取最后一环
	public java.util.Set<Integer> getAllcandobranch(); // 所有能做的支线任务的第一个任务id
	public java.util.Set<Integer> getAllcandobranchAsData(); // 所有能做的支线任务的第一个任务id

	public void setRoleid(long _v_); // 
	public void setMinhistoryexpiretime(long _v_); // 
	public void setAcceptbranchtasks(int _v_); // 记录接取的支线任务条数
	public void setCompletednumsinfamtasks(int _v_); // 每组环任务中完成的环数
	public void setIsuseyuanbao(int _v_); // 是否使用了元宝来直接完成环任务，0，否；1，是
	public void setIscancletask(int _v_); // 是否取消过任务,0，否；1，是
	public void setDailycompletedfamtasks(int _v_); // 每天完成的环任务组数
	public void setWeekcompletedsmallfamtasks(int _v_); // 每周完成的环任务组数
	public void setLastgiveupfamtasktime(long _v_); // 上次放弃家族任务的时间
	public void setGuidebranchtaskid(int _v_); // 主界面上当前显示的支线任务
}

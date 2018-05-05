
package lx.gs.task.msg;

import cfg.CfgMgr;
import cfg.active.EventNum;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.logger.FLogger;
import lx.gs.map.FMap;
import lx.gs.task.FTask;
import lx.gs.task.TaskModule;
import map.msg.XPlayerChangeTask;

import java.util.HashSet;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCompleteFamilyTask__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCompleteFamilyTask extends __CCompleteFamilyTask__ {
	@Override
	protected void process() {
		new AProcedure<CCompleteFamilyTask>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				//先区分是使用元宝直接完成还是正常完成
				//还要区分是小环结束还是大环结束
				xbean.RoleTask taskInfo = FTask.getTask(roleid);
				if(curtaskorder != taskInfo.getCompletednumsinfamtasks() + 1){
                    xdb.Trace.error("Roleid = {} complete family task err, current order is = {}, has complete = {}", roleid, curtaskorder, taskInfo.getCompletednumsinfamtasks());
					return error(ErrorCode.FAMTASK_ORDER_WRONG);
				}
				xbean.FamilyTaskDetail curTask = taskInfo.getAcceptedfamilytasks().get(curtaskorder - 1);
				if(curTask.getNpcid() != npcid || curTask.getTaskid() != taskid){
					return error(ErrorCode.NOT_FAMILY_TASK);
				}
				if(taskInfo.getIsuseyuanbao() == 1){//如果是直接用元宝完成的
					final int actdoingTaskid = curTask.getTaskid();
					xbean.TaskData data = taskInfo.getTasks().remove(actdoingTaskid);//移除实际正在做的任务
					if(data == null){
						return error(ErrorCode.NOT_IN_TASK_LIST);
					}
					if(curtaskorder == CfgMgr.familytaskconfig.circletaskamount){//如果是最后一环
						if(npcid != FTask.FAMILY_NPCID){
							return error(ErrorCode.NOT_FAMILY_NPC);
						}
						FTask.completeCircle(taskInfo);
					}else {//设置完成次数
						taskInfo.setCompletednumsinfamtasks(curtaskorder);
					}
					taskInfo.setIsuseyuanbao(0);
				}else{//否则检查条件
					final ErrorCode ret = FTask.checkCompleteFamTask(taskInfo, taskid, curtaskorder, npcid);
					if(ret.err()){
						return error(ret);
					}
				}
				taskInfo.setWeekcompletedsmallfamtasks(taskInfo.getWeekcompletedsmallfamtasks() + 1);//周计数直接加1
				//发放奖励，生产下一个奖励
				if(!FTask.comFamilyTaskAward(roleid, curtaskorder, taskid, taskInfo)){
					return false;
				}
				final lx.gs.task.Task task = TaskModule.tasks.get(taskid);
				FTask.controlMinesShow(roleid, task, taskInfo, FTask.FINISH_TASK);
                long starttime = taskInfo.getAccepttasktime().getOrDefault(taskid, 0L);
                taskInfo.getAccepttasktime().remove(taskid);
                FDailyActivity.addActiveScores(roleid, EventNum.RINGTASK);
                FLogger.endtask(roleid, FBonus.getRoleInfo(roleid), task.getTaskCfg().basic.tasktype, taskid, System.currentTimeMillis() - starttime);
//                FTask.syncTaskToMap(roleid, new XPlayerChangeTask(new HashSet<Integer>(FTask.getTaskMonsters(taskInfo))));

                return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6570045;

	public int getType() {
		return 6570045;
	}

	public int curtaskorder; // 当前提交的是第几环
	public int taskid;
	public int npcid;

	public CCompleteFamilyTask() {
	}

	public CCompleteFamilyTask(int _curtaskorder_, int _taskid_, int _npcid_) {
		this.curtaskorder = _curtaskorder_;
		this.taskid = _taskid_;
		this.npcid = _npcid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(curtaskorder);
		_os_.marshal(taskid);
		_os_.marshal(npcid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		curtaskorder = _os_.unmarshal_int();
		taskid = _os_.unmarshal_int();
		npcid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CCompleteFamilyTask) {
			CCompleteFamilyTask _o_ = (CCompleteFamilyTask)_o1_;
			if (curtaskorder != _o_.curtaskorder) return false;
			if (taskid != _o_.taskid) return false;
			if (npcid != _o_.npcid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += curtaskorder;
		_h_ += taskid;
		_h_ += npcid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(curtaskorder).append(",");
		_sb_.append(taskid).append(",");
		_sb_.append(npcid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CCompleteFamilyTask _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = curtaskorder - _o_.curtaskorder;
		if (0 != _c_) return _c_;
		_c_ = taskid - _o_.taskid;
		if (0 != _c_) return _c_;
		_c_ = npcid - _o_.npcid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


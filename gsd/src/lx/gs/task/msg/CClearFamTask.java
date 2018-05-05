
package lx.gs.task.msg;

import cfg.CfgMgr;
import cfg.active.EventNum;
import cfg.family.FamilyTaskConfig;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.logger.By;
import lx.gs.task.FTask;
import map.msg.XPlayerChangeTask;
import xbean.FamilyTaskDetail;
import xbean.RoleTask;
import xbean.TaskData;

import java.util.HashSet;
import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CClearFamTask__ extends xio.Protocol { }

/** 一键清环，使用元宝完成前十环任务，不用去家族提交,当天完成已经超过十环，不能一键清
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CClearFamTask extends __CClearFamTask__ {
	@Override
	protected void process() {
		new AProcedure<CClearFamTask>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				RoleTask taskInfo = FTask.getTask(roleid);
				FamilyTaskConfig conf = CfgMgr.familytaskconfig;
				int completeTasks = taskInfo.getDailycompletedfamtasks() * conf.circletaskamount + taskInfo.getCompletednumsinfamtasks();
				if(completeTasks >= conf.simpleclearamount){
					return error(ErrorCode.CAN_NOT_CLEAR_FAMILY_TASK);
				}
				int toClearNum = conf.simpleclearamount - completeTasks;
				ErrorCode ret = FCondition.checkA(roleid, conf.completecost, toClearNum, By.Family_Task, -1, -1);
				if(ret.err()){
					return error(ret);
				}
				int cycle = (toClearNum - 1)/conf.circletaskamount + 1;
				taskInfo.setDailycompletedfamtasks(taskInfo.getDailycompletedfamtasks() + cycle);
				taskInfo.setWeekcompletedsmallfamtasks(taskInfo.getWeekcompletedsmallfamtasks() + toClearNum);
				taskInfo.setIscancletask(0);
				taskInfo.setIsuseyuanbao(0);
				List<FamilyTaskDetail> accepts = taskInfo.getAcceptedfamilytasks();
				//发放一键清环奖励
				SClearFamTask response = new SClearFamTask();
				if(!FTask.clearFamilyTaskAward(roleid, taskInfo, toClearNum, response)){
					return false;
				}
				if(accepts.size() != taskInfo.getCompletednumsinfamtasks()){//如果接受的长度和完成的数目不一样,表示有正在做的环任务，需要移除
					int curTaskId = accepts.get(taskInfo.getCompletednumsinfamtasks()).getTaskid();
					TaskData data = taskInfo.getTasks().remove(curTaskId);
					if(data == null){
						return error(ErrorCode.NOT_IN_TASK_LIST);
					}
				}
				accepts.clear();
				taskInfo.setCompletednumsinfamtasks(0);
                FDailyActivity.addActiveScores(roleid, EventNum.RINGTASK, toClearNum);
				taskInfo.getTasks().values().forEach(t -> response.acceptedtasks.add(FTask.convert(t)));
				taskInfo.getAcceptedfamilytasks().forEach(f -> response.curfamtasks.add(FTask.convertFam(f)));
				response.completefamtasknum = taskInfo.getCompletednumsinfamtasks();
				response.comdaycycle = taskInfo.getDailycompletedfamtasks();
				response.comweeksmallcycle = taskInfo.getWeekcompletedsmallfamtasks();
				response.isuseyuanbaocomtask = taskInfo.getIsuseyuanbao();
				response.iscanclefamtask = taskInfo.getIscancletask();
				response(response);
//                FTask.syncTaskToMap(roleid, new XPlayerChangeTask(new HashSet<Integer>(FTask.getTaskMonsters(taskInfo))));
                return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6563815;

	public int getType() {
		return 6563815;
	}


	public CClearFamTask() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CClearFamTask) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CClearFamTask _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


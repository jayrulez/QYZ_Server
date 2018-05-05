
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.task.FTask;
import xbean.FamilyTaskDetail;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCancelFamilyTask__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCancelFamilyTask extends __CCancelFamilyTask__ {
	@Override
	protected void process() {
		new AProcedure<CCancelFamilyTask>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleTask taskInfo = FTask.getTask(roleid);
				java.util.List<FamilyTaskDetail> acceptTasks = taskInfo.getAcceptedfamilytasks();
				if(acceptTasks.size() == 0){
					return error(ErrorCode.NOT_HAS_ACCEPT_FAMILY_TASK);
				}
				if(curtaskorder > acceptTasks.size()){
					return error(ErrorCode.PARAM_ERROR);
				}
				if(curtaskorder != taskInfo.getCompletednumsinfamtasks() + 1){
                    return error(ErrorCode.FAMTASK_ORDER_WRONG);
                }
				final int curTaskid = acceptTasks.get(curtaskorder - 1).getTaskid();
				if(curTaskid != taskid){
                    return error(ErrorCode.NOT_EXIST_TASKID);
                }
				//清空环任务相关的信息;
				xbean.TaskData data = taskInfo.getTasks().remove(taskid);
				if(data == null)
					return error(ErrorCode.NOT_IN_TASK_LIST);
				acceptTasks.remove(curtaskorder -1);//移除当前正在做的环任务
				taskInfo.setIscancletask(1);//标志取消了任务，下次要去家族npc处接收环任务
				taskInfo.setLastgiveupfamtasktime(System.currentTimeMillis());
				SCancelFamilyTask response = new SCancelFamilyTask();
				response.curtaskorder = curtaskorder;
				response.taskid = taskid;
				response(response);
				xdb.Trace.debug("cancle family task success,complete family tasks num = {}, cancletaskorder = {}, taskid = {}",taskInfo.getCompletednumsinfamtasks(), curtaskorder, taskid);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571118;

	public int getType() {
		return 6571118;
	}

	public int curtaskorder; // 当前正在做第几环
	public int taskid; // 当前做的任务的id

	public CCancelFamilyTask() {
	}

	public CCancelFamilyTask(int _curtaskorder_, int _taskid_) {
		this.curtaskorder = _curtaskorder_;
		this.taskid = _taskid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(curtaskorder);
		_os_.marshal(taskid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		curtaskorder = _os_.unmarshal_int();
		taskid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CCancelFamilyTask) {
			CCancelFamilyTask _o_ = (CCancelFamilyTask)_o1_;
			if (curtaskorder != _o_.curtaskorder) return false;
			if (taskid != _o_.taskid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += curtaskorder;
		_h_ += taskid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(curtaskorder).append(",");
		_sb_.append(taskid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CCancelFamilyTask _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = curtaskorder - _o_.curtaskorder;
		if (0 != _c_) return _c_;
		_c_ = taskid - _o_.taskid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


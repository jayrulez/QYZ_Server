
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.task.FTask;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAcceptFamilyTask__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAcceptFamilyTask extends __CAcceptFamilyTask__ {
	@Override
	protected void process() {
		new AProcedure<CAcceptFamilyTask>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleTask taskInfo = FTask.getTask(roleid);
				xbean.FamilyTaskDetail toDoTask = taskInfo.getAcceptedfamilytasks().get(taskInfo.getCompletednumsinfamtasks());
				if(toDoTask.getNpcid() != npcid || toDoTask.getTaskid() != taskid){
					return error(ErrorCode.CAN_NOT_ACCEPT_FAMILY_TASK);
				}
				
				ErrorCode ret = FTask.checkAcceptTask(roleid, taskInfo, npcid, taskid);
				if(ret.err()){
					return error(ret);
				}
				
				SAcceptFamilyTask response = new SAcceptFamilyTask();
				response.npcid = npcid;
				response.taskid = taskid;
				response(response);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586140;

	public int getType() {
		return 6586140;
	}

	public int npcid;
	public int taskid;

	public CAcceptFamilyTask() {
	}

	public CAcceptFamilyTask(int _npcid_, int _taskid_) {
		this.npcid = _npcid_;
		this.taskid = _taskid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(npcid);
		_os_.marshal(taskid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		npcid = _os_.unmarshal_int();
		taskid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CAcceptFamilyTask) {
			CAcceptFamilyTask _o_ = (CAcceptFamilyTask)_o1_;
			if (npcid != _o_.npcid) return false;
			if (taskid != _o_.taskid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += npcid;
		_h_ += taskid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(npcid).append(",");
		_sb_.append(taskid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CAcceptFamilyTask _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = npcid - _o_.npcid;
		if (0 != _c_) return _c_;
		_c_ = taskid - _o_.taskid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


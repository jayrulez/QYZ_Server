
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SCompleteFamilyTask__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SCompleteFamilyTask extends __SCompleteFamilyTask__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566295;

	public int getType() {
		return 6566295;
	}

	public int curtaskorder; // 当前提交的是第几环
	public int taskid;
	public int comdaycycle; // 已经完成的日环任务组数
	public int completefamtasknum; // 在一组环任务内已经完成的环数
	public int comweeksmallcycle; // 已经完成的周环任务组数
	public map.msg.Bonus bonus;
	public lx.gs.task.msg.FamilyTaskInfo nextfamtask; // 提交当前环后，返回下一环将要做的，如果是完成一组了，该返回为空

	public SCompleteFamilyTask() {
		bonus = new map.msg.Bonus();
		nextfamtask = new lx.gs.task.msg.FamilyTaskInfo();
	}

	public SCompleteFamilyTask(int _curtaskorder_, int _taskid_, int _comdaycycle_, int _completefamtasknum_, int _comweeksmallcycle_, map.msg.Bonus _bonus_, lx.gs.task.msg.FamilyTaskInfo _nextfamtask_) {
		this.curtaskorder = _curtaskorder_;
		this.taskid = _taskid_;
		this.comdaycycle = _comdaycycle_;
		this.completefamtasknum = _completefamtasknum_;
		this.comweeksmallcycle = _comweeksmallcycle_;
		this.bonus = _bonus_;
		this.nextfamtask = _nextfamtask_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		if (!nextfamtask._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(curtaskorder);
		_os_.marshal(taskid);
		_os_.marshal(comdaycycle);
		_os_.marshal(completefamtasknum);
		_os_.marshal(comweeksmallcycle);
		_os_.marshal(bonus);
		_os_.marshal(nextfamtask);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		curtaskorder = _os_.unmarshal_int();
		taskid = _os_.unmarshal_int();
		comdaycycle = _os_.unmarshal_int();
		completefamtasknum = _os_.unmarshal_int();
		comweeksmallcycle = _os_.unmarshal_int();
		bonus.unmarshal(_os_);
		nextfamtask.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SCompleteFamilyTask) {
			SCompleteFamilyTask _o_ = (SCompleteFamilyTask)_o1_;
			if (curtaskorder != _o_.curtaskorder) return false;
			if (taskid != _o_.taskid) return false;
			if (comdaycycle != _o_.comdaycycle) return false;
			if (completefamtasknum != _o_.completefamtasknum) return false;
			if (comweeksmallcycle != _o_.comweeksmallcycle) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			if (!nextfamtask.equals(_o_.nextfamtask)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += curtaskorder;
		_h_ += taskid;
		_h_ += comdaycycle;
		_h_ += completefamtasknum;
		_h_ += comweeksmallcycle;
		_h_ += bonus.hashCode();
		_h_ += nextfamtask.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(curtaskorder).append(",");
		_sb_.append(taskid).append(",");
		_sb_.append(comdaycycle).append(",");
		_sb_.append(completefamtasknum).append(",");
		_sb_.append(comweeksmallcycle).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(nextfamtask).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


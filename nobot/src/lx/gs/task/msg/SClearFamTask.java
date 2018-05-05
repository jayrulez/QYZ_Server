
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SClearFamTask__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SClearFamTask extends __SClearFamTask__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6574849;

	public int getType() {
		return 6574849;
	}

	public map.msg.Bonus bonus; // 一键清环的奖励
	public java.util.ArrayList<lx.gs.task.msg.TaskInfo> acceptedtasks; // 正在做的任务信息
	public java.util.ArrayList<lx.gs.task.msg.FamilyTaskInfo> curfamtasks; // 当前已经获取的所有环任务
	public int completefamtasknum; // 在一组环任务内已经完成的环数
	public int comdaycycle; // 已经完成的日环任务组数
	public int comweeksmallcycle; // 已经完成的周环任务组数
	public int isuseyuanbaocomtask; // 标识是否使用元宝完成家族环任务，0：否；1：是
	public int iscanclefamtask; // 标识是否放弃过家族环任务,0:否；1：是

	public SClearFamTask() {
		bonus = new map.msg.Bonus();
		acceptedtasks = new java.util.ArrayList<lx.gs.task.msg.TaskInfo>();
		curfamtasks = new java.util.ArrayList<lx.gs.task.msg.FamilyTaskInfo>();
	}

	public SClearFamTask(map.msg.Bonus _bonus_, java.util.ArrayList<lx.gs.task.msg.TaskInfo> _acceptedtasks_, java.util.ArrayList<lx.gs.task.msg.FamilyTaskInfo> _curfamtasks_, int _completefamtasknum_, int _comdaycycle_, int _comweeksmallcycle_, int _isuseyuanbaocomtask_, int _iscanclefamtask_) {
		this.bonus = _bonus_;
		this.acceptedtasks = _acceptedtasks_;
		this.curfamtasks = _curfamtasks_;
		this.completefamtasknum = _completefamtasknum_;
		this.comdaycycle = _comdaycycle_;
		this.comweeksmallcycle = _comweeksmallcycle_;
		this.isuseyuanbaocomtask = _isuseyuanbaocomtask_;
		this.iscanclefamtask = _iscanclefamtask_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		for (lx.gs.task.msg.TaskInfo _v_ : acceptedtasks)
			if (!_v_._validator_()) return false;
		for (lx.gs.task.msg.FamilyTaskInfo _v_ : curfamtasks)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bonus);
		_os_.compact_uint32(acceptedtasks.size());
		for (lx.gs.task.msg.TaskInfo _v_ : acceptedtasks) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(curfamtasks.size());
		for (lx.gs.task.msg.FamilyTaskInfo _v_ : curfamtasks) {
			_os_.marshal(_v_);
		}
		_os_.marshal(completefamtasknum);
		_os_.marshal(comdaycycle);
		_os_.marshal(comweeksmallcycle);
		_os_.marshal(isuseyuanbaocomtask);
		_os_.marshal(iscanclefamtask);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bonus.unmarshal(_os_);
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.task.msg.TaskInfo _v_ = new lx.gs.task.msg.TaskInfo();
			_v_.unmarshal(_os_);
			acceptedtasks.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.task.msg.FamilyTaskInfo _v_ = new lx.gs.task.msg.FamilyTaskInfo();
			_v_.unmarshal(_os_);
			curfamtasks.add(_v_);
		}
		completefamtasknum = _os_.unmarshal_int();
		comdaycycle = _os_.unmarshal_int();
		comweeksmallcycle = _os_.unmarshal_int();
		isuseyuanbaocomtask = _os_.unmarshal_int();
		iscanclefamtask = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SClearFamTask) {
			SClearFamTask _o_ = (SClearFamTask)_o1_;
			if (!bonus.equals(_o_.bonus)) return false;
			if (!acceptedtasks.equals(_o_.acceptedtasks)) return false;
			if (!curfamtasks.equals(_o_.curfamtasks)) return false;
			if (completefamtasknum != _o_.completefamtasknum) return false;
			if (comdaycycle != _o_.comdaycycle) return false;
			if (comweeksmallcycle != _o_.comweeksmallcycle) return false;
			if (isuseyuanbaocomtask != _o_.isuseyuanbaocomtask) return false;
			if (iscanclefamtask != _o_.iscanclefamtask) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bonus.hashCode();
		_h_ += acceptedtasks.hashCode();
		_h_ += curfamtasks.hashCode();
		_h_ += completefamtasknum;
		_h_ += comdaycycle;
		_h_ += comweeksmallcycle;
		_h_ += isuseyuanbaocomtask;
		_h_ += iscanclefamtask;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bonus).append(",");
		_sb_.append(acceptedtasks).append(",");
		_sb_.append(curfamtasks).append(",");
		_sb_.append(completefamtasknum).append(",");
		_sb_.append(comdaycycle).append(",");
		_sb_.append(comweeksmallcycle).append(",");
		_sb_.append(isuseyuanbaocomtask).append(",");
		_sb_.append(iscanclefamtask).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


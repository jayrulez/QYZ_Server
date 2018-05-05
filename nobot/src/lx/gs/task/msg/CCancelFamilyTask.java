
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCancelFamilyTask__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCancelFamilyTask extends __CCancelFamilyTask__ {
	@Override
	protected void process() {
		// protocol handle
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


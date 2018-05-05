
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetFamilyTask__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetFamilyTask extends __SGetFamilyTask__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6563845;

	public int getType() {
		return 6563845;
	}

	public int npcid;
	public lx.gs.task.msg.FamilyTaskInfo taskinfo; // 获取到的环任务信息

	public SGetFamilyTask() {
		taskinfo = new lx.gs.task.msg.FamilyTaskInfo();
	}

	public SGetFamilyTask(int _npcid_, lx.gs.task.msg.FamilyTaskInfo _taskinfo_) {
		this.npcid = _npcid_;
		this.taskinfo = _taskinfo_;
	}

	public final boolean _validator_() {
		if (!taskinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(npcid);
		_os_.marshal(taskinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		npcid = _os_.unmarshal_int();
		taskinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetFamilyTask) {
			SGetFamilyTask _o_ = (SGetFamilyTask)_o1_;
			if (npcid != _o_.npcid) return false;
			if (!taskinfo.equals(_o_.taskinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += npcid;
		_h_ += taskinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(npcid).append(",");
		_sb_.append(taskinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SGetFamilyTask _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = npcid - _o_.npcid;
		if (0 != _c_) return _c_;
		_c_ = taskinfo.compareTo(_o_.taskinfo);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


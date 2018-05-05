
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAppointJob__ extends xio.Protocol { }

/** 任命职位
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAppointJob extends __CAppointJob__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556180;

	public int getType() {
		return 6556180;
	}

	public long memberid; // 要任命的角色的id
	public int jobid; // 家族的职位id

	public CAppointJob() {
	}

	public CAppointJob(long _memberid_, int _jobid_) {
		this.memberid = _memberid_;
		this.jobid = _jobid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(memberid);
		_os_.marshal(jobid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		memberid = _os_.unmarshal_long();
		jobid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CAppointJob) {
			CAppointJob _o_ = (CAppointJob)_o1_;
			if (memberid != _o_.memberid) return false;
			if (jobid != _o_.jobid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)memberid;
		_h_ += jobid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(memberid).append(",");
		_sb_.append(jobid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CAppointJob _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(memberid - _o_.memberid);
		if (0 != _c_) return _c_;
		_c_ = jobid - _o_.jobid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


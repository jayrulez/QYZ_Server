
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAppointJob__ extends xio.Protocol { }

/** 任命职位
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAppointJob extends __SAppointJob__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579097;

	public int getType() {
		return 6579097;
	}

	public lx.gs.family.msg.FamilyMember member; // 要任命的角色的id
	public int jobid; // 家族的职位id

	public SAppointJob() {
		member = new lx.gs.family.msg.FamilyMember();
	}

	public SAppointJob(lx.gs.family.msg.FamilyMember _member_, int _jobid_) {
		this.member = _member_;
		this.jobid = _jobid_;
	}

	public final boolean _validator_() {
		if (!member._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(member);
		_os_.marshal(jobid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		member.unmarshal(_os_);
		jobid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAppointJob) {
			SAppointJob _o_ = (SAppointJob)_o1_;
			if (!member.equals(_o_.member)) return false;
			if (jobid != _o_.jobid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += member.hashCode();
		_h_ += jobid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(member).append(",");
		_sb_.append(jobid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


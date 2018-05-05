
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __STransferChiefNotify__ extends xio.Protocol { }

/** 转让族长职位通知,任命成功后发给被任命者，也会发给全族人员
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class STransferChiefNotify extends __STransferChiefNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564232;

	public int getType() {
		return 6564232;
	}

	public lx.gs.family.msg.FamilyMember operator; // 操作者角色id
	public lx.gs.family.msg.FamilyMember member; // 要任命的角色的id
	public int jobid; // 家族的职位id

	public STransferChiefNotify() {
		operator = new lx.gs.family.msg.FamilyMember();
		member = new lx.gs.family.msg.FamilyMember();
	}

	public STransferChiefNotify(lx.gs.family.msg.FamilyMember _operator_, lx.gs.family.msg.FamilyMember _member_, int _jobid_) {
		this.operator = _operator_;
		this.member = _member_;
		this.jobid = _jobid_;
	}

	public final boolean _validator_() {
		if (!operator._validator_()) return false;
		if (!member._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(operator);
		_os_.marshal(member);
		_os_.marshal(jobid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		operator.unmarshal(_os_);
		member.unmarshal(_os_);
		jobid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof STransferChiefNotify) {
			STransferChiefNotify _o_ = (STransferChiefNotify)_o1_;
			if (!operator.equals(_o_.operator)) return false;
			if (!member.equals(_o_.member)) return false;
			if (jobid != _o_.jobid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += operator.hashCode();
		_h_ += member.hashCode();
		_h_ += jobid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(operator).append(",");
		_sb_.append(member).append(",");
		_sb_.append(jobid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


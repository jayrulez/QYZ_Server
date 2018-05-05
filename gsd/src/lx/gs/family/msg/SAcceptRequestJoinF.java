
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAcceptRequestJoinF__ extends xio.Protocol { }

/** 同意申请
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAcceptRequestJoinF extends __SAcceptRequestJoinF__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576295;

	public int getType() {
		return 6576295;
	}

	public lx.gs.family.msg.FamilyMember member; // 同意申请的角色id

	public SAcceptRequestJoinF() {
		member = new lx.gs.family.msg.FamilyMember();
	}

	public SAcceptRequestJoinF(lx.gs.family.msg.FamilyMember _member_) {
		this.member = _member_;
	}

	public final boolean _validator_() {
		if (!member._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(member);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		member.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAcceptRequestJoinF) {
			SAcceptRequestJoinF _o_ = (SAcceptRequestJoinF)_o1_;
			if (!member.equals(_o_.member)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += member.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(member).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


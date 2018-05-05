
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAcceptInvite__ extends xio.Protocol { }

/** 接收和拒绝入队申请，入队邀请，队长转移邀请，跟随邀请
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAcceptInvite extends __CAcceptInvite__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557815;

	public int getType() {
		return 6557815;
	}

	public long inviteroleid;

	public CAcceptInvite() {
	}

	public CAcceptInvite(long _inviteroleid_) {
		this.inviteroleid = _inviteroleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(inviteroleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		inviteroleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CAcceptInvite) {
			CAcceptInvite _o_ = (CAcceptInvite)_o1_;
			if (inviteroleid != _o_.inviteroleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)inviteroleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(inviteroleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CAcceptInvite _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(inviteroleid - _o_.inviteroleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


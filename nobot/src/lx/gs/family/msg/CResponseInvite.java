
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CResponseInvite__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CResponseInvite extends __CResponseInvite__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578017;

	public int getType() {
		return 6578017;
	}

	public int result; // 回复邀请，0拒绝；1同意
	public long familyid;
	public long inviteroleid;

	public CResponseInvite() {
	}

	public CResponseInvite(int _result_, long _familyid_, long _inviteroleid_) {
		this.result = _result_;
		this.familyid = _familyid_;
		this.inviteroleid = _inviteroleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(result);
		_os_.marshal(familyid);
		_os_.marshal(inviteroleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		result = _os_.unmarshal_int();
		familyid = _os_.unmarshal_long();
		inviteroleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CResponseInvite) {
			CResponseInvite _o_ = (CResponseInvite)_o1_;
			if (result != _o_.result) return false;
			if (familyid != _o_.familyid) return false;
			if (inviteroleid != _o_.inviteroleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += result;
		_h_ += (int)familyid;
		_h_ += (int)inviteroleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(result).append(",");
		_sb_.append(familyid).append(",");
		_sb_.append(inviteroleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CResponseInvite _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = result - _o_.result;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(familyid - _o_.familyid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(inviteroleid - _o_.inviteroleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


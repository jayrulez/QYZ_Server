
package lx.gs.login;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRoleRelogin__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRoleRelogin extends __CRoleRelogin__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6580194;

	public int getType() {
		return 6580194;
	}

	public long roleid;
	public long receivedmessagecount;

	public CRoleRelogin() {
	}

	public CRoleRelogin(long _roleid_, long _receivedmessagecount_) {
		this.roleid = _roleid_;
		this.receivedmessagecount = _receivedmessagecount_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(receivedmessagecount);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		receivedmessagecount = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRoleRelogin) {
			CRoleRelogin _o_ = (CRoleRelogin)_o1_;
			if (roleid != _o_.roleid) return false;
			if (receivedmessagecount != _o_.receivedmessagecount) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += (int)receivedmessagecount;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(receivedmessagecount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CRoleRelogin _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(receivedmessagecount - _o_.receivedmessagecount);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


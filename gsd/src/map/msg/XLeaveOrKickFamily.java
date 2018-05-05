
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XLeaveOrKickFamily__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XLeaveOrKickFamily extends __XLeaveOrKickFamily__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6696640;

	public int getType() {
		return 6696640;
	}

	public long roleid;
	public long familyid;
	public int reason;

	public XLeaveOrKickFamily() {
	}

	public XLeaveOrKickFamily(long _roleid_, long _familyid_, int _reason_) {
		this.roleid = _roleid_;
		this.familyid = _familyid_;
		this.reason = _reason_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(familyid);
		_os_.marshal(reason);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		familyid = _os_.unmarshal_long();
		reason = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XLeaveOrKickFamily) {
			XLeaveOrKickFamily _o_ = (XLeaveOrKickFamily)_o1_;
			if (roleid != _o_.roleid) return false;
			if (familyid != _o_.familyid) return false;
			if (reason != _o_.reason) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += (int)familyid;
		_h_ += reason;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(familyid).append(",");
		_sb_.append(reason).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(XLeaveOrKickFamily _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(familyid - _o_.familyid);
		if (0 != _c_) return _c_;
		_c_ = reason - _o_.reason;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


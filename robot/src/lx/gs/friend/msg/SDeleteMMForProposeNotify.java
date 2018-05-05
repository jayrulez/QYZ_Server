
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SDeleteMMForProposeNotify__ extends xio.Protocol { }

/** 由于结婚，可能需要删除已有的脉脉关系
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SDeleteMMForProposeNotify extends __SDeleteMMForProposeNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6569110;

	public int getType() {
		return 6569110;
	}

	public int mmtype;
	public long roleid;

	public SDeleteMMForProposeNotify() {
	}

	public SDeleteMMForProposeNotify(int _mmtype_, long _roleid_) {
		this.mmtype = _mmtype_;
		this.roleid = _roleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(mmtype);
		_os_.marshal(roleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		mmtype = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SDeleteMMForProposeNotify) {
			SDeleteMMForProposeNotify _o_ = (SDeleteMMForProposeNotify)_o1_;
			if (mmtype != _o_.mmtype) return false;
			if (roleid != _o_.roleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += mmtype;
		_h_ += (int)roleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mmtype).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SDeleteMMForProposeNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = mmtype - _o_.mmtype;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


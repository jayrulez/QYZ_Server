
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SDeleteMM__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SDeleteMM extends __SDeleteMM__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579603;

	public int getType() {
		return 6579603;
	}

	public int result;
	public long roleid;
	public int mmtype;

	public SDeleteMM() {
	}

	public SDeleteMM(int _result_, long _roleid_, int _mmtype_) {
		this.result = _result_;
		this.roleid = _roleid_;
		this.mmtype = _mmtype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(result);
		_os_.marshal(roleid);
		_os_.marshal(mmtype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		result = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		mmtype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SDeleteMM) {
			SDeleteMM _o_ = (SDeleteMM)_o1_;
			if (result != _o_.result) return false;
			if (roleid != _o_.roleid) return false;
			if (mmtype != _o_.mmtype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += result;
		_h_ += (int)roleid;
		_h_ += mmtype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(result).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(mmtype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SDeleteMM _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = result - _o_.result;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		_c_ = mmtype - _o_.mmtype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


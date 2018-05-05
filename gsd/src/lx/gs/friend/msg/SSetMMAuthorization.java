
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SSetMMAuthorization__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SSetMMAuthorization extends __SSetMMAuthorization__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6563979;

	public int getType() {
		return 6563979;
	}

	public int result;
	public int allowfriendgetmm;
	public int allowstrangergetmm;

	public SSetMMAuthorization() {
	}

	public SSetMMAuthorization(int _result_, int _allowfriendgetmm_, int _allowstrangergetmm_) {
		this.result = _result_;
		this.allowfriendgetmm = _allowfriendgetmm_;
		this.allowstrangergetmm = _allowstrangergetmm_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(result);
		_os_.marshal(allowfriendgetmm);
		_os_.marshal(allowstrangergetmm);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		result = _os_.unmarshal_int();
		allowfriendgetmm = _os_.unmarshal_int();
		allowstrangergetmm = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SSetMMAuthorization) {
			SSetMMAuthorization _o_ = (SSetMMAuthorization)_o1_;
			if (result != _o_.result) return false;
			if (allowfriendgetmm != _o_.allowfriendgetmm) return false;
			if (allowstrangergetmm != _o_.allowstrangergetmm) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += result;
		_h_ += allowfriendgetmm;
		_h_ += allowstrangergetmm;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(result).append(",");
		_sb_.append(allowfriendgetmm).append(",");
		_sb_.append(allowstrangergetmm).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SSetMMAuthorization _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = result - _o_.result;
		if (0 != _c_) return _c_;
		_c_ = allowfriendgetmm - _o_.allowfriendgetmm;
		if (0 != _c_) return _c_;
		_c_ = allowstrangergetmm - _o_.allowstrangergetmm;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


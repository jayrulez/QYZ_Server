
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __RequireLoginActivationCode__ extends xio.Protocol { }

/** 需要输入激活码才能登陆（或者激活码错误需重新输入） deliver - link - client
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class RequireLoginActivationCode extends __RequireLoginActivationCode__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 120;

	public int getType() {
		return 120;
	}

	public gnet.ActivationCodeErr err;
	public int localsid;
	public long userid;

	public RequireLoginActivationCode() {
		err = new gnet.ActivationCodeErr();
	}

	public RequireLoginActivationCode(gnet.ActivationCodeErr _err_, int _localsid_, long _userid_) {
		this.err = _err_;
		this.localsid = _localsid_;
		this.userid = _userid_;
	}

	public final boolean _validator_() {
		if (!err._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(err);
		_os_.marshal(localsid);
		_os_.marshal(userid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		err.unmarshal(_os_);
		localsid = _os_.unmarshal_int();
		userid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RequireLoginActivationCode) {
			RequireLoginActivationCode _o_ = (RequireLoginActivationCode)_o1_;
			if (!err.equals(_o_.err)) return false;
			if (localsid != _o_.localsid) return false;
			if (userid != _o_.userid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += err.hashCode();
		_h_ += localsid;
		_h_ += (int)userid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(err).append(",");
		_sb_.append(localsid).append(",");
		_sb_.append(userid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(RequireLoginActivationCode _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = err.compareTo(_o_.err);
		if (0 != _c_) return _c_;
		_c_ = localsid - _o_.localsid;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(userid - _o_.userid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


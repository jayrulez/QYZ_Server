
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __InputLoginActivationCode__ extends xio.Protocol { }

/** 输入登陆激活码 client - link - deliver
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class InputLoginActivationCode extends __InputLoginActivationCode__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 121;

	public int getType() {
		return 121;
	}

	public int localsid;
	public long userid;
	public com.goldhuman.Common.Octets code;

	public InputLoginActivationCode() {
		code = new com.goldhuman.Common.Octets();
	}

	public InputLoginActivationCode(int _localsid_, long _userid_, com.goldhuman.Common.Octets _code_) {
		this.localsid = _localsid_;
		this.userid = _userid_;
		this.code = _code_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(localsid);
		_os_.marshal(userid);
		_os_.marshal(code);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		localsid = _os_.unmarshal_int();
		userid = _os_.unmarshal_long();
		code = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof InputLoginActivationCode) {
			InputLoginActivationCode _o_ = (InputLoginActivationCode)_o1_;
			if (localsid != _o_.localsid) return false;
			if (userid != _o_.userid) return false;
			if (!code.equals(_o_.code)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += localsid;
		_h_ += (int)userid;
		_h_ += code.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(localsid).append(",");
		_sb_.append(userid).append(",");
		_sb_.append("B").append(code.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


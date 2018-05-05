
package gnet.link;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SetLogin__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SetLogin extends __SetLogin__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 65543;

	public int getType() {
		return 65543;
	}

	public final static int eLogout = 0;
	public final static int eLogin = 1;

	public int linksid;
	public int login;
	public long roleid; // for debug

	public SetLogin() {
	}

	public SetLogin(int _linksid_, int _login_, long _roleid_) {
		this.linksid = _linksid_;
		this.login = _login_;
		this.roleid = _roleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(linksid);
		_os_.marshal(login);
		_os_.marshal(roleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		linksid = _os_.unmarshal_int();
		login = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SetLogin) {
			SetLogin _o_ = (SetLogin)_o1_;
			if (linksid != _o_.linksid) return false;
			if (login != _o_.login) return false;
			if (roleid != _o_.roleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += linksid;
		_h_ += login;
		_h_ += (int)roleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(linksid).append(",");
		_sb_.append(login).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SetLogin _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = linksid - _o_.linksid;
		if (0 != _c_) return _c_;
		_c_ = login - _o_.login;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


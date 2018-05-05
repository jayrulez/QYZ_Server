
package lx.gs.login;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SRoleLogin__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SRoleLogin extends __SRoleLogin__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553608;

	public int getType() {
		return 6553608;
	}

	public final static int OK = 0;
	public final static int ROLEID_NOT_IN_USER = 1;
	public final static int ROLEINFO_NOT_FOUND = 2;
	public final static int USER_NOT_FOUND = 3;
	public final static int ROLE_LOGIN_IS_FORBIDDEN = 4;
	public final static int ROLE_NOT_LOGIN = 5;
	public final static int SESSION_CORRUPT = 6;
	public final static int TOOMANY_ONLINES_ROLE = 7; // 超过最大在线人数
	public final static int SERVER_LOADAVG_BUSY = 8; // 服务器忙，cpu利用率达到8成

	public int err;
	public lx.gs.login.RoleDetail roledetail;
	public long servertime;
	public java.lang.String servertimezone;
	public int isservermerge; // 是否合服
	public java.lang.String forbiddesc; // 禁止登陆描述，错误码为4时显示

	public SRoleLogin() {
		roledetail = new lx.gs.login.RoleDetail();
		servertimezone = "";
		forbiddesc = "";
	}

	public SRoleLogin(int _err_, lx.gs.login.RoleDetail _roledetail_, long _servertime_, java.lang.String _servertimezone_, int _isservermerge_, java.lang.String _forbiddesc_) {
		this.err = _err_;
		this.roledetail = _roledetail_;
		this.servertime = _servertime_;
		this.servertimezone = _servertimezone_;
		this.isservermerge = _isservermerge_;
		this.forbiddesc = _forbiddesc_;
	}

	public final boolean _validator_() {
		if (!roledetail._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(err);
		_os_.marshal(roledetail);
		_os_.marshal(servertime);
		_os_.marshal(servertimezone, "UTF-16LE");
		_os_.marshal(isservermerge);
		_os_.marshal(forbiddesc, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		err = _os_.unmarshal_int();
		roledetail.unmarshal(_os_);
		servertime = _os_.unmarshal_long();
		servertimezone = _os_.unmarshal_String("UTF-16LE");
		isservermerge = _os_.unmarshal_int();
		forbiddesc = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SRoleLogin) {
			SRoleLogin _o_ = (SRoleLogin)_o1_;
			if (err != _o_.err) return false;
			if (!roledetail.equals(_o_.roledetail)) return false;
			if (servertime != _o_.servertime) return false;
			if (!servertimezone.equals(_o_.servertimezone)) return false;
			if (isservermerge != _o_.isservermerge) return false;
			if (!forbiddesc.equals(_o_.forbiddesc)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += err;
		_h_ += roledetail.hashCode();
		_h_ += (int)servertime;
		_h_ += servertimezone.hashCode();
		_h_ += isservermerge;
		_h_ += forbiddesc.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(err).append(",");
		_sb_.append(roledetail).append(",");
		_sb_.append(servertime).append(",");
		_sb_.append("T").append(servertimezone.length()).append(",");
		_sb_.append(isservermerge).append(",");
		_sb_.append("T").append(forbiddesc.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


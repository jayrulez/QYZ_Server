
package lx.gs.login;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SCreateRole__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SCreateRole extends __SCreateRole__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553604;

	public int getType() {
		return 6553604;
	}

	public int err;
	public lx.gs.role.msg.RoleBrief newinfo;
	public long servertime;

	public SCreateRole() {
		newinfo = new lx.gs.role.msg.RoleBrief();
	}

	public SCreateRole(int _err_, lx.gs.role.msg.RoleBrief _newinfo_, long _servertime_) {
		this.err = _err_;
		this.newinfo = _newinfo_;
		this.servertime = _servertime_;
	}

	public final boolean _validator_() {
		if (!newinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(err);
		_os_.marshal(newinfo);
		_os_.marshal(servertime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		err = _os_.unmarshal_int();
		newinfo.unmarshal(_os_);
		servertime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SCreateRole) {
			SCreateRole _o_ = (SCreateRole)_o1_;
			if (err != _o_.err) return false;
			if (!newinfo.equals(_o_.newinfo)) return false;
			if (servertime != _o_.servertime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += err;
		_h_ += newinfo.hashCode();
		_h_ += (int)servertime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(err).append(",");
		_sb_.append(newinfo).append(",");
		_sb_.append(servertime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}



package lx.gs.role.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetRoleInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetRoleInfo extends __SGetRoleInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556205;

	public int getType() {
		return 6556205;
	}

	public long roleid;
	public lx.gs.role.msg.RoleShowInfo5 roleinfo;

	public SGetRoleInfo() {
		roleinfo = new lx.gs.role.msg.RoleShowInfo5();
	}

	public SGetRoleInfo(long _roleid_, lx.gs.role.msg.RoleShowInfo5 _roleinfo_) {
		this.roleid = _roleid_;
		this.roleinfo = _roleinfo_;
	}

	public final boolean _validator_() {
		if (!roleinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(roleinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		roleinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetRoleInfo) {
			SGetRoleInfo _o_ = (SGetRoleInfo)_o1_;
			if (roleid != _o_.roleid) return false;
			if (!roleinfo.equals(_o_.roleinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += roleinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(roleinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


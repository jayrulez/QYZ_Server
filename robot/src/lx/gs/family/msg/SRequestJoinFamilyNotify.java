
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SRequestJoinFamilyNotify__ extends xio.Protocol { }

/** 给族长和副族长发送申请通知
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SRequestJoinFamilyNotify extends __SRequestJoinFamilyNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6585918;

	public int getType() {
		return 6585918;
	}

	public lx.gs.role.msg.RoleShowInfo4 roleinfo;

	public SRequestJoinFamilyNotify() {
		roleinfo = new lx.gs.role.msg.RoleShowInfo4();
	}

	public SRequestJoinFamilyNotify(lx.gs.role.msg.RoleShowInfo4 _roleinfo_) {
		this.roleinfo = _roleinfo_;
	}

	public final boolean _validator_() {
		if (!roleinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SRequestJoinFamilyNotify) {
			SRequestJoinFamilyNotify _o_ = (SRequestJoinFamilyNotify)_o1_;
			if (!roleinfo.equals(_o_.roleinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += roleinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


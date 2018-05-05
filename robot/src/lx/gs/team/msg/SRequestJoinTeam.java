
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SRequestJoinTeam__ extends xio.Protocol { }

/** 通知队长
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SRequestJoinTeam extends __SRequestJoinTeam__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557832;

	public int getType() {
		return 6557832;
	}

	public lx.gs.role.msg.RoleShowInfo4 requestrole;

	public SRequestJoinTeam() {
		requestrole = new lx.gs.role.msg.RoleShowInfo4();
	}

	public SRequestJoinTeam(lx.gs.role.msg.RoleShowInfo4 _requestrole_) {
		this.requestrole = _requestrole_;
	}

	public final boolean _validator_() {
		if (!requestrole._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(requestrole);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		requestrole.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SRequestJoinTeam) {
			SRequestJoinTeam _o_ = (SRequestJoinTeam)_o1_;
			if (!requestrole.equals(_o_.requestrole)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += requestrole.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(requestrole).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


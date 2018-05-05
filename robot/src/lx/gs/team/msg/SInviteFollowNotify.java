
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SInviteFollowNotify__ extends xio.Protocol { }

/** 队长发送跟随邀请
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SInviteFollowNotify extends __SInviteFollowNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557841;

	public int getType() {
		return 6557841;
	}

	public lx.gs.team.msg.TeamMember leader;

	public SInviteFollowNotify() {
		leader = new lx.gs.team.msg.TeamMember();
	}

	public SInviteFollowNotify(lx.gs.team.msg.TeamMember _leader_) {
		this.leader = _leader_;
	}

	public final boolean _validator_() {
		if (!leader._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(leader);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		leader.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SInviteFollowNotify) {
			SInviteFollowNotify _o_ = (SInviteFollowNotify)_o1_;
			if (!leader.equals(_o_.leader)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += leader.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(leader).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


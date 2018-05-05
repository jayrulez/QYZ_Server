
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class TeamInfo implements Marshal {
	public long teamid;
	public int membernum;
	public map.msg.MemberInfo leader;

	public TeamInfo() {
		leader = new map.msg.MemberInfo();
	}

	public TeamInfo(long _teamid_, int _membernum_, map.msg.MemberInfo _leader_) {
		this.teamid = _teamid_;
		this.membernum = _membernum_;
		this.leader = _leader_;
	}

	public final boolean _validator_() {
		if (!leader._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(teamid);
		_os_.marshal(membernum);
		_os_.marshal(leader);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		teamid = _os_.unmarshal_long();
		membernum = _os_.unmarshal_int();
		leader.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TeamInfo) {
			TeamInfo _o_ = (TeamInfo)_o1_;
			if (teamid != _o_.teamid) return false;
			if (membernum != _o_.membernum) return false;
			if (!leader.equals(_o_.leader)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)teamid;
		_h_ += membernum;
		_h_ += leader.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(teamid).append(",");
		_sb_.append(membernum).append(",");
		_sb_.append(leader).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


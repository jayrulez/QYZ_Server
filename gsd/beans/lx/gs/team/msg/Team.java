
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Team implements Marshal {
	public long teamid;
	public long leaderid;
	public long createtime; // 组队时间
	public java.util.HashMap<Long,lx.gs.team.msg.TeamMember> members; // 队友信息，key为角色id，value为角色信息

	public Team() {
		members = new java.util.HashMap<Long,lx.gs.team.msg.TeamMember>();
	}

	public Team(long _teamid_, long _leaderid_, long _createtime_, java.util.HashMap<Long,lx.gs.team.msg.TeamMember> _members_) {
		this.teamid = _teamid_;
		this.leaderid = _leaderid_;
		this.createtime = _createtime_;
		this.members = _members_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Long, lx.gs.team.msg.TeamMember> _e_ : members.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(teamid);
		_os_.marshal(leaderid);
		_os_.marshal(createtime);
		_os_.compact_uint32(members.size());
		for (java.util.Map.Entry<Long, lx.gs.team.msg.TeamMember> _e_ : members.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		teamid = _os_.unmarshal_long();
		leaderid = _os_.unmarshal_long();
		createtime = _os_.unmarshal_long();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			lx.gs.team.msg.TeamMember _v_ = new lx.gs.team.msg.TeamMember();
			_v_.unmarshal(_os_);
			members.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Team) {
			Team _o_ = (Team)_o1_;
			if (teamid != _o_.teamid) return false;
			if (leaderid != _o_.leaderid) return false;
			if (createtime != _o_.createtime) return false;
			if (!members.equals(_o_.members)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)teamid;
		_h_ += (int)leaderid;
		_h_ += (int)createtime;
		_h_ += members.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(teamid).append(",");
		_sb_.append(leaderid).append(",");
		_sb_.append(createtime).append(",");
		_sb_.append(members).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}


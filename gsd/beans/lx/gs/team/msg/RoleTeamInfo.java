
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RoleTeamInfo implements Marshal , Comparable<RoleTeamInfo>{
	public long teamid;
	public int autoacceptrequest; // 是否自动接收别人的入队申请
	public int autoacceptinvite; // 是否自动接收别人的入队邀请

	public RoleTeamInfo() {
	}

	public RoleTeamInfo(long _teamid_, int _autoacceptrequest_, int _autoacceptinvite_) {
		this.teamid = _teamid_;
		this.autoacceptrequest = _autoacceptrequest_;
		this.autoacceptinvite = _autoacceptinvite_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(teamid);
		_os_.marshal(autoacceptrequest);
		_os_.marshal(autoacceptinvite);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		teamid = _os_.unmarshal_long();
		autoacceptrequest = _os_.unmarshal_int();
		autoacceptinvite = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleTeamInfo) {
			RoleTeamInfo _o_ = (RoleTeamInfo)_o1_;
			if (teamid != _o_.teamid) return false;
			if (autoacceptrequest != _o_.autoacceptrequest) return false;
			if (autoacceptinvite != _o_.autoacceptinvite) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)teamid;
		_h_ += autoacceptrequest;
		_h_ += autoacceptinvite;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(teamid).append(",");
		_sb_.append(autoacceptrequest).append(",");
		_sb_.append(autoacceptinvite).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(RoleTeamInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(teamid - _o_.teamid);
		if (0 != _c_) return _c_;
		_c_ = autoacceptrequest - _o_.autoacceptrequest;
		if (0 != _c_) return _c_;
		_c_ = autoacceptinvite - _o_.autoacceptinvite;
		if (0 != _c_) return _c_;
		return _c_;
	}

}


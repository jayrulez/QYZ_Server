
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XCreateTeamFight__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XCreateTeamFight extends __XCreateTeamFight__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6700231;

	public int getType() {
		return 6700231;
	}

	public int ectypeid;
	public int serverid;
	public int levelgroupid;
	public java.util.ArrayList<map.msg.TeamFightMember> team1;
	public java.util.ArrayList<map.msg.TeamFightMember> team2;

	public XCreateTeamFight() {
		team1 = new java.util.ArrayList<map.msg.TeamFightMember>();
		team2 = new java.util.ArrayList<map.msg.TeamFightMember>();
	}

	public XCreateTeamFight(int _ectypeid_, int _serverid_, int _levelgroupid_, java.util.ArrayList<map.msg.TeamFightMember> _team1_, java.util.ArrayList<map.msg.TeamFightMember> _team2_) {
		this.ectypeid = _ectypeid_;
		this.serverid = _serverid_;
		this.levelgroupid = _levelgroupid_;
		this.team1 = _team1_;
		this.team2 = _team2_;
	}

	public final boolean _validator_() {
		for (map.msg.TeamFightMember _v_ : team1)
			if (!_v_._validator_()) return false;
		for (map.msg.TeamFightMember _v_ : team2)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(serverid);
		_os_.marshal(levelgroupid);
		_os_.compact_uint32(team1.size());
		for (map.msg.TeamFightMember _v_ : team1) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(team2.size());
		for (map.msg.TeamFightMember _v_ : team2) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		serverid = _os_.unmarshal_int();
		levelgroupid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.TeamFightMember _v_ = new map.msg.TeamFightMember();
			_v_.unmarshal(_os_);
			team1.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.TeamFightMember _v_ = new map.msg.TeamFightMember();
			_v_.unmarshal(_os_);
			team2.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XCreateTeamFight) {
			XCreateTeamFight _o_ = (XCreateTeamFight)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (serverid != _o_.serverid) return false;
			if (levelgroupid != _o_.levelgroupid) return false;
			if (!team1.equals(_o_.team1)) return false;
			if (!team2.equals(_o_.team2)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += serverid;
		_h_ += levelgroupid;
		_h_ += team1.hashCode();
		_h_ += team2.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(levelgroupid).append(",");
		_sb_.append(team1).append(",");
		_sb_.append(team2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


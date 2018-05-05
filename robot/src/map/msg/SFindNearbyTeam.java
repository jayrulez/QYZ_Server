
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFindNearbyTeam__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFindNearbyTeam extends __SFindNearbyTeam__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6708982;

	public int getType() {
		return 6708982;
	}

	public java.util.ArrayList<map.msg.TeamInfo> teamlist;

	public SFindNearbyTeam() {
		teamlist = new java.util.ArrayList<map.msg.TeamInfo>();
	}

	public SFindNearbyTeam(java.util.ArrayList<map.msg.TeamInfo> _teamlist_) {
		this.teamlist = _teamlist_;
	}

	public final boolean _validator_() {
		for (map.msg.TeamInfo _v_ : teamlist)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(teamlist.size());
		for (map.msg.TeamInfo _v_ : teamlist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.TeamInfo _v_ = new map.msg.TeamInfo();
			_v_.unmarshal(_os_);
			teamlist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFindNearbyTeam) {
			SFindNearbyTeam _o_ = (SFindNearbyTeam)_o1_;
			if (!teamlist.equals(_o_.teamlist)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += teamlist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(teamlist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}



package lx.matcher.guardtower;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MGuardTowerMatchSucc__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MGuardTowerMatchSucc extends __MGuardTowerMatchSucc__ {
	@Override
	protected void process() {
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6641384;

	public int getType() {
		return 6641384;
	}

	public long mapid;
	public java.util.ArrayList<Long> myteammembers;
	public lx.gs.map.msg.MatchTeamInfo team;

	public MGuardTowerMatchSucc() {
		myteammembers = new java.util.ArrayList<Long>();
		team = new lx.gs.map.msg.MatchTeamInfo();
	}

	public MGuardTowerMatchSucc(long _mapid_, java.util.ArrayList<Long> _myteammembers_, lx.gs.map.msg.MatchTeamInfo _team_) {
		this.mapid = _mapid_;
		this.myteammembers = _myteammembers_;
		this.team = _team_;
	}

	public final boolean _validator_() {
		if (!team._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(mapid);
		_os_.compact_uint32(myteammembers.size());
		for (Long _v_ : myteammembers) {
			_os_.marshal(_v_);
		}
		_os_.marshal(team);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		mapid = _os_.unmarshal_long();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			myteammembers.add(_v_);
		}
		team.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MGuardTowerMatchSucc) {
			MGuardTowerMatchSucc _o_ = (MGuardTowerMatchSucc)_o1_;
			if (mapid != _o_.mapid) return false;
			if (!myteammembers.equals(_o_.myteammembers)) return false;
			if (!team.equals(_o_.team)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)mapid;
		_h_ += myteammembers.hashCode();
		_h_ += team.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mapid).append(",");
		_sb_.append(myteammembers).append(",");
		_sb_.append(team).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


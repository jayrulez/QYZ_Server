
package lx.matcher;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MTeamFightMatchSucc__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MTeamFightMatchSucc extends __MTeamFightMatchSucc__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6642043;

	public int getType() {
		return 6642043;
	}

	public long mapid;
	public java.util.ArrayList<Long> myteammembers;
	public lx.gs.map.msg.MatchTeamInfo team1;
	public lx.gs.map.msg.MatchTeamInfo team2;

	public MTeamFightMatchSucc() {
		myteammembers = new java.util.ArrayList<Long>();
		team1 = new lx.gs.map.msg.MatchTeamInfo();
		team2 = new lx.gs.map.msg.MatchTeamInfo();
	}

	public MTeamFightMatchSucc(long _mapid_, java.util.ArrayList<Long> _myteammembers_, lx.gs.map.msg.MatchTeamInfo _team1_, lx.gs.map.msg.MatchTeamInfo _team2_) {
		this.mapid = _mapid_;
		this.myteammembers = _myteammembers_;
		this.team1 = _team1_;
		this.team2 = _team2_;
	}

	public final boolean _validator_() {
		if (!team1._validator_()) return false;
		if (!team2._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(mapid);
		_os_.compact_uint32(myteammembers.size());
		for (Long _v_ : myteammembers) {
			_os_.marshal(_v_);
		}
		_os_.marshal(team1);
		_os_.marshal(team2);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		mapid = _os_.unmarshal_long();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			myteammembers.add(_v_);
		}
		team1.unmarshal(_os_);
		team2.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MTeamFightMatchSucc) {
			MTeamFightMatchSucc _o_ = (MTeamFightMatchSucc)_o1_;
			if (mapid != _o_.mapid) return false;
			if (!myteammembers.equals(_o_.myteammembers)) return false;
			if (!team1.equals(_o_.team1)) return false;
			if (!team2.equals(_o_.team2)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)mapid;
		_h_ += myteammembers.hashCode();
		_h_ += team1.hashCode();
		_h_ += team2.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mapid).append(",");
		_sb_.append(myteammembers).append(",");
		_sb_.append(team1).append(",");
		_sb_.append(team2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


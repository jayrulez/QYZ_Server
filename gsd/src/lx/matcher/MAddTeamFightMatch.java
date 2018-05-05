
package lx.matcher;

import cfg.ectype.MatchType;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gnet.link.Onlines;
import lx.gs.map.FMap;
import lx.gs.map.msg.SBeginMatchTeamFight;

import java.util.HashSet;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MAddTeamFightMatch__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MAddTeamFightMatch extends __MAddTeamFightMatch__ {
	@Override
	protected void process() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                if(err == 0) {
                    for (long roleid : members) {
                        FMap.beginMatch(roleid, FMap.getEctype(roleid), MatchType.TEAM_FIGHT);
                    }
                } else if(err == ErrorCode.MATCH_TIMEOUT.getErrorId() || err == ErrorCode.INTERNAL_ERR.getErrorId()) {
                    for(long roleid : members)
                        FMap.cancelMatch(roleid);
                }
                Onlines.getInstance().multicast(members, new SBeginMatchTeamFight(err));
                return true;
            }
        }.execute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6637427;

	public int getType() {
		return 6637427;
	}

	public int err;
	public java.util.ArrayList<Long> members;

	public MAddTeamFightMatch() {
		members = new java.util.ArrayList<Long>();
	}

	public MAddTeamFightMatch(int _err_, java.util.ArrayList<Long> _members_) {
		this.err = _err_;
		this.members = _members_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(err);
		_os_.compact_uint32(members.size());
		for (Long _v_ : members) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		err = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			members.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MAddTeamFightMatch) {
			MAddTeamFightMatch _o_ = (MAddTeamFightMatch)_o1_;
			if (err != _o_.err) return false;
			if (!members.equals(_o_.members)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += err;
		_h_ += members.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(err).append(",");
		_sb_.append(members).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


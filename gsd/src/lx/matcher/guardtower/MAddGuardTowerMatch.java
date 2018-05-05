
package lx.matcher.guardtower;

import cfg.CfgMgr;
import cfg.ectype.MatchType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gnet.link.Onlines;
import lx.gs.map.FMap;
import lx.gs.map.msg.SGuardTowerMatchError;
import lx.gs.map.msg.SGuardTowerMatchStart;
import lx.gs.map.msg.SGuardTowerMatcherUpdate;
import lx.gs.role.FRole;
import lx.gs.role.msg.RoleShowInfo4;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MAddGuardTowerMatch__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MAddGuardTowerMatch extends __MAddGuardTowerMatch__ {
	@Override
	protected void process() {
		new xdb.Procedure() {
			@Override
			protected boolean process() {
				if(err == 0) {
					for (long roleid : members) {
						FMap.beginMatch(roleid, FMap.getEctype(roleid), MatchType.GUARD_TOWER);
					}
					final SGuardTowerMatcherUpdate msg = new SGuardTowerMatcherUpdate();
					msg.matched = 0;
					members.forEach(aLong -> msg.teaminfo.members.add(FRole.genRoleShowInfo(aLong, new RoleShowInfo4())));
					msg.countdown = CfgMgr.guardtower.entercountdown;

					Onlines.getInstance().multicast(members, msg);
					Onlines.getInstance().multicast(members, new SGuardTowerMatchStart());
				} else if(err == ErrorCode.MATCH_TIMEOUT.getErrorId() || err == ErrorCode.INTERNAL_ERR.getErrorId()) {
					for(long roleid : members){
						FMap.cancelMatch(roleid);
					}
					Onlines.getInstance().multicast(members, new SGuardTowerMatchError(err));
				}
				return true;
			}
		}.execute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6642697;

	public int getType() {
		return 6642697;
	}

	public int err;
	public java.util.ArrayList<Long> members;

	public MAddGuardTowerMatch() {
		members = new java.util.ArrayList<Long>();
	}

	public MAddGuardTowerMatch(int _err_, java.util.ArrayList<Long> _members_) {
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
		if (_o1_ instanceof MAddGuardTowerMatch) {
			MAddGuardTowerMatch _o_ = (MAddGuardTowerMatch)_o1_;
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


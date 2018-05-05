
package lx.gs.map.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.map.attackcity.AttackCityMatcher;
import lx.gs.map.attackcity.AttackCityModule;
import lx.gs.team.FTeam;
import xdb.Lockeys;

import java.util.ArrayList;
import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEnrollAttackCity__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEnrollAttackCity extends __CEnrollAttackCity__ {
	@Override
	protected void process() {
        new AProcedure<CEnrollAttackCity>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                final xbean.Team team = FTeam.selectTeamByRoleId(roleid);
                final List<Long> roleids = new ArrayList<>();
                if(team != null) {
                    roleids.addAll(team.getMembers().keySet());
                } else {
                    roleids.add(roleid);
                }
                final List<Long> members = new ArrayList<>();
                Lockeys.lock(xtable.Roleinfos.getTable(), roleids);
                int gid = -1;

                for(long rid : roleids) {
                    final xbean.RoleInfo roleInfo = xtable.Roleinfos.get(rid);
                    members.add(rid);
                    final int level = roleInfo.getLevel();
                    if(level < CfgMgr.attackcity.requirelevel.level)
                        return error(ErrorCode.SOME_MEMBER_LEVEL_TOO_LOW);
                    final int groupid = AttackCityModule.INSTANCE.matcher.calcGroupid(level);
                    if(gid != -1 && gid != groupid)
                        return error(ErrorCode.MEMBER_NOT_SAME_MATCH_GROUP);
                    gid = groupid;
                }

                final ErrorCode err = AttackCityModule.INSTANCE.enroll(new AttackCityMatcher.Team(gid, members));
                if(err.err())
                    return error(err);
                response(new SEnrollAttackCity());
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565130;

	public int getType() {
		return 6565130;
	}


	public CEnrollAttackCity() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEnrollAttackCity) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CEnrollAttackCity _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


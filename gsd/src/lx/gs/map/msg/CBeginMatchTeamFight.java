
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.ectype.TeamFightStage;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.map.FMap;
import lx.gs.map.TeamFightModule;
import lx.gs.team.FTeam;
import xdb.Lockeys;

import java.util.ArrayList;
import java.util.List;


// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBeginMatchTeamFight__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBeginMatchTeamFight extends __CBeginMatchTeamFight__ {
	@Override
	protected void process() {
		new AProcedure<CBeginMatchTeamFight>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                final xbean.RoleEctype info = FMap.getEctype(roleid);
                if(TeamFightModule.INSTANCE.stage != TeamFightStage.OPEN)
                    return error(ErrorCode.TEAM_FIGHT_CLOSED);
                if(FMap.isForbidMatch(info))
                    return error(ErrorCode.FORBID_MATCH);
                final xbean.Team team = FTeam.selectTeamByRoleId(roleid);
                final List<Long> roleids = new ArrayList<>();
                if(team != null) {
                    if(team.getLeaderid() != roleid)
                        return error(ErrorCode.ONLY_TEAM_LEADER_CAN_START);
                    roleids.addAll(team.getMembers().keySet());
                } else {
                    roleids.add(roleid);
                }
                final List<Long> members = new ArrayList<>();
                Lockeys.lock(xtable.Roleinfos.getTable(), roleids);
                int minVipLevel = Integer.MAX_VALUE;
                long gid = -1;

                for(long rid : roleids) {
                    if (!FMap.isInWorldOrFamily(roleid)) {
                        return error(ErrorCode.SOME_MEMBER_IN_ECTYPE);
                    }
                    final xbean.RoleInfo roleInfo = xtable.Roleinfos.get(rid);
                    members.add(rid);
                    final int level = roleInfo.getLevel();
                    if(level < CfgMgr.teamfight.levellimit)
                        return error(ErrorCode.SOME_MEMBER_LEVEL_TOO_LOW);
                    final long groupid = TeamFightModule.INSTANCE.calcGroupid(level);
                    if(gid == -1) {
                        gid = groupid;
                    } else if(groupid != gid) {
                        return error(ErrorCode.MEMBER_NOT_SAME_MATCH_GROUP);
                    }
                    minVipLevel = Math.min(minVipLevel, roleInfo.getViplevel());
                }
                TeamFightModule.INSTANCE.addMatch(gid, members, minVipLevel);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566806;

	public int getType() {
		return 6566806;
	}


	public CBeginMatchTeamFight() {
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
		if (_o1_ instanceof CBeginMatchTeamFight) {
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

	public int compareTo(CBeginMatchTeamFight _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


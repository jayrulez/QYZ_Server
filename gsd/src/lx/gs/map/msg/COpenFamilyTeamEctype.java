
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.cmd.CmdId;
import cfg.cmd.ConfigId;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.team.FTeam;
import map.msg.FamilyTeamMember;
import map.msg.XCreateFamilyTeam;
import xbean.TeamMember;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __COpenFamilyTeamEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class COpenFamilyTeamEctype extends __COpenFamilyTeamEctype__ {
	@Override
	protected void process() {
        new AProcedure<COpenFamilyTeamEctype>(this) {
            @Override
            protected boolean doProcess() {
                final xbean.Team team = FTeam.getTeamByRoleId(roleid);
                if(team == null)
                    return error(ErrorCode.FAMILY_TEAM_NOT_INT_TEAM);
                if(roleid != team.getLeaderid())
                    return error(ErrorCode.FAMILY_TEAM_MEMBER_CANNOT_APPLY);
                final Map<Long, TeamMember> members = team.getMembers();
                if(members.size() < CfgMgr.familyteam.minteammembernum)
                    return error(ErrorCode.FAMILY_TEAM_MEMBER_NOT_ENOUGH);

                long familyid = -1;
                final XCreateFamilyTeam msg = new XCreateFamilyTeam();
                for(long mid : members.keySet()) {
                    if(!FMap.isInWorldOrFamily(mid))
                        return error(ErrorCode.SOME_MEMBER_IN_ECTYPE);
                    final Long mfid = xtable.Rolefamily.selectCurrentfid(mid);
                    if(mfid == null || mfid == 0)
                        return error(ErrorCode.FAMILY_TEAM_NOT_INT_FAMLIY);
                    if(familyid == -1) {
                        familyid = mfid;
                    } else if(familyid != mfid) {
                        return error(ErrorCode.FAMILY_TEAM_NOT_IN_SAME_FAMILY);
                    }
                    msg.level += xtable.Roleinfos.selectLevel(mid);
                    final FamilyTeamMember mem = new FamilyTeamMember();
                    msg.serverid = gs.Utils.getServerId();
                    mem.roleid = mid;
                    new xdb.Procedure() {
                        @Override
                        protected boolean process() {
                            mem.hasbonus = common.Utils.toByte(FCondition.checkA(mid, CfgMgr.familyteam.rewardfinishnum, 1, By.Family_Team, ConfigId.FAMILY_TEAM_ECTYPE, 0).ok());
                            return false;
                        }
                    }.call();
                    msg.members.add(mem);
                }
                if(msg.members.isEmpty())
                    return false;
                msg.level = msg.level / msg.members.size();
                FMap.createMapInProcedure(gs.Utils.getServerId(), msg, new FMap.CreateMapCallback<XCreateFamilyTeam>() {
                    @Override
                    public void onSucc(XCreateFamilyTeam builder, long mapid) {
                        for(FamilyTeamMember member : msg.members) {
                            FMap.enterMapNotInProcedure(member.roleid, mapid);
                        }
                    }
                });
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566922;

	public int getType() {
		return 6566922;
	}


	public COpenFamilyTeamEctype() {
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
		if (_o1_ instanceof COpenFamilyTeamEctype) {
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

	public int compareTo(COpenFamilyTeamEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


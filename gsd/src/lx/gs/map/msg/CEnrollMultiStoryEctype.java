
package lx.gs.map.msg;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gnet.ServiceClient;
import gs.AProcedure;
import lx.gs.map.FMap;
import lx.gs.map.MapModule;
import lx.gs.team.FTeam;
import lx.matcher.GAddMultiMatch;
import xdb.Lockeys;

import java.util.HashSet;
import java.util.Set;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEnrollMultiStoryEctype__ extends xio.Protocol { }

/** 报名组队剧情副本
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEnrollMultiStoryEctype extends __CEnrollMultiStoryEctype__ {
	@Override
	protected void process() {
		new AProcedure<CEnrollMultiStoryEctype>(this){
            @Override
            protected boolean doProcess() throws Exception {
                xbean.RoleTeamInfo teamInfo = FTeam.selectRoleTeamInfo(roleid);
                Set<Long> enrollPlayers = new HashSet<>();
                //队伍检查
                if(enrolltype == SINGLE){
                    if(teamInfo.getTeamid() != 0){ //此时客户端需要选择是否离开当前队伍并进入单排
                        return error(ErrorCode.ALREADY_IN_TEAM);
                    }
                    enrollPlayers.add(roleid);
                }else if(enrolltype == TEAM){
                    if(teamInfo.getTeamid() == 0){
                        return error(ErrorCode.NOT_IN_TEAM);
                    }
                    xbean.Team team = FTeam.selectTeamByTeamId(teamInfo.getTeamid());
                    if(team.getLeaderid() != roleid){
                        return error(ErrorCode.ONLY_TEAM_LEADER_CAN_START);
                    }
                    Set<Long> members = team.getMembers().keySet();
                    if(members.size() > MapModule.MULTI_STORY_MATCH_NUMS){
                        return error(ErrorCode.HAS_EXCEED_MAX_ENROLL_NUMS);
                    }
                    enrollPlayers.addAll(members);
                }else {
                    return error(ErrorCode.PARAM_ERROR);
                }
                cfg.ectype.TeamStoryEctype conf = CfgMgr.teamstoryectype.get(ectypeid);
                //先检查队长，再检查队员
                ErrorCode ret1 = FMap.checkTeamLeader(roleid, ectypeid, conf);
                if(ret1.err()){
                    return error(ret1);
                }
                ErrorCode ret = FMap.checkEnrollMultiCondition(enrollPlayers, ectypeid, conf);
                if(ret.err()){
                    return error(ret);
                }

                lock(Lockeys.get(xtable.Locks.ROLELOCK, enrollPlayers));
                for(long i : enrollPlayers){ //设置匹配状态
                    xbean.RoleEctype info = FMap.getEctype(i);
                    if(FMap.isForbidMatch(info)){
                        return error(ErrorCode.FORBID_MATCH);
                    }
//                    FMap.beginMatch(roleid, info, MatchType.MULTI_STORY);
                }

                SEnrollMultiStoryEctype response = new SEnrollMultiStoryEctype();
                response.ectypeid = ectypeid;
                response.enrolltype = enrolltype;
                xdb.Transaction.tsendWhileCommit(enrollPlayers, response);//通知所有参与匹配的玩家
                GAddMultiMatch msg = new GAddMultiMatch();
                msg.gid = ectypeid;
                FMap.makeMatchMultiInfo(enrollPlayers, msg, ectypeid);
                ServiceClient.send(msg);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571201;

	public int getType() {
		return 6571201;
	}

	public final static int SINGLE = 1;
	public final static int TEAM = 2;

	public int ectypeid;
	public int enrolltype;

	public CEnrollMultiStoryEctype() {
	}

	public CEnrollMultiStoryEctype(int _ectypeid_, int _enrolltype_) {
		this.ectypeid = _ectypeid_;
		this.enrolltype = _enrolltype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(enrolltype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		enrolltype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEnrollMultiStoryEctype) {
			CEnrollMultiStoryEctype _o_ = (CEnrollMultiStoryEctype)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (enrolltype != _o_.enrolltype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += enrolltype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(enrolltype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CEnrollMultiStoryEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		_c_ = enrolltype - _o_.enrolltype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


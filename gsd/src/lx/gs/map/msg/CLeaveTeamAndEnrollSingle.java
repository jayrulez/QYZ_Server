
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.ectype.MatchType;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gnet.ServiceClient;
import gs.AProcedure;
import lx.gs.map.FMap;
import lx.gs.map.MapModule;
import lx.gs.team.FTeam;
import lx.matcher.GAddMultiMatch;

import java.util.ArrayList;
import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CLeaveTeamAndEnrollSingle__ extends xio.Protocol { }

/** 离开当前队伍并单人报名
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CLeaveTeamAndEnrollSingle extends __CLeaveTeamAndEnrollSingle__ {
	@Override
	protected void process() {
		new AProcedure<CLeaveTeamAndEnrollSingle>(this){
            @Override
            protected boolean doProcess() throws Exception {
                //先退出队伍，再单排
                if(!FTeam.quitTeam(roleid)) return error(ErrorCode.NOT_IN_TEAM);
                List<Long> enrollPlayers = new ArrayList<>();
                enrollPlayers.add(roleid);
                cfg.ectype.TeamStoryEctype conf = CfgMgr.teamstoryectype.get(ectypeid);
                ErrorCode ret = FMap.checkTeamLeader(roleid, ectypeid, conf);
                if(ret.err()){
                    return error(ret);
                }
                xbean.RoleEctype info = FMap.getEctype(roleid);
                if(FMap.isForbidMatch(info)){
                    return error(ErrorCode.FORBID_MATCH);
                }
                GAddMultiMatch msg = new GAddMultiMatch();
                msg.gid = ectypeid;
                FMap.makeMatchMultiInfo(enrollPlayers, msg, ectypeid);
                ServiceClient.send(msg);
                SLeaveTeamAndEnrollSingle response = new SLeaveTeamAndEnrollSingle();
                response.ectypeid = ectypeid;
                response(response);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6567076;

	public int getType() {
		return 6567076;
	}

	public int ectypeid;

	public CLeaveTeamAndEnrollSingle() {
	}

	public CLeaveTeamAndEnrollSingle(int _ectypeid_) {
		this.ectypeid = _ectypeid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CLeaveTeamAndEnrollSingle) {
			CLeaveTeamAndEnrollSingle _o_ = (CLeaveTeamAndEnrollSingle)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CLeaveTeamAndEnrollSingle _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


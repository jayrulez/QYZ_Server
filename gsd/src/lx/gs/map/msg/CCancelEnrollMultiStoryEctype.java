
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gnet.ServiceClient;
import gs.AProcedure;
import lx.gs.map.FMap;
import lx.gs.map.MapModule;
import lx.matcher.GCancelMultiMatch;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCancelEnrollMultiStoryEctype__ extends xio.Protocol { }

/** 取消报名
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCancelEnrollMultiStoryEctype extends __CCancelEnrollMultiStoryEctype__ {
	@Override
	protected void process() {
		new AProcedure<CCancelEnrollMultiStoryEctype>(this){
            @Override
            protected boolean doProcess() throws Exception {
//                xbean.RoleTeamInfo teamInfo = FTeam.getRoleTeamInfo(roleid);
//                Set<Long> enrollPlayers = new HashSet<>();
//                xbean.Team team = FTeam.getTeamByTeamId(teamInfo.getTeamid());
//                if(team != null) {//如果是组队，则通知所有队员取消
//                    Set<Long> members = team.getMembers().keySet();
//                    if (members.size() > MapModule.MULTI_STORY_MATCH_NUMS) {
//                        return error(ErrorCode.HAS_EXCEED_MAX_ENROLL_NUMS);
//                    }
//                    enrollPlayers.addAll(members);
//                }else {
//                    enrollPlayers.add(roleid);
//                }
                if(!FMap.isInMatch(FMap.getEctype(roleid))){
                    return error(ErrorCode.NOT_INT_MATCH);
                }
                GCancelMultiMatch msg = new GCancelMultiMatch();
                msg.roleid = roleid;
                ServiceClient.send(msg);
                SCancelEnrollMultiStoryEctype response = new SCancelEnrollMultiStoryEctype();
                response.result = ErrorCode.OK.getErrorId();
                xdb.Transaction.tsendWhileCommit(roleid, response);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6585326;

	public int getType() {
		return 6585326;
	}


	public CCancelEnrollMultiStoryEctype() {
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
		if (_o1_ instanceof CCancelEnrollMultiStoryEctype) {
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

	public int compareTo(CCancelEnrollMultiStoryEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


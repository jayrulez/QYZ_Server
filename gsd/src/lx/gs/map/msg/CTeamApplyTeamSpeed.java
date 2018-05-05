
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.map.teamspeed.TeamSpeedModule;
import lx.gs.team.FTeam;
import xdb.Lockeys;
import xdb.Transaction;

import java.util.List;
import java.util.stream.Collectors;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CTeamApplyTeamSpeed__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CTeamApplyTeamSpeed extends __CTeamApplyTeamSpeed__ {
	@Override
	protected void process() {
		new AProcedure<CTeamApplyTeamSpeed>(this){
			@Override
			protected boolean doProcess() throws Exception {

				if(!FTeam.isInTeam(roleid) || !FTeam.isTeamLeader(roleid)) {
					error(ErrorCode.ONLY_TEAM_LEADER_CAN_START);
					return false;
				}

				List<Long> applyRoleIds = FTeam.selectTeamByRoleId(roleid).getMembers().keySet().stream().map(aLong -> aLong).collect(Collectors.toList());

				Lockeys.lock(xtable.Roleinfos.getTable(), applyRoleIds);

				boolean result = TeamSpeedModule.INSTANCE.applyMatch(roleid, applyRoleIds);
				if(result) applyRoleIds.forEach(aLong -> Transaction.tsendWhileCommit(aLong, new SApplyTeamSpeedSucc()));
				return result;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575507;

	public int getType() {
		return 6575507;
	}


	public CTeamApplyTeamSpeed() {
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
		if (_o1_ instanceof CTeamApplyTeamSpeed) {
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

	public int compareTo(CTeamApplyTeamSpeed _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


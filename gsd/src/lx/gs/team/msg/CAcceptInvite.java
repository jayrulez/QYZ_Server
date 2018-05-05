
package lx.gs.team.msg;

import cfg.tips.LocationType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.team.FTeam;
import lx.gs.tips.FTips;
import cfg.tips.TipsCode;
import xdb.Lockeys;
import xdb.Transaction;
import xtable.Roleinfos;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAcceptInvite__ extends xio.Protocol { }

/** 接收和拒绝入队申请，入队邀请，队长转移邀请，跟随邀请
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAcceptInvite extends __CAcceptInvite__ {
	@Override
	protected void process() {
		new AProcedure<CAcceptInvite>(this){
			@Override
			protected boolean doProcess() throws Exception {
				lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid, inviteroleid)); //都先进行加锁操作
				xbean.Team inviteTeam = FTeam.getTeamByRoleId(inviteroleid);
				xbean.Team myTeam = FTeam.getTeamByRoleId(roleid);

				if(inviteTeam != null && myTeam != null && inviteTeam.getTeamid() == myTeam.getTeamid()){
					return false;
				}

				if(!FTeam.isOnLine(inviteroleid)){
					return error(LocationType.ALERT, TipsCode.OFFLINE, Roleinfos.selectName(inviteroleid));
				}

				if(FTeam.isInTeam(roleid)){
					if(inviteTeam != null){
						return error(LocationType.ALERT, TipsCode.PLAYER_ALREADY_IN_TEAM, Roleinfos.selectName(inviteroleid));
					}
					if(FTeam.isTeamFull(myTeam)){
						return error(LocationType.ALERT, TipsCode.MY_TEAM_FULL);
					}
					return FTeam.addMember(myTeam, inviteroleid);
				}
				if(inviteTeam == null){
					FTeam.createTeam(inviteroleid); //必然成功
					Transaction.tsendWhileCommit(inviteroleid, FTips.create(LocationType.CENTER, TipsCode.MAKE_TEAM_SUCC));
					inviteTeam = FTeam.getTeamByRoleId(inviteroleid);
				}

				if(FTeam.isTeamFull(inviteTeam)){
					tsend(roleid, FTips.create(LocationType.ALERT, TipsCode.TEAM_FULL));
					return false;
				}
				return FTeam.addMember(inviteTeam, roleid);
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557815;

	public int getType() {
		return 6557815;
	}

	public long inviteroleid;

	public CAcceptInvite() {
	}

	public CAcceptInvite(long _inviteroleid_) {
		this.inviteroleid = _inviteroleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(inviteroleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		inviteroleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CAcceptInvite) {
			CAcceptInvite _o_ = (CAcceptInvite)_o1_;
			if (inviteroleid != _o_.inviteroleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)inviteroleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(inviteroleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CAcceptInvite _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(inviteroleid - _o_.inviteroleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


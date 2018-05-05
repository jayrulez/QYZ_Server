
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

abstract class __CInviteJoinTeam__ extends xio.Protocol { }

/** 邀请入队
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CInviteJoinTeam extends __CInviteJoinTeam__ {
	@Override
	protected void process() {
		new AProcedure<CInviteJoinTeam>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid, param.roleid)); //都先进行加锁操作
				long toroleid = param.roleid;

				xbean.Team myTeam = FTeam.getTeamByRoleId(roleid);
				boolean isMeInTeam = FTeam.isInTeam(roleid);
				boolean isMyTeamFull = isMeInTeam && FTeam.isTeamFull(myTeam);

				xbean.Team toRoleTeam = FTeam.getTeamByRoleId(toroleid);
				boolean isToRoleInTeam = FTeam.isInTeam(toroleid);
				boolean isToRoleTeamFull = isToRoleInTeam && FTeam.isTeamFull(toRoleTeam);

				if(myTeam != null && toRoleTeam != null && myTeam.getTeamid() == toRoleTeam.getTeamid()){
					return error(LocationType.CENTER, TipsCode.ALREADY_IN_TEAM);
				}

				if(!FTeam.isOnLine(toroleid)){
					Transaction.tsend(roleid, FTips.create(LocationType.CENTER, TipsCode.OFFLINE, Roleinfos.selectName(toroleid)));
					return false;
				}

				if(isMeInTeam){
					if(isToRoleInTeam){
						return error(LocationType.ALERT, TipsCode.PLAYER_ALREADY_IN_TEAM, Roleinfos.selectName(toroleid));
					}

					if(isMyTeamFull){
						return error(LocationType.ALERT, TipsCode.MY_TEAM_FULL);
					}

					response(LocationType.CENTER, TipsCode.INVITE_SUCC);
					if(FTeam.getRoleTeamInfo(toroleid).getAutoacceptinvite() > 0){
						//自动接收邀请，直接加入队伍
						return FTeam.addMember(myTeam, toroleid);
					}else{
						//发送邀请给对方
						tsendWhileCommit(toroleid, new SInviteJoinTeam(roleid, Roleinfos.selectName(roleid)));
						return true;
					}
				} else {
					if(isToRoleInTeam && isToRoleTeamFull){
						return error(LocationType.ALERT, TipsCode.PLAYER_TEAM_FULL);
					}
					response(LocationType.CENTER, TipsCode.INVITE_SUCC);
					if(!isToRoleInTeam){
						if(FTeam.getRoleTeamInfo(toroleid).getAutoacceptinvite() > 0){//自动接收邀请，直接加入队伍
							FTeam.createTeam(roleid); //必然成功
							return FTeam.addMember(FTeam.getTeamByRoleId(roleid), toroleid);
						}else{
							//发送邀请给对方
							tsendWhileCommit(toroleid, new SInviteJoinTeam(roleid, Roleinfos.selectName(roleid)));
							return true;
						}
					}
					return FTeam.requestJoin(roleid, toRoleTeam.getTeamid());
				}
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557809;

	public int getType() {
		return 6557809;
	}

	public long roleid; // 邀请角色id,只发送邀请给角色，如果角色设置自动接收组队邀请，就自动成为队友

	public CInviteJoinTeam() {
	}

	public CInviteJoinTeam(long _roleid_) {
		this.roleid = _roleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CInviteJoinTeam) {
			CInviteJoinTeam _o_ = (CInviteJoinTeam)_o1_;
			if (roleid != _o_.roleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CInviteJoinTeam _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


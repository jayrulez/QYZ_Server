
package lx.gs.team.msg;

import cfg.tips.LocationType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.team.FTeam;
import cfg.tips.TipsCode;
import xtable.Roleinfos;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CTransferLeader__ extends xio.Protocol { }

/** 转移队长身份
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CTransferLeader extends __CTransferLeader__ {
	@Override
	protected void process() {
		new AProcedure<CTransferLeader>(this) {
			@Override
			protected boolean doProcess() throws Exception {

				xbean.Team team = FTeam.getTeamByRoleId(roleid);
				if(!FTeam.isTeamLeader(roleid)){
					return error(LocationType.ALERT, TipsCode.NOT_LEADER);
				}
				if(!FTeam.isTeamMember(team.getTeamid(), memberid)){
					return error(LocationType.ALERT, TipsCode.NOT_IN_TEAM, Roleinfos.selectName(memberid));
				}
				team.setLeaderid(memberid);

				FTeam.syncTeamToMap(team);
				FTeam.broadcastTeamByTeamid(team.getTeamid(), new STransferLeader(memberid));
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557827;

	public int getType() {
		return 6557827;
	}

	public long memberid; // 要转移给的队友id

	public CTransferLeader() {
	}

	public CTransferLeader(long _memberid_) {
		this.memberid = _memberid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(memberid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		memberid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CTransferLeader) {
			CTransferLeader _o_ = (CTransferLeader)_o1_;
			if (memberid != _o_.memberid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)memberid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(memberid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CTransferLeader _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(memberid - _o_.memberid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


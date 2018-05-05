
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import lx.gs.cmd.FAction;
import lx.gs.cmd.FCmd.Context;
import common.ErrorCode;
import lx.gs.friend.FFriend;
import lx.gs.friend.msg.SClaimIdolAward;
import lx.gs.logger.By;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CClaimIdolAward__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CClaimIdolAward extends __CClaimIdolAward__ {
	@Override
	protected void process() {
		new AProcedure<CClaimIdolAward>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				SClaimIdolAward result = new SClaimIdolAward();
				result.awardid = awardid;
				result.idolid = idolid;

				if (awardid <= 0 || idolid <= 0)
					return error(ErrorCode.PARAM_ERROR);
				xbean.RoleFriendsInfo info = FFriend.getRoleFriendsInfo(roleid);
				xbean.IdolAwardClaim v = info.getIdolawardclaiminfo().get(idolid);
				if (v == null) {
					v = xbean.Pod.newIdolAwardClaim();
					info.getIdolawardclaiminfo().put(idolid, v);
				}
                cfg.friend.BonusList bonusconf = cfg.CfgMgr.idol.get(idolid).bonuslist_bonusid.get(awardid);
                int degree = info.getIdolfrienddegree().getOrDefault(idolid, 0);
                if (degree < bonusconf.frienddegree) {
                    return error(ErrorCode.FRIEND_DEGREE_TOO_LOW);
                }
                if(!v.getClaiminfo().add(awardid)) {
                    return error(ErrorCode.IDOL_AWARD_ALREADY_CLAIMED);
                }
                Context ctx = FAction.processByReflection(roleid, bonusconf, By.Idol_Award);
                if (ctx.errcode.err()) {
                    return error(ctx.errcode);
                }
				response(result);
				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579519;

	public int getType() {
		return 6579519;
	}

	public long idolid; // 偶像的id
	public int awardid; // 奖励的id

	public CClaimIdolAward() {
	}

	public CClaimIdolAward(long _idolid_, int _awardid_) {
		this.idolid = _idolid_;
		this.awardid = _awardid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(idolid);
		_os_.marshal(awardid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		idolid = _os_.unmarshal_long();
		awardid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CClaimIdolAward) {
			CClaimIdolAward _o_ = (CClaimIdolAward)_o1_;
			if (idolid != _o_.idolid) return false;
			if (awardid != _o_.awardid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)idolid;
		_h_ += awardid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(idolid).append(",");
		_sb_.append(awardid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CClaimIdolAward _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(idolid - _o_.idolid);
		if (0 != _c_) return _c_;
		_c_ = awardid - _o_.awardid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

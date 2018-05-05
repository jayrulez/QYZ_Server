
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import cfg.CfgMgr;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import common.ErrorCode;
import lx.gs.friend.FFriend;
import lx.gs.marriage.FMarriage;
import xdb.Lockeys;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAttemptPropose__ extends xio.Protocol { }

/** 判断是否满足结婚条件
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAttemptPropose extends __CAttemptPropose__ {
	@Override
	protected void process() {
		new AProcedure<CAttemptPropose>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid,beproposedroleid)); //注意加锁
                xbean.RoleMarriage beMarryInfo = FMarriage.getMarriageInfo(beproposedroleid);
                xbean.RoleMarriage marryInfo = FMarriage.getMarriageInfo(roleid);
                if(!FFriend.isFriendOfRole(roleid, beproposedroleid)){
                    return error(ErrorCode.FRIEND_NOT_FRIEND);
                }

                if(marryInfo.getCoupleroleid() != 0 ){//如果任意一方已经结婚了
                    return error(ErrorCode.HAS_MARRIED);
                }

                if(beMarryInfo.getCoupleroleid() != 0){
                    return error(ErrorCode.OTHER_HAS_MARRIED);
                }

                if(System.currentTimeMillis() - marryInfo.getStartproposetime() >= FMarriage.PROPOSE_INTERVAL){
                    marryInfo.setCurproposeid(0);
                    marryInfo.setStartproposetime(0);
                }

				if(marryInfo.getCurproposeid() !=0){//如果正在向别人求婚
					return error(ErrorCode.IS_PROPOSING);
				}

				xbean.RoleInfo marryRoleInfo = xtable.Roleinfos.select(roleid);
				xbean.RoleInfo beMarryRoleInfo = xtable.Roleinfos.select(beproposedroleid);
//				if(marryRoleInfo.getGender() == beMarryRoleInfo.getGender()){ //如果性别相同
//					return error(ErrorCode.MUST_DIFF_GENDER);
//				}
				final int limitLvl = CfgMgr.marrigeconfig.requirelevel;
                if(beMarryRoleInfo.getLevel() < limitLvl){//如果等级不够
                    return error(ErrorCode.MARRIAGE_LEVEL_NOT_ENOUGH);
                }
				if(marryRoleInfo.getLevel() < limitLvl){
                    return error(ErrorCode.NOT_ENOUGH_LEVEL);
                }
				SAttemptPropose response = new SAttemptPropose();
				response.errcode = ErrorCode.OK.getErrorId();
				response(response);
				return true;
			}
			
		}.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576050;

	public int getType() {
		return 6576050;
	}

	public long beproposedroleid; // 被求婚的id

	public CAttemptPropose() {
	}

	public CAttemptPropose(long _beproposedroleid_) {
		this.beproposedroleid = _beproposedroleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(beproposedroleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		beproposedroleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CAttemptPropose) {
			CAttemptPropose _o_ = (CAttemptPropose)_o1_;
			if (beproposedroleid != _o_.beproposedroleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)beproposedroleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(beproposedroleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CAttemptPropose _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(beproposedroleid - _o_.beproposedroleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


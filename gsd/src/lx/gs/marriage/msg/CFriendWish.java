
package lx.gs.marriage.msg;

import cfg.CfgMgr;
import cfg.cmd.action.Currencys;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.logger.By;
import lx.gs.marriage.FMarriage;
import xdb.Lockeys;

import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CFriendWish__ extends xio.Protocol { }

/** 好友祝福
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CFriendWish extends __CFriendWish__ {
	@Override
	protected void process() {
		new AProcedure<CFriendWish>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid, proposeroleid,beproposeroleid)); //都先进行加锁操作 
			    xbean.RoleMarriage marryInfo = FMarriage.getMarriageInfo(proposeroleid);
			    List<Long> friendList = marryInfo.getWishfriendlist();
			    if(friendList.contains(roleid)){
			    	return error(ErrorCode.HAS_WISHED);
			    }
			    friendList.add(roleid);//记录祝福过的好友
				SFriendWish response = new SFriendWish();
				Currencys currencys = CfgMgr.marrigeconfig.giftcurrency.get(proposetype == 0 ? 0 : 1);
				if(!FBonus.genAndAddBonus(roleid, currencys, common.Bonus.BindType.BIND, response.marrygift, By.Marry)){
					return false;
				}
				response(response);
				SFriendWishNotify wishNotify = new SFriendWishNotify();
				wishNotify.friendname = xtable.Roleinfos.get(roleid).getName();
				wishNotify.wishgift = response.marrygift;//结婚双方得到的祝福奖励和朋友是一样的
				FBonus.addBonusAlwaysSucc(proposeroleid, wishNotify.wishgift, By.Marry);
				FBonus.addBonusAlwaysSucc(beproposeroleid, wishNotify.wishgift, By.Marry);
				tsendWhileCommit(proposeroleid, wishNotify);
				tsendWhileCommit(beproposeroleid, wishNotify);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577940;

	public int getType() {
		return 6577940;
	}

	public long proposeroleid;
	public long beproposeroleid;
	public int proposetype;

	public CFriendWish() {
	}

	public CFriendWish(long _proposeroleid_, long _beproposeroleid_, int _proposetype_) {
		this.proposeroleid = _proposeroleid_;
		this.beproposeroleid = _beproposeroleid_;
		this.proposetype = _proposetype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(proposeroleid);
		_os_.marshal(beproposeroleid);
		_os_.marshal(proposetype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		proposeroleid = _os_.unmarshal_long();
		beproposeroleid = _os_.unmarshal_long();
		proposetype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFriendWish) {
			CFriendWish _o_ = (CFriendWish)_o1_;
			if (proposeroleid != _o_.proposeroleid) return false;
			if (beproposeroleid != _o_.beproposeroleid) return false;
			if (proposetype != _o_.proposetype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)proposeroleid;
		_h_ += (int)beproposeroleid;
		_h_ += proposetype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(proposeroleid).append(",");
		_sb_.append(beproposeroleid).append(",");
		_sb_.append(proposetype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CFriendWish _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(proposeroleid - _o_.proposeroleid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(beproposeroleid - _o_.beproposeroleid);
		if (0 != _c_) return _c_;
		_c_ = proposetype - _o_.proposetype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


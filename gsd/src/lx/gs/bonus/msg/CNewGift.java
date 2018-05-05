
package lx.gs.bonus.msg;

import cfg.bonus.BONUSTYPE;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import cfg.CfgMgr;
import common.Bonus;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import common.ErrorCode;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CNewGift__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CNewGift extends __CNewGift__ {
	@Override
	protected void process() {
		new AProcedure<CNewGift>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleWelfareInfo welfare = FBonus.get(roleid);
				xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
				SNewGift response = new SNewGift();
				java.util.List<Integer> list = welfare.getReceivednewgift();
				if(list.contains(newgiftid)){ //如果已经领取过
					return error(ErrorCode.HAS_RECEIVED);
				}else{
					if(newgiftid > roleinfo.getLogindaycount()){ //能领取的新手礼包跟登录天数有关
						return error(ErrorCode.NOT_ENOUGH_LOGIN_DAYS);
					}
					list.add(newgiftid);
					response.newgiftid = newgiftid;
				}
                FLogger.welfare(roleid, roleinfo, BONUSTYPE.NEW_GIFT, newgiftid);
				boolean result = FBonus.addBonus(roleid, CfgMgr.beginnerbonus.get(newgiftid).bonuslist, Bonus.BindType.BIND, By.NewerPlayer);
				if(result) response(response);
				return result;
			}
			
		}.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556105;

	public int getType() {
		return 6556105;
	}

	public int newgiftid;

	public CNewGift() {
	}

	public CNewGift(int _newgiftid_) {
		this.newgiftid = _newgiftid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(newgiftid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		newgiftid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CNewGift) {
			CNewGift _o_ = (CNewGift)_o1_;
			if (newgiftid != _o_.newgiftid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += newgiftid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(newgiftid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CNewGift _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = newgiftid - _o_.newgiftid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


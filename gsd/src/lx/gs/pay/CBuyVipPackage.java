
package lx.gs.pay;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import cfg.currency.CurrencyType;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBuyVipPackage__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBuyVipPackage extends __CBuyVipPackage__ {
	@Override
	protected void process() {
        new AProcedure<CBuyVipPackage>(this){
            @Override
            protected boolean doProcess() throws Exception {
                if(index == -1){//get first pay bonus
                    xbean.RolePay rolePay = FPay.getRolePay(roleid);
                    if(!rolePay.getCangetfirstbonus()){
                        return error(ErrorCode.NOT_PAY);
                    }
                    if(!rolePay.getIsfirstpayused()){
                        if(!FBonus.addBonus(roleid, CfgMgr.firstcharge.bonus, common.Bonus.BindType.BIND, By.Rmb_Pay)){
							return false;
						}
						rolePay.setIsfirstpayused(true);
                    }else {
                        return error(ErrorCode.PAY_AWARD_HAS_GOT);
                    }
                }else {//normal buy
                    xbean.RoleInfo roleInfo = FBonus.getRoleInfo(roleid);
                    final int vipLevel = roleInfo.getViplevel();
                    if (vipLevel < index) {
                        return error(ErrorCode.NOT_ENOUGH_VIP_LEVEL);
                    }
                    //vippackage buy
                    cfg.bonus.VipBonus conf = CfgMgr.vipbonus.get(index);
                    ErrorCode ret = FCondition.checkByReflection(roleid, conf, 1, By.Vip_Package_buy, ConfigId.VIP_PACKAGE_BUY, index);
                    if (ret.err()) {
                        return error(ret);
                    }
                    if(!FBonus.addBonus(roleid, conf.gainbonus, common.Bonus.BindType.BIND, By.Vip_Package_buy)){
						return false;
					}
                    //use viplevel as the config key, each vip package only can buy once
                    FLogger.shop_trade(roleid, roleInfo, conf.viplevel, 1, CurrencyType.YuanBao, conf.price.amount);
                }
                SBuyVipPackage response = new SBuyVipPackage(index);
                response(response);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586079;

	public int getType() {
		return 6586079;
	}

	public int index; // 购买vip礼包，如果是领取首冲奖励，为-1

	public CBuyVipPackage() {
	}

	public CBuyVipPackage(int _index_) {
		this.index = _index_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(index);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		index = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CBuyVipPackage) {
			CBuyVipPackage _o_ = (CBuyVipPackage)_o1_;
			if (index != _o_.index) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += index;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(index).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CBuyVipPackage _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = index - _o_.index;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


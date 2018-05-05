
package lx.gs.bonus.msg;

import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import cfg.CfgMgr;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import common.ErrorCode;
import lx.gs.logger.By;
import lx.gs.pay.FPay;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetPayGift__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetPayGift extends __CGetPayGift__ {
	@Override
	protected void process() {
		new AProcedure<CGetPayGift>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleWelfareInfo welfare = FBonus.get(roleid);
				xbean.RolePay rolepay = FPay.getRolePay(roleid);
				xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
				cfg.bonus.ChargeBonus gift = CfgMgr.chargebonus.get(id);
				if (gift.bonustype == cfg.bonus.ChargeBonusType.EVERYDAY_CHARGE
						&& rolepay.getDailytotalpay()/100 < gift.requirevalue) {
					return error(ErrorCode.NOT_ENOUGH_CHARGE);
				} else if (gift.bonustype == cfg.bonus.ChargeBonusType.TOTAL_CHARGE
						&& rolepay.getTotalpay()/100 < gift.requirevalue) {
					return error(ErrorCode.NOT_ENOUGH_CHARGE);
				} else if (gift.bonustype == cfg.bonus.ChargeBonusType.TOTAL_CONSUME
						&& roleinfo.getTotalcostyuanbao() < gift.requirevalue) {
					return error(ErrorCode.NOT_ENOUGH_COST);
                }
                if(welfare.getReceivedpaycharge().contains(id)){
                    return error(ErrorCode.HAS_RECEIVE_BONUS);
                }
				welfare.getReceivedpaycharge().add(id);
				SGetPayGift response = new SGetPayGift();
				response.id = id;
				response(response);
				return FBonus.genAndAddBonus(roleid, gift.bonuslist, common.Bonus.BindType.BIND, response.paygift, By.Pay_And_Cost_Total);
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556124;

	public int getType() {
		return 6556124;
	}

	public int id;

	public CGetPayGift() {
	}

	public CGetPayGift(int _id_) {
		this.id = _id_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetPayGift) {
			CGetPayGift _o_ = (CGetPayGift)_o1_;
			if (id != _o_.id) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += id;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetPayGift _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = id - _o_.id;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

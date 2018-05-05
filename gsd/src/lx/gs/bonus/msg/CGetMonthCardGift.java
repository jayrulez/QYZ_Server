
package lx.gs.bonus.msg;

import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import cfg.CfgMgr;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import common.ErrorCode;
import lx.gs.logger.By;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetMonthCardGift__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetMonthCardGift extends __CGetMonthCardGift__ {
	@Override
	protected void process() {
		new AProcedure<CGetMonthCardGift>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleWelfareInfo welfare = FBonus.get(roleid);
				if(welfare.getReceivedmonthcarddate().contains(date)){
					return error(ErrorCode.HAS_RECEIVED);
				}
				if(!FBonus.isCurrentMonthCardDay(roleid, date)){
					return error(ErrorCode.WRONG_MONTH_CARD_DATE);
				}
				welfare.getReceivedmonthcarddate().add(date);
				cfg.bonus.MonthlyCard bonus = CfgMgr.monthlycard.get(date);
				SGetMonthCardGift response = new SGetMonthCardGift();
				response.date = date;
				response(response);
				return FBonus.genAndAddBonus(roleid, bonus.bonus, common.Bonus.BindType.BIND, response.monthcardgift, By.MonthCard_Reward);
			}
			
		}.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556118;

	public int getType() {
		return 6556118;
	}

	public int date;

	public CGetMonthCardGift() {
	}

	public CGetMonthCardGift(int _date_) {
		this.date = _date_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(date);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		date = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetMonthCardGift) {
			CGetMonthCardGift _o_ = (CGetMonthCardGift)_o1_;
			if (date != _o_.date) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += date;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(date).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetMonthCardGift _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = date - _o_.date;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


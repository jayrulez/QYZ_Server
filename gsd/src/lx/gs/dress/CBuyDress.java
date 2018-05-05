
package lx.gs.dress;

import cfg.currency.CurrencyType;
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

abstract class __CBuyDress__ extends xio.Protocol { }

/** 购买Dress，购买后处于获得状态
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBuyDress extends lx.gs.dress.__CBuyDress__ {
	@Override
	protected void process() {
		new AProcedure<CBuyDress>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				cfg.equip.Dress dconf = cfg.CfgMgr.dress.get(dresskey);
				ErrorCode ret = FCondition.checkByReflection(roleid, dconf, 1, By.Buy_Dress);
				if(ret.err()) {
					return error(ret);
				}
				ret = FDress.unlockDress(roleid, dresskey, 0);
				if(ret.err()) {
					return error(ret);
				}
				FLogger.shop_trade(roleid, FBonus.getRoleInfo(roleid), dresskey, 1, CurrencyType.YuanBao, dconf.price.amount);
				response(new SBuyDress(dresskey));
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576363;

	public int getType() {
		return 6576363;
	}

	public int dresskey; // 购买的Dresskey

	public CBuyDress() {
	}

	public CBuyDress(int _dresskey_) {
		this.dresskey = _dresskey_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(dresskey);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		dresskey = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CBuyDress) {
			CBuyDress _o_ = (CBuyDress)_o1_;
			if (dresskey != _o_.dresskey) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += dresskey;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(dresskey).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CBuyDress _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = dresskey - _o_.dresskey;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


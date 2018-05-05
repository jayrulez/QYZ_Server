
package lx.gs.mount;

import cfg.CfgMgr;
import cfg.currency.CurrencyType;
import cfg.equip.Riding;
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

abstract class __CBuyRide__ extends xio.Protocol { }

/** 购买坐骑，购买后处于获得状态
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBuyRide extends lx.gs.mount.__CBuyRide__ {
	@Override
	protected void process() {
		new AProcedure<CBuyRide>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				Riding conf = CfgMgr.riding.get(ridekey);
				ErrorCode ret = FCondition.checkByReflection(roleid, conf, 1, By.Buy_Dress);
				if(ret.err()) {
					return error(ret);
				}
				ret = FRide.unlockRide(roleid, ridekey, 0);
				if(ret.err()) {
					return error(ret);
				}
				FLogger.shop_trade(roleid, FBonus.getRoleInfo(roleid), ridekey, 1, CurrencyType.YuanBao, conf.price.amount);
				response(new SBuyRide(ridekey));
				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581695;

	public int getType() {
		return 6581695;
	}

	public int ridekey; // 购买的坐骑key

	public CBuyRide() {
	}

	public CBuyRide(int _ridekey_) {
		this.ridekey = _ridekey_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ridekey);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ridekey = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CBuyRide) {
			CBuyRide _o_ = (CBuyRide)_o1_;
			if (ridekey != _o_.ridekey) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ridekey;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ridekey).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CBuyRide _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ridekey - _o_.ridekey;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


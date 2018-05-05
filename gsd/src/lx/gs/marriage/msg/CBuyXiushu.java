
package lx.gs.marriage.msg;

import cfg.CfgMgr;
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

import java.util.HashMap;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBuyXiushu__ extends xio.Protocol { }

/** 购买休书
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBuyXiushu extends __CBuyXiushu__ {
	@Override
	protected void process() {
		new AProcedure<CBuyXiushu>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				ErrorCode result = FCondition.checkA(roleid, CfgMgr.marrigeconfig.divorceprice, 1, By.Marry_Buy, 0, 0);
				if(result.err()){
					return error(result);
				}
				//花费元宝购买休书
				HashMap<Integer, Integer> buyContent = new HashMap<>();
				buyContent.put(CfgMgr.marrigeconfig.divorceitemid, 1);
                FLogger.shop_trade(roleid, FBonus.getRoleInfo(roleid), CfgMgr.marrigeconfig.divorceitemid, 1, CurrencyType.YuanBao, CfgMgr.marrigeconfig.divorceprice.amount);
				if(!FBonus.addBonus(roleid, common.Bonus.BindType.BIND, buyContent, By.Marry_Buy)){
                    return false;
                }
				SBuyXiushu response = new SBuyXiushu();
				response(response);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575958;

	public int getType() {
		return 6575958;
	}


	public CBuyXiushu() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CBuyXiushu) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CBuyXiushu _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}



package lx.gs.role.msg;

import cfg.CfgMgr;
import cfg.cmd.CmdId;
import cfg.cmd.ConfigId;
import cfg.cmd.condition.Currency;
import cfg.cmd.condition.LimitType;
import cfg.currency.CurrencyType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.role.FRole;
import xtable.Roleinfos;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBuyTili__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBuyTili extends __CBuyTili__ {
	@Override
	protected void process() {
		new AProcedure<CBuyTili>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final ErrorCode err = FCondition.checkA(roleid, CfgMgr.roleconfig.buytilicost, 1, By.Role_Buy_Tili, ConfigId.ROLE, CmdId.BUYTILI);
				if(err.err())
					return error(err);

				FRole.addCurrency(roleid, Roleinfos.get(roleid), CurrencyType.TiLi, CfgMgr.roleconfig.buyaddtili.amount, By.Role_Buy_Tili);
                int limitTimes = FLimit.getLimitTimes(roleid, ConfigId.ROLE, CmdId.BUYTILI, LimitType.DAY);
                cfg.cmd.condition.VipLimits conf = CfgMgr.roleconfig.buytilicost;
                int cost = conf.amout.get(Math.min(limitTimes - 1, conf.amout.size()-1));
                FLogger.shop_trade(roleid, FBonus.getRoleInfo(roleid), CurrencyType.TiLi, CfgMgr.roleconfig.buyaddtili.amount, conf.currencytype, cost);
                response(new SBuyTili());
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581791;

	public int getType() {
		return 6581791;
	}


	public CBuyTili() {
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
		if (_o1_ instanceof CBuyTili) {
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

	public int compareTo(CBuyTili _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


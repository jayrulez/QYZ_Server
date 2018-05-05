
package lx.gs.exchange.msg;

import cfg.currency.CurrencyType;
import cfg.exchange.Const;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gnet.link.Onlines;
import gs.AProcedure;
import lx.gs.exchange.FExchange;
import lx.gs.logger.By;
import lx.gs.role.FRole;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBuy__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBuy extends __CBuy__ {
    @Override
    protected void process() {
        new AProcedure<CBuy>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                final long id = param.exchangeid;
                final xbean.ExchangeItem item = xtable.Exchangeitem.get(id);
                final long seller = item.getOwner();
                if (seller == roleid)
                    return error(ErrorCode.CANNOT_BUY_SELF_ITEM);
                final int num = param.num;
                if(num <= 0)
                    return error(ErrorCode.INVALID_NUM);
                final int remain = item.getNum() - num;
                if (remain < 0)
                    return error(ErrorCode.EXCEED_AVALIABLE_NUM);
                final long totalprice = Math.multiplyExact(item.getPrice(), param.num);
                if(totalprice <= 0)
                    return error(ErrorCode.EXCHANGE_PRICE_OVERLIMIT);
                if (!FRole.checkCostCurrency(roleid, CurrencyType.YuanBao, totalprice, By.Exchange_Buy))
                    return error(ErrorCode.YUANBAO_NOT_ENOUGH);
                final ErrorCode err = FExchange.addExchangeItemToBag(roleid, id, num);
                if (err.err())
                    return error(err);

                final xbean.RoleExchange sellerinfo = FExchange.get(seller);
                if (remain == 0 && !sellerinfo.getItems().remove(id)) {
                    return error(ErrorCode.NOT_IN_EXCHANGE_ITEM_LIST);
                }

                final xbean.ExchangeLog log = xbean.Pod.newExchangeLog();
                item.setNum(num);
                FExchange.gen(item, roleid, log);
                final long logid = xtable.Exchangelog.insert(log);
                sellerinfo.getLogs().add(logid);

                final xbean.RoleExchange buyerinfo = FExchange.get(roleid);
                buyerinfo.getLogs().add(logid);

                item.setNum(remain);
                if (remain == 0) {
                    xtable.Exchangeitem.remove(id);
                    if (!FExchange.removeItem(id)) {
                        xdb.Trace.error("impossible! PBuy remove ExchangeItem fail!. id:" + id);
                        return false;
                    }
                } else {
                    item.setUnshelvetime(System.currentTimeMillis() + Const.EXCHANGE_UNSHELVE_TIME * 1000L);
                    if (!FExchange.changeItem(item.toData())) {
                        xdb.Trace.error("impossible! PBuy change ExchangeItem fail!. id:" + id);
                        return false;
                    }
                }

                final long incomeAfterTax = (long)(totalprice * (1 - Const.EXCHANGE_TAX));
                FRole.addCurrency(seller, cfg.exchange.Const.BUY_COST_CURRENTCY_TYPE, incomeAfterTax, By.Exchange_Sell);
                final SNewLog snewlog = new SNewLog(FExchange.convert(log));

                response(new SBuy(id, num));
                response(snewlog);


                Onlines.getInstance().send(seller, snewlog);
                Onlines.getInstance().send(seller, new SBuyByOther(exchangeid, remain));
                return true;
            }
        }.validateRoleidAndExecute();
    }

    public static void main(String[] argv) {

    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573225;

	public int getType() {
		return 6573225;
	}

	public long exchangeid;
	public int num;

	public CBuy() {
	}

	public CBuy(long _exchangeid_, int _num_) {
		this.exchangeid = _exchangeid_;
		this.num = _num_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(exchangeid);
		_os_.marshal(num);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		exchangeid = _os_.unmarshal_long();
		num = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CBuy) {
			CBuy _o_ = (CBuy)_o1_;
			if (exchangeid != _o_.exchangeid) return false;
			if (num != _o_.num) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)exchangeid;
		_h_ += num;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(exchangeid).append(",");
		_sb_.append(num).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CBuy _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(exchangeid - _o_.exchangeid);
		if (0 != _c_) return _c_;
		_c_ = num - _o_.num;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


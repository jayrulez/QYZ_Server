
package lx.gs.depot.msg;

import cfg.currency.CurrencyType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.depot.FDepot;
import lx.gs.logger.By;
import lx.gs.role.FRole;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CTakeGoldCoin__ extends xio.Protocol { }

/** 从仓库中取出金币
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CTakeGoldCoin extends __CTakeGoldCoin__ {
    @Override
    protected void process() {
        new AProcedure<CTakeGoldCoin>(this) {
            @Override
            protected boolean doProcess() throws Exception {
				if(amount <= 0){
					return false;
				}
                if (!FDepot.addGoldcoinDepot(roleid, -amount)) {
                    return error(ErrorCode.XUNIBI_NOT_ENOUGH);
                }
				FRole.addCurrency(roleid, CurrencyType.XuNiBi, amount, By.Depot_Take);

				FDepot.syncDepotGoldcoin(roleid);
                return true;

            }
        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555982;

	public int getType() {
		return 6555982;
	}

	public long amount; // 数量

	public CTakeGoldCoin() {
	}

	public CTakeGoldCoin(long _amount_) {
		this.amount = _amount_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(amount);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		amount = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CTakeGoldCoin) {
			CTakeGoldCoin _o_ = (CTakeGoldCoin)_o1_;
			if (amount != _o_.amount) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)amount;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(amount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CTakeGoldCoin _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(amount - _o_.amount);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


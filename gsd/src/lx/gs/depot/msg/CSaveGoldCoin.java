
package lx.gs.depot.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.depot.FDepot;
import lx.gs.logger.By;
import lx.gs.role.FRole;
import xtable.Roleinfos;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSaveGoldCoin__ extends xio.Protocol { }

/** 把金币放入仓库
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSaveGoldCoin extends __CSaveGoldCoin__ {
    @Override
    protected void process() {
        new AProcedure<CSaveGoldCoin>(this) {
            @Override
            protected boolean doProcess() throws Exception {
				if(amount <= 0){
					return false;
				}
                if (!FRole.checkCostXuNiBi(roleid, Roleinfos.get(roleid), amount, By.Depot_Take)) {
                    return error(ErrorCode.XUNIBI_NOT_ENOUGH);
                }
                if(!FDepot.addGoldcoinDepot(roleid, amount)){
                    return error(ErrorCode.XUNIBI_DEPOT_IS_FULL);
                }
				FDepot.syncDepotGoldcoin(roleid);
                return true;

            }
        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555980;

	public int getType() {
		return 6555980;
	}

	public long amount; // 数量

	public CSaveGoldCoin() {
	}

	public CSaveGoldCoin(long _amount_) {
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
		if (_o1_ instanceof CSaveGoldCoin) {
			CSaveGoldCoin _o_ = (CSaveGoldCoin)_o1_;
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

	public int compareTo(CSaveGoldCoin _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(amount - _o_.amount);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


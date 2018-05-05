
package lx.gs.pay;

import cfg.cmd.action.Currency;
import cfg.currency.CurrencyType;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gnet.DeliverClient;
import gnet.GGetPayReturn;
import gnet.GsToAuany;
import gs.AProcedure;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetPayReturn__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetPayReturn extends __CGetPayReturn__ {
	@Override
	protected void process() {
		new AProcedure<CGetPayReturn>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                final xbean.RolePay info = FPay.getRolePay(roleid);
                if(info.getHasgotpayreturn())
                    return error(ErrorCode.PAY_HAS_GOT_PAY_RETURN);
                DeliverClient.getInstance().sendByGsToAuany(userid, roleid, new GGetPayReturn());
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576860;

	public int getType() {
		return 6576860;
	}


	public CGetPayReturn() {
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
		if (_o1_ instanceof CGetPayReturn) {
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

	public int compareTo(CGetPayReturn _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


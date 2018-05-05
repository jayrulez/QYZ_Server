
package lx.gs.bonus.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import cfg.cmd.condition.LimitType;
import cfg.currency.CurrencyType;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.role.FRole;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CShakeMoneyTree__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CShakeMoneyTree extends __CShakeMoneyTree__ {
	@Override
	protected void process() {
		new AProcedure<CShakeMoneyTree>(this){
            @Override
            protected boolean doProcess() throws Exception {
                cfg.shakemoneytree.ShakeMoneyTree conf = CfgMgr.shakemoneytree;
                ErrorCode ret = FCondition.checkA(roleid, conf.viplimit, 1, By.Shake_Money_Tree, ConfigId.SHAKE_MONEY_TREE, 1);
                if(ret.err()){
                    return error(ret);
                }
                int times = FLimit.getLimitTimes(roleid, ConfigId.SHAKE_MONEY_TREE, 1, LimitType.DAY);
                cfg.shakemoneytree.ShakeDetail detail = conf.shakeinfo.get(times - 1);
                ErrorCode det = FCondition.checkByReflection(roleid, detail, By.Shake_Money_Tree);
                if(det.err()){
                    return error(det);
                }
                SShakeMoneyTree response = new SShakeMoneyTree();
                response.time = times;
                double pro = common.Utils.random01();
                if(pro <= detail.criticalrate){
                    response.criticalnum = detail.criticalvalue;
                    response.receinexunibi = Math.multiplyExact((long)detail.criticalvalue, detail.getmoney.amount);
                }else {
                    response.receinexunibi = detail.getmoney.amount;
                }
                FRole.addCurrency(roleid, CurrencyType.XuNiBi, response.receinexunibi, By.Shake_Money_Tree);
                response(response);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564422;

	public int getType() {
		return 6564422;
	}


	public CShakeMoneyTree() {
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
		if (_o1_ instanceof CShakeMoneyTree) {
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

	public int compareTo(CShakeMoneyTree _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


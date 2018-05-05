
package lx.gs.bonus.msg;

import cfg.CfgMgr;
import cfg.bonus.BONUSTYPE;
import cfg.bonus.MealType;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FAction;
import lx.gs.cmd.FCmd;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEatBaozi__ extends xio.Protocol { }

/** 吃包子或者补领
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEatBaozi extends __CEatBaozi__ {
	@Override
	protected void process() {
		new AProcedure<CEatBaozi>(this){
            @Override
            protected boolean doProcess() throws Exception {
                xbean.RoleWelfareInfo info = FBonus.get(roleid);
                SEatBaozi response = new SEatBaozi();
                response.eattype = eattype;
                switch (eattype){
                    case EAT_LUNCH:{
                        if(info.getIseatlunch() == 1){
                            return error(ErrorCode.HAS_EATEN_LUNCH);
                        }
                        cfg.bonus.BaoZi conf = CfgMgr.baozi.get(MealType.LUNCH);
                        if(!FBonus.isInTimeInterval(conf)){
                            return error(ErrorCode.NOT_THE_EAT_TIME);
                        }
                        info.setIseatlunch(1);
                        response.addtili = conf.gettili.amount;
                        FAction.processByReflection(roleid, conf, 1, By.Eat_Baozi);
                        break;
                    }
                    case EAT_DINNER:{
                        if(info.getIseatdinner() == 1){
                            return error(ErrorCode.HAS_EATEN_DINNER);
                        }
                        cfg.bonus.BaoZi conf = CfgMgr.baozi.get(MealType.DINNER);
                        if(!FBonus.isInTimeInterval(conf)){
                            return error(ErrorCode.NOT_THE_EAT_TIME);
                        }
                        info.setIseatdinner(1);
                        response.addtili = conf.gettili.amount;
                        FAction.processByReflection(roleid, conf, 1, By.Eat_Baozi);
                        break;
                    }
                    case RE_EAT_LUNCH:{//补吃
                        if(info.getIseatlunch() == 1){
                            return error(ErrorCode.HAS_EATEN_LUNCH);
                        }
                        cfg.bonus.BaoZi conf = CfgMgr.baozi.get(MealType.LUNCH);
                        if(!FBonus.isAfterEatTime(conf)){
                            return error(ErrorCode.NOT_THE_EAT_TIME);
                        }
                        info.setIseatlunch(1);
                        response.addtili = conf.gettili.amount;
                        FCmd.Context ctx = FCmd.invoke(roleid, By.Eat_Baozi, conf);
                        if(ctx.errcode.err()){
                            return error(ctx.errcode);
                        }
                        break;
                    }
                    case RE_EAT_DINNER:{
                        if(info.getIseatdinner() == 1){
                            return error(ErrorCode.HAS_EATEN_DINNER);
                        }
                        cfg.bonus.BaoZi conf = CfgMgr.baozi.get(MealType.DINNER);
                        if(!FBonus.isAfterEatTime(conf)){
                            return error(ErrorCode.NOT_THE_EAT_TIME);
                        }
                        info.setIseatdinner(1);
                        response.addtili = conf.gettili.amount;
                        FCmd.Context ctx = FCmd.invoke(roleid, By.Eat_Baozi, conf);
                        if(ctx.errcode.err()){
                            return error(ctx.errcode);
                        }
                        break;
                    }
                    default:
                        return error(ErrorCode.PARAM_ERROR);
                }
                FLogger.welfare(roleid, FBonus.getRoleInfo(roleid), BONUSTYPE.EAT_BAOZI, eattype);
                response(response);
                return true;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6580377;

	public int getType() {
		return 6580377;
	}

	public final static int EAT_LUNCH = 1;
	public final static int EAT_DINNER = 2;
	public final static int RE_EAT_LUNCH = 3;
	public final static int RE_EAT_DINNER = 4;

	public int eattype; // 吃包子的类型

	public CEatBaozi() {
	}

	public CEatBaozi(int _eattype_) {
		this.eattype = _eattype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(eattype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		eattype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEatBaozi) {
			CEatBaozi _o_ = (CEatBaozi)_o1_;
			if (eattype != _o_.eattype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += eattype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(eattype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CEatBaozi _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = eattype - _o_.eattype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


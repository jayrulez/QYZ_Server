package lx.gs.pay;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import cfg.pay.ActiveCharge;
import cfg.pay.GrowPlan;
import cfg.pay.GrowPlanType;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCondition;
import lx.gs.idgen.FIdGen;
import lx.gs.logger.By;

public class PGetAppOrder extends AProcedure<CGetAppOrder>{
	
	protected PGetAppOrder(CGetAppOrder param) {
		super(param);
	}

	@Override
	protected boolean doProcess() throws Exception {
		cfg.pay.Charge payCfg = CfgMgr.charge.get(param.productid);
		if(payCfg == null){
			return err(SGetAppOrder.PRODUCT_NOT_FOUND);
		}
        if(payCfg.getTypeId() == GrowPlan.TYPEID){ // grow plan limit
            xbean.RoleInfo roleInfo = FBonus.getRoleInfo(roleid);
            xbean.RoleWelfareInfo welfareInfo = FBonus.get(roleid);
            cfg.pay.GrowPlan growPlan = (GrowPlan)payCfg;
            if(roleInfo.getLogindaycount() < growPlan.thresholddaynum){
                return error(ErrorCode.NOT_ENOUGH_LOGIN_DAYS);
            }
            if(roleInfo.getBuygrowplan() + 1 != growPlan.growplantype){
                return error(ErrorCode.WRONG_GROW_PLAN_TYPE);
            }
            if((growPlan.growplantype == GrowPlanType.SECONDTYPE && welfareInfo.getThreegrowplan().size() != 3) ||
                    growPlan.growplantype == GrowPlanType.THIRDTYPE && welfareInfo.getFivegrowplan().size() != 5){
                return error(ErrorCode.MUST_COMPLETE_PRE_GROW);
            }
        }else if(payCfg.getTypeId() == ActiveCharge.TYPEID){
            xbean.RolePay payInfo = FPay.getRolePay(roleid);
            cfg.pay.ActiveCharge aCfg = (ActiveCharge)payCfg;
            if(payInfo.getDailyactivepay() >= aCfg.daylimit.num){
                return error(ErrorCode.HAS_BUY_THIS_PRODUCT_TODAY);
            }
        }

		xbean.AppOrder order = xbean.Pod.newAppOrder();
		order.setRoleid(roleid);
		order.setProductid(param.productid);
		order.setTime(System.currentTimeMillis());

		long orderid = FIdGen.nextUniqueId();

		SGetAppOrder response = new SGetAppOrder();
		response.err = SGetAppOrder.OK;
		response.orderid = String.valueOf(orderid);
		response.productid = param.productid;
        xtable.Processingorders.insert(orderid, order);

		xdb.Transaction.tsendWhileCommit(roleid, response);
		return true;
	}
	
	private boolean err(int errorCode){
		SGetAppOrder response = new SGetAppOrder();
		response.err = errorCode;

		xdb.Transaction.tsend(roleid, response);

		return false;
	}

}

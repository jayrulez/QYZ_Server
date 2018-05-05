package lx.gs.pay;

import cfg.CfgMgr;
import cfg.pay.GrowPlan;
import cfg.pay.GrowPlanType;
import cfg.pay.MonthCard;
import gnet.DeliverClient;
import gnet.GGetPayReturnInfo;
import xdb.Transaction;

import java.util.concurrent.TimeUnit;

public enum PayModule implements gs.Module, gs.DayIdleListener, gs.RoleLoginListener {
	INSTANCE;
    public static long MONTH_CARD_PRICE = 0;
    public static long GROWPLAN_1 = 0;
    public static long GROWPLAN_2 = 0;
    public static long GROWPLAN_3 = 0;


	@Override
	public void start() {
        for(cfg.pay.Charge conf : CfgMgr.charge.values()){
            if(conf.getTypeId() == MonthCard.TYPEID){
                MONTH_CARD_PRICE = conf.price;
            }else if(conf.getTypeId() == GrowPlan.TYPEID){
                GrowPlan growPlan = (GrowPlan) conf;
                if(growPlan.growplantype == GrowPlanType.FIRSTTYPE){
                    GROWPLAN_1 = growPlan.price;
                }else if(growPlan.growplantype == GrowPlanType.SECONDTYPE){
                    GROWPLAN_2 = growPlan.price;
                }else if(growPlan.growplantype == GrowPlanType.THIRDTYPE){
                    GROWPLAN_3 = growPlan.price;
                }
            }
        }
    }

	@Override
	public void onDayIdle() {
		xtable.Processingorders.getTable().walk((orderid, appOrder) -> {
			//超过7天的订单认为是超时的
			if(System.currentTimeMillis() - appOrder.getTime() > TimeUnit.DAYS.toMillis(7)){
				new PTimeoutOrder(orderid, appOrder.getRoleid()).execute();
				return true;
			}else{
				return false;
			}
		}); 	
	}

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        final xbean.RolePay info = FPay.getRolePay(roleid);
        if(!info.getHasgotpayreturn()) {
            DeliverClient.getInstance().sendByGsToAuany(xtable.Roleinfos.selectUserid(roleid), roleid, new GGetPayReturnInfo());
        } else {
            Transaction.tsend(roleid, FPay.create(true, info.getTotalyunbao(), info.getTotalbindyuanbao(), info.getTotalvipexp()));
        }
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {

    }


    private static class PTimeoutOrder extends xdb.Procedure{
		private long orderid;
		private long roleid;
		
		public PTimeoutOrder(long orderid, long roleid) {
			super();
			this.orderid = orderid;
			this.roleid = roleid;
		}
		
		@Override
		protected boolean process(){
			xdb.Lockey[] lockeys = {
					xdb.Lockeys.get(xtable.Locks.ROLELOCK, roleid),
					xdb.Lockeys.get(xtable.Locks.PROCESSINGORDERLOCK, orderid),
			};
			lock(lockeys);
			
			xbean.AppOrder order = xtable.Processingorders.get(orderid);
			if(order != null){
				long roleid = order.getRoleid();
				
				xbean.RoleOrderHistroy orderhistory = xtable.Roleorderhistorys.get(roleid);
				if(orderhistory == null){
					orderhistory = xbean.Pod.newRoleOrderHistroy();
					xtable.Roleorderhistorys.insert(roleid, orderhistory);
				}
				xtable.Processingorders.delete(orderid);
				orderhistory.getTimeoutorder().put(orderid, order);
			}
			
			return true;
		}
	}


}

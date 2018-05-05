package lx.gs.pay;

import cfg.CfgMgr;
import cfg.cmd.action.BindType;
import cfg.currency.CurrencyType;
import cfg.item.EItemBindType;
import cfg.pay.*;
import common.Bonus;
import lx.gs.bonus.FBonus;
import lx.gs.gm.Role;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.role.FRole;
import xtable.Locks;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 支付回调
 * 游戏中给角色增加元宝
 */
public class PPayOrderCallback extends xdb.Procedure{
	private gnet.NotifyOrderInfo notifyOrder;
	
	public PPayOrderCallback(gnet.NotifyOrderInfo order){
		this.notifyOrder = order;
	}

	// 这三家渠道的实际充值金额 amount <= 订单金额bookAmount
	private final static List<Integer> bookAmountMayGreaterThanAmountAgentids = Arrays.asList(6, 14, 102);

    @Override
	protected boolean process() throws Exception{
		long userid = notifyOrder.userid;
		long orderid = Long.decode(notifyOrder.gsorderid);
        Map<String, String> params = gnet.PlatUtils.unmarshalMap(notifyOrder.vars);
		Long roleid =  xtable.Processingorders.selectRoleid(orderid);
        xdb.Trace.info("Roleid = {}, userid = {} params:{} starts to pay", roleid, userid, params);
		if(roleid == null){
			xdb.Trace.error("订单未找到(orderid:{})", orderid);
			return error(gnet.NotifyOrderInfoAck.ERR_ORDER_UNKNOWN);
		}

		xdb.Lockey[] lockeys = {
				xdb.Lockeys.get(xtable.Locks.ROLELOCK, roleid),
				xdb.Lockeys.get(xtable.Locks.PROCESSINGORDERLOCK, orderid),
		};
		lock(lockeys);

		boolean isTimeout = false;
		xbean.AppOrder order = xtable.Processingorders.get(orderid);
		if(order == null){
			xbean.RoleOrderHistroy orderhistory = xtable.Roleorderhistorys.get(roleid);
			if(orderhistory != null){
				order = orderhistory.getTimeoutorder().get(orderid);
				isTimeout = true;
			}
		}

		if(order == null){
			xdb.Trace.error("订单未找到(orderid:{})", orderid);
			return error(gnet.NotifyOrderInfoAck.ERR_ORDER_UNKNOWN);
		}

		int productid = order.getProductid();
		cfg.pay.Charge payCfg = CfgMgr.charge.get(productid);
		if(payCfg == null){
			xdb.Trace.error("物品(productid:{})不存在", productid);
			return error(gnet.NotifyOrderInfoAck.ERR_VARS_INVALID);
		}

		int amount = Integer.decode(params.get("amount"));
        int bookAmount = Integer.decode(params.get("bookAmount"));
        int agentid = Integer.decode(params.get("agentId"));
		if(payCfg.price != amount){
		    if(!bookAmountMayGreaterThanAmountAgentids.contains(agentid) || payCfg.price != bookAmount) {
                xdb.Trace.error("物品(productid:{})对应的价格是{}，amount:{} bookamount:{}", productid, payCfg.price, amount, bookAmount);
                return error(gnet.NotifyOrderInfoAck.ERR_VARS_INVALID);
            }
		}

		final List<Long> roleids = xtable.Users.selectRoleids(userid);
		if(!roleids.contains(roleid)){
			xdb.Trace.error("用户下不存在该角色。userid={}，roles={}，订单参数={}", userid, roleids, params);
			return error(gnet.NotifyOrderInfoAck.ERR_USER_UNKNOWN);
		}
		xbean.RoleInfo roleInfo = xtable.Roleinfos.get(roleid);

		SPaySuccessNotify payNotify = new SPaySuccessNotify();

		xbean.RolePay pay = FPay.getRolePay(roleid);

        if(payCfg.getTypeId() == NormalCharge.TYPEID){//如果是正常充值
            pay.setCangetfirstbonus(true);//表示能领取首冲奖励了,月卡和成长计划不算在首冲里面,以后一元档也不算
            cfg.pay.NormalCharge nCfg = (NormalCharge)payCfg;
            payNotify.yuanbao = nCfg.getyuanbao.amount;
            if(!pay.getIsentryfirstpay().getOrDefault(productid, false)){
                payNotify.bindyuanbao = nCfg.firstgetbindyuanbao.amount;
                pay.getIsentryfirstpay().put(productid, true);
            }else {
                payNotify.bindyuanbao = nCfg.getbindyuanbao.amount;
            }
        }else if(payCfg.getTypeId() == MonthCard.TYPEID){//如果是月卡充值,现在给元宝
            cfg.pay.MonthCard mCfg = (MonthCard)payCfg;
            payNotify.yuanbao = mCfg.getyuanbao.amount;
            roleInfo.setMonthcardlefttimes(roleInfo.getMonthcardlefttimes() + mCfg.days);
            roleInfo.setBuymonthcardtimes(roleInfo.getBuymonthcardtimes() + 1);
        }else if(payCfg.getTypeId() == ActiveCharge.TYPEID){
            cfg.pay.ActiveCharge aCfg = (ActiveCharge)payCfg;
            payNotify.yuanbao = aCfg.getyuanbao.amount;
            if(!pay.getIsentryfirstpay().getOrDefault(productid, false)){
                payNotify.bindyuanbao = aCfg.firstgetbindyuanbao.amount;
                pay.getIsentryfirstpay().put(productid, true);
            }else {
                payNotify.bindyuanbao = aCfg.getbindyuanbao.amount;
            }
            pay.setDailyactivepay(pay.getDailyactivepay() + 1);
            FBonus.addBonusAlwaysSucc(roleid, aCfg.extrabonus, 1, Bonus.BindType.BIND, By.Rmb_Pay);
        }
        if(payNotify.yuanbao > 0) {
            FRole.addYuanBao(roleid, roleInfo, payNotify.yuanbao, By.Rmb_Pay);
        }
        if(payNotify.bindyuanbao > 0){
            FRole.addCurrency(roleid, roleInfo, CurrencyType.BindYuanBao, payNotify.bindyuanbao, By.Rmb_Pay);
        }
        //增加充值积分,月卡不算充值积分,积分是元和积分的比例
        if(payCfg.getTypeId() == NormalCharge.TYPEID){
            FRole.addCurrency(roleid, roleInfo, CurrencyType.ChongZhiJiFen, Math.multiplyExact(payCfg.price, CfgMgr.firstcharge.rmbtojifen)/100 , By.Rmb_Pay);
        }

        doneOrder(roleid, roleInfo, pay, notifyOrder.platorderid, notifyOrder.gsorderid, payCfg, amount);

		//订单状态置为成功
		xbean.RoleOrderHistroy orderhistory = xtable.Roleorderhistorys.get(roleid);
		if(orderhistory == null){
			orderhistory = xbean.Pod.newRoleOrderHistroy();
			xtable.Roleorderhistorys.insert(roleid, orderhistory);
		}
		if(isTimeout){
			orderhistory.getTimeoutorder().remove(orderid);
		}
		else{
			xtable.Processingorders.delete(orderid);
		}
		orderhistory.getSucceedorder().put(orderid, order);

		//向auany发送订单完成消息
		response(gnet.NotifyOrderInfoAck.ERR_SUCCEED);
        payNotify.productid = productid;
		payNotify.dailypaystatus = pay.getDailypaystatus();
		payNotify.dailytotalpaystatus = pay.getDailytotalpaystatus();
		xdb.Transaction.tsend(roleid, payNotify);
        xdb.Trace.info("Roleid = {}, name = {} pay success charge = {}", roleid, roleInfo.getName(), payCfg.price);
		return true;
	}

    public static void doneOrder(long roleid, xbean.RoleInfo info, xbean.RolePay pay, String platorderid, String gsorderid, cfg.pay.Charge payCfg, int realPay) {
        xbean.RolePay rolePay = FPay.getRolePay(roleid);
        rolePay.setRoleallpay(rolePay.getRoleallpay() + payCfg.price);//记录任何充值记录
        if(payCfg.getTypeId() == NormalCharge.TYPEID) {//只有正常充值才算vip等级
            FPay.pay(roleid, pay, payCfg);//记录普通充值记录
            FRole.addVipLevel(roleid, info, pay);//记录vip等级改变
        }
        if(payCfg.getTypeId() == MonthCard.TYPEID){
            SBuyMonthCardNotify monthCardNotify = new SBuyMonthCardNotify();
            monthCardNotify.buymonthcardtime = info.getBuymonthcardtimes();
            monthCardNotify.monthcardleftdays = info.getMonthcardlefttimes();
            xdb.Transaction.tsendWhileCommit(roleid, monthCardNotify);
        }
        if(payCfg.getTypeId() == GrowPlan.TYPEID){
            GrowPlan conf = (GrowPlan)payCfg;
            info.setBuygrowplan(conf.growplantype);//购买的成长计划类型
            if(conf.growplantype == GrowPlanType.FIRSTTYPE){
//                info.setBuygrowplan(1);
                info.setGrowonetime(info.getLogindaycount());
            } else if(conf.growplantype == GrowPlanType.SECONDTYPE){
//                info.setBuygrowplantwo(1);
                info.setGrowtwotime(info.getLogindaycount());
            } else if(conf.growplantype == GrowPlanType.THIRDTYPE){
//                info.setBuygrowplanthree(1);
                info.setGrowthreetime(info.getLogindaycount());
            }
            xdb.Transaction.tsendWhileCommit(roleid, new SBuyGrowPlanNotify(conf.growplantype));
        }
        FLogger.addcash(roleid, info, payCfg.price, realPay, platorderid, gsorderid, payCfg.getClass().getSimpleName());//记录充值记录
    }
	
	/**
	 * 验证错误立即向auany发送收到通知，但不将订单移到角色历史记录中
	 * @return
	 */
	public boolean error(int errorCode){
		response(errorCode);
		return false;
	}
	
	public void response(int errorCode){
		gnet.NotifyOrderInfoAck response = new gnet.NotifyOrderInfoAck();
		response.errcode = errorCode;
		response.plattype = notifyOrder.plattype;
		response.gsorderid = notifyOrder.gsorderid;
		response.platorderid = notifyOrder.platorderid;
		response.userid = notifyOrder.userid;

		response.send(gnet.DeliverClient.getInstance());
	}
}

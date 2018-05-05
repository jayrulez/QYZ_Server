package lx.gs.pay;

import gnet.AGetPayReturnInfo;
import gnet.link.Onlines;
import lx.gs.event.EventModule;
import lx.gs.event.RechargeEvent;
import xdb.Trace;

public class FPay {
	
	public static xbean.RolePay getRolePay(long roleid){
		long currTimeMills = System.currentTimeMillis();
		xbean.RolePay pay = xtable.Rolepays.get(roleid);
		if(pay == null){
			pay = xbean.Pod.newRolePay();
			pay.setIsfirstpayused(false);
			pay.setDailypaystatus(PayAward.STATUS_NOT_PAY);
			pay.setDailytotalpaystatus(PayAward.STATUS_NOT_PAY);
			pay.setDailytotalpay(0);
			pay.setLastrefreshtime(currTimeMills);
			xtable.Rolepays.insert(roleid, pay);
		}
		else if(!common.Time.isSameDay(currTimeMills, pay.getLastrefreshtime())){
			pay.setDailypaystatus(PayAward.STATUS_NOT_PAY);
			pay.setDailytotalpaystatus(PayAward.STATUS_NOT_PAY);
			pay.setDailytotalpay(0);
            pay.setDailyactivepay(0);
			pay.setLastrefreshtime(currTimeMills);
		}
		
		return pay;
	}
	
	public static void pay(long roleid, xbean.RolePay pay, cfg.pay.Charge payCfg){
		if(pay.getDailypaystatus() == PayAward.STATUS_NOT_PAY){
			pay.setDailypaystatus(PayAward.STATUS_NOT_GET_AWARD);
		}
		pay.setDailytotalpay(pay.getDailytotalpay() + payCfg.price);
        pay.setTotalpay(pay.getTotalpay() + payCfg.price);
		EventModule.INSTANCE.broadcastEvent(new RechargeEvent(roleid));
	}

	public static SPayReturnInfo create(boolean hasgotpayreturn, long totalyunbao, long totalbindyuanbao, long totalvipexp) {
        final SPayReturnInfo re = new SPayReturnInfo();
        re.hasgotpayreturn = common.Utils.toByte(hasgotpayreturn);
        re.vipexp = totalvipexp;
        re.yuanbao = totalyunbao;
        re.bindyuanbao = totalbindyuanbao;
        return re;
    }
}

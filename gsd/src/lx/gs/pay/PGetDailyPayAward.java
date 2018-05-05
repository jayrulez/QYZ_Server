package lx.gs.pay;

import gs.AProcedure;
import common.ErrorCode;

public class PGetDailyPayAward extends AProcedure<CGetDailyPayAward>{

	protected PGetDailyPayAward(CGetDailyPayAward param) {
		super(param);
	}

	@Override
	protected boolean doProcess() throws Exception {
		xbean.RolePay pay = FPay.getRolePay(roleid);
		if(pay.getDailypaystatus() == PayAward.STATUS_NOT_PAY){
			return error(ErrorCode.NOT_PAY);
		}
		if(pay.getDailypaystatus() == PayAward.STATUS_GET_AWARD){
			return error(ErrorCode.PAY_AWARD_HAS_GOT);
		}
		
		//奖励礼包
		SGetDailyPayAward p = new SGetDailyPayAward();
//		FPay.addToReward(p.reward, config.Paygiftbag.DAILY_PAY);
//		if (!FRole.takeReward(roleid, p.reward, By.Pay_Daily_Reward))
//			return error(ErrorCode.BAG_FULL);

		pay.setDailypaystatus(PayAward.STATUS_GET_AWARD);
		
		p.dailypaystatus = pay.getDailypaystatus();
		return response(p);
	}

}

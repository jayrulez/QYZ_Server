package lx.gs.pay;

import gs.AProcedure;
import common.ErrorCode;

public class PGetDailyTotalPayAward extends AProcedure<CGetDailyTotalPayAward> {

    protected PGetDailyTotalPayAward(CGetDailyTotalPayAward param) {
        super(param);
    }

    @Override
    protected boolean doProcess() throws Exception {
        xbean.RolePay pay = FPay.getRolePay(roleid);
        if (pay.getDailytotalpaystatus() == PayAward.STATUS_NOT_PAY) {
            return error(ErrorCode.NOT_PAY);
        }
        if (pay.getDailytotalpaystatus() == PayAward.STATUS_GET_AWARD) {
            return error(ErrorCode.PAY_AWARD_HAS_GOT);
        }

        //奖励礼包

        SGetDailyTotalPayAward p = new SGetDailyTotalPayAward();
//        FPay.addToReward(p.reward, config.Paygiftbag.DAILY_TOTAL_PAY);
//        if (!FRole.takeReward(roleid, p.reward, By.Pay_Daily_Total_Reward))
//            return error(ErrorCode.BAG_FULL);

        pay.setDailytotalpaystatus(PayAward.STATUS_GET_AWARD);
        p.dailytotalpaystatus = pay.getDailytotalpaystatus();

        return response(p);
    }

}

package lx.gs.pay;

import cfg.currency.CurrencyType;
import common.ErrorCode;
import gnet.AGetPayReturn;
import gnet.AuanyToGs;
import gs.AProcedure;
import lx.gs.logger.By;
import lx.gs.role.FRole;
import xdb.Trace;

/**
 * Created by HuangQiang on 2016/8/15.
 */
public class PGotPayReturn extends xdb.Procedure {
    private AGetPayReturn param;
    public PGotPayReturn(AGetPayReturn param) {
        this.param = param;
    }

    @Override
    protected boolean process(){
        final AuanyToGs ctx = (AuanyToGs)param.getContext();
        final long roleid = ctx.roleid;
        final xbean.RoleInfo roleInfo = xtable.Roleinfos.get(roleid);
        final xbean.User userInfo = xtable.Users.get(ctx.userid);
        Trace.info("AGetPayReturn userid:{} roleid:{} msg:{}", ctx.userid, ctx.roleid, param);
        if(roleInfo == null || userInfo == null) {
            Trace.error("AGetPayReturn. unknown roleid or userid. ");
            return false;
        }
        if(!userInfo.getRoleids().contains(roleid)) {
            Trace.error("AGetPayReturn. role isn't in user's roleids");
            return false;
        }
        final xbean.RolePay rolePay = FPay.getRolePay(roleid);
        if(rolePay.getHasgotpayreturn()) {
            Trace.error("AGetPayReturn. has got pay return.");
            return false;
        }
        final SGetPayReturn re = new SGetPayReturn();
        if(param.err == AGetPayReturn.OK || (param.err == AGetPayReturn.HAS_GOT_RETURN && roleid == param.getreturnroleid)) {
            rolePay.setHasgotpayreturn(true);
            if(param.totalpay > 0) {
                FRole.addCurrency(roleid, CurrencyType.YuanBao, param.totalyuanbao, By.Pay_Return);
                FRole.addCurrency(roleid, CurrencyType.BindYuanBao, param.totalbindyuanbao, By.Pay_Return);
                // totalpay里的月卡与成长计划不能加vipexp,故以totalvipexp为准
                rolePay.setTotalpay(rolePay.getTotalpay() + param.totalvipexp * 100);
                FRole.addVipLevel(roleid, roleInfo, rolePay);
            }
        } else {
            xdb.Transaction.tsend(roleid, AProcedure.makeError(param.err == AGetPayReturn.HAS_GOT_RETURN ?
                    ErrorCode.PAY_HAS_GOT_PAY_RETURN : ErrorCode.PAY_HAS_NOT_PAY_RETURN));
            return false;
        }
        xdb.Transaction.tsendWhileCommit(roleid, re);
        return true;
    }
}

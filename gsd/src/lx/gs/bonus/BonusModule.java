package lx.gs.bonus;

import xdb.Procedure;

/**
 * Created by xiong on 2016/4/21.
 */
public enum BonusModule implements gs.Module, gs.RoleDayOverListener, gs.RoleLoginListener {
    INSTANCE;

    @Override
    public void start() {}

    @Override
    public void onDayOver(long roleid) {
        new Procedure() {
            @Override
            protected boolean process() {
                FBonus.clearWelFareRecords(roleid); //每日清空福利领取信息
                return true;
            }
        }.execute();
    }

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        //登录后发送福利信息，客户端用来判断红点的显隐
        xdb.Transaction.tsendWhileCommit(roleid, FBonus.creatInfo(roleid));
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {}
}

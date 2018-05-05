package lx.gs.marriage;

/**
 * Created by arctest on 2016/5/13.
 */
public enum MarriageModule implements gs.Module, gs.RoleLoginListener {
    INSTANCE;

    @Override
    public void start() {

    }


    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        //登录后发送婚姻状态信息
        xdb.Transaction.tsendWhileCommit(roleid, FMarriage.createInfo(roleid));
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {

    }
}

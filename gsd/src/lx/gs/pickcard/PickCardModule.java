package lx.gs.pickcard;


/**
 * Created by xiong on 2016/6/28.
 */
public enum PickCardModule implements gs.Module, gs.RoleLoginListener {
    INSTANCE;

    @Override
    public void start() {

    }


    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        FPickCard.sendPickCardTimes(roleid, FPickCard.get(roleid));
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {

    }
}

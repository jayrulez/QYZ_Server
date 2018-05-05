package lx.gs.amulet;

import gs.LevelUpListener;
import gs.RoleCreateListener;
import gs.RoleLoginListener;
import xbean.Pod;

/**
 * @author Jin Shuai
 */
public enum AmuleModule implements gs.Module, RoleLoginListener, RoleCreateListener, LevelUpListener {
    INSTANCE;

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        FAmulet.onRoleLogin(roleid);
    }

    @Override
    public void onLevelUp(long roleid, int oldLevel, int newLevel) {
        FAmulet.onRoleLevelUp(roleid, newLevel);
    }


    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {
        FAmulet.get(roleid).getPagemap().values().forEach(amuletPage -> FAmulet.applyWashResult(amuletPage));
    }

    @Override
    public void start() {}

    @Override
    public void onRoleCreateInProcedure(long roleid) {
        xtable.Amulet.add(roleid, Pod.newRoleAmuletInfo());
        FAmulet.onRoleLevelUp(roleid, 1);
    }

    @Override
    public void onRoleDeleteInProcedure(long roleid) {}
}

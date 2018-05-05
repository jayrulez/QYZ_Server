package lx.gs.dress;

import common.TaskQueue;
import gnet.link.Onlines;
import gnet.link.Role;
import gs.Module;
import gs.RoleAttrListener;
import gs.RoleCreateListener;
import gs.RoleLoginListener;
import xbean.Pod;
import xdb.Procedure;
import xdb.logs.Listener;
import xdb.logs.Note;

import java.util.concurrent.TimeUnit;

/**
 * @author Jin Shuai
 */
public enum  DressModule implements Module, RoleCreateListener, RoleAttrListener, RoleLoginListener {
    INSTANCE;

    private static final int checkInterval = 1; // 单位:分钟

    @Override
    public void start() {
        TaskQueue.getScheduleExecutor().scheduleWithFixedDelay(this::checkExpire, checkInterval, checkInterval, TimeUnit.MINUTES);

        xtable.Dress.getTable().addListener(new Listener() {
            @Override
            public void onChanged(Object key) {}

            @Override
            public void onRemoved(Object key) {}

            @Override
            public void onChanged(Object key, String fullVarName, Note note) {
                long roleId = (long) key;
                new Procedure(){
                    @Override
                    protected boolean process() throws Exception {
                        FDress.onActiveDressChange(roleId);
                        return true;
                    }
                }.execute();
            }
        }, "value", "activedress");
    }

    private void checkExpire(){
        for (Role role : Onlines.getInstance().roles()) {
            new Procedure(){
                @Override
                protected boolean process() throws Exception {
                    FDress.checkExpire(role.getRoleid());
                    return true;
                }
            }.execute();
        }
    }



    @Override
    public void onRoleCreateInProcedure(long roleid) {
        xtable.Dress.add(roleid, Pod.newRoleDress());
    }

    @Override
    public void onRoleDeleteInProcedure(long roleid) {}

    @Override
    public void updateRoleAttr(long roleid) {
        FDress.updateRoleAttr(roleid);
    }

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        FDress.onRoleLogin(roleid);
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {}
}

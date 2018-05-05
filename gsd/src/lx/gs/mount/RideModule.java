package lx.gs.mount;

import common.TaskQueue;
import gnet.link.Onlines;
import gnet.link.Role;
import gs.Module;
import gs.RoleCreateListener;
import xbean.Pod;
import xdb.Procedure;
import xdb.logs.Listener;
import xdb.logs.Note;

import java.util.concurrent.TimeUnit;

/**
 * @author Jin Shuai
 */
public enum  RideModule implements Module, RoleCreateListener {
    INSTANCE;

    private static final int checkInterval = 1; //单位：分钟

    @Override
    public void start() {
        TaskQueue.getScheduleExecutor().scheduleWithFixedDelay(this::checkExpire, checkInterval, checkInterval, TimeUnit.MINUTES);

        xtable.Ride.getTable().addListener(new Listener() {
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
                        FRide.onActiveRideChange(roleId);
                        return true;
                    }
                }.call();
            }
        }, "value", "activeride");
    }

    private void checkExpire(){
        for (Role role : Onlines.getInstance().roles()) {
            new Procedure(){
                @Override
                protected boolean process() throws Exception {
                    FRide.checkExpire(role.getRoleid());
                    return true;
                }
            }.call();
        }
    }

    @Override
    public void onRoleCreateInProcedure(long roleid) {
        xtable.Ride.add(roleid, Pod.newRoleRide());
    }

    @Override
    public void onRoleDeleteInProcedure(long roleid) {
    }
}

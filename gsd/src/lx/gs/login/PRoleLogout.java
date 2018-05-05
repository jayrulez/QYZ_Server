package lx.gs.login;

import lx.gs.logger.FLogger;
import lx.gs.map.FMap;

public class PRoleLogout extends xdb.Procedure {
    private final long roleid;

    public PRoleLogout(long roleid) {
        this.roleid = roleid;
    }

    @Override
    protected boolean process() throws Exception {
        gs.Listeners.INSTANCE.beforeRoleLogout(roleid);
		xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
		
		final long now = System.currentTimeMillis();
        long onlinetime = role.getLastlogintime() == 0 ? 0 : now - role.getLastlogintime();
        role.setTotalonlinetime(role.getTotalonlinetime() + onlinetime);
        role.setTotaldayonlinetime(FLogin.calcTodayOnlineTime(role, now));
        // 注意setLastLogouttime一定要放在calcTodayOnlineTime之后,
        // 否则totaldayonlinetime计算错误
        role.setLastlogouttime(now);
        FLogger.rolelogout(roleid, role, onlinetime);
        FLogger.chardata(roleid, role);

        LoginTraceMgr.onLogout(roleid);
		return true;
    }
}

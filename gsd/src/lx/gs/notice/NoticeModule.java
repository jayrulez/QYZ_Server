package lx.gs.notice;

import gnet.link.Onlines;

public enum NoticeModule implements gs.Module, gs.RoleLoginListener {
	INSTANCE;
	
	@Override
	public void afterRoleLoginInProcedure(long roleid) {
        try {
            FNotice.createSNoticeAndClear(roleid);
        } catch (Exception e) {
            e.printStackTrace();
        }
//		Onlines.getInstance().send(roleid, FNotice.createSNoticeAndClear(roleid));
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {

	}

	@Override
	public void start() {

	}

}

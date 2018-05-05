package lx.gs.rank;

/**
 * @author HuangQiang
 * @createtime 2015年11月6日 上午10:42:12
 */

public enum RankModule implements gs.Module, gs.RoleLoginListener, gs.RoleDayOverListener, gs.RoleCreateListener {
	INSTANCE;

	@Override
	public void start() {
		FRank.refreshRanks(false);
	}



	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		xdb.Transaction.tsendWhileCommit(roleid, FRank.createSInfo(roleid));
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {}

	@Override
	public void onDayOver(long roleid) {
		new xdb.Procedure() {
			@Override
			protected boolean process() {
				xdb.Transaction.tsendWhileCommit(roleid, FRank.createSInfo(roleid));
				return true;
			}
		}.execute();
	}

    @Override
    public void onRoleCreateInProcedure(long roleid) {
    }

    @Override
    public void onRoleDeleteInProcedure(long roleid) {

    }
}

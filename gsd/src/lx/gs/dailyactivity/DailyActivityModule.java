package lx.gs.dailyactivity;

import cfg.active.Activebonus;
import lx.gs.dailyactivity.msg.SGetActiveInfo;
import lx.gs.dailyactivity.msg.SGetUndoActive;
import xdb.Procedure;

public enum DailyActivityModule implements gs.Module, gs.RoleDayOverListener, gs.RoleLoginListener{
	INSTANCE;

	
	@Override
	public void start() {
	}

	@Override
	public void onDayOver(long roleid) {
		new Procedure() {
			@Override
			protected boolean process() {
				FDailyActivity.clearActiveInfo(roleid); //每日清空活跃信息
				return true;
			}
		}.execute();
		
	}

	@Override
	public void afterRoleLoginInProcedure(long roleid) {
        SGetActiveInfo active = new SGetActiveInfo();
        if(FDailyActivity.getActiveInfo(roleid, active).ok()){
            xdb.Transaction.tsendWhileCommit(roleid, active);
        }
//        if(role.getLevel() >= Activebonus.openlevel){
            SGetUndoActive undo = new SGetUndoActive();
            FDailyActivity.getUndoActiveInfo(roleid, undo.undoactive);
            if(undo.undoactive.undoactive.size() > 0) {
                FDailyActivity.genTotalFindBackBonus(undo.undoactive);
                xdb.Transaction.tsendWhileCommit(roleid, undo);
            }
//        }
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {

	}

}

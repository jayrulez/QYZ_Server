package lx.gs.logger;

import java.util.concurrent.TimeUnit;

public enum LoggerModule implements gs.Module, gs.DayIdleListener {
	INSTANCE;

	@Override
	public void start() {

	}

	@Override
	public void onDayIdle() {
//		final long yesterDayMills = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1);
//		xtable.Roleinfos.getTable().browse((roleid, role) ->{
//			//昨天登录过的角色信息才会写快照
//			if(common.Time.isSameDay(yesterDayMills, role.getLastlogintime())){
//
//				new xdb.Procedure(){
//					@Override
//					protected boolean process(){
//						FLogger.chardata(roleid);
//
//						return true;
//					}
//				}.call();
//			}
//			return true;
//		});
	}

	
}

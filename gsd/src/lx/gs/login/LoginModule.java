package lx.gs.login;

import gnet.link.Onlines;
import gs.Config;
import lx.gs.logger.FLogger;

import java.util.concurrent.TimeUnit;

public enum LoginModule implements gs.Module, gs.GsdStartListener{
	INSTANCE;

	public static long DELETE_ROLE_PUT_OFF = TimeUnit.DAYS.toMillis(3);


	@Override
	public void start() {
		Onlines.getInstance().setMaxOnline(Config.getInstance().getMaxOnline());
		//xtable.Roleinfos.getTable().addListener(new BaseAttrListener(), "value", "baseattr");
	}
	
	@Override
	public void afterGsdStart() {
		//每1分钟统计一次在线人数
		xdb.Executor.getInstance().scheduleAtFixedRate(FLogger::onlineuser, 5, 5, TimeUnit.MINUTES);
	}

	@Override
	public void beforeGsdStop() {
	}

	public void setPutOff(long val){
		DELETE_ROLE_PUT_OFF = TimeUnit.SECONDS.toMillis(val);
	}

//	private static class BaseAttrListener implements Listener {
//
//		@Override
//		public void onChanged(Object key) {
//			sync((Long) key);
//		}
//
//		@Override
//		public void onRemoved(Object key) {
//		}
//
//		@Override
//		public void onChanged(Object key, String fullVarName, Note note) {
//			sync((Long) key);
//		}
//
//		private void sync(Long key) {
//			xbean.RoleBaseAttr base = xtable.Roleinfos.selectBaseattr(key);
//			if (base == null)
//				return;
//
//			SSyncBaseAttr p = new SSyncBaseAttr();
//			FLogin.fillRoleBaseAttr(p.attr, base);
//			gnet.link.Onlines.getInstance().send(key, p);
//		}
//
//	}

}

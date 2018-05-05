package gs;

import gnet.link.Onlines;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public enum Listeners {
	INSTANCE;

	private List<GsdStartListener> gsdStartListeners = new CopyOnWriteArrayList<>();
	private List<RoleCreateListener> roleCreateListeners = new CopyOnWriteArrayList<>();
	private List<RoleLoginListener> roleLoginListeners = new CopyOnWriteArrayList<>();
	private List<DayOverListener> dayOverListeners = new CopyOnWriteArrayList<>();
	private List<HourChangeListener> hourChangeListeners = new CopyOnWriteArrayList<>();
	private List<RoleDayOverListener> roleDayOverListeners = new CopyOnWriteArrayList<>();
	private List<DayIdleListener> dayIdleListeners = new CopyOnWriteArrayList<>();
	private List<LevelUpListener> levelUpListeners = new CopyOnWriteArrayList<>();
	private List<RoleAttrListener> RoleAttrCreateListners = new CopyOnWriteArrayList<>();
	
	public void listenModule(Module module) {
        if(module instanceof DayIdleListener) {
        	listenDayIdle((DayIdleListener)module);
        }
        if(module instanceof DayOverListener) {
        	listenDayOver((DayOverListener)module);
        }
		if(module instanceof RoleDayOverListener) {
			listenRoleDayOver((RoleDayOverListener)module);
		}
        if(module instanceof GsdStartListener) {
        	listenGsdStart((GsdStartListener)module);
        }
        if(module instanceof LevelUpListener) {
        	listenLevelUp((LevelUpListener)module);
        }
        if(module instanceof RoleCreateListener) {
        	listenRoleCreate((RoleCreateListener)module);
        }
        if(module instanceof RoleLoginListener) {
        	listenRoleLogin((RoleLoginListener)module);
        }
		if(module instanceof RoleAttrListener) {
			listenRoleAttrCreate((RoleAttrListener)module);
		}
		if(module instanceof HourChangeListener) {
			listenHourchange((HourChangeListener) module);
		}
	}

	public void listenGsdStart(GsdStartListener listener) {
		gsdStartListeners.add(listener);
	}

	public void listenRoleCreate(RoleCreateListener listener) {
		roleCreateListeners.add(listener);
	}

	public void listenRoleLogin(RoleLoginListener listener) {
		roleLoginListeners.add(listener);
	}

	public void listenDayOver(DayOverListener listener) {
		dayOverListeners.add(listener);
	}

	public void listenRoleDayOver(RoleDayOverListener listener) {
		roleDayOverListeners.add(listener);
	}

	public void listenDayIdle(DayIdleListener listener) {
		dayIdleListeners.add(listener);
	}
	
	public void listenLevelUp(LevelUpListener listener) {
		levelUpListeners.add(listener);
	}

	public void listenRoleAttrCreate(RoleAttrListener listener) {
		RoleAttrCreateListners.add(listener);
	}

	public void listenHourchange(HourChangeListener listener) {
		hourChangeListeners.add(listener);
	}

	public void unlistenGsdStart(GsdStartListener listener) {
		gsdStartListeners.remove(listener);
	}

	public void unlistenRoleCreate(RoleCreateListener listener) {
		roleCreateListeners.remove(listener);
	}

	public void unlistenRoleLogin(RoleLoginListener listener) {
		roleLoginListeners.remove(listener);
	}

	public void unlistenDayOver(DayOverListener listener) {
		dayOverListeners.remove(listener);
	}

	public void unlistenDayIdle(DayIdleListener listener) {
		dayIdleListeners.remove(listener);
	}
	
	public void unlistenLevelUp(LevelUpListener listener) {
		levelUpListeners.remove(listener);
	}

	public void afterGsdStart() {
		gsdStartListeners.forEach(GsdStartListener::afterGsdStart);
	}

	public void beforeGsdStop() {
		gsdStartListeners.forEach(GsdStartListener::beforeGsdStop);
	}

	public void afterRoleLogin(long roleid) {
		roleLoginListeners.forEach(s -> s.afterRoleLoginInProcedure(roleid));
	}

	public void beforeRoleLogout(long roleid) {
		roleLoginListeners.forEach(s -> s.beforeRoleLogoutInProcedure(roleid));
	}

	public void roleCreate(long roleid) {
		roleCreateListeners.forEach(s -> s.onRoleCreateInProcedure(roleid));
	}

	public void roleDelete(long roleid) {
		roleCreateListeners.forEach(s -> s.onRoleDeleteInProcedure(roleid));
	}

	public void dayOver() {
		dayOverListeners.forEach(DayOverListener::onDayOver);
		for(gnet.link.Role role : Onlines.getInstance().roles()) {
			roleDayOverListeners.forEach(l -> l.onDayOver(role.getRoleid()));
		}
	}

	public void dayIdle() {
		dayIdleListeners.forEach(DayIdleListener::onDayIdle);
	}

	public void hourChanged() {
		hourChangeListeners.forEach(HourChangeListener::hourChanged);
	}
	
	public void levelUp(long roleid, int oldLevel, int newLevel) {
		levelUpListeners.forEach(l -> l.onLevelUp(roleid, oldLevel, newLevel));
	}

	public void updateRoleAttr(long roleid) {
		RoleAttrCreateListners.forEach(l -> l.updateRoleAttr(roleid));
	}

}

package lx.gs.limit;

import gnet.link.Onlines;
import lx.gs.activity.worldmonster.msg.SKillExpMonsterNums;
import lx.gs.limit.msg.SLimitChange;
import xdb.Procedure;
import xdb.logs.Note;

import java.util.HashMap;
import java.util.Map;

public enum LimitModule implements gs.Module, gs.RoleLoginListener, gs.RoleDayOverListener {
	INSTANCE;

	
	@Override
	public void onDayOver(long roleid) {
		new Procedure() {
			@Override
			protected boolean process() {
				FLimit.clearExpireLimits(roleid);
				FLimit.clearExpireCoolDown(roleid);
				FLimit.clearExpireDailyData(roleid);
				return true;
			}
		}.execute();
	}

	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		FLimit.clearExpireLimits(roleid);
		FLimit.clearExpireCoolDown(roleid);
		FLimit.clearExpireDailyData(roleid);
		xdb.Transaction.tsend(roleid, FLimit.createSLimit(roleid));
        xbean.DailyResetData dailyResetData = FLimit.getDaily(roleid);
        SKillExpMonsterNums notify = new SKillExpMonsterNums();
        notify.nums = dailyResetData.getKillexpmonsters();
        notify.receivedbonus.addAll(dailyResetData.getRecexpmonbonus());
        xdb.Transaction.tsend(roleid, notify);
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {


	}

	@Override
	public void start() {
		
		xtable.Rolelimit.getTable().addListener(new xdb.logs.Listener() {
			@Override
			public void onChanged(Object key) {	}

			@Override
			public void onRemoved(Object key) { }

			@Override
			public void onChanged(Object key, String fullVarName, Note note) {
				final Long roleid = (Long)key;
				final xbean.RoleLimit info = xtable.Rolelimit.get(roleid);
				final Map<Long, xbean.Limit> limits = info.getLimits();
				@SuppressWarnings("unchecked")
				xdb.logs.NoteMap<Long, xbean.Limit> nmap = (xdb.logs.NoteMap<Long, xbean.Limit>)note;
				final SLimitChange re = new SLimitChange();
				
				final HashMap<Long, lx.gs.limit.msg.Limit> changes = new HashMap<>();
				
				for(xbean.Limit limit : nmap.getChanged()) {
					final long id = limit.getId();
					if(!changes.containsKey(id) && limits.containsKey(id))
						changes.put(id, FLimit.convert(limit));
				}
				
				for(long id : nmap.getReplaced().keySet()) {
					if(!changes.containsKey(id))
						changes.put(id, FLimit.convert(limits.get(id)));
				}
				
				for(long id : nmap.getAdded()) {
					if(!changes.containsKey(id))
						changes.put(id, FLimit.convert(limits.get(id)));
				}
				
				re.changelimits.addAll(changes.values());
				re.removelimits.addAll(nmap.getRemoved().keySet());
				Onlines.getInstance().send(roleid, re);
			}
			
		}, "value", "limits");
	}

}

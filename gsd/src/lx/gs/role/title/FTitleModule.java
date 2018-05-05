package lx.gs.role.title;

import common.TaskQueue;
import gnet.link.Onlines;
import gnet.link.Role;
import gs.RoleAttrListener;
import lx.gs.role.FRoleAttr;
import xdb.Procedure;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public enum FTitleModule implements gs.Module, gs.RoleCreateListener, gs.RoleLoginListener, RoleAttrListener {

	INSTANCE;
    //每个角色的称号检查时间
    public static ConcurrentHashMap<Long, Long> Roleid2TitleExpireTime = new ConcurrentHashMap<>();
	/*	旧版本称号定时检查任务处理注释，该部分功能已重写 */
//	public static final long TITLE_TIMEOUT_TASK_PERIOD = 30 * 60 *1000;//每半个小时检查一次
	@Override
	public void start() {
		TaskQueue.getScheduleExecutor().scheduleAtFixedRate(() -> {
            final long now = System.currentTimeMillis();
			for (Role role : Onlines.getInstance().roles()) {
                long expireTime = Roleid2TitleExpireTime.getOrDefault(role.getRoleid(), 0L);
                if(expireTime == 0 || expireTime > now){
                    continue;
                }
                //只有需要检查的才会创建事务去检查
				new Procedure() {
					@Override
					protected boolean process() throws Exception {
						FTitle.checkTitleExpire(role.getRoleid(), now);
						return true;
					}
				}.execute();
			}
		}, 0 , 60, TimeUnit.SECONDS);
	}

	@Override
	public void onRoleCreateInProcedure(long roleid) {
		FTitle.initRoleTitle(roleid);
	}

	@Override
	public void onRoleDeleteInProcedure(long roleid) {
//		xtable.Title.remove(roleid);
	}

	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		FTitle.checkTitleExpire(roleid, System.currentTimeMillis());
		/*	旧版本称号定时检查任务处理注释，该部分功能已重写 */
//		//启动定时器检查称号到期
//		FTitle.startTitleTimeoutCheckTask(roleid);
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {
        Roleid2TitleExpireTime.remove(roleid);
	}

	@Override
	public void updateRoleAttr(long roleid) {
		final xbean.RoleTitle info = FTitle.getRoleTitle(roleid);
		final Map<Integer,Float> attrs = new HashMap<>();
		for(xbean.GroupTitle group : info.getTitleinfo().values()) {
			for(int titleKey : group.getTitles().keySet()) {
				common.AttrUtils.addAttrs(attrs, cfg.CfgMgr.title.get(titleKey).property);
			}
		}
		FRoleAttr.updateGroup(roleid, "title", attrs);
	}
}
package lx.gs.role.title;

import cfg.role.TitleType;
import cfg.tips.LocationType;
import cfg.tips.TipsCode;
import common.ErrorCode;
import common.Time;
import lx.gs.map.FMap;
import lx.gs.role.title.msg.STitleGetNotify;
import lx.gs.role.title.msg.STitleTimeOutNotify;
import lx.gs.role.title.msg.Title;
import lx.gs.tips.FTips;
import map.msg.SChangeTitle;
import xbean.RoleTitle;
import xdb.Procedure;
import xdb.Transaction;
import xtable.Roleinfos;

import java.util.Collections;

public class FTitle {

	/*	旧版本称号定时检查任务处理注释，该部分功能已重写 */
//	//角色对应的称号时限定时任务列表，用来取消
//	private static ConcurrentMap<Long, ScheduledFuture<?>> timeoutTaskMap = xdb.util.Misc.newConcurrentMap();
//	private static ReentrantLock taskLock = new ReentrantLock();

    /**
     * 初始化称号表
     *
     * @param roleid
     */
    public static void initRoleTitle(long roleid) {
        if (roleid <= 0) return;
        xbean.RoleTitle info = xtable.Title.get(roleid);
        if (info == null) {
            info = xbean.Pod.newRoleTitle();
            xtable.Title.add(roleid, info);
        }
    }

    /**
     * 获取称号信息
     *
     * @param roleid
     * @return
     */
    public static xbean.RoleTitle getRoleTitle(long roleid) {
        xbean.RoleTitle info = xtable.Title.get(roleid);
        if (info == null) {
            info = xbean.Pod.newRoleTitle();
            xtable.Title.add(roleid, info);
        }
        return info;
    }

    public static boolean isTitleModuleUnlock(long roleid) {
        if (roleid <= 0) return false;
        xbean.RoleInfo roleinfo = xtable.Roleinfos.get(roleid);
        //检查Title module的配置
        return roleinfo.getLevel() >= cfg.role.Title.OPEN_LEVEL;
    }

    private static xbean.Title getTitleByIdType(xbean.RoleTitle info, int titlekey, int type) {
        //根据类型从对应的列表中
        xbean.GroupTitle groupt = info.getTitleinfo().get(type);
        if (groupt == null) return null;
        return groupt.getTitles().get(titlekey);
    }

    private static xbean.Title delTitleByIdType(xbean.RoleTitle info, int titlekey, int type){
        xbean.GroupTitle groupt = info.getTitleinfo().get(type);
        if (groupt == null) return null;
        return groupt.getTitles().remove(titlekey);
    }
    /**
     * deactive称号
     *
     * @param roleid
     * @param titlekey
     * @return
     */
    public static ErrorCode deActiveTitle(long roleid, int titlekey, int type) {
        if (roleid <= 0) return ErrorCode.PARAM_ERROR;
        if (titlekey <= 0) return ErrorCode.PARAM_ERROR;
        if (type <= 0) return ErrorCode.PARAM_ERROR;

        if (!FTitle.isTitleModuleUnlock(roleid)) {
            return ErrorCode.TITLE_MODULE_LOCKED;
        }
        xbean.RoleTitle info = FTitle.getRoleTitle(roleid);
        xbean.Title title = getTitleByIdType(info, titlekey, type);
        if (title == null) {
            return ErrorCode.TITLE_NOT_GET;
        }
        if (!(title.getState() == Title.STATE_ACTIVED) || title.getActivetime() <= 0) {
            return ErrorCode.TITLE_NOT_ACTIVED;
        }
        title.setState(Title.STATE_GET);
        title.setActivetime(0);

        info.setActivekey(0);
        info.setActivetype(0);

        onChangeActiveTitle(roleid, 0);
        return ErrorCode.OK;
    }

    /**
     * 激活称号
     *
     * @param roleid
     * @param titlekey
     * @return
     */
    public static ErrorCode activeTitle(long roleid, int titlekey, int type) {

        if (!FTitle.isTitleModuleUnlock(roleid)) {
            return ErrorCode.TITLE_MODULE_LOCKED;
        }
        xbean.RoleTitle info = FTitle.getRoleTitle(roleid);
        xbean.Title title = getTitleByIdType(info, titlekey, type);
        if (title == null) {
            return ErrorCode.TITLE_NOT_GET;
        }
        if (title.getState() == Title.STATE_ACTIVED && title.getActivetime() > 0) {
            return ErrorCode.TITLE_ALREADY_ACTIVE;
        }
        title.setState(Title.STATE_ACTIVED);
        title.setActivetime(System.currentTimeMillis());

        if (info.getActivekey() > 0) {
            xbean.Title curA = getTitleByIdType(info, info.getActivekey(), info.getActivetype());
            assert (curA != null);
            curA.setState(Title.STATE_GET);
            curA.setActivetime(0);
        }
        info.setActivekey(titlekey);
        info.setActivetype(type);

        onChangeActiveTitle(roleid, titlekey);
        return ErrorCode.OK;
    }

    /**
     * 根据类型添加到相对应的列表里
     *
     * @param newt
     * @param type
     */
    private static void addNewTitle(long roleid, xbean.RoleTitle info, xbean.Title newt, int type) {
        xbean.GroupTitle groupt = info.getTitleinfo().get(type);
        if (null == groupt) {
            groupt = xbean.Pod.newGroupTitle();
            info.getTitleinfo().put(type, groupt);
        }
        groupt.getTitles().put(newt.getTitlekey(), newt);

    }

    public static void delTitle(long roleid, int titlekey){
        cfg.role.Title tconf = cfg.CfgMgr.title.get(titlekey);
        int type = tconf.titletype;
        xbean.RoleTitle roletitle = getRoleTitle(roleid);
        xbean.Title title = delTitleByIdType(roletitle, titlekey, type);
        if(title == null){
            return;
        }
        if (title.getTitlekey() == roletitle.getActivekey()) {//如果过期的称号恰好是当前装备的称号，需要重置
            roletitle.setActivekey(0);
            roletitle.setActivetype(0);
            onChangeActiveTitle(roleid, 0);//通知地图
        }
        xdb.Trace.debug("role = {} title = {} has expire.", roleid, title.getTitlekey());
        STitleTimeOutNotify notify = new STitleTimeOutNotify();
        notify.title = FTitle.makeProtocolTitle(title);
        Transaction.tsendWhileCommit(roleid, notify);
    }

    /**
     *
     * @param roleid
     * @param titlekey
     * @return
     */
    public static ErrorCode unlockTitle(long roleid, int titlekey) {
        cfg.role.Title tconf = cfg.CfgMgr.title.get(titlekey);
        int type = tconf.titletype;

        xbean.RoleTitle info = FTitle.getRoleTitle(roleid);
        xbean.Title title = getTitleByIdType(info, titlekey, type);
        if (title != null) { //如果已经有该称号了
            long expireTime = getExpireTime(tconf);
            title.setExpiretime(expireTime);
            resetCheckTime(roleid, expireTime);
            STitleGetNotify notify = new STitleGetNotify();
            notify.title = FTitle.makeProtocolTitle(title);
            Transaction.tsendWhileCommit(roleid, notify);
            return ErrorCode.OK;
        }
        title = xbean.Pod.newTitle();
        title.setTitlekey(titlekey);
        title.setTitletype(type);
        long expireTime = getExpireTime(tconf);
        title.setExpiretime(expireTime);
        resetCheckTime(roleid, expireTime);
        addNewTitle(roleid, info, title, type);
        title.setGettime(System.currentTimeMillis());
        title.setState(Title.STATE_GET);

        FTitleModule.INSTANCE.updateRoleAttr(roleid);
        if(type == TitleType.IDOL){
            FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.IDOL_TITLE_BROADCAST, Roleinfos.selectName(roleid), tconf.name);
        }
        STitleGetNotify notify = new STitleGetNotify();
        notify.title = FTitle.makeProtocolTitle(title);
        Transaction.tsendWhileCommit(roleid, notify);
        return ErrorCode.OK;
    }

    public static long getExpireTime(cfg.role.Title titleConf){
        int titlelifetime = titleConf.titletime;
        if (titlelifetime < 0) {
            return -1;
        }
        if (titlelifetime == 0) {
            return Time.getTimeInfo().dayZeroTime + Time.DAY_MILLISECONDS;
        }
        if(titlelifetime <= 7){
            return Time.getNextWeekMondayZeroTime() + (titlelifetime - 1) * Time.DAY_MILLISECONDS;
        }
        return System.currentTimeMillis() + titlelifetime * 1000L;
    }

    //获得称号后要重置检查时间
    public static void resetCheckTime(long roleid, long expireTime){
        if(expireTime < 0){
            return;
        }
//        System.out.println(roleid + " oldtime" + FTitleModule.Roleid2TitleExpireTime.getOrDefault(roleid, 0L) + " newtime" + expireTime);
        if(!FTitleModule.Roleid2TitleExpireTime.containsKey(roleid)){
            FTitleModule.Roleid2TitleExpireTime.put(roleid, expireTime);
        }else {
            if(expireTime < FTitleModule.Roleid2TitleExpireTime.get(roleid)) {
                FTitleModule.Roleid2TitleExpireTime.put(roleid, expireTime);
            }
        }
    }

    /**
     * xdb bean转换为协议使用的bean
     *
     * @param titleinfo
     * @return
     */
    public static lx.gs.role.title.msg.RoleTitle makeProtocolRoleTitle(xbean.RoleTitle titleinfo) {
        if (titleinfo == null) return null;
        lx.gs.role.title.msg.RoleTitle p = new lx.gs.role.title.msg.RoleTitle();
        for (java.util.Map.Entry<Integer, xbean.GroupTitle> e : titleinfo.getTitleinfo().entrySet()) {
            p.titles.put(e.getKey(), FTitle.makeProtocolGroupTitle(e.getValue()));
        }
        p.activekey = titleinfo.getActivekey();
        p.activetype = titleinfo.getActivetype();
        return p;
    }

    /**
     * xdb bean转换为协议使用的bean
     *
     * @param t
     * @return
     */
    public static lx.gs.role.title.msg.GroupTitle makeProtocolGroupTitle(xbean.GroupTitle t) {
        if (t == null) return null;
        lx.gs.role.title.msg.GroupTitle g = new lx.gs.role.title.msg.GroupTitle();
        for (java.util.Map.Entry<Integer, xbean.Title> e : t.getTitles().entrySet()) {
            g.titleinfo.add(FTitle.makeProtocolTitle(e.getValue()));
        }
        Collections.sort(g.titleinfo, (o1, o2) -> {
            int ret = Integer.compare(o1.state, o2.state) * -1;
            return ret != 0 ? ret : Long.compare(o1.gettime, o2.gettime) * -1;
        });
        return g;
    }

    /**
     * xdb bean转换为协议使用的bean
     *
     * @return
     */
    public static lx.gs.role.title.msg.Title makeProtocolTitle(xbean.Title d) {
        if (d == null) return null;
        lx.gs.role.title.msg.Title p = new lx.gs.role.title.msg.Title();
        p.titlekey = d.getTitlekey();
        p.titletype = d.getTitletype();
        p.state = d.getState();
        p.gettime = d.getGettime();
        p.activetime = d.getActivetime();
        p.expiretime = d.getExpiretime();
        return p;
    }

    @SuppressWarnings("unused")
    private static void clearRoleTitle(long roleid) {
        xtable.Title.remove(roleid);
    }


    private static void onChangeActiveTitle(long roleid, int activeTitleKey) {
        FTitleModule.INSTANCE.updateRoleAttr(roleid);
        FMap.dispatchMessageInProcedure(roleid, new SChangeTitle(activeTitleKey));
    }

    /**
     * 称号过期检查
     * < 0 永久称号
     * ==0 每日0点回收
     * (0, 7] 周x0点回收
     * > 7 称号有效时间 单位 s
     * @param roleid
     * @param now
     */
    public static void checkTitleExpire(long roleid, long now) {
        RoleTitle roletitle = getRoleTitle(roleid);
        final Long[] lastestExpireTime = {Long.MAX_VALUE};
        roletitle.getTitleinfo().values().forEach(typeTitles -> typeTitles.getTitles().entrySet().removeIf(title -> {
            long expiretime = title.getValue().getExpiretime();
            if(expiretime < 0){
                return false;
            }
            if(expiretime <= now){//如果过期了
                if(title.getValue().getTitlekey() == roletitle.getActivekey()){//如果过期的称号恰好是当前装备的称号，需要重置
                    roletitle.setActivekey(0);
                    roletitle.setActivetype(0);
                    onChangeActiveTitle(roleid, 0);//通知地图
                }
                xdb.Trace.info("role = {} title = {} has expire.", roleid, title.getValue().getTitlekey());
                STitleTimeOutNotify notify = new STitleTimeOutNotify();
                notify.title = FTitle.makeProtocolTitle(title.getValue());
                Transaction.tsendWhileCommit(roleid, notify);
                return true;
            }else {
                //如果有还没到期的称号，那么找出最近需要删除的时间
                lastestExpireTime[0] = Math.min(lastestExpireTime[0], expiretime);
            }
            return false;
        }));
        if(lastestExpireTime[0] < Long.MAX_VALUE){
            FTitleModule.Roleid2TitleExpireTime.put(roleid, lastestExpireTime[0]);
        }else {
            FTitleModule.Roleid2TitleExpireTime.remove(roleid);
        }
//        System.out.println(roleid + " " + FTitleModule.Roleid2TitleExpireTime.getOrDefault(roleid, 0L));
    }

}

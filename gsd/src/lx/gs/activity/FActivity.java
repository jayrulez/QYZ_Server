package lx.gs.activity;

import cfg.CfgMgr;
import cfg.bag.BagType;
import cfg.operationalactivity.*;
import common.ErrorCode;
import common.Time;
import lx.gs.activity.msg.ActivityInfo;
import lx.gs.activity.msg.SActivityChangeNotify;
import lx.gs.activity.operational.OperationalActivityHandler;
import xbean.GlobalActivity;
import xbean.Pod;
import xbean.RoleActivityRecord;
import xbean.RoleActivityStatus;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/6/20.
 */
public class FActivity {
    public static xbean.GlobalAllActivity get() {
        xbean.GlobalAllActivity allActivity = xtable.Globallactivity.get(0);
        if(allActivity == null) {
            allActivity = xbean.Pod.newGlobalAllActivity();
            xtable.Globallactivity.insert(0, allActivity);
        }
        return allActivity;
    }

    public static xbean.GlobalActivity getData(Activity activity) {
        final xbean.GlobalAllActivity allActivity = get();
        final int id = activity.getId();
        final Map<Integer, GlobalActivity> datas = allActivity.getDatas();
        xbean.GlobalActivity data = datas.get(id);
        final long timestamp = activity.getTimestamp();
        if(data == null || data.getTimestamp() != timestamp) {
            data = xbean.Pod.newGlobalActivityData();
            data.setTimestamp(timestamp);
            datas.put(id, data);
        }
        return data;
    }

    public static xbean.RoleAllActivity getRole(long roleid) {
        xbean.RoleAllActivity info = xtable.Roleallactivity.get(roleid);
        if(info == null) {
            info = xbean.Pod.newRoleAllActivity();
            xtable.Roleallactivity.insert(roleid, info);
        }
        return info;
    }

    public static xbean.RoleActivityStatus getRoleActivityStatus(Activity activity, long roleid) {
        return getRoleActivityStatus(activity, getRole(roleid).getStatus());
    }

    public static xbean.RoleActivityStatus getRoleActivityStatus(Activity activity, Map<Integer, xbean.RoleActivityStatus> datas) {
        final int id = activity.getId();
        final long timestamp = activity.getTimestamp();
        xbean.RoleActivityStatus data = datas.get(id);
        if(data == null || data.getTimestamp() != timestamp) {
            data = xbean.Pod.newRoleActivityStatus();
            data.setTimestamp(timestamp);
            datas.put(id, data);
        }
        return data;
    }


    public static ActivityInfo create(Activity activity, xbean.RoleActivityStatus datas) {
        ActivityInfo info = new ActivityInfo();
        info.id = activity.getId();
        info.isopen = common.Utils.toByte(activity.isOpen());
        final TimeRange openRange = activity.getOpenRange();
        if(openRange != null) {
            info.opentime = openRange.openTime;
            info.closetime = openRange.closeTime;
        }
        if(activity.isOpen()){
            info.status = datas.getStatus();
        }
        return info;
    }


    public static Map<Integer, xbean.RoleActivityStatus> getActivityStatusByType(long roleid, int type) {
        final Collection<OperatorActivity> activities = OperatorActivity.getActivitysByType(type);
        if(activities.isEmpty())
            return Collections.emptyMap();
        final Map<Integer, xbean.RoleActivityStatus> datas = getRole(roleid).getStatus();
        return activities.stream().collect(Collectors.toMap(a -> a.getId(), a -> getRoleActivityStatus(a, datas)));
    }

    public static xbean.RoleActivityStatus getActivityStatusByTypeAndId(long roleid, int type, int id){
        Activity activity = OperatorActivity.getActivityByTypeAndId(type, id);
        return activity == null ? null : getRoleActivityStatus(activity, roleid);
    }

    public static xbean.RoleActivityRecord getActivityDataByType(long roleid, int type){
        OperationalActivity activity = CfgMgr.operationalactivity.get(type);
        Map<Integer, xbean.RoleActivityRecordMap> datas = getRole(roleid).getRecords();
        xbean.RoleActivityRecordMap dataMap = datas.get(type);
        if(dataMap == null){
            dataMap = xbean.Pod.newRoleActivityRecordMap();
            datas.put(type, dataMap);
        }
        long time = 0;
        if(activity.isstartactivity){
            time = Time.todayZeroTime(ActivityModule.gsdFirstStartTime + activity.relativestarttime * Time.DAY_MILLISECONDS);
        }else {
            time = Time.calcDateTime(activity.timerange.begintime);
        }
        RoleActivityRecord record = dataMap.getRecords().get(time);
        if(record == null){
            record = Pod.newRoleActivityRecord();
            dataMap.getRecords().put(time, record);
        }
        return record;
    }

    public static void sendNotify(long roleid, int type, int cmdid, int status){
        SActivityChangeNotify notify = new SActivityChangeNotify();
        notify.id = type;
        notify.cmdid = cmdid;
        notify.status = status;
        xdb.Transaction.tsendWhileCommit(roleid, notify);
    }

    /**
     *
     * Activity total cost yuanbao check
     * @param roleid
     * @param cost
     */
//    public static void totalCostYuanbao(long roleid, long cost){
//        Map<Integer, xbean.RoleActivityStatus> RoleActivityStatusStatus = getActivityStatusByType(roleid, OperationalType.TOTAL_COST_YUANBAO);
//        if(RoleActivityStatusStatus.isEmpty()){
//            return;
//        }
//        xbean.RoleActivityRecord datas = getActivityDataByType(roleid, OperationalType.TOTAL_COST_YUANBAO);
//        long newValue = datas.getTotalcostyuanbbao() + cost;
//        datas.setTotalcostyuanbbao(newValue);
//        cfg.operationalactivity.OperationalActivity conf = CfgMgr.operationalactivity.get(OperationalType.TOTAL_COST_YUANBAO);
//        for(ActivityEntry award : conf.activityinfo){
//            if(newValue >= ((SimpleCondition) award.condition).num){
//                xbean.RoleActivityStatus detail = RoleActivityStatusStatus.get(award.id);
//                if(detail.getStatus() == OperationStatus.NOT_COMPLETE){
//                    detail.setStatus(OperationStatus.COMPLETE);
//                    sendNotify(roleid, OperationalType.TOTAL_COST_YUANBAO, award.id, OperationStatus.COMPLETE);
//                }
//            }
//        }
//    }

    public static void checkTotalCost(long roleid, long cost){
        List<ActivityEntry> entries = ActivityModule.INSTACNE.getEntries(TotalCost.class);
        if(!entries.isEmpty()) {
            Set<Integer> types = entries.stream().map(e -> getEntryType(e.id)).collect(Collectors.toSet());
            for(int t : types){
                xbean.RoleActivityRecord datas = getActivityDataByType(roleid, t);
                long newValue = datas.getTotalcostyuanbbao() + cost;
                datas.setTotalcostyuanbbao(newValue);
            }
            OperationalActivityHandler handler = ActivityModule.INSTACNE.getOperationalActivityHandler(TotalCost.class);
            for (ActivityEntry entry : entries) {
//                if (isOpenTime(getEntryType(entry.id), entry.id)) {
                    handler.checkNotify(roleid, entry);
//                }
            }
        }
    }

    /**
     * Activity daily gift packge buy check and add bonus
     * @param roleid
     * @param cmdid
     * @return
     */
//    public static ErrorCode dailyGiftBuy(long roleid, int cmdid){
//        cfg.operationalactivity.OperationalActivity conf = CfgMgr.operationalactivity.get(OperationalType.DAILY_GIFT_BUY);
//        for(ActivityEntry buy : conf.activityinfo){
//            if(buy.id == cmdid){
//                ErrorCode ret = FCondition.checkByReflection(roleid, buy.condition, 1, By.Operator_Activity, ConfigId.OPERATION_DAILY_GIFT_BUY, cmdid);
//                if(ret.err()){
//                    return ret;
//                }
//                if(!FRole.checkCostYuanBao(roleid, ((DailyGift) buy.condition).off.amount, By.Operator_Activity)){
//                    return ErrorCode.YUANBAO_NOT_ENOUGH;
//                }
////                addMonsterDropBonus(roleid, buy.reward);
//                return ErrorCode.OK;
//            }
//        }
//        return ErrorCode.PARAM_ERROR;
//    }

    /**
     * Activity equip annel check
     * @param roleid
     * @param bagtype
     */
//    public static void equipAnnel(long roleid, int bagtype, int equiptype, int annellevel) {
//        if (bagtype != BagType.EQUIP_BODY) {
//            return;
//        }
//        Map<Integer, xbean.RoleActivityStatus> RoleActivityStatusStatus = getActivityStatusByType(roleid, OperationalType.EQUIP_ANNEL);
//        if (RoleActivityStatusStatus.isEmpty()) {
//            return;
//        }
//        xbean.RoleActivityRecord datas = getActivityDataByType(roleid, OperationalType.EQUIP_ANNEL);
//        int oldvalue = datas.getMaxanneallevel().getOrDefault(equiptype, 0);
//        if (annellevel > oldvalue) {
//            datas.getMaxanneallevel().put(equiptype, annellevel);
//            int totalAnnel = datas.getMaxanneallevel().values().stream().reduce(0, Integer::sum);
//            int maxAnnel = Collections.max(datas.getMaxanneallevel().values());
//            cfg.operationalactivity.OperationalActivity conf = CfgMgr.operationalactivity.get(OperationalType.EQUIP_ANNEL);
//            for (ActivityEntry award : conf.activityinfo) {
//                SimpleCondition cond = (SimpleCondition)award.condition;
//                if((cond.getTypeId() == EquipAnnealMax.TYPEID && maxAnnel >= cond.num)
//                        || (cond.getTypeId() == EquipAnnealTotal.TYPEID && totalAnnel >= cond.num)){
//                    xbean.RoleActivityStatus detail = RoleActivityStatusStatus.get(award.id);
//                    if (detail.getStatus() == OperationStatus.NOT_COMPLETE) {
//                        detail.setStatus(OperationStatus.COMPLETE);
//                        sendNotify(roleid, OperationalType.EQUIP_ANNEL, award.id, OperationStatus.COMPLETE);
//                    }
//                }
//            }
//        }
//    }

    public static void checkEquipAnnel(long roleid, int bagtype) {
        if (bagtype != BagType.EQUIP_BODY) {
            return;
        }
        List<ActivityEntry> entries = ActivityModule.INSTACNE.getEntries(EquipAnnealMax.class);
        entries.addAll(ActivityModule.INSTACNE.getEntries(EquipAnnealTotal.class));
        if(!entries.isEmpty()) {
            for (ActivityEntry entry : entries) {
//                if (isOpenTime(getEntryType(entry.id), entry.id)) {
                    OperationalActivityHandler handler = ActivityModule.INSTACNE.getOperationalActivityHandler(entry.condition.getClass());
                    handler.checkNotify(roleid, entry);
//                }
            }
        }
    }

    /**
     * Activity equip perfuse check
     * @param roleid
     * @param bagtype
     */
//    public static void equipPerfuse(long roleid, int bagtype, int equiptype, int perfuselevel){
//        if (bagtype != BagType.EQUIP_BODY) {
//            return;
//        }
//        Map<Integer, xbean.RoleActivityStatus> RoleActivityStatusStatus = getActivityStatusByType(roleid, OperationalType.EQUIP_PERFUSE);
//        if (RoleActivityStatusStatus.isEmpty()) {
//            return;
//        }
//        xbean.RoleActivityRecord datas = getActivityDataByType(roleid, OperationalType.EQUIP_PERFUSE);
//        int oldvalue = datas.getMaxperfuselevel().getOrDefault(equiptype, 0);
//        if (perfuselevel > oldvalue) {
//            datas.getMaxperfuselevel().put(equiptype, perfuselevel);
//            int totalPerfuse = datas.getMaxperfuselevel().values().stream().reduce(0, Integer::sum);
//            int maxPerfuse = Collections.max(datas.getMaxperfuselevel().values());//find max perfuse level
//            cfg.operationalactivity.OperationalActivity conf = CfgMgr.operationalactivity.get(OperationalType.EQUIP_PERFUSE);
//            for (ActivityEntry award : conf.activityinfo) {
//                SimpleCondition cond = (SimpleCondition)award.condition;
//                if((cond.getTypeId() == EquipPerfuseMax.TYPEID && maxPerfuse >= cond.num)
//                        || cond.getTypeId() == EquipPerfuseTotal.TYPEID && totalPerfuse >= cond.num){
//                    xbean.RoleActivityStatus detail = RoleActivityStatusStatus.get(award.id);
//                    if (detail.getStatus() == OperationStatus.NOT_COMPLETE) {
//                        detail.setStatus(OperationStatus.COMPLETE);
//                        sendNotify(roleid, OperationalType.EQUIP_PERFUSE, award.id, OperationStatus.COMPLETE);
//                    }
//                }
//            }
//        }
//    }

    public static void checkEquipPerfuse(long roleid, int bagtype){
        if (bagtype != BagType.EQUIP_BODY) {
            return;
        }
        List<ActivityEntry> entries = ActivityModule.INSTACNE.getEntries(EquipPerfuseMax.class);
        entries.addAll(ActivityModule.INSTACNE.getEntries(EquipPerfuseTotal.class));
        if(!entries.isEmpty()) {
            for (ActivityEntry entry : entries) {
//                if (isOpenTime(getEntryType(entry.id), entry.id)) {
                    OperationalActivityHandler handler = ActivityModule.INSTACNE.getOperationalActivityHandler(entry.condition.getClass());
                    handler.checkNotify(roleid, entry);
//                }
            }
        }

    }

    /**
     * Activity equip collect by quality, check and add bonus
     * @param roleid
     * @return
     */
    public static void checkEuipQuality(long roleid){
        List<ActivityEntry> entries = ActivityModule.INSTACNE.getEntries(EquipQuality.class);
        if(!entries.isEmpty()) {
            OperationalActivityHandler handler = ActivityModule.INSTACNE.getOperationalActivityHandler(EquipQuality.class);
            for (ActivityEntry collect : entries) {
//                if (isOpenTime(getEntryType(collect.id), collect.id)) {
                    handler.checkNotify(roleid, collect);
//                }
            }
        }
    }

    /**
     * Activity achievement points check
     * @param roleid
     * */
//    public static void achievementPoints(long roleid, long points){
//        Map<Integer, xbean.RoleActivityStatus> RoleActivityStatusStatus = getActivityStatusByType(roleid, OperationalType.ACHIEVEMENT_POINTS);
//        if(RoleActivityStatusStatus.isEmpty()){
//            return;
//        }
//        cfg.operationalactivity.OperationalActivity conf = CfgMgr.operationalactivity.get(OperationalType.ACHIEVEMENT_POINTS);
//        for(ActivityEntry award : conf.activityinfo){
//            if(points >= ((SimpleCondition)award.condition).num){
//                xbean.RoleActivityStatus detail = RoleActivityStatusStatus.get(award.id);
//                if(detail.getStatus() == OperationStatus.NOT_COMPLETE){
//                    detail.setStatus(OperationStatus.COMPLETE);
//                    sendNotify(roleid, OperationalType.ACHIEVEMENT_POINTS, award.id, OperationStatus.COMPLETE);
//                }
//            }
//        }
//    }

    public static void checkAchievementPoints(long roleid){
        List<ActivityEntry> entries = ActivityModule.INSTACNE.getEntries(AchievementPoints.class);
        if(!entries.isEmpty()) {
            OperationalActivityHandler handler = ActivityModule.INSTACNE.getOperationalActivityHandler(AchievementPoints.class);
            for (ActivityEntry award : entries) {
//                if (isOpenTime(getEntryType(award.id), award.id)) {
                    handler.checkNotify(roleid, award);
//                }
            }
        }
    }

    public static int getEntryType(int entryId){
        return ActivityModule.INSTACNE.getType(entryId);
    }

    public static ErrorCode addExternFixOpenTime(int id, TimeRange range) {
        final Activity activity = Activity.activities.get(id);
        if(activity == null)
            return ErrorCode.ACTIVITY_NOT_EXIST;
        ActivityModule.INSTACNE.addTask(() -> {
            activity.addFixOpenTime(range);
        });
        return ErrorCode.OK;
    }

    public static ErrorCode enableActivity(int id, boolean enable) {
        final Activity activity = Activity.activities.get(id);
        if(activity == null)
            return ErrorCode.ACTIVITY_NOT_EXIST;
        ActivityModule.INSTACNE.addTask(() -> {
            activity.setExternEnable(enable);
        });
        return ErrorCode.OK;
    }

    public static ErrorCode closeActivity(int id) {
        final Activity activity = Activity.activities.get(id);
        if(activity == null)
            return ErrorCode.ACTIVITY_NOT_EXIST;
        ActivityModule.INSTACNE.addTask(() -> {
            activity.externClose();
        });
        return ErrorCode.OK;
    }

//    public static boolean isValidTime(DateTimeRange timerange) {
//        long now = System.currentTimeMillis();
//        return Time.calcDateTime(timerange.begintime) < now && Time.calcDateTime(timerange.endtime) > now;
//    }

    public static boolean isOpenTime(int typeid, int entryid){
        Activity activity = OperatorActivity.getActivityByTypeAndId(typeid, entryid);
        return activity != null;
    }

    public static boolean isOpenTimeByType(int typeid){
        final Collection<OperatorActivity> activities = OperatorActivity.getActivitysByType(typeid);
        return !activities.isEmpty();
    }

    public static int currStatus(long roleId, int type, int cmdid){
        Map<Integer, RoleActivityStatus> RoleActivityStatusMap = getActivityStatusByType(roleId, type);
        return RoleActivityStatusMap == null || !RoleActivityStatusMap.containsKey(cmdid)
                ? OperationStatus.NOT_COMPLETE
                : RoleActivityStatusMap.get(cmdid).getStatus();
    }

    public static void changeStatus(long roleId, int type, int cmdid, int status) {
        Map<Integer, RoleActivityStatus> roleActivityStatusMap = getActivityStatusByType(roleId, type);
        if(roleActivityStatusMap != null && roleActivityStatusMap.containsKey(cmdid)){
            roleActivityStatusMap.get(cmdid).setStatus(status);
        }
    }
}

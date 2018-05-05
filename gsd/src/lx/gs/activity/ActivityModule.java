package lx.gs.activity;

import cfg.CfgMgr;
import cfg.Const;
import cfg.operationalactivity.*;
import cfg.role.EProfessionType;
import cfg.tips.LocationType;
import cfg.tips.TipsCode;
import common.TaskQueue;
import common.Time;
import gnet.link.Onlines;
import gnet.link.Role;
import gs.DayOverListener;
import lx.gs.activity.familygodanimal.FamilyGodAnimal;
import lx.gs.activity.huiwu.FHuiWu;
import lx.gs.activity.huiwu.HuiWu;
import lx.gs.activity.msg.SActivity;
import lx.gs.activity.operational.*;
import lx.gs.activity.worldboss.WorldBoss;
import lx.gs.activity.worldmonster.WorldMonster;
import lx.gs.event.EventModule;
import lx.gs.event.EventType;
import lx.gs.system.FSystem;
import lx.gs.tips.FTips;
import xbean.RoleActivityStatus;
import xdb.Procedure;
import xtable.Roleinfos;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by huangqiang on 2016/3/19.
 */
public enum ActivityModule implements gs.Module, gs.GsdStartListener, gs.RoleLoginListener, DayOverListener {
    INSTACNE;

    private final TaskQueue taskQueue = new TaskQueue();

    final Map<Class<? extends ActivityCondition>, OperationalActivityHandler> handlerMap = new HashMap<>();
    final Map<Class<? extends ActivityCondition>, List<ActivityEntry>> activityEntryMap = new ConcurrentHashMap<>();
    final Map<Integer, Integer> entry2Type = new ConcurrentHashMap<>();

    public final static class HuiWuChapinionNotifyRecord {
        public final long roleid;
        public volatile long lastNotifyTime;

        public HuiWuChapinionNotifyRecord(long roleid) {
            this.roleid = roleid;
        }
    }
    public static final Map<Integer, HuiWuChapinionNotifyRecord> huiwuChampinionNotifys = new ConcurrentHashMap<>();
    public static final long NOTIFY_CD = 30 * 60 * 1000L;

    static long gsdFirstStartTime;

    public void addSimpleWeeklyActivity(List<SimpleWeeklyActivity.WeeklyWork> works) {
        if(works.isEmpty()) return;
        final List<Activity.Work> jobs = works.stream().map(w -> new Activity.Work(Time.calcWeekMilliseconds(w.time), w.task)).collect(Collectors.toList());
        Collections.sort(jobs, (w1, w2) -> Long.compare(w1.time, w2.time));
        long weekZeroTime = Time.getTimeInfo().weekZeroTime;
        final long now = System.currentTimeMillis();
        final Activity.Work cmpJob = works.size() <= 1 ? jobs.get(0) : jobs.get(1);
        if(weekZeroTime + cmpJob.time <= now) {
            weekZeroTime += Time.WEEK_MILLISECONDS;
        }

        for(Activity.Work job : jobs) {
            taskQueue.scheduleAtFixedRate(job.task, job.time + weekZeroTime - now, Time.WEEK_MILLISECONDS);
        }
    }

    public void addSimpleDailyActivity(List<SimpleDailyActivity.DailyWork> works) {
        if(works.isEmpty()) return;
        final List<Activity.Work> jobs = works.stream().map(w -> new Activity.Work(Time.calcDayMilliseconds(w.time), w.task)).collect(Collectors.toList());
        Collections.sort(jobs, (w1, w2) -> Long.compare(w1.time, w2.time));
        long dayZeroTime = Time.getTimeInfo().dayZeroTime;
        final long now = System.currentTimeMillis();
        final Activity.Work cmpJob = works.size() <= 1 ? jobs.get(0) : jobs.get(1);
        if(dayZeroTime + cmpJob.time <= now) {
            dayZeroTime += Time.DAY_MILLISECONDS;
        }

        for(Activity.Work job : jobs) {
            taskQueue.scheduleAtFixedRate(job.task, job.time + dayZeroTime - now, Time.DAY_MILLISECONDS);
        }
    }

    @Override
    public void start() {
        new Procedure(){
            @Override
            protected boolean process() throws Exception {
                xbean.System systemInfo = FSystem.get();
                if(systemInfo.getGsdfirststarttime() == 0){
                    systemInfo.setGsdfirststarttime(System.currentTimeMillis());
                }
                gsdFirstStartTime = systemInfo.getGsdfirststarttime();

                xbean.HuiWuCurTerm curTerm = FHuiWu.getCurTermInfo();
                if(curTerm != null) {
                    final xbean.HuiWuPriviousTerm priviousTerm = xtable.Huiwupriviousterms.get(curTerm.getTermid());
                    if(priviousTerm != null) {
                        priviousTerm.getChampions().forEach((profession, champion) -> {
                            huiwuChampinionNotifys.put(profession, new HuiWuChapinionNotifyRecord(champion.getRoleid()));
                        });
                    }
                }

                return true;
            }
        }.call();

        for (cfg.ectype.WorldBoss wcfg : CfgMgr.worldboss.values()) {
            WorldBoss.create(wcfg);
        }
        new WorldMonster(CfgMgr.expmonster);
        FamilyGodAnimal.creat(CfgMgr.bossconfig);
        registerOperationalActivityHandler();
        new HuiWu(CfgMgr.huiwu);

        List<Integer> list = new ArrayList<>();
        list.addAll(CfgMgr.operationalactivityindex.index);
        list.addAll(CfgMgr.daychargebonusindex.index);

        list.forEach(integer -> {
            OperationalActivity activity = CfgMgr.operationalactivity.get(integer);
            if(activity != null){
                activity.activityinfo.forEach(entry -> {
                    if (activity.isstartactivity) {
                        new OperatorActivity(entry.id, activity.type,
                                Time.todayZeroTime(gsdFirstStartTime + activity.relativestarttime * Time.DAY_MILLISECONDS), Time.todayZeroTime(gsdFirstStartTime + activity.relativeendtime * Time.DAY_MILLISECONDS), entry);
                    } else {
                        new OperatorActivity(entry.id, activity.type, Arrays.asList(activity.timerange), entry);
                    }
                });
            }
        });

        Activity.doLoad();
    }

    @SuppressWarnings("unchecked")
    public <C extends ActivityCondition> OperationalActivityHandler<C> getOperationalActivityHandler(Class<C> clazz){
        return handlerMap.get(clazz);
    }

    public List<ActivityEntry> getEntries(Class<? extends ActivityCondition> clazz){
        return activityEntryMap.getOrDefault(clazz, Collections.emptyList());
    }

    public int getType(int entryId){
        return entry2Type.getOrDefault(entryId, Const.NULL);
    }

    public void addTask(Runnable task) {
        taskQueue.add(task);
    }


    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        final int profession = xtable.Roleinfos.selectProfession(roleid);
        final HuiWuChapinionNotifyRecord record = huiwuChampinionNotifys.get(profession);
        if(record != null && record.roleid == roleid) {
            final long now = java.lang.System.currentTimeMillis();
            if(now > record.lastNotifyTime + NOTIFY_CD) {
                record.lastNotifyTime = now;
                FTips.broadcast(LocationType.CENTER_SCROLL, profession - EProfessionType.QINGYUNMEN + TipsCode.HUIWU_CHAMPINION_QINGYUN, xtable.Roleinfos.selectName(roleid));
            }
        }
        xdb.Transaction.texecuteWhileCommit(() -> {
            addTask(() -> {
                new xdb.Procedure() {
                    @Override
                    protected boolean process() {
                        SActivity msg = new SActivity();
                        final Map<Integer, RoleActivityStatus> activityStatus = FActivity.getRole(roleid).getStatus();
                        for (Activity activity : Activity.activities.values()) {
//                            if (activity.isOpen()) {
                                RoleActivityStatus detail = FActivity.getRoleActivityStatus(activity, activityStatus);
                                msg.activityinfos.put(activity.getId(), FActivity.create(activity, detail));
//                            }
                        }
                        xdb.Transaction.tsendWhileCommit(roleid, msg);

                        execAll(entry -> {
                            OperationalActivityHandler handler = getOperationalActivityHandler(entry.condition.getClass());
                            long lastLogout = Roleinfos.selectLastlogouttime(roleid);
                            if(handler.isDailyActivity() && !Time.isSameDay(lastLogout, System.currentTimeMillis())){
                                handler.onDayOver(roleid, entry);
                            } else {
                                handler.checkNotify(roleid, entry);
                            }
                        });
                        return true;
                    }
                }.call();
            });
        });
        // 这么写是减少死锁机率
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                FHuiWu.sendSTermInfo(roleid);
                return true;
            }
        }.execute();

    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {}

    @Override
    public void afterGsdStart() {
        taskQueue.scheduleAtFixedRate(Activity::doUpdate, 1000, 1000);
    }

    @Override
    public void beforeGsdStop() {}

    @Override
    public void onDayOver() {
        execAll(entry -> {
            OperationalActivityHandler handler = getOperationalActivityHandler(entry.condition.getClass());
            if(handler.isDailyActivity()){
                for (Role role : Onlines.getInstance().roles()) {
                    new Procedure(){
                        @Override
                        protected boolean process() throws Exception {
                            handler.onDayOver(role.getRoleid(), entry);
                            return true;
                        }
                    }.call();
                }
            }
        });
    }

    private void execAll(Consumer<ActivityEntry> consumer){
        activityEntryMap.values().forEach(activityEntries -> activityEntries.forEach(entry -> consumer.accept(entry)));
    }

    private void execCondition(Class<? extends ActivityCondition> clazz, Consumer<ActivityEntry> consumer){
        getEntries(clazz).forEach(entry -> consumer.accept(entry));
    }

    private void triggerCheckOnEvent(Class<? extends ActivityCondition> clazz, Integer... eventType){
        EventModule.INSTANCE.registerListener(Arrays.asList(eventType), event -> {
            Set<Integer> typeSet = new HashSet<>();
            execCondition(clazz, entry -> typeSet.add(getType(entry.id)));
            execCondition(clazz, entry -> {
                int typeId = getType(entry.id);
                OperationalActivityHandler handler = getOperationalActivityHandler(entry.condition.getClass());
                if (typeSet.contains(typeId) && handler.consumeEvent(event, entry)) {
                    typeSet.remove(typeId);
                }
                handler.checkNotify(event.roleId, entry, event);
            });
        });
    }

    private <C extends ActivityCondition> void registerHandler(Class<C> clazz, OperationalActivityHandler<C> handler, Integer... triggerOnEvent) {
        handlerMap.put(clazz, handler);
        if(triggerOnEvent != null){
            triggerCheckOnEvent(clazz, triggerOnEvent);
        }
    }

    private void registerOperationalActivityHandler() {
        registerHandler(CollectItem.class, new CollectitemHandler(), EventType.ADD_ITEM, EventType.DELETE_ITEM);
        registerHandler(RoleLevel.class, new RoleLevelHandler(), EventType.LEVEL_UP);
        registerHandler(RoleCombatPower.class, new RoleCombatPowerHandler(), EventType.COMBATPOWER_CHANGE);
        registerHandler(PetQuality.class, new PetQualityHandler(), EventType.ADD_PET);
        registerHandler(TalismanAwake.class, new TalismanAwakeHandler(), EventType.TALISMAN_AWAKE_LEVEL_UP);
        registerHandler(TalismanStar.class, new TalismanStarHandler(), EventType.TALISMAN_STAR_LEVEL_UP);
        registerHandler(TalismanQuality.class, new TalismanQualityHandler(), EventType.TALISMAN_ADD);
        registerHandler(DailyRecharge.class, new DailyRechargeHandler(), EventType.RECHARGE);
        registerHandler(RechargeShop.class, new RechargeShopHandler());
        registerHandler(AchievementPoints.class, new AchievementHandler());
        registerHandler(DailyGift.class, new DailyGiftBuyHandler());
        registerHandler(EquipAnnealMax.class, new EquipAnnelMaxHandler());
        registerHandler(EquipAnnealTotal.class, new EquipAnnelTotalHandler());
        registerHandler(EquipPerfuseMax.class, new EquipPerfuseMaxHandler());
        registerHandler(EquipPerfuseTotal.class, new EquipPerfuseTotalHandler());
        registerHandler(EquipQuality.class, new EquipQualityHandler());
        registerHandler(TotalCost.class, new TotalCostHandler());

        registerHandler(PetMaxStar.class, new PetMaxStarHandler(), EventType.PET_STAR_UP);
        registerHandler(PetMaxAwake.class, new PetMaxAwakeHandler(), EventType.PET_AWAKE_UP);
        registerHandler(PetMaxLevel.class, new PetMaxLevelHandler(), EventType.PET_LEVEL_UP);
        registerHandler(PetTotalStar.class, new PetTotalStarHandler(), EventType.PET_STAR_UP);
        registerHandler(PetTotalAwake.class, new PetTotalAwakeHandler(), EventType.PET_AWAKE_UP);
        registerHandler(PetTotalLevel.class, new PetTotalLevelHandler(), EventType.PET_LEVEL_UP);
        registerHandler(TalismanMaxLevel.class, new TalismanMaxLevelHandler(), EventType.TALISMAN_LEVEL_UP);
        registerHandler(JadeLevel.class, new JadeLevelHandler(), EventType.JADE_LEVEL_UP);
        registerHandler(StoryNoteLevel.class, new StoryNoteLevelHandler(), EventType.STORYNOTE_LEVEL_UP);
        registerHandler(KillWorldBoss.class, new KillWorldBossHandler(), EventType.KILL_WORLDBOSS);
        registerHandler(ArenaWin.class, new ArenaWinHandler(), EventType.ARENA_WIN);
        registerHandler(ArenaRanking.class, new ArenaRankingHandler(), EventType.ARENA_WIN);
        registerHandler(TeamSpeed.class, new TeamSpeedHandler(), EventType.TEAM_SPEED_WIN);
        registerHandler(TeamFight.class, new TeamFightHandler(), EventType.TEAM_FIGHT_WIN);
        registerHandler(ClimbTower.class, new ClimbTowerHandler(), EventType.CLIMB_TOWER_UP);
        registerHandler(AccessoryQuality.class, new AccessoryQualityHandler(), EventType.EQUIP_ON_BODY);
    }
}

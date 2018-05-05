package lx.gs.activity;

import cfg.Const;
import gnet.link.Onlines;
import gnet.link.Role;
import lx.gs.activity.msg.SActivity;
import xdb.Trace;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by huangqiang on 2016/3/19.
 */
public abstract class Activity {
    // 这个列表只能在启动时修改,
    // 在运行过程中不可再变
    public static final Map<Integer, Activity> activities = new HashMap<>();

    public static void doLoad() {
        for(Activity activity : activities.values()) {
            try {
                xdb.Trace.info("onLoad activity:{}", activity);
                activity.load();
            } catch (Exception e) {
                Trace.error("activity:" + activity + " load error.", e);
            }
        }
    }

    public static void doUpdate() {
        final long now = System.currentTimeMillis();
        for(Activity activity : activities.values()) {
            try {
                activity.update(now);
            } catch (Exception e) {
                xdb.Trace.error("Activity.update activity:" + activity, e);
            }
        }
    }

    private final int id;
    private final long maxOpenDelayTime;

    protected final List<TimeCalculator> intervalOpenTimeCalculators = new ArrayList<>();
    protected final Set<TimeRange> fixedOpenTimes = new HashSet<>();

    // 是否禁止此活动开启
    private boolean enable;

    private volatile boolean isOpen;
    private TimeRange openRange;

    // 只可在 Activity 的构造函数中往以下works里添加Work,
    // 不能在活动运行中还往其中添加!
    // work 必须按照时间顺序添加
    // beforeOpenWork与beforeEndWork中的time都必须为正数,而且数越大表示越早
    // afterOpenWorks的time必须为正数,数越大表示越晚
    protected final List<Work> beforeOpenWorks = new ArrayList<>();
    protected final List<Work> afterOpenWorks = new ArrayList<>();
    protected final List<Work> beforeEndWorks = new ArrayList<>();

    private int beforeOpenIndex;
    private int afterOpenIndex;
    private int beforeEndIndex;



    public Activity(int id, long maxOpenDelayTime) {
        this.id = id;
        this.enable = false;
        this.maxOpenDelayTime = maxOpenDelayTime;

        this.isOpen = false;
        this.openRange = null;

        if(activities.putIfAbsent(id, this) != null) {
            Trace.error("Activity:{} duplicate!", this);
        }
    }

    public static class Work {
        public final long time;
        public final Runnable task;
        public Work(long time, Runnable task) {
            this.time = time;
            this.task = task;
        }
    }

    protected void loadExtraOpenTimes() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.GlobalAllActivity allActivity = FActivity.get();
                final xbean.GlobalActivityOpenInfos openInfo = allActivity.getOpeninfos().get(id);
                if(openInfo != null) {
                    enable = openInfo.getEnable();
                    fixedOpenTimes.addAll(openInfo.getInfos().stream().map(e -> new TimeRange(e.getOpentime(), e.getClosetime())).collect(Collectors.toList()));
                } else {
                    enable = true;
                }
                return true;
            }
        }.call();
    }

    protected void saveExtraOpenTimes() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.GlobalAllActivity allActivity = FActivity.get();
                xbean.GlobalActivityOpenInfos openInfo = allActivity.getOpeninfos().get(id);
                if(openInfo == null) {
                    openInfo = xbean.Pod.newGlobalActivityOpenInfos();
                    allActivity.getOpeninfos().put(id, openInfo);
                }

                openInfo.setEnable(enable);
                final List<xbean.TimeRange> openTimes = openInfo.getInfos();
                openTimes.clear();
                final long now = System.currentTimeMillis();
                for(TimeRange time : fixedOpenTimes) {
                    if(time.closeTime > now) {
                        final xbean.TimeRange r = xbean.Pod.newTimeRange();
                        r.setOpentime(time.openTime);
                        r.setClosetime(time.closeTime);
                        openTimes.add(r);
                    }
                }
                return true;
            }
        }.call();
    }

    public final int getId() {
        return id;
    }

    public final long getTimestamp() {
        return openRange != null ? openRange.openTime : Const.NULL;
    }

    public final long getOpenTime() {
        return openRange != null ? openRange.openTime : Const.NULL;
    }

    public final long getCloseTime() {
        return openRange != null ? openRange.closeTime : Const.NULL;
    }

    public final TimeRange getOpenRange() {
        return openRange;
    }

    public final boolean isOpen() {
        return this.isOpen;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "id=" + id +
                ", enable=" + enable +
                ", isOpen=" + isOpen +
                ", openRange=" + openRange +
                '}';
    }

    protected abstract void onOpen();
    protected abstract void onClose();
    protected abstract void onLoad();

    public void load() {
        loadExtraOpenTimes();
        onLoad();
        if(enable)
            scheduleNext();
    }

    public void update(long now) {
        if(this.openRange != null) {
            final long relateOpenTime = now - openRange.openTime;
            if(isOpen) {
                for( ; this.afterOpenIndex < afterOpenWorks.size() ; this.afterOpenIndex++) {
                    final Work work = afterOpenWorks.get(this.afterOpenIndex);
                    if(work.time <= relateOpenTime) {
                        try {
                            work.task.run();
                        } catch (Exception e) {
                            Trace.error("Activity.update afterOpenWorks. index:" + this.afterOpenIndex, e);
                        }
                    }else {
                        break;
                    }
                }
                final long relateEndTime = now - openRange.closeTime;
                for( ; this.beforeEndIndex < beforeEndWorks.size() ; this.beforeEndIndex++) {
                    final Work work = beforeEndWorks.get(this.beforeEndIndex);
                    if(-work.time <= relateEndTime) {
                        try {
                            work.task.run();
                        } catch (Exception e) {
                            Trace.error("Activity.update beforeEndWorks. index:" + this.beforeEndIndex, e);
                        }
                    }else {
                        break;
                    }
                }
                if(relateEndTime >= 0) {
                    close();
                }
            } else {
                for( ; this.beforeOpenIndex < beforeOpenWorks.size() ; this.beforeOpenIndex++) {
                    final Work work = beforeOpenWorks.get(this.beforeOpenIndex);
                    if(-work.time <= relateOpenTime) {
                        try {
                            work.task.run();
                        } catch (Exception e) {
                            Trace.error("Activity.update beforeOpenWorks. index:" + this.beforeOpenIndex, e);
                        }
                    }else {
                        break;
                    }
                }
                if(relateOpenTime >= 0) {
                    open();
                }
            }
        }
    }

    protected TimeRange calcNextOpenTime() {
        final long now = System.currentTimeMillis();
        TimeRange newOpenRange = null;
        for(TimeCalculator calc : this.intervalOpenTimeCalculators) {
            final TimeRange range = calc.calcNexTime(now, this.maxOpenDelayTime);
            if(range != null && (newOpenRange == null || range.openTime < newOpenRange.openTime))
                newOpenRange = range;
        }
        for(TimeRange range : this.fixedOpenTimes) {
            if(now < Math.max(range.openTime + this.maxOpenDelayTime, range.closeTime) && (newOpenRange == null || range.openTime < newOpenRange.openTime))
                newOpenRange = range;
        }
        return newOpenRange;
    }

    protected void scheduleNext() {
        if(!this.enable) {
            xdb.Trace.warn("activity.scheduleNext enable:false. end");
            return;
        }
        if(this.isOpen) {
            xdb.Trace.warn("activity.scheduleNext has open! ignore");
            return;
        }

        this.openRange = calcNextOpenTime();
        if(this.openRange != null) {
            this.beforeOpenIndex = 0;
            xdb.Trace.info("activity:{} schedule", this);
        }

    }

    public void addFixOpenTime(TimeRange time) {
        if(this.fixedOpenTimes.add(time)) {
            saveExtraOpenTimes();
            if(enable && !isOpen) {
                scheduleNext();
            }
        }
    }

    public void setExternEnable(boolean enable) {
        if(this.enable != enable) {
            this.enable = enable;
            saveExtraOpenTimes();
        }
    }

    public void externClose() {
        if(isOpen) {
            close();
        }
    }

    protected void open() {
        isOpen = true;
        this.afterOpenIndex = 0;
        this.beforeEndIndex = 0;
        Trace.info("activity:{} open", this);

        for(Role role : Onlines.getInstance().roles()) {
            new xdb.Procedure() {
                @Override
                protected boolean process() {
                    final long roleid = role.getRoleid();
                    SActivity msg = new SActivity();
                    msg.activityinfos.put(getId(), FActivity.create(Activity.this, FActivity.getRoleActivityStatus(Activity.this, roleid)));
                    xdb.Transaction.tsendWhileCommit(roleid, msg);
                    return true;
                }
            }.execute();
        }

        this.onOpen();
    }

    protected void close() {
        xdb.Trace.info("activity:{} close", this);
        onClose();

        isOpen = false;
        this.beforeEndIndex = 0;
        this.afterOpenIndex = 0;
        this.beforeEndIndex = 0;
        this.openRange = null;



        for(Role role : Onlines.getInstance().roles()) {
            new xdb.Procedure() {
                @Override
                protected boolean process() {
                    final long roleid = role.getRoleid();
                    SActivity msg = new SActivity();
                    msg.activityinfos.put(getId(), FActivity.create(Activity.this, FActivity.getRoleActivityStatus(Activity.this, roleid)));
                    xdb.Transaction.tsendWhileCommit(roleid, msg);
                    return true;
                }
            }.execute();
        }

        if(enable)
            scheduleNext();
    }

}

package lx.gs.activity;

import cfg.common.DateTimeRange;
import cfg.operationalactivity.ActivityEntry;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/6/17.
 */
public class OperatorActivity extends Activity {
    private final static Map<Integer, Map<Integer, OperatorActivity>> activitysByTypes = new HashMap<>();

    public static OperatorActivity getActivityByTypeAndId(int type, int id) {
        synchronized (activitysByTypes) {
            final Map<Integer, OperatorActivity> activitys = activitysByTypes.get(type);
            return activitys != null ? activitys.get(id) : null;
        }
    }

    public static  Collection<OperatorActivity> getActivitysByType(int type) {
        synchronized (activitysByTypes) {
            return activitysByTypes.getOrDefault(type, Collections.emptyMap()).values();
        }
    }

    private final int type;
    private cfg.operationalactivity.ActivityEntry entryConf;
    public OperatorActivity(int id, int type, List<DateTimeRange> openRanges, ActivityEntry conf) {
        super(id, 0);
        this.type = type;
        this.fixedOpenTimes.addAll(openRanges.stream().map(e ->
                new TimeRange(common.Time.calcDateTime(e.begintime), common.Time.calcDateTime(e.endtime))).collect(Collectors.toList()));
        entryConf = conf;
    }

    public OperatorActivity(int id, int type, long opentime, long endtime, ActivityEntry conf){
        super(id, 0);
        this.type = type;
        this.fixedOpenTimes.add(new TimeRange(opentime, endtime));
        entryConf = conf;
    }

    public final int getType() {
        return type;
    }

    @Override
    public void onLoad() {

    }


    @Override
    protected void onOpen() {
        synchronized (activitysByTypes) {
            Map<Integer, OperatorActivity> activitys = activitysByTypes.get(type);
            if(activitys == null) {
                activitys = new HashMap<>();
            } else {
                activitys = new HashMap<>(activitys);
            }
            activitys.put(getId(), this);
            activitysByTypes.put(type, activitys);
            ActivityModule.INSTACNE.entry2Type.put(this.entryConf.id, type);
            if (!ActivityModule.INSTACNE.activityEntryMap.containsKey(this.entryConf.condition.getClass())) {
                ActivityModule.INSTACNE.activityEntryMap.putIfAbsent(this.entryConf.condition.getClass(), new CopyOnWriteArrayList<>());
            }
            List<ActivityEntry> entryList = ActivityModule.INSTACNE.activityEntryMap.get(this.entryConf.condition.getClass());
            entryList.add(this.entryConf);
        }
    }

    @Override
    protected void onClose() {

        synchronized (activitysByTypes) {
            final Map<Integer, OperatorActivity> activitys = new HashMap<>(activitysByTypes.get(type));
            activitys.remove(getId());
            activitysByTypes.put(type, activitys);
            ActivityModule.INSTACNE.entry2Type.remove(this.entryConf.id);
            List<ActivityEntry> entryList = ActivityModule.INSTACNE.activityEntryMap.get(this.entryConf.condition.getClass());
            if (entryList == null) {
                return;
            }
            entryList.remove(this.entryConf);
            if(entryList.isEmpty()){
                ActivityModule.INSTACNE.activityEntryMap.remove(this.entryConf.condition.getClass());
            }
        }
    }
}

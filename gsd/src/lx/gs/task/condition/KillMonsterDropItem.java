package lx.gs.task.condition;

import xbean.RoleTask;
import xbean.TaskData;

import java.util.Map;

/**
 * Created by huangqiang on 2016/1/15.
 */
public class KillMonsterDropItem extends Condition {
    private final int monsterid;
    private final int dropitemid;
    private final int neednum;
    private final float dropprob;
    public KillMonsterDropItem(int monsterid, int dropitemid, int neednum, float dropprob) {
        this.monsterid = monsterid;
        this.dropitemid = dropitemid;
        this.neednum = neednum;
        this.dropprob = dropprob;
    }
    @Override
    public boolean check(RoleTask info, TaskData data) {
        final Integer value = data.getCounter().get(dropitemid);
        return value != null && value >= neednum;
    }

    @Override
    public void onEvent(RoleTask info, TaskData data, ConditionParam param) {
        if(param.id == monsterid && common.Utils.random01() < dropprob) {
            final Map<Integer, Integer> counter = data.getCounter();
            final int oldValue = counter.getOrDefault(dropitemid, 0);
            if(oldValue >= neednum) return;
            final int newValue = oldValue + param.num;
            counter.put(dropitemid, newValue);
            xdb.Trace.debug("Task.Condition.KillMonsterDropItem change. roleid:{}  monsterid:{} dropitemid:{} add:{} newvalue:{}",
                    info.getRoleid(), monsterid, dropitemid, param.num, newValue);
        }
    }

    @Override
    public int getEventId() {
        return EventIds.MONSTER;
    }
}

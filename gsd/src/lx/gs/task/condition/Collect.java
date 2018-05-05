package lx.gs.task.condition;

import xbean.RoleTask;
import xbean.TaskData;

/**
 * Created by huangqiang on 2016/1/13.
 */
public class Collect extends Condition  {
    private final int mineid;
    private final int num;
    public Collect(int mineid, int num) {
        this.mineid = mineid;
        this.num = num;
    }

    @Override
    public boolean check(RoleTask info, TaskData data) {
        return data.getCounter().getOrDefault(mineid, 0) >= num;
    }

    @Override
    public void onEvent(RoleTask info, TaskData data, ConditionParam param) {
        if(param.id == mineid) {
            final int newValue = common.Utils.addValue(data.getCounter(), mineid, param.num);
            xdb.Trace.debug("Task.Collect roleid:{} mineid:{} add:{} newValue:{}", info.getRoleid(), mineid, param.num, newValue);
        }
    }

    @Override
    public int getEventId() {
        return EventIds.COLLECT;
    }

    public int getMineid(){
        return  mineid;
    }
}

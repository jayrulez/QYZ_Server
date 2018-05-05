package lx.gs.task.condition;

import xbean.RoleTask;
import xbean.TaskData;

/**
 * Created by huangqiang on 2016/1/18.
 */
public class FinishEctype extends Condition {
    private final int ectypeid;
    private final int count;
    public FinishEctype(int ectypeid, int count) {
        this.ectypeid = ectypeid;
        this.count = count;
    }
    @Override
    public boolean check(RoleTask info, TaskData data) {
        return data.getCounter().getOrDefault(ectypeid, 0) >= count;
    }

    @Override
    public void onEvent(RoleTask info, TaskData data, ConditionParam param) {
        if(param.id == ectypeid) {
            final int newValue = common.Utils.addValue(data.getCounter(), ectypeid, param.num);
            xdb.Trace.debug("Task.FinishEctype roleid:{} ectypeid:{} count:{} add:{} newvalue:{}", info.getRoleid(), ectypeid, count, param.num, newValue);
        }
    }

    @Override
    public int getEventId() {
        return EventIds.ECTYPE;
    }
}

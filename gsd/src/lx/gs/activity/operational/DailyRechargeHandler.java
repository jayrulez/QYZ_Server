package lx.gs.activity.operational;

import cfg.CfgMgr;
import cfg.operationalactivity.ActivityEntry;
import cfg.operationalactivity.DailyRecharge;
import cfg.operationalactivity.OperationStatus;
import common.ErrorCode;
import lx.gs.activity.FActivity;
import lx.gs.event.AbstractEvent;
import lx.gs.pay.FPay;

/**
 * @author Jin Shuai
 */
public class DailyRechargeHandler extends OperationalActivityHandler<DailyRecharge> {

    @Override
    public boolean checkCondition(long roleId, ActivityEntry entry, AbstractEvent event) {//covert to yuan
        return (FPay.getRolePay(roleId).getDailytotalpay()* CfgMgr.firstcharge.rmbtojifen)/100  >= cast(entry).num;
    }

    @Override
    public ErrorCode handleCondition(long roleId, ActivityEntry entry){
        return ErrorCode.OK;
    }

    @Override
    public void onDayOver(long roleId, ActivityEntry entry) {
        FActivity.changeStatus(roleId, getOperationalType(entry.id), entry.id, OperationStatus.NOT_COMPLETE);
        FActivity.sendNotify(roleId, getOperationalType(entry.id), entry.id, OperationStatus.NOT_COMPLETE);
        super.checkNotify(roleId, entry);
    }

    @Override
    public boolean isDailyActivity() {
        return true;
    }

    @Override
    protected boolean canChangeCompleteStatus() {
        return true;
    }
}

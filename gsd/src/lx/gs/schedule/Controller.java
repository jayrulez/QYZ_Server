package lx.gs.schedule;

import cfg.controller.IntervalController;
import cfg.controller.MonthlyController;
import cfg.controller.Normal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangqiang on 2016/1/5.
 */
public class Controller extends Scheduler {
    private final cfg.controller.GlobalController ccfg;
    private Activity activity;
    public Controller(cfg.controller.GlobalController ccfg) {
        super(ccfg.id, genCaculators(ccfg), (long)(ccfg.duration * 1000), ccfg.waitbeforeopen * 1000L, ccfg.waitbeforeclose * 1000L);
        this.ccfg = ccfg;
        this.activity = null;
    }

    private static List<TimeCalculator> genCaculators(cfg.controller.GlobalController gc) {
        final List<TimeCalculator> calcs = new ArrayList<>();
        switch (gc.getTypeId()) {
            case IntervalController.TYPEID: {
                final cfg.controller.IntervalController ic = (IntervalController)gc;
                calcs.add(new IntervalTimeCaculator(common.Time.getTime(ic.year, ic.month, ic.day, ic.hour, ic.minute), (long)(gc.duration * 1000), ic.intervaldays * common.Time.DAY_MILLISECONDS));
                break;
            }
            case MonthlyController.TYPEID: {
                final cfg.controller.MonthlyController mc = (MonthlyController)gc;
                calcs.add(new MonthlyTimeCaculator(mc.day, mc.hour, mc.minute, (long)(gc.duration * 1000)));
                break;
            }
        }
        return calcs;
    }

    private Activity createActivity(Controller controller, cfg.controller.Activity acfg) {
        switch (acfg.getTypeId()) {
            case Normal.TYPEID : {
                return new lx.gs.schedule.activity.Normal(controller);
            }
            default: {
                throw new RuntimeException("unknown activity:" + acfg);
            }
        }
    }

    @Override
    public void onScheduleOpen() {
        this.activity = createActivity(this, ccfg.activity);
        this.activity.onScheduleOpen(getOpenTime());
    }

    @Override
    public void onPreOpen() {
        xdb.Trace.info("controller.onpreopen. id:{}", ccfg.id);
        this.activity.onPreOpen();
    }

    @Override
    public void onOpen() {
        xdb.Trace.info("controller.onopen. id:{}", ccfg.id);
        this.activity.onOpen();
    }

    @Override
    public void onPreClose() {
        xdb.Trace.info("controller.onpreclose. id:{}", ccfg.id);
        this.activity.onPreClose();
    }

    @Override
    public void onClose() {
        xdb.Trace.info("controller.onclose. id:{}", ccfg.id);
        this.activity.onClose();
        this.activity = null;
    }

    @Override
    public void onStop() {
        xdb.Trace.info("controller.onstop. id:{}", ccfg.id);
        this.activity.onStop();
        this.activity = null;
    }
}

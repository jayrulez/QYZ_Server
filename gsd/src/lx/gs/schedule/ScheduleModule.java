package lx.gs.schedule;

import cfg.CfgMgr;

import java.util.HashMap;

/**
 * Created by huangqiang on 2015/12/31.
 */
public enum ScheduleModule implements gs.Module, gs.GsdStartListener {
    INSTANCE;

    HashMap<Integer, Controller> globalControllers = new HashMap<>();

    @Override
    public void afterGsdStart() {
        globalControllers.values().forEach(e -> e.start());
    }

    @Override
    public void beforeGsdStop() {

    }

    @Override
    public void start() {
        initControllers();
    }

    private void initControllers() {
        for(cfg.controller.GlobalController gc : CfgMgr.globalcontroller.values()) {
            globalControllers.put(gc.id, new Controller(gc));
        }
    }

}

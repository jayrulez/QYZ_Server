package lx.gs.schedule;

/**
 * Created by huangqiang on 2015/12/31.
 */
public class FSchedule {
    public static boolean isControllerOpen(int controllerid) {
        final Scheduler s = ScheduleModule.INSTANCE.globalControllers.get(controllerid);
        return s != null && s.isOpen();
    }

    public static Controller getController(int controllerid) {
        return ScheduleModule.INSTANCE.globalControllers.get(controllerid);
    }
}

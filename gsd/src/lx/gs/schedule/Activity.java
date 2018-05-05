package lx.gs.schedule;

/**
 * Created by huangqiang on 2016/3/11.
 */
public abstract class Activity {
    protected final Controller controller;
    public Activity(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public abstract void onScheduleOpen(long openTime);
    public abstract void onPreOpen();
    public abstract void onOpen();
    public abstract void onPreClose();
    public abstract void onClose();
    public abstract void onStop();
}

package match;

import common.TaskQueue;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Created by HuangQiang on 2016/5/10.
 */
public final class Manager<T extends IMatcher> {

    public Manager(T matcher) {
        this.matcher = matcher;
    }

    private final T matcher;

    private ScheduledFuture<?> futureTask;

    public void start() {
        futureTask = TaskQueue.getScheduleExecutor().scheduleAtFixedRate(this::update, 1, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        if(futureTask != null) {
            futureTask.cancel(false);
            futureTask = null;
        }
    }

    public T getMatcher() {
        return matcher;
    }

    public void add(Team team) {
        synchronized (this) {
            matcher.add(team);
        }
    }

    public void removeTeamByRoleid(long roleid) {
        synchronized (this) {
            matcher.removeTeamByRoleid(roleid);
        }
    }

    public void removeAllTeam() {
        synchronized (this) {
            matcher.removeAllTeam();
        }
    }

    public void call(Consumer<T> action) {
        synchronized (this) {
            action.accept(matcher);
        }
    }

    private void update() {
        synchronized (this) {
            matcher.update(System.currentTimeMillis());
        }
    }

}

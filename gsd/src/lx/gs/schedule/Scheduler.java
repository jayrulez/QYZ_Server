package lx.gs.schedule;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangqiang on 2016/1/4.
 */
public abstract class Scheduler {
    enum Stage {
        CLOSE,
        PRE_OPEN,
        OPEN,
        PRE_CLOSE,
    }
    private final int id;
    private final List<TimeCalculator> calculators;
    private Future<?> scheduleFuture;
    private final long duration;
    private final long preOpenDuration;
    private final long preCloseDuration;

    private long openTime;
    private long closeTime;
    private Stage stage;

    private boolean isStop;
    public Scheduler(int id, List<TimeCalculator> calcs, long duration, long preOpenDuration, long preCloseDuration) {
        this.id = id;
        this.calculators = calcs;
        this.duration = duration;
        this.preCloseDuration = preCloseDuration;
        this.preOpenDuration = preOpenDuration;
    }

    public long getOpenTime() {
        return openTime;
    }

    public void start() {
        synchronized (this) {
            isStop = false;
            stage = Stage.CLOSE;
            final long now = System.currentTimeMillis();
            calculators.forEach(c -> c.init(now));
            scheduleNextOpen();
        }
    }

    public void stop(boolean stopRunTask) {
        synchronized (this) {
            isStop = true;
            if (scheduleFuture != null) {
                scheduleFuture.cancel(false);
                scheduleFuture = null;
            }
            if (stopRunTask && stage != Stage.CLOSE) {
                onStop();
            }
        }
    }

    public void openAhead() {
        synchronized (this) {
            if(stage != Stage.CLOSE) return;
            if (scheduleFuture != null) {
                scheduleFuture.cancel(false);
                scheduleFuture = null;
            }
            openTime = System.currentTimeMillis() + preOpenDuration;
            closeTime = openTime + duration;
            preOpen();
        }
    }

    public void closeAhead() {
        synchronized (this) {
            if(stage == Stage.OPEN) {
                closeTime = System.currentTimeMillis() + preCloseDuration;
                scheduleFuture.cancel(false);
                preClose();
            }
        }
    }

    public boolean isOpen() {
        synchronized (this) {
            return stage.ordinal() >= Stage.OPEN.ordinal();
        }
    }

    private void scheduleNextOpen() {
        synchronized (this) {
            openTime = Long.MAX_VALUE;
            TimeCalculator calc = null;
            for(TimeCalculator c : calculators) {
                if(c.cur() < openTime) {
                    openTime = c.cur();
                    calc = c;
                }
            }
            closeTime = openTime + duration;
            xdb.Trace.info("scheduler:{} open at:{} close at:{}", id, common.Time.toFormatStr(openTime), common.Time.toFormatStr(closeTime));
            calc.next();
            scheduleFuture = xdb.Xdb.getInstance().getExecutor().schedule(this::preOpen, openTime - preOpenDuration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            onScheduleOpen();
        }
    }

    private void preOpen() {
        synchronized (this) {
            if (stage != Stage.CLOSE) return;
            stage = Stage.PRE_OPEN;
            onPreOpen();
            scheduleFuture = xdb.Xdb.getInstance().getExecutor().schedule(this::open, openTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
    }

    private void open() {
        synchronized (this) {
            if(stage != Stage.PRE_OPEN) return;
            stage = Stage.OPEN;
            onOpen();
            scheduleFuture = xdb.Xdb.getInstance().getExecutor().schedule(this::preClose, closeTime - preCloseDuration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
    }

    private void preClose() {
        synchronized (this) {
            if(stage != Stage.OPEN) return;
            stage = Stage.PRE_CLOSE;
            onPreClose();
            scheduleFuture = xdb.Xdb.getInstance().getExecutor().schedule(this::close, closeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
    }

    private void close() {
        synchronized (this) {
            if(stage != Stage.PRE_CLOSE) return;
            stage = Stage.CLOSE;
            onClose();
            if(!isStop)
             scheduleNextOpen();
        }
    }

    public abstract void onScheduleOpen();
    public abstract void onPreOpen();
    public abstract void onOpen();
    public abstract void onPreClose();
    public abstract void onClose();
    public abstract void onStop();

}

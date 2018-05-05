package lx.gs.schedule;

/**
 * Created by huangqiang on 2016/1/5.
 */
public class IntervalTimeCaculator implements TimeCalculator {
    private final long beginTime;
    private final long duration;
    private final long interval;

    private long curTime;
    public IntervalTimeCaculator(long beginTime, long duration, long interval) {
        this.beginTime = beginTime;
        this.duration = duration;
        this.interval = interval;
    }

    @Override
    public void init(long now) {
        curTime = now >= beginTime ? (now - beginTime) / interval * interval + beginTime : beginTime;
        if(now >= curTime + duration) {
            curTime += interval;
        }
    }

    @Override
    public long cur() {
        return curTime;
    }

    @Override
    public void next() {
        curTime += interval;
    }
}

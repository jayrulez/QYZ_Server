package lx.gs.schedule;

import java.util.Calendar;

/**
 * Created by huangqiang on 2016/1/5.
 */
public class MonthlyTimeCaculator implements TimeCalculator {
    private final int day;
    private final int hour;
    private final int minite;
    private final long duration;

    private long curTime;

    public MonthlyTimeCaculator(int day, int hour, int minite, long duration) {
        this.day = day;
        this.hour = hour;
        this.minite = minite;
        this.duration = duration;
    }

    @Override
    public void init(long now) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minite);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        curTime = calendar.getTimeInMillis();
        if(curTime + duration <= now) {
            calendar.add(Calendar.MONTH, 1);
            curTime = calendar.getTimeInMillis();
        }
    }

    @Override
    public long cur() {
        return curTime;
    }

    @Override
    public void next() {
        init(curTime + duration + 1000);
    }
}

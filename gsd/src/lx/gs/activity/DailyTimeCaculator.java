package lx.gs.activity;

import common.Time;

/**
 * Created by huangqiang on 2016/3/19.
 */
public class DailyTimeCaculator implements TimeCalculator {
    private final long beginTime;
    private final long endTime;
    public DailyTimeCaculator(int hour, int minute, int second, int duration) {
        this.beginTime = (hour * 3600 + minute * 60 + second) * 1000L;
        this.endTime = this.beginTime + duration * 1000L;
    }

    @Override
    public TimeRange calcNexTime(long startTime, long maxDelayOpenTime) {
        long dayZeroTime = Time.todayZeroTime();
        final long timeInDay = startTime - dayZeroTime;
        if(timeInDay >= this.endTime || timeInDay >= startTime + maxDelayOpenTime) {
            dayZeroTime = Time.tomorrowZeroTime();
        }
        return new TimeRange(beginTime + dayZeroTime, endTime + dayZeroTime);
    }
}

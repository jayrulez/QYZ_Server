package lx.gs.activity;

import common.Time;

/**
 * Created by huangqiang on 2016/3/19.
 */
public class WeeklyTimeCaculator implements TimeCalculator {
    private final long beginTime;
    private final long endTime;

    /**
     *
     * @param weekday  周一到周日 对应  0 - 6
     * @param hour
     * @param minute
     * @param second
     */
    public WeeklyTimeCaculator(int weekday, int hour, int minute, int second, int duration) {
        this.beginTime = common.Time.calcWeekMilliseconds(weekday, hour, minute, second);
        this.endTime = this.beginTime + duration * 1000L;
    }

    public WeeklyTimeCaculator(cfg.common.WeekTime time, int duration) {
        this.beginTime = common.Time.calcWeekMilliseconds(time);
        this.endTime = this.beginTime + duration * 1000L;
    }
//
//    @Override
//    public long calcNexTime(long startTime) {
//        final long time = common.Time.getThisWeekMondayZeroTime(startTime) + this.beginTime;
//        return startTime <= time ? time : time + common.Time.DAY_MILLISECONDS * 7;
//    }


    @Override
    public TimeRange calcNexTime(long startTime, long maxDelayOpenTime) {
        long weekZeroTime = Time.getThisWeekMondayZeroTime();
        final long timeInWeek = startTime - weekZeroTime;
        if(endTime <= timeInWeek || beginTime + maxDelayOpenTime <= timeInWeek) {
            weekZeroTime = Time.getNextWeekMondayZeroTime();
        }
        return new TimeRange(beginTime + weekZeroTime, endTime + weekZeroTime);
    }
}

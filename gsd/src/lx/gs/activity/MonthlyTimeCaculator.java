package lx.gs.activity;

/**
 * Created by huangqiang on 2016/3/19.
 */
public class MonthlyTimeCaculator implements TimeCalculator{
    private final long beginTime;
    private final long endTime;
    public MonthlyTimeCaculator(int day, int hour, int minute, int duration) {
        this.beginTime = ((day - 1) * 86400 + hour * 3600 + minute * 60) * 1000L;
        this.endTime = this.beginTime + duration * 1000L;
    }

    @Override
    public TimeRange calcNexTime(long startTime, long maxDelayOpenTime) {
        long monthZeroTime = common.Time.getThisMonthZeroTime();
        long timeOfMonth = startTime - monthZeroTime;
        if(timeOfMonth >= endTime || timeOfMonth >= beginTime + maxDelayOpenTime)  {
            monthZeroTime = common.Time.getNextMonthZeroTime();
        }
        return new TimeRange(monthZeroTime + beginTime, monthZeroTime + endTime);
    }
}

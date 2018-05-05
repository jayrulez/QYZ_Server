package lx.gs.activity;

/**
 * Created by huangqiang on 2016/3/19.
 */
public interface TimeCalculator {
    TimeRange calcNexTime(long startTime, long maxDelayOpenTime);
}

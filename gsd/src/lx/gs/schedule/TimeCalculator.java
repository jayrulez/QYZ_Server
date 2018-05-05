package lx.gs.schedule;

/**
 * Created by huangqiang on 2016/1/4.
 */
public interface TimeCalculator {
    void init(long now);
    long cur();
    void next();
}

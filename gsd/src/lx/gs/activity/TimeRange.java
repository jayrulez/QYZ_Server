package lx.gs.activity;

import java.util.Objects;

/**
 * Created by HuangQiang on 2016/6/20.
 */
public final class TimeRange {
    public final long openTime;
    public final long closeTime;
    public TimeRange(long openTime, long closeTime) {
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(openTime) * 1231984 + Long.hashCode(closeTime);
    }

    @Override
    public boolean equals(Object o) {
        final TimeRange r = (TimeRange)o;
        return r.openTime == this.openTime && r.closeTime == this.openTime;
    }

    @Override
    public String toString() {
        return "{opentime=" + common.Time.toFormatStr(openTime) + ", closetime=" + common.Time.toFormatStr(closeTime) + "}";
    }
}

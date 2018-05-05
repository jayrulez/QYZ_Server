package lx.gs.activity;

import cfg.Const;
import common.Time;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/5/19.
 */
public class SimpleWeeklyActivity extends Activity {
    private static AtomicInteger NEXT_ID = new AtomicInteger(0);

    SimpleWeeklyActivity(List<WeeklyWork> works) {
        super(NEXT_ID.incrementAndGet(), 0);

        this.intervalOpenTimeCalculators.add(new WeeklyTimeCaculator(0, 0, 0, 0, 0));
        this.afterOpenWorks.addAll(works.stream().map(w -> new Work(Time.calcWeekMilliseconds(w.time), w.task)).collect(Collectors.toList()));
    }

    public static class WeeklyWork {
        public final cfg.common.WeekTime time;
        public final Runnable task;
        public WeeklyWork(cfg.common.WeekTime time, Runnable task) {
            this.time = time;
            this.task = task;
        }
    }

    @Override
    public void onLoad() {
    }

    @Override
    protected void onOpen() {
    }


    @Override
    protected void onClose() {

    }
}

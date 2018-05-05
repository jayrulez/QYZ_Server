package common;

import cfg.common.DateTime;
import cfg.common.DayTimeRange;
import cfg.common.WeekTimeRange;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class Time {

	public static class TimeInfo {
		public final long dayZeroTime;
		public final long weekZeroTime;
		public final long monthZeroTime;
		public TimeInfo(long dt, long wt, long mt) {
			this.dayZeroTime = dt;
			this.weekZeroTime = wt;
			this.monthZeroTime = mt;
		}
	}
	private static volatile TimeInfo timeInfo;
	
	public static TimeInfo getTimeInfo() {
		return timeInfo;
	}
	
	public static void refreshTimeInfo() {
		final long now = System.currentTimeMillis();

		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		final long monthZeroTime = cal.getTimeInMillis();
		
		timeInfo = new TimeInfo(todayZeroTime(now), getThisWeekMondayZeroTime(now), monthZeroTime);
		
//		printTime(dayZeroTime);
//		printTime(weekZeroTime);
//		printTime(monthZeroTime);
	}
	
	public static void printTime(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 System.out.println(sdf.format(cal.getTime()));
	}

	public static String toFormatStr(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(cal.getTime());
	}
	
	public static Calendar nextHourMinuteOfDay(int hourOfDay, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		if (cal.before(Calendar.getInstance()))
			cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal;
	}
	
	public final static long TIMEZONE_OFFSET = Calendar.getInstance().getTimeZone().getRawOffset();
	public final static long DAY_MILLISECONDS = 86400000;
	public final static long HOUR_MILLISECONDS = 3600000;
	public final static long MINUTE_MILLISECONDS = 60000;
    public final static long WEEK_MILLISECONDS = 86400000 * 7;
	public final static long WEEKDAY_OF_19700101 = 3;

	public static boolean isSameDay(long time1, long time2) {
		return (time1 + TIMEZONE_OFFSET) / DAY_MILLISECONDS == (time2 + TIMEZONE_OFFSET) / DAY_MILLISECONDS;
	}
	
	public static boolean isSameWeek(long time1, long time2){
		return getThisWeekMondayZeroTime(time1) == getThisWeekMondayZeroTime(time2);
	}
	
	public static long todayZeroTime() {
		return todayZeroTime(System.currentTimeMillis());
	}
	
	public static long todayZeroTime(long time) {
		return (time + TIMEZONE_OFFSET) / DAY_MILLISECONDS * DAY_MILLISECONDS - TIMEZONE_OFFSET;
	}
	
	public static long tomorrowZeroTime() {
		return tomorrowZeroTime(System.currentTimeMillis());
	}
	
	public static long tomorrowZeroTime(long time) {
		return todayZeroTime(time) + DAY_MILLISECONDS;
	}
	
	public static long anotherDayZeroTime(long time, int offsetDayNum) {
		return todayZeroTime(time) + DAY_MILLISECONDS * offsetDayNum;
	}
	
	public static boolean isContinuesDay(long time1, long time2){
		return (time1 + TIMEZONE_OFFSET) / DAY_MILLISECONDS + 1 == (time2 + TIMEZONE_OFFSET) / DAY_MILLISECONDS;
	}
	
	public static long intervalDays(long time1, long time2){
		long a = (time1 + TIMEZONE_OFFSET) / DAY_MILLISECONDS;
		long b = (time2 + TIMEZONE_OFFSET) / DAY_MILLISECONDS;
		return  Math.abs(a-b);
	}

	public static long getDayMilliseconds(long time) {
		return time - todayZeroTime(time);
	}

	public static int getHourOfDay(long time){
		return (int)((time + TIMEZONE_OFFSET) % DAY_MILLISECONDS / HOUR_MILLISECONDS);
	}

	/**
	 * @return 周1 为0, 。。。 周日 为6
	 */
	public static int getWeekDay(long time) {
		return (int) ((time + TIMEZONE_OFFSET) / DAY_MILLISECONDS + WEEKDAY_OF_19700101) % 7;
	}
	
	public static int getWeekDay(){
		return getWeekDay(System.currentTimeMillis());
	}

    public static long getWeekMilliseconds(long now) {
        return now - getThisWeekMondayZeroTime(now);
    }
	
    public static long getNextWeekMondayZeroTime(long time) {
        return todayZeroTime(time) + DAY_MILLISECONDS * (7 - getWeekDay(time));
    }

	public static long getNextWeekMondayZeroTime() {
		return getNextWeekMondayZeroTime(System.currentTimeMillis());
	}

	public static long getThisWeekMondayZeroTime(long time) {
		return todayZeroTime(time) - DAY_MILLISECONDS * getWeekDay(time);
	}

	public static long getThisWeekMondayZeroTime() {
		return getThisWeekMondayZeroTime(System.currentTimeMillis());
	}

	public static long calcWeekMilliseconds(int weekday, int hour, int minute, int second) {
		return (weekday * 86400 + hour * 3600 + minute * 60 + second) * 1000L;
	}

    public static long calcWeekMilliseconds(cfg.common.WeekTime time) {
        return calcWeekMilliseconds(time.weekday - 1, time.hour, time.minute, time.second);
    }

    public static long calcWeekMilliseconds(cfg.ectype.TimeLimit time) {
        return calcWeekMilliseconds(time.day - 1, time.hour, time.minute, time.second);
    }

    public static long calcDayMilliseconds(cfg.common.DayTime time) {
        return (time.hour * 3600 + time.minute * 60 + time.second) * 1000L;
    }

    public static boolean inDayTimeRange(cfg.common.DayTimeRange time, long now) {
        final long dayTime = getDayMilliseconds(now);
        return calcDayMilliseconds(time.begintime) <= dayTime && dayTime < calcDayMilliseconds(time.endtime);
    }

    public static boolean inDayTimeRange(List<DayTimeRange> times, long now) {
        final long dayTime = getDayMilliseconds(now);
        for(DayTimeRange time : times) {
            if(calcDayMilliseconds(time.begintime) <= dayTime && dayTime < calcDayMilliseconds(time.endtime))
                return true;
        }
        return false;
    }

    public static boolean inWeekTimeRange(cfg.common.WeekTimeRange time, long now) {
        final long dayTime = getWeekMilliseconds(now);
        return calcWeekMilliseconds(time.begintime) <= dayTime && dayTime < calcWeekMilliseconds(time.endtime);
    }

    public static boolean inWeekTimeRange(List<WeekTimeRange> times, long now) {
        final long dayTime = getWeekMilliseconds(now);
        for(WeekTimeRange time : times) {
            if(calcWeekMilliseconds(time.begintime) <= dayTime && dayTime < calcWeekMilliseconds(time.endtime))
                return true;
        }
        return false;
    }

	public static long getThisMonthZeroTime(long time) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTimeInMillis();
	}

	public static long getThisMonthZeroTime() {
		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTimeInMillis();
	}

	public static long getNextMonthZeroTime(long time) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		return cal.getTimeInMillis();
	}

	public static long getNextMonthZeroTime() {
		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		return cal.getTimeInMillis();
	}
	
    /**
     * 获取当月天数
     * @return
     */
    public static int getCurrentMonthDays() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据指定年月获取对应的月份天数
     * */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

	public static boolean after(long afterTime, long now) {
		return now >= afterTime;
	}

	public static boolean after(long afterTime) {
		return System.currentTimeMillis() >= afterTime;
	}

	public static boolean before(long beforeTime, long now) {
		return now < beforeTime;
	}

	public static boolean before(long beforeTime) {
		return System.currentTimeMillis() < beforeTime;
	}

	public static long getTime(int year, int month, int day, int hour, int minute) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DAY_OF_MONTH, day);
		a.set(Calendar.HOUR_OF_DAY, hour);
		a.set(Calendar.MINUTE, minute);
		a.clear(Calendar.SECOND);
		a.clear(Calendar.MILLISECOND);
		return a.getTimeInMillis();
	}

    /**
     * 获取当前时间的小时
     * @param time
     * @return
     */
    public static int getTimeHour(long time){
        long interval = time - todayZeroTime(time);
        return (int)(interval / HOUR_MILLISECONDS);
    }

    /**
     *获取当前时间的分钟
     * @return
     */
    public static int getTimeMinute(long time){
        long interval = time - todayZeroTime(time);
        long left = interval % HOUR_MILLISECONDS;
        return (int)(left / MINUTE_MILLISECONDS);
    }

    /**
     * 获取在指定小时和指定分钟的时候经过的时间
     * @param hour
     * @param minute
     * @return
     */
    public static long getTodayMillisecondByHourAndMin(int hour, int minute){
        return hour * HOUR_MILLISECONDS + minute * MINUTE_MILLISECONDS;
    }

    private static class IntervalTask implements Runnable {
        private final Runnable task;
        private final long interval;
        private long nextRunTime;

        public IntervalTask(long initTime, long interval, Runnable task) {
            this.task = task;
            this.interval = interval;

            final long now = System.currentTimeMillis();
            final long todayTime = initTime + (now - initTime) / interval * interval;
            this.nextRunTime = todayTime >= now ? todayTime : todayTime + interval;

            TaskQueue.getScheduleExecutor().schedule(this, nextRunTime - now, TimeUnit.MILLISECONDS);
        }

        @Override
        public void run() {
            final long now = System.currentTimeMillis();
            if(now >= nextRunTime) {
                nextRunTime += /* (now - nextRunTime) / interval * interval + */ interval;
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    TaskQueue.getScheduleExecutor().schedule(this, nextRunTime - now, TimeUnit.MILLISECONDS);
                }
            } else {
                TaskQueue.getScheduleExecutor().schedule(this, nextRunTime - now, TimeUnit.MILLISECONDS);
            }
        }
    }

	public static void everydaySchedule(Runnable task, int hour, int minute) {
        new IntervalTask(todayZeroTime() + hour * HOUR_MILLISECONDS + minute * MINUTE_MILLISECONDS, DAY_MILLISECONDS, task);
	}

    public static long calcDateTime(DateTime time) {
        return getTime(time.year, time.month, time.day, time.hour, time.minute) + time.second * 1000L;
    }

	public static void hourSchedule(Runnable task) {
        new IntervalTask(0, HOUR_MILLISECONDS, task);
	}

    public static int toSeconds(long milliseconds) {
        return (int)((milliseconds + 999) / 1000);
    }

    public static long toMilliseconds(float seconds) {
        return (long)(seconds * 1000);
    }
    
    public static void main(String[] args){
        System.out.println(Long.parseLong("WZJ2G1TV5Z", 36));
        System.out.println(String.format("%s %s %s", -1/5, -6/5, 6/5));
    	System.out.println(Time.getWeekDay(System.currentTimeMillis()+24*4*60*60*1000));
		printTime(todayZeroTime());
		
		System.out.println(new Date(getThisWeekMondayZeroTime()));
		System.out.println(getWeekDay(0));
		System.out.println(getWeekDay(System.currentTimeMillis()));
		System.out.println(getWeekDay(todayZeroTime()));
		System.out.println(getWeekDay(System.currentTimeMillis() - HOUR_MILLISECONDS * 10));
		System.out.println(getWeekDay(System.currentTimeMillis() - HOUR_MILLISECONDS * 17));
		System.out.println(getWeekDay(getThisWeekMondayZeroTime()));
		System.out.println(getWeekDay(getNextWeekMondayZeroTime()));
    }
}

package xdb.util;

import org.w3c.dom.Element;

import xdb.XdbConf;

public class ConcurrentCacheConf {
	private final String name;
	private volatile int capacity;

	// clean time every day.
	private volatile int hourOfDay;
	private volatile int minute;

	public static final int DEFAULT_CACHE_CAPACITY = 20480;
	public static final int DEFAULT_CLEAN_HOUR_OF_DAY = 5;
	public static final int DEFAULT_CLEAN_MINUTE = 30;

	public ConcurrentCacheConf(Element self) {
		name = self.getAttribute("name").trim().toLowerCase();
		capacity = XdbConf.getInt(self, "capacity", DEFAULT_CACHE_CAPACITY);
		hourOfDay = XdbConf.getInt(self, "hourOfDay", DEFAULT_CLEAN_HOUR_OF_DAY);
		minute = XdbConf.getInt(self, "minute", DEFAULT_CLEAN_MINUTE);
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getHourOfDay() {
		return hourOfDay;
	}

	public int getMinute() {
		return minute;
	}

	public synchronized void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public synchronized void setHourOfDay(int hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	public synchronized void setMinute(int minute) {
		this.minute = minute;
	}
}

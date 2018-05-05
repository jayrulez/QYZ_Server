package com.goldhuman.Common;

import java.util.Observable;
import java.util.Timer;


public class TimerObserver extends Observable
{
	private static TimerObserver instance = new TimerObserver();
	private static long now = System.currentTimeMillis();
	private  Timer timer;

	public static TimerObserver GetInstance()
	{
		return instance;
	}

	public TimerObserver()
	{
		timer = new Timer("jio--TimerObserver");
		timer.schedule(
			new java.util.TimerTask()
			{
				public void run()
				{
					now = scheduledExecutionTime();
					instance.setChanged();
					instance.notifyObservers();
				}
			},
			0,
			1000
		);
	}

	public void StopTimer()
	{
		timer.cancel();
	}

	public static class WatchDog
	{
		private long t = now;
		public long GetTime() { return now; }
		public long Elapse()  { return now - t; }
		public void Reset() { t = now; }
	}
}

package com.goldhuman.Common;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
/*
 * 这里的update仍然有点小问题，新加的任务可能会比早先加的任务提前执行ThreadPool.AddTask(tp.task)而先得到执行
 */
public class TimerTask implements Observer
{
	private class TaskPair
	{
		TaskPair(long _w, Runnable _t)
		{
			waitsecds = _w;
			task = _t;
		}

		long waitsecds;
		Runnable task;
	}

	private static TimerTask instance = new TimerTask();
	private static SortedMap<Long, LinkedList<TaskPair>> tasks = Collections
			.synchronizedSortedMap(new TreeMap<Long, LinkedList<TaskPair>>());
	private long elapse = 0;

	private TimerTask()
	{
		TimerObserver.GetInstance().addObserver(this);
	}

	public synchronized void update(Observable o, Object arg)
	{
		++elapse;
		synchronized (tasks)
		{
			Set<Map.Entry<Long, LinkedList<TaskPair>>> entryset = tasks.entrySet();
			Iterator<Map.Entry<Long, LinkedList<TaskPair>>> it = entryset.iterator();
			Map.Entry<Long, LinkedList<TaskPair>> map_entry;

			while (it.hasNext())
			{
				map_entry = it.next();
				LinkedList<TaskPair> ll = map_entry.getValue();
				Iterator<TaskPair> iter = ll.iterator();
				synchronized(ll)
				{
					while (iter.hasNext())
					{
						TaskPair tp = (TaskPair) iter.next();
						if (tp.waitsecds > elapse)
							break;
						ThreadPool.AddTask(tp.task);
						iter.remove();
					}
				}
				if(ll.isEmpty())
					it.remove();
			}
		}
	}

	public synchronized void AddTask(Runnable task, long waitsecds)
	{
		LinkedList<TaskPair> ll = (LinkedList<TaskPair>) tasks.get(waitsecds);
		if (ll == null)
		{
			tasks.put(waitsecds, ll = new LinkedList<TaskPair>());
		} 
		synchronized(ll)
		{
			ll.addFirst(new TaskPair(waitsecds + elapse, task));
		}
	}

	public static void AddTimerTask(Runnable task, long waitsecds)
	{
		instance.AddTask(task, waitsecds);
	}
}

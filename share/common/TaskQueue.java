package common;

import xdb.Trace;

import java.util.ArrayList;
import java.util.concurrent.*;

public final class TaskQueue implements Runnable {
	private ArrayList<Runnable> tasks;
	private ArrayList<Runnable> waitAddTask;

	private boolean running;

	public TaskQueue() {
        tasks = null;
        waitAddTask = new ArrayList<>();
        running = false;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
			    if(waitAddTask.isEmpty()) {
                    running = false;
                    return;
                }
                tasks = waitAddTask;
                waitAddTask = new ArrayList<>();
			}
			for(Runnable task : tasks) {
				try {
					task.run();
				} catch (Exception e) {
					Trace.error("TaskQueue", e);
				}
			}
		}
	}

	public final void add(Runnable task) {
        //Trace.debug("TaskQueue.add task:{}", task);
		synchronized (this) {
            waitAddTask.add(task);
			if (!running) {
                running = true;
                this.schedule();
			}
		}
	}

	public final void schedule(Runnable task, long delayMilliseconds) {
        //Trace.debug("TaskQueue.schedule task:{} delay:{}", task, delayMilliseconds);
        TaskQueue.scheduleExecutor.schedule(() -> {
            this.add(task);
		}, delayMilliseconds, TimeUnit.MILLISECONDS);
	}

	public final ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long initDelay, long interval) {
		return TaskQueue.scheduleExecutor.scheduleAtFixedRate(() -> {
            this.add(task);
		}, initDelay, interval, TimeUnit.MILLISECONDS);
	}

	protected void schedule() {
        TaskQueue.executor.execute(this);
	}

	public static ExecutorService getNormalExecutor() {
		return TaskQueue.executor;
	}

	public static ScheduledExecutorService getScheduleExecutor() {
		return TaskQueue.scheduleExecutor;
	}

	private static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	private static final ScheduledExecutorService scheduleExecutor = Executors.newScheduledThreadPool(4);
}

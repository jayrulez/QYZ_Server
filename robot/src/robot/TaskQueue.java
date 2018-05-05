package robot;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.*;

public final class TaskQueue implements Runnable {

	private final LinkedList<Runnable> tasks;
	private final ArrayList<Runnable> waitAddTask;

	private boolean running;

	public TaskQueue() {
		this.tasks = new LinkedList<>();
		this.waitAddTask = new ArrayList<>();
		this.running = false;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this.waitAddTask) {
				this.tasks.addAll(this.waitAddTask);
				this.waitAddTask.clear();
				if (this.tasks.isEmpty()) {
					this.running = false;
					return;
				}
			}
			while (true) {
				Runnable task = this.tasks.poll();
				if (task == null)
					break;
				try {
					task.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public final void add(Runnable task) {
		synchronized (this.waitAddTask) {
			this.waitAddTask.add(task);
			if (!this.running) {
				this.running = true;
				schedule();
			}
		}
	}

	public final void schedule(Runnable task, long delayMilliseconds) {
		scheduleExecutor.schedule(() -> {
			add(task);
		}, delayMilliseconds, TimeUnit.MILLISECONDS);
	}

	public final ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long initDelay, long interval) {
		return scheduleExecutor.scheduleAtFixedRate(() -> {
			add(task);
		}, initDelay, interval, TimeUnit.MILLISECONDS);
	}

	protected void schedule() {
		executor.execute(this);
	}

	private final static ExecutorService executor = Executors.newCachedThreadPool();
	private final static ScheduledExecutorService scheduleExecutor = Executors.newSingleThreadScheduledExecutor();
}

package map.map;

import map.agent.Agent;
import xdb.Trace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by HuangQiang on 2016/7/20.
 */
public class Scheduler {
    private final GameMap gameMap;


    private final class Task {
        final Agent agent;
        final Runnable task;
        final long time;
        Task(Agent agent, Runnable task, long time) {
            this.agent = agent;
            this.task = task;
            this.time = time;
        }
    }
    private LinkedList<Task> tasks = new LinkedList<>();


    public Scheduler(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void schedule(Runnable task, long delay) {
        this.tasks.add(new Task(null, task, gameMap.getNow() + delay));
    }

    public void schedule(Agent agent, Runnable task, long delay) {
        this.tasks.add(new Task(agent, task, gameMap.getNow() + delay));
    }

    private ArrayList<Task> activeTasks = new ArrayList<>();
    public void update(long now) {
        for(Iterator<Task> it = tasks.iterator(); it.hasNext() ; ) {
            final Task task = it.next();
            if(task.time <= now) {
                it.remove();
                if(task.agent == null || task.agent.isActive())
                    activeTasks.add(task);
            }
        }
        if(!activeTasks.isEmpty()) {
            for(Task task : activeTasks) {
                try {
                    if(task.agent == null || task.agent.isActive())
                        task.task.run();
                } catch (Exception e) {
                    Trace.error("Scheduler.doUpdate " + gameMap, e);
                }
            }
            activeTasks.clear();
        }
    }
}

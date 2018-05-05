package com.goldhuman.Common;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class TaskGraph extends Runnable implements TaskState
{
	protected class TaskNode extends Runnable implements Observer
	{
		private HashSet<TaskNode> prev = new HashSet<TaskNode>();
		private HashSet<TaskNode> next = new HashSet<TaskNode>();

	    private StatefulRunnable task = null;
    	private TaskGraph graph = null;
	    private int state;

		void TurnStopping()
    	{
        	if ( state == RUNNING )   state = STOPPING;
	        else if ( state == INIT ) state = STOPPED;
    	}

	    void SetInit()
    	{
        	state = INIT;
	        if ( task != null )
    	        task.Init();
	    }

	    void TurnRunning()
    	{
        	if ( state == INIT || state >= FAIL )
	        {
    	        ThreadPool.AddTask(this);
        	    state = RUNNING;
	        }
    	}

		void Destroy()
    	{
			// delete this;
	    }

	    TaskNode(StatefulRunnable _task, TaskGraph _graph) 
		{
			task = _task;
			graph = _graph;
			state = INIT;
        	if ( task != null )
	        {
    	        task.Init();
        	    task.addObserver(this);
	        }
    	}

		public void update(Observable o, Object arg)
		{
			synchronized ( graph.locker ) {
				if ( graph.IsStopping() )		
					return;
				state = task.GetState();
			}
			graph.RunnableChangeState( this );
			if ( state >= FAIL )
			{
				synchronized ( graph.locker ) {
					Iterator<TaskNode> iter = next.iterator();
					while( iter.hasNext() ) {
						((TaskNode)iter.next()).TurnRunning();
					}
				}
			}
			else if ( state == RUNNING )
			{
				ThreadPool.AddTask( this );
			}
		}

		boolean IsFinish() { return state >= FAIL || state == STOPPED; }
    	public int GetState() { return state; }

	    public void run()
    	{
        	if ( state == STOPPING )
	        {	
    	        state = STOPPED;
        	    return;
	        }
    	    if ( state != RUNNING )
        	    return;
	        synchronized ( graph.locker ) {
				boolean prev_all_finished = true; 
				Iterator<TaskNode> iter = prev.iterator();
				while ( iter.hasNext() ) {
					if( !((TaskNode)iter.next()).IsFinish()) 
					{
						prev_all_finished =false;
						break;
					}
				}
				if ( !prev_all_finished )
	        	{
    	        	state = INIT;
	        	    return;
		        }
        	}
	        if ( task != null )
    	    {
        	    task.Run();
            	if ( state == RUNNING || state == STOPPING )
                	ThreadPool.AddTask(this);
	        }
    	    else
        	{
            	state = STOPPED;
	            graph.Stop();
    	    }
	    }

		public void AddChild(TaskNode node)
	    {
			synchronized ( graph.locker ) {
	    	    next.add(node);
    	    	node.prev.add(this);
			}
    	}
	}


    private HashSet<StatefulRunnable> runners = new HashSet<StatefulRunnable>();
	private HashSet<TaskNode> nodes = new HashSet<TaskNode>();
    private boolean restart;
    private boolean stopping;
	private TaskNode root = null;
	TaskContext context= null;
	Object locker = new Object();

    public void run()
    {
        if ( ! IsFinish() )
        {
            ThreadPool.AddTask(this);
            stopping = false;
        }
        else if ( restart )
        {
			Iterator<TaskNode> iter = nodes.iterator();
			while ( iter.hasNext() ) {
				((TaskNode)iter.next()).SetInit();
			}
            root.TurnRunning();
            restart  = false;
            stopping = false;
        }
        else
        {
			Iterator iter = runners.iterator();
			while ( iter.hasNext() ) {
				((StatefulRunnable)iter.next()).Destroy();
			}
			iter = nodes.iterator();
			while ( iter.hasNext() ) {
				((TaskNode)iter.next()).Destroy();
			}
            //delete context;
            //delete this;
        }
    }

	protected TaskGraph(TaskContext ctx)
	{
		restart = false;
		stopping = false;
		root = null;
		context = ctx;
    }

	public boolean IsFinish()
    {
		synchronized ( locker ) {
			Iterator<TaskNode> iter = nodes.iterator();
			while ( iter.hasNext() ) {
				if( !((TaskNode)iter.next()).IsFinish() )
					return false;
			}
		}
		return true;
    }

    public boolean IsStopping() { return stopping; }

    public void Start(TaskNode init_node)
    {
		root = init_node;
		root.TurnRunning();
    }

    public void Restart(TaskNode init_node)
    {
        root = init_node;
        restart = true;
        Stop();
    }

	public void Stop()
    {
		synchronized ( locker ) {
			Iterator<TaskNode> iter = nodes.iterator();
			while ( iter.hasNext() ) {
				((TaskNode)iter.next()).TurnStopping();
			}
    	    stopping = true;
        	ThreadPool.AddTask(this);
		}
    }

    public TaskNode CreateNode(StatefulRunnable r)
    {
        TaskNode node = new TaskNode(r, this);
        r.graph = this;
        runners.add(r);
        nodes.add(node);
        return node;
    }

    public TaskNode CreateStopNode()
    {
        TaskNode node = new TaskNode(null, this);
        nodes.add(node);
        return node;
    }

    public TaskContext GetContext() { return context; }

    public void RunnableChangeState(TaskNode n)
    {
        if ( n.GetState() == FAIL )
            Stop();
    }

}

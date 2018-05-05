package com.goldhuman.Common;

import java.util.Observable;
import java.util.Observer;

public abstract class StatefulRunnable extends Observable implements Observer,TaskState
{
    TaskGraph graph = null;
	
    TaskContext GetContext() { return graph.context; }

    public void Destroy() { 
		// delete this;
	}
    public void Init() { }
    public void update(Observable o,Object arg) { }
    public abstract int GetState();
    public abstract void Run();
};

package com.goldhuman.IO.Protocol;

import com.goldhuman.Common.Runnable;

public class ReconnectTask extends Runnable
{
	public Manager manager;

    public ReconnectTask(Manager m,int priority)
	{
		super(priority);
		manager = m;
	}

    public void run()
    {
        Protocol.Client(manager);  //reconnect
    }
}

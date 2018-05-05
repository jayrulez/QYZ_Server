
package com.goldhuman.IO.Protocol;

import com.goldhuman.IO.PollIO;

public final class Task extends com.goldhuman.Common.Runnable
{
	private Manager manager;
	private Session session;
	private Protocol protocol;
	//private boolean	immediately = false;
	//TODO
	private long time_start = 0;

	private Task(int priority, Manager _manager, Session _session, Protocol _protocol)
	{
		super(priority);
		manager = _manager;
		session = _session;
		protocol = _protocol;
		time_start = System.currentTimeMillis();
	}

	public void run()
	{
		try
		{
			protocol.time_wait = System.currentTimeMillis() - time_start;
			protocol.Process(manager, session);
			if (Session.need_wakeup /*&& !immediately*/ ) 
			{
				PollIO.WakeUp();
				Session.need_wakeup = false;
			}
		}
		catch (ProtocolException e)
		{
			System.out.println("jio exception type=" + protocol.getType() + "," + e.toString());
			manager.Close(session);
		}
	}
}

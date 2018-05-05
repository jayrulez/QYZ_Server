
package com.goldhuman.IO.Protocol;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Octets;
import com.goldhuman.Common.TimerObserver;
import com.goldhuman.IO.NetIO.NetSession;
import com.goldhuman.IO.PollIO;

import java.net.SocketAddress;
import java.util.LinkedList;

public final class Session extends NetSession
{
	protected Manager manager;
	public static volatile boolean need_wakeup = false;
	private Stream is;
	private LinkedList<OctetsStream> os;
	private TimerObserver.WatchDog timer;
	private long timestamp;

	private LinkedList<com.goldhuman.Common.Runnable> private_tasks;

	public void AddTask( com.goldhuman.Common.Runnable r )
	{
		synchronized( private_tasks )
		{
			if ( private_tasks.isEmpty() )
			{
				private_tasks.addFirst(r);
				new Thread(new com.goldhuman.Common.Runnable()
				{
					public void run()
					{
						while ( true )
						{
							com.goldhuman.Common.Runnable r = null;
							synchronized( private_tasks )
							{
								r = (com.goldhuman.Common.Runnable) private_tasks.getLast();
							}
							r.run();
							synchronized( private_tasks )
							{
								private_tasks.removeLast();
								if ( private_tasks.isEmpty() )
									return;
							}

						}
					}
				}
				).start();
			}
			else
			{
				private_tasks.addFirst(r);
			}
			private_tasks.notify();
		}
	}

	public Object clone()
	{
		try
		{
			Session s = (Session)super.clone();
			s.is = new Stream(s);
			s.os = new LinkedList<OctetsStream>();
			s.private_tasks = new LinkedList<com.goldhuman.Common.Runnable>();
			s.timer = new TimerObserver.WatchDog();
			return s;
		}
		catch (Exception e) { }
		return null;
	}

	public String Identification()
	{
		return manager.Identification();
	}

	public SocketAddress OnCheckAddress(SocketAddress sa)
	{
		return manager.OnCheckAddress(sa);
	}

	protected void OnOpen()
	{
		timer.Reset();
		manager.AddSession(this);
	}

	protected void OnClose()
	{
		manager.DelSession(this);
	}

	public void OnAbort()
	{
		manager.AbortSession(this);
	}

	public long getTimestamp() { return timestamp; }

	protected void OnRecv()
	{
		timestamp = System.currentTimeMillis();
		timer.Reset();
		Octets input = Input();
		is.insert(is.size(), input);
		input.clear();
		try
		{
			while(Protocol.Decode(is));
		}
		catch (ProtocolException e)
		{
			Close();
		}
	}

	protected void OnSend()
	{
        synchronized (os) {
            if (os.size() != 0) {
                do {
                    OctetsStream o = os.getFirst();
                    if (!Output(o))
                        break;
                    os.removeFirst();
                }
                while (os.size() != 0);
                timer.Reset();
            }
        }
    }

	public boolean Send(Protocol protocol)
	{
		synchronized(os)
		{
			OctetsStream o = new OctetsStream();
			protocol.Encode(o);
            os.addLast(o);
            if(!need_wakeup) {
                need_wakeup = true;
                PollIO.WakeUp();
            }
            return true;
		}
	}



	protected void Close() { closing = true; }


	public Session(Manager m)
	{
		manager = m;
	}
}

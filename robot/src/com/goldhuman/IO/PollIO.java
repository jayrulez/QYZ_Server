
package com.goldhuman.IO;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Iterator;

import com.goldhuman.Common.Runnable;
import com.goldhuman.Common.ThreadPool;
import com.goldhuman.IO.Protocol.Session;

class Task extends Runnable
{
	protected Task()
	{
		super(1);
	}

	public void run()
	{
		PollIO.Poll(1000);
		ThreadPool.AddTask(this);
	}
}

public abstract class PollIO
{
	private static Selector iomap = null;
	private static final Runnable task = new Task();
	private static Object regist_locker=new Object();

	static 
	{
		try
		{
			iomap = Selector.open();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected abstract int UpdateEvent();
	protected abstract boolean Close();
	protected void PollIn() { }
	protected void PollOut() { }
	protected void PollAccept() { }
	protected void PollConnect() { }

	public static PollIO register(PollIO io)
	{
		synchronized ( regist_locker )
		{
			PollIO.WakeUp();
			try { io.channel.register(iomap, 0, io); }
			catch(java.nio.channels.ClosedChannelException ex)
			{ ex.printStackTrace(); }
			return io;
		}
	}

	protected static synchronized void Poll(long timeout)
	{
		try
		{
            Session.need_wakeup = false;
			ArrayList<SelectionKey> al = new ArrayList<SelectionKey>();
			synchronized ( regist_locker )
			{
				for (Iterator<SelectionKey> it = iomap.keys().iterator(); it.hasNext(); al.add(it.next()) );
			}
			for (Iterator<SelectionKey> it = al.iterator(); it.hasNext(); )
			{
				SelectionKey sk = (SelectionKey)it.next();
				PollIO io = (PollIO)sk.attachment();
				int event = io.UpdateEvent();
				if (event == -1)
				{
					if (io.Close())
					{
						try { io.channel.close(); } catch(Exception ex) { }
						sk.cancel();
					}
				} else {
					try { sk.interestOps(event); } catch (Exception ex) { }
				}
			}
			iomap.selectedKeys().clear(); // must clear first, wakeup operator cannot update it. may be a bug.

			if (timeout < 0) iomap.select();
			else if (timeout == 0) iomap.selectNow();
			else iomap.select(timeout);

			for (Iterator<SelectionKey> it = iomap.selectedKeys().iterator(); it.hasNext(); )
			{
				SelectionKey sk = it.next();
				PollIO io = (PollIO)sk.attachment();
				if (sk.isAcceptable())  
				{
					io.PollAccept();
				}
				if (sk.isConnectable()) 
				{
					io.PollConnect();
				}
				if (sk.isReadable())    
				{
					io.PollIn();
				}
				if (sk.isWritable())    
				{
					io.PollOut();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected SelectableChannel channel;

	protected PollIO(SelectableChannel sc)
	{
		try
		{
			channel = sc;
			sc.configureBlocking(false);
//			synchronized ( regist_locker ) {
//				PollIO.WakeUp();
//				sc.register(iomap, 0, this);
//			}
/*
	This method will then synchronize on the selector's key set and therefore may block if
	invoked concurrently with another registration or selection operation involving the same selector.
*/
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static Runnable GetTask()
	{
		return task;
	}

	public static void WakeUp()
	{
		iomap.wakeup();
	}
}

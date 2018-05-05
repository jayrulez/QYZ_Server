
package com.goldhuman.IO.NetIO;

import java.nio.channels.SelectableChannel;

import com.goldhuman.IO.PollIO;

public abstract class NetIO extends PollIO
{
	protected NetSession session;

	protected NetIO(SelectableChannel sc, NetSession s)
	{
		super(sc);
		session = s;
	}

	protected boolean Close()
	{
		session.OnClose();
		return true;
	}
}

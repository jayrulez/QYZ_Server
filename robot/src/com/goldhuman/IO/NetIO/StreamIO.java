
package com.goldhuman.IO.NetIO;

import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class StreamIO extends NetIO
{
	protected void PollIn()
	{
		try
		{
			ByteBuffer buffer = session.ibuffer.getByteBuffer(session.ibuffer.size(), session.ibuffer.capacity() - session.ibuffer.size() );
			if (((SocketChannel)channel).read(buffer) > 0)
			{
				session.ibuffer.resize(buffer.position());
				return;
			}
		}
		catch (Exception e) { }
		session.obuffer.clear();
		session.closing = true;
	}

	protected void PollOut()
	{
		try
		{
			ByteBuffer buffer = session.obuffer.getByteBuffer(0, session.obuffer.size());
			if (((SocketChannel)channel).write(buffer) > 0)
			{
				session.obuffer.erase(0, buffer.position());
				return;
			}
		}
		catch (Exception e) { }
		session.obuffer.clear();
		session.closing = true;
	}

	protected int UpdateEvent()
	{
		int event = 0;
			if (session.ibuffer.size() > 0)
				session.OnRecv();
			if (!session.closing)
				session.OnSend();
			if (session.obuffer.size() > 0){
				event = SelectionKey.OP_WRITE;
			}
			if (session.closing)
			{
				try { channel.close(); }
				catch (Exception e) { }
				return -1;
			}
			if (session.ibuffer.size() < session.ibuffer.capacity())
				event |= SelectionKey.OP_READ;

		return event;
	}

	public StreamIO(SelectableChannel sc, NetSession s)
	{
		super(sc, s);
		s.OnOpen();
	}
}

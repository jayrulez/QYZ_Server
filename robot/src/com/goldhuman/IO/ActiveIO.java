
package com.goldhuman.IO;

import com.goldhuman.Common.Conf;
import com.goldhuman.IO.NetIO.NetSession;
import com.goldhuman.IO.NetIO.StreamIO;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;


public class ActiveIO extends PollIO 
{
	boolean closing;
	NetSession assoc_session;

	protected int UpdateEvent()
	{
		return closing ? -1 : SelectionKey.OP_CONNECT;
	}

	protected void PollConnect()
	{
		closing = true;
	}

	private ActiveIO (SocketChannel sc, NetSession session)
	{
		super(sc);
		(assoc_session = session).LoadConfig();
		closing = false;
		PollIO.WakeUp();
	}

	public boolean Close()
	{
		boolean b = true;
		try
		{
			SocketChannel sc = (SocketChannel)channel;
			if (sc.finishConnect())
			{
				b = false;
				register(new StreamIO(sc, (NetSession)assoc_session.clone()));
				PollIO.WakeUp();
				return false;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.err.println( "assoc_session = " + assoc_session.getPeerAddress() + " activeio = " + this );
		}
		try { assoc_session.OnAbort(); } catch (Exception e) { e.printStackTrace(); 
			System.err.println( "assoc_session = " + assoc_session.getPeerAddress() + " activeio = " + this );
			return b;
		}
		return true;
	}

	public static ActiveIO Open(NetSession assoc_session)
	{
		Conf conf = Conf.GetInstance();
		String section = assoc_session.Identification();
		String type = conf.find(section, "type");
		if (type.compareToIgnoreCase("tcp") == 0)
		{
			try
			{
				SocketChannel sc = SocketChannel.open();
				sc.configureBlocking(false);

				InetSocketAddress sa = new InetSocketAddress(InetAddress.getByName(conf.find(section, "address")),
						Integer.parseInt(conf.find(section, "port")));


				Socket sock = sc.socket();
				try { sock.setReceiveBufferSize(Integer.parseInt(conf.find(section, "so_rcvbuf"))); }
				catch (Exception e) { }
				try { sock.setSendBufferSize(Integer.parseInt(conf.find(section, "so_sndbuf"))); }
				catch (Exception e) { }
				try
				{
					if (Integer.parseInt(conf.find(section, "tcp_nodelay")) != 0)
						sock.setTcpNoDelay(true);
				}
				catch (Exception e) { }

				SocketAddress peer = assoc_session.OnCheckAddress(sa);
				sc.connect(peer);
				NetSession ns = (NetSession)assoc_session.clone();
				ns.setPeerAddress(peer);
				return (ActiveIO)register(new ActiveIO(sc, ns));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
}

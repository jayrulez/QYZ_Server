
package com.goldhuman.IO;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.goldhuman.Common.Conf;
import com.goldhuman.IO.NetIO.NetSession;
import com.goldhuman.IO.NetIO.StreamIO;


public class PassiveIO extends PollIO 
{
	boolean closing;
	NetSession assoc_session;

	protected int UpdateEvent()
	{
		return closing ? -1 : SelectionKey.OP_ACCEPT;
	}

	protected void PollAccept()
	{
		try
		{
			SocketChannel sc = ((ServerSocketChannel)channel).accept();
			if (sc != null)
			{
				NetSession ns = (NetSession)assoc_session.clone();
				ns.setPeerAddress(sc.socket().getRemoteSocketAddress());
				register(new StreamIO(sc, ns));
				sc.socket().setKeepAlive(true);
			}
		}
		catch (Exception e) { }
	}

	private PassiveIO (ServerSocketChannel ssc, NetSession session)
	{
		super(ssc);
		(assoc_session = session).LoadConfig();
		closing = false;
	}

	public boolean Close() { return closing = true; }

	public static PassiveIO Open(NetSession assoc_session)
	{
		Conf conf = Conf.GetInstance();
		String section = assoc_session.Identification();
		String type = conf.find(section, "type");

		if (type.compareToIgnoreCase("tcp") == 0)
		{
			try
			{
				ServerSocketChannel ssc = ServerSocketChannel.open();

				InetSocketAddress sa = null;
				try
				{
					sa = new InetSocketAddress(InetAddress.getByName(conf.find(section, "address")), 
						Integer.parseInt(conf.find(section, "port")));
				}
				catch (Exception e) { }

				ServerSocket ss = ssc.socket();
				try
				{
					ss.setReuseAddress(true);
					ss.setReceiveBufferSize(Integer.parseInt(conf.find(section, "so_rcvbuf")));
				}
				catch (Exception e) { }

				ss.bind(assoc_session.OnCheckAddress(sa), Integer.parseInt(conf.find(section, "listen_backlog")));
				return (PassiveIO)register(new PassiveIO(ssc, assoc_session));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
}

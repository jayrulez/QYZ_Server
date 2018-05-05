
package com.goldhuman.IO.Protocol;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Octets;
import com.goldhuman.IO.ActiveIO;
import com.goldhuman.IO.PassiveIO;

public abstract class Protocol implements Marshal, Cloneable
{
	protected int type;
	protected long time_wait = 0;


	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}



	public int getType()
	{
		return type;
	}

	protected void Encode(OctetsStream os)
	{
		os.compact_uint32(type).marshal(new OctetsStream().marshal(this));
	}

	protected static boolean Decode(Stream is) throws ProtocolException
	{
		if (is.eos()) return false;
		try
		{
            final Octets data = new Octets();
			is.Begin();
			final int type = is.uncompact_uint32();
			is.unmarshal(data);
			is.Commit();
            is.session.manager.onUnknownProtocol(is.session, type, data);
            return true;
		}
		catch (MarshalException e)
		{
			is.Rollback();

		}
		return false;
	}
	public static PassiveIO Server(Manager manager) { return PassiveIO.Open(new Session(manager)); }
	public static ActiveIO  Client(Manager manager) { return ActiveIO.Open (new Session(manager)); }

	public abstract void Process(Manager manager, Session session) throws ProtocolException;

	public static void main(String argv[]) 
	{
		com.goldhuman.Common.TimerObserver.GetInstance().StopTimer();
	}

}

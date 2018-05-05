
package com.goldhuman.IO.Protocol;

import com.goldhuman.Common.Octets;

import java.net.SocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Manager
{
	private Set<Session> set = Collections.synchronizedSet(new HashSet<Session>());

	protected void AddSession(Session session)
	{
		set.add(session);
		try
		{
			OnAddSession(session);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void DelSession(Session session)
	{
		try
		{
			OnDelSession(session);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		set.remove(session);
	}

	protected void AbortSession(Session session)
	{
		try
		{
			OnAbortSession(session);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean SetISecurity(Session session, String type, Octets key)
	{
		if (!set.contains(session)) return false;
		session.SetISecurity(type, key);	
		return true;
	}

	public boolean SetOSecurity(Session session, String type, Octets key)
	{
		if (!set.contains(session)) return false;
		session.SetOSecurity(type, key);	
		return true;
	}

	public boolean Send(Session session, Protocol protocol)
	{
		if (!set.contains(session)) return false;
		return session.Send(protocol);
	}

	public boolean Close(Session session)
	{
		if (!set.contains(session)) return false;
		session.Close();
		return true;
	}

	protected abstract void OnAddSession(Session session);
	protected abstract void OnDelSession(Session session);
	protected void OnAbortSession(Session session) { }
    protected abstract void onUnknownProtocol(Session session, int type, Octets data);

	protected abstract String Identification();



	protected SocketAddress OnCheckAddress(SocketAddress sa) { return sa; }
	protected Manager() { }
}

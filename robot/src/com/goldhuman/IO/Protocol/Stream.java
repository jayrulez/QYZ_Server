
package com.goldhuman.IO.Protocol;

import com.goldhuman.Common.Marshal.OctetsStream;

public final class Stream extends OctetsStream
{
	protected Session session;
	protected Stream(Session s) { session = s; }
	protected Stream(Session s, int size) { super(size); session = s; }
}


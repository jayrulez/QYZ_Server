
package com.goldhuman.Common.Marshal;

public interface Marshal
{
	public abstract OctetsStream marshal(OctetsStream os);
	public abstract OctetsStream unmarshal(OctetsStream os) throws MarshalException;
}

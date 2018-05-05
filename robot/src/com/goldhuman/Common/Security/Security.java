
package com.goldhuman.Common.Security;

import com.goldhuman.Common.Octets;

import java.util.HashMap;

public abstract class Security implements Cloneable
{
	private static final HashMap<String,Security> map = new HashMap<String,Security>();
	private int type;

	static 
	{
		map.put("NULL", new NullSecurity());
        map.put("MD5", new MD5Hash());
        map.put("HMAC_MD5", new HMAC_MD5Hash());
        map.put("ARC4", new ARCFourSecurity());
        map.put("RANDOM", new Random());
        map.put("DECOMPRESS_ARC4", new DecompressARCFourSecurity());
	}

	public void SetParameter(Octets o) { }
	public void GetParameter(Octets o) { }
	public Octets Update(Octets o) { return o; }
	public Octets Final (Octets o) { return o; }

	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch (Exception e) { }
		return null;
	}

	public static Security Create(String name)
	{
		Security stub = map.get(name.toUpperCase());
		return stub == null ? new NullSecurity() : (Security)stub.clone();
	}
}


package com.goldhuman.Common.Security;

import com.goldhuman.Common.Octets;

public final class HMAC_MD5Hash extends Security
{
	private Octets k_opad = new Octets(64);
	private MD5Hash md5hash = new MD5Hash();
	protected HMAC_MD5Hash() { }

	public Object clone()
	{
		try
		{
			HMAC_MD5Hash o = (HMAC_MD5Hash)super.clone();
			(o.k_opad  = (Octets)k_opad.clone()).reserve(64);
			o.md5hash = (MD5Hash)md5hash.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void SetParameter(Octets param)
	{
		Octets k_ipad = new Octets(64);
		int keylen = param.size();
		if (keylen > 64)
		{
			Octets key = MD5Hash.Digest(param);
			k_ipad.replace(key);
			k_opad.replace(key);
			keylen = key.size();
		}
		else
		{
			k_ipad.replace(param);
			k_opad.replace(param);
		}
		int i = 0;
		for (; i < keylen; i++) 
		{
			k_ipad.setByte(i, (byte)(k_ipad.getByte(i) ^ 0x36));
			k_opad.setByte(i, (byte)(k_opad.getByte(i) ^ 0x5c));
		}
		for (; i < 64; i++)
		{
			k_ipad.setByte(i, (byte)0x36);
			k_opad.setByte(i, (byte)0x5c);
		}
		k_ipad.resize(64);
		k_opad.resize(64);
		md5hash.Update(k_ipad);
	}

	public Octets Update(Octets o)
	{
		md5hash.Update(o);
		return o;
	}

	public Octets Final(Octets digest)
	{
		md5hash.Final(digest);
		MD5Hash ctx = new MD5Hash();
		ctx.Update(k_opad);
		ctx.Update(digest);
		return ctx.Final(digest);
	}
}


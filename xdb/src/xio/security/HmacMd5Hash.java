package xio.security;

import com.goldhuman.Common.Octets;

public final class HmacMd5Hash extends Security
{
	private Octets k_opad = new Octets(64);
	private MD5Hash md5hash = new MD5Hash();
	//protected HMAC_MD5Hash() { }
	public HmacMd5Hash() 
	{
		
	}

	public Object clone()
	{
		try
		{
			HmacMd5Hash o = (HmacMd5Hash) super.clone();
			o.k_opad  = (Octets) (k_opad.clone());
			o.k_opad.reserve(64);
			o.md5hash = (MD5Hash) md5hash.clone();
			return o;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public void setParameter(Octets param)
	{
		Octets ipad = new Octets(64);

		int keylen = param.size();
		if (keylen > 64)
		{
			Octets key = MD5Hash.doDigest(param);
			ipad.replace(key);
			k_opad.replace(key);
			keylen = key.size();
		}
		else
		{
			ipad.replace(param);
			k_opad.replace(param);
		}
		int i = 0;
		for (; i < keylen; i++) 
		{
			ipad.setByte(i, (byte) (ipad.getByte(i) ^ 0x36));
			k_opad.setByte(i, (byte) (k_opad.getByte(i) ^ 0x5c));
		}
		for (; i < 64; i++)
		{
			ipad.setByte(i, (byte) 0x36);
			k_opad.setByte(i, (byte) 0x5c);
		}
		ipad.resize(64);
		k_opad.resize(64);
		md5hash.doUpdate(ipad);
	}

	public Octets doUpdate(Octets o)
	{
		md5hash.doUpdate(o);
		return o;
	}

	public Octets doFinal(Octets digest)
	{
		md5hash.doFinal(digest);
		MD5Hash ctx = new MD5Hash();
		ctx.doUpdate(k_opad);
		ctx.doUpdate(digest);
		return ctx.doFinal(digest);
	}
}

package xio.security;

import java.security.MessageDigest;

import com.goldhuman.Common.Octets;

public final class MD5Hash extends Security
{
	private MessageDigest md5 = null;

	public MD5Hash()
	{
		try
		{
			md5 = MessageDigest.getInstance("MD5");
		}
		catch (Exception e) 
		{ 
			e.printStackTrace();
		}
	}

	public Object clone()
	{
		try
		{
			MD5Hash o = (MD5Hash) super.clone();
			o.md5 = (MessageDigest) md5.clone();
			return o;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public Octets doUpdate(Octets o)
	{
		if (md5 != null) 
		{
			md5.update(o.array(), 0, o.size());
		}
		return o;
	}

	public Octets doFinal(Octets digest)
	{
		if (md5 != null) 
		{
			digest.replace(md5.digest());
		}
		return digest;
	}

	public static Octets doDigest(Octets o)
	{
		try
		{
			return new Octets(MessageDigest.getInstance("MD5").digest(o.getBytes()));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new Octets();
	}
}

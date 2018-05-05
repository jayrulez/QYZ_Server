package xio.security;

import com.goldhuman.Common.Octets;

public class Compress extends Security 
{
	private long handle;
	
	public Compress()
	{
		handle = open_compress();
	}
	
	public Octets doUpdate(Octets o) 
	{ 
		byte[] key = compress_update(handle, o.getBytes(), o.size());		
		o.replace(key);
		
		return o; 
	}
	
	public Octets doFinal(Octets o) 
	{
		byte[] key = compress_final(handle, o.getBytes(), o.size());		
		o.replace(key);		
		return o; 
	}
	
	@Override
	protected void finalize() throws Throwable 
	{
		super.finalize();
		close_compress(handle);
	}
	
	public Object clone()
	{
		try
		{	
			return new Compress();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
}

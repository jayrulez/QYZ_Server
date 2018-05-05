package xio.security;

import com.goldhuman.Common.Octets;


public class Decompress extends Security 
{
	
	private long handle;
	
	public Decompress()
	{
		handle = open_decompress();
	}
	
	public Octets doUpdate(Octets o) 
	{ 
		byte[] key = decompress_update(handle, o.getBytes(), o.size());		
		o.replace(key);
		return o; 
	}
		
	@Override
	protected void finalize() throws Throwable 
	{		
		super.finalize();
		close_decompress(handle);
		
	}
	
	public Object clone()
	{
		try
		{		
			return new Decompress();
		}
		catch (Exception e) 
		{ 
			e.printStackTrace();
		}
		return null;
	}
}

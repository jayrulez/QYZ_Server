package xio.security;

import com.goldhuman.Common.Octets;

public class CompressARCFourSecurity extends Security 
{
	
	private ARCFourSecurity arc4;
	private Compress compress;
	
	public CompressARCFourSecurity()
	{
		arc4 = new ARCFourSecurity(); 
			//(ARCFourSecurity) Security.create(arc4.getClass().getName());
		compress = new Compress();
			//(Compress) Security.create(compress.getClass().getName());
	}
	
	public void setParameter(Octets o) 
	{ 
		arc4.setParameter(o);
	};
	
	public Octets doUpdate(Octets o) 
	{ 
		arc4.doUpdate(compress.doFinal(o));
		return o; 
	}
	
	public Octets doFinal(Octets o) 
	{
		return o; 
	}
	
	public Object clone()
	{
		try
		{
			CompressARCFourSecurity o = (CompressARCFourSecurity) super.clone();
			o.arc4  = (ARCFourSecurity) arc4.clone();
			o.compress = (Compress) compress.clone();
			return o;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}

package xio.security;

import com.goldhuman.Common.Octets;

public class DecompressARCFourSecurity extends Security 
{
	
	private ARCFourSecurity arc4;
	private Decompress decompress;
	
	public DecompressARCFourSecurity()
	{
		arc4 = new ARCFourSecurity();
		decompress = new Decompress(); 
	}
	
	public void setParameter(Octets o) 
	{ 
		arc4.setParameter(o);
	};
	
	public Octets doUpdate(Octets o) 
	{ 
		decompress.doUpdate(arc4.doUpdate(o));
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
			DecompressARCFourSecurity o = (DecompressARCFourSecurity) super.clone();
			o.arc4  = (ARCFourSecurity) arc4.clone();
			o.decompress = (Decompress) decompress.clone();
			return o;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}

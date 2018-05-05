package com.goldhuman.Common.Security;

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

    @Override
	public void SetParameter(Octets o)
	{ 
		arc4.SetParameter(o);
	};

    @Override
	public Octets Update(Octets o)
	{ 
		decompress.doUpdate(arc4.Update(o));
		return o; 
	}

    @Override
	public Octets Final(Octets o)
	{
		return o; 
	}
	
	public Object clone()
	{
		try
		{
			DecompressARCFourSecurity o = (DecompressARCFourSecurity) super.clone();
			o.arc4  = (ARCFourSecurity) arc4.clone();
			o.decompress = decompress.clone();
			return o;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}

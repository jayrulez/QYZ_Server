package xio.security;

import com.goldhuman.Common.Octets;

public class JDecompressARCFourSecurity extends Security 
{
	private ARCFourSecurity arc4 = new ARCFourSecurity();
	private StreamMPPCDecopress decom = new StreamMPPCDecopress();
	
	public JDecompressARCFourSecurity() 
	{
		
	}
	
	public void setParameter(Octets o) 
	{
		arc4.setParameter(o);
	}
	
	public Octets doUpdate(Octets o) 
	{
		return decom.Update(arc4.doUpdate(o));
	}
}

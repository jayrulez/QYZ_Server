package xio.security;

import java.nio.ByteBuffer;

import xio.Filter;
import xio.Xio;
import com.goldhuman.Common.Octets;



/*	
 * Xio Security支持类
 */
public class SecurityFilter extends Filter 
{
	
	/**
	 * 设置output的加密类型
	 * @param con : xio连接handle
	 * @param security : 加密类型
	 * @param key : 加密的key值，可以为null
	 */
	public static void setXioOutputSecurity(xio.Xio con, Security security, Octets key)
	{
		final String outputFilterName = "OutputSecurity";
		
		if (con.getOutput().get(outputFilterName) != null)
		{
			con.getOutput().remove(outputFilterName);
		}
		
		con.getOutput().addLast(
				new SecurityFilter(outputFilterName, security, key));
		
	}
	
	/**
	 * 设置input的加密类型
	 * @param con : xio连接handle
	 * @param security : 加密类型
	 * @param key : 加密的key值，可以为null
	 */
	public static void setXioInputSecurity(xio.Xio con, Security security, Octets key)
	{
		final String inputFilterName = "InputSecurity";
		
		if (con.getInput().get(inputFilterName) != null)
		{
			con.getInput().remove(inputFilterName);
		}
		
		con.getInput().addFirst(
				new SecurityFilter(inputFilterName, security, key));
	}
	
	
	private Security _sec;
	
	public SecurityFilter(String name, Security security, Octets key) 
	{
		super(name);			
		_sec = security;
		_sec.setParameter(key);
	}

	synchronized public void setSecurity(Security security, Octets key)
	{
		_sec = security;
		_sec.setParameter(key);			
	}
//	IO {
//		buffer;
//		dofillter() {
//			socket.read(buffer);
//			buffer.flip();
//			decompress.dofilter(buffer);
//			buffer.compact();
//		}
//	}
//	decompress {
//		buffer; // alloc
//		dofillter(b) {
//			buffer.put(decom(b));
//			buffer.flip();
//			decoder.dofilter(buffer);
//			buffer.compact();
//		}
//		decom(b) {
//			private_buf = b.read 800;
//			...
//			return outbuf;
//		}
//	}
//	decoder {
//		buffer;
//	}
	
	protected void doFilter(Iterator it, ByteBuffer b, Xio x)
	{
		// read b
		Octets data = new Octets(b.array(), b.position(), b.remaining());	
		_sec.doUpdate(data);
		
//		b.mark().limit(b.capacity());
//		b.put(data.array(), 0, data.size());
//		b.limit(b.position()).reset();

		ByteBuffer next = ByteBuffer.wrap(data.array(), 0, data.size());
		it.doFilterNextOf(this, next, x);
		
		b.position(b.limit());	
		
	}

	

	
	
}

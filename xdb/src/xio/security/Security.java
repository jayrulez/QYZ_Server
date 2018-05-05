package xio.security;

import com.goldhuman.Common.Octets;

public abstract class Security implements Cloneable 
{
	
	public void setParameter(Octets o) 
	{ 
		
	};
	public Octets doUpdate(Octets o) 
	{ 
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
			return super.clone();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 以下为iolib的jni版本的Security接口，开放只是用于测试用。需要有相应的XioSecurity.dll支持，暂不开放应用
	 *  其中compress接口，是用于Compress类封装
		//enum { RANDOM = 0, NULLSECURITY = 1, ARCFOURSECURITY = 2, MD5HASH = 3, HMAC_MD5HASH = 4, 
		//	COMPRESSARCFOURSECURITY = 5, DECOMPRESSARCFOURSECURITY = 6, SHA1HASH = 7 };
	 */
	protected native long open_compress();
	protected native byte[] compress_update(long handle, byte [] key, int keysize);
	protected native byte[] compress_final(long handle, byte [] key, int keysize);
	protected native void close_compress(long handle);
	
	protected native long open_decompress();
	protected native byte[] decompress_update(long handle, byte [] key, int keysize);
	protected native void close_decompress(long handle);
	
	final static public int RAMDOM = 0;
	final static public int NULLSECURITY = 1;
	final static public int ARCFOURSECURITY = 2;
	final static public int MD5HASH = 3;
	final static public int HMAC_MD5HASH = 4;
	final static public int COMPRESSARCFOURSECURITY = 5;
	final static public int DECOMPRESSFOURSECURITY = 6;
	final static public int SHA1HASH = 7;
	
	static public native long open_security(int type);
	static public native void security_set_parameter( long handle, byte [] key, int keysize );
	static public native byte[] security_update( long handle, byte [] data, int datasize );
	static public native byte[] security_final( long handle, byte [] data, int datasize );
	static public native void close_security( long handle );
	
}

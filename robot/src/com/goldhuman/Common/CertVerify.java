package com.goldhuman.Common;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CertVerify
{
	private Provider provider;
	private KeyStore keystore;
	private Certificate cert;
	private Key	key;
	private KeyPair keypair;
	private boolean initialized = false;

	private static CertVerify instance = new CertVerify();
	public CertVerify() { }
	public static CertVerify getInstance() { return instance; }
	
	private static final char[] DIGITS = {
		'0', '1', '2', '3', '4', '5', '6', '7',
		'8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	};

	private static int toDigit(char ch, int index)
	{
		int digit = Character.digit(ch, 16);
		if (digit == -1) {
			return 0;
		}
		return digit;
	}

	public static byte[] decodeHex(char[] data)
	{
		int len = data.length;

		if ((len & 0x01) != 0) {
			return null;
		}

		byte[] out = new byte[len >> 1];

		for (int i = 0, j = 0; j < len; i++) {
			int f = toDigit(data[j], j) << 4;
			j++;
			f = f | toDigit(data[j], j);
			j++;
			out[i] = (byte) (f & 0xFF);
		}

		return out;
	}

	public static char[] encodeHex(byte[] data)
	{
		int l = data.length;

		char[] out = new char[l << 1];

		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4 ];
			out[j++] = DIGITS[ 0x0F & data[i] ];
		}

		return out;
	}

	public int initJKS( String filejks, String pass )
	{
		if(initialized) {
			return 0;
		}
		
		Enumeration<String> e_aliase; 
		try
		{
			keystore = KeyStore.getInstance("jks");
			keystore.load( new java.io.FileInputStream(filejks), pass.toCharArray() );

			e_aliase = keystore.aliases();
			if (e_aliase.hasMoreElements()){
				String alias = e_aliase.nextElement();
				key = keystore.getKey(alias, pass.toCharArray());

				if(key instanceof PrivateKey) {
					cert=keystore.getCertificate(alias);
					PublicKey publicKey=cert.getPublicKey();
					keypair = new KeyPair(publicKey,(PrivateKey)key);
					return 0;
				}
			}else{
				return -1;
			}

			initialized = true;
			return 1;
		}catch(KeyStoreException e){
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
			//no pw
			System.out.println("ERROR:incorrect passphrase for this key!");
			return -2; 
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int initPKCS11(String pkcs11_conf, String pin)
	{
		if(initialized){
			return 0;
		}
		
	//	byte[] keybuf;
		Enumeration<String> e_aliase; 
		try
		{
			Security.addProvider( provider = new sun.security.pkcs11.SunPKCS11(pkcs11_conf) );
			keystore = KeyStore.getInstance("PKCS11", provider);
			keystore.load(null, pin.toCharArray());

			e_aliase = keystore.aliases();
			if (e_aliase.hasMoreElements()){
				String alias = e_aliase.nextElement();
				key = keystore.getKey(alias, null);

				if(key instanceof PrivateKey) {
					cert=keystore.getCertificate(alias);
					PublicKey publicKey=cert.getPublicKey();
					keypair = new KeyPair(publicKey,(PrivateKey)key);
					return 0;
				}
			}else{
				return -1;
			}

			initialized = true;
			return 1;
		}catch(KeyStoreException e){
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
			//no pw
			System.out.println("ERROR:incorrect passphrase for this key!");
			return -2; 
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public byte[] getDER()
	{
		try {
			return cert.getEncoded();
		} catch (Exception e) { e.printStackTrace(System.out); }
		return new byte[0];
	}

	public void dump()
	{
		try {
			System.out.print( "Certificate Type:" );
			System.out.println( cert.getType() );
			System.out.print( "Certificate Encode:" );
			System.out.println( new String(encodeHex(cert.getEncoded())) );
			System.out.print( "PublicKey Type:" );
			System.out.println( cert.getPublicKey().getFormat() );
			System.out.print( "PublicKey Encode:" );
			System.out.println( new String(encodeHex(cert.getPublicKey().getEncoded())) );
			System.out.print( "toString:" );
			System.out.println( cert.toString() );
		} catch (Exception e) { System.out.println("ERROR:CertVerify dump exception!"); e.printStackTrace(System.out); }
	}

	public void test( byte[] text)
	{
		try {
			byte [] buf = CertVerify.getInstance().EncryptWithPublic(text);
			System.out.print("encrypt with public:");
			System.out.println(new String(encodeHex(buf)));
			System.out.print("decrypt with private:");
			System.out.println(new String(encodeHex(CertVerify.getInstance().DecryptWithPrivate(buf))));

			buf = CertVerify.getInstance().EncryptWithPrivate(text);
			System.out.print("encrypt with private:");
			System.out.println(new String(encodeHex(buf)));
			System.out.print("decrypt with public:");
			System.out.println(new String(encodeHex(CertVerify.getInstance().DecryptWithPublic(buf))));

		} catch (Exception e) { System.out.println("ERROR:CertVerify test exception!"); e.printStackTrace(System.out); }
	}

	private byte[] Encrypt( byte[] buf, Key key )
	{
		if(buf == null){
			return null;
		}
		byte[] dst = null;
		Cipher c = null;
		try {
			if( null != provider )
				c = Cipher.getInstance( "RSA/ECB/PKCS1Padding", provider);
			else
				c = Cipher.getInstance( "RSA/ECB/PKCS1Padding" );
			c.init( Cipher.ENCRYPT_MODE, key );
			//dst = c.doFinal(BinaryCodec.toAsciiString(buf).getBytes());
			dst = c.doFinal(buf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return dst;
	}

	private byte[] Decrypt( byte[] buf, Key key ) 
	{
		if(buf == null){
			return null;
		}

		byte[] dst = null;
		Cipher c = null;;
		try {
			if( null != provider )
				c = Cipher.getInstance( "RSA/ECB/PKCS1Padding", provider);
			else
				c = Cipher.getInstance( "RSA/ECB/PKCS1Padding" );
			c.init(Cipher.DECRYPT_MODE, key);
			dst = c.doFinal(buf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
			
		//return BinaryCodec.fromAscii(dst);
		return dst;
	}

	public byte[] EncryptWithPublic( byte[] buf,Key key ) 
	{
		return Encrypt( buf, key );
	}
	
	public byte[] EncryptWithPublic( byte[] buf ) 
	{
		return Encrypt( buf, keypair.getPublic() );
	}

	public byte[] DecryptWithPublic( byte[] buf ) 
	{
		return Decrypt( buf, keypair.getPublic() );
	}

	public byte[] EncryptWithPrivate( byte[] buf ) 
	{
		return Encrypt( buf, keypair.getPrivate() );
	}

	public byte[] DecryptWithPrivate( byte[] buf ) 
	{
		return Decrypt( buf, keypair.getPrivate() );
	}

	public static void main( String args[] ) throws Exception
	{
		if( 3 != args.length )
		{
			System.out.println( "usage:     java util.CertVerify JKS jks_file pin" );
			System.out.println( "       or: java util.CertVerify PKCS11 pkcs11_cfg_file pin" );
			return;
		}

		if( "JKS".equals(args[0]) )
			CertVerify.getInstance().initJKS( args[1], args[2] );
		else if ( "PKCS11".equals(args[1]) )
			CertVerify.getInstance().initPKCS11( args[1], args[2] );
		else
		{
			System.out.println( "usage:     java util.CertVerify JKS jks_file pin" );
			System.out.println( "       or: java util.CertVerify PKCS11 pkcs11_cfg_file pin" );
			return;
		}
		
		CertVerify.getInstance().dump();
	}
}


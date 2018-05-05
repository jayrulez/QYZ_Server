package xauany.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
	private static final Charset UTF_8 = Charset.forName("UTF-8");
	
	private Utils(){	
	}
	
	private static final char[] HEX_CHARS = 
		{'0', '1', '2', '3', 
		'4', '5', '6', '7', 
		'8', '9', 'a', 'b', 
		'c', 'd', 'e', 'f'};
	
	public static String digestMd5(String str) throws NoSuchAlgorithmException{
		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		md5Digest.update(str.getBytes(UTF_8));
		byte[] bytes = md5Digest.digest();
		
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for(byte b : bytes){
			sb.append(HEX_CHARS[(b >>> 4) & 0x0F]);
			sb.append(HEX_CHARS[b & 0x0F]);
		}
		
		return sb.toString();
	}
	
	
	public static int convertIpToInt(String ip) throws UnknownHostException{
		byte[] addr = InetAddress.getByName(ip).getAddress();
		
		return makeInt(addr[0], addr[1], addr[2], addr[3]);
	}
	
	static private int makeInt(byte b3, byte b2, byte b1, byte b0) {
        return (((b3       ) << 24) |
                ((b2 & 0xff) << 16) |
                ((b1 & 0xff) <<  8) |
                ((b0 & 0xff)      ));
    }
	
	public static String convertIntToIp(int address) throws UnknownHostException{
		byte[] addr = new byte[4];
        addr[0] = (byte) ((address >>> 24) & 0xFF);
        addr[1] = (byte) ((address >>> 16) & 0xFF);
        addr[2] = (byte) ((address >>> 8) & 0xFF);
        addr[3] = (byte) (address & 0xFF);
        
        return InetAddress.getByAddress(addr).getHostAddress();
	}
	
	public static void main(String[] args) throws UnknownHostException{
		int address = convertIpToInt("10.241.64.193");
		System.out.println(address);
		System.out.println(convertIntToIp(address));
	}
}

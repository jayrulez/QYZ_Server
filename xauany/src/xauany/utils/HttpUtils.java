package xauany.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import xauany.Config;

public class HttpUtils {
	private static final String USER_AGENT = "wanmei/auany";
	/** url分隔*/
	public static final String URL_SEG_STRING="/";
	/** url参数分隔 */
	public static final String PARAM_SEG_STRING="&";

	public static String getRequest( String urlstring, HttpConfig config) throws IOException {
		return getRequest(urlstring, config, 0);
	}

	public static String getRequest( String urlstring, HttpConfig config ,int maxlinenum) throws IOException {
		if( xdb.Trace.isDebugEnabled())
			xdb.Trace.debug( "HttpIO.getRequest url = " + urlstring);

		final URL url = new URL( urlstring);
		final HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setAllowUserInteraction(false);
		conn.setUseCaches(false);
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setConnectTimeout( config.connecttimeout);
		conn.setReadTimeout( config.readtimeout);
		int linenum = 0;
		final InputStream is = conn.getInputStream();
		try {
			InputStreamReader in = new InputStreamReader(is, Config.UTF8);
			BufferedReader buffer = new BufferedReader(in, 4096);
			StringBuilder resultDataBuilder = new StringBuilder();
            while( true) {
				final String line = buffer.readLine();
				if( null == line)
					break;
				resultDataBuilder.append( line);
				linenum++;
				if(maxlinenum > 0 && linenum >= maxlinenum)
					break;
			}
			if( xdb.Trace.isDebugEnabled())
				xdb.Trace.debug( "HttpIO.getRequest resultDataBuilder = " + resultDataBuilder);
			return resultDataBuilder.toString();
		} finally {
			is.close();
		}
	}

	//==============test start==========================
	public static String getRequest1( String urlstring, HttpConfig config) throws Exception {
		return getRequest1(urlstring, config, 0);
	}
	
	public static String getRequest1( String urlstring, HttpConfig config ,int maxlinenum) throws Exception {
		if( xdb.Trace.isDebugEnabled())
			xdb.Trace.debug( "HttpIO.getRequest url = " + urlstring);

		trustAllHttpsCertificates();
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { 
			public boolean verify(String urlHostName, SSLSession session) {   
				System.out.println("Warning: URL Host: " + urlHostName + " vs. "  
						+ session.getPeerHost());   
				return true;   
			}   
		}); 
		
		String ctype = "application/json;charset=utf-8";   
		final URL url = new URL( urlstring);
		final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setDoInput(true);
		conn.setDoOutput(true);
//		conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");   
		conn.setRequestProperty("User-Agent", USER_AGENT);   
		conn.setRequestProperty("Content-Type", ctype); 
		conn.setConnectTimeout( config.connecttimeout);
		conn.setReadTimeout( config.readtimeout);
		
		int linenum = 0;
		final InputStream is = conn.getInputStream();
		try {
			InputStreamReader in = new InputStreamReader(is, Config.UTF8);
			BufferedReader buffer = new BufferedReader(in, 4096);
			StringBuilder resultDataBuilder = new StringBuilder();
            while( true) {
				final String line = buffer.readLine();
				if( null == line)
					break;
				resultDataBuilder.append( line);
				linenum++;
				if(maxlinenum > 0 && linenum >= maxlinenum)
					break;
			}
			if( xdb.Trace.isDebugEnabled())
				xdb.Trace.debug( "HttpIO.getRequest resultDataBuilder = " + resultDataBuilder);
			return resultDataBuilder.toString();
		} finally {
			is.close();
		}
	}
	
	private static void trustAllHttpsCertificates() throws Exception {   
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];   
		javax.net.ssl.TrustManager tm = new miTM();   
		trustAllCerts[0] = tm;   
		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext   .getInstance("SSL");   
		sc.init(null, trustAllCerts, null);   
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc .getSocketFactory());   
	} 

	static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {   
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {   
			return null;   
		}   

		public boolean isServerTrusted(   
				java.security.cert.X509Certificate[] certs) {   
			return true;   
		}   

		public boolean isClientTrusted(   
				java.security.cert.X509Certificate[] certs) {   
			return true;   
		}   

		public void checkServerTrusted(   
				java.security.cert.X509Certificate[] certs, String authType)   
		throws java.security.cert.CertificateException {   
			return;   
		}   

		public void checkClientTrusted(   
				java.security.cert.X509Certificate[] certs, String authType)   
		throws java.security.cert.CertificateException {   
			return;   
		}   
	}
	
	public static String getRequestWithCookie( String urlstring, HttpConfig config , String cookie) throws IOException {
		int maxlinenum = 0;
		if( xdb.Trace.isDebugEnabled())
			xdb.Trace.debug( "HttpIO.getRequest url = " + urlstring);

		final URL url = new URL( urlstring);
		final HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setAllowUserInteraction(false);
		conn.setUseCaches(false);
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setConnectTimeout( config.connecttimeout);
		conn.setReadTimeout( config.readtimeout);
		conn.setRequestProperty("Cookie", cookie);
		int linenum = 0;
		final InputStream is = conn.getInputStream();
		try {
			InputStreamReader in = new InputStreamReader(is, Config.UTF8);
			BufferedReader buffer = new BufferedReader(in, 4096);
			StringBuilder resultDataBuilder = new StringBuilder();
            while( true) {
				final String line = buffer.readLine();
				if( null == line)
					break;
				resultDataBuilder.append( line);
				linenum++;
				if(maxlinenum > 0 && linenum >= maxlinenum)
					break;
			}
			if( xdb.Trace.isDebugEnabled())
				xdb.Trace.debug( "HttpIO.getRequest resultDataBuilder = " + resultDataBuilder);
			return resultDataBuilder.toString();
		} finally {
			is.close();
		}
	}

	public static Map<String,String> convertQueryString2Map(String query) throws UnsupportedEncodingException {
		final Map<String,String> result = new HashMap<String,String>();
		final String[] kvs = query.split( "&");
		for( final String kv : kvs) {
			final int index = kv.indexOf( '=');
			if( -1 == index)
				continue;
			final String key = kv.substring( 0, index);
			final String value = URLDecoder.decode(kv.substring( index + 1), Config.OCTETS_CHARSET_UTF8);
			result.put( key, value);
		}
		return result;
	}
}

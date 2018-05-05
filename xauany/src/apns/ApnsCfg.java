package apns;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApnsCfg {
	private static String keystore;
	private static String password;
	private static boolean production;
	private static int sendThreadNum;
	private static int waitQueueCapacity;
	private static int threadPoolSize;
	private static int sendAllPeriod;
	private static int sendAllPerNum;
	
	public static void init(String apnsCfgFile) throws IOException{
		InputStream in = null;
		try {
			in = new FileInputStream(new File("apns.properties"));
			Properties props = new Properties();
			props.load(in);
			keystore = props.getProperty("keystore");
			password = props.getProperty("password");
			production = "true".equalsIgnoreCase(props.getProperty("production"));
			
			sendThreadNum = Integer.decode(props.getProperty("sendThreadNum"));
			waitQueueCapacity = Integer.decode(props.getProperty("waitQueueCapacity"));
			threadPoolSize = Integer.decode(props.getProperty("threadPoolSize"));
			sendAllPeriod = Integer.decode(props.getProperty("sendAllPeriod"));
			sendAllPerNum = Integer.decode(props.getProperty("sendAllPerNum"));
		} 
		finally {
			if(null != in) {
				try {
					in.close();
				} 
				catch (IOException e) {
					xdb.Trace.error(e);
				}
			}
		}
	}

	public static String getKeystore() {
		return keystore;
	}

	public static String getPassword() {
		return password;
	}

	public static boolean isProduction() {
		return production;
	}

	public static int getSendThreadNum() {
		return sendThreadNum;
	}

	public static int getWaitQueueCapacity() {
		return waitQueueCapacity;
	}

	public static int getThreadPoolSize() {
		return threadPoolSize;
	}

	public static int getSendAllPeriod() {
		return sendAllPeriod;
	}

	public static int getSendAllPerNum() {
		return sendAllPerNum;
	}
	
	
}

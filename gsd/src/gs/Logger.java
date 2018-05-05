package gs;

import xdb.util.FormattingTuple;
import xdb.util.MessageFormatter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class Logger{
    //    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //    private static final SimpleDateFormat fileDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /** 多线程环境用ThreadLocal包装一下 */
    private static final ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> fileDateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    static Logger eventlog;
    private static int serverid;
    
    private final Lock lock = new ReentrantLock();
    private PrintStream ps;
    
    static void init(int serverid){
    	Logger.serverid = serverid;
    	
    	try {
            eventlog = new Logger();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public static int getServerid(){
    	return serverid;
    }
    
    private Logger() throws FileNotFoundException, UnsupportedEncodingException {
    	ps = newPrintStream();
        
        Calendar todayBegin = Calendar.getInstance();
        todayBegin.set(Calendar.HOUR_OF_DAY, 0);
        todayBegin.set(Calendar.MINUTE, 0);
        todayBegin.set(Calendar.SECOND, 1);
        
        long delay = todayBegin.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        if(delay <= 0){
        	delay += TimeUnit.DAYS.toMillis(1);
        }
        xdb.Xdb.executor().scheduleAtFixedRate(() -> rotate(), delay, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
    }
    
    private PrintStream newPrintStream() throws UnsupportedEncodingException, FileNotFoundException{
    	String filename = "/export/logs/" + serverid + "/" + fileDateFormat.get().format(Calendar.getInstance().getTime()) + "/format.log";
        File file = new File(filename);
        if(!file.getParentFile().exists()){
        	file.getParentFile().mkdirs();
        }
        return new PrintStream(new FileOutputStream(file, true), false, "UTF-8");// open with append
    }
    
    public void log(String format, Object[] argArray) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
        String content ="{\"logtime\":\"" + dateFormat.get().format(Calendar.getInstance().getTime()) + "\"," + ft.getMessage() + "}";
        lock.lock();
        try {
            ps.println(content);
        }finally {
            lock.unlock();
        }
    }

    public void close() {
        lock.lock();
        try {
            ps.close();
        }finally {
            lock.unlock();
        }
    }

    public void rotate() {
        try {
        	if(ps != null){
        		ps.close();
        	}
        	ps = newPrintStream();
        } catch (Exception ex) {
            ex.printStackTrace();
            ps = null;
        }
    }

    public static void trace(String format, Object... argArray)  {
        eventlog.log(format, argArray);
    }
}

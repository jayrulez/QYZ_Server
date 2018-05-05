package xauany;

import xdb.util.FormattingTuple;
import xdb.util.MessageFormatter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public final class Logger {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final PrintStream ps;
    private final Lock lock = new ReentrantLock();

    public Logger(String fn) throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File(fn);
        ps = new PrintStream(new FileOutputStream(file, true), false, "UTF-8");// open with append
    }

    public void log(String format, Object[] argArray) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
        String content = dateFormat.format(Calendar.getInstance().getTime()) + " " + ft.getMessage();
        lock.lock();
        try {
            if (null != ps) {
                ps.println(content);
            }
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

    static Logger eventlog;
    static {
        try {
            eventlog = new Logger("xauany.event");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void trace(String format, Object... argArray)  {
        eventlog.log(format, argArray);
    }
}

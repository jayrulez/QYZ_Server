package xdb;

import xdb.util.FormattingTuple;
import xdb.util.MessageFormatter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p> <b>日志性能</b> 当日志级别低于配置时，消息不会被显示，但消息参数格式化仍然进行，有时候这会造成很大的开销。如：
 * <p> <code>Trace.debug("The large object is " + largeObject.toString());</code>
 * <p> 这里的largeObject.toString可能非常耗时，为了避免这种开销，按下面的方式来记录日志。
 * <p> <code>if (Trace.isDebugEnabled()) Trace.debug("The large object is", largeObject); </code>
 * 
 * <p> 开始的时候，想用这个类包装java.util.logging或log4j。一直都没时间去研究包装方式，最后写成这样，也懒得弄了。
 */
public enum Trace {
	DEBUG(0, "DEBUG"),
	INFO(1, "INFO "),
	WARN(2, "WARN "),
	ERROR(3, "ERROR"), 
	FATAL(4, "FETAL");

	private volatile static Trace logger = INFO;

	private volatile static boolean isPrintToConsole = false;

	public static final boolean isDebugEnabled() { return logger.level <= DEBUG.level; }
	public static final boolean isInfoEnabled()  { return logger.level <= INFO.level; }
	public static final boolean isWarnEnabled()  { return logger.level <= WARN.level; }
	public static final boolean isErrorEnabled() { return logger.level <= ERROR.level; }
	public static final boolean isFatalEnabled() { return logger.level <= FATAL.level; }

	public final static void debug(Object message) { logger.trace(DEBUG, message, null); }
	public final static void info(Object message)  { logger.trace(INFO,  message, null); }
	public final static void warn(Object message)  { logger.trace(WARN,  message, null); }
	public final static void error(Object message) { logger.trace(ERROR, message, null); }
	public final static void fatal(Object message) { logger.trace(FATAL, message, null); }

	public final static void debug(Object message, Throwable e) { logger.trace(DEBUG, message, e); }
	public final static void info(Object message, Throwable e)  { logger.trace(INFO, message, e); }
	public final static void warn(Object message, Throwable e)  { logger.trace(WARN, message, e); }
	public final static void error(Object message, Throwable e) { logger.trace(ERROR, message, e); }
	public final static void fatal(Object message, Throwable e) { logger.trace(FATAL, message, e); }
	
	public final static void debug(String format, Object... argArray) { logger.trace(DEBUG, format, argArray); }
	public final static void info(String format, Object... argArray)  { logger.trace(INFO, format, argArray); }
	public final static void warn(String format, Object... argArray)  { logger.trace(WARN, format, argArray); }
	public final static void error(String format, Object... argArray) { logger.trace(ERROR, format, argArray); }
	public final static void fatal(String format, Object... argArray) { logger.trace(FATAL, format, argArray); }
	
	public final static void log(Trace level, Object message)              { logger.trace(level, message, null ); }
	public final static void log(Trace level, Object message, Throwable e) { logger.trace(level, message, e ); }
	public final static void log(Trace level, String message, Object... o) { logger.trace(level, message, o ); }

	public final static void set(Trace trace) {
		logger = trace;
	}

	public final static Trace get() {
		return logger;
	}

    private volatile static TraceHandler extraHander = null;

    public final static void setExtraHandler(TraceHandler handler){
        extraHander = handler;
    }

	///////////////////////////////////////////////////////////////////////////
	public final static class Log extends java.util.TimerTask {
		private final Lock lock = new ReentrantLock();
		private final File home;
		private boolean console;
		private PrintStream ps;

		public Log(File home, boolean console, int hourOfDay, int minute) {
			this.home = home;
			this.console = console;
			try {
				if (null != home) {
					File file = new File(home, "trace.log");
					// open with append
					ps = new PrintStream(new FileOutputStream(file, true), false, "GBK");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				ps = null;
			}

			if (hourOfDay < 0 || minute < 0 || null == ps)
				return; // no rotate

			java.util.Calendar firstTime = java.util.Calendar.getInstance();
			firstTime.set(java.util.Calendar.HOUR_OF_DAY, hourOfDay);
			firstTime.set(java.util.Calendar.MINUTE, minute);
			firstTime.set(java.util.Calendar.SECOND, 0);
			firstTime.set(java.util.Calendar.MILLISECOND, 0);
			if (firstTime.before(Calendar.getInstance())) // 如果第一次的时间比当前时间早，推到明天。
				firstTime.add(java.util.Calendar.DAY_OF_MONTH, 1); // tomorrow!

			//System.out.println("xdb.Trace.rotate schedule. firstTime=" + dateFormat.format(firstTime.getTime()));
			//new Exception().printStackTrace(System.out);
			Xdb.timer().schedule(this, firstTime.getTime(), 24 * 3600 * 1000); // every day
			//System.out.println("TimerTask.executionTime=" + dateFormat.format(scheduledExecutionTime()));
		}

		public boolean rotate() {
			//System.out.println("xdb.Trace.rotate :");
			//new Exception().printStackTrace(System.out);

			lock.lock();
			try {
				if (null == ps)
					return false;

				File dest = new File(this.home, "trace."
						+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS")
						.format(Calendar.getInstance().getTime()) + ".log");
				if (dest.exists())
					return false;

				File file = new File(this.home, "trace.log");
				ps.close(); // close before rename
				boolean renameOk = file.renameTo(dest);
				// open with truncate if renameOk
				ps = new PrintStream(new FileOutputStream(file, !renameOk), false, "GBK");
				return renameOk;
			} catch (Exception ex) {
				ex.printStackTrace();
				ps = null;
			} finally {
				lock.unlock();
			}
			return false;
		}

		public void println(String str, Throwable e) {
			lock.lock();
			try {
				if (null != ps) {
					ps.println(str);
					Throwable tmpe = e;
					while (null != tmpe) {
						tmpe.printStackTrace(ps);
						tmpe = tmpe.getCause();
					}
				}
				if (this.console) {
					System.out.println(str);
					Throwable tmpe = e;
					while (null != tmpe) {
						tmpe.printStackTrace(System.out);
						tmpe = tmpe.getCause();
					}
				}
			} finally {
				lock.unlock();
			}
		}

		public void close() {
			lock.lock();
			try {
				if (null != ps) {
					ps.close();
					ps = null;
				}
				this.console = false;
				this.cancel();
			} finally {
				lock.unlock();
			}
		}

		@Override
		public void run() {
			this.rotate(); // skip error
		}
	}

	/**
	 * 打开日志，兼容旧接口。
	 * @see xdb.Trace#openNew()
	 * @param conf
	 */
	public static void open(XdbConf conf) {
		openNew(conf.getDbHome(), conf.getTraceTo(),
			conf.getTraceRotateHourOfDay(), conf.getTraceRotateMinute());
	}

	/**
	 * 在当前目录下生成日志文件，同时打印到标准输出流中。
	 */
	public static Log openNew() {
		return openNew(new File("."), ":file:out", -1, -1);
	}

	/**
	 * 如果日志实例存在则返回否则调用openNew创建并返回一个实例。
	 */
	public static Log openIf() {
		loglock.lock();
		try {
			if (null != log)
				return log;
			return openNew();
		} finally {
			loglock.unlock();
		}
	}

	private static Lock loglock = new ReentrantLock();
	private static Log log = null;

	public static void close() {
		loglock.lock();
		try {
			if (null != log)
				log.close();
			log = null;
		} finally {
			loglock.unlock();
		}
	}

	/**
	 * 打开日志。关闭旧日志，重新打开。
	 * @param dir       日志文件所在目录，null表示不记录到文件中。
	 * @param out       输出到文件或者标准输出流中，格式为 ":file:out"。
	 * @param hourOfDay 每天滚动日志之小时。-1不滚动。
	 * @param minute    每天滚动日志之分钟。-1不滚动。
	 * @return
	 */
	public static Log openNew(File dir, String out, int hourOfDay, int minute) {
		final File logdir = out.indexOf(":file")  != -1 ? dir : null;
		final boolean console = out.indexOf(":out") != -1;
		loglock.lock();
		try {
			if (null != log)
				log.close();
			log = new Log(logdir, console, hourOfDay, minute);;
			return log;
		} finally {
			loglock.unlock();
		}
	}

	/**
	 * 得到日志实例。
	 * @return null if not open
	 */
	public static Log getLog() {
		loglock.lock();
		try {
			return log;
		} finally {
			loglock.unlock();
		}
	}

	private int level;
	private String name;
	
	private Trace(int level, String name){
		this.level = level;
		this.name = name;
	}
	
	private final void dotrace(Trace t, Object message, Throwable e) {
		Log log = getLog();
		if (log != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			StringBuilder sb = new StringBuilder();

			/* time   */ sb.append(dateFormat.format(Calendar.getInstance().getTime()));
			/* level  */ sb.append(' ').append(t.name).append(' ');
			/* thread */ sb.append('<').append(Thread.currentThread().getName()).append('>');
			/* message*/ sb.append(' ').append(message);

			log.println(sb.toString(), e);
			// 打印到控制台
			if(isPrintToConsole){
				System.out.println(getCallStackInfo() + "\n" + sb);
			}
		}

        TraceHandler volatileTmp = extraHander;
        if (volatileTmp != null){
            volatileTmp.publish(t, message.toString(), e);
        }
	}

	public static void setIsPrintToConsole(boolean isPrintToConsole) {
		Trace.isPrintToConsole = isPrintToConsole;
	}

	private static String getStackTrace(Throwable throwable){
		if(throwable == null){
			return "";
		}
		try{
			StringWriter stringWriter = new StringWriter();
			throwable.printStackTrace(new PrintWriter(stringWriter));
			return stringWriter.toString();
		} catch (Exception e){
		}
		return "";
	}

	private static String getCallStackInfo() {
		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			if (stackTraceElements == null) {
				return "";
			}
			String thisClassName = Trace.class.getName();
			String msgSep = "\r\n";
			StackTraceElement targetStack = null;
			int targetStackIndex = -1;
			for (int i = 0; i < stackTraceElements.length; ++i) {
				StackTraceElement var = stackTraceElements[i];
				String className = var.getClassName();
				if (thisClassName.equals(className)) {
					targetStackIndex = i;
					continue;
				}
				if (targetStackIndex != -1) {
					// 上一帧是调用的帧
					targetStackIndex++;
					if (targetStackIndex < stackTraceElements.length) {
						targetStack = stackTraceElements[targetStackIndex];
					}
					break;
				}
			}
			return targetStack == null ? "" : (targetStack.toString() + msgSep);
		} catch (Exception e) {
		}
		return "";
	}
	
	private final void trace(Trace t, String format, Object[] argArray) {
		if (t.level >= this.level) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
			logger.dotrace(t, ft.getMessage(), ft.getThrowable());
		}
	}
	
	private final void trace(Trace t, Object message, Throwable e) {
		if (t.level >= this.level) 
			dotrace(t, message, e);
	}
}

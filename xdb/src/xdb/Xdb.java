package xdb;

import java.util.Map;
import java.util.Random;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Timer;


/**
 * xdb是一个简单的文件数据库，支持表操作和事务，不具备表之间的关联关系。
 *
 */
public final class Xdb implements XdbMBean {
	private volatile XdbConf    conf;
	private volatile Tables     tables;
	private volatile Angel      angel;
	private volatile Checkpoint checkpoint;

	private volatile boolean isOpen = false;

	private static Timer timer = new Timer("xdb.Timer", true);

	private Random random = new Random(System.currentTimeMillis());
	private static Xdb xdbinstance = new Xdb();

	private xdb.util.MBeans.Manager xdbmbeans = new xdb.util.MBeans.Manager();

	public static xdb.util.MBeans.Manager mbeans() {
		return xdbinstance.xdbmbeans;
	}

	public static Xdb getInstance() {
		return xdbinstance;
	}

	// package private
	final Angel getAngel() {
		return angel;
	}

	// package private
//	final Checkpoint getCheckpoint() {
//		return checkpoint;
//	}

	public final Tables getTables() {
		return tables;
	}

	public static Timer timer() {
		return timer;
	}

	public static Random random() {
		return xdbinstance.random;
	}

	public final Executor getExecutor() {
		return Executor.getInstance();
	}

	public static Executor executor() {
		return Executor.getInstance();
	}

	public static boolean isOpen() {
		return xdbinstance.isOpen;
	}

	private Xdb() {
		Runtime.getRuntime().addShutdownHook(new Thread("xdb.ShutdownHook") {
			@Override
			public void run() {
				Trace.fatal("xdb stop from ShutdownHook.");
				Xdb.this.stop();
			}
		});
	}


	public final void setConf(XdbConf conf) {
		if (null == conf)
			throw new NullPointerException();
		Trace.set(conf.getTrace());
		this.conf = conf;
		final Executor e = Executor.getInstance(); // volatile
		if (null != e)
			e.setCorePoolSize(conf.getCorePoolSize(), conf.getProcPoolSize(), conf.getSchedPoolSize());
		XBean._set_xdb_verify_(conf.isXdbVerify());
	}

	public final XdbConf getConf() {
		return conf;
	}

	/**
	 * 启动网络引擎
	 */
	public final static int NETWORK      = 1;

	/**
	 * 启动唯一名。启动唯一名隐含启动网络引擎。没有唯一名支持，不能使用自增长(autoIncrement)
	 */
	public final static int UNIQNAME     = 2;

	////////////////////////////////////////////////////////////////////
	// 用来防止在同一个数据库目录下启动两个实例。同时用来检查不正常退出。
	private File inuseFile;
	public static final String XDBINUSEFILENAME = "xdb.inuse";
	private void deleteInuseFile() {
		this.inuseFile.delete();
	}
	private final void createInuseFile() {
		try {
			inuseFile = new File(conf.getDbHome(), XDBINUSEFILENAME);
			if (false == inuseFile.createNewFile())
				throw new XError("xdb is still in active use(never use simultaneously) OR"
						+ "\n\t\tnot stop normally: 1 verify and repair the db(commendatory), "
						+ "2 delete the file '" + inuseFile.getPath() + "', "
						+ "3 start again)");
		}
		catch (java.io.IOException ex) {
			throw new XError(ex);
		}
	}
	////////////////////////////////////////////////////////////////////

	public final synchronized boolean start() {
		if (xdb.util.Dbx.getManager().isOpen())
			throw new IllegalAccessError("i hate dbx.");

		if (isOpen)
			return false;

		conf.getTableHome().mkdirs();
		conf.getLogHome().mkdirs();

		//检查数据库表结构是否匹配。added by scm
		//if(!xdb.util.DatabaseMetaData.getInstance().isSame(conf.getDbHome()))
		//	throw new RuntimeException("Compare metadata fail,should run xtransform?");
		xdb.util.DatabaseMetaData.getInstance().createXML(conf.getDbHome());
		
		Trace.open(conf);
		this.createInuseFile();
		Trace.fatal("xdb start begin");
		{
			// 确保创建好静态实例，避免在以后多线程同时装载时的线程问题。
			// 当然，如果ClassLoader确保装载一个类是不并发的，那么就不需要这样处理。有待确认。
			Lockeys.getInstance();
			new XBean(null, null);
		}


		try {
			Class<?> cls = Class.forName("xtable._Tables_");
			Object instance = cls.newInstance();
			if (!(instance instanceof Tables))
				throw new XError("invalid xtable.Tables");

			Executor.start(conf.getProcedureConf().getMaxExecutionTime(),
					conf.getCorePoolSize(), conf.getProcPoolSize(), conf.getSchedPoolSize(),
					conf.getTimeoutPeriod());

			// MORE CHECK ?
			tables = (Tables)instance;
			tables.open(conf);

			checkpoint = new Checkpoint(tables);
			angel = new Angel();

			isOpen = true; // 到这里,Xdb已经启动完成。而且网络会依赖数据库，先设置标志。

			mbeans().register(this, "xdb:type=Xdb");

			checkpoint.start();
			angel.start();

			Trace.fatal("xdb start end");
			return true;

		} catch (XError e) {
			close();
			throw e;
		} catch (Throwable e) {
			close();
			throw new XError(e);
		}
	}

	/**
	 * 单独启动唯一名和网络。
	 */
	public void startNetwork() {
		xio.Engine.getInstance().open();
	}

	private final synchronized void close() {
		isOpen = false;
		Trace.fatal("xdb stop begin");
		mbeans().unregisterAll();
		xio.Engine.getInstance().close();

		Executor.stop();

		if (null != angel) {
			angel.shutdown();
			angel = null;
		}

		if (null != checkpoint) {
			checkpoint.shutdown();
			checkpoint = null;
		}

		if (null != tables) {
			tables.close();
			tables = null;
		}
		this.deleteInuseFile();
		Trace.fatal("xdb stop end");
	}

	public final void stop() {
		// try { Thread.sleep(60000); } catch (Exception ex) { } // delay stop. debug
		synchronized (this)	{
			if (false == isOpen)
				return;
			isOpen = false; // 马上设置标志
		}
		final StopHandle handle = this.stopHandle;
		if (null != handle)
			handle.beforeStop();
		this.close();
		if (null != handle)
			handle.afterStop();
	}

	private volatile StopHandle stopHandle = null;
	public void setStopHandle(StopHandle stopHandle) {
		this.stopHandle = stopHandle;
	}

	public static interface StopHandle {
		/**
		 * XDB 停止前调用。
		 * 此时XDB数据等功能都能使用。但由于isOpen已经为false，依赖于这个标志的功能会受影响。
		 */
		public void beforeStop();
		/**
		 * XDB 停止后调用。
		 */
		public void afterStop();
	}

	///////////////////////////////////////////////////////////////////////////////
	// mbean implement
	@Override
	public void shutdown(String iamsure) {
		if (iamsure.equals("iamsure")) {
			this.stop();
			Trace.fatal("halt program from jmx");
			Runtime.getRuntime().halt(0);
		}
	}

	@Override
	public int getAngleInterruptCount() {
		return this.angel.getInterruptCount();
	}

	@Override
	public long getTransactionCount() {
		return Transaction.getTotalCount();
	}

	@Override
	public long getTransactionFalse() {
		return Transaction.getTotalFalse();
	}

	@Override
	public long getTransactionException() {
		return Transaction.getTotalException();
	}

	@Override
	public void testAlive(long timeout)
		throws InterruptedException, ExecutionException, TimeoutException {
		this.getExecutor().testAlive(timeout);
		// more test ...
	}

	public CheckpointMBean getCheckpointMBean() {
		return checkpoint;
	}

	@Override
	public Map<String, AtomicInteger> top(String nsClass, String nsLock) {
		return new xdb.util.StackTrace(nsClass, nsLock).top();
	}
}

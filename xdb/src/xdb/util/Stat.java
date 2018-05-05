package xdb.util;

import xdb.util.DatabaseMetaData;
import xdb.util.Dbx;
import xdb.util.Elapse;

import java.io.File;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p> 一个简单的，基于 Dbx 的统计框架（仅支持只读统计）。
 * <ul> 数据流： browse -> decode[uncompress and unmarshal] -> process
 * <li> browse: 从硬盘读取文件时的并发由实现控制。
 * <li> decode: 解码并发由 threadPoolSize 参数控制。
 * <li> process：是否并发由 isConcurrent 参数控制。
 * </ul>
 * <p> 请参考: main; Task.run; Stat.perform;
 */
public class Stat {
	/**
	 * 例子 
	 * <pre>
	 * 命令行：
	 * cd snail/test
	 * java -cp "../xdb/bin;../test/bin;../bin/jio.jar" xdb.util.Stat -d 1
	 * 
	 * 执行结果：
	 * 其中两个表格执行会抛出异常。
	 * 1. testmerge，原因是classpath缺少junit.jar。
	 * 2. flush3，这是异常是故意的，see Stat.initializeSample()。
	 * </pre>
	 */
	public static void main(String[] args) throws Exception {

		// 解析参数
		String dbHome = "xdb";
		String libDir = ".";
		int    debugLevel = -1;
		for (int i = 0; i < args.length; ++i) {
			if (args[i].equals("-db"))  { dbHome = args[++i]; continue; }
			if (args[i].equals("-lib")) { libDir = args[++i]; continue; }
			if (args[i].equals("-d"))   { debugLevel = Integer.parseInt(args[++i]); continue; }
		}

		// 初始化统计
		Stat stat = new Stat(libDir, dbHome, true);
		if (debugLevel > 0) stat.setDebugLevel(debugLevel);
		List<TableBrowser> browsers = stat.initializeSample();

		// 执行统计
		xdb.util.Elapse elapse = new xdb.util.Elapse();
		Stat.perform(browsers, 2);

		// 统计结果：这里输出的是统计框架本身的信息
		System.out.println("total time=" + elapse.elapsed());
		Stat.reportSample(browsers);
		
		// 当实现需要安排表格的浏览顺序时，使用 newTableBrowser和TableBrowser.run完成。
		// 参考 initializeSample 和 Stat.perform。
	}

	private List<TableBrowser> initializeSample() {
		List<TableBrowser> browsers = new ArrayList<TableBrowser>();

		// 实际的统计代码是知道自己要读取那些表的。这个例子，把所有的表格都读一遍。
		for (DatabaseMetaData.Table dmdTable : this.databaseMetaData.getTables()) {
			if (!dmdTable.isPersistence())
				continue;

			browsers.add(this.newTableBrowser(dmdTable.getName(), new TableHandle() {
				@Override
				public void process(Object key, Object value) {
					// 强制转换成具体类型，然后使用它。
					// 这里的代码是否在多线程中执行，由 Stat.isConcurrent 控制的。
					if (this.getTableBrowser().getWalker().getTable().getName().equals("flush3"))
						throw new RuntimeException("process exception test.");
				}
			}));
		}

		return browsers;
	}

	public static void reportSample(List<TableBrowser> browsers) {
		for (TableBrowser b : browsers) {
			final Walker w = b.getWalker();
			final Dbx.Table t = w.getTable();
			if (b.getException() != null) {
				System.out.println(t.getName() + " <EXCEPTION>");
				b.getException().printStackTrace();
				continue;
			}

			// 汇总
			Sum sumAll = new Sum();
			for (Sum sum : w.getSums())
				sumAll.add(sum);

			// skip empty table.
			if (sumAll.recordCount == 0)
				continue;

			System.out.println(t.getName() + " file(" + w.getTableFileSize() + ") " + sumAll);
			for (Sum sum : w.getSums())
				System.out.println("		" + sum);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////
	// 实现
	private int threadPoolSize;
	private int capacityOfQueue;
	private boolean isConcurrent;
	private DatabaseMetaData databaseMetaData;
	private Dbx dbx;

	private static int DEFAULT_THREADPOOLSIZE  = 4;
	private static int DEFAULT_CAPACITYOFQUEUE = 1024;

	/**
	 * 处理记录时是否允许并发执行。
	 * @param isConcurrent
	 */
	public Stat(String libDir, String dbHome, boolean isConcurrent) {
		this(libDir, dbHome, DEFAULT_THREADPOOLSIZE, DEFAULT_CAPACITYOFQUEUE, isConcurrent, DatabaseMetaData.getInstance());
	}

	/**
	 * @param threadPoolSize   并发线程数量。
	 * @param capacityOfQueue  任务队列容量。不建议设置成MAX_VALUE，以防止工作线程处理跟不上时，导致内存不足。
	 * @param isConcurrent     统计计算是否并发执行。
	 * @param databaseMetaData 数据库信息。
	 */
	public Stat(String libDir, String dbHome, 
			int threadPoolSize, int capacityOfQueue, boolean isConcurrent,
			DatabaseMetaData databaseMetaData) {

		this.threadPoolSize   = threadPoolSize;
		this.capacityOfQueue  = capacityOfQueue;
		this.isConcurrent     = isConcurrent;
		this.databaseMetaData = databaseMetaData;

		Dbx.start(libDir);
		dbx = Dbx.open(new File(dbHome), databaseMetaData);
	}

	public static abstract class TableHandle {
		TableBrowser tableBrowser; // setup in newTableBrowser

		public TableBrowser getTableBrowser() {
			return tableBrowser;
		}

		public abstract void process(Object key, Object value);
	}

	public TableBrowser newTableBrowser(String tableName, TableHandle handle) {
		if (null == handle)
			throw new NullPointerException();

		DatabaseMetaData.Table dmdTable = databaseMetaData.getTable(tableName);

		if (null == dmdTable)
			throw new RuntimeException("table not found: " + tableName);

		// dbx.openTable 不判断持久性，当表格文件不存在时会自动创建，需要明确判断。
		if (!dmdTable.isPersistence())
			throw new RuntimeException("table is not persistence: " + tableName);

		final Dbx.Table table = dbx.openTable(tableName);
		final Walker walker = this.newWalker(this, table, handle);
		final TableBrowser browser = new TableBrowser(walker);
		handle.tableBrowser = browser;
		return browser;
	}

	public static class TableBrowser implements Runnable {
		private Walker walker;
		private Throwable exception = null;

		public TableBrowser(Walker walker) {
			this.walker = walker;
		}

		@Override
		public void run() {
			try {
				walker.getTable()._browse(walker);
				walker.awaitTermination();
				exception = walker.getFirstTaskException();
			} catch (Throwable ex) {
				exception = ex;
			}
		}

		public Walker getWalker() {
			return walker;
		}

		public Throwable getException() {
			return exception;
		}
	}

	/**
	 * 完成所有的表格浏览。
	 * @param browsers
	 * @param concurrency 并发数量
	 * @throws InterruptedException
	 */
	public static void perform(List<TableBrowser> browsers, int concurrency) throws InterruptedException {
		if (concurrency > 1) {
			ThreadPoolExecutor browseExecutor = new ThreadPoolExecutor(
					concurrency, concurrency, 0, TimeUnit.NANOSECONDS,
					new LinkedBlockingQueue<Runnable>());
			for (TableBrowser browser : browsers)
				browseExecutor.execute(browser);
			browseExecutor.shutdown();
			browseExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // INFINITE
		} else {
			for (TableBrowser browser : browsers)
				browser.run();
		}
	}

	public DatabaseMetaData getDatabaseMetaData() {
		return databaseMetaData;
	}

	public int getCapacityOfQueue() {
		return capacityOfQueue;
	}

	public int getThreadPoolSize() {
		return threadPoolSize;
	}
	
	public boolean isConcurrent() {
		return isConcurrent;
	}

	protected Walker newWalker(Stat stat, Dbx.Table table, TableHandle handle) {
		return new Walker(stat, table, handle);
	}

	protected Task newTask(Walker walker) {
		return new Task(walker);
	}

	/**
	 * 每个线程分开统计，最后根据需要汇总。有些统计参数需要按线程计算，比如 uncompress速度。
	 */
	public static class Sum {
		public long recordCount = 0;

		public long totalKeySize = 0; // never compressed
		public long totalCompressedValueSize = 0;
		public long totalValueSize = 0;

		public long totalUncompressTime = 0;
		public long totalUnmarshalTime = 0;
		public long totalProcessTime = 0;

		public void add(Sum r) {
			this.recordCount              += r.recordCount;

			this.totalKeySize             += r.totalKeySize;
			this.totalCompressedValueSize += r.totalCompressedValueSize;
			this.totalValueSize           += r.totalValueSize;

			this.totalUncompressTime      += r.totalUncompressTime;
			this.totalUnmarshalTime       += r.totalUnmarshalTime;
			this.totalProcessTime         += r.totalProcessTime;
		}

		public static String headers() {
			return "RecordCount	KeySize	CompressedValueSize	ValueSize	UncompressTime	UnmarshalTime	ProcessTime";
		}

		@Override
		public String toString() {
			return String.format("%d	%d	%d	%d	%d	%d	%d", this.recordCount
					, this.totalKeySize, this.totalCompressedValueSize, this.totalValueSize
					, this.totalUncompressTime, this.totalUnmarshalTime, this.totalProcessTime);
		}
	}

	public static class Walker implements xdb.Storage.IWalk {
		private Stat stat;
		private Dbx.Table table;
		private Stat.TableHandle handle;
		private ThreadPoolExecutor executor;

		private Task task = null;
		private volatile Throwable firstTaskException = null;

		private List<Sum> sums = Collections.synchronizedList(new ArrayList<Sum>());
		private ThreadLocal<Sum> threadLocalSum = new ThreadLocal<Sum>() {
			@Override
			protected Sum initialValue() {
				Sum x = new Sum();
				sums.add(x);
				return x;
			};
		};

		Sum getThreadLocalSum() {
			return threadLocalSum.get();
		}

		public Walker(Stat stat, Dbx.Table table, Stat.TableHandle handle) {
			this.stat = stat;
			this.table = table;
			this.handle = handle;
			final int threadPoolSize = stat.getThreadPoolSize();
			this.executor = new ThreadPoolExecutor(
					threadPoolSize, threadPoolSize, 0, TimeUnit.NANOSECONDS,
					new LinkedBlockingQueue<Runnable>(stat.getCapacityOfQueue()));
		}

		public long getTableFileSize() {
			return table.getFile().length();
		}

		public Stat getStat() {
			return stat;
		}

		public Dbx.Table getTable() {
			return table;
		}

		public Stat.TableHandle getTableHandle() {
			return handle;
		}

		/**
		 * Note: 参数中的 data 是压缩后的数据。
		 */
		@Override
		public boolean onRecord(byte[] key, byte[] data) {
			if (null == task)
				task = this.stat.newTask(this);

			if (task.prepare(key, data)) {
				executor.execute(task);
				task = null;
			}
			return null == firstTaskException;
		}

		void setFirstTaskException(Throwable exp) {
			synchronized (this) {
				if (null != firstTaskException)
					return;
				firstTaskException = exp;
			}
			executor.shutdownNow();
		}

		public Throwable getFirstTaskException() {
			return firstTaskException;
		}

		public void awaitTermination() throws InterruptedException {
			if (null != task) {
				if (null == firstTaskException) {
					try { executor.execute(task); } catch (Throwable ex) { /*skip*/ }
				}
				task = null;
			}
			executor.shutdown();
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS); // INFINITE
		}

		public List<Sum> getSums() {
			if (!executor.isTerminated())
				throw new IllegalStateException("walker is not terminated! table=" + this.table.getName());
			return sums;
		}
	}

	public static class Task implements Runnable {
		private static final int BULKCOUNT = 1024;

		private byte[][] keys = new byte[BULKCOUNT][];
		private byte[][] values = new byte[BULKCOUNT][];
		private int size = 0;

		private Walker walker;

		public Task(Walker walker) {
			this.walker = walker;
		}

		public Walker getWalker() {
			return walker;
		}

		/**
		 * @param key
		 * @param value
		 * @return true if full.
		 */
		public boolean prepare(byte[] key, byte[] value) {
			keys[size] = key;
			values[size] = value;
			++size;
			return BULKCOUNT == size;
		}

		@Override
		public void run() {
			try {
				_run();
			} catch (Throwable ex) {
				this.getWalker().setFirstTaskException(ex);
				if (this.getWalker().getStat().getDebugLevel() >= 5)
					ex.printStackTrace();
			}
		}

		private void _run() {
			Sum counter = this.getWalker().getThreadLocalSum();

			Elapse elapse = new Elapse();
			counter.recordCount += size;

			// uncompress
			byte[][] uncompressedValues = new byte[size][];
			for (int i = 0; i < size; ++i) {
				counter.totalKeySize += keys[i].length;
				counter.totalCompressedValueSize += values[i].length;
				uncompressedValues[i] = Dbx.Table._uncompress(values[i], values[i].length);
				counter.totalValueSize += uncompressedValues[i].length;
			}
			counter.totalUncompressTime += elapse.elapsedAndReset();

			// unmarshal
			final Dbx.Table table = this.getWalker().getTable();
			final DatabaseMetaData.Type keyType = table.getMetaData().getKeyType();
			final DatabaseMetaData.Type valueType = table.getMetaData().getValueType();
			Object[] objKeys = new Object[size];
			Object[] objValues = new Object[size];
			for (int i = 0; i < size; ++i) {
				objKeys[i] = keyType.unmarshal(keys[i]);
				objValues[i] = valueType.unmarshal(uncompressedValues[i]);
			}
			counter.totalUnmarshalTime += elapse.elapsed();

			// process
			final boolean isConcurrent = this.getWalker().getStat().isConcurrent();
			final Stat.TableHandle handle = this.getWalker().getTableHandle();
			if (isConcurrent)
				this.getWalker().getStat().lockConcurrent();

			try {
				elapse.reset();
				for (int i = 0; i < size; ++i) {
					handle.process(objKeys[i], objValues[i]);
				}
				counter.totalProcessTime += elapse.elapsed();
			} finally {
				if (isConcurrent)
					this.getWalker().getStat().unlockConcurrent();
			}
		}
	}

	private Lock concurrentLock = new ReentrantLock();
	private int debugLevel = 5;

	public void lockConcurrent() {
		this.concurrentLock.lock();
	}

	public void unlockConcurrent() {
		this.concurrentLock.unlock();
	}

	/**
	 * 设置调试级别。数字越大，输出越多。默认为5。
	 * @param debugLevel
	 */
	public void setDebugLevel(int debugLevel) {
		this.debugLevel = debugLevel;
	}

	public int getDebugLevel() {
		return debugLevel;
	}
}

package xdb.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.goldhuman.Common.Marshal.OctetsStream;

/**
 * 一个无日志的数据库。
 *
 * <p>可以用来处理数据导入导出，数据转换，等需要批量修改。
 * <p>线程安全，提供两种锁：DB.lock(), Table.lock()。
 */
public class Dbx {
	//////////////////////////////////////////////////////////////////////
	// db.h JNI native library load helper
	/**
	 * load native library。
	 * <p>if libname exist in jar, load from jar; else if (null != libpath) load from libpath; else load from dbhome.getParent;
	 * 
	 * @param libpath
	 * @param dbhome
	 * @param libname library name. 如果为 null; 则根据$(os.arch)构造名字。
	 */
	public static void load(File libpath, File dbhome, String libname) {
		if ( null == libname)
			libname = System.mapLibraryName("db_" + System.getProperty("os.arch"));

		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(libname);
		if (null != is){
			try {
				String base = libname;
				String ext = null;
				int idx = libname.lastIndexOf(".");
				if (-1 != idx) {
					base = libname.substring(0, idx);
					ext = libname.substring(idx);
				}

				File tmpFile = File.createTempFile(base, ext, new File(System.getProperty("java.io.tmpdir")));
				try {
                    Files.copy(is, tmpFile.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
                    xdb.Trace.info("System.load(" + tmpFile.getAbsolutePath() + ")");
                    System.load(tmpFile.getAbsolutePath());
                } finally {
					tmpFile.delete();
				}
            }catch(IOException e){
				throw new RuntimeException(e);
			}finally {
				try {
					is.close();
				} catch (IOException ignore) {
				}
			}
		}else{
			String absoluteLibPath = null != libpath
				? new File(libpath, libname).getAbsolutePath()
				: new File(dbhome.getParentFile(), libname).getAbsolutePath();
			xdb.Trace.info("System.load(" + absoluteLibPath + ")");
			System.load( absoluteLibPath );
		}
	}

	public static void load(File libpath, File dbhome) {
		load(libpath, dbhome, null);
	}

	public static void load(String libpath) {
		load(new File(libpath), null, null);
	}

	//////////////////////////////////////////////////////////////////////////
	// Dbx 管理。
	public final static class Manager {
		private final Lock lock = new ReentrantLock();
		private Map<String, Dbx> dbxs = new HashMap<String, Dbx>();
		private volatile Timer timer;
		private boolean opened = false;
		private volatile boolean saveAtExit = true;

		public Manager() {
			Runtime.getRuntime().addShutdownHook(new Thread("xdb.util.Dbx.ShutdownHook") {
				@Override
				public void run() {
					Manager.this.stop();
				}
			});
		}

		public boolean isOpen() {
			lock.lock();
			try {
				return opened;
			} finally {
				lock.unlock();
			}
		}

		/**
		 * @param libpath    library path.
		 * @param saveAtExit save at exit.
		 * @return
		 */
		public boolean start(String libpath, boolean saveAtExit) {
			xdb.Trace.openIf(); // 如果日志没有打开则打开一个。
			lock.lock();
			try {
				if (opened)
					return false;
				xdb.Trace.info("xdb.util.Dbx start ...");
				this.saveAtExit = saveAtExit;
				Dbx.load(libpath);
				timer = new Timer("xdb.util.Dbx.Timer", true);
				opened = true;
				return true;
			} finally {
				lock.unlock();
			}
		}

		public void stop() {
			stop(saveAtExit);
		}

		public void stop(boolean save) {
			lock.lock();
			try {
				if (opened) {
					xdb.Trace.info("xdb.util.Dbx stop begin");
					// remove in loop. 这里并不需要在锁里面关闭数据库。
					if (save)
						for (Dbx dbx : Manager.this.getDbxs()) dbx.close();
					else
						for (Dbx dbx : Manager.this.getDbxs()) dbx.destroy();
					assert(Manager.this.getDbxs().length == 0);

					timer.cancel();
					timer = null;
					opened = false;
					xdb.Trace.info("xdb.util.Dbx stop end");
				}
			} finally {
				lock.unlock();
			}
		}

		public void setSaveAtExit(boolean saveAtExit) {
			this.saveAtExit = saveAtExit;
		}

		public Timer getTimer() {
			return timer;
		}

		/**
		 * open a DB ( NullLogger only )
		 * @param home
		 * @param metaData
		 * @return
		 */
		public Dbx open(File home, DatabaseMetaData metaData) {
			String homepath = toCanonicalPath(home);
			lock.lock();
			try {
				Dbx dbx = dbxs.get(homepath);
				if ( null == dbx ) {
					dbx = new Dbx(home, metaData);
					dbxs.put(homepath, dbx);
				}
				return dbx;
			} finally {
				lock.unlock();
			}
		}

		// helper
		private static String toCanonicalPath(File file) {
			try {
				return file.getCanonicalPath();
			} catch (IOException x) {
				throw new RuntimeException(x);
			}
		}

		public Dbx[] getDbxs() {
			lock.lock();
			try {
				return dbxs.values().toArray(new Dbx[dbxs.size()]);
			} finally {
				lock.unlock();
			}
			
		}

		// called when Dbx close and destroy
		private void removeDbx(Dbx dbx) {
			String homepath = toCanonicalPath(dbx.getHome());
			lock.lock();
			try {
				dbxs.remove(homepath);
			} finally {
				lock.unlock();
			}
		}

	}

	private static final Manager manager = new Manager();

	public static Manager getManager() {
		return manager;
	}

	public static boolean start(String libpath) {
		return manager.start(libpath, true);
	}

	public static void stop() {
		manager.stop();
	}

	public static Dbx open(File dbhome, DatabaseMetaData metaData) {
		if (!manager.isOpen())
			throw new IllegalStateException("Dbx is stopped"); 
		return manager.open(dbhome, metaData);
	}

	////////////////////////////////////////////////////////////
	// Dbx 实现
	private final Lock dbLock = new ReentrantLock(); // 保护 DB 的所有操作。
	private final File home;      // database directory
	private final File tableHome; // table directory
	private final File logHome;    // log directory
	private final DatabaseMetaData metaData;
	private final Map<String, Table> tables = new HashMap<String, Table>();
	private boolean closed = false;

	/**
	 * 继承自 xdb.Storage。
	 * @see xdb.Storage
	 */
	public class Table extends xdb.Storage {
		private final String name;
		private final DatabaseMetaData.Table metaData; // fast reference
		private boolean walking = false;
		private boolean browsing = false;

		Table(xdb.Logger logger, String tableName, int cacheHigh, int cacheLow) {
			super(logger, Dbx.this.tableHome, tableName, cacheHigh, cacheLow);
			this.name = tableName;
			this.metaData = Dbx.this.getMetaData().getTable(tableName);
		}

		/**
		 * 表所属的数据库
		 * @return
		 */
		public Dbx getDatabase() {
			return Dbx.this;
		}

		public DatabaseMetaData.Table getMetaData() {
			return metaData;
		}
		/**
		 * @return 表名。
		 */
		public String getName() {
			return name;
		}

		/**
		 * lock outside.
		 */
		private void assertNotWalking() {
			if (this.walking)
				throw new ConcurrentModificationException("walking!");
		}

//		/**
//		 * lock outside.
//		 */
//		private void assertNotBrowsing() {
//			if (this.browsing)
//				throw new ConcurrentModificationException("browsering!");
//		}

		/**
		 * lock outside.
		 */
		private void assertNotWalkingAndBrowsing() {
			if (this.walking || this.browsing)
				throw new ConcurrentModificationException("walking or browsering!");
		}

		/**
		 * 保存修改。<b>锁：Locked</b>
		 */
		public void save() {
			super.lock();
			try {
				assertNotWalkingAndBrowsing();
				try {
					this.getLogger().checkpoint(this, System.currentTimeMillis());
				} catch (Throwable e) {
					xdb.Trace.fatal("Table.save: halt program", e);
					Runtime.getRuntime().halt(54321);
				}
			} finally {
				super.unlock();
			}
		}

		/**
		 * 关闭表，不保存。<b>锁：Locked</b>
		 */
		public void destroy() {
			super.lock();
			try {
				assertNotWalkingAndBrowsing();
				Dbx.this.removeTable(this.getName());
				super.close();
			} finally {
				super.unlock();
			}
		}

		/**
		 * 关闭表，提交保存。<b>锁：Locked</b>
		 */
		@Override
		public void close() {
			super.lock();
			try {
				assertNotWalkingAndBrowsing();
				this.save();
				Dbx.this.removeTable(this.getName());
				super.close();
			} finally {
				super.unlock();
			}
		}

		/**
		 * 插入记录，如果记录已经存在则返回false。<b>锁：Locked</b>。
		 * <p><b>value 必须是压缩过后的数据</b>
		 * @return 通过返回值报告错误。
		 */
		@Override
		public boolean _insert(OctetsStream key, OctetsStream value) {
			super.lock();
			try {
				assertNotWalking();
				return super._insert(key, value);
			} finally {
				super.unlock();
			}
		}

		/**
		 * 插入记录，如果存在则覆盖，如果不存在则添加。<b>锁：Locked</b>。
		 * <p><b>value 必须是压缩过后的数据</b>
		 * @return 通过返回值报告错误。
		 */
		@Override
		public boolean _replace(OctetsStream key, OctetsStream value) {
			super.lock();
			try {
				assertNotWalking();
				return super._replace(key, value);
			} finally {
				super.unlock();
			}
		}

		/**
		 * 插入记录，如果存在则覆盖，如果不存在则添加。<b>锁：Locked</b>。
		 * @return 通过返回值报告错误。
		 */
		@Override
		public boolean replace(OctetsStream key, OctetsStream value) {
			super.lock();
			try {
				assertNotWalking();
				return super.replace(key, value);
			} finally {
				super.unlock();
			}
		}

		/**
		 * 插入记录，如果记录已经存在返回false。<b>锁：Locked</b>。
		 * @return 通过返回值报告错误。
		 */
		@Override
		public boolean insert(OctetsStream key, OctetsStream value) {
			super.lock();
			try {
				assertNotWalking();
				return super.insert(key, value);
			} finally {
				super.unlock();
			}
		}

		/**
		 * 删除记录。如果记录不存在则抛出异常。<b>锁：Locked</b>。
		 * @return 通过返回值报告错误。
		 */
		@Override
		public boolean remove(OctetsStream key) {
			super.lock();
			try {
				assertNotWalking();
				return super.remove(key);
			} finally {
				super.unlock();
			}
		}

		/**
		 * 直接遍历数据文件。<b>锁：Locked</b>。
		 * 按存储顺序返回记录，记录数据保持压缩状态。
		 * @Deprecated 不安全。存在把索引页误认为是数据页的可能，会导致错误的结果甚至崩溃。
		 */
		@Override
		public void _browse(IWalk iw, int cachesize) {
			super.lock();
			try {
				// 不允许浏览重入，虽然底层支持。
				// 如果允许重入，则 browsering 不能用boolean类型，应改成int进行计数。
				assertNotWalkingAndBrowsing();
				this.browsing = true;
				try {
					super._browse(iw, cachesize);
				} finally {
					this.browsing = false;
				}
			} finally {
				super.unlock();
			}
		}

		/**
		 * 直接遍历数据文件。<b>锁：Locked</b>。
		 * 按存储顺序返回记录，记录数据保持压缩状态。
		 * @Deprecated 不安全。存在把索引页误认为是数据页的可能，会导致错误的结果甚至崩溃。
		 */
		public void _browse(IWalk iw) {
			this._browse(iw, 128);
		}

		/**
		 * 直接遍历数据文件。<b>锁：Locked</b>。
		 * 按存储顺序返回记录。
		 * @Deprecated 不安全。存在把索引页误认为是数据页的可能，会导致错误的结果甚至崩溃。
		 */
		public void browse(IWalk ib) {
			this.browse(ib, 128);
		}

		/**
		 * 直接遍历数据文件。<b>锁：Locked</b>。
		 * 按存储顺序返回记录。
		 * @Deprecated 不安全。存在把索引页误认为是数据页的可能，会导致错误的结果甚至崩溃。
		 */
		@Override
		public void browse(IWalk ib, int cachesize) {
			super.lock();
			try {
				// 不允许浏览重入，虽然底层支持。
				// 如果允许重入，则 browsering 不能用boolean类型，应改成int进行计数。
				assertNotWalkingAndBrowsing();
				this.browsing = true;
				try {
					super.browse(ib, cachesize);
				} finally {
					this.browsing = false;
				}
			} finally {
				super.unlock();
			}
		}

		/**
		 * 通过索引遍历表。<b>锁：Locked</b>。
		 * 按索引顺序返回记录。记录数据保持压缩状态。
		 */
		@Override
		public void _walk(IWalk iw) {
			super.lock();
			try {
				// 不允许浏览重入，虽然底层支持。
				// 如果允许重入，则 browsing 不能用boolean类型，应改成int进行计数。
				assertNotWalkingAndBrowsing();
				this.walking = true;
				try {
					super._walk(iw);
				} finally {
					this.walking = false;
				}
			} finally {
				super.unlock();
			}
		}

		/**
		 * 通过索引遍历表。<b>锁：Locked</b>。
		 * 按索引顺序返回记录
		 */
		@Override
		public void walk(IWalk iw) {
			super.lock();
			try {
				// 不允许浏览重入，虽然底层支持。
				// 如果允许重入，则 browsering 不能用boolean类型，应改成int进行计数。
				assertNotWalkingAndBrowsing();
				this.walking = true;
				try {
					super.walk(iw);
				} finally {
					this.walking = false;
				}
			} finally {
				super.unlock();
			}
		}

	}

	/**
	 * 建立数据库实例。
	 * @param home         database home directory.
	 * @param metaData     database meta data.
	 */
	private Dbx(File home, DatabaseMetaData metaData) {
		if (xdb.Xdb.isOpen())
			throw new IllegalAccessError("i hate xdb.");

		if (new File(home, xdb.Xdb.XDBINUSEFILENAME).exists())
			throw new IllegalAccessError("xdb inuse.");

		// 动态库在 Dbx.start 时已经装载。
		// 由于Dbx经常在表格发生变动时使用，这里就不检查DatabaseMetaData。 
		new Integrity(null, home).verify();

		// Integrity 中已经检查了home目录。
//		if (!home.isDirectory() || !home.exists())
//			throw new IllegalStateException("dbhome : " + home + " (!isDirectory || !exists())");

		this.home = home;
		this.tableHome = new File(home, xdb.XdbConf.DATADIR);
		this.logHome = new File(home, xdb.XdbConf.LOGSDIR);
		this.metaData = metaData;

		this.tableHome.mkdirs();
		this.logHome.mkdirs();
	}

	/**
	 * 关闭数据库，
	 */
	public void destroy() {
		dbLock.lock();
		try {
			if (false == closed) {
				// 循环中删除。
				for (Table table : this.getTables())
					table.destroy();
				Dbx.getManager().removeDbx(this);
				closed = true;
			}
		} finally {
			dbLock.unlock();
		}
	}

	/**
	 * 关闭数据库。保存修改。
	 */
	public void close() {
		dbLock.lock();
		try {
			if (false == closed) {
				/* ???????????????????????????????????????????????????????????????????????????
				 * 使用不同的 NullLogger 打开数据库中的部分表格并修改后，再使用 GlobalLogger 打开是否正确？
				 * 下面两种方式看起来都可以工作，第1个方法看起来更有保证。第2个方法好像也没问题。
				 */
				// 1. 修改所有表的 magic-page 都会被刷新。
//				for (DatabaseMetaData.Table table : metaData.getTables())
//					openTable(table.getName()).close();
//				// ToDo 清除GlobalLogger残留的所有日志：rm -rf dblogs/*

				// 2. 只关闭当前打开的表。
				for (Table table : this.getTables())
					table.close();
				//////////////////////////////////////////////////////////////////////////////
				Dbx.getManager().removeDbx(this);
				closed = true; // must after openTable
				assert(this.tables.isEmpty());
			}
		} finally {
			dbLock.unlock();
		}
	}

	/**
	 * 是否已经关闭。
	 * @return
	 */
	public boolean isClosed() {
		dbLock.lock();
		try {
			return closed;
		} finally {
			dbLock.unlock();
		}
	}

	/**
	 * @return Database Home
	 */
	public File getHome() {
		return home;
	}

	public DatabaseMetaData getMetaData() {
		return metaData;
	}

	/**
	 * 返回所有打开的表。
	 * @return
	 */
	public Table[] getTables() {
		dbLock.lock();
		try {
			return tables.values().toArray(new Table[tables.size()]);
		} finally {
			dbLock.unlock();
		}
	}

	public Table getTable(String tableName) {
		dbLock.lock();
		try {
			return tables.get(tableName);
		} finally {
			dbLock.unlock();
		}
	}

	private final Table removeTable(String tableName) {
		dbLock.lock();
		try {
			return this.tables.remove(tableName);
		} finally {
			dbLock.unlock();
		}
	}
	/**
	 * 根据表名打开表格。如果表格已经打开，则返回它。cache：cacheHigh=512, cacheLow=128
	 */
	public Table openTable(String tableName) {
		return openTable(tableName, 512, 128);
	}

	/**
	 * 根据表配置打开表格。如果表格已经打开，则返回它。
	 */
	public Table openTable(xdb.TableConf tconf) {
		return openTable(tconf.getName(), tconf.getCacheHigh(), tconf.getCacheLow());
	}

	/**
	 * 新建表格(不在Meta中定义的表格)。如果表格已经打开，则返回它。
	 * @param tableName
	 * @return
	 */
	public Table newTable(String tableName) {
		return newTable(tableName, 512, 128);
	}

	/**
	 * 新建表格(不在Meta中定义的表格)。如果表格已经打开，则返回它。
	 * @param tableName
	 * @param cacheHigh
	 * @param cacheLow
	 * @return
	 */
	public Table newTable(String tableName, int cacheHigh, int cacheLow) {
		if (null != this.metaData.getTable(tableName))
			throw new IllegalStateException("table in MetaData: " + tableName);
		return _openTable(tableName, cacheHigh, cacheLow);
	}

	/**
	 * 打开表格。如果表格已经打开，则返回它。
	 */
	public Table openTable(String tableName, int cacheHigh, int cacheLow) {
		if (null == this.metaData.getTable(tableName))
			throw new IllegalStateException("table not found in MetaData: " + tableName);
		return _openTable(tableName, cacheHigh, cacheLow);
	}

	/**
	 * 打开表格。如果表格已经打开，则返回它。
	 */
	private Table _openTable(String tableName, int cacheHigh, int cacheLow) {
		dbLock.lock();
		try {
			if (closed)
				throw new IllegalStateException("Database has closed.");
			Table table = tables.get(tableName);
			if (null == table) {
				table = new Table(new xdb.Logger(), tableName, cacheHigh, cacheLow);
				tables.put(tableName, table);
			}
			return table;
		} finally {
			dbLock.unlock();
		}
	}

	/////////////////////////////////////////////////////////
	// 保存
	private TimerTask autoSaveTask = null;

	/**
	 * 保存数据。即使没有修改也需要调用。用于清除db.h的cache。
	 * 否则遍历的时候，所有的数据都会被载入内存。
	 */
	public void save() {
		dbLock.lock();
		try {
			if (closed)
				throw new IllegalStateException("Database has closed.");

			for (Table table : tables.values())
				table.save();
		} finally {
			dbLock.unlock();
		}
	}

	public final void lock() {
		dbLock.lock();
	}
	
	public final void lockInterruptibly() {
		try {
			dbLock.lockInterruptibly();
		} catch (InterruptedException ex) {
			throw new xdb.XLockInterrupted("DBNullLogger:" + tableHome);
		}
	}

	public final void unlock() {
		dbLock.unlock();
	}

	/**
	 * 自动定时保存并且清除db.h的cache。保存在另一个线程中执行。
	 * 如果对线程安全没有把握，就自己调用save()。比如每处理10万个记录，调用一次。
	 * @param period 间隔，毫秒。
	 */
	public void setAutoSave(long period) {
		dbLock.lock();
		try {
			if (null != autoSaveTask) {
				autoSaveTask.cancel();
				autoSaveTask = null;
			}
			if (period > 0) {
				autoSaveTask = new TimerTask() {
					@Override
					public void run() {
						Dbx.this.save();
					}
				};
				Dbx.getManager().getTimer().schedule(autoSaveTask, period, period);
			}
		} finally {
			dbLock.unlock();
		}
	}
}

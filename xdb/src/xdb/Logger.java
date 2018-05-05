package xdb;

import java.io.File;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Collection;

/**
 * share/commom/db.h
 * GNET::Logger
 * 
 */
public final class Logger {
	private long handle;
	private int  type;

	public final static int TypeNull   = 0;
	public final static int TypePage   = 1;
	public final static int TypeGlobal = 2;

	public Logger() {
		this(null, 0);
	}

	public Logger(String logdir, int pages) {
		if (pages < 0)
			throw new IllegalArgumentException();

		handle = create(logdir, pages);
		if (0 == handle)
			throw new XError("Logger(" + logdir + ", " + pages + ")");

		// 这个类型识别规则请参考 jnidb/xdb_Logger.cpp 中 Java_xdb_Logger_create 的实现。
		this.type = (null != logdir) ? TypeGlobal : (TypeNull == pages ? TypeNull : TypePage);
	}

	public int getType() {
		return type;
	}

	/**
	 * 关闭以后，Logger 就失效了。
	 */
	public void close() {
		if (0 == handle)
			throw new XError("logger has closed");

		close(handle);
		handle = 0;
	}

	@Override
	protected void finalize() throws Throwable {
		if (0 != handle)
			close(handle);
		super.finalize();
	}

	long getHandle() {
		return handle;
	}

	public void prepare() {
		if (0 == handle)
			throw new XError("logger has closed");

		if (false == prepare(handle))
			throw new XError("checkpoint prepare faild");
	}

	public void commit(long timestamp) {
		if (0 == handle)
			throw new XError("logger has closed");

		if (false == commit(handle, timestamp))
			throw new XError("checkpoint commit faild");
	}


	public int verify() {
		if (0 == handle)
			throw new XError("logger has closed");

		return verify(handle);
	}

	/**
	 * 只有 GNET::GlobalLogger 才有这个属性。GNET::NullLogger返回0；
	 */
	public long loggerid() {
		if (0 == handle)
			throw new XError("logger has closed");
		return loggerid(handle);
	}

	public void removeOlder() {
		long current = loggerid();
		for (File log : listFiles()) {
			// logFileName -> snprintf(buffer, "log.%08lx", time_t); see GNET::GlobalLogger
			String loggerid = log.getName().substring(4);
			if (Long.parseLong(loggerid, 16) < current)
				log.delete();
		}
	}

	public static final boolean isLogFile(File log) {
		try {
			String name = log.getName();
			if (!name.startsWith("log."))
				return false;
			String loggerid = name.substring(4);
			if (loggerid.length() != 8)
				return false;
			Long.parseLong(loggerid, 16);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public final static java.util.List<File> listFiles() {
		return listFiles(Xdb.getInstance().getConf().getLogHome());
	}

	public final static java.util.List<File> listFiles(File dir) {
		java.util.List<File> result = new java.util.ArrayList<File>();
		File [] files = dir.listFiles();
		if (null != files) {
			for (File file : files) {
				if (file.isFile() && isLogFile(file))
					result.add(file);
			}
		}
		return result;
	}

	public static SortedMap<String, File> sortedFiles(File home) {
		SortedMap<String, File> result = new TreeMap<String, File>();
		File [] files = home.listFiles();
		if (null != files) {
			for (File file : files) {
				if (file.isFile() && isLogFile(file))
					result.put(file.getName(), file);
			}
		}
		return result;
	}

	/**
	 * 创建 Logger 实例。
	 * 
	 * <p> GlobalLogger : need logdir, need pagesize
	 * <p> NullLogger   : set logdir=null, set pagesize=0;
	 * <p> PageLogger   : set logdir=null, set pagesize=1; 暂时不准备用。
	 * 
	 * @param logdir
	 * @param pagesize
	 * @return
	 */
	private native long create(String logdir, int pagesize);
	private native int verify(long handle);
	private native void close(long handle);
	private native boolean prepare(long handle);
	private native boolean commit(long handle, long timestamp);
	private native long loggerid(long handle);

	/**
	 * 创建快照并保存数据。
	 * <p><b>如果抛出异常，必须退出程序，马上停止执行。</b>
	 */
	public final void checkpoint(Storage storage, long timestamp) {
		checkpoint(java.util.Arrays.asList(storage), timestamp);
	}

	/**
	 * 创建快照并保存数据。
	 * <p><b>如果抛出异常，必须退出程序，马上停止执行。</b>
	 */
	public final void checkpoint(Collection<Storage> storages, long timestamp) {
		for (Storage storage : storages)
			storage.lock();
		// <-- this is checkpoint. after all lock success

		for (Storage storage : storages) {
			storage.snapshot_create();
			storage.unlock(); // storage is ready for read now
		}

		this.prepare();
		this.commit(timestamp);

		for (Storage storage : storages) {
			storage.lock();
			storage.snapshot_release();
			storage.unlock();
		}
	}
}

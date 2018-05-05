package xdb;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.io.*;
import java.text.SimpleDateFormat;

public class Tables {
	private final Map<String, Table> tables = new HashMap<String, Table>();
	private final ReentrantReadWriteLock flushLock = new ReentrantReadWriteLock(); 
	private final Lock checkpointLock = new ReentrantLock();
	private Logger logger;

	// Table 管理 Storage 打开关闭。对于 Table，Storage 是可选的。
	// 这里单独保存一份所有Storage的列表，便于数据表访问。
	private List<Storage> storages = new ArrayList<Storage>();
	private TableSys tableSys = new TableSys();

	public final TableSys getTableSys() {
		return tableSys;
	}

	public final Table getTable(String name) {
		return tables.get(name);
	}

	public final Collection<Table> getTables() {
		return tables.values();
	}

	protected Tables () {
		// add system table
		add(tableSys);
	}

	protected final void add(Table table) {
		if (null != tables.put(table.getName(), table))
			throw new XError("duplicate table name " + table.getName());
	}

	final void open(XdbConf conf) {
		if (null != logger)
			throw new XError("tables opened");

		xdb.util.Dbx.load(conf.getLibdbPath(), conf.getDbHome());

		// db.h need a separator
		String logdir = conf.getLogHome().toString() + File.separator; 
		// 一共尝试两次，如果发生了数据修复，需要关闭再重新打开所有表格。
		for (int i = 0; i < 2; ++i) {
			logger = new Logger(logdir, conf.getLogPages());
			for (Table table : tables.values()) {
				Storage storage = table.open(conf, logger);
				if (null != storage)
					storages.add(storage);
			}
			int v = logger.verify();
			if (0 == v) // success
				break;
			if (1 == v && 0 == i) {
				// 第一次发生可恢复性错误时，重新尝试一次。close, and retry
				close();
				continue;
			}
			throw new XError("db corrupt @" + i);
		}

		// <<在 table.initialize 前>>先给现有的表分配好锁id。详情请看内部实现。
		xdb.Lockeys.getInstance().initializeLockIdMap(tables.values());
		for (Table table : tables.values()) {
			table.initialize(this);
		}
	}

	final void close() {
		storages.clear();

		for (Table table : tables.values())
			table.close();

		if (null != logger) {
			logger.close();
			logger = null;
		}		
	}

	final Lock flushReadLock() {
		return flushLock.readLock();
	}

	final boolean isHeldFlushWriteLockByCurrentThread() {
		return flushLock.writeLock().isHeldByCurrentThread();
	}

	final Lock flushWriteLock() {
		return flushLock.writeLock();
	}

	final void logNotify() {
		for (Table table : tables.values()) {
			table.logNotify();
		}
	}

	final Lock checkpointLock() {
		return checkpointLock;
	}

	///////////////////////////////////////////////////////////////////////
	// 发生任何错误，就终止程序。不处理错误。
	/*
	final int flush() {
		Lock lock = flushrwlock.writeLock();
		lock.lock();
		int count = 0;
		for (Storage storage : storages)
			count += storage.flush();
		lock.unlock();
		return count;
	}
	*/

	final List<Storage> getStorages() {
		return storages;
	}

	final Logger getLogger() {
		return logger;
	}

	private void copy(File src, File destDir) throws Exception {
		if (Trace.isDebugEnabled())
			Trace.debug("copy " + src + " " + destDir);

		InputStream is = new FileInputStream(src);
		try {
			OutputStream os = new FileOutputStream(new File(destDir, src.getName()));
			try {
				byte [] buffer = new byte[8192];
				int rc = 0;
				while ((rc = is.read(buffer)) > 0) {
					os.write(buffer, 0, rc);
				}
			} finally {
				os.close();
			}
		} finally {
			is.close();
		}
	}

	private static SimpleDateFormat sdf;
	static {
		sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		sdf.setLenient(false);
	}

	public static final boolean isBackupDir(File backup) {
		try {
			sdf.parse(backup.getName());
			return true;
		} catch (java.text.ParseException e) {
			return false;
		}
	}

	// 全备份，删除旧日志。
	final void backupFull() throws Throwable {
		XdbConf conf = Xdb.getInstance().getConf();
		//File tableHome = conf.getTableHome();

		String backupName = sdf.format(Calendar.getInstance().getTime());
		File backupDir = new File(conf.getBackupDir(), backupName);

		Trace.warn("backup FULL begin");

		// tables: copy data file
		File dataDir = new File(backupDir, XdbConf.DATADIR);
		dataDir.mkdirs();
		for (Storage storage : storages)
			copy(storage.getFile(), dataDir);

		// logger: removeOlder and copy
		File logsDir = new File(backupDir, XdbConf.LOGSDIR);
		logsDir.mkdirs();
		logger.removeOlder();
		for (File log : Logger.listFiles())
			copy(log, logsDir);

		new FileOutputStream(new File(backupDir, "database.backup.done")).close();
		Trace.warn("backup FULL end");
	}

	public static SortedMap<String, File> backups() {
		return backups(Xdb.getInstance().getConf().getBackupDir());
	}

	public static SortedMap<String, File> backups(File home) {
		SortedMap<String, File> sorted = new TreeMap<String, File>();
		File [] files = home.listFiles();
		if (null != files) {
			for (File file : files) {
				if (file.isDirectory() && isBackupDir(file))
					sorted.put(file.getName(), file);
			}
		}
		return sorted;
	}

	// 增量备份
	final void backupInc() throws Throwable {
		Trace.warn("backup INCREMENT begin");

		SortedMap<String, File> backups = backups();
		if (backups.isEmpty()) {
			Trace.warn("backup INCREMENT end. NO FULL BACKUP FOUND.");
			return;
		}
		File lastBackupLogsDir = new File(backups.get(backups.lastKey()), XdbConf.LOGSDIR);
		SortedMap<String, File> lastBackupLogs = Logger.sortedFiles(lastBackupLogsDir);
		if (Trace.isDebugEnabled())
			xdb.Trace.debug(lastBackupLogs.values());
		for (File log : Logger.listFiles()) {
			if (false == lastBackupLogs.containsKey(log.getName()))
				copy(log, lastBackupLogsDir);
		}
		// 全备份时，肯定拷贝了一个日志？也就是说，lastBackupLogs肯定不为空？
		// 最后一次备份过去的日志需要重新拷贝。
		if (!lastBackupLogs.isEmpty())
			copy(lastBackupLogs.get(lastBackupLogs.lastKey()), lastBackupLogsDir);

		Trace.warn("backup INCREMENT end");
	}
}

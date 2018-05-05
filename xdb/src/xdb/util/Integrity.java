package xdb.util;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * <p>Xdb 正在进行checkpoint时，程序异常停止（包括机器异常关机），数据有可能处于不一致状态。
 * 在继续使用数据库时，需要修复。xdb会自动检测和尝试修复不一致状态，对于xdb应用不需要关心这个问题。
 * 如果在修复之前使用Dbx或者其他工具去访问数据，就会造成数据损坏。
 * 
 * <p><b>这个工具类用来修复这种不一致状态。</b> Dbx 在打开时，会使用它检查和修复数据状态。
 * 
 * <p>本类可以独立使用。
 */
public class Integrity {
	private final File logHome;
	private final File tableHome;

	private xdb.Logger logger;
	private List<xdb.Storage> tables = new ArrayList<xdb.Storage>();
	private DatabaseMetaData metaData = null;

	/**
	 * 根据指定数据库目录验证数据完整性。
	 * 
	 * @param libpath 动态库所在路径。动态库已经在外面装在，设为null。
	 * @param home 数据库目录
	 */
	public Integrity(String libpath, File home) {
		this(libpath, home, null);
	}

	/**
	 * 根据指定数据库目录验证数据完整性。
	 * 同时验证DatabaseMetaData包含的表格和数据目录中包含的表格文件一致。
	 * 
	 * <p> <b>只有在确定表格没有增删时，才需要验证DatabaseMetaData。</b>
	 * 
	 * @param libpath 动态库所在路径。动态库已经在外面装在，设为null。
	 * @param home
	 * @param metaData 为null时，不验证DatabaseMetaData的信息。
	 */
	public Integrity(String libpath, File home, DatabaseMetaData metaData) {
		if (null != libpath)
			Dbx.load(libpath);

		if (!home.isDirectory() || !home.exists())
			throw new IllegalStateException("dbhome : " + home + " (!isDirectory || !exists())");

		this.logHome = new File(home, xdb.XdbConf.LOGSDIR).getAbsoluteFile();
		this.tableHome = new File(home, xdb.XdbConf.DATADIR);
		this.metaData = metaData;
	}

	/**
	 * 因为 Storage 被声明为 abstract，无法直接使用，虽然它实际上不需要这么声明。
	 * 这个类继承Storage，使得实例化可以进行，并不增加实现和增加任何功能。
	 * @see xdb.Storage
	 */
	private class Table extends xdb.Storage {
		Table(xdb.Logger logger, File tableHome, String tableName, int cacheHigh, int cacheLow) {
			super(logger, tableHome, tableName, cacheHigh, cacheLow);
		}
	}

	/**
	 * 比较metadata信息
	 * @return true,没有找到metadata.xml或检查后相同。false,不相同
	 */
	public boolean checkMetaData(){	
		return metaData.isSame(this.tableHome.getParentFile());		
	}
	/**
	 * 完整性检查和修复。
	 */
	public void verify() {
		////////////////////////////////////////////////////////////////
		// 检测数据库目录
		int magic = 0;
		if (this.logHome.exists())   magic += 1;
		if (this.tableHome.exists()) magic += 2;

		switch (magic) {
		// 数据和日志目录都不存在。一般来说，这是空的数据库，认为是正确的。
		case 0: println("ok! it is an empty db."); return;
		// 不是正确的数据目录。
		case 1: case 2: throw new RuntimeException("is it a dbhome?");
		// 好吧，两个目录都在。 
		case 3: break;
		}

		////////////////////////////////////////////////////////////////
		// 验证MetaData信息和目录中包含的表格文件一致。
		final Set<String> tableNames = new HashSet<String>();
		for (File tableFile : this.tableHome.listFiles()) {
			if (!tableFile.isFile()) {
				println(tableFile + " is not a file! skip.");
				continue;
			}
			tableNames.add(tableFile.getName());
		}
		if (null != this.metaData) {
			final Set<String> tableMetas = new HashSet<String>();
			for (DatabaseMetaData.Table table : metaData.getTables()) {
				if (table.isPersistence()) // 忽略内存表
					tableMetas.add(table.getName());
			}
			if (!tableNames.equals(tableMetas))
				throw new RuntimeException("DatabaseMetaData?");
		}

		////////////////////////////////////////////////////////////////
		// 完整性验证。
		final String logdir = this.logHome.toString() + File.separator; // db.h need a separator
		try {
			// 这个流程在本质上与  xdb.Tables.open(XdbConf conf) 一致。
			// 一共尝试两次，如果发生了数据修复，需要关闭再重新打开所有表格。
			for (int i = 0; i < 2; ++i) {
				logger = new xdb.Logger(logdir, 4096);
				for (String tableName : tableNames) {
					tables.add(new Table(logger, this.tableHome, tableName, 512, 128));
				}
				int v = logger.verify();
				if (0 == v) {
					println("verify success!");
					break;
				}
				if (1 == v && 0 == i) {
					println("redo success! verify again.");
					close();
					continue;
				}
				throw new RuntimeException("db corrupt @" + i);
			}
		} finally {
			close(); // 不管什么情况都关闭日志和表格。
		}
	}

	private void close() {
		for (xdb.Storage storage : tables) {
			//println(storage.getFile());
			storage.close();
		}
		tables.clear();

		if (null != logger) {
			logger.close();
			logger = null;
		}
	}

	private void println(Object message) {
		System.out.println(message);
	}

	public static void main(String args[]) {
		new Integrity("../test", new File("../test/xdb")).verify();
	}
}

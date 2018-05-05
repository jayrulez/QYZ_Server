package xdb;

import javax.xml.parsers.*;
import org.w3c.dom.*;

import xdb.util.ConcurrentCacheConf;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

public final class XdbConf {
	public static final String DATADIR = "dbdata";
	public static final String LOGSDIR = "dblogs";

	public XdbConf(int localid, String xdbxml) {
	    this.localid = localid;
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xdbxml);
			parse(doc.getDocumentElement());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getString(Element self, String name, String def) {
		String s = self.getAttribute(name);
		return s.isEmpty() ? def : s;
	}

	public static int getInt(Element self, String name, int def) {
		String s = self.getAttribute(name);
		return s.isEmpty() ? def : Integer.parseInt(s);
	}

	public static boolean getBoolean(Element self, String name, boolean def) {
		String s = self.getAttribute(name);
		return s.isEmpty() ? def : Boolean.parseBoolean(s);
	}

	private static void deprecatedAttribute(Element self, String name) {
		if (false == self.getAttribute(name).isEmpty())
			System.out.println("Deprecated attribute \"" + name
					+ "\" in node \"" + self.getNodeName() + "\"");
	}

	private void parse(Element self) throws Exception {
		String _dbhome = self.getAttribute("dbhome");
		dbhome = new File(_dbhome);
		if (_dbhome.isEmpty() || (!dbhome.exists() && !dbhome.mkdirs()))
			throw new XError("dbhome : " + dbhome + " (isEmpty || !isDirectory");

		tablehome = new File(dbhome, DATADIR).getAbsoluteFile();
		loghome = new File(dbhome, LOGSDIR).getAbsoluteFile();

		logpages = Integer.parseInt(self.getAttribute("logpages"));
		trace = Trace.valueOf(self.getAttribute("trace").toUpperCase());
		traceTo = self.getAttribute("traceTo");
		traceRotateHourOfDay = getInt(self, "traceRotateHourOfDay", 6);
		traceRotateMinute = getInt(self, "traceRotateMinute", 30);

		corePoolSize = Integer.parseInt(self.getAttribute("corePoolSize"));
		procPoolSize = getInt(self, "procPoolSize", corePoolSize);
		schedPoolSize = getInt(self, "schedPoolSize", corePoolSize / 11 + 1);
		timeoutPeriod = getInt(self, "timeoutPeriod", 2000);

		String _backupDir = self.getAttribute("backupDir");
		backupDir = new File(_backupDir);
		if (_backupDir.isEmpty() || (!backupDir.exists() && !backupDir.mkdirs()))
			throw new XError("backupDir : " + backupDir + " (!isDirectory || isEmpty)");
		backupFullPeriod = Integer.parseInt(self.getAttribute("backupFullPeriod"));
		backupIncPeriod = Integer.parseInt(self.getAttribute("backupIncPeriod"));
		backupDelay = getInt(self, "backupDelay", 0);
		allowBackup = getBoolean(self, "allowBackup", true);

		deprecatedAttribute(self, "flushFatalTime");
		deprecatedAttribute(self, "flushPeriod");
		snapshotFatalTime = getInt(self, "snapshotFatalTime", 2000);
		marshalPeriod = getInt(self, "marshalPeriod", -1);
		marshalN = getInt(self, "marshalN", 1);
		checkpointPeriod = Integer.parseInt(self.getAttribute("checkpointPeriod"));
		allowCheckpointXXX = getBoolean(self, "allowCheckpointXXX", true);
		angelPeriod = Integer.parseInt(self.getAttribute("angelPeriod"));
		xdbVerify = getBoolean(self, "xdbVerify", false);

		serialKey = getInt(self, "serialKey", 1024);

		defaultTableCache = self.getAttribute("defaultTableCache").trim();

		NodeList childnodes = self.getChildNodes();
		for (int i = 0; i < childnodes.getLength(); ++i) {
			Node node = childnodes.item(i);
			if (Node.ELEMENT_NODE != node.getNodeType())
				continue;

			Element e = (Element) node;
			String nodename = e.getNodeName();
			if (nodename.equals("table") || nodename.equals("TableSysConf")) {
				TableConf conf = new TableConf(e);
				tableconf.put(conf.getName(), conf);
			} else if (nodename.equals("ProcedureConf")) {
				procedureConf = new ProcedureConf(e);
			} else if (nodename.equals("define")) {
				xgen.Define.getInstance().parse(e);
			} else if (nodename.equals("cache")) {
				ConcurrentCacheConf conf = new ConcurrentCacheConf(e);
				cacheconf.put(conf.getName(), conf);
			}
		}
		// default
		if ( null == this.procedureConf )
			this.procedureConf = new ProcedureConf();
	}

	public String getDefaultTableCache() {
		return defaultTableCache;
	}


	private final int localid;

	private String defaultTableCache;
	private int  serialKey;
	private int  logpages;
	private volatile File dbhome;
	private volatile File loghome;
	private volatile File tablehome;
	private volatile Trace trace;
	private String traceTo;
	private int traceRotateHourOfDay;
	private int traceRotateMinute;
	private boolean xdbVerify;

	private int corePoolSize;
	private int procPoolSize;
	private int schedPoolSize;
	private volatile int timeoutPeriod;

	private volatile long snapshotFatalTime;
	private volatile int marshalPeriod;
	private volatile int marshalN;

	private boolean allowCheckpointXXX; // 危险参数，仅用于测试。
	private volatile int checkpointPeriod;
	private int angelPeriod;

	private volatile File backupDir;
	private int backupFullPeriod;
	private int backupIncPeriod;
	private volatile int backupDelay;
	private volatile boolean allowBackup;

	private ProcedureConf procedureConf;
	private Map<String, TableConf> tableconf = new HashMap<String, TableConf>();
	private Map<String, ConcurrentCacheConf> cacheconf = new HashMap<String, ConcurrentCacheConf>();


    public int getLocalid() {
        return localid;
    }

    public ConcurrentCacheConf getConcurrentCacheConf(String cache) {
		return cacheconf.get(cache);
	}

	public int getTimeoutPeriod() {
		return timeoutPeriod;
	}

	/**
	 * 必须在启动前设置有效。
	 * @param timeoutPeriod
	 */
	public synchronized void setTimeoutPeriod(int timeoutPeriod) {
		this.timeoutPeriod = timeoutPeriod;
	}

	public int getSerialKey() {
		return serialKey;
	}

	public long getSnapshotFatalTime() {
		return snapshotFatalTime;
	}

	/**
	 * 当 snapshot 的时间超过此配置值时，记录 fatal 级别的日志。
	 * @param snapshotFatalTime
	 */
	public synchronized void setSnapshotFatalTime(long snapshotFatalTime) {
		this.snapshotFatalTime = snapshotFatalTime;
	}

	public boolean isXdbVerify() {
		return xdbVerify;
	}

	boolean isAllowCheckpointXXX() {
		return allowCheckpointXXX;
	}

	public boolean isAllowBackup() {
		return allowBackup;
	}

	/*
	 * 是否允许备份。
	 * 启动前设置有效。
	 * 启动以后使用 CheckpointMBean 进行修改。
	 */
	public synchronized void setAllowBackup(boolean allowBackup) {
		this.allowBackup = allowBackup;
	}

	/**
	 * 设置 checkpoint 定时间隔。
	 * <p> 可以在运行时修改。修改的间隔在完成一次checkpoint以后生效。
	 * @param checkpointPeriod
	 */
	public synchronized void setCheckpointPeriod(int checkpointPeriod) {
		if (checkpointPeriod < 0)
			throw new IllegalArgumentException();
		this.checkpointPeriod = checkpointPeriod;
	}

	public int getTraceRotateHourOfDay() {
		return traceRotateHourOfDay;
	}

	public int getTraceRotateMinute() {
		return traceRotateMinute;
	}

	public int getSchedPoolSize() {
		return schedPoolSize;
	}

	public String getTraceTo() {
		return traceTo;
	}

	public int getBackupIncPeriod() {
		return backupIncPeriod;
	}

	public File getBackupDir() {
		return backupDir;
	}

	public int getBackupDelay() {
		return backupDelay;
	}

	public int getAngelPeriod() {
		return angelPeriod;
	}

	public ProcedureConf getProcedureConf() {
		return procedureConf;
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public int getProcPoolSize() {
		return procPoolSize;
	}

	public final int getMarshalN() {
		return marshalN;
	}

	/**
	 * flush锁之前执行并发 marshalN 的次数。默认为 1。
	 * <b>
	 * 警告: 配置小于 1 时，记录系列化在 marshal0 中完成，这将加大flush锁定的时间。
	 * <b>
	 * 运行时修改有效。
	 * @see xdb.Checkpoint
	 */
	public synchronized void setMarshalN(int marshalN) {
		this.marshalN = marshalN;
	}

	public int getMarshalPeriod() {
		return marshalPeriod;
	}

	/**
	 * 自动 marshal 的时间间隔。
	 * 用于测试。运行时修改有效。
	 * <=0 关闭自动 Marshal。
	 */
	public synchronized void setMarshalPeriod(int marshalPeriod) {
		this.marshalPeriod = marshalPeriod;
	}

	private volatile boolean autoMarshal = true;
	public boolean isAutoMarshal() {
		return this.autoMarshal;
	}

	/**
	 *  for debug.
	 */
	public synchronized void setAutoMarshal(boolean flush) {
		this.autoMarshal = flush;
	}

	public int getCheckpointPeriod() {
		return checkpointPeriod;
	}

	public int getBackupFullPeriod() {
		return backupFullPeriod;
	}

	public Map<String, TableConf> getTableConfs() {
		return tableconf;
	}

	public TableConf getTableConf(String name) {
		return tableconf.get(name);
	}

	public File getDbHome() {
		return dbhome;
	}

	public File getTableHome() {
		return tablehome;
	}

	public File getLogHome() {
		return loghome;
	}

	public int getLogPages() {
		return logpages;
	}

	public Trace getTrace() {
		return trace;
	}

	/**
	 * 必须要在启动xdb前设置，启动修改无效。
	 */
	public synchronized void setDbhome(File dbhome) {
		if (!dbhome.isDirectory() || !dbhome.exists())
			throw new XError("dbhome : " + dbhome + " (!isDirectory || !exists())");
		this.dbhome = dbhome;
		tablehome = new File(dbhome, DATADIR).getAbsoluteFile();
		loghome = new File(dbhome, LOGSDIR).getAbsoluteFile();
		if (!tablehome.isDirectory() || !tablehome.exists())
			throw new XError("tablehome : " + tablehome + " (!isDirectory || !exists())");
		if (!loghome.isDirectory() || !loghome.exists())
			throw new XError("loghome : " + loghome + " (!isDirectory || !exists())");
	}

	/**
	 * 必须在启动xdb前设置，启动以后不再修改。
	 */
	public synchronized void setBackupDir(File backupDir) {
		if (!backupDir.isDirectory() || !backupDir.exists())
			throw new XError("backupDir : " + backupDir + " (!isDirectory || !exists())");
		this.backupDir = backupDir;
	}

	private volatile File libdbPath = null;

	public File getLibdbPath() {
		return libdbPath;
	}

	/**
	 * 设置libdb库的路径。路径必须存在。
	 */
	public synchronized void setLibdbPath(File libpath) {
		if ( !libpath.isDirectory() || !libpath.exists())
			throw new XError("libpath : " + libpath + " (!isDirectory || !exists())");
		this.libdbPath = libpath;
	}

	/**
	 * 设置备份延迟。
	 * 在xdb启动之前设置，启动之后的这段延迟时间内，xdb不会进行备份。
	 * 启动之后设置将无效
	 * @param backupDelay
	 */
	public synchronized void setBackupDelay(int backupDelay) {
		this.backupDelay = backupDelay;
	}
}

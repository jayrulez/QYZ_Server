package xdb;

/**
 * 
 * 表内部接口定义。
 * 
 * 为了充当Record的parent，所以继承了 XBean。
 * 
 * @author lichenghua
 * 
 */
public abstract class Table extends XBean {
	public static enum Persistence {
		MEMORY, DB
	}

	Table() {
		super(null, null);
	}

	abstract Storage open(XdbConf xconf, Logger logger);

	abstract void close();

	public abstract String getName();
	public abstract Persistence getPersistence();

	// 系统表不用实现。
	// 用户表需要(TTable)。
	protected void initialize(Tables tables) {
	}

	// 用户表需要(TTable)。
	void logNotify() {
	}
}

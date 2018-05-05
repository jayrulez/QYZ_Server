package xdb;

import org.w3c.dom.*;

public class ProcedureConf {

	ProcedureConf(Element self) {
		maxExecutionTime = XdbConf.getInt(self, "maxExecutionTime", 0);
		executionTime = XdbConf.getInt(self, "executionTime", 1000);
		retryTimes = XdbConf.getInt(self, "retryTimes", 3);
		retryDelay = XdbConf.getInt(self, "retryDelay", 100);
		trace = Trace.valueOf(XdbConf.getString(self, "trace", "WARN").toUpperCase());
	}

	/**
	 * 构造默认的存储过程配置。
	 */
	public ProcedureConf() {
		this(0, 1000, 3, 100);
	}

	public ProcedureConf(int maxExecutionTime, int executionTime, int retryTimes, int retryDelay) {
		this.maxExecutionTime = maxExecutionTime;
		this.executionTime = executionTime;
		this.retryTimes = retryTimes;
		this.retryDelay = retryDelay;
		this.trace = Trace.WARN;
	}

	private volatile int executionTime;
	private volatile int retryTimes;
	private volatile int retryDelay;
	private volatile int maxExecutionTime;
	private volatile Trace trace;

	public Trace getTrace() {
		return trace;
	}

	public synchronized void setTrace(Trace trace) {
		this.trace = trace;
	}

	public int getMaxExecutionTime() {
		return maxExecutionTime;
	}

	public synchronized void setMaxExecutionTime(int maxExecutionTime) {
		this.maxExecutionTime = maxExecutionTime;
	}

	public int getExecutionTime() {
		return executionTime;
	}

	public synchronized void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	public int getRetryDelay() {
		return retryDelay;
	}

	public synchronized void setRetryDelay(int retryDelay) {
		this.retryDelay = retryDelay;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	public synchronized void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}
}

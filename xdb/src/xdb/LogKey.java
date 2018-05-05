package xdb;

public class LogKey implements Comparable<LogKey> {
	private final XBean xbean;
	private final String varname;

	public LogKey(XBean xbean, String varname) {
		this.xbean = xbean;
		this.varname = varname;
	}

	protected Log create() {
		return null;
	}

	public final XBean getXBean() {
		return xbean;
	}

	public final String getVarname() {
		return varname;
	}

	@Override
	public final int hashCode() {
		return xbean.xdbObjId().hashCode() ^ varname.hashCode();
	}

	@Override
	public final boolean equals(Object another) {
		if (another instanceof LogKey) {
			LogKey lk = (LogKey)another;
			return xbean.xdbObjId().equals(lk.xbean.xdbObjId()) && varname.equals(lk.varname);
		}
		return false;
	}

	@Override
	public int compareTo(LogKey o) {
		long d = xbean.xdbObjId() - o.xbean.xdbObjId();
		if (d > 0) return 1;
		if (d < 0) return -1;
		return varname.compareTo(o.varname);
	}

	@Override
	public final String toString() {
		return xbean.getClass().getSimpleName() + "." + varname; 
	}
}

package xdb.logs;

import xdb.Log;
import xdb.LogKey;

public abstract class LogInt extends Note implements Log {
	protected LogKey logkey;
	protected int _xdb_saved;

	protected LogInt(LogKey logkey, int saved) {
		this.logkey = logkey;
		this._xdb_saved = saved;
	}

	@Override
	public void commit() {
		xdb.Logs.logNotify(logkey.getXBean(), new LogNotify(logkey, this));
	}

	@Override
	public String toString() {
		return String.valueOf(this._xdb_saved);
	}
}

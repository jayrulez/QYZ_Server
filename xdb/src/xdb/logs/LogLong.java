package xdb.logs;

import xdb.Log;
import xdb.LogKey;

public abstract class LogLong extends Note implements Log {
	protected LogKey logkey;
	protected long _xdb_saved;

	protected LogLong(LogKey logkey, long saved) {
		this.logkey = logkey;
		this._xdb_saved = saved;
	}

	@Override
	public void commit() {
		xdb.Logs.logNotify(logkey.getXBean(), new LogNotify(logkey, this));
	}
}

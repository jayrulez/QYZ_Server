package xdb.logs;

import xdb.Log;
import xdb.LogKey;

public abstract class LogObject<T> extends Note implements Log {
	protected LogKey logkey;
	protected T _xdb_saved;

	protected LogObject(LogKey logkey, T saved) {
		this.logkey = logkey;
		this._xdb_saved = saved;
	}

	@Override
	public void commit() {
		xdb.Logs.logNotify(logkey.getXBean(), new LogNotify(logkey, this));
	}
}

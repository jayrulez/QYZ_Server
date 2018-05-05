package xdb.logs;

import xdb.Log;
import xdb.LogKey;

public abstract class LogShort extends Note implements Log {
	protected LogKey logkey;
	protected short _xdb_saved;

	protected LogShort(LogKey logkey, short saved) {
		this.logkey = logkey;
		this._xdb_saved = saved;
	}

	@Override
	public void commit() {
		xdb.Logs.logNotify(logkey.getXBean(), new LogNotify(logkey, this));
	}
}

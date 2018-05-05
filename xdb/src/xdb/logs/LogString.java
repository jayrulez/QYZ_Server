package xdb.logs;

import xdb.Log;
import xdb.LogKey;

public abstract class LogString extends Note implements Log {
	protected LogKey logkey;
	protected String _xdb_saved;

	protected LogString(LogKey logkey, String saved) {
		this.logkey = logkey;
		this._xdb_saved = saved;
	}

	@Override
	public void commit() {
		xdb.Logs.logNotify(logkey.getXBean(), new LogNotify(logkey, this));
	}
}

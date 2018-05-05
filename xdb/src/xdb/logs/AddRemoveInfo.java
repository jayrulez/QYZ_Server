package xdb.logs;

import xdb.TRecord.State;

public final class AddRemoveInfo {
	private boolean _isCreateCache;
	private State _savedState;
	
	public AddRemoveInfo(boolean isCreateCache, State savedState) {
		_isCreateCache = isCreateCache;
		_savedState = savedState;
	}
	
	public boolean isCreateCache() {
		return _isCreateCache;
	}
	
	public State getSavedState() {
		return _savedState;
	}
}

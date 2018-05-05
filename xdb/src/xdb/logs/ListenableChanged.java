package xdb.logs;

/**
 * 只记录是否发生了变化，不记录变化的详细信息。
 */
public final class ListenableChanged extends Listenable {
	private boolean changed = false;

	@Override
	public void setChanged(LogNotify ln) {
		this.changed = true;
		// 现在很多类型都只记录是否变更，ln 可能来自更深的层次。
	}
	
	@Override
	public ListenableChanged copy() {
		ListenableChanged l = new ListenableChanged();
		l.fullVarName = this.fullVarName;
		return l;
	}

	@Override
	public void logNotify(Object key, RecordState rs, ListenerMap listenerMap) {
		switch (rs) {
		case ADDED:
			listenerMap.notifyChanged(fullVarName, key);
			break;

		case REMOVED:
			listenerMap.notifyRemoved(fullVarName, key);
			break;

		case CHANGED:
			if (changed)
				listenerMap.notifyChanged(fullVarName, key, null);
			break;
		
		default:
			break;
		}
		changed = false;
	}
}

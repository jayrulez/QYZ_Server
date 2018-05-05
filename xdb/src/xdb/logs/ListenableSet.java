package xdb.logs;

public final class ListenableSet extends Listenable {
	private Note note;

	@Override
	@SuppressWarnings("rawtypes")
	public void setChanged(LogNotify ln) {
		// 因为不能区分是在加入Set前还是加入Set后进行bean的修改操作，所以不严格限制Set中的bean不能修改了
		// 但是在使用的时候一定要注意，不能对加入Set的bean进行修改，因为Set依赖于bean的hashCode
		if (!ln.isLast()) {
			//throw new IllegalStateException();
			return;
		}
		
		if (null == note) {
			note = ln.getNote();
		} else {
			((NoteSet)note).merge(ln.getNote()); // unchecked
		}
	}
	
	@Override
	public ListenableSet copy() {
		ListenableSet l = new ListenableSet();
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
			if (null != note)
				listenerMap.notifyChanged(fullVarName, key, note);
			break;

		default:
			break;
		}
		note = null;
	}
}


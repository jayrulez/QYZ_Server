package xdb.logs;

import java.util.ArrayList;
import java.util.List;

public final class ListenableMap extends Listenable {
	private Note note;
	private List<Object> changed;

	@Override
	@SuppressWarnings("rawtypes")
	public void setChanged(LogNotify ln) {
		if (ln.isLast()) {
			// map本身的log。
			if (null == note) {
				note = ln.getNote();
			} else {
				((NoteMap)note).merge(ln.getNote()); // uncheck
			}
		} else {
			// map 的 value 发生了修改。
			if (null == changed)
				changed = new ArrayList<Object>();
			// map value 的改变
			xdb.LogKey logkey = ln.pop();
			changed.add(logkey.getXBean()); // unchecked
		}
	}

	@Override
	public ListenableMap copy() {
		ListenableMap l = new ListenableMap();
		l.fullVarName = this.fullVarName;
		return l;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void logNotify(Object key, RecordState rs, ListenerMap listenerMap) {
		switch (rs) {
		case ADDED:
			listenerMap.notifyChanged(fullVarName, key);
			break;

		case REMOVED:
			listenerMap.notifyRemoved(fullVarName, key);
			break;

		case CHANGED:
			if ((null != note || null != changed) && listenerMap.hasListener(fullVarName)) {
				NoteMap nMap = (null != note) ? (NoteMap)note : new NoteMap();  // unchecked
				nMap.setChanged((null != changed) ? changed : new ArrayList()); // unchecked
				listenerMap.notifyChanged(fullVarName, key, nMap);
			}
			break;

		default:
			break;
		}
		note = null;
		changed = null;
	}
}


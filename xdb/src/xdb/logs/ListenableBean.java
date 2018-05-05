package xdb.logs;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class ListenableBean extends Listenable {
	private boolean changed = false;
	private Map<String, Listenable> vars = new HashMap<String, Listenable>();

	public void add(Listenable la) {
		if (null != vars.put(la.getVarName(), la))
			throw new IllegalStateException();
	}

	@Override
	public String toFullVarName(VarNames names) {
		if (names.hasNext())
			return vars.get(names.next()).toFullVarName(names);
		return super.fullVarName;
	}

	@Override
	public void makeFullVarName(String base) {
		super.makeFullVarName(base);
		for (Listenable la : vars.values())
			la.makeFullVarName(super.fullVarName);
	}
	
	@Override
	public ListenableBean copy() {
		ListenableBean l = new ListenableBean();
		l.fullVarName = this.fullVarName;
		for (Entry<String, Listenable> e : vars.entrySet()) {
			l.vars.put(e.getKey(), e.getValue().copy());
		}
		return l;
	}

	@Override
	public void setChanged(LogNotify ln) {
		this.changed = true;
		// ln 此时肯定不是空的，bean的所有改变都来自于vars，本身是没有修改操作的。
		vars.get(ln.pop().getVarname()).setChanged(ln);
	}

	@Override
	public void logNotify(Object key, RecordState rs, ListenerMap listenerMap) {
		switch (rs) {
		case NONE:
			// 新加NONE 不需要处理
			return;
		case ADDED:
			listenerMap.notifyChanged(fullVarName, key);
			break;

		case REMOVED:
			listenerMap.notifyRemoved(fullVarName, key);
			break;

		case CHANGED:
			if (changed) {
				listenerMap.notifyChanged(fullVarName, key, null);
			} else
				return; // 优化，如果当前bean没有修改，变量肯定也没有。see setChanged
			break;
		}
		changed = false;
		for (Listenable la : vars.values())
			la.logNotify(key, rs, listenerMap);
	}
}


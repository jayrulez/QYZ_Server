package xio;

import java.util.*;

/**
 * 根据创建者的名字管理连接。
 * 每个创建者只允许创建一个连接。
 */
public class ManagerByCreator extends Manager {
	private Map<Creator, Xio> xios = new HashMap<Creator, Xio>();
	private Object mutex = new Object();

	protected void onXioRemoved(Xio x, Throwable e) {
		xdb.Trace.warn("onXioRemoved=" + x, e);
	}

	protected void onXioAdded(Xio x) {
		if (xdb.Trace.isWarnEnabled())
			xdb.Trace.warn("onXioAdded=" + x);
	}

	@Override
	protected void addXio(Xio xio) {
		boolean added = false;
		synchronized (mutex) {
			if (xios.containsKey(xio.getCreator())) {
				xdb.Trace.warn("Close(duplicate creator) in addXio " + xio);
				xio.close();
			} else {
				xios.put(xio.getCreator(), xio);
				added = true;
			}
		}
		if (added)
			try { onXioAdded(xio); } catch (Throwable e) { /* skip */ }
	}

	@Override
	public Xio get() {
		synchronized (mutex) {
			return xios.values().iterator().next();
		}
	}

	@Override
	protected void removeXio(Xio xio, Throwable e) {
		boolean removed = false;
		synchronized (mutex) {
			if (xios.get(xio.getCreator()) == xio) {
				xios.remove(xio.getCreator());
				removed = true;
			}
		}
		if (removed)
			try { onXioRemoved(xio, e); } catch (Throwable e2) { /* skip */ }
	}

	@Override
	public int size() {
		synchronized (mutex) {
			return xios.size();
		}
	}

	public List<Xio> getXios(){
		synchronized (mutex) {
			return new ArrayList<>(xios.values());
		}
	}

	@Override
	protected void close() {
		super.close();
		Map<Creator, Xio> tmp;
		synchronized (mutex) {
			tmp = xios;
			xios = new HashMap<Creator, Xio>();
		}
		for (Xio io : tmp.values())
			io.close();
	}

	public Xio get(Creator creator) {
		synchronized (mutex) {
			return xios.get(creator);
		}		
	}
}

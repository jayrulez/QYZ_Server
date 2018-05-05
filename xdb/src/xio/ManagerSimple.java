package xio;

import java.util.HashSet;
import java.util.Set;

/**
 * 把所有的连接放在一个集合中管理。可以作为一个基本实现的基类来用。包含以下功能：
 * <ol>
 * <li>程序关闭时，关闭所有连接。
 * <li>增加额外的回调 onXioRemoved onXioAdd 用于继承。
 * </ol>
 */
public class ManagerSimple extends Manager {
	private Set<Xio> xios = new HashSet<Xio>();
	private Object mutex = new Object();

	protected void onXioRemoved(Xio x, Throwable e) {
		xdb.Trace.warn("onXioRemoved=" + x, e);
	}

	protected void onXioAdded(Xio x) {
		if (xdb.Trace.isWarnEnabled())
			xdb.Trace.warn("onXioAdded=" + x);
	}

	@Override
	protected void removeXio(Xio xio, Throwable e) {
		boolean removed = false;
		synchronized (mutex) {
			removed = xios.remove(xio);
		}
		if (removed)
			try { onXioRemoved(xio, e); } catch (Throwable e2) { /* skip */ }
	}

	@Override
	protected void addXio(Xio xio) {
		boolean added = false;
		if (Engine.isOpen()) {
			synchronized (mutex) {
				added = (super.getMaxSize() <= 0 || xios.size() < super.getMaxSize()) && xios.add(xio);
			}
		}
		if (added)
			try { onXioAdded(xio); } catch (Throwable e) { /* skip */ }
		else {
			xdb.Trace.warn("Close in addXio " + xio + " size=" + size() + "/" + getMaxSize());
			xio.close();
		}
	}

	@Override
	protected void close() {
		super.close();

		// 对于大量连接的应用，不关闭连接更好。目前尽量干净的退出。
		Set<Xio> tmp;
		synchronized (mutex) {
			tmp = xios;
			xios = new HashSet<Xio>();
		}
		for (Xio io : tmp)
			io.close();
	}

	@Override
	public int size() {
		synchronized (mutex) {
			return xios.size();
		}
	}

	@Override
	public Xio get() {
		synchronized (mutex) {
			if (xios.isEmpty())
				return null;
			return xios.iterator().next();
		}
	}

//	// poll 得不到连接，就等待
//	public Xio take() {
//		synchronized (xios) {
//			while (xios.isEmpty()) {
//				try {
//					xios.wait();
//				} catch (InterruptedException e) {
//					throw new RuntimeException(e);
//				}
//			}
//			Xio x = xios.iterator().next();
//			xios.remove(x);
//			return x;
//		}
//	}
//
//	public void put(Xio xio) {
//		boolean added = false;
//		if (Engine.isOpen()) {
//			synchronized (xios) {
//				added = xios.add(xio);
//			}
//		}
//		if (!added)
//			xio.close();
//	}

}

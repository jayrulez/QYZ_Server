package xauany;

import java.util.HashMap;
import java.util.Map;

import xio.Engine;
import xio.Manager;
import xio.Protocol;
import xio.Xio;
import xio.security.ARCFourSecurity;
import xio.security.SecurityFilter;

public class XioManager extends Manager {
	static public class XioInfo {
		private int serverid = Config.SERVER_ID_NONE;
		private int plattype = Config.PLAT_TYPE_NONE;

		public int getServerid() {
			return serverid;
		}
		public int getPlattype() {
			return plattype;
		}
	}
	
	private Map<Integer, Xio> serverid2xio = new HashMap<Integer, Xio>();
	private Map<Xio, XioInfo> xio2info = new HashMap<Xio, XioInfo>();
	private Object mutex = new Object();

	private static XioManager instance = null;
	public static XioManager getInstance() {
		return instance;
	}
	
	public XioManager() {
		instance = this;
	}

	@Override
	public void execute(Protocol p) {
		if( xdb.Trace.isDebugEnabled())
			xdb.Trace.debug( "execute" + p);
		super.execute(p);
	}

	@Override
	protected void removeXio(Xio xio, Throwable e) {
		synchronized (mutex) {
			final XioInfo info = xio2info.remove( xio);
			if( null != info)
				xdb.Trace.info("Remove Xio : " + info.getServerid(), e);
			if( null != info && info.serverid != Config.SERVER_ID_NONE)
				serverid2xio.remove(info.serverid);
		}
	}

	@Override
	protected void addXio(Xio xio) {
		boolean added = false;
		if (Engine.isOpen()) {
			synchronized (mutex) {
				added = (super.getMaxSize() <= 0 || xio2info.size() < super.getMaxSize()) && null == xio2info.put(xio, new XioInfo());
			}
		}
		if( !added) {
			xdb.Trace.info("Close in addXio " + xio + " size=" + size() + "/" + getMaxSize());
			xio.close();
		} else {
			xdb.Trace.info("Add Xio : " + xio.toString());
			SecurityFilter.setXioOutputSecurity( xio, new ARCFourSecurity(), Config.getInstance().getXioOutputSecurity());
			SecurityFilter.setXioInputSecurity( xio, new ARCFourSecurity(), Config.getInstance().getXioInputSecurity());
		}
	}

	@Override
	protected void close() {
		super.close();

		// 对于大量连接的应用，不关闭连接更好。目前尽量干净的退出。
		Map<Xio,XioInfo> tmp;
		synchronized (mutex) {
			tmp = xio2info;
			xio2info = new HashMap<Xio,XioInfo>();
			serverid2xio.clear();
		}
		for(Map.Entry<Xio, XioInfo> e: tmp.entrySet())
			e.getKey().close();
	}
	
	@Override
	public int size() {
		synchronized (mutex) {
			return xio2info.size();
		}
	}

	@Override
	public Xio get() {
		synchronized (mutex) {
			if (xio2info.isEmpty())
				return null;
			return xio2info.keySet().iterator().next();
		}
	}
	
	
	/**
	 * 注册gs 注册gs 实际是xdeliver请求的注册 (gs --> xdeliver --> xauany)
	 * @param xio
	 * @param serverid
	 * @param plattype
	 */
	public void registerGs(Xio xio, int serverid, int plattype) {
		if(serverid == Config.SERVER_ID_NONE ){
			throw new IllegalArgumentException("Illegal serverid " + serverid);
		}
		if(plattype == Config.PLAT_TYPE_NONE){
			throw new IllegalArgumentException("Illegal plattype " + plattype);
		}
		synchronized (mutex) {
			XioInfo info = xio2info.get(xio);
			if(null != info){
				info.plattype = plattype;
				info.serverid = serverid;
				serverid2xio.put(serverid, xio);
			}
		}
	}
	
	public XioInfo getXioInfo( Xio xio) {
		synchronized (mutex) {
			return xio2info.get( xio);
		}
	}
	
	public boolean sendProtocol(int serverid, Protocol p) {
		Xio xio;
		synchronized (mutex) {
			xio = serverid2xio.get(serverid);
		}
		if( null == xio)
			return false;
		else
			return p.send(xio);
	}
}

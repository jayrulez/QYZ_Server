package gnet.link;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * 维护到glinkd的连接，以及此glinkd上的所有角色。
 *
 */
public final class Link {
	private final xio.Xio io;
	private int linkid = 0;
	private Map<Integer, Role> roles = new HashMap<Integer, Role>();

	@Override
	public String toString() {
		return io == null ? "io is null" : io.toString();
	}

	public synchronized int getLinkid() {
		return linkid;
	}

	public synchronized void setLinkid(int linkid) {
		this.linkid = linkid;
	}

	public final class Session {
		private final int sid;

		private Session(int sid) {
			this.sid = sid;
		}

		boolean close() {
			return remove(sid);
		}

		public final int getSid() {
			return sid;
		}

		public Link getLink() {
			return Link.this;
		}

		public xio.Xio getXio() {
			return Link.this.io;
		}

		@Override
		public String toString() {
			return Link.this.io.toString() + "," + sid;
		}
	}

	Link(xio.Xio link) {
		this.io = link;
	}

	public xio.Xio getXio() {
		return io;
	}

	public synchronized Role find(Integer linksid) {
		return roles.get(linksid);
	}

	// package private
	synchronized Session attach(int linksid, Role role) {
		Role old = roles.put(linksid, role);
		if (null != old)
			xdb.Trace.info("role rebind link " + old + "->" + role);
		return new Session(linksid);
	}

	private synchronized boolean remove(int linksid) {
		return roles.remove(linksid) != null;
	}

	Collection<Role> close() {
		Map<Integer, Role> tmp;
		synchronized (this) {
			tmp = roles;
			roles = new HashMap<>();
		}
		for (Role r : tmp.values())
			r.linkBroken();
		io.close();
		return tmp.values();
	}
}

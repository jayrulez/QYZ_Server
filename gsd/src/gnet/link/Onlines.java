package gnet.link;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Octets;
import lx.gs.login.LoginTraceMgr;
import org.w3c.dom.Element;
import xdb.Trace;
import xio.Protocol;

import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Onlines extends xio.Manager implements xdb.Procedure.IOnlines {
	
	static boolean traceProtocolAtInfoLevel = false;

	private volatile int maxOnline = 5000;
	
	public static void setTraceProtocolAtInfoLevel(boolean infoLevel){
		traceProtocolAtInfoLevel = infoLevel;
	}

    public static boolean isTraceProtocolAtInfoLevel(){
        return traceProtocolAtInfoLevel;
    }
	
	private static Onlines instance = null;

	public Onlines() {
		instance = this;
	}

	public static Onlines getInstance() {
		return instance;
	}

	private Set<Integer> binds = new HashSet<Integer>();

	@Override
	protected void parse(Element self) throws Exception {
		super.parse(self);
		for (String s : self.getAttribute("bind").split(","))
			binds.add(Integer.valueOf(s));
	}

	public static void loadConf(String conf) throws Exception {
        xio.XioConf.loadAndRegister(conf);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// application interface

	private Map<Long, Role> roles = new ConcurrentHashMap<>();

	public Role[] roles() {
        return roles.values().toArray(new Role[0]);
	}

	public Role find(Long roleid) {
        return roles.get(roleid);
	}

	public Role find(xio.Xio io, int linksid) {
		Link link = find(io);
		if (null != link)
			return link.find(linksid);
		return null;
	}

	public Role find(xio.Protocol p2) {
		if (null != p2.getContext())
			return find(p2.getConnection(), ((Dispatch) p2.getContext()).linksid);
		return null;
	}

	// override this to convert a subclass of Role
	protected Role newRole(long userid, long roleid) {
		return new Role(userid, roleid);
	}

	public Role insert(Link link, int linksid, long userid, long roleid) {
		if (null != link) {
            Role old = roles.get(roleid);
            if (null != old) {
                old.linkAttach(link, linksid);
                return old;
            }
            Role role = newRole(userid, roleid);
            roles.put(roleid, role);
            role.linkAttach(link, linksid);
            return role;
		} else
			xdb.Trace.info("Link not found");
		return null;
	}

	public Role insert(int ip, int port, int linksid, long userid, long roleid) {
		return insert(find(ip, port), linksid, userid, roleid);
	}

	public Role insert(xio.Protocol p2, long roleid) {
		Dispatch ctx = (Dispatch) p2.getContext();
		return insert(find(p2.getConnection()), ctx.linksid, ctx.userid, roleid);
	}

    public boolean remove(long roleid, Role role) {
        return roles.remove(roleid, role);
    }

	public boolean sendResponse(xio.Protocol THIS, xio.Protocol p2) {
		Dispatch ctx = (Dispatch) THIS.getContext();
		if (traceProtocolAtInfoLevel)
            xdb.Trace.info("send userid={}, linksid={}, class={}, this={}", ctx.userid, ctx.linksid,
                    p2.getClass().getName(), p2.toString());
		Send msg = new Send();
		msg.linksids.add(ctx.linksid);
		msg.ptype = p2.getType();
		msg.pdata = new OctetsStream().marshal(p2);
		return msg.send(ctx.getConnection());
	}

	public boolean send(Long roleid, xio.Protocol p2) {
		Role role = find(roleid);
		if (null != role)
			return role.send(p2);
		return false;
	}

	public boolean sendForward(long roleid, int ptype, Octets pdata) {
        Role role = find(roleid);
        if (null != role)
            return role.sendForward(ptype, pdata);
	    return false;
    }

	public void multicast(Collection<Long> roleids, xio.Protocol p2) {
		multicast(roleids, p2, true);
	}

	public void multicast(Collection<Long> roleids, xio.Protocol p2, boolean warn) {
		if (traceProtocolAtInfoLevel){
            xdb.Trace.info("multicast roleids={}, class={}, this={}", roleids, p2.getClass().getName(), p2);
        }

		Map<Link, HashSet<Integer>> group = new HashMap<>();
		for (Long roleid : roleids) {
			Role role = find(roleid);
			if (null == role) {
				if (warn)
					xdb.Trace.debug("send2 role not found , roleid=" + roleid);
				continue;
			}
			Link.Session ls = role.getLinkSession();
			if (null == ls) {
				if (warn)
					xdb.Trace.debug("send2 role has broken, roleid=" + roleid);
				continue;
			}
            LoginTraceMgr.traceDown(roleid, p2);
			HashSet<Integer> si = group.get(ls.getLink());
			if (null == si)
				group.put(ls.getLink(), si = new HashSet<Integer>());
			si.add(ls.getSid());
		}

		Send msg = new Send();
		msg.ptype = p2.getType();
		msg.pdata = new OctetsStream().marshal(p2);

		for (Map.Entry<Link, HashSet<Integer>> e : group.entrySet()) {
            msg.linksids = e.getValue();
			if (!msg.send(e.getKey().getXio()))
				xdb.Trace.warn("send2 error, p2={} link={}", p2.str(), e.getKey());
		}
	}

    public void multicastForward(Set<Long> roleids, int ptype, Octets pdata, boolean warn) {
        if (traceProtocolAtInfoLevel){
            xdb.Trace.info("multicast roleids={}, ptype={}", roleids, ptype);
        }

        Map<Link, HashSet<Integer>> group = new HashMap<>();
        for (Long roleid : roleids) {
            Role role = find(roleid);
            if (null == role) {
                if (warn)
                    xdb.Trace.debug("send2 role not found , roleid=" + roleid);
                continue;
            }
            Link.Session ls = role.getLinkSession();
            if (null == ls) {
                if (warn)
                    xdb.Trace.debug("send2 role has broken, roleid=" + roleid);
                continue;
            }
            HashSet<Integer> si = group.get(ls.getLink());
            if (null == si)
                group.put(ls.getLink(), si = new HashSet<Integer>());
            si.add(ls.getSid());
        }

        Send msg = new Send();
        msg.ptype = ptype;
        msg.pdata = pdata;

        for (Map.Entry<Link, HashSet<Integer>> e : group.entrySet()) {
            msg.linksids = e.getValue();
            if (!msg.send(e.getKey().getXio()))
                xdb.Trace.warn("send error, ptype={} link={}", ptype, e.getKey());
        }
    }

	public void broadcast(xio.Protocol p2) {
		broadcast(p2, 0);
	}

	public void broadcast(xio.Protocol p2, int timems) {
        xdb.Trace.info("broadcast timems={}, class={}, this={}", timems, p2.getClass().getName(), p2.toString());
		Broadcast bc = new Broadcast();
		bc.ptype = p2.getType();
		bc.pdata = new OctetsStream().marshal(p2);
		bc.time = timems;
		for (Link link : this.links()) {
			if (bc.send(link.getXio()))
				continue;
			xdb.Trace.warn("broadcast error, p2={}, link={}", p2.str(), link);
		}
	}
	
	public void keepAlive() {
		final Send re = new Send();
		for (Link link : this.links()) {
			if (re.send(link.getXio()))
				continue;
			xdb.Trace.warn("keepAlive error, link={}", link);
		}
	}

    public void checkExpire() {
        final long now = System.currentTimeMillis();
        for(Role role : this.roles()) {
            try {
                role.checkExpire(now);
            } catch (Exception e) {
                Trace.error("Onlines.checkExpire roleid:" + role.getRoleid(), e);
            }
        }
    }

//	public boolean kick(Long roleid, int error) {
//		Role role = find(roleid);
//		if (null != role)
//			return role.kick(error);
//		return false;
//	}

	public boolean bind(Long roleid, int pvid) {
		Role role = find(roleid);
		if (null != role)
			return role.bind(pvid);
		return false;
	}

	public boolean unbind(Long roleid, int pvid) {
		Role role = find(roleid);
		if (null != role)
			return role.unbind(pvid);
		return false;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// manager implement
	private Map<InetSocketAddress, Link> links = new HashMap<InetSocketAddress, Link>();
	private Object mutex = new Object();

	public Link[] links() {
		synchronized (mutex) {
			return links.values().toArray(new Link[0]);
		}
	}

	public Link find(int linkid) {
		synchronized (mutex) {
			for (Link link : links.values())
				if (link.getLinkid() == linkid)
					return link;
			return null;
		}
	}

	public Link find(int peerip, int port) {
		synchronized (mutex) {
			return links.get(xio.Helper.inetSocketAddress(peerip, port));
		}
	}

	public Link find(xio.Xio io) {
		synchronized (mutex) {
			return links.get(io.getPeer());
		}
	}

	@Override
	public xio.Xio get() {
		synchronized (mutex) {
			if (links.isEmpty())
				return null;
			return links.values().iterator().next().getXio();
		}
	}

	@Override
	public int size() {
		synchronized (mutex) {
			return links.size();
		}
	}

	@Override
	protected void addXio(xio.Xio io) {
		synchronized (mutex) {
			if (null != links.get(io.getPeer())) {
				xdb.Trace.error("duplicate connection " + io);
				io.close();
				return;
			}
			links.put(io.getPeer(), new Link(io));
		}
		// bind all gs provider protocol to me
		for (Integer pvid : binds) {
			gnet.link.Bind bind = new gnet.link.Bind();
			bind.pvid = pvid;
			bind.send(io);
		}
	}

	@Override
	protected void removeXio(xio.Xio io, Throwable e) {
		Link link;
		synchronized (mutex) {
			link = links.remove(io.getPeer());
		}
//		if (null != link) {
//			xdb.Trace.error("Onlines removeXio " + io, e);
//			Collection<Role> roles = link.close();
//			Handle volatileTemp = handle;
//			if (null != volatileTemp) {
//				try {
//					volatileTemp.onManagerBroken(roles);
//				} catch (Throwable ex) {
//					xdb.Trace.error("onManagerBroken " + io, ex);
//				}
//			}
//		}
	}

	@Override
	protected void close() {
		super.close();
		Map<InetSocketAddress, Link> tmp;
		synchronized (mutex) {
			tmp = links;
			links = new HashMap<InetSocketAddress, Link>();
		}
		for (Link l : tmp.values())
			l.close();
	}

	void process(AnnounceLinkId p) {
        if (traceProtocolAtInfoLevel)
            xdb.Trace.info("AnnounceLinkId, this={}", p.toString());

		Link link = find(p.getConnection());
		if (null == link) {
			xdb.Trace.error("link not found! linkid=" + p.linkid + p.getConnection());
			return;
		}
		// linkid duplicate-check and set
		synchronized (mutex) {
			if (null != find(p.linkid)) {
				xdb.Trace.error("duplicate linkid found! linkid={}, xio={}", p.linkid, link.getXio());
				link.getXio().close();
			} else
				link.setLinkid(p.linkid);
		}
	}


	void process(LinkBroken p) {
        if (traceProtocolAtInfoLevel)
            xdb.Trace.info("LinkBroken, this={}", p.toString());

        final Link link = find(p.getConnection());
		if (link != null) {
			final Role role = link.find(p.linksid);
			if(role != null)
                role.linkBroken();
//            FMap.dispatchMessage(role.getRoleid(), new XOffline());
        }
	}

	@Override
	public boolean send(Set<Long> roleids, Protocol p2) {
		multicast(roleids, p2);
		return true;
	}

	public void setMaxOnline(int maxOnline) {
		this.maxOnline = maxOnline;
	}

	public boolean isFull(){
		return size() >= maxOnline;
	}
}

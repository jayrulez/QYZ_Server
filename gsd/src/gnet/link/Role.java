package gnet.link;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Octets;
import common.RingQueue;
import lx.gs.CPing;
import lx.gs.SPing;
import lx.gs.login.CRoleLogin;
import lx.gs.login.CRoleRelogin;
import lx.gs.login.LoginTraceMgr;
import lx.gs.login.PRoleLogout;
import lx.gs.map.FMap;
import lx.gs.map.RoleContext;
import map.msg.XKeepAlive;
import map.msg.XOffline;
import xdb.Trace;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public final class Role {
	private final long userid;
	private final long roleid;
	private volatile Link.Session linkSession;
    private volatile long lastPingTime;

    private final RingQueue<xio.Protocol> waitConfirmQueue = new RingQueue<>(1000);

	public Role(long userid, long roleid) {
		this.userid = userid;
		this.roleid = roleid;
        this.linkSession = null;

        this.lastPingTime = System.currentTimeMillis();
	}

	public long getRoleid() {
		return roleid;
	}

	public long getUserid() {
		return userid;
	}

	public Link.Session getLinkSession() {
		return linkSession;
	}

	void linkBroken() {
	    synchronized (this) {
            final Link.Session ls = linkSession;
            if (ls != null && ls.close()) {
                FMap.dispatchMessage(roleid, new XOffline());
            }
            linkSession = null;
        }
	}

	void kickThenLinkBroken() {
        synchronized (this) {
            final Link.Session ls = linkSession;
            if (ls != null) {
                kick(Kick.A_QUICK_CLOSE);
                ls.close();
                //FMap.dispatchMessage(roleid, new XOffline());
            }
            linkSession = null;
        }
    }

	void linkAttach(Link link, int linksid) {
		linkSession = link.attach(linksid, this);
        new SetLogin(linksid, SetLogin.eLogin, roleid).send(linkSession.getXio());
	}

    final static Set<String> ignoreConfirmModules = Arrays.asList(
      "login",
       "chat"
    ).stream().map(s -> "lx.gs." + s).collect(Collectors.toSet());

	public boolean send(xio.Protocol p2) {
        LoginTraceMgr.traceDown(roleid, p2);
        final Class<?> clazz = p2.getClass();
        final String clsName = clazz.getName();
		if (Onlines.traceProtocolAtInfoLevel)
            xdb.Trace.info("send userid={}, roleid={}, class={}, this={}", userid, roleid, clsName, p2.toString());

        if (needConfirm(p2)) {
            synchronized (waitConfirmQueue) {
                waitConfirmQueue.forceAdd(p2);
            }
        } else {
            Trace.debug("Role.send ignore confirm msg:{}{}", clsName, p2);
        }

		return doSend(p2);
	}

	public boolean sendForward(int ptype, Octets pdata) {
        final Link.Session ls = linkSession;
        if (ls != null) {
            Send p1 = new Send();
            p1.linksids.add(ls.getSid());
            p1.ptype = ptype;
            p1.pdata = pdata;
            return p1.send(ls.getXio());
        }
        return false;
    }

    public static boolean needConfirm(xio.Protocol msg) {
        final Class<?> clazz = msg.getClass();
        final String clsName = clazz.getName();
        return clazz.getName().startsWith("lx.gs") && !ignoreConfirmModules.contains(clsName.substring(0, clsName.lastIndexOf('.')));
    }

    private void refreshPing() {
        this.lastPingTime = System.currentTimeMillis();
    }

    private long lastSendKeepaliveTime;
    public void onPing(CPing msg) {
        refreshPing();
        final long now = System.currentTimeMillis();
        final SPing re = new SPing(msg.sendclienttime, now, 0 /* 这个字段客户端自己使用 */, 0);
        synchronized (waitConfirmQueue) {
            waitConfirmQueue.removeUntil(msg.receivedmessagecount);
            re.sendmessagecount = waitConfirmQueue.forceAdd(re);

            RoleContext playerInfo = FMap.getRoleMapInfo(roleid);
            if(playerInfo != null && playerInfo.getCurMapid() != 0) {
                doSend(re);
                if (lastSendKeepaliveTime + 60000 < now) {
                    FMap.dispatchMessage(roleid, new XKeepAlive());
                    lastSendKeepaliveTime = now;
                }
            }
        }
    }

    public boolean onRelogin(CRoleRelogin msg) {
        synchronized (waitConfirmQueue) {
            if(waitConfirmQueue.getHeadid() > msg.receivedmessagecount || waitConfirmQueue.getTailid() < msg.receivedmessagecount)
                return false;
            Trace.debug("Role.onRelogin before headid:{} tailid:{} msg.receivecount:{}", waitConfirmQueue.getHeadid(), waitConfirmQueue.getTailid(), msg.receivedmessagecount);
            waitConfirmQueue.removeUntil(msg.receivedmessagecount);
        }
        Onlines.getInstance().insert(msg, msg.roleid);
        refreshPing();
        return true;
    }

    public void sendNotConfirmProtocols() {
        synchronized (waitConfirmQueue) {
            for (long i = waitConfirmQueue.getHeadid(), e = waitConfirmQueue.getTailid(); i < e; i++) {
                final xio.Protocol resend = waitConfirmQueue.get(i);
                Trace.debug("onRelogin resend {}{}", resend.getClass().getName(), resend);
                doSend(waitConfirmQueue.get(i));
            }
        }
    }

    public void onLogin(CRoleLogin msg) {
        refreshPing();
        synchronized (waitConfirmQueue) {
            waitConfirmQueue.reset();
        }
    }

    public void checkExpire(long now) {
        if(lastPingTime + CfgMgr.roleconfig.reconecttimeout * 1000L < now) {
            doCompleteLogout();
        }
    }

    public void doCompleteLogout() {
        kickThenLinkBroken();
        if(Onlines.getInstance().remove(roleid, this)) {
            new PRoleLogout(roleid).call();
        }
        FMap.logoutLeaveMap(roleid);
    }

    public void doReplaceLogout() {
        kickThenLinkBroken();
        if(Onlines.getInstance().remove(roleid, this)) {
            new PRoleLogout(roleid).call();
        }
    }

    private boolean doSend(xio.Protocol msg) {
        final Link.Session ls = linkSession;
        if (ls != null) {
            Send p1 = new Send();
            p1.linksids.add(ls.getSid());
            p1.ptype = msg.getType();
            p1.pdata = new OctetsStream().marshal(msg);
            ls.getXio().getCreator().getManager().getCoder().checkSend(msg, p1.pdata.size());
            return p1.send(ls.getXio());
        }
        return false;
    }

	public boolean kick(int error) {
		final Link.Session ls = linkSession;
		if (ls != null) {
			Kick p1 = new Kick();
			p1.linksid = ls.getSid();
			p1.action = Kick.A_QUICK_CLOSE;
			p1.error = error;
			return p1.send(ls.getXio());
		}
		return false;
	}

	public boolean bind(int pvid) {
		final Link.Session ls = linkSession;
		if (ls != null) {
			Bind p1 = new Bind();
			p1.pvid = pvid;
			p1.linksids.add(ls.getSid());
			return p1.send(ls.getXio());
		}
		return false;
	}

	public boolean unbind(int pvid) {
		final Link.Session ls = linkSession;
		if (ls != null) {
			UnBind p1 = new UnBind();
			p1.pvid = pvid;
			p1.linksids.add(ls.getSid());
			return p1.send(ls.getXio());
		}
		return false;
	}

	@Override
	public String toString() {
		return "userid=" + userid + " roleid=" + roleid + linkSession;
	}
}

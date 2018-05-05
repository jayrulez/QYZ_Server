
package gnet.link;
import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import lx.gs.CPing;
import lx.gs.login.LoginTraceMgr;
import lx.gs.map.FMap;
import xdb.Trace;

import java.util.concurrent.ConcurrentHashMap;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __Dispatch__ extends xio.Protocol { }

/** link to gs
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class Dispatch extends __Dispatch__ {
    private final static int maxTotalRemain = CfgMgr.roleconfig.maxtotalremainsend;
    private final static int maxCurRemain = CfgMgr.roleconfig.maxremainsendpersecond;
    private final static int addRemainPerSecond = CfgMgr.roleconfig.maxaddremainpersecond;

    static class Limit {
        private final long roleid;
        private long lastRecvTime;
        private double totalRemain;
        private double curRemain;
        private long totalFail;
        Limit(long roleid) {
            this.roleid = roleid;
            this.totalRemain = maxTotalRemain;
            this.curRemain = maxCurRemain;
        }

        public long getTotalFail() {
            return totalFail;
        }

        boolean check(long now) {
            curRemain = Math.min(maxCurRemain, curRemain + (now - lastRecvTime) * maxCurRemain * 1e-9);
            totalRemain = Math.min(maxTotalRemain, totalRemain + (now - lastRecvTime) * addRemainPerSecond * 1e-9);
            lastRecvTime = now;
            if(curRemain >= 1 && totalRemain >= 1) {
                --curRemain;
                --totalRemain;
//                System.out.printf("roleid:%s totalremain:%10.1f curremain:%10.1fs totalfailcount:%10s\n", roleid, totalRemain, curRemain, totalFail);
                return true;
            } else {
                ++totalFail;
                return false;
            }
        }
    }

    private final static ConcurrentHashMap<Long, Limit> roleMsgLimits = new ConcurrentHashMap<>();


	@Override
	public void dispatch(Stub stub) throws Exception {
		 int error;
		try {
			Stub stub2 = ((Coder)(getManager().getCoder())).getStub(this.ptype);
			xio.Protocol p = stub2.newInstance();
			p.unmarshal(OctetsStream.wrap(this.pdata));
			p.setConnection(this.getConnection());
			p.setContext(this);

			final gnet.link.Role role = gnet.link.Onlines.getInstance().find(p);
            final long roleid = role != null ? role.getRoleid() : 0;
            final String pname = p.getClass().getName();
            if (Onlines.traceProtocolAtInfoLevel) {
                if (role != null){
                    xdb.Trace.info("Dispatch userid={}, roleid={}, linksid={}, class={}, this={}", userid, roleid, linksid, pname, p);
                }else{
                    xdb.Trace.info("Dispatch userid={}, linksid={}, class={}, this={}", userid, linksid, pname, p);
                }
            }
            if(p.getClass() != CPing.class) {
                Limit limit = roleMsgLimits.get(userid);
                if (limit == null) {
                    roleMsgLimits.putIfAbsent(userid, new Limit(userid));
                    limit = roleMsgLimits.get(userid);
                }
                synchronized (limit) {
                    if (!limit.check(System.nanoTime())) {
                        final long totalFailCount = limit.getTotalFail();
                        if (totalFailCount < 30 || totalFailCount % 100 == 0) {
                            Trace.error("Dispatch. userid:{} roleid:{} exceed limit. msg:{} totalfailcount:{}", userid, roleid, p.getClass().getName(), totalFailCount);
                        }
                        return;
                    }
                }
            }

			if(pname.startsWith("map.msg")) {
                if(role != null)
                    FMap.dispatchMessage(roleid, p);
			} else {
				p.dispatch(stub2);
			}
			return;
		} catch (xio.MarshalError e) {
			error = Kick.E_MARSHAL_EXCEPTION;
			e.printStackTrace();
		} catch (Throwable e) {
			error = Kick.E_PROTOCOL_EXCEPTION;
			e.printStackTrace();
		}
		Kick kick = new Kick();
		kick.error = error;
		kick.action = Kick.A_DELAY_CLOSE;
		kick.send(super.getConnection());
	}

	@Override
	protected void process() {
		throw new UnsupportedOperationException();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 65541;

	public int getType() {
		return 65541;
	}

	public int linksid;
	public long userid;
	public int ptype;
	public com.goldhuman.Common.Octets pdata;

	public Dispatch() {
		pdata = new com.goldhuman.Common.Octets();
	}

	public Dispatch(int _linksid_, long _userid_, int _ptype_, com.goldhuman.Common.Octets _pdata_) {
		this.linksid = _linksid_;
		this.userid = _userid_;
		this.ptype = _ptype_;
		this.pdata = _pdata_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(linksid);
		_os_.marshal(userid);
		_os_.marshal(ptype);
		_os_.marshal(pdata);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		linksid = _os_.unmarshal_int();
		userid = _os_.unmarshal_long();
		ptype = _os_.unmarshal_int();
		pdata = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Dispatch) {
			Dispatch _o_ = (Dispatch)_o1_;
			if (linksid != _o_.linksid) return false;
			if (userid != _o_.userid) return false;
			if (ptype != _o_.ptype) return false;
			if (!pdata.equals(_o_.pdata)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += linksid;
		_h_ += (int)userid;
		_h_ += ptype;
		_h_ += pdata.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(linksid).append(",");
		_sb_.append(userid).append(",");
		_sb_.append(ptype).append(",");
		_sb_.append("B").append(pdata.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


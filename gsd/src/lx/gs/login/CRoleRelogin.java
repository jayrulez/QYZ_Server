
package lx.gs.login;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.TaskQueue;
import gnet.link.Onlines;
import gnet.link.Role;
import lx.gs.map.FMap;
import lx.gs.role.FForbid;
import xdb.Procedure;

import java.util.concurrent.TimeUnit;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRoleRelogin__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRoleRelogin extends __CRoleRelogin__ {
	@Override
	protected void process() {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final SRoleRelogin re = new SRoleRelogin();
                re.roleid = roleid;
                gnet.link.Dispatch context = ((gnet.link.Dispatch) getContext());
                long userid = context.userid;
                xbean.User user = xtable.Users.select(userid);
                if (!user.getRoleids().contains(roleid)) {
                    re.err = SRoleLogin.ROLEID_NOT_IN_USER;
                } else if(FForbid.isForbidLogin(roleid)) {
                    re.err = SRoleLogin.ROLE_LOGIN_IS_FORBIDDEN;
                } else {
                    final Role role = gnet.link.Onlines.getInstance().find(roleid);
                    if (role == null) {
                        re.err = SRoleLogin.ROLE_NOT_LOGIN;
                    } else if(!role.onRelogin(CRoleRelogin.this)) {
                        re.err = SRoleLogin.SESSION_CORRUPT;
                    } else {
                        re.err = SRoleLogin.OK;
                        TaskQueue.getScheduleExecutor().schedule(() -> FMap.loginEnterMap(roleid), 100, TimeUnit.MILLISECONDS);
                    }
                }
                Onlines.getInstance().sendResponse(CRoleRelogin.this, re);
                if(re.err == SRoleLogin.OK) {
                    Onlines.getInstance().find(roleid).sendNotConfirmProtocols();
                }
                return re.err == SRoleLogin.OK;
            }
        }.execute();

	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6580194;

	public int getType() {
		return 6580194;
	}

	public long roleid;
	public long receivedmessagecount;

	public CRoleRelogin() {
	}

	public CRoleRelogin(long _roleid_, long _receivedmessagecount_) {
		this.roleid = _roleid_;
		this.receivedmessagecount = _receivedmessagecount_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(receivedmessagecount);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		receivedmessagecount = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRoleRelogin) {
			CRoleRelogin _o_ = (CRoleRelogin)_o1_;
			if (roleid != _o_.roleid) return false;
			if (receivedmessagecount != _o_.receivedmessagecount) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += (int)receivedmessagecount;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(receivedmessagecount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CRoleRelogin _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(receivedmessagecount - _o_.receivedmessagecount);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


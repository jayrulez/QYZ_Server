
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import lx.gs.login.CGetRoleList;
import robot.Ctx;
import xdb.Trace;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __OnlineAnnounce__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class OnlineAnnounce extends __OnlineAnnounce__ {
	@Override
	protected void process() {
		final Ctx ctx = (Ctx)getContext();
        ctx.userid = userid;
        Trace.info("OnlineAnnounce. account:{} userid:{}", ctx.account, ctx.userid);
        new CGetRoleList().send(getConnection());
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 105;

	public int getType() {
		return 105;
	}

	public long userid;

	public OnlineAnnounce() {
	}

	public OnlineAnnounce(long _userid_) {
		this.userid = _userid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(userid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		userid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof OnlineAnnounce) {
			OnlineAnnounce _o_ = (OnlineAnnounce)_o1_;
			if (userid != _o_.userid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)userid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(userid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(OnlineAnnounce _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(userid - _o_.userid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


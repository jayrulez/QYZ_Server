
package lx.gs.login;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import xbean.User;
import xdb.Procedure;
import xtable.Users;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCancelDelteRole__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCancelDelteRole extends __CCancelDelteRole__ {
	@Override
	protected void process() {
		gnet.link.Dispatch context = ((gnet.link.Dispatch) this.getContext());
		final long userId = context.userid;
		CCancelDelteRole upProto = this;
		new Procedure(){
			@Override
			protected boolean process() throws Exception {
				long cancelId = roleid;
				User user = Users.get(userId);
				Map<Long, Long> del = user.getDeleteinfo();
				if(del.containsKey(cancelId) && System.currentTimeMillis() <= del.get(cancelId) + LoginModule.DELETE_ROLE_PUT_OFF){
					user.getRoleids().add(cancelId);
					del.remove(cancelId);
					gnet.link.Onlines.getInstance().sendResponse(upProto, new SCancelDelteRole(cancelId));
					return true;
				}
				return false;
			}
		}.call();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576594;

	public int getType() {
		return 6576594;
	}

	public long roleid;

	public CCancelDelteRole() {
	}

	public CCancelDelteRole(long _roleid_) {
		this.roleid = _roleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CCancelDelteRole) {
			CCancelDelteRole _o_ = (CCancelDelteRole)_o1_;
			if (roleid != _o_.roleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CCancelDelteRole _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


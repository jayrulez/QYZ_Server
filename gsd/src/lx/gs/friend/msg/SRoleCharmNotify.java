
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SRoleCharmNotify__ extends xio.Protocol { }

/** 通知角色魅力值变化
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SRoleCharmNotify extends __SRoleCharmNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577848;

	public int getType() {
		return 6577848;
	}

	public int notifytype;
	public long roleid;
	public int charm;

	public SRoleCharmNotify() {
	}

	public SRoleCharmNotify(int _notifytype_, long _roleid_, int _charm_) {
		this.notifytype = _notifytype_;
		this.roleid = _roleid_;
		this.charm = _charm_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(notifytype);
		_os_.marshal(roleid);
		_os_.marshal(charm);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		notifytype = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		charm = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SRoleCharmNotify) {
			SRoleCharmNotify _o_ = (SRoleCharmNotify)_o1_;
			if (notifytype != _o_.notifytype) return false;
			if (roleid != _o_.roleid) return false;
			if (charm != _o_.charm) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += notifytype;
		_h_ += (int)roleid;
		_h_ += charm;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(notifytype).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(charm).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SRoleCharmNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = notifytype - _o_.notifytype;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		_c_ = charm - _o_.charm;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


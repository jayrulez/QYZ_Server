
package lx.gs.chat.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetRoleChatShowInfos__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetRoleChatShowInfos extends __SGetRoleChatShowInfos__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582665;

	public int getType() {
		return 6582665;
	}

	public java.util.HashMap<Long,lx.gs.chat.msg.RoleChatShowInfo> roles;

	public SGetRoleChatShowInfos() {
		roles = new java.util.HashMap<Long,lx.gs.chat.msg.RoleChatShowInfo>();
	}

	public SGetRoleChatShowInfos(java.util.HashMap<Long,lx.gs.chat.msg.RoleChatShowInfo> _roles_) {
		this.roles = _roles_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Long, lx.gs.chat.msg.RoleChatShowInfo> _e_ : roles.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(roles.size());
		for (java.util.Map.Entry<Long, lx.gs.chat.msg.RoleChatShowInfo> _e_ : roles.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			lx.gs.chat.msg.RoleChatShowInfo _v_ = new lx.gs.chat.msg.RoleChatShowInfo();
			_v_.unmarshal(_os_);
			roles.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetRoleChatShowInfos) {
			SGetRoleChatShowInfos _o_ = (SGetRoleChatShowInfos)_o1_;
			if (!roles.equals(_o_.roles)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += roles.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roles).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


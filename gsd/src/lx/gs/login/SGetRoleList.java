
package lx.gs.login;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetRoleList__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetRoleList extends __SGetRoleList__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553602;

	public int getType() {
		return 6553602;
	}

	public long prevloginroleid;
	public java.util.ArrayList<lx.gs.role.msg.RoleBrief> roles;
	public java.util.HashMap<Long,Long> deleteinfo;

	public SGetRoleList() {
		roles = new java.util.ArrayList<lx.gs.role.msg.RoleBrief>();
		deleteinfo = new java.util.HashMap<Long,Long>();
	}

	public SGetRoleList(long _prevloginroleid_, java.util.ArrayList<lx.gs.role.msg.RoleBrief> _roles_, java.util.HashMap<Long,Long> _deleteinfo_) {
		this.prevloginroleid = _prevloginroleid_;
		this.roles = _roles_;
		this.deleteinfo = _deleteinfo_;
	}

	public final boolean _validator_() {
		for (lx.gs.role.msg.RoleBrief _v_ : roles)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(prevloginroleid);
		_os_.compact_uint32(roles.size());
		for (lx.gs.role.msg.RoleBrief _v_ : roles) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(deleteinfo.size());
		for (java.util.Map.Entry<Long, Long> _e_ : deleteinfo.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		prevloginroleid = _os_.unmarshal_long();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.role.msg.RoleBrief _v_ = new lx.gs.role.msg.RoleBrief();
			_v_.unmarshal(_os_);
			roles.add(_v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			long _v_;
			_v_ = _os_.unmarshal_long();
			deleteinfo.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetRoleList) {
			SGetRoleList _o_ = (SGetRoleList)_o1_;
			if (prevloginroleid != _o_.prevloginroleid) return false;
			if (!roles.equals(_o_.roles)) return false;
			if (!deleteinfo.equals(_o_.deleteinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)prevloginroleid;
		_h_ += roles.hashCode();
		_h_ += deleteinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(prevloginroleid).append(",");
		_sb_.append(roles).append(",");
		_sb_.append(deleteinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


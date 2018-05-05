
package lx.gs.team.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFindNearbyRole__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFindNearbyRole extends __SFindNearbyRole__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557802;

	public int getType() {
		return 6557802;
	}

	public java.util.LinkedList<lx.gs.role.msg.RoleShowInfo3> rolelist; // 按照设定的规则排序后的list

	public SFindNearbyRole() {
		rolelist = new java.util.LinkedList<lx.gs.role.msg.RoleShowInfo3>();
	}

	public SFindNearbyRole(java.util.LinkedList<lx.gs.role.msg.RoleShowInfo3> _rolelist_) {
		this.rolelist = _rolelist_;
	}

	public final boolean _validator_() {
		for (lx.gs.role.msg.RoleShowInfo3 _v_ : rolelist)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(rolelist.size());
		for (lx.gs.role.msg.RoleShowInfo3 _v_ : rolelist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.role.msg.RoleShowInfo3 _v_ = new lx.gs.role.msg.RoleShowInfo3();
			_v_.unmarshal(_os_);
			rolelist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFindNearbyRole) {
			SFindNearbyRole _o_ = (SFindNearbyRole)_o1_;
            return rolelist.equals(_o_.rolelist);
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += rolelist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(rolelist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


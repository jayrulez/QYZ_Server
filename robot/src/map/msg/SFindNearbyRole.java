
package map.msg;

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
	public static final int PROTOCOL_TYPE = 6713087;

	public int getType() {
		return 6713087;
	}

	public java.util.ArrayList<map.msg.NearbyRoleShowInfo> rolelist;

	public SFindNearbyRole() {
		rolelist = new java.util.ArrayList<map.msg.NearbyRoleShowInfo>();
	}

	public SFindNearbyRole(java.util.ArrayList<map.msg.NearbyRoleShowInfo> _rolelist_) {
		this.rolelist = _rolelist_;
	}

	public final boolean _validator_() {
		for (map.msg.NearbyRoleShowInfo _v_ : rolelist)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(rolelist.size());
		for (map.msg.NearbyRoleShowInfo _v_ : rolelist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.NearbyRoleShowInfo _v_ = new map.msg.NearbyRoleShowInfo();
			_v_.unmarshal(_os_);
			rolelist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFindNearbyRole) {
			SFindNearbyRole _o_ = (SFindNearbyRole)_o1_;
			if (!rolelist.equals(_o_.rolelist)) return false;
			return true;
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


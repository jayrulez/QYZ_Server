
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetPreselectionRoleList__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetPreselectionRoleList extends __SGetPreselectionRoleList__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576202;

	public int getType() {
		return 6576202;
	}

	public int profession;
	public java.util.ArrayList<lx.gs.activity.huiwu.msg.PreselectionRole> roles;

	public SGetPreselectionRoleList() {
		roles = new java.util.ArrayList<lx.gs.activity.huiwu.msg.PreselectionRole>();
	}

	public SGetPreselectionRoleList(int _profession_, java.util.ArrayList<lx.gs.activity.huiwu.msg.PreselectionRole> _roles_) {
		this.profession = _profession_;
		this.roles = _roles_;
	}

	public final boolean _validator_() {
		for (lx.gs.activity.huiwu.msg.PreselectionRole _v_ : roles)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(profession);
		_os_.compact_uint32(roles.size());
		for (lx.gs.activity.huiwu.msg.PreselectionRole _v_ : roles) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		profession = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.activity.huiwu.msg.PreselectionRole _v_ = new lx.gs.activity.huiwu.msg.PreselectionRole();
			_v_.unmarshal(_os_);
			roles.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetPreselectionRoleList) {
			SGetPreselectionRoleList _o_ = (SGetPreselectionRoleList)_o1_;
			if (profession != _o_.profession) return false;
			if (!roles.equals(_o_.roles)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += profession;
		_h_ += roles.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(profession).append(",");
		_sb_.append(roles).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


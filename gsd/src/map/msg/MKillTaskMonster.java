
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MKillTaskMonster__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MKillTaskMonster extends __MKillTaskMonster__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6714708;

	public int getType() {
		return 6714708;
	}

	public java.util.ArrayList<Long> roles;
	public int monsterid;

	public MKillTaskMonster() {
		roles = new java.util.ArrayList<Long>();
	}

	public MKillTaskMonster(java.util.ArrayList<Long> _roles_, int _monsterid_) {
		this.roles = _roles_;
		this.monsterid = _monsterid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(roles.size());
		for (Long _v_ : roles) {
			_os_.marshal(_v_);
		}
		_os_.marshal(monsterid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			roles.add(_v_);
		}
		monsterid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MKillTaskMonster) {
			MKillTaskMonster _o_ = (MKillTaskMonster)_o1_;
			if (!roles.equals(_o_.roles)) return false;
			if (monsterid != _o_.monsterid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += roles.hashCode();
		_h_ += monsterid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roles).append(",");
		_sb_.append(monsterid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


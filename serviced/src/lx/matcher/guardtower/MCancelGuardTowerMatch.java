
package lx.matcher.guardtower;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MCancelGuardTowerMatch__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MCancelGuardTowerMatch extends __MCancelGuardTowerMatch__ {
	@Override
	protected void process() {
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6646414;

	public int getType() {
		return 6646414;
	}

	public int err;
	public java.util.ArrayList<Long> teammembers;
	public java.util.ArrayList<Long> cancelmembers;

	public MCancelGuardTowerMatch() {
		teammembers = new java.util.ArrayList<Long>();
		cancelmembers = new java.util.ArrayList<Long>();
	}

	public MCancelGuardTowerMatch(int _err_, java.util.ArrayList<Long> _teammembers_, java.util.ArrayList<Long> _cancelmembers_) {
		this.err = _err_;
		this.teammembers = _teammembers_;
		this.cancelmembers = _cancelmembers_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(err);
		_os_.compact_uint32(teammembers.size());
		for (Long _v_ : teammembers) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(cancelmembers.size());
		for (Long _v_ : cancelmembers) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		err = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			teammembers.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			cancelmembers.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MCancelGuardTowerMatch) {
			MCancelGuardTowerMatch _o_ = (MCancelGuardTowerMatch)_o1_;
			if (err != _o_.err) return false;
			if (!teammembers.equals(_o_.teammembers)) return false;
			if (!cancelmembers.equals(_o_.cancelmembers)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += err;
		_h_ += teammembers.hashCode();
		_h_ += cancelmembers.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(err).append(",");
		_sb_.append(teammembers).append(",");
		_sb_.append(cancelmembers).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


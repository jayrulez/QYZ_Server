
package lx.gs.mount;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetRideInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetRideInfo extends lx.gs.mount.__SGetRideInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555781;

	public int getType() {
		return 6555781;
	}

	public java.util.ArrayList<lx.gs.mount.Ride> rideinfo;
	public int activeride;

	public SGetRideInfo() {
		rideinfo = new java.util.ArrayList<lx.gs.mount.Ride>();
	}

	public SGetRideInfo(java.util.ArrayList<lx.gs.mount.Ride> _rideinfo_, int _activeride_) {
		this.rideinfo = _rideinfo_;
		this.activeride = _activeride_;
	}

	public final boolean _validator_() {
		for (lx.gs.mount.Ride _v_ : rideinfo)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(rideinfo.size());
		for (lx.gs.mount.Ride _v_ : rideinfo) {
			_os_.marshal(_v_);
		}
		_os_.marshal(activeride);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.mount.Ride _v_ = new lx.gs.mount.Ride();
			_v_.unmarshal(_os_);
			rideinfo.add(_v_);
		}
		activeride = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetRideInfo) {
			SGetRideInfo _o_ = (SGetRideInfo)_o1_;
			if (!rideinfo.equals(_o_.rideinfo)) return false;
			if (activeride != _o_.activeride) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += rideinfo.hashCode();
		_h_ += activeride;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(rideinfo).append(",");
		_sb_.append(activeride).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}



package lx.gs.mount;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SRideGetNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SRideGetNotify extends lx.gs.mount.__SRideGetNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555784;

	public int getType() {
		return 6555784;
	}

	public lx.gs.mount.Ride ride;

	public SRideGetNotify() {
		ride = new lx.gs.mount.Ride();
	}

	public SRideGetNotify(lx.gs.mount.Ride _ride_) {
		this.ride = _ride_;
	}

	public final boolean _validator_() {
		if (!ride._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ride);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ride.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SRideGetNotify) {
			SRideGetNotify _o_ = (SRideGetNotify)_o1_;
			if (!ride.equals(_o_.ride)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ride.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ride).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SRideGetNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ride.compareTo(_o_.ride);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


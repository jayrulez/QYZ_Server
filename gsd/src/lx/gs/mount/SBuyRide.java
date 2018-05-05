
package lx.gs.mount;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBuyRide__ extends xio.Protocol { }

/** 购买坐骑，购买后处于获得状态
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBuyRide extends lx.gs.mount.__SBuyRide__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6584488;

	public int getType() {
		return 6584488;
	}

	public int ridekey; // 购买的坐骑key

	public SBuyRide() {
	}

	public SBuyRide(int _ridekey_) {
		this.ridekey = _ridekey_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ridekey);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ridekey = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBuyRide) {
			SBuyRide _o_ = (SBuyRide)_o1_;
			if (ridekey != _o_.ridekey) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ridekey;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ridekey).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SBuyRide _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ridekey - _o_.ridekey;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


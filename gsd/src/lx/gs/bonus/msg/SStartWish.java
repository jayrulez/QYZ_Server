
package lx.gs.bonus.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SStartWish__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SStartWish extends __SStartWish__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556127;

	public int getType() {
		return 6556127;
	}

	public long petid;
	public int wishtimes;

	public SStartWish() {
	}

	public SStartWish(long _petid_, int _wishtimes_) {
		this.petid = _petid_;
		this.wishtimes = _wishtimes_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(petid);
		_os_.marshal(wishtimes);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		petid = _os_.unmarshal_long();
		wishtimes = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SStartWish) {
			SStartWish _o_ = (SStartWish)_o1_;
			if (petid != _o_.petid) return false;
			if (wishtimes != _o_.wishtimes) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)petid;
		_h_ += wishtimes;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(petid).append(",");
		_sb_.append(wishtimes).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SStartWish _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(petid - _o_.petid);
		if (0 != _c_) return _c_;
		_c_ = wishtimes - _o_.wishtimes;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


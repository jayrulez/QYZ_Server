
package lx.gs.pay;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SVipLevelNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SVipLevelNotify extends __SVipLevelNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6585686;

	public int getType() {
		return 6585686;
	}

	public int newlevel;
	public long totalcharge;

	public SVipLevelNotify() {
	}

	public SVipLevelNotify(int _newlevel_, long _totalcharge_) {
		this.newlevel = _newlevel_;
		this.totalcharge = _totalcharge_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(newlevel);
		_os_.marshal(totalcharge);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		newlevel = _os_.unmarshal_int();
		totalcharge = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SVipLevelNotify) {
			SVipLevelNotify _o_ = (SVipLevelNotify)_o1_;
			if (newlevel != _o_.newlevel) return false;
			if (totalcharge != _o_.totalcharge) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += newlevel;
		_h_ += (int)totalcharge;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(newlevel).append(",");
		_sb_.append(totalcharge).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SVipLevelNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = newlevel - _o_.newlevel;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(totalcharge - _o_.totalcharge);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


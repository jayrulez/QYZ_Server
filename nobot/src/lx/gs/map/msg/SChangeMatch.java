
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SChangeMatch__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SChangeMatch extends __SChangeMatch__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573529;

	public int getType() {
		return 6573529;
	}

	public int matchtype;
	public long nextmatchtime;

	public SChangeMatch() {
	}

	public SChangeMatch(int _matchtype_, long _nextmatchtime_) {
		this.matchtype = _matchtype_;
		this.nextmatchtime = _nextmatchtime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(matchtype);
		_os_.marshal(nextmatchtime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		matchtype = _os_.unmarshal_int();
		nextmatchtime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SChangeMatch) {
			SChangeMatch _o_ = (SChangeMatch)_o1_;
			if (matchtype != _o_.matchtype) return false;
			if (nextmatchtime != _o_.nextmatchtime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += matchtype;
		_h_ += (int)nextmatchtime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(matchtype).append(",");
		_sb_.append(nextmatchtime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SChangeMatch _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = matchtype - _o_.matchtype;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(nextmatchtime - _o_.nextmatchtime);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


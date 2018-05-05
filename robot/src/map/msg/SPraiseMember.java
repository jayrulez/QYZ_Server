
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SPraiseMember__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SPraiseMember extends __SPraiseMember__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6704699;

	public int getType() {
		return 6704699;
	}

	public long from;
	public long to;

	public SPraiseMember() {
	}

	public SPraiseMember(long _from_, long _to_) {
		this.from = _from_;
		this.to = _to_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(from);
		_os_.marshal(to);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		from = _os_.unmarshal_long();
		to = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SPraiseMember) {
			SPraiseMember _o_ = (SPraiseMember)_o1_;
			if (from != _o_.from) return false;
			if (to != _o_.to) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)from;
		_h_ += (int)to;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(from).append(",");
		_sb_.append(to).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SPraiseMember _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(from - _o_.from);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(to - _o_.to);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


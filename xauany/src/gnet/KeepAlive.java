
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __KeepAlive__ extends xio.Protocol { }

/** client和xlink之间的协议 协议号范围[100,110)
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class KeepAlive extends __KeepAlive__ {
	@Override
	protected void process() {
		
		this.send(getConnection());
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 100;

	public int getType() {
		return 100;
	}

	public long code;

	public KeepAlive() {
	}

	public KeepAlive(long _code_) {
		this.code = _code_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(code);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		code = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof KeepAlive) {
			KeepAlive _o_ = (KeepAlive)_o1_;
			if (code != _o_.code) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)code;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(code).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(KeepAlive _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(code - _o_.code);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


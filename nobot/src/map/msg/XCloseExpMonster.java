
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XCloseExpMonster__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XCloseExpMonster extends __XCloseExpMonster__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6708455;

	public int getType() {
		return 6708455;
	}

	public int monsterid;

	public XCloseExpMonster() {
	}

	public XCloseExpMonster(int _monsterid_) {
		this.monsterid = _monsterid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(monsterid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		monsterid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XCloseExpMonster) {
			XCloseExpMonster _o_ = (XCloseExpMonster)_o1_;
			if (monsterid != _o_.monsterid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += monsterid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(monsterid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(XCloseExpMonster _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = monsterid - _o_.monsterid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


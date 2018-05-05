
package lx.gs.role.title.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __STitleGetNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class STitleGetNotify extends __STitleGetNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557905;

	public int getType() {
		return 6557905;
	}

	public lx.gs.role.title.msg.Title title; // 获取到的称号

	public STitleGetNotify() {
		title = new lx.gs.role.title.msg.Title();
	}

	public STitleGetNotify(lx.gs.role.title.msg.Title _title_) {
		this.title = _title_;
	}

	public final boolean _validator_() {
		if (!title._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(title);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		title.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof STitleGetNotify) {
			STitleGetNotify _o_ = (STitleGetNotify)_o1_;
			if (!title.equals(_o_.title)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += title.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(title).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(STitleGetNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = title.compareTo(_o_.title);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


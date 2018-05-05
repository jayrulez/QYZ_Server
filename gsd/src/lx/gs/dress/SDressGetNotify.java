
package lx.gs.dress;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SDressGetNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SDressGetNotify extends lx.gs.dress.__SDressGetNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555774;

	public int getType() {
		return 6555774;
	}

	public lx.gs.dress.Dress dress;

	public SDressGetNotify() {
		dress = new lx.gs.dress.Dress();
	}

	public SDressGetNotify(lx.gs.dress.Dress _dress_) {
		this.dress = _dress_;
	}

	public final boolean _validator_() {
		if (!dress._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(dress);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		dress.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SDressGetNotify) {
			SDressGetNotify _o_ = (SDressGetNotify)_o1_;
			if (!dress.equals(_o_.dress)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += dress.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(dress).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SDressGetNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = dress.compareTo(_o_.dress);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


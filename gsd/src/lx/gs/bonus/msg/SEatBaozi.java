
package lx.gs.bonus.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEatBaozi__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEatBaozi extends __SEatBaozi__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6570416;

	public int getType() {
		return 6570416;
	}

	public int eattype;
	public int addtili;

	public SEatBaozi() {
	}

	public SEatBaozi(int _eattype_, int _addtili_) {
		this.eattype = _eattype_;
		this.addtili = _addtili_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(eattype);
		_os_.marshal(addtili);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		eattype = _os_.unmarshal_int();
		addtili = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEatBaozi) {
			SEatBaozi _o_ = (SEatBaozi)_o1_;
			if (eattype != _o_.eattype) return false;
			if (addtili != _o_.addtili) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += eattype;
		_h_ += addtili;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(eattype).append(",");
		_sb_.append(addtili).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SEatBaozi _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = eattype - _o_.eattype;
		if (0 != _c_) return _c_;
		_c_ = addtili - _o_.addtili;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


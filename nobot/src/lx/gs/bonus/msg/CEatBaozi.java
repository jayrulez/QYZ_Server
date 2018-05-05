
package lx.gs.bonus.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEatBaozi__ extends xio.Protocol { }

/** 吃包子或者补领
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEatBaozi extends __CEatBaozi__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6580377;

	public int getType() {
		return 6580377;
	}

	public final static int EAT_LUNCH = 1;
	public final static int EAT_DINNER = 2;
	public final static int RE_EAT_LUNCH = 3;
	public final static int RE_EAT_DINNER = 4;

	public int eattype; // 吃包子的类型

	public CEatBaozi() {
	}

	public CEatBaozi(int _eattype_) {
		this.eattype = _eattype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(eattype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		eattype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEatBaozi) {
			CEatBaozi _o_ = (CEatBaozi)_o1_;
			if (eattype != _o_.eattype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += eattype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(eattype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CEatBaozi _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = eattype - _o_.eattype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}



package lx.gs.family.msg;

import lx.gs.family.PFamilyPray;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CFamilyPray__ extends xio.Protocol { }

/** 家族烧香
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CFamilyPray extends __CFamilyPray__ {
	@Override
	protected void process() {
		new PFamilyPray(this).validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6558601;

	public int getType() {
		return 6558601;
	}

	public final static int TYPE_COMMON = 1; // 普通香
	public final static int TYPE_ADVANCE = 2; // 高级香
	public final static int TYPE_SUPER = 3; // 至尊香

	public int burntype; // 烧香的类型，普通，高级还是至尊

	public CFamilyPray() {
	}

	public CFamilyPray(int _burntype_) {
		this.burntype = _burntype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(burntype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		burntype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFamilyPray) {
			CFamilyPray _o_ = (CFamilyPray)_o1_;
			if (burntype != _o_.burntype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += burntype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(burntype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CFamilyPray _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = burntype - _o_.burntype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


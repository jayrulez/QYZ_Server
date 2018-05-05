
package lx.gs.equip.normalequip;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CPerfuseEquip__ extends xio.Protocol { }

/** 装备灌注
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CPerfuseEquip extends __CPerfuseEquip__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582031;

	public int getType() {
		return 6582031;
	}

	public int bagtype;
	public int pos;
	public int unbindonly; // 是否只用非绑定的

	public CPerfuseEquip() {
	}

	public CPerfuseEquip(int _bagtype_, int _pos_, int _unbindonly_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.unbindonly = _unbindonly_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.marshal(unbindonly);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		unbindonly = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CPerfuseEquip) {
			CPerfuseEquip _o_ = (CPerfuseEquip)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (unbindonly != _o_.unbindonly) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += unbindonly;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(unbindonly).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CPerfuseEquip _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		_c_ = unbindonly - _o_.unbindonly;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


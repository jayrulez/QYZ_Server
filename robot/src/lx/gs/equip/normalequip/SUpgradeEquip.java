
package lx.gs.equip.normalequip;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SUpgradeEquip__ extends xio.Protocol { }

/** 神器进阶
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SUpgradeEquip extends __SUpgradeEquip__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581237;

	public int getType() {
		return 6581237;
	}

	public int bagtype1; // 包裹类型
	public int pos1; // 格子号
	public int bagtype2; // 包裹类型
	public int pos2; // 格子号

	public SUpgradeEquip() {
	}

	public SUpgradeEquip(int _bagtype1_, int _pos1_, int _bagtype2_, int _pos2_) {
		this.bagtype1 = _bagtype1_;
		this.pos1 = _pos1_;
		this.bagtype2 = _bagtype2_;
		this.pos2 = _pos2_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype1);
		_os_.marshal(pos1);
		_os_.marshal(bagtype2);
		_os_.marshal(pos2);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype1 = _os_.unmarshal_int();
		pos1 = _os_.unmarshal_int();
		bagtype2 = _os_.unmarshal_int();
		pos2 = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SUpgradeEquip) {
			SUpgradeEquip _o_ = (SUpgradeEquip)_o1_;
			if (bagtype1 != _o_.bagtype1) return false;
			if (pos1 != _o_.pos1) return false;
			if (bagtype2 != _o_.bagtype2) return false;
			if (pos2 != _o_.pos2) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype1;
		_h_ += pos1;
		_h_ += bagtype2;
		_h_ += pos2;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype1).append(",");
		_sb_.append(pos1).append(",");
		_sb_.append(bagtype2).append(",");
		_sb_.append(pos2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SUpgradeEquip _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype1 - _o_.bagtype1;
		if (0 != _c_) return _c_;
		_c_ = pos1 - _o_.pos1;
		if (0 != _c_) return _c_;
		_c_ = bagtype2 - _o_.bagtype2;
		if (0 != _c_) return _c_;
		_c_ = pos2 - _o_.pos2;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


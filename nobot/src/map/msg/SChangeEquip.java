
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SChangeEquip__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SChangeEquip extends __SChangeEquip__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6716289;

	public int getType() {
		return 6716289;
	}

	public java.util.ArrayList<map.msg.EquipBrief> equips;

	public SChangeEquip() {
		equips = new java.util.ArrayList<map.msg.EquipBrief>();
	}

	public SChangeEquip(java.util.ArrayList<map.msg.EquipBrief> _equips_) {
		this.equips = _equips_;
	}

	public final boolean _validator_() {
		for (map.msg.EquipBrief _v_ : equips)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(equips.size());
		for (map.msg.EquipBrief _v_ : equips) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.EquipBrief _v_ = new map.msg.EquipBrief();
			_v_.unmarshal(_os_);
			equips.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SChangeEquip) {
			SChangeEquip _o_ = (SChangeEquip)_o1_;
			if (!equips.equals(_o_.equips)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += equips.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(equips).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


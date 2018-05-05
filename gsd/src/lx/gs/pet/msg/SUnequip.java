
package lx.gs.pet.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SUnequip__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SUnequip extends __SUnequip__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565090;

	public int getType() {
		return 6565090;
	}

	public int equipposition;
	public int bagposition;

	public SUnequip() {
	}

	public SUnequip(int _equipposition_, int _bagposition_) {
		this.equipposition = _equipposition_;
		this.bagposition = _bagposition_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(equipposition);
		_os_.marshal(bagposition);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		equipposition = _os_.unmarshal_int();
		bagposition = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SUnequip) {
			SUnequip _o_ = (SUnequip)_o1_;
			if (equipposition != _o_.equipposition) return false;
            return bagposition == _o_.bagposition;
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += equipposition;
		_h_ += bagposition;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(equipposition).append(",");
		_sb_.append(bagposition).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SUnequip _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = equipposition - _o_.equipposition;
		if (0 != _c_) return _c_;
		_c_ = bagposition - _o_.bagposition;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


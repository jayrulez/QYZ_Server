
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SUnequipPet__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SUnequipPet extends __SUnequipPet__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6716926;

	public int getType() {
		return 6716926;
	}

	public int petmodelid;

	public SUnequipPet() {
	}

	public SUnequipPet(int _petmodelid_) {
		this.petmodelid = _petmodelid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(petmodelid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		petmodelid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SUnequipPet) {
			SUnequipPet _o_ = (SUnequipPet)_o1_;
			if (petmodelid != _o_.petmodelid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += petmodelid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(petmodelid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SUnequipPet _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = petmodelid - _o_.petmodelid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


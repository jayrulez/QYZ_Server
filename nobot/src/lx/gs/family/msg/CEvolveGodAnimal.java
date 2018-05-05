
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEvolveGodAnimal__ extends xio.Protocol { }

/** 进化神兽
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEvolveGodAnimal extends __CEvolveGodAnimal__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6580241;

	public int getType() {
		return 6580241;
	}

	public int animalid; // 要进化的神兽id

	public CEvolveGodAnimal() {
	}

	public CEvolveGodAnimal(int _animalid_) {
		this.animalid = _animalid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(animalid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		animalid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEvolveGodAnimal) {
			CEvolveGodAnimal _o_ = (CEvolveGodAnimal)_o1_;
			if (animalid != _o_.animalid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += animalid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(animalid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CEvolveGodAnimal _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = animalid - _o_.animalid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


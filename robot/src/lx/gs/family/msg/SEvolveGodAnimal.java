
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEvolveGodAnimal__ extends xio.Protocol { }

/** 进化神兽
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEvolveGodAnimal extends __SEvolveGodAnimal__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573598;

	public int getType() {
		return 6573598;
	}

	public lx.gs.family.msg.FamilyGodAnimal animal; // 要进化的神兽id

	public SEvolveGodAnimal() {
		animal = new lx.gs.family.msg.FamilyGodAnimal();
	}

	public SEvolveGodAnimal(lx.gs.family.msg.FamilyGodAnimal _animal_) {
		this.animal = _animal_;
	}

	public final boolean _validator_() {
		if (!animal._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(animal);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		animal.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEvolveGodAnimal) {
			SEvolveGodAnimal _o_ = (SEvolveGodAnimal)_o1_;
			if (!animal.equals(_o_.animal)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += animal.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(animal).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SEvolveGodAnimal _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = animal.compareTo(_o_.animal);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}



package lx.gs.family.msg;

import lx.gs.family.PRaiseGodAnimal;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRaiseGodAnimal__ extends xio.Protocol { }

/** 进化神兽
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRaiseGodAnimal extends __CRaiseGodAnimal__ {
	@Override
	protected void process() {
		new PRaiseGodAnimal(this).validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565983;

	public int getType() {
		return 6565983;
	}

	public final static int RAISE_TYPE_XUNIBI = 1;
	public final static int RAISE_TYPE_YUANBAO = 2;

	public int raisetype; // 培养的类型，金币培养还是元宝培养
	public int animalid; // 要进化的神兽id

	public CRaiseGodAnimal() {
	}

	public CRaiseGodAnimal(int _raisetype_, int _animalid_) {
		this.raisetype = _raisetype_;
		this.animalid = _animalid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(raisetype);
		_os_.marshal(animalid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		raisetype = _os_.unmarshal_int();
		animalid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRaiseGodAnimal) {
			CRaiseGodAnimal _o_ = (CRaiseGodAnimal)_o1_;
			if (raisetype != _o_.raisetype) return false;
			if (animalid != _o_.animalid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += raisetype;
		_h_ += animalid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(raisetype).append(",");
		_sb_.append(animalid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CRaiseGodAnimal _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = raisetype - _o_.raisetype;
		if (0 != _c_) return _c_;
		_c_ = animalid - _o_.animalid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}



package lx.gs.login;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import gnet.link.Onlines;
import lx.gs.role.NamePool;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRandomName__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRandomName extends __CRandomName__ {
	@Override
	protected void process() {
		Onlines.getInstance().sendResponse(this, new SRandomName(NamePool.Ins.randomUniqueName(gender)));
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553609;

	public int getType() {
		return 6553609;
	}

	public int gender;

	public CRandomName() {
	}

	public CRandomName(int _gender_) {
		this.gender = _gender_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(gender);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		gender = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRandomName) {
			CRandomName _o_ = (CRandomName)_o1_;
			if (gender != _o_.gender) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += gender;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(gender).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CRandomName _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = gender - _o_.gender;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


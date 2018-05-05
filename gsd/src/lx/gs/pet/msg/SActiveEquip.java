
package lx.gs.pet.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SActiveEquip__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SActiveEquip extends __SActiveEquip__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6572628;

	public int getType() {
		return 6572628;
	}

	public long petid;

	public SActiveEquip() {
	}

	public SActiveEquip(long _petid_) {
		this.petid = _petid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(petid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		petid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SActiveEquip) {
			SActiveEquip _o_ = (SActiveEquip)_o1_;
            return petid == _o_.petid;
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)petid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(petid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SActiveEquip _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(petid - _o_.petid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}


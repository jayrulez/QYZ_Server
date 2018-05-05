
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XAddPetBuff__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XAddPetBuff extends __XAddPetBuff__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6699349;

	public int getType() {
		return 6699349;
	}

	public int buffid;

	public XAddPetBuff() {
	}

	public XAddPetBuff(int _buffid_) {
		this.buffid = _buffid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(buffid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		buffid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XAddPetBuff) {
			XAddPetBuff _o_ = (XAddPetBuff)_o1_;
			if (buffid != _o_.buffid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += buffid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(buffid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(XAddPetBuff _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = buffid - _o_.buffid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

